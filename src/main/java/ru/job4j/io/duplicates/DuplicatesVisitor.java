package ru.job4j.io.duplicates;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, List<Path>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        FileProperty fp = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (!map.containsKey(fp)) {
            List<Path> list = new ArrayList<>();
            list.add(file.toAbsolutePath());
            map.put(fp, list);
        } else {
            map.get(fp).add(file.toAbsolutePath());
        }
        return FileVisitResult.CONTINUE;
    }

    public void findDuplicates() {
        for (FileProperty key : map.keySet()) {
            if (map.get(key).size() > 1) {
                map.get(key).forEach(System.out::println);
            }
        }
    }
}
