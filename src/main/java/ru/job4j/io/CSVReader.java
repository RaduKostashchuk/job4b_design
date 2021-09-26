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
        Arrays.stream(filter).forEach(line::add);
        result.append(line)
                .append(System.lineSeparator());
        try (Scanner scanner = new Scanner(new File(path), Charset.forName("WINDOWS-1251"))) {
            var header = new ArrayList<>(Arrays.asList(scanner.nextLine().split(delimiter)));
            int[] colToPrint = parseHeader(header, filter);
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
        writeResult(result.toString(), out);
    }

    private static int[] parseHeader(ArrayList<String> header, String[] filter) {
        int[] colToPrint = new int[filter.length];
        for (int i = 0; i < filter.length; i++) {
            int index = header.indexOf(filter[i]);
            if (index == -1) {
                throw new IllegalArgumentException("Input file doesn't have such column: " + filter[i]);
            }
            colToPrint[i] = index;
        }
        return colToPrint;
    }

    private static void writeResult(String outStr, String outFile) {
        if (outFile.equals("stdout")) {
            System.out.println(outStr);
        } else {
            try (PrintWriter writer = new PrintWriter(new FileWriter(outFile, Charset.forName("WINDOWS-1251")))) {
                writer.print(outStr);
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
