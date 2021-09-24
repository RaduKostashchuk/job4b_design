package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        StringBuilder result = new StringBuilder();
        String line, last = "";
        boolean isIn = false;
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            while ((line = in.readLine()) != null) {
                if (line.matches("^400.+|^500.+")) {
                    if (!isIn) {
                        result.append(line.split(" ")[1])
                                .append(";");
                    }
                    isIn = true;
                    last = line;
                    continue;
                }
                if (!line.matches("^400.+|^500.+") && isIn) {
                    result.append(line.split(" ")[1])
                            .append(";")
                            .append(System.lineSeparator());
                    isIn = false;
                }
            }
            if (isIn && last.matches("^400.+|^500.+")) {
                result.append(last.split(" ")[1])
                        .append(";")
                        .append(System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy obj = new Analizy();
        obj.unavailable("./data/log.txt",  "./data/unavailable.csv");
    }
}
