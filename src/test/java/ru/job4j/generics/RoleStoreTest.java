package ru.job4j.generics;

import org.hamcrest.core.IsNull;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RoleStoreTest {

    @Test
    public void whenAddTwoRole() {
        Store<Role> store = new RoleStore();
        store.add(new Role("10", "Director"));
        store.add(new Role("20", "Manager"));
        Role result = store.findById("10");
        assertThat(result.getDescription(), is("Director"));
    }

    @Test
    public void whenReplaceRole() {
        Store<Role> store = new RoleStore();
        store.add(new Role("10", "Manager"));
        store.replace("10", new Role("10", "Director"));
        Role result = store.findById("10");
        assertThat(result.getDescription(), is("Director"));
    }

    @Test
    public void whenDeleteRole() {
        Store<Role> store = new RoleStore();
        store.add(new Role("10", "Director"));
        store.delete("10");
        Role result = store.findById("10");
        assertThat(result, IsNull.nullValue());
    }


}