package com.thpt.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {

	private String id;
	private String username;
	private String password;
	private Integer gender;
	private String fullName;
	private String phone;
	private String email;
	private Long lastLogIn;
	private Long registerTime;
	private String avatarId;
	private String thumbnailId;
}
