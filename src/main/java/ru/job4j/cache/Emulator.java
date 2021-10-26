package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Scanner;

public class Emulator {

    private static void printMenu(String cacheDir) {
        System.out.println("--------------------------");
        System.out.println("Current caching directory: " + cacheDir);
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
        System.out.println(result);
        return result;
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
        if (!Files.exists(Path.of(cacheDir + "/" + file)) || file.equals("")) {
            System.out.println("File doesn't exist");
            return;
        }
        cache.load(file);
        System.out.println("File loaded.");
    }

    private static void loadFileFromCache(Scanner scanner, AbstractCache<String, String> cache) {
        System.out.println("Enter file to load from cache:");
        String content = cache.get(scanner.nextLine());
        System.out.println(
                Objects.requireNonNullElse(content,
                        "No such file in cache, you should load it to cache first."));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        String cacheDir = "";
        AbstractCache<String, String> cache = null;
        while (!userInput.equals("4")) {
            printMenu(cacheDir);
            userInput = scanner.nextLine();
            if (userInput.equals("1")) {
                cacheDir = setCacheDir(scanner);
                if (!cacheDir.equals("")) {
                    cache = new DirFileCache(cacheDir);
                }
            }
            if (userInput.equals("2")) {
                if (cacheDir.equals("")) {
                    System.out.println("Choose caching directory.");
                    continue;
                }
                loadFileToCache(cacheDir, scanner, cache);
            }
            if (userInput.equals("3")) {
                if (cache == null) {
                    System.out.println("Cache is empty.");
                    continue;
                }
                loadFileFromCache(scanner, cache);
            }
        }
    }
}
