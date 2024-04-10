import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class DepartmentDA {

        private HashMap<String, Employee> empMap;
    
        public HashMap<String, Employee> getEmpMap() {
            return empMap;
        }
        public DepartmentDA () throws FileNotFoundException {
            Scanner deptFile = new Scanner(new FileReader("dep.csv"));
            empMap = new HashMap<>();
            
            deptFile.nextLine();
            
            while (deptFile.hasNext()) {
                String[] newRow = new String[2];
                newRow = deptFile.nextLine().split(",");
    
                Department department = new Department();
                department.setDepCode(newRow[0].trim());
                department.setDepName(newRow[1].trim());
    
                readDepEmp(department); 
                department.setEmployeeMap(empMap);
                
                Double salary = 0.00;
                for (Map.Entry<String, Employee> entryMap : department.getEmployeeMap().entrySet()) {
                    salary += entryMap.getValue().getSalary();
                }
                department.setDepTotalSalary(salary);
                
                printDepartment(department);
            }
            deptFile.close();
        }
        private void readDepEmp(Department department) throws FileNotFoundException {
            Scanner deptEmpFile = new Scanner(new FileReader("deptemp.csv"));
            empMap.clear();
            deptEmpFile.nextLine();
    
            Integer key = 0;
            while (deptEmpFile.hasNext()) {
                String deptEmpRow = deptEmpFile.nextLine();
                String[] yRow = new String[3];
                yRow = deptEmpRow.split(",");
    
                if (department.getDepCode().equals(yRow[0])) {
                    EmployeeDA employeeDA = new EmployeeDA(yRow[1].trim(), Double.parseDouble(yRow[2].trim()));
                    
                    empMap.put(yRow[1].trim()+key, employeeDA.getEmployee());
                    key++; 
                }   
            }
            deptEmpFile.close();
        }

        private void printDepartment(Department department) {
            DecimalFormat df = new DecimalFormat("###,###.00");
            System.out.println("Department code: " + department.getDepCode());
            System.out.println("Department name: " + department.getDepName());
            System.out.println("Department total salary: " + df.format(department.getDepTotalSalary()));
            System.out.println("---------Details ------------------");
            System.out.println("EmpNo\t\tEmployee Name\tSalary");

            for (Map.Entry<String, Employee> entryMap : department.getEmployeeMap().entrySet()) {
                System.out.print(entryMap.getValue().getEmpNo() + "\t");
                System.out.print(entryMap.getValue().getLastName() + "," + entryMap.getValue().getFirstName() + "\t");
                System.out.println(df.format(entryMap.getValue().getSalary()));
            }
            System.out.println();
        }    
    }