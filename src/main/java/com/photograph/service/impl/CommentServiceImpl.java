package com.photograph.service.impl;

import com.photograph.dao.CommentDao;
import com.photograph.pojo.Comment;
import com.photograph.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Eminem on 2018/2/23.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public List<Comment> findByUrid(int urid) {
        return commentDao.findByUrid(urid);
    }

    @Override
    public Integer addCommentByUrid(Comment comment) {
        return commentDao.addCommentByUrid(comment);
    }
}
