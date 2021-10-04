package ru.job4j.io.find;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Starter {

    private static void validate(ArgsName argsName, int argLength) {
        String type = argsName.get("t");
        if (argLength != 4) {
        throw new IllegalArgumentException(getUsage());
        }
        if (!type.equals("mask") && !type.equals("filename") && !type.equals("regex")) {
            throw new IllegalArgumentException(getUsage());
        }
    }

    protected static String getUsage() {
        return """
                Usage: java -jar find.jar [options]
                Options:
                -d=<path> Where to search
                -n=<filename> Exact filename, mask or regex
                      Use double quotes(") for names with spaces and for regex
                -t=<type> Search type, can be 'mask', 'filename', 'regex'
                -o=<out> Filename to store result or stdout""";
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        validate(argsName, args.length);
        String root = argsName.get("d");
        String searchType = argsName.get("t");
        MyFileVisitor finder = (searchType.equals("filename")) ? new FindByName(argsName)
                : searchType.equals("mask") ? new FindByMask(argsName) : new FindByRegex(argsName);
        try {
            Files.walkFileTree(Path.of(root), finder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Writer.writeResult(finder.getResult(), argsName);
    }
}
