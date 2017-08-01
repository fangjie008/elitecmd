package com.tiexue.cms.manage.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tiexue.cms.core.entity.CmsCategory;

public class TransToPage {

	/**
	 * 转化成下拉框options
	 * @param isDefault
	 * @param key
	 * @param maps
	 * @return
	 */
	public static String mapToOptions(boolean isDefault,int key,Map<Integer,String> maps){
		String result="";
		if(isDefault){
			result += " <option value=''></option>";
		}
		for (Map.Entry<Integer,String> map : maps.entrySet()) {
			result += String.format(" <option value='%s' %s >%s</option>", map.getKey(), map.getKey() == key ? "selected=selected" : "", map.getValue());
		}
		return result;
	}
	
	/**
	 * 转化成下拉框options
	 * @param isDefault
	 * @param key
	 * @param maps
	 * @return
	 */
	public static String categoryToOptions(boolean isDefault,int key,List<CmsCategory> list){
		String result="";
		if(isDefault){
			result += " <option value=''></option>";
		}
		for (CmsCategory cmsCategory : list) {
			result += String.format(" <option value='%s' %s >%s</option>", cmsCategory.getId(), cmsCategory.getId() == key ? "selected=selected" : "", cmsCategory.getName());
		}

		return result;
	}
}
