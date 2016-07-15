package com.majun.sns.application;

import com.majun.sns.dto.Operation;
import com.majun.sns.dto.PostType;
import com.majun.sns.dto.Result;
import com.majun.sns.model.Comment;
import com.majun.sns.model.Member;
import com.majun.sns.model.Post;
import com.mongodb.Mongo;
import com.yesmynet.base.closure.ClosureUtils;
import com.yesmynet.base.closure.ClosureValue;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.Request;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by majun on 16/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/spring/spring-sns-test.xml"})
public class SnsPostServiceTest {

    @Resource(name="snsPostService")
    SnsPostService snsPostService;

    @Resource(name="snsPostService")
    SnsPostService snsPostService2;

    @Resource(name="snsUserService")
    SnsUserService snsUserService;

    @Resource(name="commentMongoTemplate")
    MongoTemplate commentMongoTemplate;

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

    @Test
    public void postBatch() throws InterruptedException {

        //1002 发布两条文章
        new Thread(){
            @Override
            public void run() {
                post(1002L,1,"article",snsPostService);
                post(1002L,2,"article",snsPostService);
            }
        }.start();


        //1003 发布1条文章 1条酒评
        new Thread(){
            @Override
            public void run() {
                post(1003L,1,"article",snsPostService2);
                post(1003L,1,"goods",snsPostService2);
            }
        }.start();


        //2000 发布5条文章 5条酒评
        new Thread(){
            @Override
            public void run() {
                for(int i =1;i<=5;i++){
                    post(2000L,i,"article",snsPostService);
                    post(2000L,i,"goods",snsPostService2);
                }
            }
        }.start();

        Thread.sleep(20000);

    }

    private void post(long memberId,int i,String type,SnsPostService service){

        Post post = new Post();
        post.setContent("content_"+memberId+"_"+i+Thread.currentThread().getName());
        post.setCover("cover");
        post.setCreateTime(new Date());
        post.setTitle("title_"+memberId+"_"+i);
        post.setType(type);
        post.setMemberId(memberId);

        Member member = snsUserService.getMemberInfo(post.getMemberId());
        post.setMember(member);

        System.out.println(service.post(post));

    }


    @Test
    public void queryFollowPosts(){

        List<Post> list = snsPostService.queryFollowPosts(1001L, PostType.article,1607131454154871007L, Operation.lt,1002);
        System.out.println(list);
        System.out.println(list.size());
    }

    @Test
    public void queryPosts(){
        List<Post> list = snsPostService.queryPost(2000L,PostType.article,1607131454155031011L,Operation.gt,5);
        System.out.println(list);
        System.out.println(list.size());
    }

    @Test
    public void comment(){

        snsPostService.comment(1001L,1607131454155101013L,null,"content2");

    }

    @Test
    public void reply(){
        snsPostService.comment(1002L,1607131454155101013L,new ObjectId("5786106677c840c2b437768b"),"content3");
    }


    @Test
    public void getComments(){
        Result<Comment> request = snsPostService.getComments(1607131454155101013L,3,2);
        System.out.println(request);
        System.out.println(request.getResult().get(0).getMember());
        System.out.println(request.getResult().get(0).getReplyComment());
        System.out.println(request.getResult().get(0).getReplyComment().getMember());
        System.out.println(snsPostService.getComments(1607131454155101013L,4,2));

    }

    @Test
    public void getPost(){
        System.out.println(snsPostService.getPost(1607131454155101013L));
    }

    @Test
    public void collect(){
        List<Post> list = snsPostService.queryPost(2000L,PostType.article,null,null,100);
        List<Long> postIds = ClosureUtils.getValue(list, new ClosureValue<Post, Long>() {
            public Long getValue(Post post) {
                return post.getPostId();
            }
        });
        for(Long postId : postIds){
            snsPostService.collect(1001L,postId);
        }

        list = snsPostService.queryPost(2000L,PostType.goods,null,null,100);
        postIds = ClosureUtils.getValue(list, new ClosureValue<Post, Long>() {
            public Long getValue(Post post) {
                return post.getPostId();
            }
        });
        for(Long postId : postIds){
            snsPostService.collect(1001L,postId);
        }

    }

    @Test
    public void unCollection(){

        snsPostService.unCollect(1001L, Arrays.asList("5788885e345621bd9b362875","57888f4f34562a77cd418abf","57889bad3456820fcd3b6c42"));

    }

    @Test
    public void test(){

        //System.out.println(commentMongoTemplate.findAll(Comment.class));;
        Query query = new BasicQuery("{memberId:1002}");
        System.out.println(commentMongoTemplate.find(query,Comment.class));
    }


}
