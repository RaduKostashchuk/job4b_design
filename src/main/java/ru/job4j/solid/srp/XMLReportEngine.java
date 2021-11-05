package ru.job4j.solid.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.function.Predicate;

public class XMLReportEngine implements Report {
    private final Store store;

    public XMLReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder result = new StringBuilder();
        try {
            JAXBContext context = JAXBContext.newInstance(Employee.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            for (Employee employee : store.findBy(filter)) {
                StringWriter writer = new StringWriter();
                marshaller.marshal(employee, writer);
                result.append(writer.getBuffer().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
