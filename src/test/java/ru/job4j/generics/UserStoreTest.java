package ru.job4j.generics;

import org.hamcrest.core.IsNull;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserStoreTest {

    @Test
    public void whenAddTwoUser() {
        Store<User> store = new UserStore();
        store.add(new User("10", "Ivan"));
        store.add(new User("20", "Egor"));
        User result = store.findById("10");
        assertThat(result.getName(), is("Ivan"));
    }

    @Test
    public void whenReplaceUser() {
        Store<User> store = new UserStore();
        store.add(new User("10", "Ivan"));
        store.replace("10", new User("10", "Stepan"));
        User result = store.findById("10");
        assertThat(result.getName(), is("Stepan"));
    }

    @Test
    public void whenDeleteUser() {
        Store<User> store = new UserStore();
        store.add(new User("10", "Ivan"));
        store.delete("10");
        User result = store.findById("10");
        assertThat(result, IsNull.nullValue());
    }

}