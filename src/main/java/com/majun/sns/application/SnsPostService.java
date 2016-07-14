package com.majun.sns.application;

import com.majun.sns.dto.Operation;
import com.majun.sns.dto.PostType;
import com.majun.sns.dto.Result;
import com.majun.sns.model.Post;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by majun on 2016/7/9.
 */
public interface SnsPostService {

    /**
     * 查询某个用户关注人发布的内容
     * @param memberId
     * @param type      类型:酒评还是文章
     * @param postId    内容Id
     * @param postIdOperation 操作:大于postId 还是 小于postId
     * @param size
     * @return
     */
    List<Post> queryFollowPosts(Long memberId, PostType type, Long postId , Operation postIdOperation,int size);

    /**
     * 查询某个用户发布的内容
     * @param memberId
     * @param type      类型:酒评还是文章
     * @param postId    内容Id
     * @param postIdOperation 操作:大于postId 还是 小于postId
     * @param size
     * @return
     */
    List<Post> queryPost(Long memberId,PostType type, Long postId, Operation postIdOperation, int size);

    /**
     * 发表评论
     * @param memberId  用户id
     * @param postId    内容id
     * @param replyId   所回复的评论的id
     * @param content   内容
     */
    void comment(Long memberId,Long postId,ObjectId replyId,String content);

    Result getComments(Long postId,int pageNum,int pageSize);

    void getCollections();

    Long post(Post post);

    void getPost();

}
