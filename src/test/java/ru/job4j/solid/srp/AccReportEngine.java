package ru.job4j.solid.srp;

import java.util.function.Predicate;

public class AccReportEngine implements Report {
    private final Store store;

    public AccReportEngine(MemStore store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(" RUR").append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
