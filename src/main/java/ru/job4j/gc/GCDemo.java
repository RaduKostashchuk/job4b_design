package ru.job4j.gc;

public class GCDemo {
    private static final long KB = 1000;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = Runtime.getRuntime().freeMemory();
        final long totalMemory = Runtime.getRuntime().totalMemory();
        final long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("========== Environment state ==========");
        System.out.printf("Free memory: %d%n", freeMemory / MB);
        System.out.printf("Total memory: %d%n", totalMemory / MB);
        System.out.printf("Maximum memory: %d%n", maxMemory / MB);
        }

    public static void main(String[] args) {
        info();
        for (int i = 0; i < 4000; i++) {
            new User(i, "Ivan");
        }
        info();
    }

}
