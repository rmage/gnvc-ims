/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 *
 * @author root
 */
public class LoginUserMap implements ParameterizedRowMapper<LoginUser>
{
       public LoginUser mapRow(ResultSet rs, int row) throws SQLException
    {
    	   System.out.println("@ rs  = "+rs);
      	   System.out.println("@ rs username = "+rs.getString("USERNAME"));
    	   
           LoginUser dto = new LoginUser();
           dto.setUserId(rs.getString("USER_ID"));
           dto.setUsername(rs.getString("USERNAME"));
           dto.setPassword(rs.getString("PASSWORD"));
           dto.setRoleCode(rs.getString("ROLE_CODE"));
           //dto.setCorpId(rs.getString("CORP_ID"));
           dto.setRoleName(rs.getString("ROLE_NAME"));
           dto.setRoleLevel(rs.getInt("ROLE_LEVEL"));
           //dto.setWhCode(rs.getString("WH_CODE"));
           dto.setDepartmentCode(rs.getString("department_code"));
           
           return dto;
       }
}
