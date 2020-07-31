package com.manage.service.sysService.impl;

import com.manage.common.dto.SysRoleDto;
import com.manage.common.entity.sys.SysRole;
import com.manage.service.annotation.pageHelper;
import com.manage.service.mapper.sysmapper.SysRoleMapper;
import com.manage.service.sysService.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @version : V1.0
 * @ClassName: com.manage.service.sysService.sysRoleServiceImpl
 * @Description: sysRoleServiceImpl
 * @Auther: ydm
 * @Date: 2020/7/31
 */
@Service(value = "sysRoleServiceImpl")
public class SysRoleServiceImpl implements SysRoleService{
    private static Logger logger = LoggerFactory.getLogger(SysRoleServiceImpl.class);
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    @pageHelper
    public List<SysRole> getList(SysRoleDto sysRoleDto) {
         //查询所有
        List<SysRole> sysRoles = sysRoleMapper.getList(sysRoleDto);
        return sysRoles;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public int create(SysRoleDto sysRoleDto) {
        return sysRoleMapper.create(sysRoleDto);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public int update(SysRoleDto sysRoleDto) {
        return sysRoleMapper.update(sysRoleDto);
    }

    @Override
    public int delete(String id) {
        return sysRoleMapper.delete(id);
    }
}
