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
import com.app.web.engine.search.CitySearch;
import com.app.wms.engine.db.dao.CityDao;
import com.app.wms.engine.db.dto.*;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.*;
import com.app.wms.web.util.AppConstant;
import java.util.HashMap;

public class CityController extends MultiActionController {

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
            //java.lang.String pcityCode = request.getParameter("cityCode");
            // create the DAO class
            //CityDao dao = DaoFactory.createCityDao();
            // execute the finder
            //City dto = dao.findByPrimaryKey(pcityCode);

            HashMap m = null;
            final String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                m = this.getModelByPrimaryKey(request);
                m.put("mode", "edit");
                return new ModelAndView("1_setup/CityEdit", "model", m);
            } else {
                //return new ModelAndView( "CityView", "dto", dto );
//                Integer page = null;
//                Integer paging = null;
//                if (request.getParameter("page") != null) {
//                    page = Integer.parseInt(request.getParameter("page"));
//                }
//                if (request.getParameter("paging") != null) {
//                    paging = Integer.parseInt(request.getParameter("paging"));
//                }
//                if (page == null) {
//                    page = 1;
//                }
//                if (paging == null) {
//                    paging = 10;
//                }
//                List<City> list = dao.findLimit(((page - 1) * paging) + 1, paging * page);
//                int total = dao.countTotalRows();
//                System.out.println("++++total row=" + total);
//                System.out.println("++" + getApplicationContext());
//                HashMap m = new HashMap();
//                m.put("cities", list);
//                m.put("totalRows", total);
                m = this.searchAndPaging(request, response);
                return new ModelAndView("1_setup/CityList", "model", m);
            }

        } catch (Throwable e) {
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

            // handle masalah search
            CitySearch c = null;
            if (request.getParameter("btnViewAll") != null) {
                request.getSession().setAttribute("CitySearch", null);
            } else if (request.getParameter("btnSearch") != null) {
                System.out.println("create new search session");
                c = new CitySearch();
                c.setCityCode(request.getParameter("cityCode"));
                c.setName(request.getParameter("name"));
                request.getSession().setAttribute("CitySearch", c);
            } else if (request.getSession().getAttribute("CitySearch") != null) {
                System.out.println("use previous search session");
                c = (CitySearch) request.getSession().getAttribute("CitySearch");
            } else {
                // no criteria
            }

            CityDao dao = DaoFactory.createCityDao();
            List<City> list = dao.findByCriteriaLimit(c, start, end);//dao.findLimit(start, end);// dao.findLimit(((page - 1) * paging) + 1, paging); //dao.findLimit(((page - 1) * paging) + 1, paging * page);

            int total = 2000; //dao.countTotalRows(); --> di ubah dulu, belum ada di DAO nya tri, 16 juni 2010
            m.put("cities", list);
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
            java.lang.String pcityCode = request.getParameter("cityCode");
            CityDao dao = DaoFactory.createCityDao();
            City dto = null;

            String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                dto = dao.findByPrimaryKey(pcityCode);
            }

            if (dto == null) {
                dto = new City();
                dto.setCityCode("");
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
        m = this.getModelByPrimaryKey(request);
        m.put("mode", "create");
        return new ModelAndView("1_setup/CityAdd", "model", m);
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
//        java.lang.String pcityCode = request.getParameter("cityCode");
//        java.lang.String pname = request.getParameter("name");
//        java.lang.String pisActive = request.getParameter("isActive");
//        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
//        java.math.BigDecimal userId = new BigDecimal(lu.getUserId());
//        java.util.Date now = new java.util.Date();
//
//        // create the DAO class
//        CityDao dao = DaoFactory.createCityDao();
//
//        City dto = dao.findByPrimaryKey(pcityCode);
//        boolean isCreate = dto == null;
//        if (isCreate) {
//            dto = new City();
//            dto.setCreatedBy(userId);
//            dto.setCreatedDate(now);
//        }
//
//        dto.setCityCode(pcityCode);
//        dto.setName(pname);
//        dto.setIsActive(pisActive);
//
//        if (isCreate) {
//            dao.insert(dto);
//        } else {
//            dto.setUpdatedBy(userId);
//            dto.setUpdatedDate(now);
//            dao.update(dto.createPk(), dto);
//        }
//
//        return new ModelAndView("1_setup/CityView", "dto", dto);

        boolean isCreate = true;
        String strError = "";
        java.lang.String mode = request.getParameter("mode");
        try {
            if (mode.equalsIgnoreCase("create")) {
                isCreate = true;
            } else {
                isCreate = false;
            }

            java.lang.String pcityCode = request.getParameter("cityCode");
            if (pcityCode.trim().isEmpty()) {
                strError += "City Code Cannot be Empty!" + AppConstant.EOL;
            }

            CityDao dao = DaoFactory.createCityDao();
            City dto = null;
            if (isCreate) {
                dto = new City();
            } else {
                dto = dao.findByPrimaryKey(pcityCode);
            }

            java.lang.String pname = request.getParameter("name");
            if (pname.trim().isEmpty()) {
                strError += "Name Cannot be Empty!" + AppConstant.EOL;
            }

            java.lang.String pisActive = request.getParameter("isActive");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            java.math.BigDecimal userId = new BigDecimal(lu.getUserId());

            if (strError.length() > 0) {
                throw new Exception(strError);
            }

            if (isCreate) {
                dto.setCreatedBy(userId+"");
                dto.setCreatedDate(new java.util.Date());
            }

            dto.setCityCode(pcityCode);
            dto.setName(pname);
            dto.setIsActive(pisActive);

            if (isCreate) {
                dao.insert(dto);
            } else {
                dto.setUpdatedBy(userId+"");
                dto.setUpdatedDate(new java.util.Date());
                dao.update(dto.createPk(), dto);
            }

            return new ModelAndView("1_setup/CityView", "dto", dto);

        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            String errorMsg = "Unique Key Constraint [Code]!" + AppConstant.EOL;
            HashMap m = this.getModelByPrimaryKey(request);
            m.put("mode", mode);
            m.put("msg", errorMsg);
            System.out.println(errorMsg);
            if (isCreate) {
                return new ModelAndView("1_setup/CityAdd", "model", m);
            } else {
                return new ModelAndView("1_setup/CityEdit", "model", m);
            }
        } catch (Exception e) {
            String errorMsg = e.getMessage();
            HashMap m = this.getModelByPrimaryKey(request);
            m.put("mode", mode);
            m.put("msg", errorMsg);
            System.out.println(errorMsg);
            if (isCreate) {
                return new ModelAndView("1_setup/CityAdd", "model", m);
            } else {
                return new ModelAndView("1_setup/CityEdit", "model", m);
            }
        }

    }

    public ModelAndView inactivate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        java.lang.String pcityCode = request.getParameter("cityCode");

        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        java.math.BigDecimal pcreatedBy = new BigDecimal(lu.getUserId());
        //java.math.BigDecimal pcreatedBy = new BigDecimal(1); // ini harus di passing ya
        //java.util.Date pcreatedDate = (request.getParameter("createdDate") == null || request.getParameter("createdDate").trim().length() == 0) ? null : DateFormat.getDateTimeInstance().parse(request.getParameter("createdDate"));

        CityDao dao = DaoFactory.createCityDao();
        City dto = dao.findByPrimaryKey(pcityCode);

        if (dto != null) {
            dto.setIsActive(AppConstant.STATUS_FALSE);
            dto.setUpdatedBy(pcreatedBy+"");
            dto.setUpdatedDate(new java.util.Date());
            dao.update(dto.createPk(), dto);
        } else {
            //throw new Exception("City dengan Code" + pcityCode + " tidak ada");
        }

        //List<City> list = dao.findAll();
        //int total = 0; //dao.countTotalRows(); --> di ubah dulu, belum ada di DAO nya tri, 16 juni 2010
        //HashMap m = new HashMap();
        //m.put("cities", list);
        //m.put("totalRows", 0); // gak kepake

        HashMap m = this.searchAndPaging(request, response);
        return new ModelAndView("1_setup/CityList", "model", m);
    }
}



