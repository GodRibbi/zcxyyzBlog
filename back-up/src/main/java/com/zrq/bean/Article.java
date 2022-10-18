package com.zrq.bean;

import java.sql.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

@Data
@Table(name = "articles")
public class Article {
    @Id
    @KeySql(useGeneratedKeys = true)
    private int article_id;

    private String article_url;

    private int article_looking;

    private int article_likes;

    private Date article_date;

    private int article_istop;

    private String article_hat;

    private String article_title;

    private String article_detail;

    private String article_image;

    private ArticleType article_type;

    private int article_type_id;

    private List<ArticleTag> article_tags;
}
