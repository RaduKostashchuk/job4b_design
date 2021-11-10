package ru.job4j.solid.menu;

public class TextAction implements Action {
    private final String text;

    public TextAction(String text) {
        this.text = text;
    }

    @Override
    public String getDescription() {
        return "Text item";
    }

    @Override
    public void execute() {
        System.out.println(text);
    }
}
