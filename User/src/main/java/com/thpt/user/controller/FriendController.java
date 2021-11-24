package com.thpt.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thpt.user.request.FriendShipRequest;

@RestController
@RequestMapping("friend")
public class FriendController {

	@PostMapping("/send-request")
	public void sendRequest(@RequestBody FriendShipRequest friendShipRequest) {
		
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
