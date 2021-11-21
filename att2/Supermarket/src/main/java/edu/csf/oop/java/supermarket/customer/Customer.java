package edu.csf.oop.java.supermarket.customer;

import edu.csf.oop.java.supermarket.product.ProductType;
import edu.csf.oop.java.supermarket.services.Utils;

import java.util.Random;

public class Customer {

    private final int length = ProductType.values().length;
    private final Demand[] needs = new Demand[length];
    private int money;

    public Customer() {
        int[] priority = Utils.rndIntArr(length);
        ProductType[] types = ProductType.values();
        for (int i = 0; i < length; i++) {
            needs[i] = new Demand(types[priority[i]]);
        }
        Random rnd = new Random();
        money = rnd.nextInt(300);
    }

    public ProductType getType(int pos) {
        return needs[pos].getType();
    }

    public int getQuantity(int pos) {
        return needs[pos].getQuantity();
    }

    public int getPrice(int pos) {
        return needs[pos].getPrice();
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
