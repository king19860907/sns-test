package com.majun.sns.repository.dao;

import com.majun.sns.dto.Operation;
import com.majun.sns.dto.PostType;
import com.majun.sns.model.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created by majun on 16/7/12.
 */
public class PostDao {

    private MongoTemplate postMongoTemplate;

    public void savePost(Post post){
        postMongoTemplate.save(post);
    }


    /**
     * 查询内容
     * @param memberIds
     * @param type      类型:酒评还是文章
     * @param postId    内容Id
     * @param postIdOperation 操作:大于postId 还是 小于postId
     * @return
     */
    public List<Post> findPosts(List<Long> memberIds, PostType type, Long postId ,Operation postIdOperation,int size){

        Criteria criteria = new Criteria();
        criteria.and("memberId").in(memberIds).and("type").is(type);
        if(postId != null && Operation.gt.equals(postIdOperation)){
            criteria.and("postId").gt(postId);
        }else if(postId != null && Operation.lt.equals(postIdOperation)){
            criteria.and("postId").lt(postId);
        }
        Query query = new Query();
        query.addCriteria(criteria).limit(size);
        query.with(new Sort(Sort.Direction.DESC,"postId"));
        return postMongoTemplate.find(query,Post.class);
    }

    public void setPostMongoTemplate(MongoTemplate postMongoTemplate) {
        this.postMongoTemplate = postMongoTemplate;
    }
}
