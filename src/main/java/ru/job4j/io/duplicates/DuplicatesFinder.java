package ru.job4j.io.duplicates;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        searchDuplicates(Path.of("./"));
    }

    public static void searchDuplicates(Path root) throws IOException {
        DuplicatesVisitor searcher = new DuplicatesVisitor();
        Files.walkFileTree(root, searcher);
        searcher.findDuplicates();
    }
}