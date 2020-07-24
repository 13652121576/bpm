package com.manage.common.base.domain;

import java.util.List;

/**
 * Tree基类
 */
public class TreeNode
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 父菜单名称 */
    private String name;

    /** 父菜单ID */
    private String parentId;

    /** 子集 */
    private List<TreeNode> children;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}