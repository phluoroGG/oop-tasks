package edu.csf.oop.java.supermarket.supermarketInfo;

public class SupermarketState {
    private int money;
    private int capacity;
    private int quantityInShoppingRoom;

    public SupermarketState() {
        money = 10000;
        capacity = 20;
        quantityInShoppingRoom = 0;
    }

    public int getMoney() {
        return money;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getQuantityInShoppingRoom() {
        return quantityInShoppingRoom;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setQuantityInShoppingRoom(int quantityInShoppingRoom) {
        this.quantityInShoppingRoom = quantityInShoppingRoom;
    }

    public void increaseCapacity(int number) {
        money -= 1000 * number;
        capacity += 10 * number;
    }

    public int maintenance() {
        int spending = 10 * capacity;
        money -= spending;
        return spending;
    }
}
