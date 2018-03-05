package com.photograph.service;

import com.photograph.pojo.Comment;

import java.util.List;

/**
 * Created by Eminem on 2018/2/23.
 */
public interface CommentService {

    List<Comment> findByUrid(int urid);

    Integer addCommentByUrid(Comment comment);
}
