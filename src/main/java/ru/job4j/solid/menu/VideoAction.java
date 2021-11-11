package ru.job4j.solid.menu;

public class VideoAction implements Action {
    private final String video;

    public VideoAction(String video) {
        this.video = video;
    }

    @Override
    public String getDescription() {
        return "Video item";
    }

    @Override
    public boolean execute() {
        System.out.println("The " + video + " is playing.");
        return true;
    }
}
