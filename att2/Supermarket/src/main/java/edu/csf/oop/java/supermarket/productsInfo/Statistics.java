package edu.csf.oop.java.supermarket.productsInfo;

public class Statistics {
    private final int[] prices;
    private final int[] sells;

    public Statistics() {
        prices = new int[]{10, 20, 30, 40};
        sells = new int[Products.values().length];
    }

    public int[] getPrices() {
        return prices;
    }

    public int[] getSells() {
        return sells;
    }

    public void setSells(int position, int sells) {
        this.sells[position] = sells;
    }
}
