package com.manage.service.sysService.impl;

import com.manage.common.dto.SysPowerDto;
import com.manage.common.entity.sys.SysPower;
import com.manage.service.annotation.pageHelper;
import com.manage.service.mapper.sysmapper.SysPowerMapper;
import com.manage.service.sysService.SysPowerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @version : V1.0
 * @ClassName: com.manage.service.sysService.SysPowerServiceImpl
 * @Description: SysPowerServiceImpl
 * @Auther: ydm
 * @Date: 2020/7/31
 */
@Service(value = "SysPowerServiceImpl")
public class SysPowerServiceImpl implements SysPowerService{
    private static Logger logger = LoggerFactory.getLogger(SysPowerServiceImpl.class);
    @Autowired
    private SysPowerMapper sysPowerMapper;

    @Override
    @pageHelper
    public List<SysPower> getList(SysPowerDto sysPowerDto) {
         //查询所有
        List<SysPower> sysPowers = sysPowerMapper.getList(sysPowerDto);
        return sysPowers;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public int create(SysPowerDto sysPowerDto) {
        return sysPowerMapper.create(sysPowerDto);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public int update(SysPowerDto sysPowerDto) {
        return sysPowerMapper.update(sysPowerDto);
    }

    @Override
    public int delete(String id) {
        return sysPowerMapper.delete(id);
    }
}
