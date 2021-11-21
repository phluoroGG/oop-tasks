package edu.csf.oop.java.supermarket.services;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import edu.csf.oop.java.supermarket.product.Product;
import edu.csf.oop.java.supermarket.product.ProductType;

import java.io.IOException;

public class ClassesSerializer extends StdSerializer<Classes> {

    public ClassesSerializer() {
        this(null);
    }

    public ClassesSerializer(Class<Classes> T) {
        super(T);
    }
    @Override
    public void serialize(Classes classes,
                          JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeObjectFieldStart("time");
        jsonGenerator.writeNumberField("days", classes.getTime().getDays());
        jsonGenerator.writeNumberField("hours", classes.getTime().getHours());
        jsonGenerator.writeNumberField("minutes", classes.getTime().getMinutes());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeObjectFieldStart("list");
        jsonGenerator.writeArrayFieldStart("list");
        for (int i = 0; i < classes.getList().size(); i++) {
            Product product = classes.getList().get(i);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("type", product.getType().name());
            jsonGenerator.writeNumberField("warehouseQuantity", product.getWarehouseQuantity());
            jsonGenerator.writeNumberField("shoppingRoomQuantity", product.getShoppingRoomQuantity());
            jsonGenerator.writeNumberField("price", product.getPrice());
            jsonGenerator.writeNumberField("daysBeforeExpiration", product.getDaysBeforeExpiration());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeArrayFieldStart("warehouseQuantityList");
        for (ProductType productType : ProductType.values()) {
            jsonGenerator.writeNumber(classes.getList().getAllWarehouseQuantity(productType));
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeArrayFieldStart("shoppingRoomQuantityList");
        for (ProductType productType : ProductType.values()) {
            jsonGenerator.writeNumber(classes.getList().getAllShoppingRoomQuantity(productType));
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();

        jsonGenerator.writeObjectFieldStart("supermarketState");
        jsonGenerator.writeNumberField("money", classes.getSupermarketState().getMoney());
        jsonGenerator.writeNumberField("capacity", classes.getSupermarketState().getCapacity());
        jsonGenerator.writeNumberField("quantityInShoppingRoom",
                classes.getSupermarketState().getQuantityInShoppingRoom());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeArrayFieldStart("sells");
        for (int i = 0; i < classes.getSells().length; i++) {
            jsonGenerator.writeNumber(classes.getSells()[i]);
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
