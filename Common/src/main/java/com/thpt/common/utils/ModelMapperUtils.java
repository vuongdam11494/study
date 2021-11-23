package com.thpt.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ModelMapperUtils {

	private static final ModelMapper MODEL_MAPPER = new ModelMapper();

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
}
