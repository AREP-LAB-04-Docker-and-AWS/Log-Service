package edu.eci.arep.logService.persistence;

import com.google.gson.Gson;
import com.mongodb.client.*;

import org.bson.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Repository {

    private final MongoClient client;
    private final MongoDatabase database;
    private final DateTimeFormatter formatter;
    private static final AtomicInteger stringsSequence;

    static {
        stringsSequence = new AtomicInteger(-1);
    }

    {
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        client = MongoClients.create("mongodb://localhost:27017");
        database = client.getDatabase("local");
        String collectionName = "strings";
        try {
            database.createCollection(collectionName);
        } catch (Exception e) {
            System.out.println("Collection '" + collectionName + "' already exist.");
        }
    }

    public void saveNewString(String newString) {
        Document document = new Document();
        document.append("id", stringsSequence.addAndGet(1));
        document.append("string", newString);
        document.append("date", formatter.format(LocalDateTime.now()));
        database.getCollection("strings").insertOne(document);
    }

    public String getLastStrings() {
        HashMap<Integer, String> strings = new HashMap<>();
        FindIterable<Document> iterable = database.getCollection("strings").find();
        for (Document document: iterable) {
            strings.put(
                    Integer.valueOf(document.get("id").toString()),
                    document.get("string").toString() + " - " + document.get("date").toString()
            );
        }
        return new Gson().toJson(strings);
    }
}