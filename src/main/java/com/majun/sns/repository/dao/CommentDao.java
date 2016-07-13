package com.majun.sns.repository.dao;

import com.majun.sns.model.Comment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by majun on 16/7/13.
 */
public class CommentDao {

    private MongoTemplate commentMongoTemplate;

    public Comment getComment(ObjectId commentId){
        return commentMongoTemplate.findById(commentId,Comment.class);
    }

    public void save(Comment comment){
        commentMongoTemplate.save(comment);
    }

    public void setCommentMongoTemplate(MongoTemplate commentMongoTemplate) {
        this.commentMongoTemplate = commentMongoTemplate;
    }
}
