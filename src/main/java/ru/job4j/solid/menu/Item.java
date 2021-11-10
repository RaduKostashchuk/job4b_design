package ru.job4j.solid.menu;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private final List<Item> children;
    private final String name;
    private final Action action;

    public Item(String name, Action action, List<Item> children) {
        this.action = action;
        this.name = name;
        this.children = children;
    }

    public List<Item> getChildren() {
        return new ArrayList<>(children);
    }

    public String getName() {
        return name;
    }

    public Action getAction() {
        return action;
    }
}
