package com.tiexue.cms.core.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiexue.cms.core.define.CmsContants;
import com.tiexue.cms.core.entity.CmsArticle;
import com.tiexue.cms.core.entity.CmsArticleSub;
import com.tiexue.cms.core.entity.CmsCategory;

import com.tiexue.cms.core.mapper.CmsArticleMapper;
import com.tiexue.cms.core.mapper.CmsArticleSubMapper;
import com.tiexue.cms.core.mapper.CmsCategoryMapper;
import com.tiexue.cms.core.service.ICmsArticleService;
import com.tiexue.cms.core.service.ICmsArticleSubService;
import com.tiexue.cms.core.service.ICmsCategoryService;


@Service("CmsArticleServiceImpl")
public class CmsArticleServiceImpl implements ICmsArticleService {
	// 日志
	private Logger logger = Logger.getLogger(CmsArticleServiceImpl.class);
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
		String strWhere=" status="+CmsContants.ArticleStatus_Normal;
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

	@Override
	@Transactional
	public int deleteArticle(int id) {
		try {
			
		     int updateNum=deleteByPrimaryKey(id);
		     updateNum=iCmsArticleSubService.delete(id);
		     return updateNum;
		    
		} catch (Exception e) {
			logger.error(e.getMessage());
			//必须抛出异常 否则事务就不能正常执行
			throw e;
		}

	}
	


}