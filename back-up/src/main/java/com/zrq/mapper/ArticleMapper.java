package com.zrq.mapper;

import java.util.List;

import com.zrq.bean.Article;
import com.zrq.bean.ArticleType;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import tk.mybatis.mapper.common.Mapper;

public interface ArticleMapper extends Mapper<Article> {

    @Select("select * from articles where article_istop=1 order by article_id desc")
    @Results({ 
        @Result(id = true,column = "article_id",property = "article_id"),
        @Result(
            column = "article_id",
            property = "article_tags",
            javaType = List.class,
            many = @Many(select = "com.zrq.mapper.ArticleTagMapper.selectById")
        )
    })
    public List<Article> getArticleTop();

    @Select("select * from articles where article_istop=0 order by article_id desc")
    @Results({ 
        @Result(id = true,column = "article_id",property = "article_id"),
        @Result(
            column = "article_id",
            property = "article_tags",
            javaType = List.class,
            many = @Many(select = "com.zrq.mapper.ArticleTagMapper.selectById")
        )
    })
    public List<Article> getArticleList();

    @Select("select * from articles where article_id=#{id}")
    @Results({ 
        @Result(id = true,column = "article_id",property = "article_id"),
        @Result(
            column = "article_type_id",
            property = "article_type",
            javaType = ArticleType.class,
            one = @One(select = "com.zrq.mapper.ArticleTypeMapper.selectById")
        ),
        @Result(
            column = "article_id",
            property = "article_tags",
            javaType = List.class,
            many = @Many(select = "com.zrq.mapper.ArticleTagMapper.selectById")
        )
    })
    public Article getArticleById(int id);

    @Update("update articles set article_likes = article_likes + 1 where article_id=#{id}")
    public void LikesUp(int id);

    @Update("update articles set article_looking = article_looking + 1 where article_id=#{id}")
    public void LookingUp(int id);

    @Insert("insert into articles "
        +"(article_type_id,article_url,article_date,article_istop,article_hat,article_title,article_detail,article_image,article_likes,article_looking)"
        +"VALUES"
        +"(#{article_type_id}, #{article_url}, #{article_date}, #{article_istop}, #{article_hat}, #{article_title}, #{article_detail}, #{article_image}, 0, 0)")
    public void addArticle(Article article);

    @Select("select article_id from articles where article_type_id=#{article_type_id} and article_url=#{article_url} and article_date=#{article_date} and  article_istop=#{article_istop} and  article_hat=#{article_hat} and  article_title=#{article_title} and  article_detail=#{article_detail} and  article_image=#{article_image}")
    public int returnArticleId(Article article);
}
