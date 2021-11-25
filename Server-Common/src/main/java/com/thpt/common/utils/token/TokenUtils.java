package com.thpt.common.utils.token;

import static com.thpt.common.constant.KeyFields.UserKeyFields.ID;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.thpt.common.config.TokenConfig;
import com.thpt.common.utils.DateTimeUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
public class TokenUtils {

	private final TokenConfig tokenConfig;
	
	public String generateToken(JwtData jwtData) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(ID, jwtData.getUserId());
		return Jwts.builder().setClaims(map).signWith(SignatureAlgorithm.HS256, tokenConfig.getSecret())
		        .setExpiration(new Date(DateTimeUtils.currentDateTime() + tokenConfig.getExpiration())).setIssuedAt(new Date())
		        .compact();
	}

	public JwtData parseToken(String token) {
		JwtData data = new JwtData();
		try {
			Claims claims = Jwts.parser().setSigningKey(tokenConfig.getSecret().getBytes()).parseClaimsJws(token).getBody();
			String userId = claims.get(ID, String.class);

			data.setUserId(userId);
		} catch (Exception ex) {
			return null;
		}
		return data;
	}

	public Authentication getAuthentication(String token) {
		JwtData jwtData = parseToken(token);
		return new UsernamePasswordAuthenticationToken(jwtData, token);
	}

	public boolean validToken(String token) {
		try {
			Jwts.parser().setSigningKey(tokenConfig.getSecret()).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
//            logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
//            logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
//            logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
//            logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
//            logger.error("JWT claims string is empty: {}", e.getMessage());
		}
		return false;
	}

	public String genereteAuthenToken(String id) {
		JwtData jwtData = new JwtData();
		jwtData.setUserId(id);
		return generateToken(jwtData);
	}
}
