package com.tiexue.cms.core.service;

import com.tiexue.cms.core.entity.CmsComment;


public interface ICmsCommentService {

    int deleteByPrimaryKey(Integer id);


    int insert(CmsComment record);

    int insertSelective(CmsComment record);

    CmsComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsComment record);
    
    int updateByPrimaryKey(CmsComment record);
}