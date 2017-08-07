package com.tiexue.cms.wap.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.executor.ReuseExecutor;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.tiexue.cms.base.util.DateUtil;
import com.tiexue.cms.core.entity.CmsArticle;
import com.tiexue.cms.wap.dto.ApiContent;

public class ConvertApiContent {
	
	private static final Logger logger=Logger.getLogger(ConvertApiContent.class);
	/**
	 * 内容转化成前端直接用的内容
	 * @param articles
	 * @return
	 */
	public static List<ApiContent> articleListToDto(List<CmsArticle> articles){
		List<ApiContent> articleDtos=new ArrayList<ApiContent>();
		if(articles==null||articles.size()<=0)
			return articleDtos;
		for (CmsArticle article : articles) {
			articleDtos.add(articleToDto(article));
		}
		return articleDtos;
	}
	
	/**
	 * 内容转化成前端直接用的内容
	 * @param article
	 * @return
	 */
	public static ApiContent articleToDto(CmsArticle article){
		if(article==null)
			return null;
		ApiContent articleDto=new ApiContent();
		articleDto.setId(article.getId());
		articleDto.setCaicount(article.getCaicount()==null?0:article.getCaicount());
		articleDto.setCategoryid(article.getCategoryid()==null?0:article.getCategoryid());
		articleDto.setContentlen(article.getOriginalContent()==null?0:article.getOriginalContent().length());
		articleDto.setContenttype(article.getContenttype()==null?0:article.getContenttype());
		articleDto.setCoverimgs(article.getCoverimgs()==null?"[]":article.getCoverimgs());		
		articleDto.setDingcount(article.getDingcount()==null?0:article.getDingcount());
		articleDto.setFromname(article.getFromname()==null?"":article.getFromname());
		articleDto.setImgshowtype(article.getImgshowtype()==null?0:article.getImgshowtype());
		articleDto.setIntro(article.getIntro()==null?"":article.getIntro());
		articleDto.setOriginalurl(article.getOriginalurl()==null?"":article.getOriginalurl());
		articleDto.setOriginalcontent(article.getOriginalContent()==null?"":article.getOriginalContent());
		articleDto.setStatus(article.getStatus()==null?0:article.getStatus());
		articleDto.setTags(article.getTags()==null?"[]":article.getTags());
		articleDto.setTitle(article.getTitle()==null?"":article.getTitle());
		articleDto.setViewcount(article.getViewcount()==null?0:article.getViewcount());
		articleDto.setContentpic(article.getContentPic()==null?"[]":article.getContentPic());
		articleDto.setMaterials(article.getMaterials()==null?"{}":article.getMaterials());	
		articleDto.setShowtime(DateUtil.date2Str(article.getShowtime(), "yyyy-MM-dd HH:mm:ss"));
		articleDto.setCreatetime(DateUtil.date2Str(article.getCreatetime(), "yyyy-MM-dd HH:mm:ss"));
	    try {
	    	@SuppressWarnings("unchecked")
			List<String> imgs= JSON.parseObject(article.getCoverimgs(),new ArrayList<String>().getClass());
	    	articleDto.setCoverimglist(article.getCoverimgs()==null?new ArrayList<String>():imgs);
		} catch (Exception e) {
			logger.info("articleToDto execption :"+e);
		}
	
		return articleDto;
	}
}
