package com.foodsharetest.android.db;

import android.app.Application;
import android.util.Log;

import androidx.annotation.Nullable;

import com.foodsharetest.android.db.model.User;

import org.litepal.LitePal;

import java.util.List;

//使用饿汉模式实现单例的登录用户信息记录
//但是此处是存储用户信息的副本，这点是否合适有待考虑，可以考虑使用User类
//LoginUser相对于模拟登陆，并且作为存储数据库的一个缓冲区
public class LoginUser extends Application {
    private static LoginUser login_user = new LoginUser();
    private static User _user;
    private int id;
    private String name;
    private byte[] portrait;
    private String region;
    private String gender;
    private String brithday;


    public static LoginUser getInstance(){
        return login_user;
    }

    public User getUser(){
        return _user;
    }

    //保存至数据库
    public void update(){
        if(_user.getId()==this.id){
            _user.setName(this.name);
            _user.setPortrait(this.portrait);
            _user.setGender(this.gender);
            _user.setRegion(this.region);
            _user.setBrithday(this.brithday);
            _user.update(_user.getId());
        }
    }

    //重新init
    public void reinit(){
        login_user.id = _user.getId();
        login_user.name = _user.getName();
        login_user.portrait = _user.getPortrait();
        login_user.region = _user.getRegion();
        login_user.gender = _user.getGender();
        login_user.brithday = _user.getBrithday();
    }

    public boolean login(User user) {
        _user = user;
        login_user.id = user.getId();
        login_user.name = user.getName();
        login_user.portrait = user.getPortrait();
        login_user.region = user.getRegion();
        login_user.gender = user.getGender();
        login_user.brithday = user.getBrithday();
        return true;
    }

    public static boolean logout(){
        if(login_user.id == -1) return false;
        login_user.id = -1;
        login_user.name = null;
        login_user.portrait = null;
        login_user.region = null;
        login_user.gender = null;
        login_user.brithday = null;
        return true;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", portrait ='" + portrait + '\'' +
                ", region='" + region + '\'' +
                ", gender='" + gender + '\'' +
                ", brithday='" + brithday + '\'' +
                '}';
    }

    public int getId() {
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
}
