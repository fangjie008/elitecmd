package com.tiexue.cms.wap.controller;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiexue.cms.core.cache.CacheKey;
import com.tiexue.cms.core.entity.CmsArticle;
import com.tiexue.cms.core.service.ICmsArticleService;
import com.tiexue.cms.wap.dto.ApiContent;
import com.tiexue.cms.wap.utils.ConvertApiContent;

@Controller
@RequestMapping("home")
public class HomeController {
	
	public static Logger logger=Logger.getLogger(HomeController.class);
	@Resource
	ICmsArticleService iCmsArticleSer;
	
	/**
	 * 首页入口
	 * @param request
	 * @return
	 */
	@RequestMapping("list")
	public String list(HttpServletRequest request){
		try {
			List<CmsArticle> articles= iCmsArticleSer.getMoreList("1", 0, 10);
			if(articles!=null&&!articles.isEmpty()){
				List<ApiContent> apiContent= ConvertApiContent.articleListToDto(articles);
				request.setAttribute("articles", apiContent);
			}
			
		} catch (Exception e) {
			logger.info("list exception:"+e);
		}
		
		return "home/list";
	}
	
	/**
	 * 内容
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("detail")
	public String detail(HttpServletRequest request,Integer id){
		try {
			CmsArticle cmsArticle= iCmsArticleSer.getNormalDetail(id);
			ApiContent apiContent=ConvertApiContent.articleToDto(cmsArticle);
			//浏览量为0时随机生成浏览量
			if(apiContent!=null&&apiContent.getViewcount()!=null&&apiContent.getViewcount()<=0){
				Random random=new Random();
				apiContent.setViewcount(random.nextInt(2000));
			}
			request.setAttribute("detail", apiContent);
		} catch (Exception e) {
			logger.info("detail exception:"+e);
		}
		return "home/detail";
	}

}
