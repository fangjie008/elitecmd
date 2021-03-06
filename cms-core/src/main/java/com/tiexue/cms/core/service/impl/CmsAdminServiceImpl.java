package com.tiexue.cms.core.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiexue.cms.core.entity.CmsAdmin;
import com.tiexue.cms.core.mapper.CmsAdminMapper;
import com.tiexue.cms.core.service.ICmsAdminService;
import com.tiexue.cms.core.shiro.PasswordHelper;

@Service("CmsAdminSer")
public class CmsAdminServiceImpl implements ICmsAdminService {

	// logger
	private static final Logger logger = LoggerFactory.getLogger(CmsAdminServiceImpl.class);

	@Resource
	private CmsAdminMapper userDao;

	@Autowired
    private PasswordHelper passwordHelper;
	
	
	/**
	 * 根据用户名称查询用户数据
	 **/
	@Override
	public CmsAdmin getByName(String username) {
		return userDao.selectByName(username);
	}

	@Override
	public CmsAdmin getById(Integer id) {
		return userDao.selectByPrimaryKey(id);
	}

	@Override
	public boolean checkPassword(CmsAdmin user, String oldPassword) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 用户对应的角色列表设置
	 */
	@Override
	public Set<String> findRoles(String username) {
		// TODO Auto-generated method stub
		CmsAdmin userAdmin = getByName(username);
		Set<String> roles = new HashSet<String>();
		if (userAdmin != null) {
			String[] roleT = userAdmin.getAuth().split(",");
			if (roleT != null && roleT.length > 0) {
				for (int i = 0; i < roleT.length; i++) {
					roles.add(roleT[i]);
				}
			}
		}
		return roles;
	}

	/**
	 * 查询列表数据
	 **/
	@Override
	public java.util.List<CmsAdmin> getList() {
		return userDao.selectList();
	}

	/**
	 * 添加数据
	 **/
	@Override
	public void addUser(CmsAdmin user) {
		//处理密码逻辑--按照指定逻辑进行加密操作
        passwordHelper.encryptPassword(user);
		userDao.insertSelective(user);
	}

	/**
	 * 更新数据
	 **/
	@Override
	public void update(CmsAdmin user) {
		userDao.updateByPrimaryKey(user);
	}

	/**
	 * 删除用户
	 **/
	@Override
	public void del(Integer id) {
		userDao.deleteByPrimaryKey(id);
	}

	/**
	 * 修改密码
	 **/
	@Override
	public void changePassword(Integer userId, String newPassword) {
		CmsAdmin user = getById(userId);
		user.setPassword(newPassword);
		passwordHelper.encryptPassword(user);
		update(user);
	}

}
