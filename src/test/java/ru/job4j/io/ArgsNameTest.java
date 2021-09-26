package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ArgsNameTest {

    @Test
    public void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        assertThat(jvm.get("Xmx"), is("512"));
    }

    @Test
    public void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[] {"-encoding=UTF-8", "-Xmx=512"});
        assertThat(jvm.get("Xmx"), is("512"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenGetEmpty() {
        ArgsName jvm = ArgsName.of(new String[] {});
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenWrongSomeArgument() {
        ArgsName jvm = ArgsName.of(new String[] {"-enconding=UTF-8", "-Xmx="});
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenWrongKey() {
        ArgsName jvm = ArgsName.of(new String[] {"enconding=UTF-8", "-Xmx=512"});
    }
}