package edu.csf.oop.java.supermarket;

import java.util.Random;

import static edu.csf.oop.java.supermarket.Main.statistics;

class Customer {
    private static class Demand {
        private Products type;
        private int quantity;
        private int price;

        public Products getType() {
            return type;
        }

        public int getQuantity() {
            return quantity;
        }

        public int getPrice() {
            return price;
        }
    }

    private final Demand[] needs = new Demand[Products.values().length];
    private int money;

    public Customer() {
        int length = Products.values().length;
        int[] priority = Utils.rndIntArr(length);
        Random rnd = new Random();
        Products[] types = Products.values();
        int[] prices = statistics.getPrices();
        for (int i = 0; i < length; i++) {
            needs[i] = new Demand();
            needs[i].type = types[priority[i]];
            needs[i].quantity = rnd.nextInt(5);
            double factor = 1 + rnd.nextDouble() / 2;
            needs[i].price = (int) Math.ceil(prices[priority[i]] * factor);
        }
        money = rnd.nextInt(300);
    }

    public Products getType(int pos) {
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
