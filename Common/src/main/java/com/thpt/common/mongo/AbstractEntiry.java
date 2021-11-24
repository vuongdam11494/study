package com.thpt.common.mongo;

import com.mongodb.DBObject;

public abstract class AbstractEntiry<T> extends BaseRepository{

    abstract public DBObject castFromUser(T t);
    abstract public T castFromObject(DBObject dbObject);
}
