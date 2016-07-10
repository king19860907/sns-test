package com.majun.sns.repository.dao;

import com.majun.sns.model.Follow;
import com.yesmynet.base.closure.ClosureUtils;
import com.yesmynet.base.closure.ClosureValue;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by majun on 2016/7/10.
 */
public class FollowDao {

    private MongoTemplate followMongoTemplate;

    private RedisTemplate followRedisTemplate;

    public static String follows_key = "follow.";

    public static String fans_key = "fans.";

    public void  follow(Long memberId,Long toMemberId){
        Date nowTime = new Date();
        Criteria criteria = new Criteria();
        criteria.where("memberId").is(memberId);
        criteria.and("toMemberId").is(toMemberId);
        Query query = new Query();
        query.addCriteria(criteria);

        Follow follow = followMongoTemplate.findOne(query,Follow.class);
        if(follow == null){
            follow = new Follow();
            follow.setMemberId(memberId);
            follow.setToMemberId(toMemberId);
        }
        follow.setFollowTime(nowTime);
        followMongoTemplate.save(follow);

        followRedisTemplate.boundZSetOps(follows_key+memberId).add(String.valueOf(toMemberId),nowTime.getTime());
        followRedisTemplate.boundZSetOps(fans_key+toMemberId).add(String.valueOf(memberId),nowTime.getTime());
    }

    public void unFollow(Long memberId,Long toMemberId){
        Criteria criteria = new Criteria();
        criteria.where("memberId").is(memberId);
        criteria.and("toMemberId").is(toMemberId);
        Query query = new Query();
        query.addCriteria(criteria);
        followMongoTemplate.remove(query,Follow.class);

        followRedisTemplate.boundZSetOps(follows_key+memberId).remove(String.valueOf(toMemberId));
        followRedisTemplate.boundZSetOps(fans_key+toMemberId).remove(String.valueOf(memberId));

    }

    public List<Long> findFollows(Long memberId, long pageNum, long pageSize){
        Set<String> idsStr = followRedisTemplate.boundZSetOps(follows_key+memberId).reverseRange((pageNum-1)*pageSize,pageNum*pageSize-1);
        List<Long> ids = ClosureUtils.getValue(idsStr, new ClosureValue<String, Long>() {
            public Long getValue(String s) {
                return Long.parseLong(s);
            }
        });
        return  ids;
    }

    public List<Long> findFans(Long memberId,Long pageNum, long pageSize){
        Set<String> idsStr = followRedisTemplate.boundZSetOps(fans_key+memberId).reverseRange((pageNum-1)*pageSize,pageNum*pageSize-1);
        List<Long> ids = ClosureUtils.getValue(idsStr, new ClosureValue<String, Long>() {
            public Long getValue(String s) {
                return Long.parseLong(s);
            }
        });
        return  ids;
    }

    public void setFollowMongoTemplate(MongoTemplate followMongoTemplate) {
        this.followMongoTemplate = followMongoTemplate;
    }

    public void setFollowRedisTemplate(RedisTemplate followRedisTemplate) {
        this.followRedisTemplate = followRedisTemplate;
    }
}
