package edu.csf.oop.java.supermarket;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);
        ListOfGoods list = new ListOfGoods();
        Scanner scanner = new Scanner(System.in);
        int i, j;

        Globals.hours = 8;
        Globals.minutes = 0;
        Globals.days = 1;
        Globals.money = 10000;
        Globals.prices = new int[]{10, 20, 30, 40};
        Globals.sells = new int[Products.values().length];
        Globals.capacity = 20;
        Globals.quantityInShoppingRoom = 0;

        System.out.printf("���� %d\n", Globals.days);

        while (true) {
            if (Globals.hours == 23) {
                if (list.isEmpty() && Globals.money < 0) {
                    System.out.println("� ��� ������������� ������ � ����������� ������! �� - �������!");
                    System.out.printf("�������� ���� �� ������ - %d\n", Globals.days);
                    String[] ruTypes = Products.productsToRussian();
                    for (i = 0; i < Products.values().length; i++) {
                        System.out.printf("������� ������� ���� %s - %d\n", ruTypes[i], Globals.sells[i]);
                    }
                    System.out.printf("��������� ��������������� �������� - %d\n", Globals.capacity);
                    System.out.println("����� ����");
                    return;
                }
                list.decreaseDaysBeforeExpiration();
                System.out.println("����� �������� ���");
                System.out.printf("������� - %d\n", 10 * Globals.capacity);
                Globals.money -= 10 * Globals.capacity;
                System.out.println("������� ���-������, ����� ������ ��������� ����");
                scanner.nextInt();
                Globals.hours = 8;
                Globals.minutes = 0;
                Globals.days++;
                System.out.printf("���� %d\n", Globals.days);
            }
            System.out.printf("�����: %02d:%02d\n", Globals.hours, Globals.minutes);
            System.out.printf("������: %d\n", Globals.money);
            System.out.printf("������������� ��������� ���� - %d/%d\n",
                    Globals.quantityInShoppingRoom, Globals.capacity);
            System.out.println("����������:");
            System.out.println("1. ����� �����");
            System.out.println("2. ������� �������");
            System.out.println("3. ���������� �������");
            System.out.println("4. �������� ������");
            System.out.println("5. ��������� ����������� ��������� ����");
            System.out.println("6. ������������� ������������ �����");
            System.out.println("7. ������� ������ �� ����� �������������� �����");
            System.out.println("8. �����");
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
                            case 1 -> Utils.waitTime(list, 1);
                            case 2 -> Utils.waitTime(list, 6);
                            case 3 -> Utils.waitTime(list, 48);
                            case 4 -> Utils.waitTime(list, 0);
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
                        String[] ruTypes = Products.productsToRussian();
                        System.out.println("������� ����������:");
                        System.out.println("1. ��� ������");
                        System.out.printf("2. %s\n", ruTypes[0]);
                        System.out.printf("3. %s\n", ruTypes[1]);
                        System.out.printf("4. %s\n", ruTypes[2]);
                        System.out.printf("5. %s\n", ruTypes[3]);
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

                    Utils.showInfo(list, i);
                    System.out.println("1. ������� �������");
                    System.out.println("2. ���������");
                    System.out.print(">");
                    i = scanner.nextInt();

                    while (true) {
                        switch (i) {
                            case 1:
                                while (true) {
                                    System.out.println("�������� �����");
                                    System.out.print(">");
                                    i = scanner.nextInt();

                                    if (i >= list.size()) {
                                        System.out.println("������, ��� ������ ������");
                                        continue;
                                    }
                                    break;
                                }
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
                                                if (Globals.capacity - Globals.quantityInShoppingRoom >= j) {
                                                    list.toShoppingRoom(i, j);
                                                } else {
                                                    System.out.println("��� ����� � �������� ����");
                                                    continue;
                                                }
                                                break;
                                            }
                                            break;

                                        case 3:
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
                                            list.toWarehouse(i, j);
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
                    Utils.showStats(list);
                    break;

                case 4:
                    while (true) {
                        String[] ruTypes = Products.productsToRussian();
                        System.out.println("��� ������:");
                        System.out.printf("1. %s (%d ����� �� �������)\n", ruTypes[0], Globals.prices[0]);
                        System.out.printf("2. %s (%d ����� �� �������)\n", ruTypes[1], Globals.prices[1]);
                        System.out.printf("3. %s (%d ����� �� �������)\n", ruTypes[2], Globals.prices[2]);
                        System.out.printf("4. %s (%d ����� �� �������)\n", ruTypes[3], Globals.prices[3]);
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

                    while (true) {
                        System.out.printf("�������� ���� - %d\n", Globals.prices[i - 1] * j);
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

                    if (Globals.money >= Globals.prices[i - 1] * j) {
                        System.out.println("���� �� ������� ������ ��� �������:");
                        System.out.print(">");
                        k = scanner.nextInt();
                        Globals.money -= Globals.prices[i - 1] * j;
                        list.add(Products.values()[i - 1], j, k);
                    } else {
                        System.out.println("������������ �����");
                    }
                    break;

                case 5:
                    while (true) {
                        System.out.printf("������� ����������� - %d\n", Globals.capacity);
                        System.out.println("��������� ��:");
                        System.out.println("1. 10 ������ (1000 �������� ������)");
                        System.out.println("2. 50 ������ (5000 �������� ������)");
                        System.out.println("3. 100 ������ (10000 �������� ������)");
                        System.out.println("4. ������");
                        System.out.print(">");
                        i = scanner.nextInt();

                        switch (i) {
                            case 1:
                                if (Globals.money >= 1000) {
                                    Globals.money -= 1000;
                                    Globals.capacity += 10;
                                } else {
                                    System.out.println("������������ �����");
                                }
                                break;

                            case 2:
                                if (Globals.money >= 5000) {
                                    Globals.money -= 5000;
                                    Globals.capacity += 50;
                                } else {
                                    System.out.println("������������ �����");
                                }
                                break;

                            case 3:
                                if (Globals.money >= 10000) {
                                    Globals.money -= 10000;
                                    Globals.capacity += 100;
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
                    return;

                default:
                    System.out.println("��������� ����.");
            }
        }
    }
}
