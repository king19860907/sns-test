package com.majun.sns.dto;

/**
 * Created by majun on 2016/7/10.
 */
public class ProcessParam {

    private Long fromMemberId;

    private Long toMemberId;

    public Long getFromMemberId() {
        return fromMemberId;
    }

    public void setFromMemberId(Long fromMemberId) {
        this.fromMemberId = fromMemberId;
    }

    public Long getToMemberId() {
        return toMemberId;
    }

    public void setToMemberId(Long toMemberId) {
        this.toMemberId = toMemberId;
    }
}
