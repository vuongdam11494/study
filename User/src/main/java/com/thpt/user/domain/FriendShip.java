package com.thpt.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FriendShip {

	private String id;
	private FriendShipRelationship friendShipRelationship;
	private Integer status;
}
