package com.app.wms.web.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.*;
import java.math.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.dto.*;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.*;
import com.app.wms.web.util.AppConstant;

import java.util.HashMap;

public class ProductCategoryController extends MultiActionController {

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
                return new ModelAndView("1_setup/ProductCategoryEdit", "model", m);
            } else {
                m = this.searchAndPaging(request, response);
                return new ModelAndView("1_setup/ProductCategoryList", "model", m);
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

            ProductCategory p = new ProductCategory();
            p.setCategoryCode(request.getParameter("categoryCode"));
            p.setCategoryName(request.getParameter("categoryName"));
            ProductCategoryDao dao = DaoFactory.createProductCategoryDao();
            List<ProductCategory> listSearchPage = dao.findProductCategoryPaging(p, page);

            int total = 2000;
            m.put("productcategory", listSearchPage);
            m.put("totalRows", total);
            m.put("page", page);
            m.put("paging", paging);

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
            // parse parameters
            // create the DAO class
            ProductCategoryDao dao = DaoFactory.createProductCategoryDao();

            // execute the finder
            List<ProductCategory> dto = dao.findAll();

            return new ModelAndView("ProductCategoryResult", "result", dto);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ModelAndView("Error", "th", e);
        }

    }

    /**
     * Method 'findWhereCategoryCodeEquals'
     *
     * @param request
     * @param response
     * @throws Exception
     * @return ModelAndView
     */
    public ModelAndView findWhereCategoryCodeEquals(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            // parse parameters
            java.lang.String pcategoryCode = request.getParameter("categoryCode");

            // create the DAO class
            ProductCategoryDao dao = DaoFactory.createProductCategoryDao();

            // execute the finder
            List<ProductCategory> dto = dao.findWhereCategoryCodeEquals(pcategoryCode);

            return new ModelAndView("ProductCategoryResult", "result", dto);
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
            ProductCategoryDao dao = DaoFactory.createProductCategoryDao();

            // execute the finder
            List<ProductCategory> dto = null;

            return new ModelAndView("ProductCategoryResult", "result", dto);
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
            ProductCategoryDao dao = DaoFactory.createProductCategoryDao();

            // execute the finder
            List<ProductCategory> dto = null;

            return new ModelAndView("ProductCategoryResult", "result", dto);
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
            ProductCategoryDao dao = DaoFactory.createProductCategoryDao();

            // execute the finder
            List<ProductCategory> dto = dao.findWhereCreatedDateEquals(pcreatedDate);

            return new ModelAndView("ProductCategoryResult", "result", dto);
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
            ProductCategoryDao dao = DaoFactory.createProductCategoryDao();

            // execute the finder
            List<ProductCategory> dto = null;

            return new ModelAndView("ProductCategoryResult", "result", dto);
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
            ProductCategoryDao dao = DaoFactory.createProductCategoryDao();

            // execute the finder
            List<ProductCategory> dto = dao.findWhereUpdatedDateEquals(pupdatedDate);

            return new ModelAndView("ProductCategoryResult", "result", dto);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ModelAndView("Error", "th", e);
        }

    }

    private HashMap getModelByPrimaryKey(HttpServletRequest request) throws Exception {
        try {
            String categoryCode = request.getParameter("categoryCode");

            ProductCategoryDao dao = DaoFactory.createProductCategoryDao();
            ProductCategory dto = null;

            String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                Integer id = Integer.parseInt(request.getParameter("id"));
                dto = dao.findByPrimaryKey(id);
            }

            if (dto == null) {
                dto = new ProductCategory();
                dto.setCategoryCode("");
                dto.setCategoryName("");
                dto.setIsActive("Y");
                dto.setIsDelete("N");
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
        return new ModelAndView("1_setup/ProductCategoryAdd", "model", m);
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
        java.util.Date now = new java.util.Date();
        java.lang.String mode = request.getParameter("mode");
        ProductCategory dto = null;
        try {

            if (mode.equalsIgnoreCase("create")) {
                isCreate = true;
            } else {
                isCreate = false;
            }

            ProductCategoryDao dao = DaoFactory.createProductCategoryDao();
            if (isCreate) {
                dto = new ProductCategory();
            } else {
                Integer id = Integer.parseInt(request.getParameter("id"));
                dto = dao.findByPrimaryKey(id);
            }

            String categoryCode = request.getParameter("categoryCode");
            String categoryName = request.getParameter("categoryName");

            List<ProductCategory> tmp = dao.findWhereCategoryCodeEquals(categoryCode);
            if ((isCreate && tmp != null && tmp.size() > 0) || (!isCreate && tmp != null && tmp.size() > 0 && !tmp.get(0).getCategoryCode().equals(categoryCode))) {
                strError += "ProductCategory code already exists. Please try a different values" + AppConstant.EOL;
            }

            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            String userId = "";
            if (lu == null) {
                HashMap m = new HashMap();
                String msg = "You haven't login or your session has been expired! Please do login again";
                m.put("msg", msg);
                return new ModelAndView("login", "model", m);
            } else {
                userId = (String) lu.getUserId();
            }

            if (isCreate) {
                dto.setCreatedBy(userId);
                dto.setCreatedDate(now);
            }

            dto.setCategoryCode(categoryCode);
            dto.setCategoryName(categoryName);
            dto.setIsActive("Y");
            dto.setIsDelete("N");

            if (strError.length() > 0) {
                throw new Exception(strError);
            }

            if (isCreate) {
                ProductCategoryPk cpk = dao.insert(dto);
                dto.setId(cpk.getId());
                
                // 2015 | add new category on category_item_currency_type
                CategoryItemCurrencyType categoryItemCurrencyType = new CategoryItemCurrencyType();
                categoryItemCurrencyType.setCategoryCode(dto.getCategoryCode());
                categoryItemCurrencyType.setCurrencyType("DAILY");
                categoryItemCurrencyType.setCreatedBy(dto.getCreatedBy());
                categoryItemCurrencyType.setCreatedDate(dto.getCreatedDate());
                
                CategoryItemCurrencyTypeDao categoryItemCurrencyTypeDao = DaoFactory.createCategoryItemCurrencyTypeDao();
                categoryItemCurrencyTypeDao.insert(categoryItemCurrencyType);
            } else {
                dto.setUpdatedBy(userId);
                dto.setUpdatedDate(now);
                dao.update(dto.createPk(), dto);
            }
//            return new ModelAndView("1_setup/ProductCategoryView", "dto", dto);
            return new ModelAndView("redirect:ProductCategory.htm");

        } catch (Exception e) {
            e.printStackTrace();
            String errorMsg = e.getMessage();
            HashMap m = this.getModelByPrimaryKey(request);
            m.put("mode", mode);
            m.put("msg", errorMsg);
            System.out.println(errorMsg);
            return new ModelAndView(isCreate ? "1_setup/ProductCategoryAdd" : "1_setup/ProductCategoryEdit", "model", m);
        }

    }

    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Integer id = Integer.parseInt(request.getParameter("key"));

        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        String pcreatedBy = "";
        if (lu == null) {
            HashMap m = new HashMap();
            String msg = "You haven't login or your session has been expired! Please do login again";
            m.put("msg", msg);
            return new ModelAndView("login", "model", m);
        } else {
            pcreatedBy = lu.getUserId();
        }

        ProductCategoryDao dao = DaoFactory.createProductCategoryDao();
        ProductCategory dto = dao.findByPrimaryKey(id);

        if (dto != null) {
            dto.setIsActive(AppConstant.STATUS_FALSE);
            dto.setIsDelete("Y");
            dto.setUpdatedBy(pcreatedBy);
            dto.setUpdatedDate(new java.util.Date());
            dao.update(dto.createPk(), dto);
            
            // 2015 | remove product category from category_item_currency_type
            CategoryItemCurrencyTypeDao categoryItemCurrencyTypeDao = DaoFactory.createCategoryItemCurrencyTypeDao();
            categoryItemCurrencyTypeDao.deleteByCategoryCode(dto.getCategoryCode());
        }

        List<ProductCategory> list = dao.findAll();

        HashMap m = new HashMap();
        m.put("productcategory", list);
        m.put("totalRows", 0);

        return new ModelAndView("1_setup/ProductCategoryList", "model", m);
    }

    public void getUnique(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        PrintWriter pw = response.getWriter();
        String uniCategoryCode = request.getParameter("term");
        System.out.println("term: " + uniCategoryCode);

        ProductCategoryDao productcategoryDao = DaoFactory.createProductCategoryDao();

        //  pw.print("[");
        List<ProductCategory> cr = productcategoryDao.findByCode(uniCategoryCode);
        System.out.println("data: " + cr);
        if (!cr.isEmpty()) {
            pw.print("{\"status\": true}");
        } else {
            pw.print("{\"status\": false}");
        }
//                pw.print("]");
    }

    //Modified 10 April 2014
    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();

        ProductCategoryDao pcDao = DaoFactory.createProductCategoryDao();

        pw.print("{\"maxpage\": " + pcDao.ajaxMaxPage(request.getParameter("where"), new BigDecimal(request.getParameter("show"))) + ",\"data\": [");
        List<ProductCategory> ps = pcDao.ajaxSearch(request.getParameter("where"), request.getParameter("order"), Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10));
        for (ProductCategory x : ps) {
            if (b) {
                pw.print(",");
            }
            pw.print("{\"1\": \"" + x.getId() + "\", ");
            pw.print("\"2\": \"" + x.getCategoryCode()+ "\", ");
            pw.print("\"3\": \"" + x.getCategoryName()+ "\", ");
            pw.print("\"4\": \"" + x.getIsActive()+ "\"}");

            b = Boolean.TRUE;
        }
        pw.print("]}");
    }
    
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.parseInt(request.getParameter("key"));
        ProductCategoryDao categoryDao = DaoFactory.createProductCategoryDao();
        ProductCategory dto = categoryDao.findId(id);
        
        Map map = new HashMap();
        map.put("mode", dto);
        return new ModelAndView("1_setup/ProductCategoryEdit","model",map);
    }
}
