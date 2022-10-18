
package com.zrq.bean;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

@Data
@Table(name = "article_tag")
public class ArticleTag {

    @Id
    @KeySql(useGeneratedKeys = true)
    private int article_tag_id;

    private int article_id;

    private String article_tag_name;
}
