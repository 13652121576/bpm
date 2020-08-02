package com.manage.web.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.manage.common.base.domain.TreeNode;
import com.manage.common.dto.PageResult;
import com.manage.common.dto.SysDeptDto;
import com.manage.common.dto.SysUserDto;
import com.manage.common.entity.sys.SysDept;
import com.manage.common.entity.sys.SysUser;
import com.manage.common.util.ServerResponse;
import com.manage.service.sysService.SysDeptService;
import com.manage.service.sysService.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/sys/dept")
@Api(tags = "部门管理相关接口")
public class SysDeptController {
	private static Logger logger = LoggerFactory.getLogger(SysDeptController.class);

	 @Autowired
	 private SysDeptService sysDeptService;

	 /**
	  * getList的接口
	  */
	 @ResponseBody
	 @RequestMapping(value="/getList",method = RequestMethod.GET)
	 @ApiOperation("查询部门接口")
	 @PreAuthorize("hasAuthority('sys:dept:getList')")
	 public ServerResponse getList(SysDeptDto sysDeptDto) {
		 logger.info("入参======info====={}",JSONObject.toJSON(sysDeptDto));
		 logger.debug("入参=====debug======{}",JSONObject.toJSON(sysDeptDto));
		 List<SysDept> sysDepts = sysDeptService.getList(sysDeptDto);
		 //获取分页信息
		 PageInfo pageInfo = new PageInfo(sysDepts);
		 logger.info("获取当前页=====》{}",pageInfo.getPageNum());
		 logger.info("获取总页数=====》{}",pageInfo.getPages());
		 logger.info("获取每页条数=====》{}",pageInfo.getPageSize());
		 logger.info("获取总记录数=====》{}",pageInfo.getTotal());
		 logger.info("获取第一页=====》{}",pageInfo.getNavigateFirstPage());
		 logger.info("获取最后一页=====》{}",pageInfo.getNavigateLastPage());
		 PageResult pageResult = new PageResult();
		 pageResult.setPageNum(pageInfo.getPageNum());
		 pageResult.setPageSize(pageInfo.getPageSize());
		 pageResult.setTotal(pageInfo.getTotal());
		 pageResult.setTotalPage(pageInfo.getPages());
		 pageResult.setRows(sysDepts);
		 return ServerResponse.createBySuccess(pageResult);
	 }

	 /**
	  * 创建部门的接口
	  */
	 @ResponseBody
	 @RequestMapping(value="/create",method = RequestMethod.POST)
	 @ApiOperation("创建部门接口")
	 @PreAuthorize("hasAuthority('sys:dept:create')")
	 public ServerResponse create(SysDeptDto sysDeptDto) {
		 logger.info("入参======info====={}",sysDeptDto.getId());
		 return ServerResponse.createBySuccess(sysDeptService.create(sysDeptDto));
	 }

	 /**
	  * 修改部门的接口
	  */
	 @ResponseBody
	 @RequestMapping(value="/update",method = RequestMethod.POST)
	 @ApiOperation("修改部门接口")
	 @PreAuthorize("hasAuthority('sys:dept:update')")
	 public ServerResponse update(SysDeptDto sysDeptDto) {
		 logger.info("入参======info====={}",sysDeptDto.getId());
		 if(StringUtils.isEmpty(sysDeptDto.getId())){
			 return ServerResponse.createByErrorMessage("修改部门id不能为空");
		 }
		 return ServerResponse.createBySuccess(sysDeptService.update(sysDeptDto));
	 }

	 /**
	  * 修改部门的接口
	  */
	 @ResponseBody
	 @RequestMapping(value="/delete",method = RequestMethod.GET	)
	 @ApiOperation("删除部门接口")
	 @PreAuthorize("hasAuthority('sys:dept:delete')")
	 public ServerResponse delete(String id) {
		 logger.info("入参======info====={}",id);
		 if(StringUtils.isEmpty(id)){
		 	return ServerResponse.createByErrorMessage("部门id不能为空");
		 }
		 return ServerResponse.createBySuccess(sysDeptService.delete(id));
	 }

	/**
	 * 获取部门树的接口
	 */
	@ResponseBody
	@RequestMapping(value="/getDeptTree",method = RequestMethod.GET	)
	@ApiOperation("获取部门树的接口")
	public ServerResponse getDeptTree() {
		List<TreeNode> treeNode = sysDeptService.getDeptTree();
		return ServerResponse.createBySuccess(treeNode);
	}


}