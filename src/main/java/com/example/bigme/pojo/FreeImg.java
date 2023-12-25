package com.example.bigme.pojo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;


public class FreeImg implements Serializable {




    public FreeImg() {
    }

    // /strategies
    private Strategy[] strategies;

    // /tokens
    private Object[]  tokens;

    //  /profile
    private String username;
    private String imgUrl;
    private String avatar;
    private String email;
    private Float capacity;
    private Float size;
    private String url;
    private Integer image_num;
    private Integer album_num;
    private String registered_ip;

    public FreeImg(String username, String imgUrl, String avatar, String email, Float capacity,
                   Float size, String url, Integer image_num, Integer album_num, String registered_ip) {
        this.username = username;
        this.imgUrl = imgUrl;
        this.avatar = avatar;
        this.email = email;
        this.capacity = capacity;
        this.size = size;
        this.url = url;
        this.image_num = image_num;
        this.album_num = album_num;
        this.registered_ip = registered_ip;
    }

    public FreeImg(Object[] tokens) {
        this.tokens = tokens;
    }

    public FreeImg(Strategy[] strategies) {
        this.strategies = strategies;
    }

    public Strategy[] getStrategies() {
        return strategies;
    }

    public void setStrategies(Strategy[] strategies) {
        this.strategies = strategies;
    }

    public Object[] getTokens() {
        return tokens;
    }

    public void setTokens(Object[] tokens) {
        this.tokens = tokens;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Float getCapacity() {
        return capacity;
    }

    public void setCapacity(Float capacity) {
        this.capacity = capacity;
    }

    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getImage_num() {
        return image_num;
    }

    public void setImage_num(Integer image_num) {
        this.image_num = image_num;
    }

    public Integer getAlbum_num() {
        return album_num;
    }

    public void setAlbum_num(Integer album_num) {
        this.album_num = album_num;
    }

    public String getRegistered_ip() {
        return registered_ip;
    }

    public void setRegistered_ip(String registered_ip) {
        this.registered_ip = registered_ip;
    }

    class Tokens {
        private String token;
        private String expired_at;
    }
class Strategy {
    private  Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Strategy(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
//    username	String	用户名
//    name	String	昵称
//    avatar	String	头像地址
//    email	String	邮箱地址
//    capacity	Float	总容量
//    size	Float	已使用容量
//    url	String	个人主页地址
//    image_num	Integer	图片数量
//    album_num	Integer	相册数量
//    registered_ip	String	注册 IP
//strategies	Object[]	策略数据
//    id	Integer	策略 ID
//    name	String	策略名称

}
