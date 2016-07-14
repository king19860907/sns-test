package com.majun.sns.repository.dao;

import com.majun.sns.dto.Result;
import com.majun.sns.model.Comment;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Created by majun on 16/7/13.
 */
public class CommentDao {

    private MongoTemplate commentMongoTemplate;

    public Comment getComment(ObjectId commentId){
        return commentMongoTemplate.findById(commentId,Comment.class);
    }

    public Result<Comment> queryComments(Long postId,int pageNum,int pageSize){
        Result<Comment> result = new Result<Comment>();
        Query query = new BasicQuery("{postId:"+postId+"}");
        result.setTotalCount(commentMongoTemplate.find(query,Comment.class).size());

        query.skip((pageNum-1)*pageSize).limit(pageSize);
        query.with(new Sort(Sort.Direction.ASC,"createTime"));
        result.setResult(commentMongoTemplate.find(query,Comment.class));
        return result;
    }

    public void save(Comment comment){
        commentMongoTemplate.save(comment);
    }

    public void setCommentMongoTemplate(MongoTemplate commentMongoTemplate) {
        this.commentMongoTemplate = commentMongoTemplate;
    }
}
