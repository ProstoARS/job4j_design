package ru.job4j.io.serialization.jsontest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class MainJson {
    public static void main(String[] args) {
        CarTestJson car1 = new CarTestJson("Ford", Color.BLACK, 60000, true,
                List.of(15000, 24500, 36300, 48000, 59000), new PersonJson("Potapov Maxim", 38, true));

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car1));

        String textJson = "{\"model\":\"BMV\","
                + "\"color\":\"WHITE\","
                + "\"carMileage\":30000,"
                + "\"actualInspection\":false,"
                + "\"inspectionMileage\":[12000,21000],"
                + "\"personJson\":{\"name\":\"Pablo Sanchez\",\"age\":41,\"sex\":true}}";

        CarTestJson car2 = gson.fromJson(textJson, CarTestJson.class);
        System.out.println(car2);
    }
}
