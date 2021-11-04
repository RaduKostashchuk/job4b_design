package ru.job4j.solid.srp;

import org.openjdk.jmh.annotations.Param;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CreateDictionary {

    public void askUser() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!checkInput(input)) {
            System.out.println("Enter string.");
            input = scanner.nextLine();
        }
        Files.writeString(Path.of("c:\\temp\\out.txt"),
                "123" + System.lineSeparator(),
                StandardOpenOption.APPEND);
    }

    public boolean checkInput(String input) throws IOException {
        boolean result = true;
        if (input.length() < 3) {
            System.out.println("Illegal input.");
            result = false;
        }
        List<String> oldDict = Files.lines(Path.of("C:\\temp\\dictionary.txt"))
                                .collect(Collectors.toList());
        if (oldDict.contains(input)) {
            System.out.println("String already exists.");
            result = false;
        }
        return result;
    }
}
