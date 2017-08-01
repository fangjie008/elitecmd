package com.tiexue.cms.manage.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.tiexue.cms.core.define.CmsAdminType;
import com.tiexue.cms.core.dto.CmsAdminDto;
import com.tiexue.cms.core.entity.CmsAdmin;

public class AdminConvert {

	private static Logger logger = Logger.getLogger(AdminConvert.class);

	/**
	 * 内容转化成前端直接用的内容
	 * 
	 * @param article
	 * @return
	 */
	public static List<CmsAdminDto> toAdminListDto(List<CmsAdmin> cmsAdmins) {
		List<CmsAdminDto> cmsAdminDtos = new ArrayList<CmsAdminDto>();
		if (cmsAdmins == null||cmsAdmins.isEmpty()||cmsAdmins.size()<=0)
			return cmsAdminDtos;
		for (CmsAdmin cAdmin : cmsAdmins) {
			cmsAdminDtos.add(toAdminDto(cAdmin));
		}
		return cmsAdminDtos;
	}

	/**
	 * 内容转化成前端直接用的内容
	 * 
	 * @param article
	 * @return
	 */
	public static CmsAdminDto toAdminDto(CmsAdmin cmsAdmin) {
		CmsAdminDto cmsAdminDto = new CmsAdminDto();
		if (cmsAdmin == null)
			return cmsAdminDto;
		cmsAdminDto.setAuth(cmsAdmin.getAuth());
		cmsAdminDto.setId(cmsAdmin.getId());
		cmsAdminDto.setIntro(cmsAdmin.getIntro());
		cmsAdminDto.setName(cmsAdmin.getName());
		cmsAdminDto.setPassword(cmsAdmin.getPassword());
		cmsAdminDto.setType(cmsAdmin.getType());
		cmsAdminDto.setTypename(CmsAdminType.AdminTypeMap.get(cmsAdmin.getType()));
		return cmsAdminDto;
	}
}
