package com.dicksoy.blog.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dicksoy.blog.base.BaseResult;
import com.dicksoy.blog.po.Article;
import com.dicksoy.blog.service.ArticleService;

@Controller
@RequestMapping("/article")
public class ArticleController {

	@Resource
	private ArticleService articleService;
	
	@GetMapping("/queryArticleListByCondition")
	@ResponseBody
	public BaseResult queryArticleListByCondition(Article article) {
		JSONObject columnMap = (JSONObject) JSONObject.toJSON(article);
		List<Article> list = articleService.selectByMap(columnMap);
		return new BaseResult.Builder(true, 200).msg("成功").data(list).build();
	}
	
	@GetMapping("/queryById")
	public String queryById(Long id, Model model) {
		Article result = articleService.selectById(id);
		String theirName = "";
		if (result.getType() == 1)
			theirName = "Dicksoy";
		if (result.getType() == 2)
			theirName = "Danish";
		model.addAttribute("article", result);
		model.addAttribute("theirName", theirName);
		return "article";
	}
	
	@PostMapping("/publishArticle")
	public BaseResult publishArticle() {
		
		return new BaseResult.Builder(true, 200).msg("成功").data(null).build();
	}
}
