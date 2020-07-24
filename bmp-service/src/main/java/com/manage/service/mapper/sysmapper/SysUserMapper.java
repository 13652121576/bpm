package com.manage.service.mapper.sysmapper;

import com.manage.common.dto.SysUserDto;
import com.manage.common.entity.sys.SysUser;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;


public interface SysUserMapper {


	 List<SysUser> getList(SysUserDto sysUserDto);

	int create(SysUserDto sysUserDto);

	int update(SysUserDto sysUserDto);

	int delete(String id);
}