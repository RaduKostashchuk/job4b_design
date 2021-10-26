package ru.job4j.gc.ref;

import javax.sound.midi.Soundbank;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WeakDemo {
    public static void main(String[] args) throws InterruptedException {
       // example1();
        //example2();
        example3();
    }

    private static void example1() throws InterruptedException {
        Object object = new Object();
        WeakReference<Object> weak = new WeakReference<>(object);
        object = null;
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(weak.get());
    }

    private static void example2() throws InterruptedException {
        List<WeakReference<Object>> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new WeakReference<>(new Object() {
                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Removed");
                }
            }));
        }

        System.gc();
        TimeUnit.SECONDS.sleep(3);
    }

    private static void example3() throws InterruptedException {
        Object object = new Object() {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Removed");
            }
        };
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        WeakReference<Object> weak = new WeakReference<>(object, queue);
        object = null;
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("From link: " + weak.get());
        System.out.println("From queue: " + queue.poll());
    }
}
