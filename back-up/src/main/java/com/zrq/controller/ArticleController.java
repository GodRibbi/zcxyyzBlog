package com.zrq.controller;

import java.util.List;

import com.zrq.bean.Article;
import com.zrq.bean.ArticleType;
import com.zrq.service.ArticleService;

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
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ResponseBody
    @RequestMapping("/blog/ArticleController/getArticleById/{id}")
    public Article getArticleById(@PathVariable int id) {
        log.info("ID:" + id + '\t' + "获取文章成功！");
        return articleService.getArticleById(id);
    }

    @ResponseBody
    @RequestMapping("/blog/ArticleController/getArticleList")
    public List<Article> getArticleList() {
        log.info('\t' + "获取文章列表成功！");
        return articleService.getArticleList();
    }

    @ResponseBody
    @RequestMapping("/blog/ArticleController/getArticleTop")
    public List<Article> getArticleTop() {
        log.info('\t'+"获取置顶文章列表成功！");
        return articleService.getArticleTop();
    }

    @ResponseBody
    @RequestMapping("/blog/ArticleController/getArticleTypeList")
    public List<ArticleType> getArticleTypeList() {
        log.info('\t'+"获取文章类型成功！");
        return articleService.getArticleTypeList();
    }

    @ResponseBody
    @RequestMapping("/blog/ArticleController/LikesUp/{id}")
    public String LikesUp(@PathVariable int id) {
        articleService.LikesUp(id);
        log.info('\t'+"点赞成功！");
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/blog/ArticleController/addArticle", method = RequestMethod.POST)
    public int addArticle(@RequestBody Article article) {
        log.info('\t'+"添加文章成功！");
        return articleService.addArticle(article);
    }
    
    @ResponseBody
    @RequestMapping(value = "/blog/ArticleController/addArticleTag/{id}", method = RequestMethod.POST)
    public String addArticleTagById(@PathVariable int id,@RequestBody List<String> tags) {
        articleService.addArticleTagById(tags,id);
        log.info("ID:" + id + '\t' + "添加文章Tag成功！");
        return "success";
    }
}
