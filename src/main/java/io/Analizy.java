package io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        List<String> listLog = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String temp = null;
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                if (temp != null) {
                    if (line.startsWith("400") || line.startsWith("500")) {
                        continue;
                    } else {
                        listLog.add(line.substring(line.indexOf(" ") + 1) + ";");
                        temp = null;
                    }
                }
                if (line.startsWith("400") || line.startsWith("500")) {
                    temp = line;
                    listLog.add(line.substring(line.indexOf(" ") + 1) + ";");
                }
            }
            for (int i = 0; i < listLog.size(); i += 2) {
                out.println(listLog.get(i) + listLog.get(i + 1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy log = new Analizy();
        log.unavailable("./data/server.log", "./data/analysis.csv");
    }
}
