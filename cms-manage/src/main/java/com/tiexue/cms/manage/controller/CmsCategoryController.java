package com.tiexue.cms.manage.controller;


import java.util.List;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;


import com.alibaba.fastjson.JSON;

import com.tiexue.cms.core.define.CmsContants;
import com.tiexue.cms.core.dto.CmsCategoryDto;
import com.tiexue.cms.core.entity.CmsCategory;
import com.tiexue.cms.core.service.ICmsCategoryService;
import com.tiexue.cms.manage.define.ImageType;

import com.tiexue.cms.manage.dto.Paging;
import com.tiexue.cms.manage.dto.ResultMsg;

import com.tiexue.cms.manage.utils.CategoryConvert;
import com.tiexue.cms.manage.utils.TransToPage;

@Controller
@RequestMapping("category")
public class CmsCategoryController {

	Logger logger = Logger.getLogger(CmsCategoryController.class);
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
			int pcount=iCmsCategoryService.getCount(" 1=1");
			//如果最后一页只有一条数据，则在删除时取上一页数据
			if(pStart>0&&pStart>=pcount){
				pStart=pStart-psize;
			}
			List<CmsCategory> Categorys= iCmsCategoryService.getList("1=1",pStart,psize);
			List<CmsCategoryDto> Categorydtos =CategoryConvert.categoryListToDto(Categorys);
			Paging paging=new Paging();
			paging.setPcount(pcount);
			paging.setPsize(psize);
			paging.calcPtotalpages();
			if(pindex>paging.getPtotalpages()){
				pindex=paging.getPtotalpages();
			}
				paging.setPindex(pindex);
			request.setAttribute("categorys",Categorydtos);
			request.setAttribute("paging",paging);
		} catch (Exception e) {
			logger.error("category/list error:"+e.getMessage());
		}
		return "category/list";
	}

	@RequestMapping("detail")
	public String getDetail(HttpServletRequest request, HttpServletResponse response, Integer id) {
		int imgShowType = 0;
		int categoryId = 0;
		try {
			if (id != null && id > 0) {
				CmsCategory category = iCmsCategoryService.getModel(id);

				request.setAttribute("category", category);
			}
		} catch (Exception e) {
			logger.debug("getDetail exception: " + e);
		}
		String imgTypeStr = TransToPage.mapToOptions(false, imgShowType, ImageType.typeMap);
		List<CmsCategory> categorys = iCmsCategoryService.getNormalList();
		String categoryStr = TransToPage.categoryToOptions(true, categoryId, categorys);
		request.setAttribute("imgType", imgTypeStr);
		request.setAttribute("categoryType", categoryStr);
		return "category/detail";
	}

	@RequestMapping("save")
	@ResponseBody
	public String save(HttpServletRequest request, HttpServletResponse response, Integer id) {
		CmsCategory cmsCategory = new CmsCategory();
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setOk(false);
		try {
			String name = request.getParameter("name");
			String intro = request.getParameter("intro");
			String remark = request.getParameter("remark");
			// 更新
			if (id != null && id > 0) {
				cmsCategory = iCmsCategoryService.getModel(id);
			} else {
				// 状态为正常
				cmsCategory.setStatus(CmsContants.CategoryStatus_Normal);
			}
			cmsCategory.setName(name);
			cmsCategory.setIntro(intro);
			cmsCategory.setRemark(remark);
			CategoryConvert.fillcategory(cmsCategory);
			// 更新
			if (id != null && id > 0) {
				int res = iCmsCategoryService.updateByPrimaryKey(cmsCategory);
				if (res > 0) {
					resultMsg.setOk(true);
					resultMsg.setMsg("更新成功");
				}
			} else {
				// 状态为正常
				int res = iCmsCategoryService.insert(cmsCategory);
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
			    int status=	CmsContants.CategoryStatus_Delete;
				int res=iCmsCategoryService.updeteStatus(status, id);
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
				  int status=	CmsContants.CategoryStatus_Normal;
					int res=iCmsCategoryService.updeteStatus(status, id);
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

}
