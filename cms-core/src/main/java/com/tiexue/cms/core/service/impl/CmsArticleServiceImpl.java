package com.tiexue.cms.core.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiexue.cms.core.cache.CacheKey;
import com.tiexue.cms.core.cache.MemoryCache;
import com.tiexue.cms.core.define.CmsContants;
import com.tiexue.cms.core.entity.CmsArticle;
import com.tiexue.cms.core.entity.CmsArticleSub;


import com.tiexue.cms.core.mapper.CmsArticleMapper;
import com.tiexue.cms.core.service.ICmsArticleService;
import com.tiexue.cms.core.service.ICmsArticleSubService;
import com.tiexue.cms.core.service.ICmsCategoryService;


@Service("CmsArticleServiceImpl")
public class CmsArticleServiceImpl implements ICmsArticleService {
	// 日志
	private static Logger logger = Logger.getLogger(CmsArticleServiceImpl.class);
//	private MemoryCache memoryCache= MemoryCache.getInstance();
	@Resource
	CmsArticleMapper cmsArticleMapper;
	@Resource
	ICmsArticleSubService iCmsArticleSubService;
	@Resource
	ICmsCategoryService iCmsCategoryService;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return cmsArticleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(CmsArticle record) {
		return cmsArticleMapper.insert(record);
	}

	@Override
	public int insertSelective(CmsArticle record) {
		return cmsArticleMapper.insertSelective(record);
	}

	@Override
	public CmsArticle selectByPrimaryKey(Integer id) {
		return cmsArticleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CmsArticle record) {
		return cmsArticleMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey(CmsArticle record) {
		return cmsArticleMapper.updateByPrimaryKey(record);
	}

	@Override
	public CmsArticle getModelByUniqueFlag(String uniqueFlag) {
		return cmsArticleMapper.getModelByUniqueFlag(uniqueFlag);
	}

	@Override
	@Transactional
	public int saveArticle(CmsArticle CmsArticle) {
		try {
			if(CmsArticle==null)
				return -1;
		    int insertNum=insert(CmsArticle);
		    if(CmsArticle.getId()>0){
		    	CmsArticleSub CmsArticleSub=new CmsArticleSub();
		    	CmsArticleSub.setId(CmsArticle.getId());
		    	CmsArticleSub.setOriginalcontent(CmsArticle.getOriginalContent());
		    	CmsArticleSub.setContentpic(CmsArticle.getContentPic());
		    	CmsArticleSub.setMaterials(CmsArticle.getMaterials());
		    	insertNum=iCmsArticleSubService.insert(CmsArticleSub);
		    	return insertNum;
		    }
		} catch (Exception e) {
			logger.error(e.getMessage());
			//必须抛出异常 否则事务就不能正常执行
			throw e;
		}
		return 0;
	}
	
	@Override
	@Transactional
	public int updateArticle(CmsArticle CmsArticle) {
		try {
			if(CmsArticle==null)
				return -1;
		    int updateNum=updateByPrimaryKey(CmsArticle);
		    if(CmsArticle.getId()>0){
		    	CmsArticleSub CmsArticleSub=new CmsArticleSub();
		    	CmsArticleSub.setId(CmsArticle.getId());
		    	CmsArticleSub.setOriginalcontent(CmsArticle.getOriginalContent());
		    	CmsArticleSub.setContentpic(CmsArticle.getContentPic());
		    	CmsArticleSub.setMaterials(CmsArticle.getMaterials());
		    	updateNum=iCmsArticleSubService.update(CmsArticleSub);
		    	return updateNum;
		    }
		} catch (Exception e) {
			logger.error(e.getMessage());
			//必须抛出异常 否则事务就不能正常执行
			throw e;
		}
		return 0;
	}

	@Override
	public List<CmsArticle> getList(String strWhe, Integer pageNo, Integer pageSize) {
		return cmsArticleMapper.getList(strWhe, pageNo, pageSize);
	}

	@Override
	public int getCount(String categorys) {
		String strWhere=" 1=1";
		if(categorys!=null&&!"".equals(categorys)){
			strWhere +=" and CategoryId in ("+categorys+")";
		}
		return cmsArticleMapper.getCount(strWhere);
	}
	@Override
	public CmsArticle getDetail(int id){
		CmsArticle cmsArticle;
		cmsArticle=cmsArticleMapper.getModel(id);
		if(cmsArticle!=null){
			CmsArticleSub CmsArticleSub= iCmsArticleSubService.select(id);
			if(CmsArticleSub!=null){
				cmsArticle.setOriginalContent(CmsArticleSub.getOriginalcontent());
				cmsArticle.setContentPic(CmsArticleSub.getContentpic());
				cmsArticle.setMaterials(CmsArticleSub.getMaterials());
			}
		}
		return cmsArticle;
	}

	/**
	 * 文章删除（逻辑删除）
	 */
	@Override
	public int deleteArticle(int id) {

	     int updateNum=cmsArticleMapper.updeteStatus(CmsContants.ArticleStatus_Deleted, id);
	     return updateNum;
		   
	}
	
	
	/**
	 * 恢复文章
	 */
	@Override
	public int recoverArticle(int id) {

	     int updateNum=cmsArticleMapper.updeteStatus(CmsContants.ArticleStatus_Normal, id);
	     return updateNum;
		   
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<CmsArticle> getMoreList(String categorys, Integer pageNo, Integer pageSize) {

		MemoryCache memoryCache= MemoryCache.getInstance();
		List<CmsArticle> articles=null;
		String strWhere=" status="+CmsContants.ArticleStatus_Normal;
		if(categorys!=null&&!"".equals(categorys)){
			strWhere +=" and CategoryId in ("+categorys+")";
		}
		
		try {
			String key=CacheKey.getWap_list("1", 0);
		    articles= (List<CmsArticle>)memoryCache.get(key);
			if(articles!=null)
				return articles;
			articles=cmsArticleMapper.getList(strWhere,pageNo,pageSize);
			memoryCache.set(key, 10, articles);
			
		} catch (Exception e) {
			logger.error("getMoreList execption:"+e);
		}
		return articles;
	}

	@Override
	public CmsArticle getNormalDetail(int id) {
		CmsArticle cmsArticle=null;
		MemoryCache memoryCache= MemoryCache.getInstance();
		try {
			String key=CacheKey.getWap_detail(id);
			cmsArticle= (CmsArticle)memoryCache.get(key);
			if(cmsArticle!=null)
				return cmsArticle;
			cmsArticle=cmsArticleMapper.getNormalModel(id, CmsContants.ArticleStatus_Normal);
			if(cmsArticle!=null){
				CmsArticleSub CmsArticleSub= iCmsArticleSubService.select(id);
				if(CmsArticleSub!=null){
					cmsArticle.setOriginalContent(CmsArticleSub.getOriginalcontent());
					cmsArticle.setContentPic(CmsArticleSub.getContentpic());
					cmsArticle.setMaterials(CmsArticleSub.getMaterials());
				}
			}
			memoryCache.set(key, 2*60, cmsArticle);
		} catch (Exception e) {
			logger.error("getNormalDetail error:"+e);
		}
		
		return cmsArticle;
	}


}
