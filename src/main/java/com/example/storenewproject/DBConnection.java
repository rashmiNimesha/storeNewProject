package com.example.storenewproject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DBConnection {
    private static MongoDatabase mongoDatabase;
    private static DBConnection dbConnection;

    private DBConnection(){
        MongoClient client = new MongoClient("localhost", 27017);
        mongoDatabase = client.getDatabase("test");
    }

    public static DBConnection getConnection() {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }


    public MongoCollection getCollectionByName(String collectionName){
        return mongoDatabase.getCollection(collectionName);
    }



}

