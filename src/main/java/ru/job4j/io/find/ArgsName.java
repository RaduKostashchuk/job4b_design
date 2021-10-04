package ru.job4j.io.find;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException(Starter.getUsage());
        }
        for (String arg : args) {
            String[] entry = arg.split("=");
            if (entry.length != 2 || !entry[0].startsWith("-")) {
                throw new IllegalArgumentException("Wrong arguments format.");
            }
            entry[0] = entry[0].substring(1);
            values.put(entry[0], entry[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }
}
