package ru.job4j.solid.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class XMLReportEngine implements Report {
    private final Store store;

    public XMLReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String result = "";
        try {
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            Employees list = new Employees(store.findBy(filter));
            StringWriter writer = new StringWriter();
            marshaller.marshal(list, writer);
            result = writer.getBuffer().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
