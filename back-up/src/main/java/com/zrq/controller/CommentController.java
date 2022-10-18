package com.zrq.controller;

import java.util.List;

import com.zrq.bean.Comment;
import com.zrq.bean.CommentReply;
import com.zrq.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping("/blog/CommentController/getArticleCommentByArticleId/{id}")
    public List<Comment> getArticleCommentByArticleId(@PathVariable int id) {
        log.info("ID:" + id + '\t' + "获取文章评论成功！");
        return commentService.getArticleCommentByArticleId(id);
    }

    @ResponseBody
    @RequestMapping("/blog/CommentController/getMainComment")
    public List<Comment> getMainComment() {
        log.info('\t' + "获取主页评论成功！");
        return commentService.getMainComment();
    }

    @ResponseBody
    @RequestMapping(value = "/blog/CommentController/addArticleComment", method = RequestMethod.POST)
    public String addArticleComment(@RequestBody Comment comment) {
        commentService.addArticleComment(comment);
        log.info(
            "添加评论成功"+"\n"+
            "Article_id:"+"\t"+comment.getArticle_id()+"\n"+
            "Comment_content:"+"\t"+comment.getComment_content()+"\n"+
            "Comment_user_img:"+"\t"+comment.getComment_user_img()+"\n"+
            "Comment_user_name:"+"\t"+comment.getComment_user_name());
        return "success!";
    }

    @ResponseBody
    @RequestMapping(value = "/blog/CommentController/addArticleCommentReply", method = RequestMethod.POST)
    public String addArticleCommentReply(@RequestBody CommentReply commentReply) {
        commentService.addArticleCommentReply(commentReply);
        log.info(
            "添加回复成功"+"\n"+
            "Article_id:"+"\t"+commentReply.getArticle_id()+"\n"+
            "Comment_reply_content:"+"\t"+commentReply.getComment_reply_content()+"\n"+
            "Comment_user_img:"+"\t"+commentReply.getComment_user_img()+"\n"+
            "Comment_user_name:"+"\t"+commentReply.getComment_user_name()+"\n"+
            "Comment_reply_to_user_name:"+"\t"+commentReply.getComment_reply_to_user_name()+"\n"+
            "Comment_id:"+"\t"+commentReply.getComment_id()
            );
        return "success!";
    }

    @ResponseBody
    @RequestMapping(value = "/blog/CommentController/addMainComment", method = RequestMethod.POST)
    public String addMainComment(@RequestBody Comment comment) {
        commentService.addMainComment(comment);
        log.info(
            "添加主页评论成功"+"\n"+
            "Comment_content:"+"\t"+comment.getComment_content()+"\n"+
            "Comment_user_img:"+"\t"+comment.getComment_user_img()+"\n"+
            "Comment_user_name:"+"\t"+comment.getComment_user_name());
        return "success!";
    }

    @ResponseBody
    @RequestMapping(value = "/blog/CommentController/addMainCommentReply", method = RequestMethod.POST)
    public String addMainCommentReply(@RequestBody CommentReply commentReply) {
        commentService.addMainCommentReply(commentReply);
        log.info(
            "添加主页回复成功"+"\n"+
            "Comment_reply_content:"+"\t"+commentReply.getComment_reply_content()+"\n"+
            "Comment_user_img:"+"\t"+commentReply.getComment_user_img()+"\n"+
            "Comment_user_name:"+"\t"+commentReply.getComment_user_name()+"\n"+
            "Comment_reply_to_user_name:"+"\t"+commentReply.getComment_reply_to_user_name()+"\n"+
            "Comment_id:"+"\t"+commentReply.getComment_id()
            );
        return "success!";
    }
}
