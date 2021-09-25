package ru.job4j.io.encoding;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        List<String> log = new LinkedList<>();
        String userInput = CONTINUE;
        boolean mute = false;
        String botAnswer;
        List<String> botOutput = readPhrases();
        while (!userInput.equals(OUT)) {
            if (userInput.equals(CONTINUE)) {
                mute = false;
            }
            if (userInput.equals(STOP)) {
                mute = true;
            }
            if (!mute) {
                botAnswer = botOutput.get((int) Math.rint(Math.random() * botOutput.size()));
                System.out.println(botAnswer);
                log.add(botAnswer);
            }
            userInput = scanner.next();
            log.add(userInput);
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            in.lines().forEach(result::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/chat_log.txt", "./data/bot_answers.txt");
        cc.run();
    }
}
