package com.zrq.mapper;

import java.util.List;

import com.zrq.bean.ArticleType;

import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;

public interface ArticleTypeMapper extends Mapper<ArticleType> {

    @Select("select * from article_type where article_type_id=#{id}")
    public ArticleType selectById(int id);

    @Select("select * from article_type")
    public List<ArticleType> getArticleTypeList();
}
