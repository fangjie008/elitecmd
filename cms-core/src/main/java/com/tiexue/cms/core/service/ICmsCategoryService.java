package com.tiexue.cms.core.service;





import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tiexue.cms.core.entity.CmsCategory;

public interface ICmsCategoryService {

	    int deleteByPrimaryKey(Integer id);
	    
	    int insert(CmsCategory record);

	    int insertSelective(CmsCategory record);

	    CmsCategory selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(CmsCategory record);

	    int updateByPrimaryKey(CmsCategory record);
	    
	    CmsCategory getModelByName(String name);
	    /**
	     * 获取正常状态的分类信息
	     * @param status
	     * @return
	     */
	    List<CmsCategory> getNormalList();
}
