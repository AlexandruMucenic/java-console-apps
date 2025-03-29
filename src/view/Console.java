package view;

import service.EmployeeService;
import java.util.Scanner;
import domain.Employee;
import domain.Payslip;
import repo.EmployeeRepository;
import view.Console;

public class Console {
    private static Scanner scanner = new Scanner(System.in);
    static EmployeeRepository employeeRepository = new EmployeeRepository();
    static EmployeeService employeeService = new EmployeeService(employeeRepository);

    public static void menu() {
        System.out.println("1. Add/Update employee.");
        System.out.println("2. Show employees.");
        System.out.println("3. Delete employee.");
        System.out.println("4. Show Payslip for employee.");
        System.out.println("5. Show employees with salary below average.");
        System.out.println("6. Show employees eligible for retiring this year.");
        System.out.println("7. Show employees with personal debt. ");
        System.out.println("0. Exit.");
    }

    public static void printEmployeeTableHead() {
        System.out.format("|%-6s|%-15s|%-15s|%-4s|%-15s|%-15s|%-20s|\n",
                "ID", "First Name ", "Last Name", "Age", "Gross Salary", "Net Salary", "Monthly Debt Amount");
    }

    public static void printEmployee(Employee employee) {
        System.out.format("|%-6d|%-15s|%-15s|%-4d|%-15s|%-15s|%-20s|\n", employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getAge(), String.format("%.2f", employee.getGrossSalary()),
                String.format("%.2f", employee.getNetSalary()), String.format("%.2f", employee.getMonthlyDebtAmount()));
    }

    public static void printPayslipTableHead() {
        System.out.format("|%-6s|%-15s|%-15s|%-15s|%-15s|%-15s|\n",
                "ID", "Gross Salary", "CASS", "CAS", "Revenue Tax", "Net Salary");
    }

    public static void printPayslip(Payslip payslip) {
        System.out.format("|%-6d|%-15s|%-15s|%-15s|%-15s|%-15s|\n", payslip.getEmployeeId(),
                String.format("%.2f", payslip.getGrossSalary()),
                String.format("%.2f", payslip.getCass()),
                String.format("%.2f", payslip.getCas()),
                String.format("%.2f", payslip.getRevenueTax()),
                String.format("%.2f", payslip.getNetSalary()));
    }

    public static void addEmployee() {
        System.out.println("Enter employee id.");
        int id = scanner.nextInt();
        System.out.println("Enter employee first name.");
        String firstName = scanner.next();
        System.out.println("Enter employee last name.");
        String lastName = scanner.next();
        System.out.println("Enter employee age.");
        int age = scanner.nextInt();
        System.out.println("Enter employee gross salary.");
        double grossSalary = scanner.nextDouble();
        System.out.println("Enter employee monthly debt amount or 0.");
        double monthlyDebtAmount = scanner.nextDouble();

        employeeService.addEmployee(id, firstName, lastName, age, grossSalary, monthlyDebtAmount);
    }

    public static void showEmployees() {
        printEmployeeTableHead();
        for (Employee employee : employeeService.getAllEmployees()) {
            printEmployee(employee);
        }
        System.out.println();
    }

    public static void deleteEmployee() {
        System.out.println("Enter the id of the employee.");
        int id = scanner.nextInt();
        employeeService.deleteEmployee(id);
    }

    public static void showPayslip() {
        System.out.println("Enter the id of the employee.");
        int id = scanner.nextInt();
        Payslip payslip = employeeService.getEmployeePayslip(id);
        printPayslipTableHead();
        printPayslip(payslip);

        System.out.println();
    }

    public static void showAveragePay() {
        System.out.println("Average pay is: " + employeeService.getAveragePay());
    }

    public static void showEmployeesBelowPayAverage() {
        printEmployeeTableHead();
        for (Employee employee : employeeService.getEmployeesBelowPayAverage()) {
            printEmployee(employee);
        }
        System.out.println();
    }

    public static void showRetiringEmployees() {
        printEmployeeTableHead();
        for (Employee employee : employeeService.getRetiringEmployees()) {
            printEmployee(employee);
        }
        System.out.println();
    }

    public static void showEmployeesWithDebt() {
        printEmployeeTableHead();
        for (Employee employee : employeeService.getDebtEmployees()) {
            printEmployee(employee);
        }
        System.out.println();
    }

    public static void runMenu() {
        boolean flag = true;
        while (flag) {
            menu();
            int option = scanner.nextInt();
            switch (option) {
                case 0:
                    flag = false;
                    break;
                case 1:
                    addEmployee();
                    break;
                case 2:
                    showEmployees();
                    break;
                case 3:
                    deleteEmployee();
                    break;
                case 4:
                    showPayslip();
                    break;
                case 5:
                    showAveragePay();
                    showEmployeesBelowPayAverage();
                    break;
                case 6:
                    showRetiringEmployees();
                    break;
                case 7:
                    showEmployeesWithDebt();
                    break;
            }
        }
    }

    public static void main(String[] args) {
        runMenu();
    }
}
