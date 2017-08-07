package com.tiexue.cms.core.cache;

public class CacheKey {

	private static String wap_list="wap_list_%s_%s";
	/**
	 * wap站list缓存
	 * @param categoryIds
	 * @param page
	 * @return
	 */
	public static String getWap_list(String categoryIds,int page){
		return String.format(wap_list, categoryIds,page);
	}
	
	private static String wap_detail="wap_detail_%s";
	/**
	 * wap站detail缓存
	 * @param id
	 * @return
	 */
	public static String getWap_detail(int id){
		return String.format(wap_detail,id);
	}
}
