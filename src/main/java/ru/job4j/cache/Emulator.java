package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Emulator {
    private static final String SET_DIR = "1";
    private static final String LOAD_FILE_INTO = "2";
    private static final String LOAD_FILE_FROM = "3";
    private static final String EXIT = "4";

    private static void printMenu(String cacheDir, AbstractCache<String, String> cache) {
        System.out.println("--------------------------");
        System.out.println("Current caching directory: " + cacheDir);
        System.out.println("--------------------------");
        System.out.println("Cash content: ");
        getCacheContent(cache);
        System.out.println("--------------------------");
        System.out.println("Action:");
        System.out.println("1. Choose caching directory.");
        System.out.println("2. Load file into cache.");
        System.out.println("3. Load file from cache.");
        System.out.println("4. Exit.");
        System.out.println("--------------------------");
    }

    private static String setCacheDir(Scanner scanner) {
        String result;
        System.out.println("Enter caching directory name:");
        result = scanner.nextLine();
        if (!Files.isDirectory(Path.of(result))) {
            System.out.println("No such directory");
            result = "";
        }
        return result;
    }

    private static void loadDirToCache(String cacheDir, AbstractCache<String, String> cache) {
        try {
            Files.list(Path.of(cacheDir))
                    .filter(p -> p.toString().matches("^.+\\.txt$"))
                    .forEach(p -> {
                        String filename = p.getFileName().toString();
                        cache.put(filename, cache.load(filename));
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getCacheContent(AbstractCache<String, String> cache) {
        if (cache != null) {
            for (String key : cache.cache.keySet()) {
                System.out.println(key);
            }
        }
    }

    private static void getCacheDirContent(String cacheDir) {
        if (!cacheDir.equals("")) {
            try {
                Files.list(Path.of(cacheDir)).forEach(s -> System.out.println(s.getFileName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void loadFileToCache(String cacheDir, Scanner scanner, AbstractCache<String, String> cache) {
        System.out.println("Choose file to load into cache:");
        getCacheDirContent(cacheDir);
        String file = scanner.nextLine();
        if (!Files.exists(Path.of(cacheDir, file)) || file.equals("")) {
            System.out.println("File doesn't exist");
            return;
        }
        if (!file.matches("^.+\\.txt$")) {
            System.out.println("Unsupported format");
            return;
        }
        cache.put(file, cache.load(file));
        System.out.println("File loaded.");
    }

    private static void loadFileFromCache(Scanner scanner, AbstractCache<String, String> cache) {
        System.out.println("Enter file to load from cache:");
        String file = scanner.nextLine();
        if (!file.matches("^.+\\.txt$")) {
            System.out.println("Unsupported format");
            return;
        }
        String content = cache.get(file);
        System.out.println("File content:");
        System.out.println(content);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        String cacheDir = "";
        AbstractCache<String, String> cache = null;
        while (!userInput.equals(EXIT)) {
            printMenu(cacheDir, cache);
            userInput = scanner.nextLine();
            if (userInput.equals(SET_DIR)) {
                cacheDir = setCacheDir(scanner);
                if (!cacheDir.equals("")) {
                    cache = new DirFileCache(cacheDir);
                }
                loadDirToCache(cacheDir, cache);
                continue;
            }
            if (userInput.equals(LOAD_FILE_INTO)) {
                if (cacheDir.equals("")) {
                    System.out.println("Choose caching directory.");
                    continue;
                }
                loadFileToCache(cacheDir, scanner, cache);
                continue;
            }
            if (userInput.equals(LOAD_FILE_FROM)) {
                if (cache == null) {
                    System.out.println("Cache is empty.");
                    continue;
                }
                loadFileFromCache(scanner, cache);
            }
        }
    }
}
