package com.zrq.service;

import java.util.List;

import com.zrq.bean.Comment;
import com.zrq.bean.CommentReply;
import com.zrq.mapper.CommentMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    public List<Comment> getArticleCommentByArticleId(int id) {
        return commentMapper.getArticleCommentByArticleId(id);
    }

    public List<Comment> getMainComment() {
        return commentMapper.getMainComment();
    }

    public void addArticleComment(Comment comment){
        comment.setComment_main(0);
        comment.setComment_date(new java.sql.Date(new java.util.Date().getTime()));
        commentMapper.addComment(comment);
    }

	public void addArticleCommentReply(CommentReply commentReply) {
        commentReply.setComment_main(0);
        commentReply.setComment_date(new java.sql.Date(new java.util.Date().getTime()));
        commentMapper.addCommentReply(commentReply);
	}

	public void addMainComment(Comment comment) {
        comment.setComment_main(1);
        comment.setComment_date(new java.sql.Date(new java.util.Date().getTime()));
        commentMapper.addComment(comment);
	}

	public void addMainCommentReply(CommentReply commentReply) {
        commentReply.setComment_main(1);
        commentReply.setComment_date(new java.sql.Date(new java.util.Date().getTime()));
        commentMapper.addCommentReply(commentReply);
	}

}
