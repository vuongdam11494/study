package com.thpt.user.els.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.IdsQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Repository;

import com.thpt.common.utils.ModelMapperUtils;
import com.thpt.user.els.domain.UserEls;
import com.thpt.user.woker.ElsWorkerPool;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class UserElsRepository {
	
	private final RestHighLevelClient client;
	
	public static final String INDEX_NAME = "users";

	private final ElsWorkerPool elsWorkerPool;
	
	public void insertUser(Map<String, Object> userData, String userId) {
		IndexRequest indexRequest = new IndexRequest(INDEX_NAME).id(userId);
		indexRequest.source(userData);
		elsWorkerPool.push(indexRequest);
	}
	
	public void updateUser(Map<String, Object> updateData, String userId) {
		UpdateRequest updateRequest = new UpdateRequest(INDEX_NAME, userId);
		updateRequest.doc(updateData);
		elsWorkerPool.push(updateRequest);
	}
	
	public List<UserEls> searchByUserIds(List<String> ids) {
		List<UserEls> userEls = new ArrayList<UserEls>();
		IdsQueryBuilder idsQueryBuilder = idsToQuery(ids);
		SearchSourceBuilder builder = new SearchSourceBuilder()
				.query(idsQueryBuilder);
		
		SearchRequest request = new SearchRequest(INDEX_NAME)
				.source(builder);
		try {
			SearchResponse response = client.search(request, RequestOptions.DEFAULT);
			for(SearchHit hit:response.getHits()) {
				UserEls user = ModelMapperUtils.jsonToObject(hit.getSourceAsString(), UserEls.class);
				if(user != null) {
					userEls.add(user);
				}
			}
		} catch (IOException e) {
		}
		return userEls;
	}
	
	private IdsQueryBuilder idsToQuery(List<String> ids) {
		IdsQueryBuilder idsQueryBuilder = new IdsQueryBuilder();
		ids.forEach(idsQueryBuilder::addIds);
		return idsQueryBuilder;
	}
}
