package ru.job4j.io;

import org.hamcrest.core.IsNull;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ConfigTest {

    @Test
    public void pairWithoutComments() {
        Config config = new Config("./data/app_without_comments.properties");
        config.load();
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
        assertThat(config.value("hibernate.connection.type"), IsNull.nullValue());
    }

    @Test
    public void pairWithComments() {
        Config config = new Config("./data/app.properties");
        config.load();
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
        assertThat(config.value("hibernate.connection.type"), IsNull.nullValue());
    }

    @Test
    public void withCommentsAndBlank() {
        Config config = new Config("./data/app_with_comm_and_blank.properties");
        config.load();
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
        assertThat(config.value("hibernate.connection.type"), IsNull.nullValue());
    }

    @Test (expected = IllegalArgumentException.class)
    public void withBrokenName() {
        Config config = new Config("./data/app_name_broken.properties");
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void withBrokenValue() {
        Config config = new Config("./data/app_value_broken.properties");
        config.load();
    }
}