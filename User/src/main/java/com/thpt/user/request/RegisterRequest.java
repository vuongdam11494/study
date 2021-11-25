package com.thpt.user.request;

import static com.thpt.common.constant.KeyFields.UserKeyFields.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequest {
    
	private String id;
    @JsonProperty(USERNAME)
    private String username;
    @JsonProperty(PASSWORD)
    private String password;
    @JsonProperty(GENDER)
    private Integer gender;
    @JsonProperty(FULLNAME)
    private String fullName;
    @JsonProperty(PHONE)
    private String phone;
    @JsonProperty(EMAIL)
    private String email;
    @JsonProperty(AVATAR_ID)
    private String avatarId;
    @JsonProperty(AVATAR_URL)
	private String avatarUrl;
    @JsonProperty(THUMBNAIL_ID)
	private String thumbnailId;
    @JsonProperty(THUMBNAIL_URL)
	private String thumbnailUrl;
    
}
