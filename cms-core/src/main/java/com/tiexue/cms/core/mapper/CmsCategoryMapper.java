package com.tiexue.cms.core.mapper;

import com.tiexue.cms.core.entity.CmsArticle;
import com.tiexue.cms.core.entity.CmsCategory;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CmsCategoryMapper {
    @Delete({
        "delete from CmsCategory",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into CmsCategory (Id, Name, ",
        "CoverImg, Status, ",
        "Type, Intro, Weight, ",
        "Tags, ParentId, ",
        "CreateTime, Remark)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{coverimg,jdbcType=VARCHAR}, #{status,jdbcType=INTEGER}, ",
        "#{type,jdbcType=INTEGER}, #{intro,jdbcType=VARCHAR}, #{weight,jdbcType=INTEGER}, ",
        "#{tags,jdbcType=VARCHAR}, #{parentid,jdbcType=INTEGER}, ",
        "#{createtime,jdbcType=TIMESTAMP}, #{remark,jdbcType=VARCHAR})"
    })
    int insert(CmsCategory record);

    int insertSelective(CmsCategory record);

    @Select({
        "select",
        "Id, Name, CoverImg, Status, Type, Intro, Weight, Tags, ParentId, CreateTime, ",
        "Remark",
        "from CmsCategory",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    CmsCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsCategory record);

    @Update({
        "update CmsCategory",
        "set Name = #{name,jdbcType=VARCHAR},",
          "CoverImg = #{coverimg,jdbcType=VARCHAR},",
          "Status = #{status,jdbcType=INTEGER},",
          "Type = #{type,jdbcType=INTEGER},",
          "Intro = #{intro,jdbcType=VARCHAR},",
          "Weight = #{weight,jdbcType=INTEGER},",
          "Tags = #{tags,jdbcType=VARCHAR},",
          "ParentId = #{parentid,jdbcType=INTEGER},",
          "CreateTime = #{createtime,jdbcType=TIMESTAMP},",
          "Remark = #{remark,jdbcType=VARCHAR}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CmsCategory record);
    
    @Select({
        "select",
        "Id, Name, CoverImg, Status, Type, Intro, Weight, Tags, ParentId, CreateTime, ",
        "Remark",
        "from CmsCategory",
        "where Name = #{name,jdbcType=VARCHAR}"
    })
    @ResultMap("BaseResultMap")
    CmsCategory getModelByName(@Param("name")String name);
    
    @Select({
    	  "select",
          "Id, Name, CoverImg, Status, Type, Intro, Weight, Tags, ParentId, CreateTime, ",
          "Remark",
          "from CmsCategory",
          "where Status = #{status,jdbcType=VARCHAR}"
    })
    List<CmsCategory> getNormalList(@Param("status")Integer status);
    
    @Select({
        "select",
        "Id, Name, CoverImg, Status, Type, Intro, Weight, Tags, ParentId, CreateTime, ",
        "Remark",
        "from CmsCategory",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    CmsCategory getModel(@Param("id")int id);
    
    @Select({
        "select",
        "Id, Name, CoverImg, Status, Type, Intro, Weight, Tags, ParentId, CreateTime, ",
        "Remark",
        "from CmsCategory",
        "where ${strWhere} order by CreateTime desc limit #{pageNo,jdbcType=INTEGER},#{pageSize,jdbcType=INTEGER} "
    })
    @ResultMap("BaseResultMap")
    List<CmsCategory> getList(@Param("strWhere")String strWhere,@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize);
    
    
    @Select({
        "select",
        " count(1)",
        "from CmsCategory",
        "where ${strWhere} "
    })
    int getCount(@Param("strWhere")String strWhere);
    
    
    @Update({
        "update CmsCategory",
        "set Status = #{status,jdbcType=INTEGER} ",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updeteStatus(@Param("status")int status,@Param("id")int id);
}