package ru.job4j.io.serialization.jsontest;

import java.util.List;

public class CarTestJson {

    private String model;
    private Color color;
    private int carMileage;
    private boolean actualInspection;
    private List<Integer> inspectionMileage;
    private PersonJson personJson;

    public CarTestJson(String model, Color color, int carMileage, boolean actualInspection,
                       List<Integer> inspectionMileage, PersonJson personJson) {
        this.model = model;
        this.color = color;
        this.carMileage = carMileage;
        this.actualInspection = actualInspection;
        this.inspectionMileage = inspectionMileage;
        this.personJson = personJson;
    }

    @Override
    public String toString() {
        return "CarTest{"
                + "model='" + model + '\''
                + ", color=" + color
                + ", carMileage=" + carMileage
                + ", actualInspection=" + actualInspection
                + ", inspectionMileage=" + inspectionMileage
                + ", person=" + personJson
                + '}';
    }
}
