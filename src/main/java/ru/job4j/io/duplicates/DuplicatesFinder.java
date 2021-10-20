package ru.job4j.io.duplicates;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        searchDuplicates(Path.of("./")).forEach(System.out::println);
    }

    public static List<Path> searchDuplicates(Path root) throws IOException {
        DuplicatesVisitor searcher = new DuplicatesVisitor();
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}