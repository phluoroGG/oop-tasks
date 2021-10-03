import java.util.ArrayList;
import java.util.List;

class Product {
    private Goods type;
    private int quantity;
    private int price;
    private int daysBeforeExpiration;

    public Product(Goods type, int quantity, int price) {
        this.type = type;
        this.quantity = quantity;
        this.price = price;
    }

    public Goods getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

public class ListOfGoods {
    public List<Product> list = new ArrayList<>();

    public void add(Product prod) {
        list.add(prod);
    }

    public int sell(Customer customer) {
        int money = 0;
        for (int i = 0; i < 4; i++) {
            Goods type = customer.needs[i].getType();
            int quantity = customer.needs[i].getQuantity();
            int price = customer.needs[i].getPrice();
            int pos = get(type);
            if (pos == -1 || list.get(i).getPrice() > price) {
                continue;
            }
            if (list.get(i).getQuantity() < quantity) {
                quantity = list.get(i).getQuantity();
            }
            money += quantity * list.get(i).getPrice();
            list.get(i).setQuantity(list.get(i).getQuantity() - quantity);
        }
        return money;
    }

    private int get(Goods some) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType() == some) {
                return i;
            }
        }
        return -1;
    }

    public void getStat() {
        for (Product product : list) {
            System.out.println(product.getQuantity());
        }
    }
}
