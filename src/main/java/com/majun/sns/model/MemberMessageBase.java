package com.majun.sns.model;

import java.io.Serializable;

/**
 * Created by majun on 2016/7/10.
 */
public class MemberMessageBase implements Serializable {

    private static final long serialVersionUID = 1823444194421380817L;

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
}
