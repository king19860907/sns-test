package com.majun.sns.model;

import com.majun.sns.dto.FollowStatus;

import java.io.Serializable;

/**
 * Created by majun on 2016/7/9.
 */
public class Member implements Serializable {

    private static final long serialVersionUID = -1887755930500765043L;

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像地址
     */
    private String headImg;

    /**
     * 认证类型 0.普通会员 1.认证会员
     */
    private long auth;

    /**
     * 粉丝数量
     */
    private long fansNum;

    /**
     * 关注数量
     */
    private long followNum;

    /**
     * 简介
     */
    private String description;

    /**
     * 酒评数量
     */
    private long goodsPostNum;

    /**
     * 文章数量
     */
    private long articlePostNum;

    /**
     * 和当前用户的关注关系
     */
    private FollowStatus followStatus = FollowStatus.NONE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public long getAuth() {
        return auth;
    }

    public void setAuth(long auth) {
        this.auth = auth;
    }

    public long getFansNum() {
        return fansNum;
    }

    public void setFansNum(long fansNum) {
        this.fansNum = fansNum;
    }

    public long getFollowNum() {
        return followNum;
    }

    public void setFollowNum(long followNum) {
        this.followNum = followNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public long getGoodsPostNum() {
        return goodsPostNum;
    }

    public void setGoodsPostNum(long goodsPostNum) {
        this.goodsPostNum = goodsPostNum;
    }

    public long getArticlePostNum() {
        return articlePostNum;
    }

    public void setArticlePostNum(long articlePostNum) {
        this.articlePostNum = articlePostNum;
    }

    public FollowStatus getFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(FollowStatus followStatus) {
        this.followStatus = followStatus;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", fansNum=" + fansNum +
                ", followNum=" + followNum +
                ", followStatus=" + followStatus +
                '}';
    }
}
