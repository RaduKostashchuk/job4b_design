package ru.job4j.gc.ref;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class SoftDemo {
    private static void example1() {
        Object object = new Object();
        SoftReference<Object> soft = new SoftReference<>(object);
        object = null;
        System.out.println(soft.get());
    }

    private static void example2() {
        List<SoftReference<Object>> list = new ArrayList<>();
        for (int i = 0; i < 14_000; i++) {
            list.add(new SoftReference<>(new Object() {
                String value = String.valueOf(System.currentTimeMillis());

                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Object removed");
                }
            }));
        }
        System.gc();
        int liveObjects = 0;
        for (SoftReference<Object> ref : list) {
            Object object = ref.get();
            if (object != null) {
                liveObjects++;
            }
        }
        System.out.println(liveObjects);
    }

    public static void main(String[] args) {
        //example1();
        example2();
    }
}
