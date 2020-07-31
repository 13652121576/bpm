package com.manage.service.mapper.sysmapper;

import com.manage.common.dto.SysRoleDto;
import com.manage.common.entity.sys.SysRole;
import java.util.List;

public interface SysRoleMapper{

	/**
	 *
	 * getList的方法
	 *
	 */
	List<SysRole> getList(SysRoleDto sysRoleDto);

	 /**
	  * 
	  * 添加
	  * 
	  */
	 int create(SysRoleDto sysRoleDto);

	 /**
	  *
	  * 修改 （匹配有值的字段）
	  *
	  */
	 int update(SysRoleDto sysRoleDto);

	 /**
	  *
	  * 删除（根据主键ID删除）
	  *
	  */
	 int delete(String id);

}