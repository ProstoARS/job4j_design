package ru.job4j.io.serialization.xmltest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonXml {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private int age;
    @XmlAttribute
    private boolean sex;

    public PersonXml() {
    }

    public PersonXml(String name, int age, boolean sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", sex=" + sex
                + '}';
    }
}
