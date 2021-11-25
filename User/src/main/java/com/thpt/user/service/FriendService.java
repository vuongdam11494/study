package com.thpt.user.service;

import static com.thpt.common.constant.KeyFields.FriendShipKeyField.STATUS_BLOCK;
import static com.thpt.common.constant.KeyFields.FriendShipKeyField.STATUS_FRIEND;
import static com.thpt.common.constant.KeyFields.FriendShipKeyField.STATUS_REQUEST;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thpt.common.utils.RequestContext;
import com.thpt.user.domain.FriendShip;
import com.thpt.user.domain.FriendShipRelationship;
import com.thpt.user.els.domain.UserEls;
import com.thpt.user.els.repository.UserElsRepository;
import com.thpt.user.repository.FriendRepository;
import com.thpt.user.request.FriendShipResponseRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FriendService {

	private final FriendRepository friendRepository;
	private final UserElsRepository elsRepository;
	
	public void friendShipRequest(String userReceiverId) {
		String userId = RequestContext.getUserId();
		FriendShipRelationship friendShipRelationship = new FriendShipRelationship();
		friendShipRelationship.setUserReceiver(userReceiverId);
		friendShipRelationship.setUserRequest(userId);
		FriendShip friendShip = new FriendShip();
		friendShip.setFriendShipRelationship(friendShipRelationship);
		friendShip.setStatus(STATUS_REQUEST);
		friendRepository.addFriendShip(friendShip);
	}
	
	public void friendShipResponse(String userReceiverId,
			FriendShipResponseRequest friendShipResponseRequest) {
		String userId = RequestContext.getUserId();
		if(friendShipResponseRequest.isStatus()) {
			friendRepository.updateFriendShip(userId, userReceiverId, STATUS_FRIEND);
		}else {
			deleteFriendShip(userReceiverId);
		}
	}
	
	public void friendShipBlock(String userReceiverId) {
		String userId = RequestContext.getUserId();
		String friendShipId = friendRepository.findFriendShipId(userId, userReceiverId);
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
		String userId = RequestContext.getUserId();
		String friendShipId = friendRepository.findFriendShipId(userId, userReceiverId);
		if(friendShipId == null) {
			return false;
		}
		friendRepository.deleteFriendShip(friendShipId);
		return true;
	}
	
	public List<UserEls> listFriend() {
		String userId = RequestContext.getUserId();
		List<String> userIds = friendRepository.listFriend(userId);
		List<UserEls> userEls = elsRepository.searchByUserIds(userIds);
		return userEls;
	}
	
	public List<UserEls> listBlock() {
		String userId = RequestContext.getUserId();
		List<String> userIds = friendRepository.listBlock(userId);
		List<UserEls> userEls = elsRepository.searchByUserIds(userIds);
		return userEls;
	}
	
	public List<UserEls> listRequest() {
		String userId = RequestContext.getUserId();
		List<String> userIds = friendRepository.listRequestToMe(userId);
		List<UserEls> userEls = elsRepository.searchByUserIds(userIds);
		return userEls;
	}
	
	public List<UserEls> listWaitResponse() {
		String userId = RequestContext.getUserId();
		List<String> userIds = friendRepository.listWaitResponse(userId);
		List<UserEls> userEls = elsRepository.searchByUserIds(userIds);
		return userEls;
	}
}
