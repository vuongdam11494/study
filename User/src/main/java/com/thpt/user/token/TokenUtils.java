package com.thpt.user.token;

import static com.thpt.user.utils.constant.KeyFields.UserKeyFields.ID;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.thpt.common.utils.DateTimeUtils;
import com.thpt.user.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class TokenUtils {

    @Value("${jwt.jwtSecret}")
    private String secret;
    @Value("${jwt.jwtExpirationMs}")
    private Long expiration;

    public String generateToken(User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ID, user.getUserId());
        return Jwts.builder().setClaims(map).signWith(SignatureAlgorithm.HS256, secret)
                .setExpiration(new Date(DateTimeUtils.currentDateTime() + expiration)).setIssuedAt(new Date())
                .compact();
    }

    public User parseToken(String token) {
        User data = new User();
        try {
            Claims claims = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
            String userId = claims.get(ID, String.class);

            data.setUserId(userId);
        } catch (Exception ex) {
            return null;
        }
        return data;
    }

    public Authentication getAuthentication(String token) {
        User jwtData = parseToken(token);
        return new UsernamePasswordAuthenticationToken(jwtData, token, Collections.emptyList());
    }

    public boolean validToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
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
        User user = new User();
        user.setUserId(id);
        return generateToken(user);
    }
}
