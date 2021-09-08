package ru.job4j.generics;

public class User extends Base {
    private final String name;

    public User(String id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{"
                + "id='" + this.getId() + '\''
                + "name='" + name + '\''
                + '}';
    }
}
