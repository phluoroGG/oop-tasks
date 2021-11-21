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

        System.out.printf("День %d\n", time.getDays());

        while (true) {
            if (time.isEndOfDay()) {
                if (list.isEmpty() && supermarketState.getMoney() < 0) {
                    System.out.println("У вас отрицательный баланс и отсутствуют товары! Вы - банкрот!");
                    System.out.printf("Пройдено дней со старта - %d\n", time.getDays());
                    for (ProductType productType : ProductType.values()) {
                        System.out.printf("Продано товаров типа %s - %d\n",
                                productType.getRussianName(), productType.getSells());
                    }
                    System.out.printf("Финальная вместительность магазина - %d\n", supermarketState.getCapacity());
                    System.out.println("Конец игры");
                    logger.info("End of the game");
                    logger.info("Application closed");
                    return;
                }
                list.decreaseDaysBeforeExpiration();
                System.out.println("Конец рабочего дня");
                System.out.printf("Расходы - %d\n", supermarketState.maintenance());
                System.out.println("Введите что-нибудь, чтобы начать следующий день");
                scanner.nextInt();
                time.nextDay();
                try {
                    JsonServices.serialize(time, list, supermarketState, "autosave");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.printf("День %d\n", time.getDays());
            }
            System.out.printf("Время: %02d:%02d\n", time.getHours(), time.getMinutes());
            System.out.printf("Деньги: %d\n", supermarketState.getMoney());
            System.out.printf("Заполненность торгового зала - %d/%d\n",
                    supermarketState.getQuantityInShoppingRoom(), supermarketState.getCapacity());
            System.out.println("Управление:");
            System.out.println("1. Ждать время");
            System.out.println("2. Таблица товаров");
            System.out.println("3. Статистика товаров");
            System.out.println("4. Закупить товары");
            System.out.println("5. Увеличить вместимость торгового зала");
            System.out.println("6. Ликвидировать просроченный товар");
            System.out.println("7. Сделать скидку на почти просрочившийся товар");
            System.out.println("8. Сохранить");
            System.out.println("9. Загрузить");
            System.out.println("10. Выйти");
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
                            case 1 -> GameServices.waitTime(time, list, supermarketState, 1);
                            case 2 -> GameServices.waitTime(time, list, supermarketState, 6);
                            case 3 -> GameServices.waitTime(time, list, supermarketState, 48);
                            case 4 -> GameServices.waitTime(time, list, supermarketState, 0);
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
                        System.out.println("Вывести информацию:");
                        System.out.println("1. Все товары");
                        for (ProductType productType : ProductType.values()) {
                            System.out.printf("%d. %s\n", productType.ordinal() + 2, productType.getRussianName());
                        }
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

                    showInfo(list, i);

                    while (true) {
                        System.out.println("1. Выбрать продукт");
                        System.out.println("2. Вернуться");
                        System.out.print(">");
                        i = scanner.nextInt();
                        switch (i) {
                            case 1:
                                if (list.isEmpty()) {
                                    System.out.println("Нет товаров");
                                    break;
                                }
                                while (true) {
                                    System.out.println("Выберите номер");
                                    System.out.print(">");
                                    i = scanner.nextInt();

                                    if (i > list.size() || i < 1) {
                                        System.out.println("В списке нет товара с таким номером");
                                        continue;
                                    }
                                    break;
                                }
                                i--;
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
                                                if (supermarketState.getCapacity() -
                                                        supermarketState.getQuantityInShoppingRoom() >= j) {
                                                    if (j < 0) {
                                                        GameServices.toWarehouse(list, supermarketState, i, -j);
                                                    } else {
                                                        GameServices.toShoppingRoom(list, supermarketState, i, j);
                                                    }
                                                } else {
                                                    System.out.println("Нет места в торговом зале");
                                                    continue;
                                                }
                                                break;
                                            }
                                            break;

                                        case 3:
                                            while (true) {
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
                                                if (supermarketState.getCapacity() -
                                                        supermarketState.getQuantityInShoppingRoom() >= -j) {
                                                    if (j < 0) {
                                                        GameServices.toShoppingRoom(list, supermarketState, i, -j);
                                                    } else {
                                                        GameServices.toWarehouse(list, supermarketState, i, j);
                                                    }
                                                } else {
                                                    System.out.println("Нет места в торговом зале");
                                                    continue;
                                                }
                                                break;
                                            }
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
                    showStats(list);
                    break;

                case 4:
                    while (true) {
                        System.out.println("Вид товара:");
                        for (ProductType productType : ProductType.values()) {
                            System.out.printf("%d. %s (%d денег за единицу)\n",
                                    productType.ordinal() + 1, productType.getRussianName(), productType.getPrice());
                        }
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
                    int price = ProductType.values()[i - 1].getPrice();

                    while (true) {
                        System.out.printf("Итоговая цена - %d\n", price * j);
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

                    if (supermarketState.getMoney() >= price * j) {
                        System.out.println("Цена за единицу товара для продажи:");
                        System.out.print(">");
                        k = scanner.nextInt();
                        supermarketState.setMoney(supermarketState.getMoney() - price * j);
                        list.add(ProductType.values()[i - 1], j, k);
                    } else {
                        System.out.println("Недостаточно денег");
                    }
                    break;

                case 5:
                    while (true) {
                        System.out.printf("Текущая вместимость - %d\n", supermarketState.getCapacity());
                        System.out.println("Увеличить на:");
                        System.out.println("1. 10 единиц (1000 денежных единиц)");
                        System.out.println("2. 50 единиц (5000 денежных единиц)");
                        System.out.println("3. 100 единиц (10000 денежных единиц)");
                        System.out.println("4. Отмена");
                        System.out.print(">");
                        i = scanner.nextInt();

                        switch (i) {
                            case 1:
                                if (supermarketState.getMoney() >= 1000) {
                                    supermarketState.increaseCapacity(1);
                                } else {
                                    System.out.println("Недостаточно денег");
                                }
                                break;

                            case 2:
                                if (supermarketState.getMoney() >= 5000) {
                                    supermarketState.increaseCapacity(5);
                                } else {
                                    System.out.println("Недостаточно денег");
                                }
                                break;

                            case 3:
                                if (supermarketState.getMoney() >= 10000) {
                                    supermarketState.increaseCapacity(10);
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
                    try {
                        System.out.println("Название сохранения:");
                        String filename = scanner.next();
                        File file = new File("saves/" + filename + ".json");
                        if (file.isFile()) {
                            System.out.println("Сохранение с данным названием уже существует. Перезаписать?");
                            System.out.println("1. Да");
                            System.out.println("2. Нет");
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
                        System.out.println("Название сохранения:");
                        String filename = scanner.next();
                        File file = new File("saves/" + filename + ".json");
                        if (!file.isFile()) {
                            System.out.println("Сохранения с данным названием не существует");
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
                    System.out.println("Повторите ввод.");
            }
        }
    }

    public void showInfo(ListOfGoods list, int goodsType) {
        List<Integer> positions;
        ProductType[] types = ProductType.values();
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
                "Тип",
                "Закупочная цена",
                "Общее количество",
                "Количество на складе",
                "Количество в торговом зале",
                "Продано");
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
