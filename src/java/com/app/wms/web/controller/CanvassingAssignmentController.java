package com.app.wms.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import com.app.wms.engine.db.dao.AssignCanvasserDao;
import com.app.wms.engine.db.dao.AssignCanvasserDtlDao;
import com.app.wms.engine.db.dao.DepartmentDao;
import com.app.wms.engine.db.dao.PrsDao;
import com.app.wms.engine.db.dao.PrsDetailDao;
import com.app.wms.engine.db.dao.UserDao;
import com.app.wms.engine.db.dto.AssignCanvasser;
import com.app.wms.engine.db.dto.AssignCanvasserDtl;
import com.app.wms.engine.db.dto.Department;
import com.app.wms.engine.db.dto.Prs;
import com.app.wms.engine.db.dto.PrsDetail;
import com.app.wms.engine.db.dto.User;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.PrsDetailDaoException;
import com.app.wms.engine.db.exceptions.UserDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.helper.StringHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;

public class CanvassingAssignmentController extends MultiActionController {

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            return new ModelAndView("1_setup/CanvasserAssignList2");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("Error", "th", e);
        }
    }

    private HashMap searchAndPaging(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
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

            AssignCanvasser c = new AssignCanvasser();
            c.setPrsnumber(request.getParameter("prsnumber"));
            c.setCanvassername(request.getParameter("canvassername"));

            AssignCanvasserDao dao = DaoFactory.createAssignCanvasserDao();
            AssignCanvasserDtlDao canvasserassignmentDetailDao = DaoFactory.createAssignCanvasserDtlDao();
            List<AssignCanvasser> listSearchPage = dao.findCanvasserAssignPaging(c, page);

            UserDao userDao = DaoFactory.createUserDao();
            for (AssignCanvasser ca : listSearchPage) {
                List<AssignCanvasserDtl> cds = canvasserassignmentDetailDao.findByPrsnumber(ca.getPrsnumber());
                for (AssignCanvasserDtl cd : cds) {
                    User u = userDao.findByPrimaryKey(cd.getUserId());

                    if (ca.getCanvassername() != null) {
                        ca.setCanvassername(ca.getCanvassername() + " :: " + u.getName());
                    } else {
                        ca.setCanvassername(u.getName());
                    }
                }
            }

            int total = 2000;
            m.put("canvasserassignment", listSearchPage);
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
            AssignCanvasserDao dao = DaoFactory.createAssignCanvasserDao();
            AssignCanvasser dto = new AssignCanvasser();

            String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                Integer id = Integer.parseInt(request.getParameter("id"));
                dto = dao.findByPrimaryKey(id);
            }

            if (dto.getPrsnumber() == null) {
                dto.setPrsnumber("");
                dto.setCanvassername("");
            }

            if (dto.getPrsnumber() != null || dto.getCanvassername() != null) {
                dto.setPrsnumber(dto.getPrsnumber());
                dto.setCanvassername(dto.getCanvassername());
            }
            //edit
//            UserDao daoUser = DaoFactory.createUserDao();
//            List <User> dropListUser = daoUser.findRoleCanvasser();

            PrsDao daoPrs = DaoFactory.createPrsDao();
            DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
            List<Prs> dropListPrs = daoPrs.findAllNotInCanvas();
            for (Prs x : dropListPrs) {
                List<Department> d = departmentDao.findWhereDepartmentCodeEquals(x.getDepartmentName());
                x.setDepartmentName("[" + x.getDepartmentName() + "] " + (d.isEmpty() ? "data missing" : d.get(0).getDepartmentName()));
            }

            HashMap m = new HashMap();
            m.put("dto", dto);
