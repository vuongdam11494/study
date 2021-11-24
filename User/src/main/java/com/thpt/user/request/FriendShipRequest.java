package com.thpt.user.request;

import static com.thpt.common.constant.KeyFields.FriendShipKeyField.FriendShipRelationshipKey.*;
import static com.thpt.common.constant.KeyFields.FriendShipKeyField.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FriendShipRequest {

	@JsonProperty(USER_REQUEST)
	private String userRequest;
	@JsonProperty(USER_RECEIVER)
	private String userReceiver;
	@JsonProperty(STATUS)
	private String status;
}
