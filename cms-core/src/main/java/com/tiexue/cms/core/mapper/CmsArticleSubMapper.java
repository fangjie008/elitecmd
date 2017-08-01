package com.tiexue.cms.core.mapper;

import com.tiexue.cms.core.entity.CmsArticleSub;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CmsArticleSubMapper {
    @Insert({
        "insert into CmsArticleSub (Id, OriginalContent, ",
        "ContentPic, Materials)",
        "values (#{id,jdbcType=INTEGER}, #{originalcontent,jdbcType=LONGVARCHAR}, ",
        "#{contentpic,jdbcType=LONGVARCHAR}, #{materials,jdbcType=LONGVARCHAR})"
    })
    int insert(CmsArticleSub record);

    int insertSelective(CmsArticleSub record);
    
    @Update({
        "update CmsArticleSub",
        " set ",
          "OriginalContent = #{originalcontent,jdbcType=LONGVARCHAR},",
          "ContentPic = #{contentpic,jdbcType=LONGVARCHAR},",
          "Materials = #{materials,jdbcType=LONGVARCHAR}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int update(CmsArticleSub record);
    
    
    @Select({" select Id, OriginalContent,ContentPic,Materials from CmsArticleSub ",
    " where Id=#{id}"})
	@ResultMap("BaseResultMap")
	CmsArticleSub select(@Param("id")Integer id);
    
    
    @Delete({
        "delete from CmsArticleSub",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);
}