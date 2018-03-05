package com.photograph.dao;

import com.photograph.pojo.ImgsUri;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by Eminem on 2018/2/3.
 */
public class ImgsUriDaoProvider {

    public String insertImgs(Map<String,Object> map){
        List<ImgsUri> imgsUris = (List<ImgsUri>) map.get("list");

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO imgs_uri ");
        sb.append("(urid,uname,imgurl, imgname) ");
        sb.append("VALUES");

        MessageFormat mf = new MessageFormat("(#'{'list[{0}].urid},#'{'list[{0}].uname}," +
                "#'{'list[{0}].imgUrl},#'{'list[{0}].imgName})");
        for (int i = 0; i < imgsUris.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < imgsUris.size() - 1){
                sb.append(",");
            }
        }
        return sb.toString();
    }
}