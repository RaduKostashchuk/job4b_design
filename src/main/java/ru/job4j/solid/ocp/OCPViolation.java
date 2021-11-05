package ru.job4j.solid.ocp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class OCPViolation {

    public static class TinyData {
        private List<Short> data;

        public TinyData(List<Short> data) {
            this.data = data;
        }

        public List<Short> getData() {
            return data;
        }
    }

    public static class NormalData {
        private int[] data;

        public NormalData(int[] data) {
            this.data = data;
        }

        public String getData() {
            return Arrays.toString(data);
        }
    }

    public static void save(Object object) throws IOException {
        if (object instanceof TinyData) {
            for (Short num : ((TinyData) object).getData()) {
                Files.writeString(Path.of("c:\\temp\\tiny.txt"),
                        num.toString() + System.lineSeparator(),
                        StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            }
        } else if (object instanceof NormalData) {
                Files.writeString(Path.of("c:\\temp\\normal.txt"),
                        ((NormalData) object).getData() + System.lineSeparator(),
                        StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        }
    }

    public static void main(String[] args) throws IOException {
        TinyData tinyData = new TinyData(List.of((short) 1, (short) 2, (short) 3));
        NormalData normalData = new NormalData(new int[]{100000000, 200000000, 300000000});
        List<Object> list = List.of(tinyData, normalData);
        for (Object el : list) {
            save(el);
        }
    }
}
