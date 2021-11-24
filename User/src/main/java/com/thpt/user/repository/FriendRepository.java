package com.thpt.user.repository;

import org.springframework.stereotype.Repository;

import com.mongodb.DB;
import com.mongodb.DBObject;
import com.thpt.common.mongo.AbstractEntiry;
import com.thpt.user.domain.FriendShip;
import com.thpt.user.repository.mongo.DBManager;

@Repository
public class FriendRepository extends AbstractEntiry<FriendShip>{

	@Override
	protected DB getDatabase() {
		return DBManager.getUserDB();
	}
	
	@Override
	protected String getCollectionName() {
		return "friend_ship";
	}
	
	@Override
	public FriendShip castFromObject(DBObject dbObject) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public DBObject castFromUser(FriendShip t) {
		// TODO Auto-generated method stub
		return null;
	}
}
