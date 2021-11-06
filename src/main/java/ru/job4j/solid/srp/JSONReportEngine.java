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
        Gson gson = new GsonBuilder().create();
        return gson.toJson(store.findBy(filter));
    }
}
