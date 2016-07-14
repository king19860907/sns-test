package com.majun.sns.dto;

import java.util.List;

/**
 * Created by majun on 16/7/14.
 */
public class Result<T> {

    List<T> result;

    private long totalCount;

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "Result{" +
                "result=" + result +
                ", totalCount=" + totalCount +
                '}';
    }
}
