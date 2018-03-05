package com.photograph.service;

/**
 * Created by Eminem on 2018/2/19.
 */
public interface PageViewService {

    Integer pageView(int urid);

    Integer findByClickNum(int urid);

    Integer updateByClickNum(int clickNum,int urid);

}
