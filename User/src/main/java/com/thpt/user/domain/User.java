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

	private String userId;
	private String username;
	private String password;
	private Integer gender;
	private String fullName;
	private String phone;
	private String email;
}
