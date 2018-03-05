package com.photograph.service;

import com.photograph.pojo.User;
import com.photograph.pojo.UserTag;

import java.util.List;

/**
 * Created by Eminem on 2018/1/3.
 */
public interface UserService {

    User checkLogin(String username);

    String picture(String username);

    Integer saveUser(User user);

    User findUserById(int id);


}
