package com.photograph.service.impl;

import com.photograph.dao.DetailsDao;
import com.photograph.pojo.UserRelease;
import com.photograph.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Eminem on 2018/2/22.
 */
@Service
public class DetailsServiceImpl implements DetailsService {

    @Autowired
    private DetailsDao detailsDao;

    @Override
    public List<UserRelease> findByWorks(Integer urid) {
        return detailsDao.findByWorks(urid);
    }
}
