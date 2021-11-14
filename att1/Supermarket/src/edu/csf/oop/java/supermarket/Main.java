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

        System.out.printf("День %d\n", Globals.days);

        while (true) {
            if (Globals.hours == 23) {
                if (list.isEmpty() && Globals.money < 0) {
                    System.out.println("У вас отрицательный баланс и отсутствуют товары! Вы - банкрот!");
                    System.out.printf("Пройдено дней со старта - %d\n", Globals.days);
                    String[] ruTypes = Products.productsToRussian();
                    for (i = 0; i < Products.values().length; i++) {
                        System.out.printf("Продано товаров типа %s - %d\n", ruTypes[i], Globals.sells[i]);
                    }
                    System.out.printf("Финальная вместительность магазина - %d\n", Globals.capacity);
                    System.out.println("Конец игры");
                    return;
                }
                list.decreaseDaysBeforeExpiration();
                System.out.println("Конец рабочего дня");
                System.out.printf("Расходы - %d\n", 10 * Globals.capacity);
                Globals.money -= 10 * Globals.capacity;
                System.out.println("Введите что-нибудь, чтобы начать следующий день");
                scanner.nextInt();
                Globals.hours = 8;
                Globals.minutes = 0;
                Globals.days++;
                System.out.printf("День %d\n", Globals.days);
            }
            System.out.printf("Время: %02d:%02d\n", Globals.hours, Globals.minutes);
            System.out.printf("Деньги: %d\n", Globals.money);
            System.out.printf("Заполненность торгового зала - %d/%d\n",
                    Globals.quantityInShoppingRoom, Globals.capacity);
            System.out.println("Управление:");
            System.out.println("1. Ждать время");
            System.out.println("2. Таблица товаров");
            System.out.println("3. Статистика товаров");
            System.out.println("4. Закупить товары");
            System.out.println("5. Увеличить вместимость торгового зала");
            System.out.println("6. Ликвидировать просроченный товар");
            System.out.println("7. Сделать скидку на почти просрочившийся товар");
            System.out.println("8. Выйти");
            System.out.print(">");
            i = scanner.nextInt();

            switch (i) {
                case 1:
                    while (true) {
                        System.out.println("Время ожидания:");
                        System.out.println("1. 10 минут");
                        System.out.println("2. 1 час");
                        System.out.println("3. 8 часов");
                        System.out.println("4. До конца рабочего дня");
                        System.out.println("5. Отмена");
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
                                System.out.println("Повторите ввод");
                                continue;
                            }
                        }
                        break;
                    }
                    break;

                case 2:
                    while (true) {
                        String[] ruTypes = Products.productsToRussian();
                        System.out.println("Вывести информацию:");
                        System.out.println("1. Все товары");
                        System.out.printf("2. %s\n", ruTypes[0]);
                        System.out.printf("3. %s\n", ruTypes[1]);
                        System.out.printf("4. %s\n", ruTypes[2]);
                        System.out.printf("5. %s\n", ruTypes[3]);
                        System.out.println("6. Отмена");
                        System.out.print(">");
                        i = scanner.nextInt();

                        if (i != 1 && i != 2 && i != 3 && i != 4 && i != 5 && i != 6) {
                            System.out.println("Повторите ввод");
                        } else {
                            break;
                        }
                    }
                    if (i == 6) {
                        break;
                    }

                    Utils.showInfo(list, i);
                    System.out.println("1. Выбрать продукт");
                    System.out.println("2. Вернуться");
                    System.out.print(">");
                    i = scanner.nextInt();

                    while (true) {
                        switch (i) {
                            case 1:
                                while (true) {
                                    System.out.println("Выберите номер");
                                    System.out.print(">");
                                    i = scanner.nextInt();

                                    if (i >= list.size()) {
                                        System.out.println("Больше, чем размер списка");
                                        continue;
                                    }
                                    break;
                                }
                                System.out.println("Выберите действие");
                                System.out.println("1. Изменить цену");
                                System.out.println("2. Переместить в торговый зал");
                                System.out.println("3. Переместить на склад");
                                System.out.println("4. Отмена");
                                j = scanner.nextInt();

                                while (true) {
                                    switch (j) {
                                        case 1:
                                            System.out.printf("Текущая цена - %d\n", list.getPrice(i));
                                            System.out.println("Новая цена:");
                                            System.out.print(">");
                                            j = scanner.nextInt();
                                            list.setPrice(i, j);
                                            break;

                                        case 2:
                                            while (true) {
                                                System.out.printf("Количество на складе - %d\n",
                                                        list.getWarehouseQuantity(i));
                                                System.out.printf("Количество в торговом зале - %d\n",
                                                        list.getShoppingRoomQuantity(i));
                                                System.out.println("Переместить единиц:");
                                                System.out.print(">");
                                                j = scanner.nextInt();

                                                if (j < -list.getShoppingRoomQuantity(i) ||
                                                        j > list.getWarehouseQuantity(i)) {
                                                    System.out.println("Слишком много перемещаемого товара");
                                                    continue;
                                                }
                                                if (Globals.capacity - Globals.quantityInShoppingRoom >= j) {
                                                    list.toShoppingRoom(i, j);
                                                } else {
                                                    System.out.println("Нет места в торговом зале");
                                                    continue;
                                                }
                                                break;
                                            }
                                            break;

                                        case 3:
                                            System.out.printf("Количество в торговом зале - %d\n",
                                                    list.getShoppingRoomQuantity(i));
                                            System.out.printf("Количество на складе - %d\n",
                                                    list.getWarehouseQuantity(i));
                                            System.out.println("Переместить единиц:");
                                            System.out.print(">");
                                            j = scanner.nextInt();

                                            if (j < -list.getWarehouseQuantity(i) ||
                                                    j > list.getShoppingRoomQuantity(i)) {
                                                System.out.println("Слишком много перемещаемого товара");
                                                continue;
                                            }
                                            list.toWarehouse(i, j);
                                            break;

                                        case 4:
                                            break;

                                        default:
                                            System.out.println("Повторите ввод");
                                            continue;
                                    }
                                    break;
                                }
                                break;

                            case 2:
                                break;

                            default:
                                System.out.println("Повторите ввод");
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
                        System.out.println("Вид товара:");
                        System.out.printf("1. %s (%d денег за единицу)\n", ruTypes[0], Globals.prices[0]);
                        System.out.printf("2. %s (%d денег за единицу)\n", ruTypes[1], Globals.prices[1]);
                        System.out.printf("3. %s (%d денег за единицу)\n", ruTypes[2], Globals.prices[2]);
                        System.out.printf("4. %s (%d денег за единицу)\n", ruTypes[3], Globals.prices[3]);
                        System.out.println("5. Отмена");
                        System.out.print(">");
                        i = scanner.nextInt();

                        if (i != 1 && i != 2 && i != 3 && i != 4 && i != 5) {
                            System.out.println("Повторите ввод");
                        } else {
                            break;
                        }
                    }
                    if (i == 5) {
                        break;
                    }

                    System.out.println("Количество (шт.):");
                    System.out.print(">");
                    j = scanner.nextInt();

                    if (j <= 0) {
                        break;
                    }
                    int k;

                    while (true) {
                        System.out.printf("Итоговая цена - %d\n", Globals.prices[i - 1] * j);
                        System.out.println("Купить?");
                        System.out.println("1. Да");
                        System.out.println("2. Нет");
                        System.out.print(">");
                        k = scanner.nextInt();

                        if (k != 1 && k != 2) {
                            System.out.println("Повторите ввод");
                        } else {
                            break;
                        }
                    }
                    if (k == 2) {
                        break;
                    }

                    if (Globals.money >= Globals.prices[i - 1] * j) {
                        System.out.println("Цена за единицу товара для продажи:");
                        System.out.print(">");
                        k = scanner.nextInt();
                        Globals.money -= Globals.prices[i - 1] * j;
                        list.add(Products.values()[i - 1], j, k);
                    } else {
                        System.out.println("Недостаточно денег");
                    }
                    break;

                case 5:
                    while (true) {
                        System.out.printf("Текущая вместимость - %d\n", Globals.capacity);
                        System.out.println("Увеличить на:");
                        System.out.println("1. 10 единиц (1000 денежных единиц)");
                        System.out.println("2. 50 единиц (5000 денежных единиц)");
                        System.out.println("3. 100 единиц (10000 денежных единиц)");
                        System.out.println("4. Отмена");
                        System.out.print(">");
                        i = scanner.nextInt();

                        switch (i) {
                            case 1:
                                if (Globals.money >= 1000) {
                                    Globals.money -= 1000;
                                    Globals.capacity += 10;
                                } else {
                                    System.out.println("Недостаточно денег");
                                }
                                break;

                            case 2:
                                if (Globals.money >= 5000) {
                                    Globals.money -= 5000;
                                    Globals.capacity += 50;
                                } else {
                                    System.out.println("Недостаточно денег");
                                }
                                break;

                            case 3:
                                if (Globals.money >= 10000) {
                                    Globals.money -= 10000;
                                    Globals.capacity += 100;
                                } else {
                                    System.out.println("Недостаточно денег");
                                }
                                break;

                            case 4:
                                break;

                            default:
                                System.out.println("Повторите ввод.");
                                continue;
                        }
                        break;

                    }
                    break;

                case 6:
                    int removed = list.removeExpiredProducts();
                    System.out.printf("Убрано %d единиц товара\n", removed);
                    break;

                case 7:
                    System.out.println("Сделать скидку (%):");
                    System.out.println(">");
                    i = scanner.nextInt();
                    list.makeDiscountOnAlmostExpiredGoods(i);
                    break;

                case 8:
                    return;

                default:
                    System.out.println("Повторите ввод.");
            }
        }
    }
}
