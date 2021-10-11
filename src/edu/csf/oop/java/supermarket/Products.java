package edu.csf.oop.java.supermarket;

public enum Products {
    MILK,
    MEAT,
    FRUIT,
    ALCOHOL;

    public static String[] productsToRussian() {
        return new String[]{"Молоко", "Мясо", "Фрукты", "Алкоголь"};
    }
}

