package com.tiexue.cms.manage.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.tiexue.cms.base.util.DateUtil;
import com.tiexue.cms.core.define.CmsContants;
import com.tiexue.cms.core.dto.CmsCategoryDto;

import com.tiexue.cms.core.entity.CmsCategory;


public class CategoryConvert {
	 private static Logger logger = Logger.getLogger(CategoryConvert.class);

	 
	     /**
		 * 必填项填充
		 * @param category
		 * @return
		 */
		public static void fillcategory(CmsCategory category){
			category.setCoverimg(category.getCoverimg()==null?"":category.getCoverimg());
			category.setCreatetime(new Date());
			category.setIntro(category.getIntro()==null?"":category.getIntro());
			category.setName(category.getName()==null?"":category.getName());
			category.setParentid(category.getParentid()==null?0:category.getParentid());
			category.setRemark(category.getRemark()==null?"":category.getRemark());
			category.setStatus(category.getStatus()==null?0:category.getStatus());
			category.setTags(category.getTags()==null?"[]":category.getTags());
			category.setType(category.getType()==null?0:category.getType());
			category.setWeight(category.getWeight()==null?0:category.getWeight());
		}
	
	/**
	 * 内容转化成前端直接用的内容
	 * @param categorys
	 * @return
	 */
	public static List<CmsCategoryDto> categoryListToDto(List<CmsCategory> categorys){
		List<CmsCategoryDto> categoryDtos=new ArrayList<CmsCategoryDto>();
		if(categorys==null||categorys.size()<=0)
			return categoryDtos;
		for (CmsCategory category : categorys) {
			categoryDtos.add(categoryToDto(category));
		}
		return categoryDtos;
	}
	
	/**
	 * 内容转化成前端直接用的内容
	 * @param category
	 * @return
	 */
	public static CmsCategoryDto categoryToDto(CmsCategory category){
		CmsCategoryDto categoryDto=new CmsCategoryDto();
		categoryDto.setId(category.getId());
		categoryDto.setCoverimg(category.getCoverimg());
		categoryDto.setCreatetime(DateUtil.date2Str(category.getCreatetime(), "yyyy-MM-dd HH:mm:ss"));
		categoryDto.setIntro(category.getIntro());
		categoryDto.setName(category.getName());
		categoryDto.setParentid(category.getParentid());
		categoryDto.setRemark(category.getRemark());
		categoryDto.setStatus(category.getStatus());
		categoryDto.setStatusname(CmsContants.CategoryStatus.get(category.getStatus()));
		categoryDto.setTags(category.getTags());
		categoryDto.setType(category.getType());
		categoryDto.setWeight(category.getWeight());
		
		return categoryDto;
	}

}
