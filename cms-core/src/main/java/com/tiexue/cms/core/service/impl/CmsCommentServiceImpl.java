package com.tiexue.cms.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tiexue.cms.core.entity.CmsComment;
import com.tiexue.cms.core.mapper.CmsCommentMapper;
import com.tiexue.cms.core.service.ICmsCommentService;

@Service("iCmsCommentService")
public class CmsCommentServiceImpl implements ICmsCommentService {

	@Resource 
	CmsCommentMapper cmsCommentMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return cmsCommentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(CmsComment record) {
		return cmsCommentMapper.insert(record);
	}

	@Override
	public int insertSelective(CmsComment record) {
		return cmsCommentMapper.insertSelective(record);
	}

	@Override
	public CmsComment selectByPrimaryKey(Integer id) {
		return cmsCommentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CmsComment record) {
		return cmsCommentMapper.updateByPrimaryKeySelective(record);
	}


	@Override
	public int updateByPrimaryKey(CmsComment record) {
		return cmsCommentMapper.updateByPrimaryKey(record);
	}


}
