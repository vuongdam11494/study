package com.thpt.user.service;

import static com.thpt.common.constant.KeyFields.FriendShipKeyField.*;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thpt.user.domain.FriendShip;
import com.thpt.user.domain.FriendShipRelationship;
import com.thpt.user.repository.FriendRepository;
import com.thpt.user.request.FriendShipResponseRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FriendService {

	private final FriendRepository friendRepository;
	
	public void friendShipRequest(String userReceiverId) {
		FriendShipRelationship friendShipRelationship = new FriendShipRelationship();
		friendShipRelationship.setUserReceiver(userReceiverId);
		friendShipRelationship.setUserRequest("");
		FriendShip friendShip = new FriendShip();
		friendShip.setFriendShipRelationship(friendShipRelationship);
		friendShip.setStatus(STATUS_REQUEST);
		friendRepository.addFriendShip(friendShip);
	}
	
	public void friendShipResponse(String userReceiverId,
			FriendShipResponseRequest friendShipResponseRequest) {
		if(friendShipResponseRequest.isStatus()) {
			friendRepository.updateFriendShip("", userReceiverId, STATUS_FRIEND);
		}else {
			deleteFriendShip(userReceiverId);
		}
	}
	
	public void friendShipBlock(String userReceiverId) {
		String friendShipId = friendRepository.findFriendShipId("", userReceiverId);
		if(friendShipId != null) {
			friendRepository.deleteFriendShip(friendShipId);
		}
		FriendShipRelationship friendShipRelationship = new FriendShipRelationship();
		friendShipRelationship.setUserReceiver(userReceiverId);
		friendShipRelationship.setUserRequest("");
		FriendShip friendShip = new FriendShip();
		friendShip.setFriendShipRelationship(friendShipRelationship);
		friendShip.setStatus(STATUS_BLOCK);
		friendRepository.addFriendShip(friendShip);
	}
	
	public boolean deleteFriendShip(String userReceiverId) {
		String friendShipId = friendRepository.findFriendShipId("", userReceiverId);
		if(friendShipId == null) {
			return false;
		}
		friendRepository.deleteFriendShip(friendShipId);
		return true;
	}
	
	public void listFriend() {
		List<String> userIds = friendRepository.listFriend("");
	}
	
	public void listBlock() {
		List<String> userIds = friendRepository.listBlock("");
	}
	
	public void listRequest() {
		List<String> userIds = friendRepository.listRequestToMe("");
	}
	
	public void listWaitResponse() {
		List<String> userIds = friendRepository.listWaitResponse("");
	}
}
