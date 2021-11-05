package ru.job4j.solid.srp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public class HTMLReportEngine implements Report {
    private final Store store;

    public HTMLReportEngine(MemStore store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        DateFormat df = new SimpleDateFormat("dd MMM yyyy");
        text.append("<html><head><title>Report</title></head>")
                .append("<body><table>")
                .append("<tr> <th>Name</th> <th>Hired</th> <th>Fired</th> <th>Salary</th></tr>");
        for (Employee employee : store.findBy(filter)) {
            text.append("<tr><td>")
                    .append(employee.getName())
                    .append("</td>")
                    .append("<td>")
                    .append(df.format(employee.getHired().getTime()))
                    .append("</td>")
                    .append("<td>")
                    .append(df.format(employee.getFired().getTime()))
                    .append("</td>")
                    .append("<td>")
                    .append(employee.getSalary())
                    .append("</td></tr>");
        }
        text.append("</table></body></html>");
        return text.toString();
    }
}
