package edu.csf.oop.java.supermarket.supermarketInfo;

import edu.csf.oop.java.supermarket.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SupermarketState {

    private static final Logger logger
            = LoggerFactory.getLogger(Main.class);

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
        if (this.money == money) {
            return;
        }
        int oldMoney = this.money;
        this.money = money;
        logger.info("Changed money from {} to {}", oldMoney, money);
    }

    public void setQuantityInShoppingRoom(int quantityInShoppingRoom) {
        if (this.quantityInShoppingRoom == quantityInShoppingRoom) {
            return;
        }
        int oldQuantityInShoppingRoom = this.quantityInShoppingRoom;
        this.quantityInShoppingRoom = quantityInShoppingRoom;
        logger.info("Changed quantity in shopping room from {} to {}", oldQuantityInShoppingRoom, quantityInShoppingRoom);
    }

    public void increaseCapacity(int number) {
        setMoney(money - 1000 * number);
        capacity += 10 * number;
        logger.info("Increased capacity by {}", 10 * number);
    }

    public int maintenance() {
        int spending = 10 * capacity;
        setMoney(money - spending);
        return spending;
    }
}
