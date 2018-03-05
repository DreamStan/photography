package com.photograph.dao;

import com.photograph.pojo.UserRelease;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * Created by Eminem on 2018/2/4.
 */
public class UserReleaseDaoProvider {

    /**
     * 动态SQL
     * @param param 接收实体类数据
     * @return 返回
     */
    public String dynamicRelease(Map<String,Object> param){
        UserRelease userRelease = (UserRelease) param.get("userRelease");
        //SQL对象 ibatis包
        SQL sql = new SQL();

        sql.INSERT_INTO("u_release");

        sql.VALUES("uname","#{userRelease.uname}");
        sql.VALUES("title","#{userRelease.title}");
        //判断contents是否有值
        if (userRelease.getContents()!=null){
            sql.VALUES("contents","#{userRelease.contents}");
        }
        sql.VALUES("watt","#{userRelease.watt}");
//        sql.VALUES("tag","#{userRelease.tag}");
        sql.VALUES("protection","#{userRelease.protection}");

        return sql.toString();
    }
}
