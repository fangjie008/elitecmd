package com.tiexue.cms.core.mapper;

import com.tiexue.cms.core.entity.CmsAdmin;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CmsAdminMapper {
    @Delete({
        "delete from CmsAdmin",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into CmsAdmin (Id, Name, ",
        "Password, Intro, ",
        "Type, Auth)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{intro,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=INTEGER}, #{auth,jdbcType=VARCHAR})"
    })
    int insert(CmsAdmin record);

    int insertSelective(CmsAdmin record);

    @Select({
        "select",
        "Id, Name, Password, Intro, Type, Auth",
        "from CmsAdmin",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    CmsAdmin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsAdmin record);

    @Update({
        "update CmsAdmin",
        "set Name = #{name,jdbcType=VARCHAR},",
          "Password = #{password,jdbcType=VARCHAR},",
          "Intro = #{intro,jdbcType=VARCHAR},",
          "Type = #{type,jdbcType=INTEGER},",
          "Auth = #{auth,jdbcType=VARCHAR}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CmsAdmin record);
    
    
    @Select({
        "select",
        "Id, Name, Password, Intro, Type, Auth",
        "from CmsAdmin",
        "where Name = #{name,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    CmsAdmin selectByName(String name);
    
    
    @Select({
    	"select",
    	"Id, Name, Password, Intro, Type, Auth",
        "from CmsAdmin",
    	})
    @ResultMap("BaseResultMap")
    List<CmsAdmin> selectList();
}