package com.zrq.bean;

import java.sql.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

@Data
@Table(name = "comment_reply")
public class CommentReply {
    @Id
    @KeySql(useGeneratedKeys = true)
    private int comment_reply_id;

    private int comment_id;

    private int article_id;

    private int comment_main;

    private String comment_reply_content;

    private String comment_user_img;

    private String comment_user_name;

    private String comment_reply_to_user_name;
    
    private Date comment_date;

}
