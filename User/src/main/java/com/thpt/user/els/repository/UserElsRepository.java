package com.thpt.user.els.repository;

import java.util.Map;

import org.elasticsearch.action.index.IndexRequest;
import org.springframework.stereotype.Repository;

import com.thpt.user.woker.ElsWorkerPool;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class UserElsRepository {

	private final ElsWorkerPool elsWorkerPool;
	
	public void insertUser(Map<String, Object> userData, String userId) {
		IndexRequest indexRequest = new IndexRequest(userId);
		indexRequest.source(userData);
		elsWorkerPool.push(indexRequest);
	}
}
