package com.photograph.pojo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Eminem on 2018/1/31.
 */
public class UserRelease implements Serializable {

    private int id;
    private String uname;
    private String title;
    private String contents;
    private String watt;
    private String protection;
    private String picture;
    private Timestamp releasedate;
    private Integer clicknum;
    private List<ImgsUri> imgsUris;
    private List<UserTag> userTags;

    public Integer getClicknum() {
        return clicknum;
    }

    public void setClicknum(Integer clicknum) {
        this.clicknum = clicknum;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getReleasedate() {

        StringBuffer sb = new StringBuffer();
        sb.append(releasedate.getYear() + 1900 + "-");
        sb.append(releasedate.getMonth() + 1 + "-");
        sb.append(releasedate.getDate());

        return sb.toString();
    }

    public void setReleasedate(Timestamp releasedate) {
        this.releasedate = releasedate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getWatt() {
        return watt;
    }

    public void setWatt(String watt) {
        this.watt = watt;
    }

    public String getProtection() {
        return protection;
    }

    public void setProtection(String protection) {
        this.protection = protection;
    }

    public List<ImgsUri> getImgsUris() {
        return imgsUris;
    }

    public void setImgsUris(List<ImgsUri> imgsUris) {
        this.imgsUris = imgsUris;
    }

    public List<UserTag> getUserTags() {
        return userTags;
    }

    public void setUserTags(List<UserTag> userTags) {
        this.userTags = userTags;
    }
}
