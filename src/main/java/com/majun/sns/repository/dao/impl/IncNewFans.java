package com.majun.sns.repository.dao.impl;

import com.majun.sns.dto.ProcessParam;
import com.majun.sns.model.MemberMessage;
import com.majun.sns.repository.dao.AfterProcessor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * Created by majun on 2016/7/10.
 */
public class IncNewFans implements AfterProcessor {

    private MongoTemplate messageMongoTemplate;

    public void execute(ProcessParam param) {
        Update update = new Update();
        update.inc("newFansNum",1);
        update.inc("newGoodsNum",0);
        update.inc("newArticleNum",0);
        messageMongoTemplate.upsert(Query.query(Criteria.where("memberId").is(param.getToMemberId())),update, MemberMessage.class);
    }

    public void setMessageMongoTemplate(MongoTemplate messageMongoTemplate) {
        this.messageMongoTemplate = messageMongoTemplate;
    }
}
