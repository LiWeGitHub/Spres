package com.hugo.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.SerializationException;

import java.util.ArrayList;
import java.util.List;

public class JacksonUtil {
    private static final String EMPTY_JSON = "{}";

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * java-object as json-string
     *
     * @param object
     * @return
     */
    public static String seriazileAsString(Object object) {
        if (object == null) {
            return EMPTY_JSON;
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception ex) {
            throw new SerializationException("Could not write JSON: " + ex.getMessage(), ex);
        }
    }

    /**
     * json-string to java-object
     *
     * @param str
     * @return
     */
    public static <T> T deserializeAsObject(String str, Class<T> clazz) {
        if (str == null || clazz == null) {
            return null;
        }
        try {
            return objectMapper.readValue(str, clazz);
        } catch (Exception e) {
            throw new SerializationException("Could not write JSON: " + e.getMessage(), e);
        }
    }

    public static <T> List<T> deserializeAsList(String str, Class<T> clazz){
        if (str == null || clazz == null) {
            return null;
        }
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, clazz);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (Exception e) {
            throw new SerializationException("Could not write JSON: " + e.getMessage(), e);
        }
    }
}
