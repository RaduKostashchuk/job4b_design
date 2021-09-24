package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<Path> source, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            for (Path path : source) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void validate(ArgsName zip, int argsLen) {
        if (argsLen != 3) {
            throw new IllegalArgumentException("Wrong argument number. "
                    + "Usage java -jar pack.jar -d=SOURCE_DIR -e=EXCLUDE_EXT -o=TARGET_FILE");
        }
        Path sourceDir = Path.of(zip.get("d"));
        if (!Files.isDirectory(sourceDir)) {
            throw new IllegalArgumentException(String.format("Not a directory: %s", sourceDir));
        }
        String extension = zip.get("e");
        if (!extension.matches("^[0-9a-zA-Z]+$")) {
            throw new IllegalArgumentException("Illegal file extension format. "
                    + "Should be one or more characters of [0-9a-zA-Z] pattern.");
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName zip = ArgsName.of(args);
        validate(zip, args.length);
        Path source = Path.of(zip.get("d"));
        String excludeExt = zip.get("e");
        Path target = Path.of(zip.get("o"));
        Predicate<Path> condition = p -> !p.toFile().getName().endsWith(excludeExt);
        packFiles(Search.search(source, condition), target);
    }
}
