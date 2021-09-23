package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Analizy {
    public void unavailable(String source, String target) {
        List<String> lines = new ArrayList<>();
        List<String> result = new ArrayList<>();
        String[] line;
        boolean isIn = false;
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            lines = in.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < lines.size(); i++) {
            line = lines.get(i).split(" ");
            if (line[0].matches("400|500")) {
                if (!isIn || i == lines.size() - 1) {
                    result.add(line[1]);
                }
                isIn = true;
                continue;
            }
            if (!line[0].matches("400|500") && isIn) {
                result.add(line[1]);
                isIn = false;
            }
        }
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            for (int i = 0; i < result.size(); i += 2) {
                out.println(result.get(i) + ";" + result.get(i + 1) + ";");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy obj = new Analizy();
        obj.unavailable("./data/log.txt",  "./data/unavailable.csv");
    }
}
