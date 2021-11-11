package ru.job4j.solid.menu;

public interface Menu {

    void add(String name, String parentName, Action action);
    void add(String name, String parentName);
    Action select(String itemName);
    String show();
}
