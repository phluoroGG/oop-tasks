package edu.csf.oop.java.supermarket.product;

public enum ProductType {

    MILK ("������", 10),
    MEAT ("����", 20),
    FRUIT ("������", 30),
    ALCOHOL ("��������", 40);

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

