package edu.csf.oop.java.supermarket;

import java.util.List;
import java.util.Random;

public class Utils {
    public static void waitTime(ListOfGoods list, int number) {
        int cycles = (22 - Globals.hours) * 6 + (60 - Globals.minutes) / 10;
        if (number == 0 || number > cycles) {
            number = cycles;
        }
        for (int i = 0; i < number; i++) {
            int[] sellsAndMoney = list.sell();
            Globals.money += sellsAndMoney[sellsAndMoney.length - 1];
            for (int j = 0; j < sellsAndMoney.length - 1; j++) {
                Globals.sells[j] += sellsAndMoney[j];
                Globals.quantityInShoppingRoom -= sellsAndMoney[j];
            }
            Globals.minutes += 10;
            if (Globals.minutes == 60) {
                Globals.hours++;
                Globals.minutes = 0;
            }
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
                        i,
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
                        position,
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
        for (int i = 0; i < types.length; i++) {
            System.out.printf("|%8s|%15d|%16d|%20d|%26d|%7d|\n",
                    ruTypes[i],
                    Globals.prices[i],
                    list.getAllWarehouseQuantity(types[i]) + list.getAllShoppingRoomQuantity(types[i]),
                    list.getAllWarehouseQuantity(types[i]),
                    list.getAllShoppingRoomQuantity(types[i]),
                    Globals.sells[i]);
        }
    }

    public static int[] rndIntArr(int length) {
        int[] arr = new int[length];
        Random rnd = new Random();
        for (int i = 0; i < length; i++) {
            arr[i] = rnd.nextInt(length);
            while (true) {
                boolean u = false;
                for (int j = 0; j < i; j++) {
                    if (arr[j] == arr[i]) {
                        u = true;
                        break;
                    }
                }
                if (u) {
                    arr[i] = rnd.nextInt(length);
                } else {
                    break;
                }
            }
        }
        return arr;
    }
}
