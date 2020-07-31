package com.manage.common.entity.sys;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 系统用户角色关联表
 * 
 */
public class SysUserRole implements Serializable {

	 private static final long serialVersionUID = 1L;

	 /**主键id**/
	 private String id;

	/**用户id**/
	private String userId;

	/**角色id**/
	 private String roleId;

	 /**创建时间**/
	 private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
