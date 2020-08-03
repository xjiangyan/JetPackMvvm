package com.huago.jetpackmvvm.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @author Administrator
 * @updateDes 2020/6/16
 */

@Entity
public class UserInfo {

    @Id(autoincrement = true)
    private Long userId;
    private String userName;
    private String pwd;
    private int sex;

    @Generated(hash = 1871004802)
    public UserInfo(Long userId, String userName, String pwd, int sex) {
        this.userId = userId;
        this.userName = userName;
        this.pwd = pwd;
        this.sex = sex;
    }

    @Generated(hash = 1279772520)
    public UserInfo() {
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getSex() {
        return this.sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }


}
