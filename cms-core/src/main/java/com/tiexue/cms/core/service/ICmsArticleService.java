package com.tiexue.cms.core.service;

import java.util.List;

import com.tiexue.cms.core.entity.CmsArticle;

public interface ICmsArticleService {
	int deleteByPrimaryKey(Integer id);

    int insert(CmsArticle record);

    int insertSelective(CmsArticle record);
    CmsArticle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsArticle record);
    int updateByPrimaryKey(CmsArticle record);
    CmsArticle getModelByUniqueFlag(String uniqueFlag);
    
    int saveArticle(CmsArticle CmsArticle);
    /**
     * 根据条件获取分页的内容
     * @param categorys 分类id:如果是多个就用逗号分隔.
     * @param pageNo 页码
     * @param pageSize 每页数量
     * @return
     */
    List<CmsArticle> getList(String categorys,Integer pageNo,Integer pageSize);
    
    /**
     * 根据查询条件获取文章数量
     * @param strWhere
     * @return
     */
    int getCount(String strWhere);
    /**
     * 获取未删除的文章明细信息
     * @param id
     * @return
     */
    CmsArticle getDetail(int id);
    /**
     * 更新内容
     * @param CmsArticle
     * @return
     */
    int updateArticle(CmsArticle CmsArticle);
    /**
     * 删除内容(逻辑删除)
     * @param id
     * @return
     */
    int deleteArticle(int id);
    
    
    /**
     * 恢复内容
     * @param id
     * @return
     */
    int recoverArticle(int id);
}
