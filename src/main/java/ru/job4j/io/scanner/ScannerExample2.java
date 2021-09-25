package ru.job4j.io.scanner;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ScannerExample2 {
    public static void main(String[] args) {
        var data = "user@mail.com, admin@test.com, abuse@test.ru";
        var scanner = new Scanner(new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8)))
                .useDelimiter(", ");
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
    }
}
