package com.thpt.common.mongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.thpt.common.config.MongoConfig;

import lombok.Getter;

public class DBManager {
    private static final String USER_DB_NAME = "user_db";
    private static final String FILE_DB_NAME = "file_db";

    private static DB user_db;
    private static DB file_db;

    public static void initConncetion(MongoConfig config) {
        MongoClientURI uri = new MongoClientURI(config.getUri());
        MongoClient client = new MongoClient(uri);
        user_db = client.getDB(USER_DB_NAME);
        file_db = client.getDB(FILE_DB_NAME);
    }

    public static DB getUserDB() {
        return user_db;
    }
    
    public static DB getFileDB() {
        return file_db;
    }
}
