package ru.job4j.io;

import java.io.FileInputStream;
import java.util.Arrays;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            int read;
            StringBuilder input = new StringBuilder();
            while ((read = in.read()) != -1) {
                input.append((char) read);
            }
            Arrays.stream(
                    input.toString().split(System.lineSeparator())
                    )
                    .mapToInt(Integer::parseInt)
                    .forEach(x -> {
                        if (x % 2 == 0) {
                            System.out.println("Then number: " + x + " is even");
                        } else {
                            System.out.println("Then number: " + x + " is odd");
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
