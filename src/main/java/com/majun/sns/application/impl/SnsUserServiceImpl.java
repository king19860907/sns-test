package com.majun.sns.application.impl;

import com.majun.sns.application.SnsUserService;
import com.majun.sns.dto.FollowStatus;
import com.majun.sns.dto.ProcessParam;
import com.majun.sns.model.Member;
import com.majun.sns.repository.dao.AfterProcessor;
import com.majun.sns.repository.dao.FollowDao;
import com.majun.sns.repository.dao.MemberDao;
import com.yesmynet.base.closure.ClosureUtils;
import com.yesmynet.base.closure.ClosureValue;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by majun on 2016/7/10.
 */
public class SnsUserServiceImpl implements SnsUserService {

    private MemberDao memberDao;

    private FollowDao followDao;

    private AfterProcessor afterFollowProcessor;

    private AfterProcessor afterUnFollowProcessor;

    private AfterProcessor afterSaveMemberProcessor;

    public void saveMemberInfo(Member member) {

        memberDao.saveMember(member);

        ProcessParam param = new ProcessParam();
        param.setFromMemberId(member.getId());
        afterSaveMemberProcessor.execute(param);
    }

    public Member getMemberInfo(Long memberId) {
        return memberDao.getMember(memberId);
    }

    public void follow(Long memberId,Long toMemberid) {
        FollowStatus status = followDao.followStatus(memberId,toMemberid);
        if(status.equals(FollowStatus.NONE) || status.equals(FollowStatus.FANS)){
            followDao.follow(memberId,toMemberid);
            ProcessParam param = new ProcessParam();
            param.setFromMemberId(memberId);
            param.setToMemberId(toMemberid);
            afterFollowProcessor.execute(param);
        }

    }

    public void unFollow(Long memberId,Long toMemberId) {
        FollowStatus status = followDao.followStatus(memberId,toMemberId);
        if(status.equals(FollowStatus.EACH) || status.equals(FollowStatus.FOLLOW)) {
            followDao.unFollow(memberId, toMemberId);

            ProcessParam param = new ProcessParam();
            param.setFromMemberId(memberId);
            param.setToMemberId(toMemberId);
            afterUnFollowProcessor.execute(param);
        }
    }

    public List<Member> getFollows(Long memberId,long pageNum,long pageSize) {
        if (pageNum == 0) pageNum = 1;
        List<Long> ids = followDao.findFollows(memberId,pageNum,pageSize);
        if(!CollectionUtils.isEmpty(ids)){
            final Map<Long,Member> idMap = memberDao.findMembers(ids);
            return ClosureUtils.getValue(ids, new ClosureValue<Long,Member>(){
                public Member getValue(Long id) {
                    return idMap.get(id);
                }
            });
        }
        return Collections.EMPTY_LIST;
    }

    public List<Member> getFans(Long memberId,long pageNum,long pageSize) {
        if (pageNum == 0) pageNum = 1;
        List<Long> ids = followDao.findFans(memberId,pageNum,pageSize);
        if(!CollectionUtils.isEmpty(ids)){
            final Map<Long,Member> idMap = memberDao.findMembers(ids);
            return ClosureUtils.getValue(ids, new ClosureValue<Long,Member>(){
                public Member getValue(Long id) {
                    return idMap.get(id);
                }
            });
        }
        return Collections.EMPTY_LIST;
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void setFollowDao(FollowDao followDao) {
        this.followDao = followDao;
    }

    public void setAfterFollowProcessor(AfterProcessor afterFollowProcessor) {
        this.afterFollowProcessor = afterFollowProcessor;
    }

    public void setAfterUnFollowProcessor(AfterProcessor afterUnFollowProcessor) {
        this.afterUnFollowProcessor = afterUnFollowProcessor;
    }

    public void setAfterSaveMemberProcessor(AfterProcessor afterSaveMemberProcessor) {
        this.afterSaveMemberProcessor = afterSaveMemberProcessor;
    }
}
