package com.tiexue.cms.core.service;

import java.util.List;
import java.util.Set;

import com.tiexue.cms.core.entity.CmsAdmin;


public interface ICmsAdminService {
	/**
	 * 按登录名查询用户(配置文件)
	 * 
	 * @param username
	 * @return 用户对象
	 */
	public CmsAdmin getByName(String username);

	/**
	 * 根据id加载数据
	 * 
	 * @param id
	 * @return
	 */
	public CmsAdmin getById(Integer id);
	
	/**
	 * 查询列表数据
	 * **/
	public List<CmsAdmin>getList();
	
	/**
	 * 保存用户信息
	 * 
	 * @param userDO
	 */
	public void addUser(CmsAdmin user);
	
	/**
	 * 更新用户信息
	 * **/
	public void update(CmsAdmin user);

	/**
	 * 删除用户
	 * 
	 * @param id
	 */
	public void del(Integer id);

	
	/**
	 * 修改密码
	 * **/
	public void changePassword(Integer userId, String newPassword);
	
	
	/**
	 * 验证密码.
	 * 
	 * @param user
	 * @param oldPassword
	 * @return
	 */
	public boolean checkPassword(CmsAdmin user, String oldPassword);

	/**
	 * 角色查找设置,直接使用说明
	 * */
	public Set<String> findRoles(String username);
}
