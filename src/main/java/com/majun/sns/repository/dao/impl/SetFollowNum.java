package com.majun.sns.repository.dao.impl;

import com.majun.sns.dto.ProcessParam;
import com.majun.sns.model.Member;
import com.majun.sns.repository.dao.AfterProcessor;
import com.majun.sns.repository.dao.FollowDao;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 设置关注人数量
 * Created by majun on 2016/7/10.
 */
public class SetFollowNum implements AfterProcessor {

    private MongoTemplate memberMongoTemplate;

    private RedisTemplate followRedisTemplate;

    public void execute(ProcessParam param) {

        Long followNum = followRedisTemplate.boundZSetOps(FollowDao.follows_key+param.getFromMemberId()).size();

        Update update = new Update();
        update.set("followNum",followNum);
        memberMongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(param.getFromMemberId())), update,Member.class);

    }

    public void setMemberMongoTemplate(MongoTemplate memberMongoTemplate) {
        this.memberMongoTemplate = memberMongoTemplate;
    }

    public void setFollowRedisTemplate(RedisTemplate followRedisTemplate) {
        this.followRedisTemplate = followRedisTemplate;
    }
}
