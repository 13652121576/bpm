package com.manage.service.sysService.impl;


import com.manage.common.dto.SysDeptDto;
import com.manage.common.entity.sys.SysDept;
import com.manage.service.annotation.pageHelper;
import com.manage.service.mapper.sysmapper.SysDeptMapper;
import com.manage.service.sysService.SysDeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @version : V1.0
 * @ClassName: com.manage.service.sysService.implSysDeptServiceImpl
 * @Description: SysDeptServiceImpl
 * @Auther: ydm
 * @Date: 2020/7/10
 */
@Service(value = "sysDeptServiceImpl")
public class SysDeptServiceImpl implements SysDeptService{
    private static Logger logger = LoggerFactory.getLogger(SysDeptServiceImpl.class);
    @Autowired
    private SysDeptMapper sysDeptMapper;


    @Override
    public List<SysDept> getList(SysDeptDto sysDeptDto) {
        return sysDeptMapper.getList(sysDeptDto);
    }

    @Override
    public int create(SysDeptDto sysDeptDto) {
        return sysDeptMapper.create(sysDeptDto);
    }

    @Override
    public int update(SysDeptDto sysDeptDto) {
        return sysDeptMapper.update(sysDeptDto);
    }

    @Override
    public int delete(String id) {
        return sysDeptMapper.delete(id);
    }
}
