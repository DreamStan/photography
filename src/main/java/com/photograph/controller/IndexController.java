package com.photograph.controller;

import com.alibaba.fastjson.JSON;
import com.photograph.pojo.Comment;
import com.photograph.pojo.UserRelease;
import com.photograph.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Eminem on 2018/1/21.
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private NewWorksService newWorksService;

    @Autowired
    private PageViewService pageViewService;

    @Autowired
    private DetailsService detailsService;

    @Autowired
    private CommentService commentService;

    //头像
    private String touxiang = null;

    //全局urid
    private String GlobalUrid = null;

    /**
     * 首页
     * @param request
     * @return
     */
    @GetMapping(value = "/")
    public String index(ModelMap modelMap, HttpServletRequest request){
        String pictureSrc = userService.picture(request.getRemoteUser());
        touxiang = pictureSrc;
        modelMap.addAttribute("touxiang",touxiang);
        List<UserRelease> userReleases = newWorksService.newWorkd();
        modelMap.addAttribute("userReleases",userReleases);
        return "/index";
    }

    /**
     * 登录页面
     * @return
     */
    @GetMapping(value = "/login")
    public String login(){
        return "/login";
    }

    @GetMapping(value = "/registerede")
    public String registered(){
        return "";
    }

    /**
     * 投稿页面
     * @return
     */
    @GetMapping(value = "/submission")
    public String submission(ModelMap model,HttpServletRequest request){
        model.addAttribute("touxiang",userService.picture(request.getRemoteUser()));
        return "/submission";
    }

    /**
     * 403错误页面
     * @return
     */
    @GetMapping(value = "/403")
    public String error403(){
        return "/error/403";
    }

    /**
     * 个人首页
     * @param request
     * @return
     */
    @GetMapping(value = "/homePage")
    public String homePage(ModelMap model,HttpServletRequest request){
        String username = request.getRemoteUser();
        String pictureSrc = userService.picture(username);
        model.addAttribute("touxiang",pictureSrc);
        model.addAttribute("username",username);
        return "/homePage";
    }

    /**
     * 编辑资料
     * @param request
     * @return
     */
    @GetMapping(value = "/editData")
    public String editData(ModelMap model,HttpServletRequest request){
//        String pictureSrc = userService.picture(request.getRemoteUser());
        model.addAttribute("touxiang",touxiang);
        return "/editData";
    }

    @GetMapping(value = "/pageView/{urid}")
    public String pageView(HttpSession session, @PathVariable("urid") Integer urid){
        int count = pageViewService.pageView(urid);
        session.setAttribute("pageView",count);
        session.setAttribute("urid",urid);
        return "/pageView";
    }

    /**
     * 作品详情页面
     * @param urid 通过urid查找数据库对应作品详情
     * @return
     */
    @GetMapping(value = "/details/{urid}")
    public String details(ModelMap model,HttpServletRequest request,@PathVariable("urid") Integer urid){
        int count = pageViewService.pageView(urid);
        List<UserRelease> userReleases = detailsService.findByWorks(urid);
        List<Comment> comments = commentService.findByUrid(urid);
        model.addAttribute("comments",comments);
        model.addAttribute("pageView",count);
        model.addAttribute("touxiang",userService.picture(request.getRemoteUser()));
        userReleases.forEach(userRelease -> {
            GlobalUrid = String.valueOf(userRelease.getId());
            model.addAttribute("urid",GlobalUrid);
            model.addAttribute("uname",userRelease.getUname());
            model.addAttribute("picture",userRelease.getPicture());
            model.addAttribute("protection",userRelease.getProtection());
            model.addAttribute("releasedate",userRelease.getReleasedate());
            model.addAttribute("contents",userRelease.getContents());
            model.addAttribute("imgurl",userRelease.getImgsUris());
            model.addAttribute("watt",userRelease.getWatt());
        });
        return "/details";
    }

    /**
     * 评论功能
     * @param request
     * @param content 评论内容
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/sendComment",produces = "text/json;charset=UTF-8")
    public String sendComment(HttpServletRequest request, String content){
        Comment comment = new Comment();
        Timestamp d = new Timestamp(System.currentTimeMillis());
        comment.setDate(String.valueOf(d));
        comment.setUrid(Integer.parseInt(GlobalUrid));
        comment.setContent(content);
        comment.setUname(request.getRemoteUser());
        Integer c = commentService.addCommentByUrid(comment);
        if (c>0){
            return JSON.toJSONString(comment);
        }
        return "";
    }
}