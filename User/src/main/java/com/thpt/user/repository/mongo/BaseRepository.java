package com.thpt.user.repository.mongo;

import com.mongodb.DB;
import com.mongodb.DBCollection;

public abstract class BaseRepository {

    protected static final String ID = "_id";

    protected static final String PUSH = "$push";
    protected static final String PULL = "$pull";
    protected static final String SET = "$set";
    protected static final String UNSET = "$unset";
    protected static final String NOT_EQUALS = "$ne";
    protected static final String LESS_THAN = "$lt";
    protected static final String LESS_THAN_OR_EQUAL = "$lte";
    protected static final String GREATER_THAN = "$gt";
    protected static final String GREATER_THAN_OR_EQUAL = "$gte";
    protected static final String INCREASE = "$inc";
    protected static final String OR = "$or";
    protected static final String IN = "$in";
    protected static final String NOT_IN = "$nin";
    protected static final String EXISTS = "$exists";
    protected static final String AND = "$and";
    protected static final String NE = "$ne";
    protected static final String ELEMENT_MATCH = "$elemMatch";
    protected static final String REGEX = "$regex";
    protected static final String MATCH = "$match";
    protected static final String GROUP = "$group";
    protected static final String PROJECT = "$project";
    protected static final String IF_NULL = "$ifNull";
    protected static final String SUM = "$sum";
    protected static final String ADD = "$add";
    protected static final String ADD_TO_SET = "$addToSet";

    protected static final int KEEP_FIELD = 1;
    protected static final int REJECT_FIELD = 0;

    protected static final int ASCENDING_ORDER = 1;
    protected static final int DESCENDING_ORDER = -1;

    protected abstract DB getDatabase();
    
    protected abstract String getCollectionName();
    
    protected DBCollection getCollection() {
        DB db = getDatabase();
        String collectionName = getCollectionName();
        return db.getCollection(collectionName);
    }
}
