package com.tiexue.cms.wap.dto;

import java.util.List;

/**
 * 返回到页面的对象
 * @author zhangxiaowei
 *
 */
public class ApiContent {

	private Integer id;

	private String title;

	private String intro;

	private String coverimgs;

	private Integer imgshowtype;

	private Integer contenttype;

	private Integer viewcount;

	private Integer dingcount;

	private Integer caicount;
	
	private String showtime;
	
	public String getShowtime() {
		return showtime;
	}
	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}
	private String createtime;

	private Integer status;

	private String originalurl;

	private String fromname;

    private String tags;

	private Integer contentlen;

	private Integer categoryid;

    private String contentpic;
    private String materials;
    private String originalcontent;
    private String categoryname;
	//封面图数组
	private List<String> coverimglist;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getCoverimgs() {
		return coverimgs;
	}
	public void setCoverimgs(String coverimgs) {
		this.coverimgs = coverimgs;
	}
	public Integer getImgshowtype() {
		return imgshowtype;
	}
	public void setImgshowtype(Integer imgshowtype) {
		this.imgshowtype = imgshowtype;
	}
	public Integer getContenttype() {
		return contenttype;
	}
	public void setContenttype(Integer contenttype) {
		this.contenttype = contenttype;
	}
	public Integer getViewcount() {
		return viewcount;
	}
	public void setViewcount(Integer viewcount) {
		this.viewcount = viewcount;
	}
	public Integer getDingcount() {
		return dingcount;
	}
	public void setDingcount(Integer dingcount) {
		this.dingcount = dingcount;
	}
	public Integer getCaicount() {
		return caicount;
	}
	public void setCaicount(Integer caicount) {
		this.caicount = caicount;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getOriginalurl() {
		return originalurl;
	}
	public void setOriginalurl(String originalurl) {
		this.originalurl = originalurl;
	}
	public String getFromname() {
		return fromname;
	}
	public void setFromname(String fromname) {
		this.fromname = fromname;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public Integer getContentlen() {
		return contentlen;
	}
	public void setContentlen(Integer contentlen) {
		this.contentlen = contentlen;
	}
	public Integer getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}

	public String getMaterials() {
		return materials;
	}
	public void setMaterials(String materials) {
		this.materials = materials;
	}

	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	public String getContentpic() {
		return contentpic;
	}
	public void setContentpic(String contentpic) {
		this.contentpic = contentpic;
	}
	public String getOriginalcontent() {
		return originalcontent;
	}
	public void setOriginalcontent(String originalcontent) {
		this.originalcontent = originalcontent;
	}
	public List<String> getCoverimglist() {
		return coverimglist;
	}
	public void setCoverimglist(List<String> coverimglist) {
		this.coverimglist = coverimglist;
	}
	
	


}
