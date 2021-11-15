package ru.job4j.solid.menu;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Predicate;

public class SimpleMenu implements Menu {
    private Item root = null;

    @Override
    public void add(String name, String parentName, Action action) {
        Item newItem = new Item(name, parentName, action);
        justAdd(newItem);
    }

    @Override
    public void add(String name, String parentName) {
        Item newItem = new Item(name, parentName);
        justAdd(newItem);
    }

    private void justAdd(Item item) {
        if (item.getName().equals("root")) {
            root = item;
        } else {
            Item parent = findItem(i -> i.getName().equals(item.getParentName()));
            if (parent != null) {
                parent.addChild(item);
            }
        }
    }

    @Override
    public Action select(String itemName) {
        Item item = findItem(i -> i.getName().equals(itemName));
        return item == null ? new VoidAction() : item.getAction();
    }

    @Override
    public String show() {
        String offset = "-";
        return walkTree(root, offset).toString();
    }

    private Item findItem(Predicate<Item> predicate) {
        Item result = null;
        Queue<Item> data = new LinkedList<>();
        data.offer(root);
        while (!data.isEmpty()) {
            Item el = data.poll();
            if (predicate.test(el)) {
                result = el;
                break;
            }
            data.addAll(el.getChildren());
        }
        return result;
    }

    private StringBuilder walkTree(Item root, String offset) {
        StringBuilder result = new StringBuilder();
       if (!root.getName().equals("root")) {
            String ln = System.lineSeparator();
            result.append(offset);
            result.append("Item: ");
            result.append(root.getName());
            result.append(" ")
                    .append("(")
                    .append(root.getAction().getDescription())
                    .append(")")
                    .append(ln);
        }
        if (root.getChildren().size() != 0) {
            for (Item el : root.getChildren()) {
                result.append(walkTree(el, offset.repeat(2)));
            }
        }
        return result;
    }
}
