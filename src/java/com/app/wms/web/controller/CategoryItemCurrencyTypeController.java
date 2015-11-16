/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.CategoryItemCurrencyTypeDao;
import com.app.wms.engine.db.dao.ProductCategoryDao;
import com.app.wms.engine.db.dto.CategoryItemCurrencyType;
import com.app.wms.engine.db.dto.ProductCategory;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.DaoException;
import com.app.wms.engine.db.exceptions.ProductCategoryDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author Faridzi
 */
public class CategoryItemCurrencyTypeController extends MultiActionController {

    /* DAO | Define needed dao here */
    private final ProductCategoryDao productCategoryDao = DaoFactory.createProductCategoryDao();
    private final CategoryItemCurrencyTypeDao categoryItemCurrencyTypeDao = DaoFactory.createCategoryItemCurrencyTypeDao();
    private List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
    private List<CategoryItemCurrencyType> cis = new ArrayList<CategoryItemCurrencyType>();

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> modelMap = new HashMap<String, Object>();
        try {
            productCategoryList = productCategoryDao.findAll();
        } catch (ProductCategoryDaoException ex) {
            Logger.getLogger(CategoryItemCurrencyTypeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        modelMap.put("productCategoryList", productCategoryList);
        return new ModelAndView("1_setup/CategoryItemCurrencyTypeList", "model", modelMap);
    }

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap<String, Object> modelMap = new HashMap<String, Object>();

        modelMap.put("productCategoryList", productCategoryList);
        return new ModelAndView("1_setup/CategoryItemCurrencyTypeAdd", "model", modelMap);
    }

    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        HashMap<String, Object> modelMap = new HashMap<String, Object>();

        Integer key = Integer.parseInt(request.getParameter("key"));
        CategoryItemCurrencyType ci = categoryItemCurrencyTypeDao.getById(key);
                //cis.get(key - 1);

        modelMap.put("categoryItemCurrencyType", ci);
        return new ModelAndView("1_setup/CategoryItemCurrencyTypeEdit", "model", modelMap);
    }

    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        LoginUser user = (LoginUser) request.getSession().getAttribute("user");
        Date now = new Date();

        String currtype = request.getParameter("groupCurrencyType");
        Integer ciId = Integer.parseInt(request.getParameter("ciId"));

        System.out.println("SAVE " + ciId);
        /*SET AUDITABLE*/
        CategoryItemCurrencyType ci = new CategoryItemCurrencyType();
        ci.setId(ciId);
        ci.setCurrencyType(currtype);
        ci.setUpdatedBy(user.getUsername());
        ci.setUpdatedDate(now);

        categoryItemCurrencyTypeDao.update(ci);

        return findByPrimaryKey(request, response);
    }

    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        LoginUser user = (LoginUser) request.getSession().getAttribute("user");
        Date now = new Date();

        System.out.println("ADD");

        return findByPrimaryKey(request, response);
    }

    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();

        pw.print("{\"maxpage\": " + categoryItemCurrencyTypeDao.ajaxMaxPage(request.getParameter("where"), new BigDecimal(request.getParameter("show"))) + ",\"data\": [");
        cis = categoryItemCurrencyTypeDao.ajaxSearch(request.getParameter("where"), request.getParameter("where"), request.getParameter("order"), Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10));
        for (CategoryItemCurrencyType ci : cis) {
            if (b) {
                pw.print(",");
            }

            pw.print("{\"1\": \"" + ci.getId()+ "\", ");
            pw.print("\"2\": \"" + ci.getCategoryCode() + "\", ");
            pw.print("\"3\": \"" + "-" + "\", ");
            pw.print("\"4\": \"" + ci.getCurrencyType() + "\", ");
            pw.print("\"5\": \"" + (ci.getUpdatedBy() == null ? "" : ci.getUpdatedBy()) + "\", ");
            pw.print("\"6\": \"" + (ci.getUpdatedDate() == null ? "" : new SimpleDateFormat("dd-MM-yyyy").format(ci.getUpdatedDate())) + "\"}");

            b = Boolean.TRUE;
        }
        pw.print("]}");
    }
    
    /**
     * SQL Initialization
     * ------------------
     * insert into category_item_currency_type (category_code, currency_type, created_by, created_date)
     * select category_code, 'DAILY', 'UPLOAD', '2015-07-20' from product_category
     * ------------------
     */

}
