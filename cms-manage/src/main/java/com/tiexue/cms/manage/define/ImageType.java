package com.tiexue.cms.manage.define;

import java.util.HashMap;
import java.util.Map;

public class ImageType {


	/**
	 * 封面小图140*90
	 */
	public static final int imageType_Small=0;
	/**
	 * 封面大图500*x
	 */
	public static final int imageType_Big=1;
	
	/**
	 * 封面图类型
	 */
	public static Map<Integer, String>  typeMap=new HashMap<Integer, String>(){
		{
			put(imageType_Small,"封面小图140*90");
			put(imageType_Big, "封面大图500*x");
		}
	};
	
}
