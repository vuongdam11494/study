package com.thpt.user.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thpt.user.request.FriendShipResponseRequest;
import com.thpt.user.service.FriendService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("friend")
@AllArgsConstructor
public class FriendController {
	
	private final FriendService friendService;

	@PostMapping("/{userReceiverId}/send-request")
	public void sendRequest(@PathVariable("userReceiverId") String userReceiverId) {
		
	}
	
	@PutMapping("/{userReceiverId}/send-response")
	public void sendResponse(@PathVariable("userReceiverId") String userReceiverId,
			@RequestBody FriendShipResponseRequest friendShipResponseRequest) {
		
	}
	
	@PostMapping("/{userReceiverId}/send-block")
	public void sendBlock(@PathVariable("userReceiverId") String userReceiverId) {
		
	}
	
	@DeleteMapping("/{userId}/friend-relation")
	public void deleteFriendRelation(@PathVariable("userId") String userId) {
		
	}
	
	@GetMapping("/list-friend")
	public void listFriend() {
		
	}
	
	@GetMapping("/list-block")
	public void listBlock() {
		
	}
	
	@GetMapping("list-friend-request")
	public void listFriendRequest() {
		
	}
	
	@GetMapping("list-friend-wait-response")
	public void listFriendWaitResponse() {
		
	}
}
