package com.photograph.dao;

import com.photograph.pojo.UserRelease;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Eminem on 2018/1/31.
 */
@Component
public interface UserReleaseDao {

    @InsertProvider(type = UserReleaseDaoProvider.class,method = "dynamicRelease")
    @Options(useGeneratedKeys = true,keyProperty = "userRelease.id")
    int addRelease(@Param("userRelease") UserRelease userRelease);

}
