package com.majun.sns.application;

import com.majun.sns.model.Member;

import java.util.List;

/**
 * Created by majun on 2016/7/9.
 */
public interface SnsUserService {

    void saveMemberInfo(Member member);

    Member getMemberInfo(Long memberId);

    void follow(Long memberId,Long toMemberId);

    void unFollow(Long memberId,Long toMemberId);

    List<Member> getFollows(Long memberId,long pageNum,long pageSize);

    List<Member> getFans(Long memberId,long pageNum,long pageSize);

}
