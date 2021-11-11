package ru.job4j.solid.menu;

public class ExitAction implements Action {
    @Override
    public String getDescription() {
        return "Exit";
    }

    @Override
    public boolean execute() {
        return false;
    }
}
