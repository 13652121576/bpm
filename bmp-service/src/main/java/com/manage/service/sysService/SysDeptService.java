package com.manage.service.sysService;


import com.manage.common.base.domain.TreeNode;
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
    List<SysDept> getList(SysDeptDto sysDeptDto);

    /**
     * 新增方法
     */
    int create(SysDeptDto sysDeptDto);

    /**
     * 修改方法
     */
    int update(SysDeptDto sysDeptDto);

    /**
     * 根据id删除方法
     */
    int delete(String id);
    /**
     * 获取部门树的接口
     */
    List<TreeNode> getDeptTree();

}
