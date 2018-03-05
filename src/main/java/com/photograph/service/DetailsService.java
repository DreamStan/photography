package com.photograph.service;

import com.photograph.pojo.UserRelease;

import java.util.List;

/**
 * Created by Eminem on 2018/2/22.
 */
public interface DetailsService {

    List<UserRelease> findByWorks(Integer urid);

}
