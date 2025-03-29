package repo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import domain.Employee;

public class ReadFile {
    public static List<Employee> readEmployeesData() {
        try {
            String filePath = "./employees.txt";
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int numberOfEmployees = Integer.parseInt(bufferedReader.readLine());

            List<Employee> employeesList = new ArrayList<Employee>();

            for (int i = 0; i < numberOfEmployees; i++) {
                String currentLine = bufferedReader.readLine();
                String[] attributes = currentLine.split(",");

                int employeeId = Integer.parseInt(attributes[0]);
                String employeeFirstName = attributes[1];
                String employeeLastName = attributes[2];
                int employeeAge = Integer.parseInt(attributes[3]);
                double employeeGrossSalary = Double.parseDouble(attributes[4]);
                double employeeMonthlyDebt = Double.parseDouble(attributes[5]);

                employeesList.add(new Employee(employeeId, employeeFirstName, employeeLastName, employeeAge,
                        employeeGrossSalary, employeeMonthlyDebt));
            }
            bufferedReader.close();

            return employeesList;

        } catch (Exception eroare) {
            System.out.println(eroare);
            return null;
        }
    }

}