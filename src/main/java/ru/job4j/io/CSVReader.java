package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CSVReader {
    private final List<String[]> list = new ArrayList<>();

    public CSVReader(String[] args) {
        validate(args);
    }

    public void handle(ArgsName argsName) {
           try (Scanner scanner = new Scanner(new FileInputStream(argsName.get("path")))
                   .useDelimiter(System.lineSeparator())) {
            while (scanner.hasNext()) {
                list.add(scanner.next().split(argsName.get("delimiter")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] columns = list.get(0);
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
        for (String[] strings : list) {
            for (int j = 0; j < index.size(); j++) {
                sb.append(strings[index.get(j)]);
                if (j + 1 < index.size()) {
                    sb.append(";");
                }
            }
            sb.append(System.lineSeparator());
        }
        if (argsName.get("out").equals("stdout")) {
            System.out.println(sb);
        } else {
            writeFile(sb.toString(), argsName.get("out"));
        }
    }

    public void writeFile(String str, String path) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(path, StandardCharsets.UTF_8))) {
            pw.print(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void validate(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Not enough arguments. Please enter: -path=CSV_DOCUMENT"
                    + " -filter=ENTER_FILTER_COLUMNS"
                    + " -out=OUTPUT_FOLDER or \"stdout\" for out in console"
                    + " -delimiter=ENTER_DELIMITER");
        }
    }

    public static void main(String[] args) {
        ArgsName ar = ArgsName.of(args);
        CSVReader csv = new CSVReader(args);
                csv.handle(ar);
    }
}
