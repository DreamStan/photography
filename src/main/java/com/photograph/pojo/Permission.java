package com.photograph.pojo;

import java.io.Serializable;

/**
 * Created by Eminem on 2018/1/21.
 */
public class Permission implements Serializable {

    private int rid;

    private String rolename;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Permission() {
    }

    public Permission(int rid, String rolename) {
        this.rid = rid;
        this.rolename = rolename;
    }
}
