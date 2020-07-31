package com.manage.service.mapper.sysmapper;

import com.manage.common.entity.sys.SysUserRole;
import java.util.List;

public interface SysUserRoleMapper {

	/**
	 *
	 * getList的方法
	 *
	 */
	List<SysUserRole> getList(SysUserRole sysUserRole);
	 /**
	  * 
	  * 添加
	  * 
	  */
	 int create(SysUserRole sysUserRole);

	 /**
	  * 
	  * 修改 （匹配有值的字段）
	  * 
	  */
	 int update(SysUserRole sysUserRole);

	/**
	 *
	 * 删除（根据roleId删除）
	 *
	 */
	int delete(String roleId);


}