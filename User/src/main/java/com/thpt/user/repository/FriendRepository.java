package com.thpt.user.repository;

import static com.thpt.common.constant.KeyFields.FriendShipKeyField.FriendShipRelationshipKey.*;

import java.util.ArrayList;
import java.util.List;

import static com.thpt.common.constant.KeyFields.FriendShipKeyField.*;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.thpt.common.mongo.AbstractEntiry;
import com.thpt.user.domain.FriendShip;
import com.thpt.user.domain.FriendShipRelationship;
import com.thpt.user.repository.mongo.DBManager;

@Repository
public class FriendRepository extends AbstractEntiry<FriendShip> {

	@Override
	protected DB getDatabase() {
		return DBManager.getUserDB();
	}

	@Override
	protected String getCollectionName() {
		return "friend_ship";
	}

	public void addFriendShip(FriendShip friendShip) {
		DBObject dbObject = castFromUser(friendShip);
		if (dbObject != null) {
			getCollection().insert(dbObject);
		}
	}

	public void updateFriendShip(String userId1, String userId2, Integer status) {
		DBObject query = new BasicDBObject(userId1, new BasicDBObject(EXISTS, true))
				.append(userId2, new BasicDBObject(EXISTS, true));
		DBObject update = new BasicDBObject(STATUS,status);
		getCollection().update(query, new BasicDBObject(SET, update));
	}
	
	public void fromFriendShipToBlock(String userIdRequest, String userIdReceiver) {
		DBObject query = new BasicDBObject(userIdRequest, new BasicDBObject(EXISTS, true))
				.append(userIdReceiver, new BasicDBObject(EXISTS, true));
		DBObject update = new BasicDBObject(STATUS,STATUS_BLOCK)
				;
		getCollection().update(query, new BasicDBObject(SET, update));
	}

	public void deleteFriendShip(String friendShipId) {
		if(!ObjectId.isValid(friendShipId)) {
			return;
		}
		DBObject query = new BasicDBObject(ID,
				new ObjectId(friendShipId));
		getCollection().remove(query);
	}

	public String findFriendShipId(String userId1, String userId2) {
		DBObject query = new BasicDBObject(userId1, new BasicDBObject(EXISTS, true))
				.append(userId2, new BasicDBObject(EXISTS, true));
		DBObject dbObject = getCollection().findOne(query);
		if(dbObject == null) {
			return null;
		}
		return dbObject.get(ID).toString();
	}
	
	public List<String> listFriend(String userId){
		DBObject query = new BasicDBObject(FRIEND_SHIP_RELATION + "." + USER_REQUEST, userId)
				.append(STATUS, STATUS_FRIEND);
		DBCursor cursor = getCollection().find(query);
		List<String> userIds = new ArrayList<String>();
		while(cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			userIds.add(dbObject.get(USER_RECEIVER).toString());
		}
		return userIds;
	}
	
	public List<String> listBlock(String userId){
		DBObject query = new BasicDBObject(FRIEND_SHIP_RELATION + "." + USER_REQUEST, userId)
				.append(STATUS, STATUS_BLOCK);
		DBCursor cursor = getCollection().find(query);
		List<String> userIds = new ArrayList<String>();
		while(cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			userIds.add(dbObject.get(USER_RECEIVER).toString());
		}
		return userIds;
	}
	
	public List<String> listRequestToMe(String userId){
		DBObject query = new BasicDBObject(FRIEND_SHIP_RELATION + "." + USER_RECEIVER, userId)
				.append(STATUS, STATUS_REQUEST);
		DBCursor cursor = getCollection().find(query);
		List<String> userIds = new ArrayList<String>();
		while(cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			userIds.add(dbObject.get(USER_REQUEST).toString());
		}
		return userIds;
	}
	
	public List<String> listWaitResponse(String userId){
		DBObject query = new BasicDBObject(FRIEND_SHIP_RELATION + "." + USER_REQUEST, userId)
				.append(STATUS, STATUS_REQUEST);
		DBCursor cursor = getCollection().find(query);
		List<String> userIds = new ArrayList<String>();
		while(cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			userIds.add(dbObject.get(USER_RECEIVER).toString());
		}
		return userIds;
	}

	@Override
	public FriendShip castFromObject(DBObject dbObject) {
		if (dbObject == null) {
			return null;
		}
		BasicDBObject basicDBObject = (BasicDBObject) dbObject;
		FriendShipRelationship friendShipRelationship = new FriendShipRelationship();
		friendShipRelationship.setUserRequest(basicDBObject.getString(USER_REQUEST));
		friendShipRelationship.setUserReceiver(basicDBObject.getString(USER_RECEIVER));
		FriendShip friendShip = new FriendShip();
		friendShip.setId(basicDBObject.getObjectId(ID).toHexString());
		friendShip.setFriendShipRelationship(friendShipRelationship);
		friendShip.setStatus(basicDBObject.getInt(STATUS));
		return null;
	}

	@Override
	public DBObject castFromUser(FriendShip t) {
		if (t == null) {
			return null;
		}
		String requestId = t.getFriendShipRelationship().getUserRequest();
		String receiverId = t.getFriendShipRelationship().getUserReceiver();
		DBObject friendRelation = new BasicDBObject(USER_REQUEST, requestId).append(USER_RECEIVER, receiverId);
		DBObject dbObject = new BasicDBObject();
		dbObject.put(FRIEND_SHIP_RELATION, friendRelation);
		dbObject.put(STATUS, t.getStatus());
		dbObject.put(requestId, requestId);
		dbObject.put(receiverId, receiverId);
		return dbObject;
	}
}
