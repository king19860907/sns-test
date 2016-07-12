package com.majun.sns.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;

/**
 * Created by majun on 2016/7/10.
 */
public class MemberMessage implements Serializable {

    private static final long serialVersionUID = 1823444194421380817L;

    private ObjectId id;

    private Long memberId;

    /**
     * 新文章数
     */
    private long newArticleNum;

    /**
     * 新酒评数
     */
    private long newGoodsNum;

    /**
     * 新粉丝数
     */
    private long newFansNum;

    /**
     * 我发布的文章有了新的评论数
     */
    @Transient
    private long newCommentsNum;

    /**
     * 我发布的评论有了新的回复
     */
    @Transient
    private long newReplyNum;

    public long getNewArticleNum() {
        return newArticleNum;
    }

    public void setNewArticleNum(long newArticleNum) {
        this.newArticleNum = newArticleNum;
    }

    public long getNewGoodsNum() {
        return newGoodsNum;
    }

    public void setNewGoodsNum(long newGoodsNum) {
        this.newGoodsNum = newGoodsNum;
    }

    public long getNewFansNum() {
        return newFansNum;
    }

    public void setNewFansNum(long newFansNum) {
        this.newFansNum = newFansNum;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public long getNewReplyNum() {
        return newReplyNum;
    }

    public void setNewReplyNum(long newReplyNum) {
        this.newReplyNum = newReplyNum;
    }

    public long getNewCommentsNum() {
        return newCommentsNum;
    }

    public void setNewCommentsNum(long newCommentsNum) {
        this.newCommentsNum = newCommentsNum;
    }
}
