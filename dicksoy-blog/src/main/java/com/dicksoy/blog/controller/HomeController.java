package com.dicksoy.blog.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dicksoy.blog.base.BaseResult;
import com.dicksoy.blog.po.Aphorism;
import com.dicksoy.blog.po.Article;
import com.dicksoy.blog.po.User;
import com.dicksoy.blog.service.AphorismService;
import com.dicksoy.blog.service.ArticleService;

@Controller
public class HomeController {

	@Resource
	private ArticleService articleService;
	
	@Resource
	private AphorismService aphorismService;
	
	@GetMapping("/index")
	public String index(Model model) {
		List<Article> javaList = articleService.selectList(new EntityWrapper<Article>().eq("type", 1));
		List<Article> iosList = articleService.selectList(new EntityWrapper<Article>().eq("type", 2));
		model.addAttribute("javaArticles", javaList);
		model.addAttribute("iosArticles", iosList);
		return "index";
	}
	
	@GetMapping("/login")
    public String login(Model model) {
		Aphorism aphorism = aphorismService.selectOneRandom();
		model.addAttribute("aphorism", aphorism);
        return "login";
    }
	
	@PostMapping("/login")
	@ResponseBody
	public BaseResult login(HttpServletRequest request, User user) {
		if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
			return new BaseResult.Builder(false, 500).msg("用户名密码不能为空").build();
		}
		// 获取当前登录主体
		Subject subject = SecurityUtils.getSubject();
		// 将用户名密码封装到 UsernamePasswordToken
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
//		记住我的登录状态
//		token.setRememberMe(true);
		try {
            // 调用subject的login方法
			subject.login(token);
			return new BaseResult.Builder(true, 200).msg("登录成功").build();
        } catch (UnknownAccountException e) {	//用户名未知...  
        	token.clear();
        	return new BaseResult.Builder(false, 500).msg("用户名不存在").build();
		} catch (LockedAccountException e) {	//用户被锁定，例如管理员把某个用户禁用...  
            token.clear();
            return new BaseResult.Builder(false, 500).msg("用户已经被锁定不能登录，请与管理员联系").build();
        } catch (AuthenticationException e) {	//其他未指定异常  
            token.clear();
            return new BaseResult.Builder(false, 500).msg("其他未指定异常").build();
        }
	}
}

/**
 * shiro总抛出AuthenticationException该异常，检查doGetAuthenticationInfo方法中的SimpleAuthenticationInfo实例化操作是否正确
 */
