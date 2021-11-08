package ru.job4j.solid.products;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public interface ProcessStore {

    void process();

    default double getValidPeriodUsage(Food product) {
        Calendar now = Calendar.getInstance();
        long remainder = ChronoUnit.DAYS.between(now.toInstant(),
                product.getExpiryDate().toInstant());
        long validPeriod = ChronoUnit.DAYS.between(product.getCreateDate().toInstant(),
                product.getExpiryDate().toInstant());
        return (((double) (validPeriod - remainder)) / validPeriod) * 100;
    }
}
