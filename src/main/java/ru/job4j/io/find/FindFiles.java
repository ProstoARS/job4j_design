package ru.job4j.io.find;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FindFiles {

    public static void find(List<File> sources, File target) {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(target))) {
            for (File file : sources) {
                out.write(file.toString().getBytes(StandardCharsets.UTF_8));
                out.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void validation(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Not enough arguments. Please enter: -d=ROOT_FOLDER -n=SEARCH_ARGUMENT"
                    + " -t=SEARCH_TYPE_(mask, name, regex) -o=TARGET_DIR");
        }
    }

    public static void validArgs(Path source, String search, String type, File output) {
        if (!source.toFile().exists() && !source.toFile().isDirectory()) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar -d=ROOT_FOLDER.");
        }
        if (search == null) {
            throw new IllegalArgumentException("enter file attribute: -n=SEARCH_ARGUMENT");
        }
        if (type == null) {
            throw new IllegalArgumentException("enter type search argument:"
                    + " -t=TYPE (\"name\" or \"mask\" or \"regex\")");
        }
        if (output == null) {
            throw new IllegalArgumentException("specify the target path for the archive: -o=TARGET");
        }
    }

    public static void main(String[] args) throws IOException {
        validation(args);
        ArgsName an = ArgsName.of(args);
        Path source = Paths.get(an.get("d"));
        File output = new File(an.get("o"));
        String searchArg = an.get("n");
        String switchType = an.get("t");
        validArgs(source, searchArg, switchType, output);
        TypeFinderSwitch tps = new TypeFinderSwitch(switchType, searchArg, source);
        find(tps.get(), output);
    }
}
