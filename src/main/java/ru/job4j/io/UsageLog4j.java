package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        boolean bool = true;
        char ch = 'w';
        byte byteNum = 10;
        short shortNum = 20;
        int intNum = 30;
        long longNum = 40L;
        float floatNum = 50.1F;
        double doubleNum = 100.15;

        LOG.debug("Boolean: {}.Character: {}. Byte: {}. Short: {}. Integer: {}. Long: {}. Float: {}. Double: {}.",
                bool, ch, byteNum, shortNum, intNum, longNum, floatNum, doubleNum);
    }
}
