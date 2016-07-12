package com.majun.sns.repository.dao.impl;

import com.majun.sns.dto.PostType;
import com.majun.sns.dto.ProcessParam;
import com.majun.sns.model.Member;
import com.majun.sns.model.Post;
import com.majun.sns.repository.dao.AfterProcessor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.io.Serializable;


/**
 * 设置用户发表文章或酒评数量
 * Created by majun on 16/7/12.
 */
public class SetMemberArticleOrGoodsPostNum implements AfterProcessor {

    private MongoTemplate memberMongoTemplate;

    private MongoTemplate postMongoTemplate;

    public void execute(ProcessParam param) {

        if(param.getPost() != null){
            long count = postMongoTemplate.count(
                    Query.query(Criteria.where("memberId").is(param.getPost().getMemberId()).and("type").is(param.getPost().getType())),
                    Post.class);

            Query query = Query.query(Criteria.where("_id").is(param.getPost().getMemberId()));
            if(param.getPost().getType().equals(PostType.goods.toString())){
                memberMongoTemplate.updateFirst(query, Update.update("goodsPostNum",count),Member.class);
            }else if (param.getPost().getType().equals(PostType.article.toString())){
                memberMongoTemplate.updateFirst(query, Update.update("articlePostNum",count),Member.class);
            }
        }

    }

    public void setPostMongoTemplate(MongoTemplate postMongoTemplate) {
        this.postMongoTemplate = postMongoTemplate;
    }

    public void setMemberMongoTemplate(MongoTemplate memberMongoTemplate) {
        this.memberMongoTemplate = memberMongoTemplate;
    }
}
