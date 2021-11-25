package com.thpt.user.els.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.thpt.common.utils.ModelMapperUtils;
import com.thpt.user.domain.User;
import com.thpt.user.els.domain.UserEls;
import com.thpt.user.els.repository.UserElsRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserElsService {

	private final UserElsRepository userElsRepository;
	
	public void insertUser(User user) {
		UserEls userEls = ModelMapperUtils.toObject(user, UserEls.class);
		Map<String, Object> mapData = userElsToMap(userEls);
		String userId = user.getUserId();
		userElsRepository.insertUser(mapData, userId);
	}
	
	private Map<String,Object> userElsToMap(UserEls userEls){
		Map<String, Object> mapData = ModelMapperUtils.toObject(userEls, Map.class);
		return mapData;
	}
	
}
