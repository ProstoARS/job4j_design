package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            values = read.lines()
                    .filter(a -> !a.startsWith("#") && !a.isEmpty())
                    .collect(Collectors.toMap(a -> {
                        if (!a.contains("=") || a.contains("==")) {
                            throw new IllegalArgumentException();
                        }
                        String[] subStr = a.split("=");
                        if (Objects.equals(subStr[0], " ") || subStr[0].equals("")) {
                            throw new IllegalArgumentException();
                        }
                        return subStr[0];
                    }, a -> {
                        String[] subStr = a.split("=");
                        return subStr[1];
                    }));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        String str = values.get(key);
        if (str == null) {
            throw new UnsupportedOperationException("Don't impl this method yet!");
        }
       return str;
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }

}