package edu.csf.oop.java.supermarket.productsInfo;

public enum Products {
    MILK,
    MEAT,
    FRUIT,
    ALCOHOL;

    public static String[] productsToRussian() {
        return new String[]{"Молоко", "Мясо", "Фрукты", "Алкоголь"};
    }
}

