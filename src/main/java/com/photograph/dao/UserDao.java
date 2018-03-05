package com.photograph.dao;

import com.photograph.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * Created by Eminem on 2018/1/3.
 */
@Component
public interface UserDao {

    //登录
    @Select("SELECT * FROM user u WHERE username=#{username}")
    User checkLogin(@Param("username") String username);

    //查询头像
    @Select("SELECT u.picture FROM user u WHERE username=#{username}")
    String picture(String username);

    //添加
    @Insert("INSERT INTO photograph.user(name, password) VALUES (#{user.name},#{user.password})")
    Integer saveUser(@Param("user") User user);

    //查询
    @Select("SELECT * FROM photograph.user WHERE id=#{id}")
    User findUserById(@Param("id") int id);

}
