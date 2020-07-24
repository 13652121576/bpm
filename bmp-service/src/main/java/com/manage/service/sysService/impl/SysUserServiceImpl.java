package com.manage.service.sysService.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.manage.common.dto.SysUserDto;
import com.manage.common.entity.sys.SysUser;
import com.manage.service.annotation.pageHelper;
import com.manage.service.mapper.sysmapper.SysUserMapper;
import com.manage.service.sysService.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @version : V1.0
 * @ClassName: com.manage.service.sysService.implSysUserServiceImpl
 * @Description: SysUserServiceImpl
 * @Auther: ydm
 * @Date: 2020/7/10
 */
@Service(value = "sysUserServiceImpl")
public class SysUserServiceImpl implements SysUserService{
    private static Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    @pageHelper
    public List<SysUser> getList(SysUserDto sysUserDto) {
         //查询所有
        List<SysUser> sysUsers = sysUserMapper.getList(sysUserDto);
        return sysUsers;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public int create(SysUserDto sysUserDto) {
        return sysUserMapper.create(sysUserDto);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public int update(SysUserDto sysUserDto) {
        return sysUserMapper.update(sysUserDto);
    }

    @Override
    public int delete(String id) {
        return sysUserMapper.delete(id);
    }
}
