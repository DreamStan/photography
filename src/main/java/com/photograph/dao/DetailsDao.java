package com.photograph.dao;

import com.photograph.pojo.UserRelease;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Eminem on 2018/2/22.
 */
@Component
public interface DetailsDao {

    @Select("SELECT ur.*,u.picture FROM u_release ur INNER JOIN user u ON u.username = ur.uname WHERE ur.id = #{urid}")
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
    List<UserRelease> findByWorks(Integer urid);
}
