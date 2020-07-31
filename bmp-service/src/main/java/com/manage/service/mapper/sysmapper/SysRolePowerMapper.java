package com.manage.service.mapper.sysmapper;


import com.manage.common.entity.sys.SysRolePower;
import java.util.List;


public interface SysRolePowerMapper{


	/**
	 *
	 * getList的方法
	 *
	 */
	List<SysRolePower> getList(SysRolePower sysRolePower);
	 /**
	  * 
	  * 添加
	  * 
	  */
	 int create(SysRolePower sysRolePower);

	 /**
	  * 
	  * 修改 （匹配有值的字段）
	  * 
	  */
	 int update(SysRolePower sysRolePower);

	 /**
	  *
	  * 删除（根据roleId删除）
	  *
	  */
	 int delete(String roleId);

}