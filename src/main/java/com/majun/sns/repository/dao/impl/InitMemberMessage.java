package com.majun.sns.repository.dao.impl;

import com.majun.sns.dto.ProcessParam;
import com.majun.sns.model.MemberMessage;
import com.majun.sns.repository.dao.AfterProcessor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;

/**
 * Created by majun on 16/7/13.
 */
public class InitMemberMessage implements AfterProcessor {

    private MongoTemplate messageMongoTemplate;

    public void execute(ProcessParam param) {

        if(param.getFromMemberId() != null){
            MemberMessage message = new MemberMessage();
            message.setMemberId(param.getFromMemberId());
            messageMongoTemplate.save(message);

        }

    }

    public void setMessageMongoTemplate(MongoTemplate messageMongoTemplate) {
        this.messageMongoTemplate = messageMongoTemplate;
    }
}
