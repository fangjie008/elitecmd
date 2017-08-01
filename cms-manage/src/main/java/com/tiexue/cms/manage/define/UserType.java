package com.tiexue.cms.manage.define;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户类型
 * @author zhangxiaowei
 *
 */
public class UserType {

	public static final int UserType_Manage=1;
	public static final int UserType_User=2;
	
	public Map<Integer,String> typeMap=new HashMap<Integer,String>(){
		{
			put(UserType_Manage,"管理员");
			put(UserType_User,"权限用户");
		}
	};
	/**
	 * 获取用户类型的值
	 * @param key
	 * @return
	 */
	public String getValue(int key){
		return typeMap.get(key);
	}
}
