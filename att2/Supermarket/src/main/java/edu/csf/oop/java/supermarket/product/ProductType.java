package edu.csf.oop.java.supermarket.product;

public enum ProductType {

    MILK ("Молоко", 10),
    MEAT ("Мясо", 20),
    FRUIT ("Фрукты", 30),
    ALCOHOL ("Алкоголь", 40);

    private final String russianName;
    private final int price;
    private int sells;

    ProductType(String russianNames, int price) {
        this.russianName = russianNames;
        this.price = price;
    }

    public String getRussianName() {
        return russianName;
    }

    public int getPrice() {
        return price;
    }

    public int getSells() {
        return sells;
    }

    public void setSells(int sells) {
        this.sells = sells;
    }
}

