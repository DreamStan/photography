package com.photograph.service.impl;

import com.photograph.dao.PageViewDao;
import com.photograph.service.PageViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * Created by Eminem on 2018/2/19.
 */
@Service
public class PageViewServiceImpl implements PageViewService {

    @Autowired
    private PageViewDao pageViewDao;

    @Autowired
    RedisTemplate redisTemplate;

    Integer maxCount = 5;

    public Integer pageView(int urid){
        String key = "pageView_"+urid;

        ValueOperations<String,String> operations = redisTemplate.opsForValue();

        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey){
            //存在key
            String getValue = operations.get(key);
            int clickNum = Integer.parseInt(getValue);
            clickNum += 1;

            //点击量超过maxCount添加到数据，清空redis
            if (clickNum > maxCount){
                updateByClickNum(clickNum,urid);
//                operations.set(key,String.valueOf(0));
                redisTemplate.delete(key);
            }else{
                operations.set(key,String.valueOf(clickNum));
            }
            return clickNum;
        }else{
            //不存在
            int clickNum = findByClickNum(urid);
            operations.set(key,String.valueOf(clickNum+1));
            return clickNum+1;
        }
    }

    @Override
    public Integer findByClickNum(int urid) {
        return pageViewDao.findByClickNum(urid);
    }

    @Override
    public Integer updateByClickNum(int clickNum, int urid) {
        return pageViewDao.updateByClickNum(clickNum,urid);
    }
}
