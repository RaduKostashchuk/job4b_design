package ru.job4j.solid.products;

import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class ControlQualityTest {

    @Test
    public void whenExpiredFoodInShopThenMoveItToTrash() {
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        Calendar expire = Calendar.getInstance();
        expire.set(2021, Calendar.NOVEMBER, 1);
        Calendar created = Calendar.getInstance();
        created.set(2021, Calendar.JANUARY, 11);
        Food meat = new Meat("Chicken", expire, created, 200, 0);
        shop.add(meat);
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.distribute(meat, List.of(shop, warehouse, trash));
        assertTrue(trash.findAll().contains(meat));
        assertThat(shop.findAll().size(), is(0));
    }

    @Test
    public void whenOldFoodInShopThenSetDiscount() {
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        Calendar expire = Calendar.getInstance();
        expire.set(2021, Calendar.NOVEMBER, 20);
        Calendar created = Calendar.getInstance();
        created.set(2021, Calendar.JANUARY, 11);
        Food meat = new Meat("Chicken", expire, created, 200, 0);
        shop.add(meat);
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.distribute(meat, List.of(shop, warehouse, trash));
        assertThat(shop.findAll().get(0).getDiscount(), is(10.0));
    }

    @Test
    public void whenNewFoodInShopThenMoveItToWarehouse() {
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        Calendar expire = Calendar.getInstance();
        expire.set(2022, Calendar.NOVEMBER, 1);
        Calendar created = Calendar.getInstance();
        created.set(2021, Calendar.NOVEMBER, 1);
        Food meat = new Meat("Chicken", expire, created, 200, 0);
        shop.add(meat);
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.distribute(meat, List.of(shop, warehouse, trash));
        assertTrue(warehouse.findAll().contains(meat));
        assertThat(shop.findAll().size(), is(0));
    }

    @Test
    public void whenExpiredFoodInWarehouseThenMoveItToTrash() {
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        Calendar expire = Calendar.getInstance();
        expire.set(2021, Calendar.NOVEMBER, 1);
        Calendar created = Calendar.getInstance();
        created.set(2021, Calendar.JANUARY, 11);
        Food meat = new Meat("Chicken", expire, created, 200, 0);
        warehouse.add(meat);
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.distribute(meat, List.of(shop, warehouse, trash));
        assertTrue(trash.findAll().contains(meat));
        assertThat(warehouse.findAll().size(), is(0));
    }

    @Test
    public void whenOldFoodInWarehouseThenSetDiscountAndMoveItToShop() {
        Store shop = new Shop();
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        Calendar expire = Calendar.getInstance();
        expire.set(2021, Calendar.NOVEMBER, 20);
        Calendar created = Calendar.getInstance();
        created.set(2021, Calendar.JANUARY, 11);
        Food milk = new Milk("Chicken", expire, created, 200, 0);
        warehouse.add(milk);
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.distribute(milk, List.of(shop, warehouse, trash));
        assertThat(warehouse.findAll().size(), is(0));
        assertThat(shop.findAll().get(0).getDiscount(), is(10.0));
    }

}