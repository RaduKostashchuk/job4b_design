package ru.job4j.io.find;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FindByRegex implements MyFileVisitor {
    private final ArgsName argsName;
    private final List<String> result;

    public FindByRegex(ArgsName argsName) {
        this.argsName = argsName;
        this.result = new ArrayList<>();
    }

    @Override
    public List<String> getResult() {
        return this.result;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        String pattern = argsName.get("n");
        String fileName = file.getFileName().toString();
        if (fileName.matches(pattern)) {
            result.add(file.toAbsolutePath().toString());
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        return FileVisitResult.CONTINUE;
    }
}
