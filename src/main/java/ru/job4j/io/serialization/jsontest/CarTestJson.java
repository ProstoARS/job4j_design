package ru.job4j.io.serialization.jsontest;

import java.util.ArrayList;
import java.util.List;

public class CarTestJson {

    private final String model;
    private final Color color;
    private final int carMileage;
    private final boolean actualInspection;
    private final List<Integer> inspectionMileage;
    private final Person person;

    public CarTestJson(String model, Color color, int carMileage, boolean actualInspection, List<Integer> inspectionMileage, Person person) {
        this.model = model;
        this.color = color;
        this.carMileage = carMileage;
        this.actualInspection = actualInspection;
        this.inspectionMileage = inspectionMileage;
        this.person = person;
    }

    @Override
    public String toString() {
        return "CarTestJson{"
                + "model='" + model + '\''
                + ", color=" + color
                + ", carMileage=" + carMileage
                + ", actualInspection=" + actualInspection
                + ", inspectionMileage=" + inspectionMileage
                + ", person=" + person
                + '}';
    }
}
