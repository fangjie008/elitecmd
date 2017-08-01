package com.tiexue.cms.manage.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.alibaba.fastjson.JSON;
import com.tiexue.cms.core.define.CmsContants;
import com.tiexue.cms.core.dto.CmsArticleDto;
import com.tiexue.cms.core.entity.CmsArticle;
import com.tiexue.cms.core.entity.CmsCategory;
import com.tiexue.cms.core.service.ICmsArticleService;
import com.tiexue.cms.core.service.ICmsCategoryService;
import com.tiexue.cms.manage.define.ImageType;
import com.tiexue.cms.manage.define.ImgConstants;
import com.tiexue.cms.manage.dto.Paging;
import com.tiexue.cms.manage.dto.ResultMsg;
import com.tiexue.cms.manage.entity.ImgFormat;
import com.tiexue.cms.manage.entity.ImgUploadRet;
import com.tiexue.cms.manage.utils.ArticleConvert;
import com.tiexue.cms.manage.utils.ImgUtils;
import com.tiexue.cms.manage.utils.TransToPage;



@Controller
@RequestMapping("article")
public class CmsArticleController {

	Logger logger = Logger.getLogger(CmsArticleController.class);
	@Resource
	ICmsArticleService iCmsArticleService;
	@Resource
	ICmsCategoryService iCmsCategoryService;
	private static final int psize=10;
	
	@RequestMapping("list")
	public String getList(HttpServletRequest request, HttpServletResponse response) {
		try {
			int pindex=1;
			String pindexStr=request.getParameter("pindex");
			if(pindexStr!=null&&!pindexStr.isEmpty()){
				pindex=Integer.parseInt(pindexStr);
			}
			int pStart=(pindex-1)*psize;
			int pcount=iCmsArticleService.getCount(" 1=1");
			//如果最后一页只有一条数据，则在删除时取上一页数据
			if(pStart>0&&pStart>=pcount){
				pStart=pStart-psize;
			}
			List<CmsArticle> articles= iCmsArticleService.getList("1=1",pStart,psize);
			List<CmsCategory> categories= iCmsCategoryService.getNormalList();
			List<CmsArticleDto> articledtos =ArticleConvert.articleListToDto(articles,categories);
			Paging paging=new Paging();
			paging.setPcount(pcount);
			paging.setPsize(psize);
			paging.calcPtotalpages();
			if(pindex>paging.getPtotalpages()){
				pindex=paging.getPtotalpages();
			}
				paging.setPindex(pindex);
			request.setAttribute("articles",articledtos);
			request.setAttribute("paging",paging);
		} catch (Exception e) {
			logger.error("article/list error:"+e.getMessage());
		}
		return "article/list";
	}

	@RequestMapping("detail")
	public String getDetail(HttpServletRequest request, HttpServletResponse response, Integer id) {
		int imgShowType = 0;
		int categoryId = 0;
		try {
			if (id != null && id > 0) {
				CmsArticle cArticle = iCmsArticleService.getDetail(id);
				imgShowType = cArticle.getImgshowtype() == null ? 0 : cArticle.getImgshowtype();
				categoryId = cArticle.getCategoryid() == null ? 0 : cArticle.getCategoryid();
				request.setAttribute("article", cArticle);
			}
		} catch (Exception e) {
			logger.debug("getDetail exception: " + e);
		}
		String imgTypeStr = TransToPage.mapToOptions(false, imgShowType, ImageType.typeMap);
		List<CmsCategory> categorys = iCmsCategoryService.getNormalList();
		String categoryStr = TransToPage.categoryToOptions(true, categoryId, categorys);
		request.setAttribute("imgType", imgTypeStr);
		request.setAttribute("categoryType", categoryStr);
		return "article/detail";
	}

