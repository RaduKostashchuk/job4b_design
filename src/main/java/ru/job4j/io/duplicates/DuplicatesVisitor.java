package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, String> files = new HashMap<>();

    public List<String> getDups() {
        List<String> result = new ArrayList<>();
        for (FileProperty property : files.keySet()) {
            String[] dups = files.get(property).split(System.lineSeparator());
            if (dups.length > 1) {
                result.addAll(Arrays.stream(dups).collect(Collectors.toList()));
            }
        }
        return result;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty property = new FileProperty(file.toFile().getName(), file.toFile().length());
        files.computeIfPresent(property, (k, v) -> v += file + System.lineSeparator());
        files.putIfAbsent(property, file + System.lineSeparator());
        return super.visitFile(file, attrs);
    }
}
