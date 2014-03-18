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
import com.app.wms.engine.db.dto.*;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.UomDaoException;
import com.app.wms.engine.db.factory.*;
import com.app.wms.web.util.AppConstant;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class UomController extends MultiActionController {

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
        	HashMap m =  null;
            final String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                m = this.getModelByPrimaryKey(request);
                m.put("mode", "edit");
                return new ModelAndView("1_setup/UOMEdit", "model", m);
            } else {
            	UomDao dao = DaoFactory.createUomDao();
                List<Uom> list = dao.findAll();
                m = this.getModelByPrimaryKey(request);
                m.put("uoms", list);
                return new ModelAndView("1_setup/UOMList", "model", m);

            }

        } catch (Throwable e) {
            e.printStackTrace();
            return new ModelAndView("Error", "th", e);
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
           
            UomDao dao = DaoFactory.createUomDao();

            List<Uom> dto = dao.findAll();

            return new ModelAndView("UomResult", "result", dto);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ModelAndView("Error", "th", e);
        }

    }

    /**
     * Method 'findWhereUomCodeEquals'
     *
     * @param request
     * @param response
     * @throws Exception
     * @return ModelAndView
     */
    public ModelAndView findWhereUomCodeEquals(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            
            java.lang.String puomCode = request.getParameter("uomCode");

            UomDao dao = DaoFactory.createUomDao();

            List<Uom> dto = dao.findWhereUomCodeEquals(puomCode);

            return new ModelAndView("UomResult", "result", dto);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ModelAndView("Error", "th", e);
        }

    }

    /**
     * Method 'findWhereNameEquals'
     *
     * @param request
     * @param response
     * @throws Exception
     * @return ModelAndView
     */
    public ModelAndView findWhereNameEquals(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
           
            java.lang.String pname = request.getParameter("name");

            UomDao dao = DaoFactory.createUomDao();

            List<Uom> dto = dao.findWhereUomNameEquals(pname);

            return new ModelAndView("UomResult", "result", dto);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ModelAndView("Error", "th", e);
        }

    }

    /**
     * Method 'findWhereCreatedByEquals'
     *
     * @param request
     * @param response
     * @throws Exception
     * @return ModelAndView
     */
    public ModelAndView findWhereCreatedByEquals(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            // parse parameters
            java.math.BigDecimal pcreatedBy = new BigDecimal(request.getParameter("createdBy"));

            // create the DAO class
            UomDao dao = DaoFactory.createUomDao();

            // execute the finder
            List<Uom> dto = dao.findWhereCreatedByEquals(pcreatedBy+"");

            return new ModelAndView("UomResult", "result", dto);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ModelAndView("Error", "th", e);
        }

    }

    /**
     * Method 'findWhereCreatedDateEquals'
     *
     * @param request
     * @param response
     * @throws Exception
     * @return ModelAndView
     */
    public ModelAndView findWhereCreatedDateEquals(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            // parse parameters
            java.util.Date pcreatedDate = (request.getParameter("createdDate") == null || request.getParameter("createdDate").trim().length() == 0) ? null : DateFormat.getDateTimeInstance().parse(request.getParameter("createdDate"));

            // create the DAO class
            UomDao dao = DaoFactory.createUomDao();

            // execute the finder
            List<Uom> dto = dao.findWhereCreatedDateEquals(pcreatedDate);

            return new ModelAndView("UomResult", "result", dto);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ModelAndView("Error", "th", e);
        }

    }

    /**
     * Method 'findWhereUpdatedByEquals'
     *
     * @param requestsa
     * @param response
     * @throws Exception
     * @return ModelAndView
     */
    public ModelAndView findWhereUpdatedByEquals(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            // parse parameters
            java.math.BigDecimal pupdatedBy = new BigDecimal(request.getParameter("updatedBy"));

            // create the DAO class
            UomDao dao = DaoFactory.createUomDao();

            // execute the finder
            List<Uom> dto = dao.findWhereUpdatedByEquals(pupdatedBy+"");

            return new ModelAndView("UomResult", "result", dto);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ModelAndView("Error", "th", e);
        }

    }

    /**
     * Method 'findWhereUpdatedDateEquals'
     *
     * @param request
     * @param response
     * @throws Exception
     * @return ModelAndView
     */
    public ModelAndView findWhereUpdatedDateEquals(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            // parse parameters
            java.util.Date pupdatedDate = (request.getParameter("updatedDate") == null || request.getParameter("updatedDate").trim().length() == 0) ? null : DateFormat.getDateTimeInstance().parse(request.getParameter("updatedDate"));

            // create the DAO class
            UomDao dao = DaoFactory.createUomDao();

            // execute the finder
            List<Uom> dto = dao.findWhereUpdatedDateEquals(pupdatedDate);

            return new ModelAndView("UomResult", "result", dto);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ModelAndView("Error", "th", e);
        }

    }

    private HashMap getModelByPrimaryKey(HttpServletRequest request) throws Exception {
        try {
            java.lang.String puomCode = request.getParameter("uomCode");

            UomDao dao = DaoFactory.createUomDao();
            Uom dto = null;

            String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
            	Integer id = Integer.parseInt(request.getParameter("id"));
                dto = dao.findByPrimaryKey(id);
            }

            if (dto == null) {
                dto = new Uom();
                dto.setUomCode("");
                dto.setUomName("");
            }

            HashMap m = new HashMap();
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
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap m = this.getModelByPrimaryKey(request);
        m.put("mode", "create");
        return new ModelAndView("1_setup/UOMAdd", "model", m);
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
        Uom dto = null;
        try {
            if (mode.equalsIgnoreCase("create")) {
                isCreate = true;
            } else {
                isCreate = false;
            }

            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            String userId = "";
            if (lu == null) {
    			HashMap m = new HashMap();
                String msg = "You haven't login or your session has been expired! Please do login again";
                m.put("msg", msg);
                return new ModelAndView("login", "model", m);
            }else{
            	userId = lu.getUserId();
            }
            
            UomDao dao = DaoFactory.createUomDao();
           
            if (isCreate) {
                dto = new Uom();
            } else {
            	Integer id = Integer.parseInt(request.getParameter("id"));
                dto = dao.findByPrimaryKey(id);
            }
            
            String puomCode = request.getParameter("uomCode");
            String puomName = request.getParameter("uomName");
            String remarks = request.getParameter("remarks");
            
            List<Uom> tmp = dao.findWhereUomCodeEquals(puomCode);
            
            if ((isCreate && tmp != null && tmp.size() > 0) || (!isCreate && tmp != null && tmp.size() > 0 && !tmp.get(0).getUomCode().equals(puomCode))) {
                strError += "UoM code already exists. Please try a different values" + AppConstant.EOL;
            }

            if (strError.length() > 0) {
                throw new Exception(strError);
            }

            if (isCreate) {
                dto.setUomCode(puomCode);
                dto.setUomName(puomName);
                dto.setRemarks(remarks);
                dto.setIsActive(request.getParameter("isActive"));
                dto.setCreatedBy(userId);
                dto.setCreatedDate(now);
            }

            dto.setUomCode(puomCode);
            dto.setUomName(puomName);
            dto.setRemarks(remarks);
            dto.setIsActive(request.getParameter("isActive"));
            
            if (isCreate) {
                UomPk up = dao.insert(dto);
                dto.setId(up.getId());
            } else {
                dto.setUpdatedBy(userId);
                dto.setUpdatedDate(now);
                dao.update(dto.createPk(), dto);
            }

            return new ModelAndView("1_setup/UOMView", "dto", dto);

        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            String errorMsg = "Unique Key Constraint [UOM Code]!" + AppConstant.EOL;
            HashMap m = this.getModelByPrimaryKey(request);
            m.put("mode", mode);
            m.put("msg", errorMsg);
            System.out.println("exception >"+errorMsg);
            return new ModelAndView(isCreate ? "1_setup/UOMAdd" : "1_setup/UOMEdit", "model", m);
        } catch (Exception e) {
        	e.printStackTrace();
            String errorMsg = e.getMessage();
            HashMap m = this.getModelByPrimaryKey(request);
            m.put("mode", mode);
            m.put("msg", errorMsg);
            System.out.println("exception >>"+errorMsg);
            return new ModelAndView(isCreate ? "1_setup/UOMAdd" : "1_setup/UOMEdit", "model", m);
        
        }

    }

    public ModelAndView inactivate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        java.lang.String id = request.getParameter("id");

        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        String pcreatedBy = "";
        if (lu == null) {
			HashMap m = new HashMap();
            String msg = "You haven't login or your session has been expired! Please do login again";
            m.put("msg", msg);
            return new ModelAndView("login", "model", m);
        }else{
        	pcreatedBy = lu.getUserId();
        }

        UomDao dao = DaoFactory.createUomDao();
        Uom dto = dao.findByPrimaryKey(Integer.parseInt(id));

        if (dto != null) {
            dto.setIsActive(AppConstant.STATUS_FALSE);
            dto.setIsDelete("Y");
            dto.setUpdatedBy(pcreatedBy);
            dto.setUpdatedDate(new java.util.Date());
            dao.update(dto.createPk(), dto);
        }

        List<Uom> list = dao.findAll();

        HashMap m = new HashMap();


        m.put("uoms", list);
        m.put("totalRows", 0); 

        return new ModelAndView("1_setup/UOMList", "model", m);
    }
    
    public void getUnique(HttpServletRequest request, HttpServletResponse response)
        throws IOException, UomDaoException {
        
        PrintWriter pw = response.getWriter();
        String uniCode = request.getParameter("term");
        UomDao uomDao = DaoFactory.createUomDao();
        List<Uom> uom = uomDao.findWhereUomCodeEquals(uniCode);
        if (uom.isEmpty()) {
            pw.print("{\"status\": false}");
        } else {
            pw.print("{\"status\": true}");
        }
    }
    
}