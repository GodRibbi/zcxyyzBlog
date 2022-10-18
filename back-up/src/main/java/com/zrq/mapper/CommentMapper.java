package com.zrq.mapper;

import java.util.List;

import com.zrq.bean.Comment;
import com.zrq.bean.CommentReply;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;

public interface CommentMapper extends Mapper<Comment> {

    @Select("select * from comment where article_id=#{article_id}")
    @Results({ 
        @Result(id = true,column = "comment_id",property = "comment_id"),
        @Result(
            column = "comment_id",
            property = "comment_reply_list",
            javaType = List.class,
            many = @Many(select = "com.zrq.mapper.CommentReplyMapper.getCommentReplyByCommentId")
        )
    })
    public List<Comment> getArticleCommentByArticleId(int article_id);

    @Select("select * from comment where comment_main=1")
    @Results({ 
        @Result(id = true,column = "comment_id",property = "comment_id"),
        @Result(
            column = "comment_id",
            property = "comment_reply_list",
            javaType = List.class,
            many = @Many(select = "com.zrq.mapper.CommentReplyMapper.getCommentReplyByCommentId")
        )
    })
    public List<Comment> getMainComment();

    @Insert("insert into comment "
        +"(article_id,comment_content,comment_user_img,comment_user_name,comment_main,comment_date)"
        +"VALUES"
        +"(#{article_id}, #{comment_content}, #{comment_user_img}, #{comment_user_name}, #{comment_main}, #{comment_date})")
    public void addComment(Comment comment);

    @Insert("insert into comment_reply "
        +"(comment_id,article_id,comment_reply_content,comment_user_img,comment_reply_to_user_name,comment_user_name,comment_main,comment_date) "
        +"VALUES"
        +"(#{comment_id},#{article_id}, #{comment_reply_content}, #{comment_user_img}, #{comment_reply_to_user_name}, #{comment_user_name}, #{comment_main}, #{comment_date})")
	public void addCommentReply(CommentReply commentReply);
}
