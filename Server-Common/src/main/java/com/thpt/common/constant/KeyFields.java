package com.thpt.common.constant;

public class KeyFields {

	public class UserKeyFields {
		public static final String ID = "_id";
		public static final String USERNAME = "username";
	    public static final String PASSWORD = "password";
	    public static final String GENDER = "gender";
	    public static final String FULLNAME = "full_name";
	    public static final String PHONE = "phone";
	    public static final String EMAIL = "email";
	    public static final String AVATAR_ID = "avatar_id";
	    public static final String THUMBNAIL_ID = "thumbnail_id";
	    public static final String AVATAR_URL = "avatar_url";
	    public static final String THUMBNAIL_URL = "thumbnail_url";
	    
	    public static final int GENDER_MALE = 0;
	    public static final int GENDER_FEMALE = 1;
	}
	
	public class FriendShipKeyField {
		public static final String ID = "_id";
		public static final String FRIEND_SHIP_RELATION = "friend_ship_relation";
		public static final String STATUS = "status";
		
		public static final int STATUS_REQUEST = 1;
		public static final int STATUS_FRIEND = 2;
		public static final int STATUS_BLOCK = 3;
		
		public class FriendShipRelationshipKey {
			public static final String USER_REQUEST = "user_request";
			public static final String USER_RECEIVER = "user_receiver";
		}
	}
	
	public class FileKey {
		public static final String ID = "_id";
		public static final String DURATION = "duration";
		public static final String TYPE = "type";
		public static final String THUMBNAIL = "thumbnail";
		public static final String SIZE = "size";
		public static final String USER_ID = "user_id";
		public static final String UPLOAD_TIME = "upload_time";
		public static final String STATUS = "status";
		public static final String FILE_PATH = "file_path";
		public static final String THUMBNAIL_PATH = "thumbnail_path";
	}
}
