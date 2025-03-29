package repo;

import java.util.ArrayList;
import java.util.List;

import domain.Employee;
import domain.Payslip;

public class EmployeeRepository {
    private List<Employee> employees = ReadFile.readEmployeesData();
    private List<Payslip> payslips = new ArrayList<Payslip>(addPayslipforEmployee());

    public List<Payslip> addPayslipforEmployee() {
        List<Payslip> payslipsAdded = new ArrayList<Payslip>();
        for (Employee employee : this.employees) {
            payslipsAdded.add(new Payslip(employee.getId(), employee.getGrossSalary()));
        }
        return payslipsAdded;
    }

    public Employee findEmployeeById(int id) {
        for (Employee currentEmployee : this.employees) {
            if (id == currentEmployee.getId()) {
                return currentEmployee;
            }
        }
        return null;
    }

    public Payslip findPayslipById(int employeeId) {
        for (Payslip payslip : this.payslips) {
            if (employeeId == payslip.getEmployeeId()) {
                return payslip;
            }
        }
        return null;
    }

    public EmployeeRepository deleteEmployeeRepository(int id) {
        if (findEmployeeById(id) != null) {
            this.employees.remove(findEmployeeById(id));
            return this;
        }
        return null;
    }

    public Employee addEmployeeRepository(Employee employee) {
        if (findEmployeeById(employee.getId()) == null) {
            this.employees.add(employee);
            this.payslips.add(new Payslip(employee.getId(), employee.getGrossSalary()));
            return employee;
        }
        return null;
    }

    public List<Employee> getAllEmployeesRepository() {
        return this.employees;
    }

    public EmployeeRepository updateEmployeeRepository(Employee updatedEmployee) {
        for (Employee employee : this.employees) {
            if (employee.getId() == updatedEmployee.getId()) {
                employee.setLastName(updatedEmployee.getLastName());
                employee.setFirstName(updatedEmployee.getFirstName());
                employee.setAge(updatedEmployee.getAge());
                employee.setGrossSalary(updatedEmployee.getGrossSalary());
                employee.setMonthlyDebtAmount(updatedEmployee.getMonthlyDebtAmount());
                return this;
            }
        }
        return null;
    }

    public double getAveragePay() {
        double payTotal = 0;
        for (Employee employee : this.employees) {
            payTotal += employee.getNetSalary();
        }
        return payTotal / employees.toArray().length;
    }

    public List<Employee> getEmployeesBelowPayAverageRepository() {
        List<Employee> payBelowEmployees = new ArrayList<>();
        for (Employee employee : this.employees) {
            if (employee.getNetSalary() < this.getAveragePay()) {
                payBelowEmployees.add(employee);
            }
        }
        return payBelowEmployees;
    }

    public List<Employee> getRetiringEmployeesRepository() {
        List<Employee> retiringEmployees = new ArrayList<>();
        for (Employee employee : this.employees) {
            if (employee.getAge() >= 65) {
                retiringEmployees.add(employee);
            }
        }
        return retiringEmployees;
    }

    public List<Employee> getEmployeesWithDebtRepository() {
        List<Employee> employeesWithDebt = new ArrayList<>();
        for (Employee employee : this.employees) {
            if (employee.getMonthlyDebt()) {
                employeesWithDebt.add(employee);
            }
        }
        return employeesWithDebt;
    }
}
