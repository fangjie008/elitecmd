package com.tiexue.cms.core.entity;

import java.util.Date;

public class CmsComment {
    private Integer id;

    private Integer articleid;

    private String content;

    private String imgs;

    private Integer publisherid;

    private String publishername;

    private String publishericon;

    private Integer toptargetcommentid;

    private Integer targetcommentid;

    private Integer targetuserid;

    private String targetusername;

    private Integer dingcount;

    private Integer caicount;

    private Integer commentcount;

    private Integer status;

    private Date showtime;

    private Date createtime;

    private Integer mark;

    private Integer weight;

    private String flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs == null ? null : imgs.trim();
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

    public Integer getToptargetcommentid() {
        return toptargetcommentid;
    }

    public void setToptargetcommentid(Integer toptargetcommentid) {
        this.toptargetcommentid = toptargetcommentid;
    }

    public Integer getTargetcommentid() {
        return targetcommentid;
    }

    public void setTargetcommentid(Integer targetcommentid) {
        this.targetcommentid = targetcommentid;
    }

    public Integer getTargetuserid() {
        return targetuserid;
    }

    public void setTargetuserid(Integer targetuserid) {
        this.targetuserid = targetuserid;
    }

    public String getTargetusername() {
        return targetusername;
    }

    public void setTargetusername(String targetusername) {
        this.targetusername = targetusername == null ? null : targetusername.trim();
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }
}