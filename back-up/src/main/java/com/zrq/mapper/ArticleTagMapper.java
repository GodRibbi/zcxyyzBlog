package com.zrq.mapper;

import java.util.List;

import com.zrq.bean.ArticleTag;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;

public interface ArticleTagMapper extends Mapper<ArticleTag> {

    @Select("select * from article_tag where article_id=#{id}")
    public List<ArticleTag> selectById(int id);

    @Insert({
        "<script>"+
        "insert into article_tag(article_id,article_tag_name) values "+
        "<foreach collection=\"articleTags\" index=\"index\" item=\"item\" separator=\",\">"+
        "(#{item.article_id}, #{item.article_tag_name})"+
        "</foreach>"+
        "</script>"
    })
	public void addArticleTagById(@Param("articleTags") List<ArticleTag> articleTags);
}
