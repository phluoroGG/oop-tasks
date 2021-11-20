package edu.csf.oop.java.supermarket.services;

import edu.csf.oop.java.supermarket.product.Product;
import edu.csf.oop.java.supermarket.product.ProductType;
import edu.csf.oop.java.supermarket.supermarket.ListOfGoods;
import edu.csf.oop.java.supermarket.supermarket.SupermarketState;
import edu.csf.oop.java.supermarket.time.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameServices {
    private static final Logger logger = LoggerFactory.getLogger(GameServices.class);

    public static void toWarehouse(ListOfGoods list, SupermarketState supermarketState, int position, int quantity) {
        Product product = list.get(position);
        product.setWarehouseQuantity(product.getWarehouseQuantity() + quantity);
        product.setShoppingRoomQuantity(product.getShoppingRoomQuantity() - quantity);
        supermarketState.setQuantityInShoppingRoom(supermarketState.getQuantityInShoppingRoom() - quantity);
        list.setAllWarehouseQuantity(product.getType(), list.getAllWarehouseQuantity(product.getType()) + quantity);
        list.setAllShoppingRoomQuantity(product.getType(), list.getAllShoppingRoomQuantity(product.getType()) - quantity);
        logger.info("Moved to the warehouse {} units of the product on position {}", quantity, position);
    }

    public static void toShoppingRoom(ListOfGoods list, SupermarketState supermarketState, int position, int quantity) {
        Product product = list.get(position);
        product.setShoppingRoomQuantity(product.getShoppingRoomQuantity() + quantity);
        product.setWarehouseQuantity(product.getWarehouseQuantity() - quantity);
        supermarketState.setQuantityInShoppingRoom(supermarketState.getQuantityInShoppingRoom() + quantity);
        list.setAllShoppingRoomQuantity(product.getType(), list.getAllShoppingRoomQuantity(product.getType()) + quantity);
        list.setAllWarehouseQuantity(product.getType(), list.getAllWarehouseQuantity(product.getType()) - quantity);

        logger.info("Moved to the shopping room {} units of the product on position {}", quantity, position);
    }

    public static void waitTime(Time time, ListOfGoods list, SupermarketState supermarketState, int number) {
        int[] oldTime = new int[]{time.getHours(), time.getMinutes()};
        int cycles = ((22 - time.getHours()) * 60 + (60 - time.getMinutes())) / 10;
        if (number == 0 || number > cycles) {
            number = cycles;
        }
        for (int i = 0; i < number; i++) {
            int[] sellsAndMoney = list.sell();
            supermarketState.setMoney(supermarketState.getMoney() + sellsAndMoney[sellsAndMoney.length - 1]);
            for (ProductType productType : ProductType.values()) {
                productType.setSells(productType.getSells() + sellsAndMoney[productType.ordinal()]);
                supermarketState.setQuantityInShoppingRoom(supermarketState.getQuantityInShoppingRoom() -
                        sellsAndMoney[productType.ordinal()]);
            }
            time.setMinutes(time.getMinutes() + 10);
            if (time.getMinutes() == 60) {
                time.setHours(time.getHours() + 1);
                time.setMinutes(0);
            }
        }
        logger.info("Waited from {} hours {} minutes to {} hours {} minutes",
                oldTime[0], oldTime[1], time.getHours(), time.getMinutes());
    }
}
