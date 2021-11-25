package com.thpt.file.repository;

import static com.thpt.common.constant.KeyFields.FileKey.DURATION;
import static com.thpt.common.constant.KeyFields.FileKey.FILE_PATH;
import static com.thpt.common.constant.KeyFields.FileKey.SIZE;
import static com.thpt.common.constant.KeyFields.FileKey.STATUS;
import static com.thpt.common.constant.KeyFields.FileKey.THUMBNAIL;
import static com.thpt.common.constant.KeyFields.FileKey.THUMBNAIL_PATH;
import static com.thpt.common.constant.KeyFields.FileKey.TYPE;
import static com.thpt.common.constant.KeyFields.FileKey.UPLOAD_TIME;
import static com.thpt.common.constant.KeyFields.FileKey.USER_ID;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.thpt.common.mongo.AbstractEntiry;
import com.thpt.common.mongo.DBManager;
import com.thpt.file.domain.File;
import com.thpt.file.enums.FileStatus;
import com.thpt.file.enums.FileType;

@Repository
public class FileRepository extends AbstractEntiry<File> {

	@Override
	protected DB getDatabase() {
		return DBManager.getFileDB();
	}

	@Override
	protected String getCollectionName() {
		return "file";
	}

	public void insert(File file) {
		DBObject dbObject = castFromUser(file);
		if (dbObject != null) {
			getCollection().insert(dbObject);
		}
	}

	public File findById(String fileId) {
		DBObject query = new BasicDBObject(ID, fileId);
		DBObject data = getCollection().findOne(query);
		if (data == null) {
			return null;
		}
		return castFromObject(data);
	}

	public List<File> findByIds(List<String> ids) {
		List<DBObject> dbObjects = new ArrayList<DBObject>();
		DBObject query = null;
		DBObject result = null;
		for (String id : ids) {
			query = new BasicDBObject(ID, id);
			result = getCollection().findOne(query);
			if (result != null) {
				dbObjects.add(result);
			}
		}
		return objsToFiles(dbObjects);
	}

	private List<File> objsToFiles(List<DBObject> dbObjects) {
		return dbObjects.stream().map(this::castFromObject).collect(Collectors.toList());
	}

	@Override
	public File castFromObject(DBObject dbObject) {
		if (dbObject == null) {
			return null;
		}
		File file = new File();
		BasicDBObject basicDBObject = (BasicDBObject) dbObject;
		file.setDuration(basicDBObject.getInt(DURATION));
		file.setFilePath(basicDBObject.getString(FILE_PATH));
		file.setFileStatus(FileStatus.getFileStatus(basicDBObject.getString(STATUS)));
		file.setFileType(FileType.getFileType(basicDBObject.getString(TYPE)));
		file.setId(basicDBObject.getObjectId(ID).toHexString());
		file.setSize(basicDBObject.getLong(SIZE));
		file.setThumbnailId(basicDBObject.getString(THUMBNAIL));
		file.setUploadTime(basicDBObject.getLong(UPLOAD_TIME));
		file.setUserId(basicDBObject.getString(USER_ID));
		file.setThumbnailPath(basicDBObject.getString(THUMBNAIL_PATH));
		return null;
	}

	@Override
	public DBObject castFromUser(File t) {
		if (t == null) {
			return null;
		}
		DBObject dbObject = new BasicDBObject();
		dbObject.put(DURATION, t.getDuration());
		dbObject.put(FILE_PATH, t.getFilePath());
		dbObject.put(SIZE, t.getSize());
		dbObject.put(STATUS, t.getFileStatus());
		dbObject.put(THUMBNAIL, t.getThumbnailId());
		dbObject.put(TYPE, t.getFileType());
		dbObject.put(UPLOAD_TIME, t.getUploadTime());
		dbObject.put(USER_ID, t.getUserId());
		dbObject.put(THUMBNAIL_PATH, t.getThumbnailPath());
		return dbObject;
	}

}
