/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.controller.acc;

import com.app.wms.engine.db.dao.ProductCategoryDao;
import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dto.ProductCategory;
import com.app.wms.engine.db.exceptions.ProductCategoryDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
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
public class NonFishStockDocumentSummaryController extends MultiActionController {

    /* DAO | Define needed dao here */
    private final ProductDao productDao = DaoFactory.createProductDao();

    private final ProductCategoryDao productCategoryDao = DaoFactory.createProductCategoryDao();

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> modelMap = new HashMap<String, Object>();

        List<ProductCategory> productCategoryList = null;
        try {
            productCategoryList = productCategoryDao.findAll();
        } catch (ProductCategoryDaoException ex) {
            Logger.getLogger(NonFishStockDocumentSummaryController.class.getName()).log(Level.SEVERE, null, ex);
        }

        modelMap.put("prodCategory", productCategoryList);

        return new ModelAndView("accounting/NonFishStockDocumentSummary", "model", modelMap);
    }

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> modelMap = new HashMap<String, Object>();
        
        /*GET PARAMETER*/
        String productCat = request.getParameter("productcat");
        String docType = request.getParameter("doctype");
        String dateAsOf = request.getParameter("asOf");
        
        System.out.println("PARAM : " + productCat);
        System.out.println("PARAM : " + docType);
        System.out.println("PARAM : " + dateAsOf);

        return new ModelAndView("accounting/NonFishStockAccountingGenerate", "model", modelMap);
    }
}
