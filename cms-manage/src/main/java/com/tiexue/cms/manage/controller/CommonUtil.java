package com.tiexue.cms.manage.controller;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.tiexue.cms.core.dto.CmsShiroSubject;

public class CommonUtil {

	//日志
	private static Logger logger=Logger.getLogger(CommonUtil.class);
	/**
	 * 如果登录用户是合作者返回合作者的Id(CPId)
	 * @return
	 */
	public static Integer getCpId(){
		Integer cpId = 0;
		try {
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			CmsShiroSubject subject = (CmsShiroSubject)session.getAttribute("user");
			if(subject==null){
				return 0;
			}
			
		} catch (Exception e) {
			logger.debug("获取登录人信息出错；出错信息："+e.getMessage());
			return cpId;
		}
		
		return cpId;
	}
	
	/**
	 * 如果登录用户是合作者返回合作者的Id(CPId)
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
	
}
