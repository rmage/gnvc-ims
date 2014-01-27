package com.app.wms.web.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.dto.*;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.*;

import java.util.HashMap;

public class UserRoleController extends MultiActionController
{
    /**
     * Method 'findByPrimaryKey'
     * 
     * @param request
     * @param response
     * @throws Exception
     * @return ModelAndView
     */

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            HashMap m = null;
            final String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                m = this.getModelByPrimaryKey(request);
                m.put("mode", "edit");
                return new ModelAndView("1_setup/role/RoleEdit", "model", m);
            } else {
            	UserRoleDao dao = DaoFactory.createUserRoleDao();
            	List<UserRole> list = dao.findAll();
            	m = this.getModelByPrimaryKey(request);
            	m.put("userRoles", list);
               // m = this.searchAndPaging(request, response);
                return new ModelAndView("1_setup/role/RoleList", "model", m);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            return new ModelAndView( "Error", "th", e );
        }

    }
	 
    private HashMap getModelByPrimaryKey(HttpServletRequest request) throws Exception {
        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
           
            UserRoleDao dao = DaoFactory.createUserRoleDao();
            UserRole dto = null;
            
            String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
            	Integer id = Integer.parseInt(request.getParameter("id"));
                dto = dao.findByPrimaryKey(id);
            }

            if (dto == null) {
                dto = new UserRole();
                dto.setRoleCode("");
                dto.setRoleName("");
            }

            DepartmentDao daoDep = DaoFactory.createDepartmentDao();
            List<Department> dropListDepartment = daoDep.findAll();

            HashMap m = new HashMap();
            m.put("dropListDepartment", dropListDepartment);
            m.put("dto", dto);
            return m;
        } catch (Exception e) {
            throw e;
        }
    }
	
	
	/**
	 * Method 'create'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HashMap m = this.getModelByPrimaryKey(request);
		m.put("mode", "create");
		
		return new ModelAndView("1_setup/role/RoleAdd", "model", m);
	}

    /**
     * Method 'save'
     * 
     * @param request
     * @param response
     * @throws Exception
     * @return ModelAndView
     */
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean isCreate = true;
        String strError = "";
        Date now = new Date();
        String mode = request.getParameter("mode");
        UserRole dto = null;
        try{
            if (mode.equalsIgnoreCase("create")) {
                isCreate = true;
            } else {
                isCreate = false;
            }

            UserRoleDao dao = DaoFactory.createUserRoleDao();
            if(isCreate){
                dto = new UserRole();
            }else{
                Integer id = Integer.parseInt(request.getParameter("id"));
                dto = dao.findByPrimaryKey(id);
            }

            String code = request.getParameter("code");
            String name = request.getParameter("name");
            String departmentCode = request.getParameter("departmentCode");

            if(isCreate){
                dto.setRoleCode(code);
                dto.setRoleName(name);
                dto.setDepartmentCode(departmentCode);
            }

            dto.setRoleCode(code);
            dto.setRoleName(name);
            dto.setDepartmentCode(departmentCode);

            if (strError.length() > 0) {
                throw new Exception(strError);
            }

            if(isCreate){
                UserRolePk urp = dao.insert(dto);
                dto.setId(urp.getId());
            } else{
                dao.update(dto.createPk(), dto);
            }

            return new ModelAndView( "1_setup/role/RoleView", "dto", dto );
        } catch (Exception e){
            e.printStackTrace();
            String errorMsg = e.getMessage();
            HashMap m = new HashMap();
            m.put("mode", mode);
            m.put("msg", errorMsg);

            if(isCreate){
                return new ModelAndView( "1_setup/role/RoleAdd", "model", m );
            } else{
                return new ModelAndView( "1_setup/role/RoleEdit", "model", m );
            }
        }
    }

    public ModelAndView inactivate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        
        UserRoleDao userRoleDao = DaoFactory.createUserRoleDao();
        UserRole ur = userRoleDao.findByPrimaryKey(Integer.parseInt(request.getParameter("id")));
        if(ur != null) {
            userRoleDao.delete(ur.createPk());
        }
        
        return findByPrimaryKey(request, response);
    }
}
