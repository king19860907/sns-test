package com.majun.sns.application.impl;

import com.majun.sns.application.SnsPostService;
import com.majun.sns.dto.ProcessParam;
import com.majun.sns.model.Post;
import com.majun.sns.repository.dao.AfterProcessor;
import com.majun.sns.repository.dao.PostDao;
import com.majun.sns.util.DateUtil;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by majun on 16/7/12.
 */
public class SnsPostServiceImpl implements SnsPostService {

    private PostDao postDao;

    private AfterProcessor afterPostProcessor;

    public AtomicInteger postIdCounter = new AtomicInteger(1000);

    public void getPosts() {

    }

    public void getComments() {

    }

    public void getCollections() {

    }

    public Long post(Post post) {
        Long postId = generatePostId();
        post.setPostId(postId);
        postDao.savePost(post);

        ProcessParam param = new ProcessParam();
        param.setPost(post);
        afterPostProcessor.execute(param);

        return postId;
    }

    private Long generatePostId(){

        int postfix = postIdCounter.incrementAndGet();
        if(postfix > 9999){
            postIdCounter.set(1000);
            postfix = postIdCounter.incrementAndGet();
        }
        String postIdStr = DateUtil.getDateFormate(new Date(),"yyMMddHHmmssSSS") + postfix;

        return Long.parseLong(postIdStr);
    }

    public void getPost() {

    }

    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    public void setAfterPostProcessor(AfterProcessor afterPostProcessor) {
        this.afterPostProcessor = afterPostProcessor;
    }
}
