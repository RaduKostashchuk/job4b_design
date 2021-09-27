package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonJava {
    public static void main(String[] args) {
        JSONObject jsonProduct = new JSONObject(new Product("Banana"));
        System.out.println(jsonProduct);

        Container first = new Container(true, "Lenta", 32.12,
                new Product("Meat"), "RZHD");
        JSONObject jsonContainer1 = new JSONObject(first);
        System.out.println(jsonContainer1);

        Container second = new Container(false, "Lenta", 32.12,
                new Product("Potato"), "RZHD");

        List<String> list = new ArrayList<>();
        list.add("EverGreen");
        list.add("Girteka");
        JSONArray jsonOperators = new JSONArray(list);

        JSONObject jsonContainer2 = new JSONObject(second);
        jsonContainer2.put("refrigerated", second.isRefrigerated());
        jsonContainer2.put("owner", second.getOwner());
        jsonContainer2.put("weight", second.getWeight());
        jsonContainer2.put("operators", jsonOperators);
        System.out.println(jsonContainer2);
    }
}
