package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class GeneratorTest {

    @Ignore
    @Test
    public void whenNormalInput() {
        Generator generator = new DemoGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = Map.of("name", "Ivan", "subject", "you");
        assertEquals(generator.produce(template, map), "I am Ivan, Who are you?");
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenMapHasLessKeysThanTemplate() {
        Generator generator = new DemoGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = Map.of("subject", "you");
        generator.produce(template, map);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenMapHasMoreKeysThanTemplate() {
        Generator generator = new DemoGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = Map.of("name", "Ivan", "subject", "you", "age", "18");
        generator.produce(template, map);
    }
}

