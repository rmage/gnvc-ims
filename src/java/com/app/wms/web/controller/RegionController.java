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
import com.app.wms.engine.db.dao.RegionDao;
import com.app.wms.engine.db.dto.*;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.*;
import com.app.wms.web.util.AppConstant;
import java.util.ArrayList;
import java.util.HashMap;

public class RegionController extends MultiActionController {

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
            // parse parameters
            java.lang.String pregionCode = request.getParameter("regionCode");

            // create the DAO class
            RegionDao dao = DaoFactory.createRegionDao();

            // execute the finder
            //Region dto = dao.findByPrimaryKey(pregionCode);

            final String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                HashMap m = this.getModelByPrimaryKey(request);
                m.put("mode", "edit");
                return new ModelAndView("1_setup/RegionEdit", "model", m);
                //return new ModelAndView("1_setup/RegionEdit", "dto", dto);
            } else {
                //return new ModelAndView( "RegionView", "dto", dto );
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
                List<Region> list = dao.findLimit(((page - 1) * paging) + 1, paging * page);
                int total = dao.countTotalRows();
                System.out.println("++++total row=" + total);
                System.out.println("++" + getApplicationContext());
                HashMap m = new HashMap();
                m.put("cities", list);
                m.put("totalRows", total);
                return new ModelAndView("1_setup/RegionList", "model", m);
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
            // parse parameters
            // create the DAO class
            RegionDao dao = DaoFactory.createRegionDao();

            // execute the finder
            List<Region> dto = dao.findAll();

            return new ModelAndView("RegionResult", "result", dto);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ModelAndView("Error", "th", e);
        }

    }

    /**
     * Method 'findWhereRegionCodeEquals'
     *
     * @param request
     * @param response
     * @throws Exception
     * @return ModelAndView
     */
    public ModelAndView findWhereRegionCodeEquals(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            // parse parameters
            java.lang.String pregionCode = request.getParameter("regionCode");

            // create the DAO class
            RegionDao dao = DaoFactory.createRegionDao();

            // execute the finder
            List<Region> dto = dao.findWhereRegionCodeEquals(pregionCode);

            return new ModelAndView("RegionResult", "result", dto);
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
            // parse parameters
            java.lang.String pname = request.getParameter("name");

            // create the DAO class
            RegionDao dao = DaoFactory.createRegionDao();

            // execute the finder
            List<Region> dto = dao.findWhereNameEquals(pname);

            return new ModelAndView("RegionResult", "result", dto);
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
            RegionDao dao = DaoFactory.createRegionDao();

            // execute the finder
            List<Region> dto = dao.findWhereCreatedByEquals(pcreatedBy);

            return new ModelAndView("RegionResult", "result", dto);
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
            RegionDao dao = DaoFactory.createRegionDao();

            // execute the finder
            List<Region> dto = dao.findWhereCreatedDateEquals(pcreatedDate);

            return new ModelAndView("RegionResult", "result", dto);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ModelAndView("Error", "th", e);
        }

    }

    /**
     * Method 'findWhereUpdatedByEquals'
     *
     * @param request
     * @param response
     * @throws Exception
     * @return ModelAndView
     */
    public ModelAndView findWhereUpdatedByEquals(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            // parse parameters
            java.math.BigDecimal pupdatedBy = new BigDecimal(request.getParameter("updatedBy"));

            // create the DAO class
            RegionDao dao = DaoFactory.createRegionDao();

            // execute the finder
            List<Region> dto = dao.findWhereUpdatedByEquals(pupdatedBy);

            return new ModelAndView("RegionResult", "result", dto);
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
            RegionDao dao = DaoFactory.createRegionDao();

            // execute the finder
            List<Region> dto = dao.findWhereUpdatedDateEquals(pupdatedDate);

            return new ModelAndView("RegionResult", "result", dto);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ModelAndView("Error", "th", e);
        }

    }

    private HashMap getModelByPrimaryKey(HttpServletRequest request) throws Exception {
        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            java.lang.String pregionCode = request.getParameter("regionCode");

            RegionDao dao = DaoFactory.createRegionDao();
            Region dto = null;

            String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                dto = dao.findByPrimaryKey(pregionCode);
            }

            if (dto == null) {
                dto = new Region();
                dto.setRegionCode("");
                dto.setName("");
                dto.setIsActive("Y");
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
        return new ModelAndView("1_setup/RegionAdd", "model", m);
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
//        java.lang.String pregionCode = request.getParameter("regionCode");
//        java.lang.String pname = request.getParameter("name");
//        java.lang.String pisActive = request.getParameter("isActive");
//        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
//        java.math.BigDecimal pcreatedBy = new BigDecimal(lu.getUserId());
//
//        java.util.Date now = new java.util.Date();
//
//        // create the DAO class
//        RegionDao dao = DaoFactory.createRegionDao();
//
//        Region dto = dao.findByPrimaryKey(pregionCode);
//        boolean isCreate = dto == null;
//        if (isCreate) {
//            dto = new Region();
//            dto.setCreatedBy(pcreatedBy);
//            dto.setCreatedDate(now);
//        }
//
//        dto.setRegionCode(pregionCode);
//        dto.setName(pname);
//        dto.setIsActive(pisActive);
//
//        if (isCreate) {
//            dao.insert(dto);
//        } else {
//            dto.setUpdatedBy(pcreatedBy);
//            dto.setUpdatedDate(now);
//            dao.update(dto.createPk(), dto);
//        }
//
//        return new ModelAndView("1_setup/RegionView", "dto", dto);

        boolean isCreate = true;
        String strError = "";
        java.lang.String mode = request.getParameter("mode");
        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            BigDecimal userId = new BigDecimal(lu.getUserId());
            java.util.Date now = new java.util.Date();

            if (mode.equalsIgnoreCase("create")) {
                isCreate = true;
            } else {
                isCreate = false;
            }

            java.lang.String pregionCode = request.getParameter("regionCode");
            if (pregionCode.trim().isEmpty()) {
                strError += "Region Code Cannot be Empty!" + AppConstant.EOL;
            }

            RegionDao dao = DaoFactory.createRegionDao();
            Region dto = null;
            if (isCreate) {
                dto = new Region();
            } else {
                dto = dao.findByPrimaryKey(pregionCode);
            }

            java.lang.String pname = request.getParameter("name");
            if (pname.trim().isEmpty()) {
                strError += "Name Cannot be Empty!" + AppConstant.EOL;
            }

            java.lang.String pisActive = request.getParameter("isActive");

            if (strError.length() > 0) {
                throw new Exception(strError);
            }

            if (isCreate) {
                dto.setRegionCode(pregionCode);
                dto.setCreatedBy(userId);
                dto.setCreatedDate(now);
            }

            dto.setName(pname);
            dto.setIsActive(pisActive);

            if (isCreate) {
                dao.insert(dto);
            } else {
                dto.setUpdatedBy(userId);
                dto.setUpdatedDate(now);
                dao.update(dto.createPk(), dto);
            }

            return new ModelAndView("1_setup/RegionView", "dto", dto);

        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            String errorMsg = "Unique Key Constraint [Region Code]!" + AppConstant.EOL;
            HashMap m = this.getModelByPrimaryKey(request);
            m.put("mode", mode);
            m.put("msg", errorMsg);
            System.out.println(errorMsg);
            return new ModelAndView(isCreate ? "1_setup/RegionAdd" : "1_setup/RegionEdit", "model", m);
        } catch (Exception e) {
            String errorMsg = e.getMessage();
            HashMap m = this.getModelByPrimaryKey(request);
            m.put("mode", mode);
            m.put("msg", errorMsg);
            System.out.println(errorMsg);
            return new ModelAndView(isCreate ? "1_setup/RegionAdd" : "1_setup/RegionEdit", "model", m);
        }

    }

    public ModelAndView inactivate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        java.lang.String pregionCode = request.getParameter("regionCode");

        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        java.math.BigDecimal pcreatedBy = new BigDecimal(lu.getUserId());
        //java.math.BigDecimal pcreatedBy = new BigDecimal(1); // ini harus di passing ya
        //java.util.Date pcreatedDate = (request.getParameter("createdDate") == null || request.getParameter("createdDate").trim().length() == 0) ? null : DateFormat.getDateTimeInstance().parse(request.getParameter("createdDate"));

        RegionDao dao = DaoFactory.createRegionDao();

        Region dto = dao.findByPrimaryKey(pregionCode);

        if (dto != null) {
            dto.setIsActive(AppConstant.STATUS_FALSE);
            dto.setUpdatedBy(pcreatedBy);
            dto.setUpdatedDate(new java.util.Date());
            dao.update(dto.createPk(), dto);
        } else {
            throw new Exception("Region dengan Code" + pregionCode + " tidak ada");
        }

        List<Region> list = dao.findAll();

        //int total = 0; //dao.countTotalRows(); --> di ubah dulu, belum ada di DAO nya tri, 16 juni 2010

        HashMap m = new HashMap();


        m.put("cities", list);
        m.put("totalRows", 0); // gak kepake

        return new ModelAndView("1_setup/RegionList", "model", m);
    }

    public ModelAndView map(HttpServletRequest request, HttpServletResponse response) throws Exception {

        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        RegionDao dao = DaoFactory.createRegionDao();
        List<Region> listReg = dao.findAll();

        String regionCode = request.getParameter("regionCode");

        String strAction = request.getParameter("btnAction");
        String[] pbgCodes = request.getParameterValues("bgCode");


        if (regionCode == null && strAction == null && listReg.size() > 0) {
            regionCode = listReg.get(0).getRegionCode();
        }

        HashMap m = new HashMap();
        m.put("regions", listReg);
        m.put("regionCode", regionCode);
        if ((regionCode == null ? "" != null : !regionCode.equals(""))) {

//            String mode = request.getParameter("mode");
//            if ((mode == null ? "" != null : mode.equals("assign"))) {
//                String toAssign = request.getParameter("bgCode");
//                List<String> list = new ArrayList<String>();
//                list.add(toAssign);
//
//                dao.assignBg(regionCode, list);
//            }
//
//            if ((mode == null ? "" != null : mode.equals("remove"))) {
//                String toRemove = request.getParameter("bgCode");
//                List<String> list = new ArrayList<String>();
//                list.add(toRemove);
//
//                dao.removeBg(list);
//            }

            if (pbgCodes != null) {
                List<String> list = new ArrayList<String>();
                for (String pbgCode : pbgCodes) {
                    list.add(pbgCode);
                }

                if (strAction.equals("Delete")) {
                    dao.removeBg(list);
                }

                if (strAction.equals("Add")) {
                    dao.assignBg(regionCode, list);
                }
            }


//            List<Bg> assigned = dao.findAssignedBg(regionCode, lu.getIsWic(), lu.getIsDsa()); //dao.findAssignedBg(regionCode);
 //           List<Bg> unAssigned = dao.findUnAssignedBg(lu.getIsWic(), lu.getIsDsa());

            //System.out.println("region: " + regionCode + " assigned: " + assigned.size() + " unassigned: " + unAssigned.size());

  //          m.put("bgAssigned", assigned);
    //        m.put("bgUnassigned", unAssigned);
        }




        return new ModelAndView("1_setup/RegionMap", "model", m);


    }
}



