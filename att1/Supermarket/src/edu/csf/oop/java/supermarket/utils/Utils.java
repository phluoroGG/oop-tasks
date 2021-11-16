package edu.csf.oop.java.supermarket.utils;

import edu.csf.oop.java.supermarket.productsInfo.Products;
import edu.csf.oop.java.supermarket.supermarketInfo.ListOfGoods;

import java.util.List;
import java.util.Random;

import static edu.csf.oop.java.supermarket.Main.*;

public class Utils {
    public static void sell() {
        int[] sellsAndMoney = list.sell();
        supermarketState.setMoney(supermarketState.getMoney() + sellsAndMoney[sellsAndMoney.length - 1]);
        int[] sells = statistics.getSells();
        for (int j = 0; j < sellsAndMoney.length - 1; j++) {
            statistics.setSells(j, sells[j] + sellsAndMoney[j]);
            supermarketState.setQuantityInShoppingRoom(supermarketState.getQuantityInShoppingRoom() - sellsAndMoney[j]);
        }
    }

    public static void showInfo(ListOfGoods list, int goodsType) {
        List<Integer> positions;
        String[] ruTypes = Products.productsToRussian();
        System.out.printf("|%5s|%8s|%16s|%20s|%26s|%4s|%43s|\n",
                "Номер",
                "Тип",
                "Общее количество",
                "Количество на складе",
                "Количество в торговом зале",
                "Цена",
                "Количество дней до окончания срока годности");
        if (goodsType == 1) {
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("|%5s|%8s|%16s|%20s|%26s|%4s|%43s|\n",
                        i + 1,
                        ruTypes[list.getType(i).ordinal()],
                        list.getWarehouseQuantity(i) + list.getShoppingRoomQuantity(i),
                        list.getWarehouseQuantity(i),
                        list.getShoppingRoomQuantity(i),
                        list.getPrice(i),
                        list.getDaysBeforeExpiration(i));
            }
        } else {
            positions = list.getPositions(Products.values()[goodsType - 2]);
            for (Integer position : positions) {
                System.out.printf("|%5s|%8s|%16s|%20s|%26s|%4s|%43s|\n",
                        position + 1,
                        ruTypes[list.getType(position).ordinal()],
                        list.getWarehouseQuantity(position) + list.getShoppingRoomQuantity(position),
                        list.getWarehouseQuantity(position),
                        list.getShoppingRoomQuantity(position),
                        list.getPrice(position),
                        list.getDaysBeforeExpiration(position));
            }
        }
    }

    public static void showStats(ListOfGoods list) {
        System.out.printf("|%8s|%15s|%16s|%20s|%26s|%7s|\n",
                "Тип",
                "Закупочная цена",
                "Общее количество",
                "Количество на складе",
                "Количество в торговом зале",
                "Продано");
        Products[] types = Products.values();
        String[] ruTypes = Products.productsToRussian();
        int[] prices = statistics.getPrices();
        int[] sells = statistics.getSells();
        for (int i = 0; i < types.length; i++) {
            System.out.printf("|%8s|%15d|%16d|%20d|%26d|%7d|\n",
                    ruTypes[i],
                    prices[i],
                    list.getAllWarehouseQuantity(types[i]) + list.getAllShoppingRoomQuantity(types[i]),
                    list.getAllWarehouseQuantity(types[i]),
                    list.getAllShoppingRoomQuantity(types[i]),
                    sells[i]);
        }
    }

    public static int[] rndIntArr(int length) {
        int[] arr = new int[length];
        Random rnd = new Random();
        for (int i = 0; i < length; i++) {
            arr[i] = rnd.nextInt(length);
            while (true) {
                boolean isHasEqual = false;
                for (int j = 0; j < i; j++) {
                    if (arr[j] == arr[i]) {
                        isHasEqual = true;
                        break;
                    }
                }
                if (isHasEqual) {
                    arr[i] = rnd.nextInt(length);
                } else {
                    break;
                }
            }
        }
        return arr;
    }
}
