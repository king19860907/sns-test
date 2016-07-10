package com.majun.sns.application;

import com.majun.sns.model.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by majun on 2016/7/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/spring/spring-sns-test.xml"})
public class SnsUserServiceTest {

    @Resource(name="snsUserService")
    SnsUserService snsUserService;

    @Test
    public void initUserData(){
        //生成1000个member
        for(int i=1001;i<=2000;i++){
            Member member = new Member();
            member.setId(new Integer(i).longValue());
            member.setNickName("majun"+i);
            member.setHeadImg("http://tva4.sinaimg.cn/crop.0.0.180.180.180/67ee2f02jw1e8qgp5bmzyj2050050aa8.jpg");
            snsUserService.saveMemberInfo(member);
        }

        //用户1001关注所有人
        for(int i=1002;i<2000;i++){
            snsUserService.follow(1001L,new Integer(i).longValue());
        }

        //所有人关注2000
        for(int i=1001;i<2000;i++){
            snsUserService.follow(new Integer(i).longValue(),2000L);
        }

    }

    @Test
    public void saveMember(){

        Member member = new Member();
        member.setId(1001L);
        member.setNickName("majun");
        member.setHeadImg("http://tva4.sinaimg.cn/crop.0.0.180.180.180/67ee2f02jw1e8qgp5bmzyj2050050aa8.jpg");

        snsUserService.saveMemberInfo(member);


        member = new Member();
        member.setId(1002L);
        member.setNickName("majun2");
        member.setHeadImg("http://tva4.sinaimg.cn/crop.0.0.180.180.180/67ee2f02jw1e8qgp5bmzyj2050050aa8.jpg");
        snsUserService.saveMemberInfo(member);
    }

    @Test
    public void getMember(){
        Member member = snsUserService.getMemberInfo(1001L);
        System.out.println(member);
    }

    @Test
    public void follow(){
        snsUserService.follow(1001L,1002L);
    }

    @Test
    public void unFollow(){
        snsUserService.unFollow(1001L,1002L);
    }

    @Test
    public void getFollows(){
        System.out.println(snsUserService.getFollows(1001L,2,10));
    }

    @Test
    public void getFans(){
        System.out.println(snsUserService.getFans(1002L,1,10));
        System.out.println(snsUserService.getFans(1002L,2,10));
        System.out.println(snsUserService.getFans(2000L,1,10));
        System.out.println(snsUserService.getFans(2000L,2,10));
    }

}
