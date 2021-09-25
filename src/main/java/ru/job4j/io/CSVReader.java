package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {

    public static void handle(ArgsName argsName) {
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String[] filter = argsName.get("filter").split(",");
        String out = argsName.get("out");
        StringJoiner line = new StringJoiner(delimiter);
        StringBuilder result = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(path), Charset.forName("WINDOWS-1251"))) {
            LinkedList<String> colNames =
                    new LinkedList<>(Arrays.asList(scanner.nextLine().split(delimiter)));
            int[] colToPrint = new int[filter.length];
            for (int i = 0; i < filter.length; i++) {
                if (!colNames.contains(filter[i])) {
                    throw new IllegalArgumentException("Input file doesn't have such column: " + filter[i]);
                }
                colToPrint[i] = colNames.indexOf(filter[i]);
                line.add(filter[i]);
            }
            result.append(line)
                    .append(System.lineSeparator());
            while (scanner.hasNext()) {
                String[] output = scanner.nextLine().split(delimiter);
                line = new StringJoiner(delimiter);
                for (int i = 0; i < filter.length; i++) {
                        line.add(output[colToPrint[i]]);
                }
                result.append(line)
                        .append(System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (out.equals("stdout")) {
            System.out.println(result);
        } else {
            try (PrintWriter writer = new PrintWriter(new FileWriter(out, Charset.forName("WINDOWS-1251")))) {
                writer.print(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private static void validate(ArgsName argsName, int argLength) {
        if (argLength != 4) {
            throw new IllegalArgumentException("Wrong number of arguments. "
                    + "Usage: java -jar csvReader.jar -path=file.csv -delimiter=\"character\" -out=stdout -filter=name,age");
        }
        String delimiter = argsName.get("delimiter");
        if (delimiter == null || !delimiter.matches("[,;\t\s]")) {
            throw new IllegalArgumentException("Illegal delimiter, use one of [,:<tab><space>].");
        }
        String inputFile = argsName.get("path");
        if (!Files.exists(Path.of(inputFile))) {
            throw new IllegalArgumentException("Input file doesn't exist.");
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        validate(argsName, args.length);
        handle(argsName);
    }
}
