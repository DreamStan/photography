package com.photograph.dao;

import com.photograph.pojo.UserRelease;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Eminem on 2018/2/18.
 */
@Component
public interface NewWorksDao {

    //查询最新
    /*@Select("SELECT iu.imgurl,ur.title,u.picture,ur.uname,ut.tagname,ur.releasedate " +
            "FROM u_release ur " +
            "INNER JOIN imgs_uri iu ON ur.id = iu.urid " +
            "INNER JOIN u_tag ut ON ur.id = ut.urid " +
            "INNER JOIN user u ON ur.uname = u.username")*/
    @Select("SELECT ur.*,u.picture FROM u_release ur INNER JOIN user u ON u.username = ur.uname")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "imgsUris",column = "id",javaType = List.class,
                    many = @Many(select = "com.photograph.dao.ImgsUriDao.findByUrid",fetchType = FetchType.LAZY)),
            @Result(property = "title",column = "title"),
            @Result(property = "picture",column = "picture"),
            @Result(property = "uname",column = "uname"),
            @Result(property = "userTags",column = "id",javaType = List.class,
                    many = @Many(select = "com.photograph.dao.UserTagDao.findTagAll",fetchType = FetchType.LAZY)),
            @Result(property = "releasedate",column = "releasedate"),
    })
    List<UserRelease> newWorkd();
}
