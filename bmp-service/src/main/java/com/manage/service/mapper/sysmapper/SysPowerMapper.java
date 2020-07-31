package com.manage.service.mapper.sysmapper;

import com.manage.common.dto.SysPowerDto;
import com.manage.common.entity.sys.SysPower;
import java.util.List;


public interface SysPowerMapper{



	/**
	 *
	 * getList的方法
	 *
	 */
	List<SysPower> getList(SysPowerDto sysPowerDto);

	 /**
	  * 
	  * 添加
	  * 
	  */
	 int create(SysPowerDto sysPowerDto);

	 /**
	  * 
	  * 修改 （匹配有值的字段）
	  * 
	  */
	 int update(SysPowerDto sysPowerDto);

	 /**
	  *
	  * 删除（根据主键ID删除）
	  *
	  */
	 int delete(String id);

}