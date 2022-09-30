package edu.eci.arep.logService.persistence;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import java.util.HashMap;

public class Repository {

    MongoClient client;
    MongoDatabase database;
    MongoCollection<Document> strings;

    {
        client = MongoClients.create("mongodb://localhost:27017");
        database = client.getDatabase("local");
        database.createCollection("strings");
        strings = database.getCollection("strings");
    }

    public void saveNewString(String newString) {
        System.out.println("newString = " + newString);
    }

    public String getLastStrings(int qty) {

        HashMap<Integer, String> strings = new HashMap<>();
        for (int i = 0; i < qty; i++) {
            strings.put(i, i+"");
        }
        return new Gson().toJson(strings);
    }
}