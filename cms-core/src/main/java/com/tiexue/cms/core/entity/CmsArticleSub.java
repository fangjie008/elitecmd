package com.tiexue.cms.core.entity;

public class CmsArticleSub {
    private Integer id;

    private String originalcontent;

    private String contentpic;

    private String materials;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalcontent() {
        return originalcontent;
    }

    public void setOriginalcontent(String originalcontent) {
        this.originalcontent = originalcontent == null ? null : originalcontent.trim();
    }

    public String getContentpic() {
        return contentpic;
    }

    public void setContentpic(String contentpic) {
        this.contentpic = contentpic == null ? null : contentpic.trim();
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials == null ? null : materials.trim();
    }
}