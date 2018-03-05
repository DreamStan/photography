package com.photograph.service.impl;

import com.photograph.dao.NewWorksDao;
import com.photograph.pojo.UserRelease;
import com.photograph.service.NewWorksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Eminem on 2018/2/18.
 */
@Service
public class NewWorksServiceImpl implements NewWorksService {

    @Autowired
    private NewWorksDao newWorksDao;

    @Override
    public List<UserRelease> newWorkd() {
        return newWorksDao.newWorkd();
    }
}
