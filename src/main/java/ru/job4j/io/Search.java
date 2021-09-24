package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validate(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validate(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Wrong argument number. "
                    + "Usage java -jar search.jar START_FOLDER FILE_EXTENSION");
        }
        if (!args[1].matches("^[0-9a-zA-Z]+$")) {
            throw new IllegalArgumentException("Illegal file extension format. "
                    + "Should be one or more characters of [0-9a-zA-Z] pattern.");
        }
    }
}
