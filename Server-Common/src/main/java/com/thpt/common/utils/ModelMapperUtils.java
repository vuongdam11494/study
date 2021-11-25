package com.thpt.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ModelMapperUtils {

	private static final ModelMapper MODEL_MAPPER = new ModelMapper();
	private static final ObjectMapper MAPPER = new ObjectMapper()
            .findAndRegisterModules()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);
	
    static {
        MODEL_MAPPER.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
    }
    
    public static <T> T toObject(Object obj, Class<T> type) {
        if (obj == null) {
            return null;
        }
        T t = null;
        try {
            t = MODEL_MAPPER.map(obj, type);
        } catch (Exception ex) {
        }
        return t;
    }
    
    public static <T> List<T> toListObject(List<Object> objs, Class<T> type) {
        if (objs == null || objs.isEmpty()) {
            return null;
        }
        List<T> result = new ArrayList();
        for (Object obj : objs) {
            T t = toObject(obj, type);
            result.add(t);
        }
        return result;
    }
    
    public static <T> T jsonToObject(String json, Class<T> type) {
    	if(json == null || !StringUtils.hasText(json)) {
    		return null;
    	}
    	T t = null;
    	try {
    		t = MAPPER.readValue(json, type);
		} catch (Exception e) {
		}
    	return t;
    }
}
