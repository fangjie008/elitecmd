package com.tiexue.cms.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tiexue.cms.core.define.CmsContants;
import com.tiexue.cms.core.entity.CmsCategory;
import com.tiexue.cms.core.mapper.CmsCategoryMapper;
import com.tiexue.cms.core.service.ICmsCategoryService;

@Service("CmscategoryServiceImpl")
public class CmsCategoryServiceImpl implements ICmsCategoryService {
	@Resource
	CmsCategoryMapper cmsCategoryMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return cmsCategoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(CmsCategory record) {
		return cmsCategoryMapper.insert(record);
	}

	@Override
	public int insertSelective(CmsCategory record) {
		return cmsCategoryMapper.insertSelective(record);
	}

	@Override
	public CmsCategory selectByPrimaryKey(Integer id) {
		return cmsCategoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CmsCategory record) {
		return cmsCategoryMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(CmsCategory record) {
		return cmsCategoryMapper.updateByPrimaryKey(record);
	}

	@Override
	public CmsCategory getModelByName(String name) {
		return cmsCategoryMapper.getModelByName(name);
	}

	@Override
	public List<CmsCategory> getNormalList() {
		int status=CmsContants.CategoryStatus_Normal;
		return cmsCategoryMapper.getNormalList(status);
	}


}
