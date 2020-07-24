package com.manage.service.sysService;


import com.manage.common.dto.SysUserDto;
import com.manage.common.entity.sys.SysUser;

import java.util.List;

/**
 * @version : V1.0
 * @ClassName: com.manage.service.sysServiceSysUserService
 * @Description: SysUserService
 * @Auther: ydm
 * @Date: 2020/7/10
 */
public interface SysUserService {

    /**
     * getList的方法
     */
    public List<SysUser> getList(SysUserDto sysUserDto);

    /**
     * 新增方法
     */
    public int create(SysUserDto sysUserDto);

    /**
     * 修改方法
     */
    public int update(SysUserDto sysUserDto);

    /**
     * 根据id删除方法
     */
    public int delete(String id);

}
