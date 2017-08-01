package com.tiexue.cms.manage.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tiexue.cms.core.define.CmsAdminType;
import com.tiexue.cms.core.dto.CmsAdminDto;
import com.tiexue.cms.core.entity.CmsAdmin;
import com.tiexue.cms.core.service.ICmsAdminService;
import com.tiexue.cms.core.shiro.PasswordHelper;
import com.tiexue.cms.manage.controller.CommonUtil;
import com.tiexue.cms.manage.dto.ResultMsg;
import com.tiexue.cms.manage.utils.AdminConvert;
import com.tiexue.cms.manage.utils.TransToPage;

@Controller
@RequestMapping("admin")
public class CmsAdminController {

	@Resource
	ICmsAdminService cmsAdminSer;
	
	Logger logger=Logger.getLogger(CmsAdminController.class);
	
	@Autowired
    private PasswordHelper passwordHelper;
	
	/**
	 * 查询用户列表
	 **/
	// @RequiresRoles("admin")
	@RequestMapping("list")
	public String getList(Model model) {
		List<CmsAdmin> admins= cmsAdminSer.getList();
		List<CmsAdminDto> adminDtos= AdminConvert.toAdminListDto(admins);
		model.addAttribute("userList",adminDtos);
		return "admin/list";
	}

	/**
	 * 添加用户
	 **/
	// @RequiresRoles("admin")
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String showAddUser(HttpServletRequest request) {
		String type= TransToPage.mapToOptions(true, 0,CmsAdminType.AdminTypeMap);
		request.setAttribute("type", type);
		return "admin/add";
	}

	/**
	 * 创建用户逻辑
	 **/
	// @RequiresRoles("admin")
	@RequestMapping(value = "doAddUser", method = RequestMethod.POST)
	@ResponseBody
	public String doAddUser(String username, String password, String intro, String roles,
			Integer type) {
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setOk(false);
		try {
			CmsAdmin user = new CmsAdmin();
			user.setName(username);
			user.setIntro(intro);
			user.setPassword(password);
			user.setAuth(roles);
			user.setType(type);
			cmsAdminSer.addUser(user);
			resultMsg.setOk(true);
			resultMsg.setMsg("保存成功");
		} catch (Exception e) {
			logger.debug(" doAddUser execption:"+e);
			resultMsg.setMsg("保存失败");
		}
		
		return JSON.toJSONString(resultMsg);
	}
	@RequestMapping("checkName")
	@ResponseBody
	public String checkName(HttpServletRequest request,String username){
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setOk(false);
		try {
			if(username!=null){
			 CmsAdmin admin= cmsAdminSer.getByName(username);
			 if(admin!=null&&admin.getName()!=null)
				 resultMsg.setOk(true);
			}
			resultMsg.setMsg("用户已存在");
		} catch (Exception e) {
			logger.debug(" checkName exception:"+e);
			resultMsg.setMsg("查询失败");
		}
		
		return JSON.toJSONString(resultMsg);
	}

	/**
	 * 更新用户
	 **/
	@RequiresRoles("admin")
	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	public String showUpdateForm(HttpServletRequest request,@PathVariable("id") Integer id, Model model) {
		CmsAdmin cmsAdmin= cmsAdminSer.getById(id);
		CmsAdminDto cmsAdminDto=AdminConvert.toAdminDto(cmsAdmin);
		if(cmsAdminDto!=null){
			String type= TransToPage.mapToOptions(true, cmsAdminDto.getType(),CmsAdminType.AdminTypeMap);
			request.setAttribute("type", type);
		}
		model.addAttribute("cmsAdmin", cmsAdminDto);
		return "admin/edit";
	}

	/**
	 * 修改用户
	 **/
	@RequiresRoles("admin")
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String doUpdate(CmsAdmin user, RedirectAttributes redirectAttributes) {
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setOk(false);
		try {
			CmsAdmin CmsAdmin = cmsAdminSer.getById(user.getId());
			CmsAdmin.setAuth(user.getAuth());
			CmsAdmin.setIntro(user.getIntro());
			CmsAdmin.setType(user.getType());
			cmsAdminSer.update(CmsAdmin);
			resultMsg.setOk(true);
			resultMsg.setMsg("修改成功");
		} catch (Exception e) {
			logger.debug("doUpdate exce:"+e);
			resultMsg.setMsg("保存失败");
		}
		return JSON.toJSONString(resultMsg);
	}

