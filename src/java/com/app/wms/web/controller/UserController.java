package com.app.wms.web.controller;

import com.app.wms.web.helper.StringHelper;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import com.app.web.engine.search.UserSearch;
import com.app.wms.engine.db.dao.UserDao;
import com.app.wms.engine.db.dao.UserRoleDao;
import com.app.wms.engine.db.dto.*;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.UserRoleDaoException;
import com.app.wms.engine.db.factory.*;
import com.app.wms.web.util.AppConstant;
import com.app.wms.web.util.EncryptionUtility;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import java.util.Date;
import java.util.HashMap;

public class UserController extends MultiActionController {

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
            final String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                HashMap m = this.getModelByPrimaryKey(request);
                m.put("mode", "edit");
                return new ModelAndView("1_setup/UserEdit", "model", m);
            } else {
                HashMap m = this.searchAndPaging(request, response);
                return new ModelAndView("1_setup/UserList", "model", m);
            }

        } catch (Throwable e) {
            e.printStackTrace();
            return new ModelAndView("Error", "th", e);
        }

    }

    private HashMap searchAndPaging(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            HashMap m = new HashMap();

            Integer page = null;
            Integer paging = null;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            if (request.getParameter("paging") != null) {
                paging = Integer.parseInt(request.getParameter("paging"));
            }
            if (page == null) {
                page = 1;
            }
            if (paging == null) {
                paging = 10;
            }
            int start = (page - 1) * paging + 1;
            int end = start + paging - 1;

            UserSearch c = new UserSearch();
            c.setCode(request.getParameter("code"));
            c.setName(request.getParameter("name"));
            request.getSession().setAttribute("UserSearch", c);

            UserDao dao = DaoFactory.createUserDao();
            List<User> listSearchPage = dao.findUserPaging(c, page);

            int total = 2000;
            m.put("users", listSearchPage);
            m.put("totalRows", total);
            m.put("page", page);
            m.put("paging", paging);

            return m;

        } catch (Exception e) {
            throw e;
        }
    }

    private HashMap getModelByPrimaryKey(HttpServletRequest request) throws Exception {
        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            String puserId = request.getParameter("userId");
            UserDao dao = DaoFactory.createUserDao();
            User dto = null;

            String mode = request.getParameter("mode");
            if (mode != null && puserId != null && mode.equals("edit")) {
                dto = dao.findByPrimaryKey(puserId);
            }

            if (dto == null) {
                dto = new User();
                dto.setCode("");
                dto.setName("");
                dto.setUsername("");
                dto.setPassword("");
                dto.setEmail("");
                dto.setIsActive("Y");
            }

            UserRoleDao daoRole = DaoFactory.createUserRoleDao();
            List<UserRole> list = daoRole.findAll();

            HashMap m = new HashMap();
            m.put("dto", dto);
            m.put("roles", list);

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
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap m = this.getModelByPrimaryKey(request);
        m.put("mode", "create");

        return new ModelAndView("1_setup/UserAdd", "model", m);
    }

    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean isCreate = true;
        String strError = "";
        Date now = new Date();
        String mode = request.getParameter("mode");
        User dto = null;
        try {
            if (mode.equalsIgnoreCase("create")) {
                isCreate = true;
            } else {
                isCreate = false;
            }

            String puserId = request.getParameter("userId");
            UserDao dao = DaoFactory.createUserDao();
            if (isCreate) {
                dto = new User();
            } else {
                dto = dao.findByPrimaryKey(puserId);
            }

            String pname = request.getParameter("name");
            if (pname.trim().isEmpty()) {
                strError += "Name Cannot be Empty!" + AppConstant.EOL;
            }

            String pusername = request.getParameter("username");
            if (pusername.trim().isEmpty()) {
                strError += "Username Cannot be Empty!" + AppConstant.EOL;
            }

            String ppassword = request.getParameter("password");
            String ppasswordVer = request.getParameter("passwordVer");

            String pemail = request.getParameter("email");
            if (pemail.trim().isEmpty()) {
                strError += "Email Cannot be Empty!" + AppConstant.EOL;
            }

            String proleCode = request.getParameter("roleCode");
            String pisActive = request.getParameter("isActive");

            String whCode = request.getParameter("whCode");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            String userId = lu.getUserId();

            if (isCreate) {
                if (ppassword.trim().isEmpty()) {
                    strError += "Password Cannot be Empty!" + AppConstant.EOL;
                } else if (StringHelper.trim(ppassword).length() < 3) {
                    strError += "Password Must be 3 character or more!" + AppConstant.EOL;
                } else if (!ppasswordVer.equals(ppassword)) {
                    strError += "Password and Verification do not match!" + AppConstant.EOL;
                }
                dto.setPassword(ppassword);
                dto.setCreatedBy(userId);
                dto.setCreatedDate(now);
            }

            dto.setCode("");
            dto.setName(pname);
            dto.setUsername(pusername);

            dto.setRoleCode(proleCode);
            dto.setEmail(pemail);
            dto.setIsActive("Y");
            dto.setCorpId("");
            dto.setWhCode(whCode);
            dto.setUpdatedDate(new java.util.Date());

            if (strError.length() > 0) {
                throw new Exception(strError);
            }

            if (isCreate) {
                dao.insert(dto);
            } else {
                String oldPwd = request.getParameter("oldPwd");
                System.out.println("oldPwd =" + EncryptionUtility.getEncrypted(oldPwd.trim()));
                String newPwd = request.getParameter("newPwd");

                if (!EncryptionUtility.getEncrypted(oldPwd).equals((dto.getPassword()))) {

                    System.out.println("dtoPwd =" + dto.getPassword());
                    HashMap m = new HashMap();
                    m.put("msg", "Old password does not match");
                    m.put("dto", dto);

                    UserRoleDao daoRole = DaoFactory.createUserRoleDao();
                    List<UserRole> list = daoRole.findAll();
                    m.put("roles", list);
                    return new ModelAndView("1_setup/UserEdit", "model", m);
                }

                dto.setPassword(newPwd);
                dto.setUpdatedBy(userId);
                dto.setUpdatedDate(now);
                dao.update(dto.createPk(), dto);
            }

            return new ModelAndView("1_setup/UserView", "dto", dto);
            /*
             } catch (org.springframework.dao.DataIntegrityViolationException e) {
             String errorMsg = "Unique Key Constraint [UserId]!" + AppConstant.EOL;
             HashMap m = this.getModelByPrimaryKey(request);
             m.put("dto", dto);
             m.put("mode", mode);
             m.put("msg", errorMsg);
             System.out.println(errorMsg);
            
             e.printStackTrace();
            
             if (isCreate) {
             return new ModelAndView("1_setup/UserAdd", "model", m);
             } else {
             return new ModelAndView("1_setup/UserEdit", "model", m);
             }
             */
        } catch (Exception e) {
            String errorMsg = e.getMessage();
            HashMap m = this.getModelByPrimaryKey(request);
            m.put("dto", dto);
            m.put("mode", mode);
            m.put("msg", errorMsg);

            e.printStackTrace();

            System.out.println(errorMsg);
            if (isCreate) {
                return new ModelAndView("1_setup/UserAdd", "model", m);
            } else {
                return new ModelAndView("1_setup/UserEdit", "model", m);
            }
        }

    }

    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        String userId = new String(lu.getUserId());

        String puserId = request.getParameter("key");

        UserDao dao = DaoFactory.createUserDao();
        User dto = dao.findByPrimaryKey(puserId);
        if (dto != null /*&& dto.getPassword().equalsIgnoreCase(request.getParameter("password").trim())*/) {
//            dto.setIsActive(AppConstant.STATUS_FALSE);
//            dto.setUpdatedBy(userId);
//            dto.setUpdatedDate(new Date());
//            dao.updateInactivate(dto.createPk(), dto);
            dao.delete(dto.createPk());
        }
        HashMap m = this.searchAndPaging(request, response);
        return new ModelAndView("1_setup/UserList", "model", m);
    }

    //Modified 21 April 2014
    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        UserDao userDao = DaoFactory.createUserDao();

        pw.print("{\"maxpage\": " + userDao.ajaxMaxPage(request.getParameter("where"), new BigDecimal(request.getParameter("show"))) + ",\"data\": [");
        List<User> ps = userDao.ajaxSearch(request.getParameter("where"), request.getParameter("order"), Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10));
        for (User x : ps) {
            if (b) {
                pw.print(",");
            }
            pw.print("{\"1\": \"" + x.getUserId() + "\", ");
            pw.print("\"2\": \"" + x.getUserId() + "\", ");
            pw.print("\"3\": \"" + x.getUsername() + "\", ");
            pw.print("\"4\": \"" + x.getName() + "\", ");
            pw.print("\"5\": \"" + x.getRoleCode() + "\", ");
            pw.print("\"6\": \"" + x.getIsActive() + "\"}");
            b = Boolean.TRUE;
        }
        pw.print("]}");
    }

    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws UserRoleDaoException {
        String id = request.getParameter("key");
        UserDao userDao = DaoFactory.createUserDao();
        UserRoleDao daoRole = DaoFactory.createUserRoleDao();
        User dto = userDao.findId(id);
        List<UserRole> list = daoRole.findAll();
        Map map = new HashMap();
        map.put("mode", dto);
        map.put("roles", list);
        return new ModelAndView("1_setup/UserEdit", "model", map);
    }
}
