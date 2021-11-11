package ru.job4j.solid.menu;

public class MusicAction implements Action {
    private final String sound;

    public MusicAction(String sound) {
        this.sound = sound;
    }

    @Override
    public String getDescription() {
        return "Sound item";
    }

    @Override
    public boolean execute() {
        System.out.println("The " + sound + " is playing.");
        return true;
    }
}
