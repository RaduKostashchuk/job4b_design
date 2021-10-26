package ru.job4j.cache;

import ru.job4j.gc.ref.SoftDemo;

import javax.swing.plaf.basic.BasicButtonUI;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Path;
import java.util.Scanner;

public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        StringBuilder content = new StringBuilder();
        try (Scanner reader = new Scanner(new FileReader(cachingDir + "/" + key))) {
            while (reader.hasNextLine()) {
                    content.append(reader.nextLine());
            }
        } catch (IOException e) {
        e.printStackTrace();
        }
        put(key, content.toString());
        return content.toString();
    }
}
