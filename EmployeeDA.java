import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EmployeeDA {
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public EmployeeDA(String empNo, Double salary) throws FileNotFoundException {
        readEmpFromFile(empNo, salary);
    }

    private void readEmpFromFile(String empNo, Double salary) throws FileNotFoundException {
        Scanner empFile = null;
        try {
            empFile = new Scanner(new FileReader("emp.csv"));
            empFile.nextLine();

            while (empFile.hasNext()) {
                String[] x = empFile.nextLine().split(",");

                if (empNo.equals(x[0])) {
                    employee = new Employee();
                    employee.setEmpNo(x[0]);
                    employee.setLastName(x[1]);
                    employee.setFirstName(x[2]);
                    employee.setSalary(salary);
                    break; 
                }
            }
        } finally {
            if (empFile != null) {
                empFile.close();
            }
        }
    }
}