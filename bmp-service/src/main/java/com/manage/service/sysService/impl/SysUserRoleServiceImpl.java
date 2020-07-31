package com.manage.service.sysService.impl;


import com.manage.common.entity.sys.SysUserRole;
import com.manage.service.annotation.pageHelper;
import com.manage.service.mapper.sysmapper.SysUserRoleMapper;
import com.manage.service.sysService.SysUserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


/**
 * @version : V1.0
 * @ClassName: com.manage.service.sysService.SysUserRoleServiceImpl
 * @Description: SysUserRoleServiceImpl
 * @Auther: ydm
 * @Date: 2020/7/10
 */
@Service(value = "SysUserRoleServiceImpl")
public class SysUserRoleServiceImpl implements SysUserRoleService {
    private static Logger logger = LoggerFactory.getLogger(SysUserRoleServiceImpl.class);
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    @pageHelper
    public List<SysUserRole> getList(SysUserRole sysUserRole) {
         //查询所有
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.getList(sysUserRole);
        return sysUserRoles;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public int create(SysUserRole sysUserRole) {
        return sysUserRoleMapper.create(sysUserRole);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public int update(SysUserRole sysUserRole) {
        return sysUserRoleMapper.update(sysUserRole);
    }

    @Override
    public int delete(String id) {
        return sysUserRoleMapper.delete(id);
    }
}
