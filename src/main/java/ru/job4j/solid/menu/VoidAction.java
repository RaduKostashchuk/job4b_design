package ru.job4j.solid.menu;

public class VoidAction implements Action {
    @Override
    public String getDescription() {
        return "Void item";
    }

    @Override
    public boolean execute() {
        return true;
    }
}
