package edu.csf.oop.java.supermarket.services;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import edu.csf.oop.java.supermarket.product.Product;
import edu.csf.oop.java.supermarket.product.ProductType;
import edu.csf.oop.java.supermarket.supermarket.ListOfGoods;
import edu.csf.oop.java.supermarket.supermarket.SupermarketState;
import edu.csf.oop.java.supermarket.time.Time;

import java.util.ArrayList;
import java.util.List;

public class ClassesDeserializer extends StdDeserializer<Classes> {

    public ClassesDeserializer() {
        this(null);
    }

    public ClassesDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Classes deserialize(JsonParser parser, DeserializationContext deserializer) {
        Classes classes = new Classes();
        ObjectCodec codec = parser.getCodec();
        try {
            JsonNode node = codec.readTree(parser);

            JsonNode timeNode = node.get("time");
            JsonNode daysNode = timeNode.get("days");
            JsonNode hoursNode = timeNode.get("hours");
            JsonNode minutesNode = timeNode.get("minutes");
            int days = daysNode.asInt();
            int hours = hoursNode.asInt();
            int minutes = minutesNode.asInt();
            classes.setTime(new Time(days, hours, minutes));

            JsonNode listNode = node.get("list");
            JsonNode listOfGoodsNode = listNode.get("list");
            JsonNode warehouseQuantityListNode = listNode.get("warehouseQuantityList");
            JsonNode shoppingRoomQuantityListNode = listNode.get("shoppingRoomQuantityList");
            List<Product> list = new ArrayList<>();
            for (JsonNode jsonNode : listOfGoodsNode) {
                JsonNode typeNode = jsonNode.get("type");
                JsonNode warehouseQuantityNode = jsonNode.get("warehouseQuantity");
                JsonNode shoppingRoomQuantityNode = jsonNode.get("shoppingRoomQuantity");
                JsonNode priceNode = jsonNode.get("price");
                JsonNode daysBeforeExpirationNode = jsonNode.get("daysBeforeExpiration");
                ProductType type = ProductType.valueOf(typeNode.asText());
                int warehouseQuantity = warehouseQuantityNode.asInt();
                int shoppingRoomQuantity = shoppingRoomQuantityNode.asInt();
                int price = priceNode.asInt();
                int daysBeforeExpiration = daysBeforeExpirationNode.asInt();
                list.add(new Product(type, warehouseQuantity, shoppingRoomQuantity, price, daysBeforeExpiration));
            }
            int[] warehouseQuantityList = new int[warehouseQuantityListNode.size()];
            int i = 0;
            for (JsonNode jsonNode : warehouseQuantityListNode) {
                warehouseQuantityList[i] = jsonNode.asInt();
                i++;
            }
            int[] shoppingRoomQuantityList = new int[shoppingRoomQuantityListNode.size()];
            i = 0;
            for (JsonNode jsonNode : shoppingRoomQuantityListNode) {
                shoppingRoomQuantityList[i] = jsonNode.asInt();
                i++;
            }
            classes.setList(new ListOfGoods(list, warehouseQuantityList, shoppingRoomQuantityList));

            JsonNode supermarketState = node.get("supermarketState");
            JsonNode moneyNode = supermarketState.get("money");
            JsonNode capacityNode = supermarketState.get("capacity");
            JsonNode quantityInShoppingRoomNode = supermarketState.get("quantityInShoppingRoom");
            int money = moneyNode.asInt();
            int capacity = capacityNode.asInt();
            int quantityInShoppingRoom = quantityInShoppingRoomNode.asInt();
            classes.setSupermarketState(new SupermarketState(money, capacity, quantityInShoppingRoom));

            JsonNode sellsNode = node.get("sells");
            i = 0;
            int[] sells = new int[sellsNode.size()];
            for (JsonNode jsonNode : sellsNode) {
                sells[i] = (jsonNode.asInt());
                i++;
            }
            classes.setSells(sells);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }
}
