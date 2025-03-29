package domain;

public class Payslip {
    private int employeeId;
    private double grossSalary;
    private double netSalary;
    private double cass;
    private double cas;
    private double revenueTax;

    public Payslip(int employeeId, double grossSalary) {
        this.employeeId = employeeId;
        this.grossSalary = grossSalary;
        this.cass = grossSalary * 0.10; // calculation for Romania Health Insurance
        this.cas = grossSalary * 0.25; // calculation for Romania Pension Insurance
        this.revenueTax = ((grossSalary - (grossSalary * 0.10) - (grossSalary * 0.25)) * 0.10);
        this.netSalary = grossSalary - (cass + cas + revenueTax);
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public double getNetSalary() {
        return netSalary;
    }

    public double getCass() {
        return cass;
    }

    public double getCas() {
        return cas;
    }

    public double getRevenueTax() {
        return revenueTax;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public void setNetSalary(double netSalary) {
        this.netSalary = netSalary;
    }

    public void setCass(double cass) {
        this.cass = cass;
    }

    public void setCas(double cas) {
        this.cas = cas;
    }

    public void setRevenueTax(double revenueTax) {
        this.revenueTax = revenueTax;
    }
}
