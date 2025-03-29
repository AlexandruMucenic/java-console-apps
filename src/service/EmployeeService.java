package service;

import java.util.List;

import domain.Employee;
import domain.Payslip;
import repo.EmployeeRepository;

public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return this.employeeRepository.getAllEmployeesRepository();
    }

    public void addEmployee(int id, String firstName, String lastName, int age, double grossSalary,
            double monthlyDebtAmount) {
        Employee newEmployee = new Employee(id, firstName, lastName, age, grossSalary, monthlyDebtAmount);
        Employee employeeToBeAdded = employeeRepository.addEmployeeRepository(newEmployee);
        if (employeeToBeAdded == null) {
            employeeRepository.updateEmployeeRepository(newEmployee);
        }
    }

    public void deleteEmployee(int id) {
        employeeRepository.deleteEmployeeRepository(id);
    }

    public Payslip getEmployeePayslip(int employeeId) {
        return employeeRepository.findPayslipById(employeeId);
    }

    public double getAveragePay() {
        return employeeRepository.getAveragePay();
    }

    public List<Employee> getEmployeesBelowPayAverage() {
        return employeeRepository.getEmployeesBelowPayAverageRepository();
    }

    public List<Employee> getRetiringEmployees() {
        return employeeRepository.getRetiringEmployeesRepository();
    }

    public List<Employee> getDebtEmployees() {
        return employeeRepository.getEmployeesWithDebtRepository();
    }
}
