package ru.job4j.io.serialization.jsontest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "carTest")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarTest {
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
    private Person person;

    public CarTest() {
    }

    public CarTest(String model, Color color, int carMileage, boolean actualInspection,
                   List<Integer> inspectionMileage, Person person) {
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

    public static void main(String[] args) throws JAXBException {
        CarTest car2 = new CarTest("honda", Color.BLUE, 72000, true,
                List.of(10000, 15000, 30000, 50000, 65000),
                new Person("Trofimov", 35, true));
        File file = new File("car2.xml");
        JAXBContext context = JAXBContext.newInstance(CarTest.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            marshaller.marshal(car2, fos);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        marshaller.marshal(car2, System.out);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (FileInputStream fio = new FileInputStream("car2.xml")) {
            CarTest rsl = (CarTest) unmarshaller.unmarshal(fio);
            System.out.println(rsl);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
}
