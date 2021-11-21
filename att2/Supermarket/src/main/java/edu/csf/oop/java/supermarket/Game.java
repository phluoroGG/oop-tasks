package edu.csf.oop.java.supermarket;

import edu.csf.oop.java.supermarket.product.ProductType;
import edu.csf.oop.java.supermarket.services.*;
import edu.csf.oop.java.supermarket.supermarket.ListOfGoods;
import edu.csf.oop.java.supermarket.supermarket.SupermarketState;
import edu.csf.oop.java.supermarket.time.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Game {

    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    public void start() {
        Locale.setDefault(Locale.ROOT);
        Scanner scanner = new Scanner(System.in);
        Time time = new Time();
        SupermarketState supermarketState = new SupermarketState();
        ListOfGoods list = new ListOfGoods();
        logger.info("Application started");
        int i, j;

        System.out.printf("���� %d\n", time.getDays());

        while (true) {
            if (time.isEndOfDay()) {
                if (list.isEmpty() && supermarketState.getMoney() < 0) {
                    System.out.println("� ��� ������������� ������ � ����������� ������! �� - �������!");
                    System.out.printf("�������� ���� �� ������ - %d\n", time.getDays());
                    for (ProductType productType : ProductType.values()) {
                        System.out.printf("������� ������� ���� %s - %d\n",
                                productType.getRussianName(), productType.getSells());
                    }
                    System.out.printf("��������� ��������������� �������� - %d\n", supermarketState.getCapacity());
                    System.out.println("����� ����");
                    logger.info("End of the game");
                    logger.info("Application closed");
                    return;
                }
                list.decreaseDaysBeforeExpiration();
                System.out.println("����� �������� ���");
                System.out.printf("������� - %d\n", supermarketState.maintenance());
                System.out.println("������� ���-������, ����� ������ ��������� ����");
                scanner.nextInt();
                time.nextDay();
                try {
                    JsonServices.serialize(time, list, supermarketState, "autosave");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.printf("���� %d\n", time.getDays());
            }
            System.out.printf("�����: %02d:%02d\n", time.getHours(), time.getMinutes());
            System.out.printf("������: %d\n", supermarketState.getMoney());
            System.out.printf("������������� ��������� ���� - %d/%d\n",
                    supermarketState.getQuantityInShoppingRoom(), supermarketState.getCapacity());
            System.out.println("����������:");
            System.out.println("1. ����� �����");
            System.out.println("2. ������� �������");
            System.out.println("3. ���������� �������");
            System.out.println("4. �������� ������");
            System.out.println("5. ��������� ����������� ��������� ����");
            System.out.println("6. ������������� ������������ �����");
            System.out.println("7. ������� ������ �� ����� �������������� �����");
            System.out.println("8. ���������");
            System.out.println("9. ���������");
            System.out.println("10. �����");
            System.out.print(">");
            i = scanner.nextInt();

            switch (i) {
                case 1:
                    while (true) {
                        System.out.println("����� ��������:");
                        System.out.println("1. 10 �����");
                        System.out.println("2. 1 ���");
                        System.out.println("3. 8 �����");
                        System.out.println("4. �� ����� �������� ���");
                        System.out.println("5. ������");
                        System.out.print(">");
                        i = scanner.nextInt();

                        switch (i) {
                            case 1 -> GameServices.waitTime(time, list, supermarketState, 1);
                            case 2 -> GameServices.waitTime(time, list, supermarketState, 6);
                            case 3 -> GameServices.waitTime(time, list, supermarketState, 48);
                            case 4 -> GameServices.waitTime(time, list, supermarketState, 0);
                            case 5 -> {
                            }
                            default -> {
                                System.out.println("��������� ����");
                                continue;
                            }
                        }
                        break;
                    }
                    break;

                case 2:
                    while (true) {
                        System.out.println("������� ����������:");
                        System.out.println("1. ��� ������");
                        for (ProductType productType : ProductType.values()) {
                            System.out.printf("%d. %s\n", productType.ordinal() + 2, productType.getRussianName());
                        }
                        System.out.println("6. ������");
                        System.out.print(">");
                        i = scanner.nextInt();

                        if (i != 1 && i != 2 && i != 3 && i != 4 && i != 5 && i != 6) {
                            System.out.println("��������� ����");
                        } else {
                            break;
                        }
                    }
                    if (i == 6) {
                        break;
                    }

                    showInfo(list, i);

                    while (true) {
                        System.out.println("1. ������� �������");
                        System.out.println("2. ���������");
                        System.out.print(">");
                        i = scanner.nextInt();
                        switch (i) {
                            case 1:
                                if (list.isEmpty()) {
                                    System.out.println("��� �������");
                                    break;
                                }
                                while (true) {
                                    System.out.println("�������� �����");
                                    System.out.print(">");
                                    i = scanner.nextInt();

                                    if (i > list.size() || i < 1) {
                                        System.out.println("� ������ ��� ������ � ����� �������");
                                        continue;
                                    }
                                    break;
                                }
                                i--;
                                System.out.println("�������� ��������");
                                System.out.println("1. �������� ����");
                                System.out.println("2. ����������� � �������� ���");
                                System.out.println("3. ����������� �� �����");
                                System.out.println("4. ������");
                                j = scanner.nextInt();

                                while (true) {
                                    switch (j) {
                                        case 1:
                                            System.out.printf("������� ���� - %d\n", list.getPrice(i));
                                            System.out.println("����� ����:");
                                            System.out.print(">");
                                            j = scanner.nextInt();
                                            list.setPrice(i, j);
                                            break;

                                        case 2:
                                            while (true) {
                                                System.out.printf("���������� �� ������ - %d\n",
                                                        list.getWarehouseQuantity(i));
                                                System.out.printf("���������� � �������� ���� - %d\n",
                                                        list.getShoppingRoomQuantity(i));
                                                System.out.println("����������� ������:");
                                                System.out.print(">");
                                                j = scanner.nextInt();

                                                if (j < -list.getShoppingRoomQuantity(i) ||
                                                        j > list.getWarehouseQuantity(i)) {
                                                    System.out.println("������� ����� ������������� ������");
                                                    continue;
                                                }
                                                if (supermarketState.getCapacity() -
                                                        supermarketState.getQuantityInShoppingRoom() >= j) {
                                                    if (j < 0) {
                                                        GameServices.toWarehouse(list, supermarketState, i, -j);
                                                    } else {
                                                        GameServices.toShoppingRoom(list, supermarketState, i, j);
                                                    }
                                                } else {
                                                    System.out.println("��� ����� � �������� ����");
                                                    continue;
                                                }
                                                break;
                                            }
                                            break;

                                        case 3:
                                            while (true) {
                                                System.out.printf("���������� � �������� ���� - %d\n",
                                                        list.getShoppingRoomQuantity(i));
                                                System.out.printf("���������� �� ������ - %d\n",
                                                        list.getWarehouseQuantity(i));
                                                System.out.println("����������� ������:");
                                                System.out.print(">");
                                                j = scanner.nextInt();

                                                if (j < -list.getWarehouseQuantity(i) ||
                                                        j > list.getShoppingRoomQuantity(i)) {
                                                    System.out.println("������� ����� ������������� ������");
                                                    continue;
                                                }
                                                if (supermarketState.getCapacity() -
                                                        supermarketState.getQuantityInShoppingRoom() >= -j) {
                                                    if (j < 0) {
                                                        GameServices.toShoppingRoom(list, supermarketState, i, -j);
                                                    } else {
                                                        GameServices.toWarehouse(list, supermarketState, i, j);
                                                    }
                                                } else {
                                                    System.out.println("��� ����� � �������� ����");
                                                    continue;
                                                }
                                                break;
                                            }
                                            break;

                                        case 4:
                                            break;

                                        default:
                                            System.out.println("��������� ����");
                                            continue;
                                    }
                                    break;
                                }
                                break;

                            case 2:
                                break;

                            default:
                                System.out.println("��������� ����");
                                continue;
                        }
                        break;
                    }
                    break;

                case 3:
                    showStats(list);
                    break;

                case 4:
                    while (true) {
                        System.out.println("��� ������:");
                        for (ProductType productType : ProductType.values()) {
                            System.out.printf("%d. %s (%d ����� �� �������)\n",
                                    productType.ordinal() + 1, productType.getRussianName(), productType.getPrice());
                        }
                        System.out.println("5. ������");
                        System.out.print(">");
                        i = scanner.nextInt();

                        if (i != 1 && i != 2 && i != 3 && i != 4 && i != 5) {
                            System.out.println("��������� ����");
                        } else {
                            break;
                        }
                    }
                    if (i == 5) {
                        break;
                    }

                    System.out.println("���������� (��.):");
                    System.out.print(">");
                    j = scanner.nextInt();

                    if (j <= 0) {
                        break;
                    }
                    int k;
                    int price = ProductType.values()[i - 1].getPrice();

                    while (true) {
                        System.out.printf("�������� ���� - %d\n", price * j);
                        System.out.println("������?");
                        System.out.println("1. ��");
                        System.out.println("2. ���");
                        System.out.print(">");
                        k = scanner.nextInt();

                        if (k != 1 && k != 2) {
                            System.out.println("��������� ����");
                        } else {
                            break;
                        }
                    }
                    if (k == 2) {
                        break;
                    }

                    if (supermarketState.getMoney() >= price * j) {
                        System.out.println("���� �� ������� ������ ��� �������:");
                        System.out.print(">");
                        k = scanner.nextInt();
                        supermarketState.setMoney(supermarketState.getMoney() - price * j);
                        list.add(ProductType.values()[i - 1], j, k);
                    } else {
                        System.out.println("������������ �����");
                    }
                    break;

                case 5:
                    while (true) {
                        System.out.printf("������� ����������� - %d\n", supermarketState.getCapacity());
                        System.out.println("��������� ��:");
                        System.out.println("1. 10 ������ (1000 �������� ������)");
                        System.out.println("2. 50 ������ (5000 �������� ������)");
                        System.out.println("3. 100 ������ (10000 �������� ������)");
                        System.out.println("4. ������");
                        System.out.print(">");
                        i = scanner.nextInt();

                        switch (i) {
                            case 1:
                                if (supermarketState.getMoney() >= 1000) {
                                    supermarketState.increaseCapacity(1);
                                } else {
                                    System.out.println("������������ �����");
                                }
                                break;

                            case 2:
                                if (supermarketState.getMoney() >= 5000) {
                                    supermarketState.increaseCapacity(5);
                                } else {
                                    System.out.println("������������ �����");
                                }
                                break;

                            case 3:
                                if (supermarketState.getMoney() >= 10000) {
                                    supermarketState.increaseCapacity(10);
                                } else {
                                    System.out.println("������������ �����");
                                }
                                break;

                            case 4:
                                break;

                            default:
                                System.out.println("��������� ����.");
                                continue;
                        }
                        break;

                    }
                    break;

                case 6:
                    int removed = list.removeExpiredProducts();
                    System.out.printf("������ %d ������ ������\n", removed);
                    break;

                case 7:
                    System.out.println("������� ������ (%):");
                    System.out.println(">");
                    i = scanner.nextInt();
                    list.makeDiscountOnAlmostExpiredGoods(i);
                    break;

                case 8:
                    try {
                        System.out.println("�������� ����������:");
                        String filename = scanner.next();
                        File file = new File("saves/" + filename + ".json");
                        if (file.isFile()) {
                            System.out.println("���������� � ������ ��������� ��� ����������. ������������?");
                            System.out.println("1. ��");
                            System.out.println("2. ���");
                            System.out.print(">");
                            i = scanner.nextInt();
                            if (i != 1) {
                                break;
                            }
                        }
                        JsonServices.serialize(time, list, supermarketState, filename);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 9:
                    try {
                        System.out.println("�������� ����������:");
                        String filename = scanner.next();
                        File file = new File("saves/" + filename + ".json");
                        if (!file.isFile()) {
                            System.out.println("���������� � ������ ��������� �� ����������");
                            break;
                        }
                        Classes classes = JsonServices.deserialize(filename);
                        time = classes.getTime();
                        list = classes.getList();
                        supermarketState = classes.getSupermarketState();
                        for (ProductType productType : ProductType.values()) {
                            productType.setSells(classes.getSells()[productType.ordinal()]);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 10:
                    logger.info("Application closed");
                    return;

                default:
                    System.out.println("��������� ����.");
            }
        }
    }

    public void showInfo(ListOfGoods list, int goodsType) {
        List<Integer> positions;
        ProductType[] types = ProductType.values();
        System.out.printf("|%5s|%8s|%16s|%20s|%26s|%4s|%43s|\n",
                "�����",
                "���",
                "����� ����������",
                "���������� �� ������",
                "���������� � �������� ����",
                "����",
                "���������� ���� �� ��������� ����� ��������");
        if (goodsType == 1) {
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("|%5s|%8s|%16s|%20s|%26s|%4s|%43s|\n",
                        i + 1,
                        types[list.getType(i).ordinal()].getRussianName(),
                        list.getWarehouseQuantity(i) + list.getShoppingRoomQuantity(i),
                        list.getWarehouseQuantity(i),
                        list.getShoppingRoomQuantity(i),
                        list.getPrice(i),
                        list.getDaysBeforeExpiration(i));
            }
        } else {
            positions = list.getPositions(ProductType.values()[goodsType - 2]);
            for (Integer position : positions) {
                System.out.printf("|%5s|%8s|%16s|%20s|%26s|%4s|%43s|\n",
                        position + 1,
                        types[list.getType(position).ordinal()].getRussianName(),
                        list.getWarehouseQuantity(position) + list.getShoppingRoomQuantity(position),
                        list.getWarehouseQuantity(position),
                        list.getShoppingRoomQuantity(position),
                        list.getPrice(position),
                        list.getDaysBeforeExpiration(position));
            }
        }
    }

    public void showStats(ListOfGoods list) {
        System.out.printf("|%8s|%15s|%16s|%20s|%26s|%7s|\n",
                "���",
                "���������� ����",
                "����� ����������",
                "���������� �� ������",
                "���������� � �������� ����",
                "�������");
        for (ProductType productType : ProductType.values()) {
            System.out.printf("|%8s|%15d|%16d|%20d|%26d|%7d|\n",
                    productType.getRussianName(),
                    productType.getPrice(),
                    list.getAllWarehouseQuantity(productType) + list.getAllShoppingRoomQuantity(productType),
                    list.getAllWarehouseQuantity(productType),
                    list.getAllShoppingRoomQuantity(productType),
                    productType.getSells());
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
