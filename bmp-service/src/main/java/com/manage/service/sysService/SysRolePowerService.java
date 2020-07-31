package com.manage.service.sysService;

import com.manage.common.entity.sys.SysRolePower;
import java.util.List;

/**
 * @version : V1.0
 * @ClassName: com.manage.service.SysRolePowerService
 * @Description: SysRolePowerService
 * @Auther: ydm
 * @Date: 2020/7/31
 */
public interface SysRolePowerService {

    /**
     * getList的方法
     */
    List<SysRolePower> getList(SysRolePower sysRolePower);

    /**
     * 新增方法
     */
    int create(SysRolePower sysRolePower);

    /**
     * 修改方法
     */
    int update(SysRolePower sysRolePower);

    /**
     * 根据id删除方法
     */
    int delete(String id);

}
