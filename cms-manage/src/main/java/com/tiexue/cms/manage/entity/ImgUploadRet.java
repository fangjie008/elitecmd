package com.tiexue.cms.manage.entity;

import java.util.List;

public class ImgUploadRet {

	private int ret;
	private String msg;
	private List<ImgUploadRetUrl> urls;
	private int width;
	private int height;

	public void setRet(int ret) {
		this.ret = ret;
	}

	public int getRet() {
		return ret;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setUrls(List<ImgUploadRetUrl> urls) {
		this.urls = urls;
	}

	public List<ImgUploadRetUrl> getUrls() {
		return urls;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getWidth() {
		return width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHeight() {
		return height;
	}

	/**
	 * 获取返回的url地址
	 * @return
	 */
	public String getImgUrl(int num) {
		if (urls == null || urls.isEmpty() || urls.size() <= 0)
			return "";
		if(num<=urls.size())
			return urls.get(num).getValue().toString();
		return urls.get(0).getValue().toString();
	}
	/**
	 * 上传图片是否成功
	 * @return
	 */
	public boolean isSuc(){
	    return this.ret==0?true:false;
	}
}
