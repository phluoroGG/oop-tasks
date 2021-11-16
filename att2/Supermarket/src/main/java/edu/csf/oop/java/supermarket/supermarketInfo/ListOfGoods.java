package edu.csf.oop.java.supermarket.supermarketInfo;

import edu.csf.oop.java.supermarket.Main;
import edu.csf.oop.java.supermarket.customer.Customer;
import edu.csf.oop.java.supermarket.productsInfo.Products;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static edu.csf.oop.java.supermarket.Main.supermarketState;

public class ListOfGoods {

    private static final Logger logger
            = LoggerFactory.getLogger(Main.class);

    private static class Product {
        private final Products type;
        private int warehouseQuantity;
        private int shoppingRoomQuantity;
        private int price;
        private int daysBeforeExpiration;

        public Product(Products type, int warehouseQuantity, int price) {
            this.type = type;
            this.warehouseQuantity = warehouseQuantity;
            this.price = price;
            daysBeforeExpiration = 3;
        }

        public Products getType() {
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

    public List<Product> list = new ArrayList<>();
    public int[] warehouseQuantityList = new int[Products.values().length];
    public int[] shoppingRoomQuantityList = new int[Products.values().length];

    public void add(Products type, int quantity, int price) {
        Product product = new Product(type, quantity, price);
        list.add(product);
        warehouseQuantityList[product.getType().ordinal()] += product.getWarehouseQuantity();
        logger.info("Add product, type - {}, quantity - {}, price - {}", type, quantity, price);
    }

    public List<Integer> getPositions(Products type) {
        List<Integer> posList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType() == type) {
                posList.add(i);
            }
        }
        return posList;
    }

    public int[] sell() {
        Customer customer = new Customer();
        logger.info("Customer came with {} money", customer.getMoney());
        int money = 0;
        int length = Products.values().length;
        int[] sellsAndMoney = new int[length + 1];
        for (int i = 0; i < length; i++) {
            Products type = customer.getType(i);
            int customerQuantity = customer.getQuantity(i);
            int customerPrice = customer.getPrice(i);
            List<Integer> posList = getPositions(type);
            if (posList.isEmpty()) {
                continue;
            }
            List<Integer> posToRemove = new ArrayList<>();
            for (Integer position : posList) {
                int productQuantity = list.get(position).getShoppingRoomQuantity();
                int productPrice = list.get(position).getPrice();
                int daysBeforeExpiration = list.get(position).getDaysBeforeExpiration();
                if (customerPrice < productPrice || daysBeforeExpiration <= 0) {
                    continue;
                }
                if (daysBeforeExpiration == 1) {
                    productPrice = (int) Math.ceil((double) productPrice * 4 / 3);
                }
                int realQuantity = -1;
                if (productPrice != 0) {
                    realQuantity = customer.getMoney() / productPrice;
                }
                if (realQuantity != -1 && realQuantity < customerQuantity) {
                    customerQuantity = realQuantity;
                }
                int tempQuantity = customerQuantity;
                if (productQuantity <= customerQuantity) {
                    money += productQuantity * productPrice;
                    customer.setMoney(customer.getMoney() - productQuantity * productPrice);
                    sellsAndMoney[type.ordinal()] += productQuantity;
                    shoppingRoomQuantityList[type.ordinal()] -= productQuantity;
                    customerQuantity -= productQuantity;
                    list.get(position).setShoppingRoomQuantity(0);
                    if (list.get(position).getWarehouseQuantity() == 0) {
                        posToRemove.add(position - posToRemove.size());
                    }
                } else {
                    money += customerQuantity * productPrice;
                    customer.setMoney(customer.getMoney() - customerQuantity * productPrice);
                    sellsAndMoney[type.ordinal()] += customerQuantity;
                    shoppingRoomQuantityList[type.ordinal()] -= customerQuantity;
                    list.get(position).setShoppingRoomQuantity(productQuantity - customerQuantity);
                    customerQuantity = 0;
                }
                logger.info("Purchased a product, type - {}, quantity - {}, price - {}",
                        type, tempQuantity - customerQuantity, productPrice);
                if (customerQuantity == 0) {
                    break;
                }
            }
            for (Integer pos : posToRemove) {
                list.remove((int) pos);
            }
        }
        sellsAndMoney[length] = money;
        return sellsAndMoney;
    }

    public int removeExpiredProducts() {
        int removed = 0;
        for (int i = 0; i < list.size(); i++) {
            Product product = list.get(i);
            if (product.getDaysBeforeExpiration() <= 0) {
                removed += product.getWarehouseQuantity() + product.getShoppingRoomQuantity();
                list.remove(product);
                logger.info("Removed expired product, type - {}, quantity - {}, price - {}", product.getType(),
                        product.getWarehouseQuantity() + product.getShoppingRoomQuantity(), product.getPrice());
            }
        }
        return removed;
    }

    public void makeDiscountOnAlmostExpiredGoods(int discount) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDaysBeforeExpiration() == 1) {
                setPrice(i, (int) Math.floor((double) getPrice(i) * (100 - discount) / 100));
            }
        }
    }

    public void decreaseDaysBeforeExpiration() {
        for (Product product : list) {
            product.decreaseDaysBeforeExpiration();
        }
    }

    public int getAllWarehouseQuantity(Products type) {
        return warehouseQuantityList[type.ordinal()];
    }

    public int getAllShoppingRoomQuantity(Products type) {
        return shoppingRoomQuantityList[type.ordinal()];
    }

    public int getPrice(int position) {
        return list.get(position).getPrice();
    }

    public int getWarehouseQuantity(int position) {
        return list.get(position).getWarehouseQuantity();
    }

    public int getShoppingRoomQuantity(int position) {
        return list.get(position).getShoppingRoomQuantity();
    }

    public Products getType(int position) {
        return list.get(position).getType();
    }

    public int getDaysBeforeExpiration(int position) {
        return list.get(position).getDaysBeforeExpiration();
    }

    public void setPrice(int position, int price) {
        int oldPrice = list.get(position).getPrice();
        list.get(position).setPrice(price);
        logger.info("Changed the price of the product on position {} from {} to {}", position, oldPrice, price);
    }

    public void toWarehouse(int position, int quantity) {
        list.get(position).setWarehouseQuantity(list.get(position).getWarehouseQuantity() + quantity);
        list.get(position).setShoppingRoomQuantity(list.get(position).getShoppingRoomQuantity() - quantity);
        supermarketState.setQuantityInShoppingRoom(supermarketState.getQuantityInShoppingRoom() - quantity);
        warehouseQuantityList[list.get(position).getType().ordinal()] += quantity;
        shoppingRoomQuantityList[list.get(position).getType().ordinal()] -= quantity;
        logger.info("Moved to the warehouse {} units of the product on position {}", quantity, position);
    }

    public void toShoppingRoom(int position, int quantity) {
        list.get(position).setShoppingRoomQuantity(list.get(position).getShoppingRoomQuantity() + quantity);
        list.get(position).setWarehouseQuantity(list.get(position).getWarehouseQuantity() - quantity);
        supermarketState.setQuantityInShoppingRoom(supermarketState.getQuantityInShoppingRoom() + quantity);
        warehouseQuantityList[list.get(position).getType().ordinal()] -= quantity;
        shoppingRoomQuantityList[list.get(position).getType().ordinal()] += quantity;
        logger.info("Moved to the shopping room {} units of the product on position {}", quantity, position);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }
}
