package ru.job4j.solid.menu;

public interface Action {

    String getDescription();
    boolean execute();
}
