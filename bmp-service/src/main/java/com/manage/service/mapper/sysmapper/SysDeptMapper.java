package com.manage.service.mapper.sysmapper;


import com.manage.common.dto.SysDeptDto;
import com.manage.common.entity.sys.SysDept;

import java.util.List;


public interface SysDeptMapper {


	 List<SysDept> getList(SysDeptDto sysDeptDto);

	int create(SysDeptDto sysDeptDto);

	int update(SysDeptDto sysDeptDto);

	int delete(String id);
}