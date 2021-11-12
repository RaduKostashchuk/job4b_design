package ru.job4j.solid.products;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;

public interface Store {

    List<Food> findAll();

    boolean add(Food product);

    boolean accept(Food product);

    void clear();

    default double getExpirationPercent(Food product) {
        Calendar now = Calendar.getInstance();
        long remainder = ChronoUnit.DAYS.between(now.toInstant(),
                product.getExpiryDate().toInstant());
        long validPeriod = ChronoUnit.DAYS.between(product.getCreateDate().toInstant(),
                product.getExpiryDate().toInstant());
        return (((double) (validPeriod - remainder)) / validPeriod) * 100;
    }
}
