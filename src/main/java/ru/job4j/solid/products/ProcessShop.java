package ru.job4j.solid.products;

public class ProcessShop implements ProcessStore {
    private final Store shop;
    private final Store warehouse;
    private final Store trash;

    public ProcessShop(Store shop, Store warehouse, Store trash) {
        this.shop = shop;
        this.warehouse = warehouse;
        this.trash = trash;
    }

    @Override
    public void process() {
        var it = shop.iterator();
        while (it.hasNext()) {
            Food product = it.next();
            double valPerUsage = getValidPeriodUsage(product);
            if (valPerUsage >= 100) {
                it.remove();
                trash.add(product);
            } else if (valPerUsage > 75) {
                product.setDiscount(10);
            } else if (valPerUsage < 25) {
                it.remove();
                warehouse.add(product);
            }
        }
    }
}
