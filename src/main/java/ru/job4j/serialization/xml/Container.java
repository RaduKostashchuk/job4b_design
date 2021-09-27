package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "container")
@XmlAccessorType(XmlAccessType.FIELD)
public class Container {
    @XmlAttribute
    private boolean refrigerated;
    @XmlAttribute
    private String owner;
    @XmlAttribute
    private double weight;
    private Product product;
    @XmlElementWrapper(name = "operators")
    @XmlElement(name = "operator")
    private String[] operators;

    public Container() { }

    public Container(boolean refrigerated, String owner, double weight, Product product, String...operators) {
        this.refrigerated = refrigerated;
        this.owner = owner;
        this.weight = weight;
        this.product = product;
        this.operators = operators;
    }

    @Override
    public String toString() {
        return "Container{"
                + "refrigerated=" + refrigerated
                + ", owner='" + owner + '\''
                + ", weight=" + weight
                + ", product=" + product
                + ", operators=" + Arrays.toString(operators)
                + '}';
    }

    public static void main(String[] args) throws Exception {
        Container container = new Container(true, "Magnit", 21.550,
                new Product("fish"), "Swift", "Evergreen", "SibConTrans");
        JAXBContext context = JAXBContext.newInstance(Container.class);
        Marshaller marsh = context.createMarshaller();
        marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marsh.marshal(container, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Unmarshaller unmarsh = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Container result = (Container) unmarsh.unmarshal(reader);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
