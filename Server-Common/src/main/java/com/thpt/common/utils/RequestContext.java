package com.thpt.common.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.thpt.common.utils.token.JwtData;

public class RequestContext {

	public static String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		JwtData jwtData = (JwtData) authentication.getPrincipal();
		return jwtData.getUserId();
	}
}
