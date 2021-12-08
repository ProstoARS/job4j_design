package ru.job4j.io.find;

import ru.job4j.io.Search;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TypeFinderSwitch {

    private final String arg;

    private final String attribute;

    private final Path source;

    public TypeFinderSwitch(String arg, String attribute, Path source) {
        this.arg = arg;
        this.attribute = attribute;
        this.source = source;
    }

    public List<File> get() throws IOException {
        List<Path> temp;
        switch (arg) {
            case "name":
                temp = Search.search(source, path -> path.toFile().getName().equals(attribute));
                break;
            case "mask":
                String maskToRegex = attribute;
                maskToRegex = maskToRegex.replaceAll("\\*", "\\.\\*");
                maskToRegex = maskToRegex.replaceAll("\\?", "\\.\\{1\\}");
                Pattern patt = Pattern.compile(maskToRegex);
                temp = Search.search(source, path -> {
                    Matcher matcher = patt.matcher(path.toFile().getName());
                    return matcher.matches();
                });
                break;
            case "regex":
                Pattern pattern = Pattern.compile(attribute);
                temp = Search.search(source, path -> {
                   Matcher matcher = pattern.matcher(path.toFile().getName());
                   return matcher.matches();
                });
                break;
            default:
                System.out.println("wrong argument -t. Please enter: \"name\", \"mask\", \"regex\"");
                throw new IllegalArgumentException();
        }
        return temp.stream().map(Path::toFile).collect(Collectors.toList());
    }
}
