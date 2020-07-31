package com.manage.service.sysService.impl;


import com.manage.common.entity.sys.SysRolePower;
import com.manage.service.annotation.pageHelper;
import com.manage.service.mapper.sysmapper.SysRolePowerMapper;
import com.manage.service.sysService.SysRolePowerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @version : V1.0
 * @ClassName: com.manage.service.sysService.SysRolePowerServiceImpl
 * @Description: SysRolePowerServiceImpl
 * @Auther: ydm
 * @Date: 2020/7/31
 */
@Service(value = "SysRolePowerServiceImpl")
public class SysRolePowerServiceImpl implements SysRolePowerService {
    private static Logger logger = LoggerFactory.getLogger(SysRolePowerServiceImpl.class);
    @Autowired
    private SysRolePowerMapper sysRolePowerMapper;

    @Override
    @pageHelper
    public List<SysRolePower> getList(SysRolePower sysRolePower) {
         //查询所有
        List<SysRolePower> sysRolePowers = sysRolePowerMapper.getList(sysRolePower);
        return sysRolePowers;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public int create(SysRolePower sysRolePower) {
        return sysRolePowerMapper.create(sysRolePower);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public int update(SysRolePower sysRolePower) {
        return sysRolePowerMapper.update(sysRolePower);
    }

    @Override
    public int delete(String id) {
        return sysRolePowerMapper.delete(id);
    }
}
