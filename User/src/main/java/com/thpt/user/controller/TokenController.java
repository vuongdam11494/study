package com.thpt.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.thpt.user.request.TokenRequest;
import com.thpt.user.response.TokenResponse;
import com.thpt.user.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class TokenController {
    
    private final UserService userService;

    @PostMapping("/token")
    public ResponseEntity<TokenResponse> token(@RequestBody TokenRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }
}