//            m.put("dropListUser", dropListUser);
            m.put("dropListPrs", dropListPrs);

            return m;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Method 'findAll'
     *
     * @param request
     * @param response
     * @throws Exception
     * @return ModelAndView
     */
    public ModelAndView findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            AssignCanvasserDao dao = DaoFactory.createAssignCanvasserDao();
            List<AssignCanvasser> dto = dao.findAll();
            return new ModelAndView("1_setup/CanvasserAssignList", "model", dto);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ModelAndView("Error", "th", e);
        }

    }

    public ModelAndView inactivate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        String createdBy = "";
        if (lu == null) {
            HashMap m = new HashMap();
            String msg = "You haven't login or your session has been expired! Please do login again";
            m.put("msg", msg);
            return new ModelAndView("login", "model", m);
        } else {
            createdBy = (String) lu.getUserId();
        }
        AssignCanvasserDao dao = DaoFactory.createAssignCanvasserDao();
        Integer id = Integer.parseInt(request.getParameter("id"));
        AssignCanvasser dto = dao.findByPrimaryKey(id);
        if (dto != null) {
            dto.setUpdatedBy(createdBy);
            dto.setUpdatedDate(new java.util.Date());
            dao.update(dto.createPk(), dto);
        }

        HashMap m = this.searchAndPaging(request, response);
        return new ModelAndView("1_setup/CanvasserAssignList", "model", m);
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
        Map map = new HashMap();
        map = this.getModelByPrimaryKey(request);
        map.put("mode", "create");

        return new ModelAndView("1_setup/CanvasserAssignAdd2", "model", map);
    }

    /**
     * Method 'save'
     *
     * @param request
     * @param response
     * @throws Exception
     * @return ModelAndView
     */
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        PrsDetailDao prsDetailDao = DaoFactory.createPrsDetailDao();
        AssignCanvasserDao canvasserassignmentDao = DaoFactory.createAssignCanvasserDao();
        AssignCanvasserDtlDao canvasserassignmentDetailDao = DaoFactory.createAssignCanvasserDtlDao();

        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        AssignCanvasser dto = new AssignCanvasser();
