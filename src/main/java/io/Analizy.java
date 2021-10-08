package io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Analizy {
    public void unavailable(String source, String target) {
        List<String[]> listStrings = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            listStrings = in.lines().map(a -> a.split(" "))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> listLog = new ArrayList<>();
        String temp = null;
        for (String[] str : listStrings) {
            if (temp != null) {
                if (str[0].equals("400") || str[0].equals("500")) {
                    continue;
                } else {
                    listLog.add(str[1] + ";");
                    temp = null;
                }
            }
            if (str[0].equals("400") || str[0].equals("500")) {
                temp = str[0];
                listLog.add(str[1] + ";");
            }
        }
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            for (int i = 0; i < listLog.size(); i += 2) {
                out.println(listLog.get(i) + listLog.get(i + 1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
