package com.majun.sns.model;

import org.bson.types.ObjectId;

/**
 * Created by majun on 2016/7/10.
 */
public class MemberMessage {

    private MemberMessageBase memberMessageBase = new MemberMessageBase();

    private ObjectId id;

    private Long memberId;

    /**
     * 我发布的文章有了新的评论数
     */
    private long newCommentsNum;

    /**
     * 我发布的评论有了新的回复
     */
    private long newReplyNum;

    public long getNewCommentsNum() {
        return newCommentsNum;
    }

    public void setNewCommentsNum(long newCommentsNum) {
        this.newCommentsNum = newCommentsNum;
    }

    public long getNewReplyNum() {
        return newReplyNum;
    }

    public void setNewReplyNum(long newReplyNum) {
        this.newReplyNum = newReplyNum;
    }

    public void setMemberMessageBase(MemberMessageBase memberMessageBase) {
        this.memberMessageBase = memberMessageBase;
    }

    public long getNewArticleNum() {
        return memberMessageBase.getNewArticleNum();
    }


    public long getNewGoodsNum() {
        return memberMessageBase.getNewGoodsNum();
    }


    public long getNewFansNum() {
        return memberMessageBase.getNewFansNum();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
