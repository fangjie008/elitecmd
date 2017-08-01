package com.tiexue.cms.core.dto;

import java.util.Date;

public class CmsCategoryDto {

	  private Integer id;

	    private String name;

	    private String coverimg;

	    private Integer status;

	    private Integer type;

	    private String intro;

	    private Integer weight;

	    private String tags;

	    private Integer parentid;

	    private String createtime;

	    private String remark;
	    
	    private String statusname;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCoverimg() {
			return coverimg;
		}

		public void setCoverimg(String coverimg) {
			this.coverimg = coverimg;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public String getIntro() {
			return intro;
		}

		public void setIntro(String intro) {
			this.intro = intro;
		}

		public Integer getWeight() {
			return weight;
		}

		public void setWeight(Integer weight) {
			this.weight = weight;
		}

		public String getTags() {
			return tags;
		}

		public void setTags(String tags) {
			this.tags = tags;
		}

		public Integer getParentid() {
			return parentid;
		}

		public void setParentid(Integer parentid) {
			this.parentid = parentid;
		}

		public String getCreatetime() {
			return createtime;
		}

		public void setCreatetime(String createtime) {
			this.createtime = createtime;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getStatusname() {
			return statusname;
		}

		public void setStatusname(String statusname) {
			this.statusname = statusname;
		}
	    
	    
}
