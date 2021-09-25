package ru.job4j.io.scanner;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ScannerExample3 {
    public static void main(String[] args) throws IOException {
        var data = "10 AA 1F EE 23 7D";
        var file = File.createTempFile("data", null);
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            out.write(data.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (var scanner = new Scanner(file).useRadix(16)) {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextInt());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
