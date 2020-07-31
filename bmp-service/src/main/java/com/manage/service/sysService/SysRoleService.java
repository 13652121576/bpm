package com.manage.service.sysService;

import com.manage.common.dto.SysRoleDto;
import com.manage.common.entity.sys.SysRole;
import java.util.List;

/**
 * @version : V1.0
 * @ClassName: com.manage.service.SysRoleService
 * @Description: SysRoleService
 * @Auther: ydm
 * @Date: 2020/7/31
 */
public interface SysRoleService {

    /**
     * getList的方法
     */
    List<SysRole> getList(SysRoleDto sysRoleDto);

    /**
     * 新增方法
     */
    int create(SysRoleDto sysRoleDto);

    /**
     * 修改方法
     */
    int update(SysRoleDto sysRoleDto);

    /**
     * 根据id删除方法
     */
    int delete(String id);

}
