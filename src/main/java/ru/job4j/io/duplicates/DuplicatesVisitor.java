package ru.job4j.io.duplicates;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    Set<FileProperty> set = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        FileProperty fp = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (!set.add(fp)) {
            System.out.println(file.toAbsolutePath());
        }
        return FileVisitResult.CONTINUE;
    }
}
