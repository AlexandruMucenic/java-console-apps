package domain;

public class Employee {
    private int id;
    private String lastName;
    private String firstName;
    private int age;
    private double grossSalary;
    private double netSalary;
    private boolean monthlyDebt;
    private double monthlyDebtAmount;

    public Employee(int id, String firstName, String lastName, int age, double grossSalary, double monthlyDebtAmount) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.grossSalary = grossSalary;
        this.netSalary = grossSalary - (grossSalary * 0.10) - (grossSalary * 0.25)
                - ((grossSalary - (grossSalary * 0.10) - (grossSalary * 0.25)) * 0.10);
        this.monthlyDebt = monthlyDebtAmount > 0;
        this.monthlyDebtAmount = monthlyDebtAmount;
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public double getNetSalary() {
        return netSalary;
    }

    public boolean getMonthlyDebt() {
        return monthlyDebt;
    }

    public double getMonthlyDebtAmount() {
        return monthlyDebtAmount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public void setNetSalary(double grossSalary) {
        this.netSalary = grossSalary - (grossSalary * 0.10) - (grossSalary * 0.25)
                - ((grossSalary - (grossSalary * 0.10) - (grossSalary * 0.25)) * 0.10);
    }

    public void setMonthlyDebt(boolean monthlyDebt) {
        this.monthlyDebt = monthlyDebtAmount > 0;
    }

    public void setMonthlyDebtAmount(double monthlyDebtAmount) {
        this.monthlyDebtAmount = monthlyDebtAmount;
    }

}