//        boolean isCreate = true;
//        String strError = "";
//        Date now = new Date();
//        java.lang.String mode = request.getParameter("mode");
//        AssignCanvasser dto = null;
        try {
            /* insert canvassing assignment */
            AssignCanvasser c = new AssignCanvasser();
            c.setPrsnumber(request.getParameter("prsNo"));
            c.setCreatedBy(lu.getUserId());
            c.setCreatedDate(new Date());
            canvasserassignmentDao.insert(c);

            /* insert canvaser */
            int i = 0;
            String[] s = request.getParameterValues("userId");
            List<PrsDetail> pds = prsDetailDao.findWherePrsnumberEquals(c.getPrsnumber());
            for (PrsDetail pd : pds) {
                AssignCanvasserDtl cd = new AssignCanvasserDtl();
                cd.setPrsNumber(pd.getPrsnumber());
                cd.setProductCode(pd.getProductcode());
                cd.setUserId(s[i]);
                i++;
                canvasserassignmentDetailDao.insert(cd);
            }

//            if (mode.equalsIgnoreCase("create")) {
//                isCreate = true;
//            } else {
//                isCreate = false;
//            }
//            AssignCanvasserDao dao = DaoFactory.createAssignCanvasserDao();
//            if (isCreate) {
//                dto = new AssignCanvasser();
//            } else {
//                Integer id = Integer.parseInt(request.getParameter("id"));
//                dto = dao.findByPrimaryKey(id);
//            }
//
//            String prsnumber = request.getParameter("prsnumber");
//            String canvasserid = request.getParameter("canvasserid");
//            List<Canvasserassignment> tmp = dao.findWherePrsnumberEquals(prsnumber);
//
//            if ((isCreate && tmp != null && tmp.size() > 0) || (!isCreate && tmp != null && tmp.size() > 0 && !tmp.get(0).getPrsnumber().equals(prsnumber))) {
//                strError += "PRS Number already exists. Please try a different values" + AppConstant.EOL;
//            }
//
//            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
//            String userId = "";
//            if (lu == null) {
//                HashMap m = new HashMap();
//                String msg = "You haven't login or your session has been expired! Please do login again";
//                m.put("msg", msg);
//                return new ModelAndView("login", "model", m);
//            } else{
//                userId = (String)lu.getUserId();
//            }
//
//            if (isCreate) {
//                dto.setCreatedBy(userId);
//                dto.setCreatedDate(now);
//            }
//            dto.setPrsnumber(prsnumber);
//            dto.setCanvassername(canvasserid);
//            dto.setUpdatedBy(userId);
//            dto.setUpdatedDate(new java.util.Date());
//
//            if (strError.length() > 0) {
//                throw new Exception(strError);
//            }
//
//            if (isCreate) {
//                dao.insert(dto);
//                
//                UserDao userDao = DaoFactory.createUserDao();
//                User u = userDao.findByPrimaryKey(canvasserid);
//                dto.setCanvassername(u.getName());
//            } 
//
//            else {
//                dto.setUpdatedBy(userId);
//                dto.setUpdatedDate(new java.util.Date());
//                dao.update(dto.createPk(), dto);
//            }
//            return new ModelAndView("1_setup/CanvasserAssignView", "dto", dto);
            return findByPrimaryKey(request, response);
        } catch (Exception e) {
            e.printStackTrace();
//            String errorMsg = e.getMessage();
//            HashMap m = this.getModelByPrimaryKey(request);
//            m.put("mode", mode);
//            m.put("msg", errorMsg);
//
//            if (isCreate) {
//                return new ModelAndView("1_setup/CanvasserAssignAdd", "model", m);
//            } else {
//                return new ModelAndView("1_setup/CanvasserAssignEdit", "model", m);
//            }
            return findByPrimaryKey(request, response);
        }
    }

    /* FYA : 07 January 2014 */
    public void getPrs(HttpServletRequest request, HttpServletResponse response)
            throws PrsDetailDaoException, IOException, UserDaoException {

        String prsNo = request.getParameter("key");

        /* get canvasser */
        UserDao daoUser = DaoFactory.createUserDao();
        List<User> us = daoUser.findRoleCanvasser();

        String canvasser = "<select name=\\\"userId\\\" required=\\\"true\\\"><option value=\\\"\\\">-- Pilih Canvasser --</option>";
        for (User u : us) {
            canvasser += "<option value=\\\"" + u.getUserId() + "\\\">" + u.getName() + "</option>";
        }
        canvasser += "</select>";

        /* get prs detail */
        PrsDetailDao prsDetailDao = DaoFactory.createPrsDetailDao();
        List<PrsDetail> pds = prsDetailDao.findWherePrsnumberEquals(prsNo);

        String out = "[";
        for (PrsDetail pd : pds) {
            if (!out.equals("[")) {
                out += ",";
            }

            out += "{\"prsNo\": \"" + pd.getPrsnumber() + "\", "
                    + "\"itemCode\": \"" + pd.getProductcode() + "\", "
                    + "\"itemName\": \"" + pd.getProductname() + "\", "
                    + "\"quantity\": \"" + pd.getQty() + "\", "
                    + "\"canvasser\": \"" + canvasser + "\"}";
        }
        out += "]";
        response.getWriter().print(out);
    }

    public void ajaxDocument(HttpServletRequest request, HttpServletResponse response)
            throws PrsDetailDaoException, UserDaoException, IOException {

        String prsNo = request.getParameter("key");

        /* get prs detail */
        UserDao daoUser = DaoFactory.createUserDao();
        PrsDetailDao prsDetailDao = DaoFactory.createPrsDetailDao();
        AssignCanvasserDtlDao canvasserassignmentDetailDao = DaoFactory.createAssignCanvasserDtlDao();

        List<PrsDetail> pds = prsDetailDao.findWherePrsnumberEquals(prsNo);
        List<AssignCanvasserDtl> cds = canvasserassignmentDetailDao.findByPrsnumber(prsNo);

        int i = 0;
        String out = "[";
        for (PrsDetail pd : pds) {
            if (!out.equals("[")) {
                out += ",";
            }

            User u = daoUser.findByPrimaryKey(cds.get(i).getUserId());

            out += "{\"prsNo\": \"" + pd.getPrsnumber() + "\", "
                    + "\"itemCode\": \"" + pd.getProductcode() + "\", "
                    + "\"itemName\": \"" + pd.getProductname() + "\", "
                    + "\"quantity\": \"" + pd.getQty() + "\", "
                    + "\"canvasser\": \"" + u.getName() + "\"}";
            i++;
        }
        out += "]";
        response.getWriter().print(out);
    }

    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response) throws IOException, UserDaoException {
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();

        AssignCanvasserDao dao = DaoFactory.createAssignCanvasserDao();
        AssignCanvasserDtlDao canvasserassignmentDetailDao = DaoFactory.createAssignCanvasserDtlDao();
        UserDao userDao = DaoFactory.createUserDao();

        pw.print("{\"maxpage\": " + dao.ajaxMaxPage(request.getParameter("where"), new BigDecimal(request.getParameter("show"))) + ",\"data\": [");
        List<AssignCanvasser> ca = dao.ajaxSearch(request.getParameter("where"), request.getParameter("order"), Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10));
        for (AssignCanvasser x : ca) {
            List<AssignCanvasserDtl> cds = canvasserassignmentDetailDao.findByPrsnumber(x.getPrsnumber());
            for (AssignCanvasserDtl cd : cds) {
                User u = userDao.findByPrimaryKey(cd.getUserId());
                if (x.getCanvassername() != null) {
                    x.setCanvassername(x.getCanvassername() + " :: " + u.getName());
                } else {
                    x.setCanvassername(u.getName());
                }
            }
            if (b) {
                pw.print(",");
            }
            pw.print("{\"1\": \"" + x.getId() + "\", ");
            pw.print("\"2\": \"" + x.getPrsnumber() + "\", ");
            pw.print("\"3\": \"" + x.getCanvassername() + "\"}");

            b = Boolean.TRUE;
        }
        pw.print("]}");
    }

    // 2015 Update | by FYA
    public ModelAndView ajaxNSave(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map<String, Object> json = new HashMap<String, Object>();

        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            String data = URLDecoder.decode(request.getParameter("data"), "utf-8");
            String[] separator = StringHelper.getDataSeparator(data, 2);

            data = data.replaceAll(":s:", separator[0]).replaceAll(":se:", separator[1]);
            DaoFactory.createAssignCanvasserDao().ajaxNSave(data, separator[0], separator[1], lu.getUserId());

            json.put("message", "");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
        }

        return new ModelAndView("jsonView", json);
    }

    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            DaoFactory.createAssignCanvasserDao().delete(Integer.parseInt(request.getParameter("key")), lu.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:CanvassingAssignment.htm");
    }

    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();

        try {
            UserDao daoUser = DaoFactory.createUserDao();
            model.put("cs", daoUser.findRoleCanvasser());
            
            int key = Integer.parseInt(request.getParameter("key"));
            AssignCanvasserDao acDao = DaoFactory.createAssignCanvasserDao();
            model.put("acs", acDao.getAssignedCanvasser(key));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("default/purchase/CanvasserAssignmentUpdate", "model", model);
    }
    
    public ModelAndView ajaxNUpdate(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map<String, Object> json = new HashMap<String, Object>();

        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            String data = URLDecoder.decode(request.getParameter("data"), "utf-8");
            String[] separator = StringHelper.getDataSeparator(data, 2);

            data = data.replaceAll(":s:", separator[0]).replaceAll(":se:", separator[1]);
            DaoFactory.createAssignCanvasserDao().ajaxNUpdate(data, separator[0], separator[1], lu.getUserId());

            json.put("message", "");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
        }

        return new ModelAndView("jsonView", json);
    }

}
