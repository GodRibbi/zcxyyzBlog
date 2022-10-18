package com.zrq.bean;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

@Data
@Table(name = "article_type")
public class ArticleType {
    @Id
    @KeySql(useGeneratedKeys = true)
    private int article_type_id;

    private String article_type_name;


}
