package com.photograph.dao;

import com.photograph.pojo.UserRelease;
import com.photograph.pojo.UserTag;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Eminem on 2018/2/14.
 */
@Component
public interface UserTagDao {

    @InsertProvider(type = UserTagDaoProvider.class,method = "insertTag")
    Integer addTag(@Param("list") List<UserTag> userTags);

    @Select("SELECT u.tagname FROM u_tag u WHERE u.urid = #{urid}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "urid",column = "urid"),
            @Result(property = "tagname",column = "tagname"),
            @Result(property = "uname",column = "uname"),
    })
    List<UserTag> findTagAll(Integer urid);

}
