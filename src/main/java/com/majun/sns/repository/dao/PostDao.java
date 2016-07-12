package com.majun.sns.repository.dao;

import com.majun.sns.model.Post;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by majun on 16/7/12.
 */
public class PostDao {

    private MongoTemplate postMongoTemplate;

    public void savePost(Post post){
        postMongoTemplate.save(post);
    }

    public void setPostMongoTemplate(MongoTemplate postMongoTemplate) {
        this.postMongoTemplate = postMongoTemplate;
    }
}
