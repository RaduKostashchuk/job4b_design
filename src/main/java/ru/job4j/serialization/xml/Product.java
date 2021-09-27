package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "product")
public class Product {
    @XmlAttribute
    private String name;

    public Product() { }

    public Product(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{"
                + "name='" + name + '\''
                + '}';
    }
}
