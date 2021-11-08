package ru.job4j.solid.products;

public class ControlQuality {
    private final ProcessStore processing;

    public ControlQuality(ProcessStore processing) {
        this.processing = processing;
    }

    public void execute() {
        processing.process();
    }
}
