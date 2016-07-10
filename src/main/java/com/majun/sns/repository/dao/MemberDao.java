package com.majun.sns.repository.dao;

import com.majun.sns.model.Member;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by majun on 2016/7/9.
 */
public class MemberDao {

    private MongoTemplate userMongoTemplate;

    public void saveMember(Member member){
        userMongoTemplate.save(member);
    }

    public Member getMember(Long memberId){
        Criteria criteria = new Criteria();
        criteria.and("_id").is(memberId);
        Query query = new Query();
        query.addCriteria(criteria);
        return userMongoTemplate.findOne(query,Member.class);
    }

    public Map<Long,Member> findMembers(Collection<Long> ids){
        Criteria criteria = new Criteria();
        criteria.and("_id").in(ids);
        Query query = new Query();
        query.addCriteria(criteria);
        List<Member> list = userMongoTemplate.find(query,Member.class);
        if(!CollectionUtils.isEmpty(list)){
            Map<Long,Member> idMap = new HashMap<Long,Member>();
            for(Member member : list){
                idMap.put(member.getId(),member);
            }
            return idMap;
        }
        return Collections.EMPTY_MAP;
    }

    public void setUserMongoTemplate(MongoTemplate userMongoTemplate) {
        this.userMongoTemplate = userMongoTemplate;
    }


}