	@RequestMapping("save")
	@ResponseBody
	public String save(HttpServletRequest request, HttpServletResponse response, Integer id) {
		CmsArticle cmsArticle = new CmsArticle();
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setOk(false);
		try {
			String title = request.getParameter("title");
			String intro = request.getParameter("intro");
			String originalurl = request.getParameter("originalurl");
			String viewcount = request.getParameter("viewcount");
			String bodyContent = request.getParameter("bodyContent");
			String cover_ImgDefault = request.getParameter("cover_ImgDefault");
			String select_ImgShowType = request.getParameter("select_ImgShowType");
			String categoryid = request.getParameter("categoryid");
			// 更新
			if (id != null && id > 0) {
				cmsArticle = iCmsArticleService.getDetail(id);
			} else {
				// 状态为正常
				cmsArticle.setStatus(CmsContants.ArticleStatus_Normal);
			}
			cmsArticle.setTitle(title);
			cmsArticle.setIntro(intro);
			cmsArticle.setOriginalurl(originalurl);
			cmsArticle.setViewcount(Integer.parseInt(viewcount));
			cmsArticle.setOriginalContent(bodyContent);
			cmsArticle.setCoverimgs(cover_ImgDefault);
			cmsArticle.setImgshowtype(Integer.parseInt(select_ImgShowType));
			cmsArticle.setCategoryid(Integer.parseInt(categoryid));

			// 填充必填项
			ArticleConvert.fillArticle(cmsArticle);
			// 更新
			if (id != null && id > 0) {
				int res = iCmsArticleService.updateArticle(cmsArticle);
				if (res > 0) {
					resultMsg.setOk(true);
					resultMsg.setMsg("更新成功");
				}
			} else {
				// 状态为正常
				int res = iCmsArticleService.saveArticle(cmsArticle);
				if (res > 0) {
					resultMsg.setOk(true);
					resultMsg.setMsg("新增成功");
				}
			}

		} catch (Exception e) {
			logger.debug(" save execption:"+e);
			resultMsg.setOk(false);
			resultMsg.setMsg("操作异常");
		}
		return JSON.toJSONString(resultMsg);
	}
	
	@RequestMapping("deleteModel")
	@ResponseBody
	public String deleteModel(HttpServletRequest request,Integer id){
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setOk(false);
		try {
			if(id!=null&&id>0){
				int res=iCmsArticleService.deleteArticle(id);
				resultMsg.setOk(true);
				resultMsg.setMsg("删除成功");
			}else{
				resultMsg.setMsg("参数异常");
			}
			
		} catch (Exception e) {
			logger.debug(" deleteModel error:"+e);
			resultMsg.setMsg("删除异常");
		}
		return JSON.toJSONString(resultMsg);
	}
	@RequestMapping("recover")
	@ResponseBody
	public String recover(HttpServletRequest request,Integer id){
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setOk(false);
		try {
			if(id!=null&&id>0){
				int res=iCmsArticleService.recoverArticle(id);
				resultMsg.setOk(true);
				resultMsg.setMsg("恢复成功");
			}else{
				resultMsg.setMsg("参数异常");
			}
			
		} catch (Exception e) {
			logger.debug(" deleteModel error:"+e);
			resultMsg.setMsg("恢复异常");
		}
		return JSON.toJSONString(resultMsg);
	}

	/**
	 * 图片上传到资源服务器
	 * 
	 * @param img1
	 * @param img2
	 * @param img3
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("updateImg")
	@ResponseBody
	public String updateImg(@RequestParam(value = "img1", required = false) MultipartFile img1,
			@RequestParam(value = "img2", required = false) MultipartFile img2,
			@RequestParam(value = "img3", required = false) MultipartFile img3, HttpServletRequest request,
			HttpServletResponse response) {

		// MultipartHttpServletRequest multipartRequest =
		// (MultipartHttpServletRequest) request;
		// img1= multipartRequest.getFile("img1");
		// img2= multipartRequest.getFile("img2");
		// img3= multipartRequest.getFile("img3");
		Map<String, String> result = new HashMap<String, String>();
		boolean isWideImage = "1".equals(request.getParameter("select_ImgShowType"));
		ImgFormat format = null;

		if (isWideImage) {
			format = new ImgFormat(1, ImgConstants.ImgFormatType_ScaleWith, 500, 0);
		} else {
			format = new ImgFormat(1, ImgConstants.ImgFormatType_Spec, 140, 90);
		}

		if (img1 != null) {
			ImgUploadRet imgRet = ImgUtils.updateImage(img1, format);
			if (imgRet.isSuc()) {
				result.put("img1", imgRet.getImgUrl(1));
			}
		}
		if (img2 != null) {
			ImgUploadRet imgRet = ImgUtils.updateImage(img2, format);
			if (imgRet.isSuc()) {
				result.put("img2", imgRet.getImgUrl(1));
			}
		}
		if (img3 != null) {
			ImgUploadRet imgRet = ImgUtils.updateImage(img3, format);
			if (imgRet.isSuc()) {
				result.put("img3", imgRet.getImgUrl(1));
			}
		}
		String resStr = JSON.toJSONString(result);
		return resStr;
	}

}
