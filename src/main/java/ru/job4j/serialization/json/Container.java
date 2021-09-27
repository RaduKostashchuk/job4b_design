package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Container {
    private final boolean refrigerated;
    private final String owner;
    private final double weight;
    private final Product product;
    private final String[] operators;

    public Container(boolean refrigerated, String owner, double weight, Product product, String...operators) {
        this.refrigerated = refrigerated;
        this.owner = owner;
        this.weight = weight;
        this.product = product;
        this.operators = operators;
    }

    @Override
    public String toString() {
        return "Container{"
                + "refrigerated=" + refrigerated
                + ", owner='" + owner + '\''
                + ", weight=" + weight
                + ", product=" + product
                + ", operators=" + Arrays.toString(operators)
                + '}';
    }

    public static void main(String[] args) {
        Container first = new Container(true, "Magnit", 21.550,
                new Product("fish"), "Swift", "Evergreen", "SibConTrans");
        Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(first));

        String fromJson = "{"
                + "\"refrigerated\":false,"
                + "\"owner\":\"Lenta\","
                + "\"weight\":32.120,"
                + "\"product\":{\"name\":\"Potato\"},"
                + "\"operators\":[\"RZHD\", \"Delco\"]"
                + "}";
        Container second = gson.fromJson(fromJson, Container.class);
        System.out.println(second);
    }
}
