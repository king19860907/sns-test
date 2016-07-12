package com.majun.sns.application;

import com.majun.sns.model.Post;

/**
 * Created by majun on 2016/7/9.
 */
public interface SnsPostService {

    void getPosts();

    void getComments();

    void getCollections();

    Long post(Post post);

    void getPost();

}
