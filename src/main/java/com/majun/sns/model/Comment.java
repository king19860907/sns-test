package com.majun.sns.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by majun on 16/7/13.
 */
public class Comment implements Serializable {


    private static final long serialVersionUID = 1669653650988670173L;

    @Id
    private ObjectId commentId;

    private Long postId;

    private String content;

    private Long memberId;

    @Transient
    private Member member;

    private Date createTime;

    private Comment replyComment;

    public ObjectId getCommentId() {
        return commentId;
    }

    public void setCommentId(ObjectId commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Comment getReplyComment() {
        return replyComment;
    }

    public void setReplyComment(Comment replyComment) {
        this.replyComment = replyComment;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "postId=" + postId +
                ", commentId=" + commentId +
                '}';
    }
}
