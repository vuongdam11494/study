package com.thpt.user.els.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.mongodb.bulk.UpdateRequest;
import com.thpt.common.utils.ModelMapperUtils;
import com.thpt.common.utils.RequestContext;
import com.thpt.user.domain.User;
import com.thpt.user.els.domain.UserEls;
import com.thpt.user.els.repository.UserElsRepository;
import com.thpt.user.request.AvatarRequest;
import com.thpt.user.request.RegisterRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserElsService {

	private final UserElsRepository userElsRepository;
	
	public void insertUser(RegisterRequest registerRequest) {
		UserEls userEls = ModelMapperUtils.toObject(registerRequest, UserEls.class);
		Map<String, Object> mapData = userElsToMap(userEls);
		String userId = registerRequest.getId();
		userElsRepository.insertUser(mapData, userId);
	}
	
	public void updateUser(AvatarRequest avatarRequest) {
		String userId = RequestContext.getUserId();
		Map<String, Object> mapData = ModelMapperUtils.toObject(avatarRequest, Map.class);
		userElsRepository.updateUser(mapData, userId);
	}
	
	private Map<String,Object> userElsToMap(UserEls userEls){
		Map<String, Object> mapData = ModelMapperUtils.toObject(userEls, Map.class);
		return mapData;
	}
	
}
