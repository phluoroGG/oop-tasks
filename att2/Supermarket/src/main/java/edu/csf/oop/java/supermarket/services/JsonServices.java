package edu.csf.oop.java.supermarket.services;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import edu.csf.oop.java.supermarket.product.ProductType;
import edu.csf.oop.java.supermarket.supermarket.ListOfGoods;
import edu.csf.oop.java.supermarket.supermarket.SupermarketState;
import edu.csf.oop.java.supermarket.time.Time;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonServices {

    public static void serialize(Time time, ListOfGoods list, SupermarketState supermarketState, String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
                new SimpleModule("CustomClassesSerializer", new Version(1, 0, 0, null, null, null));
        module.addSerializer(Classes.class, new ClassesSerializer());
        mapper.registerModule(module);
        int[] sells = new int[ProductType.values().length];
        for (int i = 0; i < ProductType.values().length; i++) {
            sells[i] = ProductType.values()[i].getSells();
        }
        Classes classes = new Classes(time, list, supermarketState, sells);
        mapper.writeValue(new File("saves/" + fileName + ".json"), classes);
    }

    public static Classes deserialize(String fileName) throws IOException {
        String json = Files.readString(Path.of("saves/" + fileName + ".json"));
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
                new SimpleModule("CustomClassesDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(Classes.class, new ClassesDeserializer());
        mapper.registerModule(module);
        return mapper.readValue(json, Classes.class);
    }
}
