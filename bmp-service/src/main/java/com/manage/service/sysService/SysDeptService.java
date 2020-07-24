package com.manage.service.sysService;


import com.manage.common.dto.SysDeptDto;
import com.manage.common.dto.SysUserDto;
import com.manage.common.entity.sys.SysDept;

import java.util.List;

/**
 * @version : V1.0
 * @ClassName: com.manage.service.SysDeptService
 * @Description: SysDeptService
 * @Auther: ydm
 * @Date: 2020/7/10
 */
public interface SysDeptService {

    /**
     * getList的方法
     */
    public List<SysDept> getList(SysDeptDto sysDeptDto);

    /**
     * 新增方法
     */
    public int create(SysDeptDto sysDeptDto);

    /**
     * 修改方法
     */
    public int update(SysDeptDto sysDeptDto);

    /**
     * 根据id删除方法
     */
    public int delete(String id);

}
