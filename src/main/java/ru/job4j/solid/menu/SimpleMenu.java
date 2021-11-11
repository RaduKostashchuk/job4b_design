package ru.job4j.solid.menu;

import java.util.ArrayList;
import java.util.List;

public class SimpleMenu implements Menu {
    private final List<Item> items;

    public SimpleMenu() {
        items = new ArrayList<>();
    }

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
            items.add(item);
        } else {
            Item parent = findItem(item.getParentName());
            if (parent != null) {
                parent.addChild(item);
                items.add(item);
            }
        }
    }

    @Override
    public Action select(String itemName) {
        Item item = findItem(itemName);
        return item == null ? new VoidAction() : item.getAction();
    }

    @Override
    public String show() {
        Item root = findItem("root");
        String offset = "-";
        return walkTree(root, offset).toString();
    }

    private Item findItem(String name) {
        Item result = null;
        for (Item item : items) {
            if (item.getName().equals(name)) {
                result = item;
                break;
            }
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
