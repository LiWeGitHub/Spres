package com.hugo.service;

import com.hugo.vo.UserVO;
import org.springframework.cache.annotation.CacheConfig;

@CacheConfig(cacheNames = "users")
public interface UserService {
    UserVO findById(Long id);
}
