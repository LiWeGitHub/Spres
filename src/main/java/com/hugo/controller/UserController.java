package com.hugo.controller;

import com.hugo.dao.UserMapper;
import com.hugo.entity.User;
import com.hugo.service.UserRedisService;
import com.hugo.service.UserService;
import com.hugo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRedisService userRedisService;
//    @Autowired
//    private RedisTemplate<String,Object> redisTemplate;

    @RequestMapping("/index")
    public String index(@RequestParam String id) {
        userRedisService.delete();
        UserVO userVO = this.userService.findById(Long.parseLong(id));
        userRedisService.put("LW", userVO);

//        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
//        valueOperations.set("hello", user);
        return userRedisService.get("LW").toString();
    }
}
