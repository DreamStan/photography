package com.photograph.dao;

import com.photograph.pojo.Permission;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * Created by Eminem on 2018/1/21.
 */
@Component
public interface PermissionDao {

    @Select("SELECT * FROM permission WHERE rid=#{rid}")
    Permission findByRoleId(int rid);

}
