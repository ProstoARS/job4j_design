package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CSVReader {
    private static final List<String[]> LIST = new ArrayList<>();

    public static void handle(ArgsName argsName) {
        try (BufferedInputStream br = new BufferedInputStream(new FileInputStream(argsName.get("path")))) {
            byte[] data = br.readAllBytes();
            Scanner scanner = new Scanner(new ByteArrayInputStream(data)).useDelimiter(System.lineSeparator());
            while (scanner.hasNext()) {
                LIST.add(scanner.next().split(argsName.get("delimiter")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] columns = LIST.get(0);
        String[] filter = argsName.get("filter").split(",");
        List<Integer> index = new ArrayList<>();
        for (int i = 0; i < columns.length; i++) {
            for (String str : filter) {
                if (columns[i].equals(str)) {
                    index.add(i);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String[] strings : LIST) {
            for (int j = 0; j < index.size(); j++) {
                sb.append(strings[index.get(j)]);
                if (j + 1 < index.size()) {
                    sb.append(";");
                }
            }
            sb.append(System.lineSeparator());
        }

        String outString = sb.toString();
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(argsName.get("out"), StandardCharsets.UTF_8))) {
            pw.print(outString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
