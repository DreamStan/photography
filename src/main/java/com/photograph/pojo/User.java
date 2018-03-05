package com.photograph.pojo;

import java.io.Serializable;

/**
 * Created by Eminem on 2018/1/3.
 */
public class User implements Serializable {

    private int id;

    private String username;

    private String password;

    private int rid;

    private String picture;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public User(int id, String username, String password, int rid) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.rid = rid;
    }
}
