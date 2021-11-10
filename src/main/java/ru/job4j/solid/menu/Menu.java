package ru.job4j.solid.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Action text = new TextAction("This is a text.");
        Action music = new MusicAction("Bing-Bing");
        Action film = new VideoAction("Star Wars");
        Item child6 = new Item("1.1.2", film, new ArrayList<>());
        Item child5 = new Item("1.1.1", music, new ArrayList<>());
        Item child3 = new Item("1.1", null, List.of(child5, child6));
        Item child4 = new Item("1.2", text, new ArrayList<>());
        Item child1 = new Item("1", null, List.of(child3, child4));
        Item child2 = new Item("2", null, new ArrayList<>());
        Item root = new Item("root", null, List.of(child1, child2));
        String name = "";
        Scanner scanner = new Scanner(System.in);
        while (!name.equals("exit")) {
            showMenu(root, "");
            System.out.println("For exit type \"exit\".");
            System.out.println("Choose item.");
            name = scanner.nextLine();
            Item found = search(root, name);
            if (found != null) {
                Action action = found.getAction();
                if (action != null) {
                    System.out.println("----------------------------------");
                    action.execute();
                    System.out.println("----------------------------------");
                } else {
                    System.out.println("----------------------------------");
                    System.out.println("There is no action for item # " + found.getName());
                    System.out.println("----------------------------------");
                }
            }
        }
    }

    private static Item search(Item root, String name) {
        Item result = null;
        if (root.getName().equals(name)) {
            result = root;
        } else if (root.getChildren().size() != 0) {
            for (Item el : root.getChildren()) {
                if (el.getName().equals(name)) {
                    result = el;
                    break;
                } else {
                    result = search(el, name);
                    if (result != null) {
                        break;
                    }
                }
            }
        }
        return result;
    }

    private static void showMenu(Item item, String offset) {
        System.out.print(offset);
        System.out.print("Item: ");
        System.out.print(item.getName());
        if (item.getAction() != null) {
            System.out.print(" ");
            System.out.println("(" + item.getAction().getDescription() + ")");
        } else {
            System.out.println();
        }
        if (item.getChildren().size() != 0) {
            offset += " ";
            for (Item el : item.getChildren()) {
                showMenu(el, offset);
            }
        }
    }
}
