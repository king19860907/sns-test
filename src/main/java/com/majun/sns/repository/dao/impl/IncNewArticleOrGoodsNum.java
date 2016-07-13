package com.majun.sns.repository.dao.impl;

import com.majun.sns.dto.PostType;
import com.majun.sns.dto.ProcessParam;
import com.majun.sns.model.Follow;
import com.majun.sns.model.Member;
import com.majun.sns.model.MemberMessage;
import com.majun.sns.repository.dao.AfterProcessor;
import com.majun.sns.repository.dao.FollowDao;
import com.mongodb.Mongo;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by majun on 16/7/12.
 */
public class IncNewArticleOrGoodsNum implements AfterProcessor {

    private MongoTemplate messageMongoTemplate;

    private FollowDao followDao;

    public void execute(final ProcessParam param) {

        new Thread(){
            @Override
            public void run() {
                if(param.getPost() != null){
                    Update update = new Update();
                    if(param.getPost().getType().equals(PostType.goods.toString())){
                        update.inc("newGoodsNum",1);
                    }else if (param.getPost().getType().equals(PostType.article.toString())){
                        update.inc("newArticleNum",1);
                    }

                    long i = 1;
                    int pageSize = 1000;
                    while(true){
                        List<Long> ids = followDao.findFans(param.getPost().getMemberId(),i,pageSize);
                        if(!CollectionUtils.isEmpty(ids)){
                            messageMongoTemplate.updateMulti(Query.query(Criteria.where("memberId").in(ids)),update,MemberMessage.class);
                        }
                        if(CollectionUtils.isEmpty(ids) || ids.size() < pageSize){
                            break;
                        }
                    }
                }
            }
        }.start();

    }

    public void setMessageMongoTemplate(MongoTemplate messageMongoTemplate) {
        this.messageMongoTemplate = messageMongoTemplate;
    }

    public void setFollowDao(FollowDao followDao) {
        this.followDao = followDao;
    }
}
