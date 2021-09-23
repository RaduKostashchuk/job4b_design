package ru.job4j.io;

import java.io.File;
import java.util.Objects;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("File does not exist: %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not a directory: %s", file.getAbsoluteFile()));
        }
        System.out.printf("Size is: %s%n", file.getTotalSpace());
        for (File subFile : Objects.requireNonNull(file.listFiles())) {
            System.out.printf("%s %d%n", subFile.getName(), subFile.length());
        }
    }
}
