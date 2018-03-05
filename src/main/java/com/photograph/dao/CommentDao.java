package com.photograph.dao;

import com.photograph.pojo.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Eminem on 2018/2/23.
 */
@Component
public interface CommentDao {

    @Select("SELECT * FROM comment c WHERE c.urid = #{urid} ORDER BY id DESC")
    List<Comment> findByUrid(int urid);

    @Insert("INSERT INTO comment(urid, uname, content) VALUES (#{comment.urid},#{comment.uname},#{comment.content})")
    Integer addCommentByUrid(@Param("comment") Comment comment);
}