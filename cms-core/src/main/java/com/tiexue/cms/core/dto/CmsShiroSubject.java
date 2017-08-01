package com.tiexue.cms.core.dto;

import com.tiexue.cms.core.entity.CmsAdmin;


public class CmsShiroSubject {
	

	//管理员
	private CmsAdmin CmsAdmin;

	//用户名
	private String userName;
	//密码
	private String password;
	
	

	
	public CmsAdmin getCmsAdmin()
	{
		return CmsAdmin;
	}
	
	public void setCmsAdmin(CmsAdmin value)
	{
		CmsAdmin = value;
		if (value !=null) {
			this.userName = value.getName();
			this.password = value.getPassword();
		}
	}

	
	public String getUserName()
	{
		return this.userName;
	}
	public void setUserName(String value)
	{
		this.userName = value;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	public void setPassword(String value)
	{
		this.password = value;
	}
}
