package com.tiexue.cms.core.mapper;

import com.tiexue.cms.core.entity.CmsArticle;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CmsArticleMapper {
    @Delete({
        "delete from CmsArticle",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into CmsArticle (Id, Title, ",
        "Intro, CoverImgs, ",
        "ImgShowType, ContentType, ",
        "PublisherId, PublisherName, ",
        "PublisherIcon, ViewCount, ",
        "DingCount, CaiCount, ",
        "CommentCount, ShareCount, ",
        "CollectionCount, ShowTime, ",
        "CreateTime, Mark, ",
        "Status, SourceType, ",
        "Weight, OriginalId, ",
        "OriginalTitle, OriginalUrl, ",
        "FromId, FromName, ",
        "PlatformId, PlatformName, ",
        "Tags, ContentLen, ",
        "CategoryId, Uscore, ",
        "UniqueFlag)",
        "values (#{id,jdbcType=INTEGER}, #{title,jdbcType=VARCHAR}, ",
        "#{intro,jdbcType=VARCHAR}, #{coverimgs,jdbcType=VARCHAR}, ",
        "#{imgshowtype,jdbcType=INTEGER}, #{contenttype,jdbcType=INTEGER}, ",
        "#{publisherid,jdbcType=INTEGER}, #{publishername,jdbcType=VARCHAR}, ",
        "#{publishericon,jdbcType=VARCHAR}, #{viewcount,jdbcType=INTEGER}, ",
        "#{dingcount,jdbcType=INTEGER}, #{caicount,jdbcType=INTEGER}, ",
        "#{commentcount,jdbcType=INTEGER}, #{sharecount,jdbcType=INTEGER}, ",
        "#{collectioncount,jdbcType=INTEGER}, #{showtime,jdbcType=TIMESTAMP}, ",
        "#{createtime,jdbcType=TIMESTAMP}, #{mark,jdbcType=INTEGER}, ",
        "#{status,jdbcType=INTEGER}, #{sourcetype,jdbcType=INTEGER}, ",
        "#{weight,jdbcType=INTEGER}, #{originalid,jdbcType=VARCHAR}, ",
        "#{originaltitle,jdbcType=VARCHAR}, #{originalurl,jdbcType=VARCHAR}, ",
        "#{fromid,jdbcType=INTEGER}, #{fromname,jdbcType=VARCHAR}, ",
        "#{platformid,jdbcType=INTEGER}, #{platformname,jdbcType=VARCHAR}, ",
        "#{tags,jdbcType=VARCHAR}, #{contentlen,jdbcType=INTEGER}, ",
        "#{categoryid,jdbcType=INTEGER}, #{uscore,jdbcType=INTEGER}, ",
        "#{uniqueflag,jdbcType=VARCHAR})"
    })
    int insert(CmsArticle record);

    int insertSelective(CmsArticle record);

    @Select({
        "select",
        "Id, Title, Intro, CoverImgs, ImgShowType, ContentType, PublisherId, PublisherName, ",
        "PublisherIcon, ViewCount, DingCount, CaiCount, CommentCount, ShareCount, CollectionCount, ",
        "ShowTime, CreateTime, Mark, Status, SourceType, Weight, OriginalId, OriginalTitle, ",
        "OriginalUrl, FromId, FromName, PlatformId, PlatformName, Tags, ContentLen, CategoryId, ",
        "Uscore, UniqueFlag",
        "from CmsArticle",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    CmsArticle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsArticle record);

    @Update({
        "update CmsArticle",
        "set Title = #{title,jdbcType=VARCHAR},",
          "Intro = #{intro,jdbcType=VARCHAR},",
          "CoverImgs = #{coverimgs,jdbcType=VARCHAR},",
          "ImgShowType = #{imgshowtype,jdbcType=INTEGER},",
          "ContentType = #{contenttype,jdbcType=INTEGER},",
          "PublisherId = #{publisherid,jdbcType=INTEGER},",
          "PublisherName = #{publishername,jdbcType=VARCHAR},",
          "PublisherIcon = #{publishericon,jdbcType=VARCHAR},",
          "ViewCount = #{viewcount,jdbcType=INTEGER},",
          "DingCount = #{dingcount,jdbcType=INTEGER},",
          "CaiCount = #{caicount,jdbcType=INTEGER},",
          "CommentCount = #{commentcount,jdbcType=INTEGER},",
          "ShareCount = #{sharecount,jdbcType=INTEGER},",
          "CollectionCount = #{collectioncount,jdbcType=INTEGER},",
          "ShowTime = #{showtime,jdbcType=TIMESTAMP},",
          "CreateTime = #{createtime,jdbcType=TIMESTAMP},",
          "Mark = #{mark,jdbcType=INTEGER},",
          "Status = #{status,jdbcType=INTEGER},",
          "SourceType = #{sourcetype,jdbcType=INTEGER},",
          "Weight = #{weight,jdbcType=INTEGER},",
          "OriginalId = #{originalid,jdbcType=VARCHAR},",
          "OriginalTitle = #{originaltitle,jdbcType=VARCHAR},",
          "OriginalUrl = #{originalurl,jdbcType=VARCHAR},",
          "FromId = #{fromid,jdbcType=INTEGER},",
          "FromName = #{fromname,jdbcType=VARCHAR},",
          "PlatformId = #{platformid,jdbcType=INTEGER},",
          "PlatformName = #{platformname,jdbcType=VARCHAR},",
          "Tags = #{tags,jdbcType=VARCHAR},",
          "ContentLen = #{contentlen,jdbcType=INTEGER},",
          "CategoryId = #{categoryid,jdbcType=INTEGER},",
          "Uscore = #{uscore,jdbcType=INTEGER},",
          "UniqueFlag = #{uniqueflag,jdbcType=VARCHAR}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CmsArticle record);
    
    @Select({
        "select",
        "Id, Title, Intro, CoverImgs, ImgShowType, ContentType, PublisherId, PublisherName, ",
        "PublisherIcon, ViewCount, DingCount, CaiCount, CommentCount, ShareCount, CollectionCount, ",
        "ShowTime, CreateTime, Mark, Status, SourceType, Weight, OriginalId, OriginalTitle, ",
        "OriginalUrl, FromId, FromName, PlatformId, PlatformName, Tags, ContentLen, CategoryId, ",
        "Uscore, UniqueFlag",
        "from CmsArticle",
        "where UniqueFlag = #{uniqueFlag} limit 0,1"
    })
    @ResultMap("BaseResultMap")
    CmsArticle getModelByUniqueFlag(@Param("uniqueFlag")String uniqueFlag);
    
    
    @Select({
        "select",
        "Id, Title, Intro, CoverImgs, ImgShowType, ContentType, PublisherId, PublisherName, ",
        "PublisherIcon, ViewCount, DingCount, CaiCount, CommentCount, ShareCount, CollectionCount, ",
        "ShowTime, CreateTime, Mark, Status, SourceType, Weight, OriginalId, OriginalTitle, ",
        "OriginalUrl, FromId, FromName, PlatformId, PlatformName, Tags, ContentLen, CategoryId, ",
        "Uscore, UniqueFlag",
        "from CmsArticle",
        "where Id = #{id,jdbcType=INTEGER} and status=1"
    })
    @ResultMap("BaseResultMap")
    CmsArticle getModel(@Param("id")int id);
    
    @Select({
        "select",
        "Id, Title, Intro, CoverImgs, ImgShowType, ContentType, PublisherId, PublisherName, ",
        "PublisherIcon, ViewCount, DingCount, CaiCount, CommentCount, ShareCount, CollectionCount, ",
        "ShowTime, CreateTime, Mark, Status, SourceType, Weight, OriginalId, OriginalTitle, ",
        "OriginalUrl, FromId, FromName, PlatformId, PlatformName, Tags, ContentLen, CategoryId, ",
        "Uscore, UniqueFlag",
        "from CmsArticle",
        "where ${strWhere} order by ShowTime desc limit #{pageNo,jdbcType=INTEGER},#{pageSize,jdbcType=INTEGER} "
    })
    @ResultMap("BaseResultMap")
    List<CmsArticle> getList(@Param("strWhere")String strWhere,@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize);
    
    
    @Select({
        "select",
        " count(1)",
        "from CmsArticle",
        "where ${strWhere} "
    })
    int getCount(@Param("strWhere")String strWhere);
    
    
    @Update({
        "update CmsArticle",
        "set Status = #{status,jdbcType=INTEGER} ",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int deleteArticle(@Param("status")int status,@Param("id")int id);
}