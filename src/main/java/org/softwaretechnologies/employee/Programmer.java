package org.softwaretechnologies.employee;

import java.time.LocalDate;
import java.time.YearMonth;

public class Programmer extends Employee {
    public Programmer(String name, int baseSalary) {
        super(name, baseSalary);
    }

    @Override
    public int getMonthSalary(int month) {
        return baseSalary;
    }
}
