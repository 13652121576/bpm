package com.manage.common.dto;

import com.manage.common.entity.sys.SysDept;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 系统部门表
 * 
 */
public class SysDeptDto extends SysDept{
	/** 当前记录起始索引 */
	private Integer pageNum;
	/** 每页显示记录数 */
	private Integer pageSize;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
