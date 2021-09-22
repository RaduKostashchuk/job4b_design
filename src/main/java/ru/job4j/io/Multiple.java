package ru.job4j.io;

import java.io.FileOutputStream;

public class Multiple {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("c:\\temp\\result.txt")) {
            for (int i = 1; i < 10; i++) {
                String string = "1 * " + i + " = " + i;
                out.write(string.getBytes());
                out.write(System.lineSeparator().getBytes());
            }
        }   catch (Exception e) {
            e.printStackTrace();
        }
    }
}
