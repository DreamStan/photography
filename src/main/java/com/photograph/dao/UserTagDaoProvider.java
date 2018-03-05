package com.photograph.dao;

import com.photograph.pojo.UserTag;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by Eminem on 2018/2/16.
 */
public class UserTagDaoProvider {

    public String insertTag(Map<String,Object> map){
        List<UserTag> userTagList = (List<UserTag>) map.get("list");

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO u_tag");
        sb.append("(uname, tagname,urid)");
        sb.append("VALUES ");

        MessageFormat mf = new MessageFormat("(#'{'list[{0}].uname}," +
                "#'{'list[{0}].tagname},#'{'list[{0}].urid})");
        for (int i = 0; i < userTagList.size(); i++) {
            System.out.println("list:"+new Object[]{i});
            System.out.println("tagname:"+userTagList.get(i).getTagname());
            sb.append(mf.format(new Object[]{i}));
            if (i < userTagList.size() - 1){
                sb.append(",");
            }
        }
        return sb.toString();
    }
}