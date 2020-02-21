package com.foodsharetest.android.db.model;


import android.util.Log;

import androidx.annotation.NonNull;

import org.litepal.crud.LitePalSupport;
/**
LitePal巨坑之一！下面的属性remember本可以设计为boolean值，但是LitePal的boolean值update不了！int尝试也不行！所以只能用Integer代替。
可能如int和boolean这种基本类型可能都是设为不可更改（但是第一次set还是成功了呀。。。后面就失败了）
 **/
public class User extends LitePalSupport implements Comparable<User> {
    private long id;
    private String name;
    private String password;
    private Integer remember;
    private byte[] portrait;
    private String region;
    private String gender;
    private String brithday;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", remember=" + remember +
                ", portrait='" + portrait + '\'' +
                ", region='" + region + '\'' +
                ", gender='" + gender + '\'' +
                ", brithday='" + brithday + '\'' +
                '}';
    }

    //check是传入未MD5加密的
    public boolean checkPassword(String str){
//        if (remember.equals(0)) str = com.foodsharetest.android.util.MD5.md5(str);
        if (password.equals(str)) return true;
        else return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null) {
            User UserInfo = (User) o;
            return (getId()==UserInfo.getId());
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(@NonNull User User) {
        return this.getName().compareTo(User.getName());
    }

    public byte[] getPortrait() {
        return portrait;
    }

    public void setPortrait(byte[] portrait) {
        this.portrait = portrait;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBrithday() {
        return brithday;
    }

    public void setBrithday(String brithday) {
        this.brithday = brithday;
    }

    public Integer getRemember() {
        return remember;
    }

    public void setRemember(Integer remember) {
        this.remember = remember;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
