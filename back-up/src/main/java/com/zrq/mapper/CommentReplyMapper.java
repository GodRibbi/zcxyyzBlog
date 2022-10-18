package com.zrq.mapper;

import java.util.List;

import com.zrq.bean.CommentReply;

import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;

public interface CommentReplyMapper extends Mapper<CommentReply> {

    @Select("select * from comment_reply where comment_id=#{comment_id}")
    public List<CommentReply> getCommentReplyByCommentId(int comment_id);
}
