package com.tiexue.cms.core.dto;

public class CmsAdminDto {

	 private Integer id;

	    private String name;

	    private String password;

	    private String intro;

	    private Integer type;

	    private String auth;
	    
	    private String typename;

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
	        this.name = name == null ? null : name.trim();
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password == null ? null : password.trim();
	    }

	    public String getIntro() {
	        return intro;
	    }

	    public void setIntro(String intro) {
	        this.intro = intro == null ? null : intro.trim();
	    }

	    public Integer getType() {
	        return type;
	    }

	    public void setType(Integer type) {
	        this.type = type;
	    }

	    public String getAuth() {
	        return auth;
	    }

	    public void setAuth(String auth) {
	        this.auth = auth == null ? null : auth.trim();
	    }

		public String getTypename() {
			return typename;
		}

		public void setTypename(String typename) {
			this.typename = typename;
		}
	    
	    
}
