package com.app.wms.web.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import com.app.wms.engine.db.dao.AppMenuDao;
import com.app.wms.engine.db.dao.AppMenuGroupDao;
import com.app.wms.engine.db.dto.*;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.*;
import com.app.wms.web.helper.StringHelper;
import com.app.wms.web.util.AppConstant;
import java.util.HashMap;

public class AppMenuController extends MultiActionController {

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
            String btnGroup = request.getParameter("btnGroup");
            java.lang.String pmenuCode = request.getParameter("menuCode");
            java.lang.String pgroupCode = request.getParameter("groupCode");

            AppMenuGroupDao daoGroup = DaoFactory.createAppMenuGroupDao();
            AppMenuGroup dtoGroup;

            HashMap m = new HashMap();

            final String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                AppMenuDao dao = DaoFactory.createAppMenuDao();
                AppMenu dto = dao.findByPrimaryKey(pmenuCode);
                m.put("dto", dto);
                return new ModelAndView("100_menu/AppMenuEdit", "model", m);
            } else if (StringHelper.emptyIfNull(btnGroup).equals("Add")) {

                return new ModelAndView("100_menu/AppMenuGroupAdd");
            } else if (StringHelper.emptyIfNull(btnGroup).equals("Edit") && !StringHelper.emptyIfNull(pgroupCode).equals("ALL")) {
                dtoGroup = daoGroup.findByPrimaryKey(pgroupCode);
                m.put("dto", dtoGroup);
                return new ModelAndView("100_menu/AppMenuGroupEdit", "model", m);
            } else if (StringHelper.emptyIfNull(btnGroup).equals("Delete") && !StringHelper.emptyIfNull(pgroupCode).equals("ALL")) {
                dtoGroup = daoGroup.findByPrimaryKey(pgroupCode);
                daoGroup.delete(dtoGroup.createPk());
                return listAll(request, response);
            } else {
                return listAll(request, response);
            }

        } catch (Throwable e) {
            e.printStackTrace();
            return new ModelAndView("Error", "th", e);
        }

    }

    public ModelAndView listAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        java.lang.String pgroupCode = request.getParameter("groupCode");
        AppMenuDao daoMenu = DaoFactory.createAppMenuDao();
        AppMenuGroupDao daoGroup = DaoFactory.createAppMenuGroupDao();

        try {
            List<AppMenu> listMenu;

            if (pgroupCode == null) {
                pgroupCode = "ALL";
            }

            if (StringHelper.emptyIfNull(pgroupCode).equals("ALL")) {
                listMenu = daoMenu.findAll();
            } else {
                listMenu = daoMenu.findByMenuGroup(pgroupCode);
            }
            List<AppMenuGroup> listGroup = daoGroup.findAll();

            HashMap m = new HashMap();
            m.put("menus", listMenu);
            m.put("groups", listGroup);
            m.put("groupCode", pgroupCode);

            return new ModelAndView("100_menu/AppMenuList", "model", m);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("Error", "th", e);
        }
    }

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws Exception {
        java.lang.String pgroupCode = request.getParameter("groupCode");
        AppMenu dto = new AppMenu();
        dto.setGroupCode(pgroupCode);

        AppMenuGroupDao daoGroup = DaoFactory.createAppMenuGroupDao();
        List<AppMenuGroup> listGroup = daoGroup.findAll();

        HashMap m = new HashMap();
        m.put("dto", dto);
        m.put("groups", listGroup);
        return new ModelAndView("100_menu/AppMenuAdd", "model", m);
    }

    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws Exception {

        java.lang.String pgroupCode = request.getParameter("groupCode");
        java.lang.String pmenuCode = request.getParameter("menuCode");
        java.lang.String pname = request.getParameter("name");
        java.lang.String purl = request.getParameter("url");
        String psortNo = request.getParameter("sortNo");
        String isdelete = "N";
        java.util.Date now = new java.util.Date();

        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        String userId = (String) lu.getUserId();

        String strError = "";
        if (pmenuCode.trim().isEmpty()) {
            strError += "Menu Code Cannot be Empty!" + AppConstant.EOL;
        }
        if (pname.trim().isEmpty()) {
            strError += "Menu Name Cannot be Empty!" + AppConstant.EOL;
        }
        if (pgroupCode.trim().isEmpty()) {
            strError += "Group Code Cannot be Empty!" + AppConstant.EOL;
        }
        if (purl.trim().isEmpty()) {
            strError += "Url Cannot be Empty!" + AppConstant.EOL;
        }

        AppMenuDao dao = DaoFactory.createAppMenuDao();

        AppMenu dto = dao.findByPrimaryKey(pmenuCode);
        boolean isCreate = dto == null;
        if (isCreate) {
            dto = new AppMenu();
            dto.setMenuCode(pmenuCode);
            dto.setGroupCode(pgroupCode);
            dto.setIs_delete(isdelete);
            dto.setCreatedBy(userId);
            dto.setCreatedDate(now);
        }

        dto.setName(pname);
        dto.setSortNo(psortNo);
        dto.setUrl(purl);
        dto.setUpdatedBy(userId);
        dto.setUpdatedDate(now);

        try {
            if (strError.length() > 0) {
                throw new Exception(strError);
            }

            if (isCreate) {
                dao.insert(dto);
            } else {
                dao.update(dto.createPk(), dto);
            }

            return new ModelAndView("100_menu/AppMenuView", "dto", dto);
        } catch (Exception e) {
            HashMap m = new HashMap();
            m.put("dto", dto);
            m.put("msg", e.getMessage());

            if (isCreate) {
                AppMenuGroupDao daoGroup = DaoFactory.createAppMenuGroupDao();
                List<AppMenuGroup> listGroup = daoGroup.findAll();
                m.put("groups", listGroup);
            }
            return new ModelAndView(isCreate ? "100_menu/AppMenuAdd" : "100_menu/AppMenuEdit", "model", m);
        }
    }

    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {

        java.lang.String pmenuCode = request.getParameter("menuCode");

        AppMenuDao dao = DaoFactory.createAppMenuDao();
        AppMenu dto;
        dto = dao.findByPrimaryKey(pmenuCode);
        dao.delete(dto.createPk());

        return listAll(request, response);
    }

    public ModelAndView saveGroup(HttpServletRequest request, HttpServletResponse response) throws Exception {

        java.lang.String pgroupCode = request.getParameter("groupCode");
        java.lang.String pname = request.getParameter("name");
        String psortNo = request.getParameter("sortNo");
        java.util.Date now = new java.util.Date();
        String isDelete = "N";
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        String userId = lu.getUserId();

        String strError = "";
        if (pgroupCode.trim().isEmpty()) {
            strError += "Code Cannot be Empty!" + AppConstant.EOL;
        }
        if (pname.trim().isEmpty()) {
            strError += "Name Cannot be Empty!" + AppConstant.EOL;
        }

        AppMenuGroupDao dao = DaoFactory.createAppMenuGroupDao();

        AppMenuGroup dto = dao.findByPrimaryKey(pgroupCode);
        boolean isCreate = dto == null;
        if (isCreate) {
            dto = new AppMenuGroup();
            dto.setIsDelete(isDelete);
            dto.setCreatedBy(userId);
            dto.setCreatedDate(now);
        }

        dto.setGroupCode(pgroupCode);
        dto.setName(pname);
        dto.setSortNo(psortNo);
        dto.setIsDelete("N");
        dto.setUpdatedBy(userId);
        dto.setUpdatedDate(now);

        try {
            if (strError.length() > 0) {
                throw new Exception(strError);
            }
            if (isCreate) {
                dao.insert(dto);
            } else {
                dao.update(dto.createPk(), dto);
            }
            return new ModelAndView("100_menu/AppMenuGroupView", "dto", dto);
        } catch (Exception e) {
            HashMap m = new HashMap();
            m.put("dto", dto);
            m.put("msg", e.getMessage());
            return new ModelAndView(isCreate ? "100_menu/AppMenuGroupAdd" : "100_menu/AppMenuGroupEdit", "model", m);
        }

    }
}
