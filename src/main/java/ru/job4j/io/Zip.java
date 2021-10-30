package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : sources) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        if (args.length != 3) {
            throw new IllegalArgumentException("Тot enough arguments. Please enter: -d=ROOT_FOLDER -e=EXCLUDE_FILE"
                    + " -o=TARGET_DIR");
        }
        ArgsName an = ArgsName.of(args);
        Path source = Paths.get(an.get("d"));
        if (!source.toFile().exists()) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar -d=ROOT_FOLDER.");
        }
        List<Path> filesWithOutUnnecessary = Search.search(source,
                p -> !p.toFile().getName().endsWith(an.get("e")));
        List<File> filesToArchive = filesWithOutUnnecessary.stream().map(Path::toFile).collect(Collectors.toList());
        File output = new File(an.get("o"));
        packFiles(filesToArchive, output);
    }
}