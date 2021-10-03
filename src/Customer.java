import java.util.Random;

class Customer {
    public class Demand {
        private Goods type;
        private int quantity;
        private int price;

        public Goods getType() {
            return type;
        }

        public int getQuantity() {
            return quantity;
        }

        public int getPrice() {
            return price;
        }
    }

    public Demand[] needs = new Demand[Goods.values().length];
    public int money;

    private int[] rndArr(int length) {
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

    public Customer() {
        int length = Goods.values().length;
        int[] priority = rndArr(length);
        Random rnd = new Random();
        Goods[] types = Goods.values();
        for (int i = 0; i < length; i++) {
            needs[i] = new Demand();
            needs[i].type = types[priority[i]];
            needs[i].quantity = rnd.nextInt(10);
            needs[i].price = rnd.nextInt(10);
        }
        money = rnd.nextInt(100);

    }
}
