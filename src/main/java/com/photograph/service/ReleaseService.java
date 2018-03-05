package com.photograph.service;

import com.photograph.pojo.ImgsUri;
import com.photograph.pojo.UserRelease;
import com.photograph.pojo.UserTag;

import java.util.List;

/**
 * Created by Eminem on 2018/1/31.
 */
public interface ReleaseService {

    int addRelease(UserRelease userRelease);

    int addImgs(List<ImgsUri> imgsUris);

    int addTag(List<UserTag> userTags);

}
