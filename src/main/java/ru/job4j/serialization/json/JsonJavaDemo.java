package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonJavaDemo {
    public static void main(String[] args) {
        JSONObject jsonContact = new JSONObject("{\"phone\":\"8-913-335-12-12\"}");
        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);

        final Person person = new Person(false, 30,
                new Contact("222-333-11"), "Worker", "Married");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", person.isSex());
        jsonObject.put("age", person.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);

        System.out.println(jsonObject);
        System.out.println(new JSONObject(person));
    }
}
