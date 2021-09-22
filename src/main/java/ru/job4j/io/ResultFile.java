package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class ResultFile {
    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("c:\\temp\\result.txt")
                )
        )) {
            out.println("Hi");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
