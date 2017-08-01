package com.tiexue.cms.manage.define;

import java.util.HashMap;
import java.util.Map;

public class ImgConstants {


	/**
	 * 固定宽高
	 */
	public static final int ImgFormatType_Spec=1;
	/**
	 * 指定高度,宽度同比例
	 */
	public static final int ImgFormatType_ScaleWith=2;
	/**
	 * 指定宽度，高度同比例
	 */
	public static final int ImgFormatType_ScaleHigh=3;
	/**
	 * 指定宽高
	 */
	public static final int ImgFormatType_Assign=4;
	/**
	 * 图片格式化类型
	 */
	public static Map<Integer, String> ImgFormatType=new HashMap<Integer,String>(){
		{
			put(ImgFormatType_Spec, "固定宽高");
			put(ImgFormatType_ScaleWith, "指定高度,宽度同比例");
			put(ImgFormatType_ScaleHigh, "指定宽度，高度同比例");
			put(ImgFormatType_Assign, "指定宽高");
		}
	};
}
