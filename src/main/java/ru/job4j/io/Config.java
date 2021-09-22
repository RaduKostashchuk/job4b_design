package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            in.lines()
                    .filter(s -> !s.contains("#"))
                    .filter(s -> !s.matches("^\s*$"))
                    .forEach(s -> {
                        String[] lines = s.split("=");
                        values.put(lines[0],
                                (lines.length == 1) ? " " : lines[1]);
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String key : values.keySet()) {
            if (key.matches("^\s*$") || values.get(key).matches("^\s*$")) {
                throw new IllegalArgumentException();
            }
        }
    }

    public String value(String name) {
        return values.get(name);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            in.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("./data/app_without_comments.properties"));
    }
}
