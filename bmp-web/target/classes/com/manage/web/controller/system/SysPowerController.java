package com.manage.web.controller.system;

import com.github.pagehelper.PageInfo;
import com.manage.common.dto.PageResult;
import com.manage.common.dto.SysPowerDto;
import com.manage.common.dto.SysUserDto;
import com.manage.common.entity.sys.SysPower;
import com.manage.common.entity.sys.SysUser;
import com.manage.common.util.ServerResponse;
import com.manage.service.sysService.SysPowerService;
import com.manage.service.sysService.SysUserService;
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
@RequestMapping("/sys/power")
@Api(tags = "权限管理相关接口")
public class SysPowerController {
	private static Logger logger = LoggerFactory.getLogger(SysPowerController.class);

	 @Autowired
	 private SysPowerService sysPowerService;

	 /**
	  * getList的接口
	  */
	 @ResponseBody
	 @RequestMapping(value="/getList",method = RequestMethod.GET)
	 @ApiOperation("查询权限接口")
	 public ServerResponse getList(SysPowerDto sysPowerDto) {
		 logger.info("入参======info====={}",sysPowerDto.getId());
		 logger.debug("入参=====debug======{}",sysPowerDto.getId());
		 List<SysPower> sysPowers = sysPowerService.getList(sysPowerDto);
		 //获取分页信息
		 PageInfo pageInfo = new PageInfo(sysPowers);
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
		 pageResult.setRows(sysPowers);
		 return ServerResponse.createBySuccess(pageResult);
	 }

	 /**
	  * 创建权限的接口
	  */
	 @ResponseBody
	 @RequestMapping(value="/create",method = RequestMethod.POST)
	 @ApiOperation("创建权限接口")
	 public ServerResponse create(SysPowerDto sysPowerDto) {
		 logger.info("入参======info====={}",sysPowerDto.getId());
		 return ServerResponse.createBySuccess(sysPowerService.create(sysPowerDto));
	 }

	 /**
	  * 修改权限的接口
	  */
	 @ResponseBody
	 @RequestMapping(value="/update",method = RequestMethod.POST)
	 @ApiOperation("修改权限接口")
	 public ServerResponse update(SysPowerDto sysPowerDto) {
		 logger.info("入参======info====={}",sysPowerDto.getId());
		 if(StringUtils.isEmpty(sysPowerDto.getId())){
			 return ServerResponse.createByErrorMessage("修改权限id不能为空");
		 }
		 return ServerResponse.createBySuccess(sysPowerService.update(sysPowerDto));
	 }

	 /**
	  * 删除权限的接口
	  */
	 @ResponseBody
	 @RequestMapping(value="/delete",method = RequestMethod.GET	)
	 @ApiOperation("删除权限接口")
	 public ServerResponse delete(String id) {
		 logger.info("入参======info====={}",id);
		 if(StringUtils.isEmpty(id)){
		 	return ServerResponse.createByErrorMessage("id不能为空");
		 }
		 return ServerResponse.createBySuccess(sysPowerService.delete(id));
	 }

}