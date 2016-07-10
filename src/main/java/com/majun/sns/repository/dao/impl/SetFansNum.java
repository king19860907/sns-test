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
 * 设置粉丝数量
 * Created by majun on 2016/7/10.
 */
public class SetFansNum implements AfterProcessor {

    private MongoTemplate memberMongoTemplate;

    private RedisTemplate followRedisTemplate;

    public void execute(ProcessParam param) {
        Long fansNum = followRedisTemplate.boundZSetOps(FollowDao.fans_key+param.getToMemberId()).size();

        Update update = new Update();
        update.set("fansNum",fansNum);
        memberMongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(param.getToMemberId())), update,Member.class);
    }

    public void setMemberMongoTemplate(MongoTemplate memberMongoTemplate) {
        this.memberMongoTemplate = memberMongoTemplate;
    }

    public void setFollowRedisTemplate(RedisTemplate followRedisTemplate) {
        this.followRedisTemplate = followRedisTemplate;
    }
}
