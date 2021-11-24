package com.thpt.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FriendShipRelationship {

	private String userRequest;
	private String userReceiver;
}
