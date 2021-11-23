package com.thpt.user.repository;

import static com.thpt.user.utils.constant.KeyFields.UserKeyFields.EMAIL;
import static com.thpt.user.utils.constant.KeyFields.UserKeyFields.FULLNAME;
import static com.thpt.user.utils.constant.KeyFields.UserKeyFields.GENDER;
import static com.thpt.user.utils.constant.KeyFields.UserKeyFields.PASSWORD;
import static com.thpt.user.utils.constant.KeyFields.UserKeyFields.PHONE;
import static com.thpt.user.utils.constant.KeyFields.UserKeyFields.USERNAME;

import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.thpt.user.domain.User;
import com.thpt.user.repository.mongo.DBManager;

@Repository
public class UserRepository extends AbstractEntiry<User>{

    @Override
    protected String getCollectionName() {
        return "user_info";
    }
    
    @Override
    protected DB getDatabase() {
        return DBManager.getUserDB();
    }
    
    public void insertUser(User user) {
    	DBObject dbObject = castFromUser(user);
    	if(dbObject != null) {
    		getCollection().insert(dbObject);
    		String userId = dbObject.get(ID).toString();
    		if(userId != null) {
    			user.setUserId(userId);
    		}
    	}
    }
    
    public User findByUsername(String username) {
        DBObject query = new BasicDBObject(USERNAME, username);
        DBObject object = getCollection().findOne(query);
        return castFromObject(object);
    }
    
    public DBObject castFromUser(User user) {
    	if(user == null) {
    		return null;
    	}
    	DBObject dbObject = new BasicDBObject();
    	dbObject.put(USERNAME, user.getUsername());
    	dbObject.put(PASSWORD, user.getPassword());
    	dbObject.put(GENDER, user.getGender());
    	dbObject.put(FULLNAME, user.getFullName());
    	dbObject.put(PHONE, user.getPhone());
    	dbObject.put(EMAIL, user.getEmail());
    	
    	return dbObject;
    }
    
    public User castFromObject(DBObject dbObject) {
    	if(dbObject == null) {
    		return null;
    	}
    	
    	BasicDBObject basicDBObject = (BasicDBObject)dbObject;
    	User user = User.builder()
    			.userId(basicDBObject.getObjectId(ID).toHexString())
    			.username(basicDBObject.getString(USERNAME))
    			.password(basicDBObject.getString(PASSWORD))
    			.gender(basicDBObject.getInt(GENDER))
    			.fullName(basicDBObject.getString(FULLNAME))
    			.phone(basicDBObject.getString(PHONE))
    			.email(basicDBObject.getString(EMAIL))
    			.build();
    	return user;
    }
}
