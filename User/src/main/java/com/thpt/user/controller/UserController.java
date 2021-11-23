package com.thpt.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thpt.user.request.RegisterRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest) {
        
    }
}
