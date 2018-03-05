package com.photograph.pojo;

import java.io.Serializable;

public class UserTag implements Serializable {
  private int id;
  private int urid;
  private String uname;
  private String tagname;
  private UserRelease userRelease;

  public UserRelease getUserRelease() {
    return userRelease;
  }

  public void setUserRelease(UserRelease userRelease) {
    this.userRelease = userRelease;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUrid() {
    return urid;
  }

  public void setUrid(int urid) {
    this.urid = urid;
  }

  public String getUname() {
    return uname;
  }

  public void setUname(String uname) {
    this.uname = uname;
  }

  public String getTagname() {
    return tagname;
  }

  public void setTagname(String tagname) {
    this.tagname = tagname;
  }
}
