package com.thpt.user.token;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import com.thpt.common.utils.StringUtils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class TokenFilter extends OncePerRequestFilter{

    private static final String BEARER = "Bearer ";
    private static final String AUTHORIZATION = "Authorization";

    private final TokenUtils TOKEN_UTILS;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if(SecurityContextHolder.getContext().getAuthentication()!=null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            filterChain.doFilter(request, response);
            return;
        }
        String token =  getAccessToken(request);
        if(!TOKEN_UTILS.validToken(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        SecurityContextHolder.getContext().setAuthentication(TOKEN_UTILS.getAuthentication(token));
    }
    
    private String getAccessToken(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader(AUTHORIZATION);
        if (StringUtils.isText(authorization)) {
            return authorization;
        }
        return authorization.replaceFirst(BEARER, "");
    }
}
