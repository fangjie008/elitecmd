package com.tiexue.cms.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tiexue.cms.core.entity.CmsArticleSub;
import com.tiexue.cms.core.mapper.CmsArticleSubMapper;
import com.tiexue.cms.core.service.ICmsArticleSubService;
@Service("iCmsArticleSubService")
public class CmsArticleSubServiceImpl implements ICmsArticleSubService {

	@Resource 
	CmsArticleSubMapper cmsArticleSubMapper;
	
	@Override
	public int insert(CmsArticleSub record) {
		return cmsArticleSubMapper.insert(record);
	}

	@Override
	public int insertSelective(CmsArticleSub record) {
		return cmsArticleSubMapper.insertSelective(record);
	}

	@Override
	public CmsArticleSub select(Integer id) {
		return cmsArticleSubMapper.select(id);
	}

	@Override
	public int update(CmsArticleSub record) {
		return cmsArticleSubMapper.update(record);
	}

	@Override
	public int delete(int id) {
		return cmsArticleSubMapper.deleteByPrimaryKey(id);
	}



}
