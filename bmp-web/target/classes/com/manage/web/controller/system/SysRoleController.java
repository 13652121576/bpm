package com.manage.web.controller.system;

import com.github.pagehelper.PageInfo;
import com.manage.common.dto.PageResult;
import com.manage.common.dto.SysRoleDto;
import com.manage.common.entity.sys.SysRole;
import com.manage.common.entity.sys.SysUser;
import com.manage.common.util.ServerResponse;
import com.manage.service.sysService.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/sys/role")
@Api(tags = "角色管理相关接口")
public class SysRoleController {
	private static Logger logger = LoggerFactory.getLogger(SysRoleController.class);

	 @Autowired
	 private SysRoleService sysRoleService;

	 /**
	  * getList的接口
	  */
	 @ResponseBody
	 @RequestMapping(value="/getList",method = RequestMethod.GET)
	 @ApiOperation("查询角色接口")
	 public ServerResponse getList(SysRoleDto sysRoleDto) {
		 logger.info("入参======info====={}",sysRoleDto.getId());
		 logger.debug("入参=====debug======{}",sysRoleDto.getId());
		 List<SysRole> sysRoles = sysRoleService.getList(sysRoleDto);
		 //获取分页信息
		 PageInfo pageInfo = new PageInfo(sysRoles);
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
		 pageResult.setRows(sysRoles);
		 return ServerResponse.createBySuccess(pageResult);
	 }

	 /**
	  * 创建角色的接口
	  */
	 @ResponseBody
	 @RequestMapping(value="/create",method = RequestMethod.POST)
	 @ApiOperation("创建角色接口")
	 public ServerResponse create(SysRoleDto sysRoleDto) {
		 logger.info("入参======info====={}",sysRoleDto.getId());
		 return ServerResponse.createBySuccess(sysRoleService.create(sysRoleDto));
	 }

	 /**
	  * 修改角色的接口
	  */
	 @ResponseBody
	 @RequestMapping(value="/update",method = RequestMethod.POST)
	 @ApiOperation("修改角色接口")
	 public ServerResponse update(SysRoleDto sysRoleDto) {
		 logger.info("入参======info====={}",sysRoleDto.getId());
		 if(StringUtils.isEmpty(sysRoleDto.getId())){
			 return ServerResponse.createByErrorMessage("修改用户id不能为空");
		 }
		 return ServerResponse.createBySuccess(sysRoleService.update(sysRoleDto));
	 }

	 /**
	  * 删除角色的接口
	  */
	 @ResponseBody
	 @RequestMapping(value="/delete",method = RequestMethod.GET	)
	 @ApiOperation("删除角色接口")
	 public ServerResponse delete(String id) {
		 logger.info("入参======info====={}",id);
		 if(StringUtils.isEmpty(id)){
		 	return ServerResponse.createByErrorMessage("id不能为空");
		 }
		 return ServerResponse.createBySuccess(sysRoleService.delete(id));
	 }

}