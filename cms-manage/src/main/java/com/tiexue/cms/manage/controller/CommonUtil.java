package com.tiexue.cms.manage.controller;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.tiexue.cms.core.dto.CmsShiroSubject;
import com.tiexue.cms.core.entity.CmsAdmin;

public class CommonUtil {

	//日志
	private static Logger logger=Logger.getLogger(CommonUtil.class);
	/**
	 *返回登录后的ID
	 * @return
	 */
	public static Integer getId(){
		try {
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			CmsShiroSubject subject = (CmsShiroSubject)session.getAttribute("user");
			if(subject==null){
				return 0;
			}
			return subject.getCmsAdmin().getId();
		} catch (Exception e) {
			logger.debug("获取登录人信息出错；出错信息："+e.getMessage());
			return 0;
		}
	}
	
	/**
	 * 返回登录后保存的登录信息
	 * @return
	 */
	public static CmsShiroSubject getCmsShiroSubject(){
		Integer cpId = 0;
		try {
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			CmsShiroSubject subject = (CmsShiroSubject)session.getAttribute("user");
			if(subject==null){
				return null;
			}
			return subject;
		} catch (Exception e) {
			logger.debug("获取登录人信息出错；出错信息："+e.getMessage());
			return null;
		}
		
		
	}
	
	/**
	 * 返回登录后保存的用户信息
	 * @return
	 */
	public static CmsAdmin getCmsAdmin(){
		try {
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			CmsShiroSubject subject = (CmsShiroSubject)session.getAttribute("user");
			if(subject==null){
				return null;
			}
			return subject.getCmsAdmin();
		} catch (Exception e) {
			logger.debug("获取登录人信息出错；出错信息："+e.getMessage());
			return null;
		}
		
		
	}
	
}
