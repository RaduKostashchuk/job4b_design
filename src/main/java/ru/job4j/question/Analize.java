package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, String> map = new HashMap<>();
        int added = 0;
        int changed = 0;
        int deleted;
        for (User el : previous) {
            map.put(el.getId(), el.getName());
        }
        for (User el : current) {
            String name = map.get(el.getId());
            if (name == null) {
                added++;
            } else if (!name.equals(el.getName())) {
                changed++;
            }
        }
        deleted = previous.size() - current.size() + added;
        return new Info(added, changed, deleted);
    }
}
