package ru.job4j.io.encoding;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

public class UsageEncoding {
    public String readFile(String path) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path, Charset.forName("WINDOWS-1251")))) {
            br.lines().forEach(s -> builder.append(s).append(System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public void writeDataInFile(String path, List<String> data) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            data.forEach(pw::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = ".\\data\\text.txt";
        UsageEncoding encoding = new UsageEncoding();
        List<String> list = List.of(
                "Новая строка 1",
                "Новая строка 2",
                "Новая строка 3",
                "Новая строка 4",
                "Новая строка 5"
        );
        encoding.writeDataInFile(path, list);
        String result = encoding.readFile(path);
        System.out.println(result);
    }
}
