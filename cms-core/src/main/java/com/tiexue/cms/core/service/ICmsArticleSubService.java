package com.tiexue.cms.core.service;

import com.tiexue.cms.core.entity.CmsArticleSub;

public interface ICmsArticleSubService {

    int insert(CmsArticleSub record);

    int insertSelective(CmsArticleSub record);
    
    CmsArticleSub select(Integer id);
    
    int update(CmsArticleSub record);
    /**
     * 删除
     * @param id
     * @return
     */
    int delete(int id);
}