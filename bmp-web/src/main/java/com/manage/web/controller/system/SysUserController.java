package com.manage.web.controller.system;

import com.github.pagehelper.PageInfo;
import com.manage.common.dto.PageResult;
import com.manage.common.dto.SysUserDto;
import com.manage.common.entity.sys.SysUser;
import com.manage.common.util.ServerResponse;
import com.manage.service.sysService.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Controller
@RequestMapping("/sys/user")
@Api(tags = "用户管理相关接口")
public class SysUserController {
	private static Logger logger = LoggerFactory.getLogger(SysUserController.class);

	 @Autowired
	 private SysUserService sysUserService;

	 /**
	  * getList的接口
	  */
	 @ResponseBody
	 @RequestMapping(value="/getList",method = RequestMethod.GET)
	 @ApiOperation("查询用户接口")
	 public ServerResponse getList(SysUserDto sysUserDto) {
		 logger.info("入参======info====={}",sysUserDto.getId());
		 logger.debug("入参=====debug======{}",sysUserDto.getId());
		 List<SysUser> sysUsers = sysUserService.getList(sysUserDto);
		 //获取分页信息
		 PageInfo pageInfo = new PageInfo(sysUsers);
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
		 pageResult.setRows(sysUsers);
		 return ServerResponse.createBySuccess(pageResult);
	 }

	 /**
	  * 创建用户的接口
	  */
	 @ResponseBody
	 @RequestMapping(value="/create",method = RequestMethod.POST)
	 @ApiOperation("创建用户接口")
	 public ServerResponse create(SysUserDto sysUserDto) {
		 logger.info("入参======info====={}",sysUserDto.getId());
		 return ServerResponse.createBySuccess(sysUserService.create(sysUserDto));
	 }

	 /**
	  * 修改用户的接口
	  */
	 @ResponseBody
	 @RequestMapping(value="/update",method = RequestMethod.POST)
	 @ApiOperation("修改用户接口")
	 public ServerResponse update(SysUserDto sysUserDto) {
		 logger.info("入参======info====={}",sysUserDto.getId());
		 if(StringUtils.isEmpty(sysUserDto.getId())){
			 return ServerResponse.createByErrorMessage("修改用户id不能为空");
		 }
		 return ServerResponse.createBySuccess(sysUserService.update(sysUserDto));
	 }

	 /**
	  * 修改用户的接口
	  */
	 @ResponseBody
	 @RequestMapping(value="/delete",method = RequestMethod.GET	)
	 @ApiOperation("删除用户接口")
	 public ServerResponse delete(String id) {
		 logger.info("入参======info====={}",id);
		 if(StringUtils.isEmpty(id)){
		 	return ServerResponse.createByErrorMessage("id不能为空");
		 }
		 return ServerResponse.createBySuccess(sysUserService.delete(id));
	 }

}