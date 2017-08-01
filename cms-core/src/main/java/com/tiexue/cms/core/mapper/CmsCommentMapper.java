package com.tiexue.cms.core.mapper;

import com.tiexue.cms.core.entity.CmsComment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CmsCommentMapper {
    @Delete({
        "delete from CmsComment",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into CmsComment (Id, ArticleId, ",
        "Content, Imgs, PublisherId, ",
        "PublisherName, PublisherIcon, ",
        "TopTargetCommentId, TargetCommentId, ",
        "TargetUserId, TargetUserName, ",
        "DingCount, CaiCount, ",
        "CommentCount, Status, ",
        "ShowTime, CreateTime, ",
        "Mark, Weight, Flag)",
        "values (#{id,jdbcType=INTEGER}, #{articleid,jdbcType=INTEGER}, ",
        "#{content,jdbcType=VARCHAR}, #{imgs,jdbcType=VARCHAR}, #{publisherid,jdbcType=INTEGER}, ",
        "#{publishername,jdbcType=VARCHAR}, #{publishericon,jdbcType=VARCHAR}, ",
        "#{toptargetcommentid,jdbcType=INTEGER}, #{targetcommentid,jdbcType=INTEGER}, ",
        "#{targetuserid,jdbcType=INTEGER}, #{targetusername,jdbcType=VARCHAR}, ",
        "#{dingcount,jdbcType=INTEGER}, #{caicount,jdbcType=INTEGER}, ",
        "#{commentcount,jdbcType=INTEGER}, #{status,jdbcType=INTEGER}, ",
        "#{showtime,jdbcType=TIMESTAMP}, #{createtime,jdbcType=TIMESTAMP}, ",
        "#{mark,jdbcType=INTEGER}, #{weight,jdbcType=INTEGER}, #{flag,jdbcType=VARCHAR})"
    })
    int insert(CmsComment record);

    int insertSelective(CmsComment record);

    @Select({
        "select",
        "Id, ArticleId, Content, Imgs, PublisherId, PublisherName, PublisherIcon, TopTargetCommentId, ",
        "TargetCommentId, TargetUserId, TargetUserName, DingCount, CaiCount, CommentCount, ",
        "Status, ShowTime, CreateTime, Mark, Weight, Flag",
        "from CmsComment",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    CmsComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsComment record);

    @Update({
        "update CmsComment",
        "set ArticleId = #{articleid,jdbcType=INTEGER},",
          "Content = #{content,jdbcType=VARCHAR},",
          "Imgs = #{imgs,jdbcType=VARCHAR},",
          "PublisherId = #{publisherid,jdbcType=INTEGER},",
          "PublisherName = #{publishername,jdbcType=VARCHAR},",
          "PublisherIcon = #{publishericon,jdbcType=VARCHAR},",
          "TopTargetCommentId = #{toptargetcommentid,jdbcType=INTEGER},",
          "TargetCommentId = #{targetcommentid,jdbcType=INTEGER},",
          "TargetUserId = #{targetuserid,jdbcType=INTEGER},",
          "TargetUserName = #{targetusername,jdbcType=VARCHAR},",
          "DingCount = #{dingcount,jdbcType=INTEGER},",
          "CaiCount = #{caicount,jdbcType=INTEGER},",
          "CommentCount = #{commentcount,jdbcType=INTEGER},",
          "Status = #{status,jdbcType=INTEGER},",
          "ShowTime = #{showtime,jdbcType=TIMESTAMP},",
          "CreateTime = #{createtime,jdbcType=TIMESTAMP},",
          "Mark = #{mark,jdbcType=INTEGER},",
          "Weight = #{weight,jdbcType=INTEGER},",
          "Flag = #{flag,jdbcType=VARCHAR}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CmsComment record);
}