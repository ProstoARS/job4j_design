package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                if (line.startsWith("#") || line.isEmpty()) {
                    continue;
                }
                if (!line.contains("=")) {
                    throw new IllegalArgumentException();
                }
                String[] subStr = line.split("=");
                if (Objects.equals(subStr[0], " ") || subStr[0].equals("") || subStr.length > 2) {
                    throw new IllegalArgumentException();
                }
                values.put(subStr[0], subStr[1]);
            }
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