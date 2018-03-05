package com.photograph.service.impl;

import com.photograph.dao.ImgsUriDao;
import com.photograph.dao.UserReleaseDao;
import com.photograph.dao.UserTagDao;
import com.photograph.pojo.ImgsUri;
import com.photograph.pojo.UserRelease;
import com.photograph.pojo.UserTag;
import com.photograph.service.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Eminem on 2018/1/31.
 */
@Service
public class ReleaseServiceImpl implements ReleaseService {

    @Autowired
    private UserReleaseDao userReleaseDao;

    @Autowired
    private UserTagDao userTagDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ImgsUriDao imgsUriDao;

    @Override
    public int addRelease(UserRelease userRelease) {
        return userReleaseDao.addRelease(userRelease);
    }

    @Override
    public int addImgs(List<ImgsUri> imgsUris) {

        ValueOperations<String,List<ImgsUri>> listValueOperations =redisTemplate.opsForValue();


        return imgsUriDao.addImgs(imgsUris);
    }

    @Override
    public int addTag(List<UserTag> userTags) {
        return userTagDao.addTag(userTags);
    }
}
