package com.majun.sns.model;

import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by majun on 2016/7/10.
 */
public class Follow implements Serializable {

    private static final long serialVersionUID = -6414741630480173553L;

    private ObjectId id;

    private Long memberId;

    private Long toMemberId;

    private Date followTime;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getToMemberId() {
        return toMemberId;
    }

    public void setToMemberId(Long toMemberId) {
        this.toMemberId = toMemberId;
    }

    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Date followTime) {
        this.followTime = followTime;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
