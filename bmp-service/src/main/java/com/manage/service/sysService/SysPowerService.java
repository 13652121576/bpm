package com.manage.service.sysService;

import com.manage.common.base.domain.TreeNode;
import com.manage.common.dto.SysPowerDto;
import com.manage.common.entity.sys.SysPower;
import java.util.List;

/**
 * @version : V1.0
 * @ClassName: com.manage.service.SysPowerService
 * @Description: SysPowerService
 * @Auther: ydm
 * @Date: 2020/7/31
 */
public interface SysPowerService {

    /**
     * getList的方法
     */
    List<SysPower> getList(SysPowerDto sysPowerDto);

    /**
     * 新增方法
     */
    int create(SysPowerDto sysPowerDto);

    /**
     * 修改方法
     */
    int update(SysPowerDto sysPowerDto);

    /**
     * 根据id删除方法
     */
    int delete(String id);

    /**
     * 根据用户id查权限
     */
    List<SysPower> getPowerByUserId(String userId);

    List<TreeNode> getPowerTree();
}
