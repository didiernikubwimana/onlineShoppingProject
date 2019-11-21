package edu.mum.wap.model;

import java.io.Serializable;

public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long userId;
	private ERoleType roleType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public ERoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(ERoleType roleType) {
		this.roleType = roleType;
	}

}
