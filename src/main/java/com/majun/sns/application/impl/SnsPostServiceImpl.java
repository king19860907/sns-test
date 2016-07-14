package com.majun.sns.application.impl;

import com.majun.sns.application.SnsPostService;
import com.majun.sns.dto.Operation;
import com.majun.sns.dto.PostType;
import com.majun.sns.dto.ProcessParam;
import com.majun.sns.dto.Result;
import com.majun.sns.model.Comment;
import com.majun.sns.model.Member;
import com.majun.sns.model.Post;
import com.majun.sns.repository.dao.*;
import com.majun.sns.util.DateUtil;
import com.sun.corba.se.spi.orbutil.closure.Closure;
import com.yesmynet.base.closure.ClosureUtils;
import com.yesmynet.base.closure.ClosureValue;
import org.bson.types.ObjectId;
import org.omg.CORBA.Request;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by majun on 16/7/12.
 */
public class SnsPostServiceImpl implements SnsPostService {

    private PostDao postDao;

    private FollowDao followDao;

    private MemberDao memberDao;

    private CommentDao commentDao;

    private AfterProcessor afterPostProcessor;

    public AtomicInteger postIdCounter = new AtomicInteger(1000);

    public List<Post> queryFollowPosts(Long memberId, PostType type, Long postId , Operation postIdOperation,int size) {

        //查询所有的关注人
        List<Long> ids = followDao.findFollows(memberId,1,Integer.MAX_VALUE);

        //查询关注人发布的内容
        List<Post> posts = postDao.findPosts(ids,type,postId,postIdOperation,size);

        //封装用户信息
        final Map<Long,Member> memberMap= memberDao.findMembers(ids);
        posts = ClosureUtils.getValue(posts, new ClosureValue<Post, Post>() {
            public Post getValue(Post post) {
                post.setMember(memberMap.get(post.getMemberId()));
                return post;
            }
        });

        return posts;
    }

    public List<Post> queryPost(Long memberId, PostType type, Long postId, Operation postIdOperation, int size) {
        List<Post> posts = postDao.findPosts(Arrays.asList(memberId),type,postId,postIdOperation,size);

        //封装用户信息
        final Map<Long,Member> memberMap= memberDao.findMembers(Arrays.asList(memberId));
        posts = ClosureUtils.getValue(posts, new ClosureValue<Post, Post>() {
            public Post getValue(Post post) {
                post.setMember(memberMap.get(post.getMemberId()));
                return post;
            }
        });

        return posts;
    }

    public void comment(Long memberId,Long postId,ObjectId replyId,String content) {
        Comment comment = new Comment();
        comment.setMemberId(memberId);
        comment.setPostId(postId);
        comment.setContent(content);
        comment.setCreateTime(new Date());
        if(replyId != null){
            Comment replyComment = commentDao.getComment(replyId);
            comment.setReplyComment(replyComment);
        }
        commentDao.save(comment);
    }

    public Result<Comment> getComments(Long postId,int pageNum,int pageSize) {
        Result<Comment> result = commentDao.queryComments(postId,pageNum,pageSize);
        if(!CollectionUtils.isEmpty(result.getResult())){
            final List<Long> memberIds = new ArrayList<Long>(2*pageSize);
            ClosureUtils.getValue(result.getResult(), new ClosureValue<Comment, Long>() {
                public Long getValue(Comment comment) {
                    memberIds.add(comment.getMemberId());
                    if(comment.getReplyComment() != null){
                        memberIds.add(comment.getReplyComment().getMemberId());
                    }
                    return comment.getMemberId();
                }
            });
            final Map<Long,Member> memberMap = memberDao.findMembers(memberIds);
            ClosureUtils.getValue(result.getResult(), new ClosureValue<Comment, Comment>() {
                public Comment getValue(Comment comment) {
                    if(comment.getReplyComment() != null){
                        comment.getReplyComment().setMember(memberMap.get(comment.getReplyComment().getMemberId()));
                    }
                    comment.setMember(memberMap.get(comment.getMemberId()));
                    return comment;
                }
            });
        }

        return result;
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

    public Post getPost(Long postId) {
        Post post = postDao.getPostById(postId);
        post.setMember(memberDao.getMember(post.getMemberId()));
        return post;
    }

    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    public void setFollowDao(FollowDao followDao) {
        this.followDao = followDao;
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void setAfterPostProcessor(AfterProcessor afterPostProcessor) {
        this.afterPostProcessor = afterPostProcessor;
    }

    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }
}
