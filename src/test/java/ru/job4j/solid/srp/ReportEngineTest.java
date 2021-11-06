package ru.job4j.solid.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenJSONGenerated() {
        Gson gson = new GsonBuilder().create();
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Egor", now, now, 100);
        String expected = gson.toJson(List.of(worker1, worker2));
        store.add(worker1);
        store.add(worker2);
        Report engine = new JSONReportEngine(store);
        String result = engine.generate(em -> true);
        assertThat(result, is(expected));
    }

    @Test
    public void whenXMLGenerated() throws Exception {
        JAXBContext context = JAXBContext.newInstance(Employees.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Egor", now, now, 100);
        Employees list = new Employees(List.of(worker1, worker2));
        Report engine = new XMLReportEngine(store);
        StringWriter writer = new StringWriter();
        marshaller.marshal(list, writer);
        String expected = writer.getBuffer().toString();
        store.add(worker1);
        store.add(worker2);
        String result = engine.generate(em -> true);
        assertThat(result, is(expected));
    }

    @Test
    public void whenAccReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new AccReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(" RUR").append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHTMLReportGenerated() {
        DateFormat df = new SimpleDateFormat("dd MMM yyyy");
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new HTMLReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("<html><head><title>Report</title></head>")
                .append("<body><table>")
                .append("<tr> <th>Name</th> <th>Hired</th> <th>Fired</th> <th>Salary</th></tr>")
                .append("<tr><td>")
                .append(worker.getName())
                .append("</td>")
                .append("<td>")
                .append(df.format(worker.getHired().getTime()))
                .append("</td>")
                .append("<td>")
                .append(df.format(worker.getFired().getTime()))
                .append("</td>")
                .append("<td>")
                .append(worker.getSalary())
                .append("</td></tr>")
                .append("</table></body></html>");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHRReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Egor", now, now, 200);
        store.add(worker1);
        store.add(worker2);
        Report engine = new HRReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

}