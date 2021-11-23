package ru.job4j.io.serialization.xmltest;

import ru.job4j.io.serialization.jsontest.Color;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

public class MainXml {
    public static void main(String[] args) throws JAXBException {
        CarTestXml car2 = new CarTestXml("honda", Color.BLUE, 72000, true,
                List.of(10000, 15000, 30000, 50000, 65000),
                new PersonXml("Trofimov", 35, true));
        File file = new File("car2.xml");
        JAXBContext context = JAXBContext.newInstance(CarTestXml.class);
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
            CarTestXml rsl = (CarTestXml) unmarshaller.unmarshal(fio);
            System.out.println(rsl);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
}
