package com.thpt.user.service;

import static com.thpt.user.utils.constant.MessageResponse.UserMessage.PASS_INCORRECT;
import static com.thpt.user.utils.constant.MessageResponse.UserMessage.USERNAME_NOT_FOUND;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.thpt.common.utils.DateTimeUtils;
import com.thpt.common.utils.ModelMapperUtils;
import com.thpt.common.utils.RequestContext;
import com.thpt.common.utils.token.JwtData;
import com.thpt.common.utils.token.TokenUtils;
import com.thpt.user.domain.User;
import com.thpt.user.els.service.UserElsService;
import com.thpt.user.repository.UserRepository;
import com.thpt.user.request.AvatarRequest;
import com.thpt.user.request.RegisterRequest;
import com.thpt.user.request.TokenRequest;
import com.thpt.user.response.TokenResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserElsService userElsService;
    private final PasswordEncoder encoder;
    private final TokenUtils tokenUtils;

    public void register(RegisterRequest registerRequest) {
        User user = ModelMapperUtils.toObject(registerRequest, User.class);
        String encoderPass = encoder.encode(registerRequest.getPassword());
        user.setPassword(encoderPass);
        user.setRegisterTime(DateTimeUtils.currentDateTime());
        user.setLastLogIn(DateTimeUtils.currentDateTime());
        userRepository.insertUser(user);
        if (user.getId() != null) {
        	registerRequest.setId(user.getId());
            userElsService.insertUser(registerRequest);
        }
    }
    
    public void updateAvatarId(AvatarRequest avatarRequest) {
    	String userId = RequestContext.getUserId();
    	userRepository.updateAvatarId(userId, avatarId);
    	userElsService.updateUser(avatarRequest);
    }

    public TokenResponse login(TokenRequest tokenRequest) {
        User user = userRepository.findByUsername(tokenRequest.getUsername());
        if(user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, USERNAME_NOT_FOUND);
        }
        if (!encoder.matches(tokenRequest.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, PASS_INCORRECT);
        }
        TokenResponse tokenResponse = new TokenResponse();
        JwtData jwtData = new JwtData();
        jwtData.setUserId(user.getId());
        String jwtToken = tokenUtils.generateToken(jwtData);
        tokenResponse.setJwt(jwtToken);
        return tokenResponse;
    }
}
