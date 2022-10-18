package com.zrq.bean;

import java.sql.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

@Data
@Table(name = "comment")
public class Comment {
    @Id
    @KeySql(useGeneratedKeys = true)
    private int comment_id;

    private int article_id;

    private String comment_content;

    private String comment_user_img;

    private String comment_user_name;

    private int comment_main;

    private Date comment_date;

    private List<CommentReply> comment_reply_list;
}
