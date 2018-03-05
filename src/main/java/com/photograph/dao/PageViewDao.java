package com.photograph.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * Created by Eminem on 2018/2/19.
 */
@Component
public interface PageViewDao {

    @Select("SELECT ur.clicknum FROM u_release ur WHERE ur.id = #{urid}")
    Integer findByClickNum(int urid);

    @Update("UPDATE u_release ur SET ur.clicknum = #{clickNum} WHERE id = #{urid}")
    Integer updateByClickNum(@Param("clickNum") int clickNum,@Param("urid") int urid);

}
