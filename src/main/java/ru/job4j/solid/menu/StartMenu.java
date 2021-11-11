package ru.job4j.solid.menu;

import java.util.Scanner;

public class StartMenu {

    public void run(Menu menu) {
        boolean run = true;
        String name;
        Scanner scanner = new Scanner(System.in);
        while (run) {
            System.out.println(menu.show());
            name = scanner.nextLine();
            System.out.println("--------- Item content -----------");
            run = menu.select(name).execute();
            System.out.println("----------------------------------");
        }
    }

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        Action text = new TextAction("This is a text.");
        Action music = new MusicAction("Bing-Bing");
        Action film = new VideoAction("Star Wars");
        Action exit = new ExitAction();
        menu.add("root", "");
        menu.add("1", "root");
        menu.add("2", "root");
        menu.add("3", "root", exit);
        menu.add("1.1", "1");
        menu.add("1.2", "1", text);
        menu.add("1.1.1", "1.1", music);
        menu.add("1.1.2", "1.1", film);
        StartMenu start = new StartMenu();
        start.run(menu);
    }
}
