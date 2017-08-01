package com.tiexue.cms.manage.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.tiexue.cms.base.util.DateUtil;
import com.tiexue.cms.core.define.CmsContants;
import com.tiexue.cms.core.dto.CmsArticleDto;
import com.tiexue.cms.core.entity.CmsArticle;
import com.tiexue.cms.manage.controller.CmsArticleController;

public class ArticleConvert {
	 private static Logger logger = Logger.getLogger(ArticleConvert.class);
	/**
	 * 必填项填充
	 * @param article
	 * @return
	 */
	public static void fillArticle(CmsArticle article){
		article.setCaicount(article.getCaicount()==null?0:article.getCaicount());
		article.setCategoryid(article.getCategoryid()==null?0:article.getCategoryid());
		article.setCollectioncount(article.getCollectioncount()==null?0:article.getCollectioncount());
		article.setCommentcount(article.getCommentcount()==null?0:article.getCommentcount());
		article.setContentlen(article.getOriginalContent()==null?0:article.getOriginalContent().length());
		article.setContenttype(article.getContenttype()==null?0:article.getContenttype());
		article.setCoverimgs(article.getCoverimgs()==null?"[]":article.getCoverimgs());
		article.setCreatetime(new Date());
		article.setDingcount(article.getDingcount()==null?0:article.getDingcount());
		article.setFromid(article.getFromid()==null?0:article.getFromid());
		article.setFromname(article.getFromname()==null?"":article.getFromname());
		article.setImgshowtype(article.getImgshowtype()==null?0:article.getImgshowtype());
		article.setIntro(article.getIntro()==null?"":article.getIntro());
		article.setMark(article.getMark()==null?0:article.getMark());
		article.setOriginalid(article.getOriginalid()==null?"":article.getOriginalid());
		article.setOriginaltitle(article.getOriginaltitle()==null?"":article.getOriginaltitle());
		article.setOriginalurl(article.getOriginalurl()==null?"":article.getOriginalurl());
		article.setOriginalContent(article.getOriginalContent()==null?"":article.getOriginalContent());
		article.setPlatformname(article.getPlatformname()==null?"":article.getPlatformname());
		article.setPlatformid(article.getPlatformid()==null?0:article.getPlatformid());
		article.setPublisherid(article.getPublisherid()==null?0:article.getPublisherid());
		article.setPublishericon(article.getPublishericon()==null?"":article.getPublishericon());
		article.setPublishername(article.getPublishername()==null?"":(article.getPublishername().length()>32?article.getPublishername().substring(0,30):article.getPublishername()));
		article.setShowtime(new Date());
		article.setSourcetype(article.getSourcetype()==null?0:article.getSourcetype());
		article.setStatus(article.getStatus()==null?0:article.getStatus());
		article.setTags(article.getTags()==null?"[]":article.getTags());
		article.setTitle(article.getTitle()==null?"":article.getTitle());
		article.setUniqueflag(article.getUniqueflag()==null?"":article.getUniqueflag());
		article.setUscore(article.getUscore()==null?0:article.getUscore());
		article.setViewcount(article.getViewcount()==null?0:article.getViewcount());
		article.setWeight(article.getWeight()==null?0:article.getWeight());
		article.setSharecount(article.getSharecount()==null?0:article.getSharecount());
		article.setContentPic(article.getContentPic()==null?"[]":article.getContentPic());
		article.setMaterials(article.getMaterials()==null?"{}":article.getMaterials());		
	}
	
	/**
	 * 内容转化成前端直接用的内容
	 * @param articles
	 * @return
	 */
	public static List<CmsArticleDto> articleListToDto(List<CmsArticle> articles){
		List<CmsArticleDto> articleDtos=new ArrayList<CmsArticleDto>();
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
	public static CmsArticleDto articleToDto(CmsArticle article){
		CmsArticleDto articleDto=new CmsArticleDto();
		articleDto.setId(article.getId());
		articleDto.setCaicount(article.getCaicount()==null?0:article.getCaicount());
		articleDto.setCategoryid(article.getCategoryid()==null?0:article.getCategoryid());
		articleDto.setCollectioncount(article.getCollectioncount()==null?0:article.getCollectioncount());
		articleDto.setCommentcount(article.getCommentcount()==null?0:article.getCommentcount());
		articleDto.setContentlen(article.getOriginalContent()==null?0:article.getOriginalContent().length());
		articleDto.setContenttype(article.getContenttype()==null?0:article.getContenttype());
		articleDto.setCoverimgs(article.getCoverimgs()==null?"[]":article.getCoverimgs());
		
		articleDto.setDingcount(article.getDingcount()==null?0:article.getDingcount());
		articleDto.setFromid(article.getFromid()==null?0:article.getFromid());
		articleDto.setFromname(article.getFromname()==null?"":article.getFromname());
		articleDto.setImgshowtype(article.getImgshowtype()==null?0:article.getImgshowtype());
		articleDto.setIntro(article.getIntro()==null?"":article.getIntro());
		articleDto.setMark(article.getMark()==null?0:article.getMark());
		articleDto.setOriginalid(article.getOriginalid()==null?"":article.getOriginalid());
		articleDto.setOriginaltitle(article.getOriginaltitle()==null?"":article.getOriginaltitle());
		articleDto.setOriginalurl(article.getOriginalurl()==null?"":article.getOriginalurl());
		articleDto.setOriginalContent(article.getOriginalContent()==null?"":article.getOriginalContent());
		articleDto.setPlatformname(article.getPlatformname()==null?"":article.getPlatformname());
		articleDto.setPlatformid(article.getPlatformid()==null?0:article.getPlatformid());
		articleDto.setPublisherid(article.getPublisherid()==null?0:article.getPublisherid());
		articleDto.setPublishericon(article.getPublishericon()==null?"":article.getPublishericon());
		articleDto.setPublishername(article.getPublishername()==null?"":(article.getPublishername().length()>32?article.getPublishername().substring(0,30):article.getPublishername()));
		
		articleDto.setSourcetype(article.getSourcetype()==null?0:article.getSourcetype());
		articleDto.setStatus(article.getStatus()==null?0:article.getStatus());
		articleDto.setTags(article.getTags()==null?"[]":article.getTags());
		articleDto.setTitle(article.getTitle()==null?"":article.getTitle());
		articleDto.setUniqueflag(article.getUniqueflag()==null?"":article.getUniqueflag());
		articleDto.setUscore(article.getUscore()==null?0:article.getUscore());
		articleDto.setViewcount(article.getViewcount()==null?0:article.getViewcount());
		articleDto.setWeight(article.getWeight()==null?0:article.getWeight());
		articleDto.setSharecount(article.getSharecount()==null?0:article.getSharecount());
		articleDto.setContentPic(article.getContentPic()==null?"[]":article.getContentPic());
		articleDto.setMaterials(article.getMaterials()==null?"{}":article.getMaterials());
		
		articleDto.setShowtime(DateUtil.date2Str(article.getShowtime(), "yyyy-MM-dd HH:mm:ss"));
		articleDto.setCreatetime(DateUtil.date2Str(article.getCreatetime(), "yyyy-MM-dd HH:mm:ss"));
		articleDto.setStatusName(CmsContants.ArticleType.get(article.getStatus()));
	    try {
	    	@SuppressWarnings("unchecked")
			List<String> imgs= JSON.parseObject(article.getCoverimgs(),new ArrayList<String>().getClass());
	    	articleDto.setCoverimgList(article.getCoverimgs()==null?new ArrayList<String>():imgs);
		} catch (Exception e) {
			logger.debug("articleToDto execption :"+e);
		}
		
		return articleDto;
	}

}
