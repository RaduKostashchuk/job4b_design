package ru.job4j.generics;

public class Role extends Base {
    private final String description;

    public Role(String id, String description) {
        super(id);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Role{"
                + "id='" + this.getId() + '\''
                + "description='" + description + '\''
                + '}';
    }
}