package ru.job4j.solid.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.function.Predicate;

public class JSONReportEngine implements Report {
    private final Store store;

    public JSONReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        Gson gson = new GsonBuilder().create();
        for (Employee employee : store.findBy(filter)) {
            text.append(gson.toJson(employee));
        }
        return text.toString();
    }
}
