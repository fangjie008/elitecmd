package com.tiexue.cms.core.service;





import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
	    
	    /**
	     * 获取分类信息
	     * @param id
	     * @return
	     */
	    CmsCategory getModel(int id);
	    

	    /**
	     * 获取分组信息
	     * @param strWhere
	     * @param pageNo
	     * @param pageSize
	     * @return
	     */
	    List<CmsCategory> getList(String strWhere,Integer pageNo,Integer pageSize);
	    
	    
	    /**
	     * 获取总数
	     * @param strWhere
	     * @return
	     */
	    int getCount(String strWhere);
	    /**
	     * 更改分类状态
	     * @param status
	     * @param id
	     * @return
	     */
	    int updeteStatus(int status,int id);
}
