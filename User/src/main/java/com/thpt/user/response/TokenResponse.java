package com.thpt.user.response;

import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenResponse {

    private String jwt;
}
