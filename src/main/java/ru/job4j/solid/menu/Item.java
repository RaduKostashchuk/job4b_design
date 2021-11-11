package ru.job4j.solid.menu;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private final String name;
    private final String parentName;
    private final List<Item> children;
    private final Action action;

    public Item(String name, String parentName, Action action) {
        this.action = action;
        this.name = name;
        this.parentName = parentName;
        children = new ArrayList<>();
    }

    public Item(String name, String parentName) {
        this.name = name;
        this.parentName = parentName;
        children = new ArrayList<>();
        action = new VoidAction();
    }

    public String getName() {
        return name;
    }

    public String getParentName() {
        return parentName;
    }

    public List<Item> getChildren() {
        return new ArrayList<>(children);
    }

    public Action getAction() {
        return action;
    }

    public void addChild(Item item) {
        children.add(item);
    }

    @Override
    public String toString() {
        return "Item{"
                + "name='" + name + '\''
                + ", parentName='" + parentName + '\''
                + ", children=" + children
                + ", action=" + action
                + '}';
    }
}
