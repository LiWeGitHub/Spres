package com.hugo.service;

import com.hugo.entity.User;
import com.hugo.util.JacksonUtil;
import com.hugo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserRedisService {
    @Autowired
    private RedisTemplate<String, String> redisTemplates;

    private HashOperations<String, String, String> hashOperations;

    @PostConstruct
    public void init() {
        hashOperations = redisTemplates.opsForHash();
    }

    public void put(String key, UserVO userVO) {
        hashOperations.put("hello", key, JacksonUtil.seriazileAsString(userVO));
    }
    public UserVO get(String key){
        String json = hashOperations.get("hello",key);
        return JacksonUtil.deserializeAsObject(json,UserVO.class);
    }

    public void delete(){
        redisTemplates.delete("LW");
    }
}
