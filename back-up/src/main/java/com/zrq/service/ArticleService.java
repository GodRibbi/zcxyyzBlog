package com.zrq.service;

import java.util.ArrayList;
import java.util.List;

import com.zrq.bean.Article;
import com.zrq.bean.ArticleTag;
import com.zrq.bean.ArticleType;
import com.zrq.mapper.ArticleMapper;
import com.zrq.mapper.ArticleTagMapper;
import com.zrq.mapper.ArticleTypeMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleTypeMapper articleTypeMapper;

    @Autowired
    private ArticleTagMapper articleTagMapper;

    public List<Article> getArticleTop(){
        return articleMapper.getArticleTop();
    }

    public List<Article> getArticleList(){
        return articleMapper.getArticleList();
    }

    public Article getArticleById(int id){
        articleMapper.LookingUp(id);
        return articleMapper.getArticleById(id);
    }

    public void LikesUp(int id) {
        articleMapper.LikesUp(id);
    }

    public List<ArticleType> getArticleTypeList(){
        return articleTypeMapper.getArticleTypeList();
    }

    public int addArticle(Article article) {
        article.setArticle_date(new java.sql.Date(new java.util.Date().getTime()));
        article.setArticle_url("http://39.107.117.218/BlogMarkdown/"+article.getArticle_title()+".md");
        articleMapper.addArticle(article);
        return articleMapper.returnArticleId(article);
    }

	public void addArticleTagById(List<String> tags,int id) {
        List<ArticleTag> articleTags=new ArrayList<>();
        for (String tag : tags) {
            ArticleTag aTag=new ArticleTag();
            aTag.setArticle_id(id);
            aTag.setArticle_tag_name(tag);
            articleTags.add(aTag);
        }
        articleTagMapper.addArticleTagById(articleTags);
	}
}
