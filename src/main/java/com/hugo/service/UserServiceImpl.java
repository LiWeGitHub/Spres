package com.hugo.service;

import com.hugo.dao.UserMapper;
import com.hugo.entity.User;
import com.hugo.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {
   private static final Logger logger =  LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
//    @Cacheable(key = "#id",value = "userCache")
    public UserVO findById(Long id) {
        logger.info("获取数据： service");
        UserVO userVO = this.convertToVO(this.userMapper.findById(id));
        logger.info("获取数据成功： service");
        return userVO ;
    }

    private UserVO convertToVO(User user) {
        UserVO userVO = null;
        if (user != null) {
            userVO = new UserVO();
            userVO.setId(user.getId());
            userVO.setName(user.getName());
        }
        return userVO;
    }

//    public static void main(String [] args){
//        StringBuffer sb = new StringBuffer();
//        System.out.print(sb.toString());
//    }
}
