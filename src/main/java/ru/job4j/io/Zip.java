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

    public static void validation(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Тot enough arguments. Please enter: -d=ROOT_FOLDER -e=EXCLUDE_FILE"
                    + " -o=TARGET_DIR");
        }
    }

    public static void validArgs(Path source, String extension, File output) {
        if (!source.toFile().exists() && !source.toFile().isDirectory()) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar -d=ROOT_FOLDER.");
        }
        if (extension == null) {
            throw new IllegalArgumentException("enter file extension to exclude from archive: -e=EXTENSION");
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
        String extension = an.get("e");
        validArgs(source, extension, output);
        List<Path> filesWithOutUnnecessary = Search.search(source,
                p -> !p.toFile().getName().endsWith(extension));
        List<File> filesToArchive = filesWithOutUnnecessary.stream().map(Path::toFile).collect(Collectors.toList());
        packFiles(filesToArchive, output);
    }
}