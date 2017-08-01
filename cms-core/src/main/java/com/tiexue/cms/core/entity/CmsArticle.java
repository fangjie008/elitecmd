package com.tiexue.cms.core.entity;

import java.util.Date;

public class CmsArticle {
	private Integer id;

	private String title;

	private String intro;

	private String coverimgs;

	private Integer imgshowtype;

	private Integer contenttype;

	private Integer publisherid;

	private String publishername;

	private String publishericon;

	private Integer viewcount;

	private Integer dingcount;

	private Integer caicount;

	private Integer commentcount;

	private Integer sharecount;

	private Integer collectioncount;

	private Date showtime;

	private Date createtime;

	private Integer mark;

	private Integer status;

	private Integer sourcetype;

	private Integer weight;

	private String originalid;

	private String originaltitle;

	private String originalurl;

	private Integer fromid;

	private String fromname;

	private Integer platformid;

	private String platformname;

	private String tags;

	private Integer contentlen;

	private Integer categoryid;

	private Integer uscore;

	private String uniqueflag;

    private String ContentPic;
    private String materials;
    private String originalContent;
    private String categoryName;

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
		this.title = title == null ? null : title.trim();
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro == null ? null : intro.trim();
	}

	public String getCoverimgs() {
		return coverimgs;
	}

	public void setCoverimgs(String coverimgs) {
		this.coverimgs = coverimgs == null ? null : coverimgs.trim();
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

	public Integer getPublisherid() {
		return publisherid;
	}

	public void setPublisherid(Integer publisherid) {
		this.publisherid = publisherid;
	}

	public String getPublishername() {
		return publishername;
	}

	public void setPublishername(String publishername) {
		this.publishername = publishername == null ? null : publishername.trim();
	}

	public String getPublishericon() {
		return publishericon;
	}

	public void setPublishericon(String publishericon) {
		this.publishericon = publishericon == null ? null : publishericon.trim();
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

	public Integer getCommentcount() {
		return commentcount;
	}

	public void setCommentcount(Integer commentcount) {
		this.commentcount = commentcount;
	}

	public Integer getSharecount() {
		return sharecount;
	}

	public void setSharecount(Integer sharecount) {
		this.sharecount = sharecount;
	}

	public Integer getCollectioncount() {
		return collectioncount;
	}

	public void setCollectioncount(Integer collectioncount) {
		this.collectioncount = collectioncount;
	}

	public Date getShowtime() {
		return showtime;
	}

	public void setShowtime(Date showtime) {
		this.showtime = showtime;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSourcetype() {
		return sourcetype;
	}

	public void setSourcetype(Integer sourcetype) {
		this.sourcetype = sourcetype;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getOriginalid() {
		return originalid;
	}

	public void setOriginalid(String originalid) {
		this.originalid = originalid == null ? null : originalid.trim();
	}

	public String getOriginaltitle() {
		return originaltitle;
	}

	public void setOriginaltitle(String originaltitle) {
		this.originaltitle = originaltitle == null ? null : originaltitle.trim();
	}

	public String getOriginalurl() {
		return originalurl;
	}

	public void setOriginalurl(String originalurl) {
		this.originalurl = originalurl == null ? null : originalurl.trim();
	}

	public Integer getFromid() {
		return fromid;
	}

	public void setFromid(Integer fromid) {
		this.fromid = fromid;
	}

	public String getFromname() {
		return fromname;
	}

	public void setFromname(String fromname) {
		this.fromname = fromname == null ? null : fromname.trim();
	}

	public Integer getPlatformid() {
		return platformid;
	}

	public void setPlatformid(Integer platformid) {
		this.platformid = platformid;
	}

	public String getPlatformname() {
		return platformname;
	}

	public void setPlatformname(String platformname) {
		this.platformname = platformname == null ? null : platformname.trim();
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags == null ? null : tags.trim();
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

	public Integer getUscore() {
		return uscore;
	}

	public void setUscore(Integer uscore) {
		this.uscore = uscore;
	}

	public String getUniqueflag() {
		return uniqueflag;
	}

	public void setUniqueflag(String uniqueflag) {
		this.uniqueflag = uniqueflag == null ? null : uniqueflag.trim();
	}

	public String getContentPic() {
		return ContentPic;
	}

	public void setContentPic(String contentPic) {
		ContentPic = contentPic;
	}

	public String getMaterials() {
		return materials;
	}

	public void setMaterials(String materials) {
		this.materials = materials;
	}

	public String getOriginalContent() {
		return originalContent;
	}

	public void setOriginalContent(String originalContent) {
		this.originalContent = originalContent;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}



}