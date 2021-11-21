package edu.csf.oop.java.supermarket.product;

public class Product {

    private final ProductType type;
    private int warehouseQuantity;
    private int shoppingRoomQuantity;
    private int price;
    private int daysBeforeExpiration;

    public Product(ProductType type, int warehouseQuantity, int price) {
        this.type = type;
        this.warehouseQuantity = warehouseQuantity;
        this.price = price;
        daysBeforeExpiration = 3;
    }

    public Product(ProductType type, int warehouseQuantity, int shoppingRoomQuantity, int price, int daysBeforeExpiration) {
        this.type = type;
        this.warehouseQuantity = warehouseQuantity;
        this.shoppingRoomQuantity = shoppingRoomQuantity;
        this.price = price;
        this.daysBeforeExpiration = daysBeforeExpiration;
    }

    public ProductType getType() {
        return type;
    }

    public int getWarehouseQuantity() {
        return warehouseQuantity;
    }

    public int getShoppingRoomQuantity() {
        return shoppingRoomQuantity;
    }

    public int getPrice() {
        return price;
    }

    public int getDaysBeforeExpiration() {
        return daysBeforeExpiration;
    }

    public void setWarehouseQuantity(int warehouseQuantity) {
        this.warehouseQuantity = warehouseQuantity;
    }

    public void setShoppingRoomQuantity(int shoppingRoomQuantity) {
        this.shoppingRoomQuantity = shoppingRoomQuantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void decreaseDaysBeforeExpiration() {
        daysBeforeExpiration--;
    }
}