package com.majun.sns.application;

import com.majun.sns.model.Member;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by majun on 2016/7/9.
 */
public interface SnsUserService {

    void saveMemberInfo(Member member);

    /**
     * 获取用户信息
     * @param loginMemberId  //当前登录的用户  如果为空或者和memberId相同则返回的用户信息中关注关系为NONE
     * @param memberId      //需要获取信息的用户id
     * @return
     */
    Member getMemberInfo(Long loginMemberId,Long memberId);

    Member getMemberInfo(Long memberId);

    /**
     * 批量获取用户信息
     * @param loginMemberId  //当前登录的用户  如果为空或者和memberId相同则返回的用户信息中关注关系为NONE
     * @param memberIds     //需要获取信息的用户列表
     * @return
     */
    Map<Long,Member> findMembers(Long loginMemberId,Collection<Long> memberIds);

    Map<Long,Member> findMembers(Collection<Long> memberIds);

    void follow(Long memberId,Long toMemberId);

    void unFollow(Long memberId,Long toMemberId);

    /**
     * 查询关注者列表
     * @param loginMemberId //当前登录的用户  如果为空或者和memberId相同则返回的用户信息中关注关系为NONE
     * @param memberId
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Member> getFollows(Long loginMemberId,Long memberId,long pageNum,long pageSize);

    List<Member> getFollows(Long memberId,long pageNum,long pageSize);

    /**
     * 查询粉丝列表
     * @param loginMemberId //当前登录的用户  如果为空或者和memberId相同则返回的用户信息中关注关系为NONE
     * @param memberId
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Member> getFans(Long loginMemberId,Long memberId,long pageNum,long pageSize);

    List<Member> getFans(Long memberId,long pageNum,long pageSize);


}
