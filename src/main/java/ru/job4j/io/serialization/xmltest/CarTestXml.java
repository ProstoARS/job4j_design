package ru.job4j.io.serialization.xmltest;

import ru.job4j.io.serialization.jsontest.Color;
import ru.job4j.io.serialization.jsontest.PersonJson;

import java.util.List;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "carTest")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarTestXml {
    @XmlAttribute
    private String model;

    @XmlAttribute
    private Color color;

    @XmlAttribute
    private int carMileage;

    @XmlAttribute
    private boolean actualInspection;

    @XmlElementWrapper(name = "inspectionMileage")
    @XmlElement(name = "status")
    private List<Integer> inspectionMileage;
    @XmlElement
    private PersonXml person;

    public CarTestXml() {
    }

    public CarTestXml(String model, Color color, int carMileage, boolean actualInspection,
                      List<Integer> inspectionMileage, PersonXml person) {
        this.model = model;
        this.color = color;
        this.carMileage = carMileage;
        this.actualInspection = actualInspection;
        this.inspectionMileage = inspectionMileage;
        this.person = person;
    }

    @Override
    public String toString() {
        return "CarTest{"
                + "model='" + model + '\''
                + ", color=" + color
                + ", carMileage=" + carMileage
                + ", actualInspection=" + actualInspection
                + ", inspectionMileage=" + inspectionMileage
                + ", person=" + person
                + '}';
    }
}
