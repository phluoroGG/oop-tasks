package edu.csf.oop.java.supermarket.customer;

import edu.csf.oop.java.supermarket.product.ProductType;

import java.util.Random;

class Demand {

    private final ProductType type;
    private final int quantity;
    private final int price;

    public Demand(ProductType type) {
        Random rnd = new Random();
        this.type = type;
        quantity = rnd.nextInt(5);
        double factor = 1 + rnd.nextDouble() / 2;
        price = (int) Math.ceil(type.getPrice() * factor);
    }

    public ProductType getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }
}
