package com.majun.sns.model;

import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jun_ma on 2016/7/15.
 */
public class Collection implements Serializable {
    private static final long serialVersionUID = 8648314711629140456L;

    private ObjectId id;

    private Long memberId;

    private Long postId;

    private String type;

    private Date createTime;

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

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
