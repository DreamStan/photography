package com.photograph.dao;

import com.photograph.pojo.ImgsUri;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Eminem on 2018/1/31.
 */
@Repository
public interface ImgsUriDao {

//    @Insert("INSERT INTO imgs_uri(urid,imgurl, imgname) VALUES(#{imgsUri.urid},#{imgsUri.imgUrl},#{imgsUri.imgName})")
    @InsertProvider(type = ImgsUriDaoProvider.class,method = "insertImgs")
    int addImgs(@Param("list") List<ImgsUri> imgsUris);

    @Select("SELECT * FROM imgs_uri")
    List<ImgsUri> findByImgs();

    @Select("SELECT * FROM imgs_uri iu WHERE iu.urid = #{urid}")
    List<ImgsUri> findByUrid(Integer urid);
}
