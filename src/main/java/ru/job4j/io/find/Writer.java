package ru.job4j.io.find;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Writer {

    public static void writeResult(List<String> result, ArgsName argsName) {
        String method = argsName.get("o");
        if (method.equals("stdout")) {
            for (String el : result) {
                System.out.println(el);
            }
        } else {
            String file = argsName.get("o");
            while (Files.exists(Path.of(file))) {
                System.out.println("File " + file + " already exists, overwrite?\n"
                    + "[yes\\no]\n");
                Scanner sc = new Scanner(System.in);
                if (sc.nextLine().toLowerCase().matches("y|yes")) {
                    break;
                } else {
                    System.out.println("Enter filename:");
                    file = sc.nextLine();
                }
            }
            try (PrintWriter pw = new PrintWriter(file)) {
                for (String el : result) {
                    pw.println(el);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
