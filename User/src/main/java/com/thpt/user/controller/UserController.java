package com.thpt.user.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thpt.user.request.RegisterRequest;
import com.thpt.user.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
	
	private final UserService userService;

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest) {
        userService.register(registerRequest);
    }
    
    @PutMapping("{avatarId}/avatar")
    public void updateAvatar(@PathVariable("avatarId") String avatarId) {
    	
    }
    
}
