package com.app.wms.web.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.*;
import java.math.*;
import java.util.Date;
import java.util.List;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.dao.AppMenuDao;
import com.app.wms.engine.db.dao.AppMenuGroupDao;
import com.app.wms.engine.db.dao.AppMenuRoleDao;
import com.app.wms.engine.db.dao.UserRoleDao;
import com.app.wms.engine.db.dto.*;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.*;
import com.app.wms.web.helper.StringHelper;
import java.util.HashMap;

public class AppMenuRoleController extends MultiActionController {

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
	try {
	    java.lang.String proleCode = request.getParameter("roleCode");
	    java.lang.String pmenuCode = request.getParameter("menuCode");

	    HashMap m = new HashMap();
	    AppMenuRole dto;

	    final String mode = request.getParameter("mode");
	    if (mode != null && mode.equals("edit")) {
		AppMenuRoleDao dao = DaoFactory.createAppMenuRoleDao();
		dto = dao.findByPrimaryKey(proleCode, pmenuCode);
		m.put("dto", dto);
		return new ModelAndView("AppMenuRoleEdit", "model", m);
	    } else {
		m = this.fillData(request, response);
		return new ModelAndView("100_menu/AppMenuRoleList", "model", m);
	    }

	} catch (Throwable e) {
	    e.printStackTrace();
	    return new ModelAndView("Error", "th", e);
	}

    }

    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		java.lang.String strAction = request.getParameter("btnAction");
		java.lang.String proleCode = request.getParameter("roleCode");
		java.lang.String[] pmenuCodes = request.getParameterValues("menuCode");
		java.lang.String pisView = request.getParameter("isView");
		java.lang.String pisCreate = request.getParameter("isCreate");
		java.lang.String pisEdit = request.getParameter("isEdit");
		java.lang.String pisDelete = request.getParameter("isDelete");
		java.util.Date now = new java.util.Date();
		LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
		String userId = lu.getUserId();
	
		if (pisView == null) {
		    pisView = "Y";
		}
		if (pisCreate == null) {
		    pisCreate = "Y";
		}
		if (pisEdit == null) {
		    pisEdit = "Y";
		}
		if (pisDelete == null) {
		    pisDelete = "N";
		}
	
		AppMenuRoleDao dao = DaoFactory.createAppMenuRoleDao();
		AppMenuRole dto;
	
		if (pmenuCodes != null) {
		    if (strAction.equals("Delete")) {
			for (String pmenuCode : pmenuCodes) {
			    dto = dao.findByPrimaryKey(proleCode, pmenuCode);
			    if (dto != null) {
				dao.delete(dto.createPk());
			    }
			}
		    }
		    if (strAction.equals("Change Status")) {
			for (String pmenuCode : pmenuCodes) {
			    dto = dao.findByPrimaryKey(proleCode, pmenuCode);
			    if (dto != null) {
				dto.setIsView(pisView);
				dto.setIsCreate(pisCreate);
				dto.setIsEdit(pisEdit);
				dto.setIsDelete(pisDelete);
	
				dto.setUpdatedBy(userId);
				dto.setUpdatedDate(now);
				dao.update(dto.createPk(), dto);
			    }
			}
		    }
		    if (strAction.equals("Add")) {
			for (String pmenuCode : pmenuCodes) {
			    dto = new AppMenuRole();
			    dto.setCreatedBy(userId);
			    dto.setCreatedDate(now);
	
			    dto.setRoleCode(proleCode);
			    dto.setMenuCode(pmenuCode);
			    dto.setIsView(pisView);
			    dto.setIsCreate(pisCreate);
			    dto.setIsEdit(pisEdit);
			    dto.setIsDelete(pisDelete);
	
			    dto.setUpdatedBy(userId);
			    dto.setUpdatedDate(now);
	
			    dao.insert(dto);
			}
		    }
		}
		HashMap m = this.fillData(request, response);
		return new ModelAndView("100_menu/AppMenuRoleList", "model", m);
    }

	    HashMap fillData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
	
	    java.lang.String proleCode = request.getParameter("roleCode");
		java.lang.String groupCode = request.getParameter("groupCode");
	
		HashMap m = new HashMap();
	
		AppMenuDao dao = DaoFactory.createAppMenuDao();
		List<AppMenu> dto = null;
		AppMenuGroupDao daoGroup = DaoFactory.createAppMenuGroupDao();
		List<AppMenuGroup> dtoGroup = daoGroup.findAll();
		AppMenuRoleDao daoRole = DaoFactory.createAppMenuRoleDao();
		List<AppMenuRole> dtoRole = null;
		UserRoleDao daoUserRole = DaoFactory.createUserRoleDao();
		List<UserRole> dtoUserRole = daoUserRole.findAll();
	
		if (groupCode == null) {
		    //groupCode = "ALL";
		    groupCode = dtoGroup.get(0).getGroupCode();
		}
		if (proleCode == null) {
		    proleCode = dtoUserRole.get(0).getRoleCode();
		}
	
		dtoRole = daoRole.findByUserRole(proleCode);
	
		StringBuffer filter = new StringBuffer();
		if (!StringHelper.emptyIfNull(groupCode).equals("ALL")) {
		    filter.append(String.format(" APP_MENU.GROUP_CODE = '%s' ", groupCode));
		}
		
		dto = dao.findWhereNotInAppMenuRole(proleCode, filter.toString());
	
                System.out.println(">>> "+dtoRole.size());
                
		m.put("menuGroups", dtoGroup);
		m.put("menuRoles", dtoRole);
		m.put("userRoles", dtoUserRole);
		m.put("menus", dto);
		m.put("roleCode", proleCode);
		m.put("groupCode", groupCode);
		return m;
	    }
}
