package com.tiexue.cms.manage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.tiexue.cms.core.entity.CmsAdmin;
import com.tiexue.cms.core.service.ICmsAdminService;

@Controller
@RequestMapping("admin")
public class CmsAdminController {

	@Resource
	ICmsAdminService cmsAdminSer;
	
	Logger logger=Logger.getLogger(CmsAdminController.class);
	
	/**
	 * 查询用户列表
	 **/
	// @RequiresRoles("admin")
	@RequestMapping("list")
	public String getList(Model model) {
		model.addAttribute("userList", cmsAdminSer.getList());
		return "admin/list";
	}

	/**
	 * 添加用户
	 **/
	// @RequiresRoles("admin")
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String showAddUser() {
		return "admin/add";
	}

	/**
	 * 创建用户逻辑
	 **/
	// @RequiresRoles("admin")
	@RequestMapping(value = "doAddUser", method = RequestMethod.POST)
	public String doAddUser(String username, String password, String intro, String roles,
			RedirectAttributes redirectAttributes) {
		CmsAdmin user = new CmsAdmin();
		user.setName(username);
		user.setIntro(intro);
		user.setPassword(password);
		user.setAuth(roles);

		cmsAdminSer.addUser(user);

		redirectAttributes.addAttribute("msg", "新增成功");
		return "redirect:/admin/list";
	}

	/**
	 * 更新用户
	 **/
	@RequiresRoles("admin")
	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("CmsAdmin", cmsAdminSer.getById(id));
		return "admin/edit";
	}

	/**
	 * 修改用户
	 **/
	@RequiresRoles("admin")
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String doUpdate(CmsAdmin user, RedirectAttributes redirectAttributes) {
		JSONObject jObject = new JSONObject();
		try {
			CmsAdmin CmsAdmin = cmsAdminSer.getById(user.getId());
			CmsAdmin.setAuth(user.getAuth());
			CmsAdmin.setIntro(user.getIntro());
			cmsAdminSer.update(CmsAdmin);
			jObject.put("ok",true);
			jObject.put("msg", "修改成功");
			// redirectAttributes.addAttribute("msg", "修改成功");
			// return "redirect:/admin/list";
		} catch (Exception e) {
			// TODO: handle exception
			jObject.put("ok",false);
			jObject.put("msg","保存失败");
		}
		return jObject.toString();
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
	@RequestMapping(value = "/{id}/changepassword", method = RequestMethod.GET)
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
	 * 查询单个用户
	 **/
	@RequestMapping("get")
	public String getUser(HttpServletRequest req, Integer userId) {
		// todo:查询用户
		// 赋值给request
		return "admin/get";
	}

	/**
	 * 修改用户密码
	 **/
	@RequestMapping("changepassword")
	public String changePwd(HttpServletRequest req) {
		// todo:查询当前登录用户
		// todo:执行修改密码操作
		return "admin/changePassword";
	}

}
