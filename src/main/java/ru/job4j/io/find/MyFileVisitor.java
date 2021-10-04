package ru.job4j.io.find;

import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.util.List;

public interface MyFileVisitor extends FileVisitor<Path> {
    List<String> getResult();
}