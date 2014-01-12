/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dto.map;

/**
 *
 * @author root
 */
public class LoginUser
{

    private String userId;
    private String username;
    private String password;
    private String roleCode;
    private String corpId;
    private String roleName;
    private Integer roleLevel;
    private String whCode;
    private String departmentCode;
    
    public LoginUser(){
    	
    }
    
	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getWhCode() {
		return whCode;
	}

	public void setWhCode(String whCode) {
		this.whCode = whCode;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getRoleCode()
    {
        return roleCode;
    }

    public void setRoleCode(String roleCode)
    {
        this.roleCode = roleCode;
    }

    public Integer getRoleLevel()
    {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel)
    {
        this.roleLevel = roleLevel;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    @Override
    public String toString()
    {
        return "LoginUser [" + "corpId= " + corpId + " " +"whCode= "+whCode + " " + "password= " + password + " " + "roleCode= " + roleCode + " " + "roleLevel= " + roleLevel + " " + "roleName= " + roleName + " " + "userId= " + userId + " " + "username= " + username + " " + "departmentCode= " + departmentCode + "]";
    }
}
