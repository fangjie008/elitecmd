package com.tiexue.cms.manage.entity;

public class ImgFormat {


	/**
	 *  全参构造函数
	 * @param id
	 * @param imgFormatType
	 * @param width
	 * @param high
	 */
    public ImgFormat(int id, int imgFormatType, int width, int high)
    {
        this.id = id;
        this.formatType = imgFormatType;
        this.width = width;
        this.high = high;
    }
    /**
     * 编号
     */
    public int id;
    /**
     * 图片类型
     */
    public int formatType; 
    /**
     * 图片宽度 0:不限制宽度
     */
    public int width; 
    /**
     * 图片高度 0:不限制高度
     */
    public int high;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFormatType() {
		return formatType;
	}
	public void setFormatType(int formatType) {
		this.formatType = formatType;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHigh() {
		return high;
	}
	public void setHigh(int high) {
		this.high = high;
	} 
    
}
