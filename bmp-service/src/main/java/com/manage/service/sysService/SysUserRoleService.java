package com.manage.service.sysService;

import com.manage.common.entity.sys.SysUserRole;
import java.util.List;

/**
 * @version : V1.0
 * @ClassName: com.manage.service.SysUserRoleService
 * @Description: SysUserRoleService
 * @Auther: ydm
 * @Date: 2020/7/31
 */
public interface SysUserRoleService {

    /**
     * getList的方法
     */
    List<SysUserRole> getList(SysUserRole sysUserRole);

    /**
     * 新增方法
     */
    int create(SysUserRole sysUserRole);

    /**
     * 修改方法
     */
    int update(SysUserRole sysUserRole);

    /**
     * 根据id删除方法
     */
    int delete(String id);

}
