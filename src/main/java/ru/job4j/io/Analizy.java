package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        String line, last = "";
        boolean isIn = false;
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            while ((line = in.readLine()) != null) {
                if (line.matches("^400.+|^500.+")) {
                    if (!isIn) {
                        out.print(line.split(" ")[1] + ";");
                    }
                    isIn = true;
                    last = line;
                    continue;
                }
                if (!line.matches("^400.+|^500.+") && isIn) {
                    out.println(line.split(" ")[1] + ";");
                    isIn = false;
                }
            }
            if (isIn && last.matches("^400.+|^500.+")) {
                out.println(last.split(" ")[1]);
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
