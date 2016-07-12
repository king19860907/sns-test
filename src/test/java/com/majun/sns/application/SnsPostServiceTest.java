package com.majun.sns.application;

import com.majun.sns.model.Member;
import com.majun.sns.model.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by majun on 16/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/spring/spring-sns-test.xml"})
public class SnsPostServiceTest {

    @Resource(name="snsPostService")
    SnsPostService snsPostService;

    @Resource(name="snsUserService")
    SnsUserService snsUserService;

    @Test
    public void postArticle() throws InterruptedException {
        Post post = new Post();
        post.setContent("content1");
        post.setCover("cover1");
        post.setCreateTime(new Date());
        post.setTitle("title1");
        post.setType("article");
        post.setMemberId(2000L);

        Member member = snsUserService.getMemberInfo(post.getMemberId());
        post.setMember(member);

        System.out.println(snsPostService.post(post));
        Thread.sleep(10000);
    }

    @Test
    public void postGoods() throws InterruptedException {
        Post post = new Post();
        post.setContent("content1");
        post.setCover("cover1");
        post.setCreateTime(new Date());
        post.setTitle("title1");
        post.setType("goods");
        post.setMemberId(1002L);

        Member member = snsUserService.getMemberInfo(post.getMemberId());
        post.setMember(member);

        System.out.println(snsPostService.post(post));
        Thread.sleep(10000);
    }

}
