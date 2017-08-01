package com.tiexue.cms.manage.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tiexue.cms.base.config.CustomizedPropertyConfigurer;
import com.tiexue.cms.core.dto.CmsShiroSubject;


@Controller
@RequestMapping("home")
public class HomeController {

	/**
	 * 后台跳转地址
	 */
	@Value("back.adminPath")
	private String adminPath;
	@SuppressWarnings("unused")
	private String getAdminPath() {
		return (String) CustomizedPropertyConfigurer.getContextProperty(adminPath);
	}

	

	
	
	/**
	 * 程序入口
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="doLogin",method=RequestMethod.POST)
	public String doLogin(HttpServletRequest request,HttpServletResponse response){
		Subject subject=SecurityUtils.getSubject();
		String userName=request.getParameter("username");
		String password=request.getParameter("password");
		UsernamePasswordToken token=new UsernamePasswordToken(userName,password);
		token.setRememberMe(true);
		String error="";
		try {
			request.setAttribute("username", userName);
			request.setAttribute("password",password);
			subject.login(token);
			if(subject.isAuthenticated()){
				return "redirect:/home/homepage";
			}else {
				request.setAttribute("msg", "登录名或密码错误！");
				return "forward:/login.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("msg","登录名或密码错误");
			error = "错误：" + e.getMessage();
			return "forward:/login.jsp";
		}
	}
	
	
	@RequestMapping("homepage")
	public String homepage(HttpServletRequest request,HttpServletResponse response){
		CmsShiroSubject subject=CommonUtil.getCmsShiroSubject();
		if(subject!=null&&subject.getCmsAdmin()!=null){
			String name="";
			name=subject.getCmsAdmin().getName();	
			request.setAttribute("name", name);
			request.setAttribute("type", subject.getCmsAdmin().getType());
		}
		
		return "home/homepage";
	}
	
	/**
	 * 欢迎页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("welcome")
	public String welcome(HttpServletRequest request,HttpServletResponse response){
		return "home/welcome";
	}
}
