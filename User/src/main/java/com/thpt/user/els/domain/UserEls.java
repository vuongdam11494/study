package com.thpt.user.els.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEls {
	private Integer gender;
	private String fullName;
	private String phone;
	private String email;
	private Long lastLogIn;
	private Long registerTime;
}
