package com.manage.service.sysService.impl;


import com.manage.common.base.domain.TreeNode;
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

import java.util.ArrayList;
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

    @Override
    public List<TreeNode> getDeptTree() {
        SysDeptDto sysDeptDto = new SysDeptDto();
        List<SysDept> treeAll = this.getList(sysDeptDto);
        List<TreeNode> treeNode = initTree("0", treeAll);
        return treeNode;
    }

    /**
     * 初始化tree
     */
    private List<TreeNode> initTree(String topId, List<SysDept> treeAll){
        List<TreeNode> tree = new ArrayList<TreeNode>();
        for (SysDept sysDept:treeAll){
            if(sysDept.getpId().equals(topId)){
                TreeNode treeNode = new TreeNode();
                treeNode.setId(sysDept.getId());
                treeNode.setParentId(sysDept.getpId());
                treeNode.setName(sysDept.getDepartmentName());
                tree.add(treeNode);
                for (SysDept sys:treeAll){
                    treeNode.setChildren(initTree(sysDept.getId(),treeAll));
                }
            }
        }
        return tree;
    }
}
