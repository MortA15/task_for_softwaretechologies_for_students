package org.softwaretechnologies.employee;

public class Tester extends Employee {
    protected Tester(String name, int baseSalary) {
        super(name, baseSalary);
    }

    @Override
    public int getMonthSalary(int month) {
        return 0;
    }
}
