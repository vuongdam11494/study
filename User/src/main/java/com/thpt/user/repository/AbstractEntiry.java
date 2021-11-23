package com.thpt.user.repository;

import com.mongodb.DBObject;
import com.thpt.user.repository.mongo.BaseRepository;

public abstract class AbstractEntiry<T> extends BaseRepository{

    abstract public DBObject castFromUser(T t);
    abstract public <T> T castFromObject(DBObject dbObject);
}
