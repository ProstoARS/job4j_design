package ru.job4j.io.serialization.jsontest;

import org.json.JSONObject;

import java.util.List;

public class MainJson {
    public static void main(String[] args) {
        CarTestJson car1 = new CarTestJson("Ford", Color.BLACK, 60000, true,
                List.of(15000, 24500, 36300, 48000, 59000), new PersonJson("Potapov Maxim", 38, true));
        String rsl = new JSONObject(car1).toString();
        System.out.println(rsl);
    }
}
