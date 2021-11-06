package ru.job4j.solid.srp;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Employees {
    @XmlElement(name = "employee")
    private List<Employee> list;

    public Employees() { }

    public Employees(List<Employee> list) {
        this.list = list;
    }

    public List<Employee> getList() {
        return list;
    }
}