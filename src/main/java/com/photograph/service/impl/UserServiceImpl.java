package com.photograph.service.impl;

import com.photograph.dao.UserDao;
import com.photograph.dao.UserTagDao;
import com.photograph.pojo.User;
import com.photograph.pojo.UserTag;
import com.photograph.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Eminem on 2018/1/3.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;


//    redis缓存
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public User checkLogin(String username) {
        return userDao.checkLogin(username);
    }

    @Override
    public String picture(String username) {
        return userDao.picture(username);
    }

    @Override
    public Integer saveUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public User findUserById(int id) {
        String key = "USER_"+id;

        ValueOperations<String, User> operations = redisTemplate.opsForValue();
//        System.out.println(operations.get(key).getUsername());

        //缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey){
            User u = operations.get(key);
//            LOGGER.info("UserServiceImpl.findUserById():从缓存中获取了用户>>"+u.toString());
            return u;
        }

        //从DB获取用户
        User user = userDao.findUserById(id);
        //redis保存对象需要实体类实现Serializable
        operations.set(key,user,10, TimeUnit.SECONDS);
//        LOGGER.info("UserServiceImpl.findUserById():用户插入缓存>>"+user.toString());
        return user;
    }
}