	/**
	 * 删除用户
	 **/
	@RequiresRoles("admin")
	@RequestMapping(value = "/{id}/del", method = RequestMethod.GET)
	public String showDelUser(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("user", cmsAdminSer.getById(id));
		model.addAttribute("op", "删除");

		return "admin/edit";
	}

	/**
	 * 删除操作
	 **/
	@RequiresRoles("admin")
	@RequestMapping(value = "/{id}/del", method = RequestMethod.POST)
	@ResponseBody
	public String doDelUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		//cmsAdminSer.del(id);
		//redirectAttributes.addFlashAttribute("msg", "删除成功");
		//return "redirect:/admin/list";
		JSONObject jObject=new JSONObject();
		try {
			cmsAdminSer.del(id);
			jObject.put("ok",true);
			jObject.put("msg", "删除成功");
		} catch (Exception e) {
			logger.error("error:"+e.getMessage());
			jObject.put("ok",false);
			jObject.put("msg","删除失败");
		}
		return jObject.toJSONString();
	}

	/**
	 * 修改密码
	 **/
	@RequiresRoles("admin")
	@RequestMapping(value = "/changepassword", method = RequestMethod.GET)
	public String showChangePassword(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("user", cmsAdminSer.getById(id));
		model.addAttribute("op", "修改密码");
		return "admin/changePassword";
	}

	/**
	 * 修改密码-操作
	 **/
	@RequiresRoles("admin")
	@RequestMapping(value = "/{id}/changepassword", method = RequestMethod.POST)
	public String doChangePassword(@PathVariable("id") Integer id, String newPassword,
			RedirectAttributes redirectAttributes) {
		cmsAdminSer.changePassword(id, newPassword);
		redirectAttributes.addFlashAttribute("msg", "密码修改成功");
		return "redirect:/admin/list";
	}


	
	/**
	 * 用户修改自己的密码
	 **/
	@RequestMapping(value = "/editPassword", method = RequestMethod.GET)
	public String changepassword(HttpServletRequest request) {
		CmsAdmin cmsAdmin=CommonUtil.getCmsAdmin();
		if (cmsAdmin == null || cmsAdmin.getId()==null) {
			return "redirect:/login.jsp";
		}
		
		request.setAttribute("admin", cmsAdmin);
		return "admin/editPassword";
	}

	/**
	 * 修改密码-操作
	 **/
	@RequestMapping(value = "/savepassword", method = RequestMethod.POST)
	@ResponseBody
	public String savePassword(HttpServletRequest request) {
		ResultMsg resultMsg = new ResultMsg();
		String password=request.getParameter("password");
		
		try {
			// todo:权限判断	
			Integer id = 0;
			id=CommonUtil.getId();
			if (id == null || id == 0) {
				return "redirect:/login.jsp";
			}
			if(password!=null){
				//password= Md5Utils.ToBit32(password,McpConstants.Mcp_Md5_Key);
				//加密方法统一
			    cmsAdminSer.changePassword(id,password);
				resultMsg.setOk(true);
				resultMsg.setMsg("保存成功");

			}
		} catch (Exception e) {
			logger.debug("savePassword execption:"+e);
			resultMsg.setOk(false);
			resultMsg.setMsg("保存失败");
		}
		return JSON.toJSONString(resultMsg);
	}
	
	/**
	 * 验证密码是否正确
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("check")
	@ResponseBody
	public String checkPassword(HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject jObject=new JSONObject();
		String oldpassword=request.getParameter("oldpassword");
		String md5password=request.getParameter("md5password");
		if(oldpassword!=null&&md5password!=null){
			oldpassword=passwordHelper.encryptPassword(oldpassword);;
			if(oldpassword.equals(md5password.toLowerCase())){
				jObject.put("ok", true);
			}
			else{
				jObject.put("ok", false);
			}
		}
		return jObject.toString();
	}
	
	
	/**
	 * 登出
	 * 
	 * @param userName
	 * @param model
	 * @return
	 */
	@RequestMapping("loginOut")
	public String logout(Model model) {
		try {
			Subject subject = SecurityUtils.getSubject();
			subject.logout();
			return "redirect:/login.jsp";
		} catch (Exception e) {
			logger.debug("loginOut execption :"+e);
		}
		return "redirect:/login.jsp";
	
	}


}
