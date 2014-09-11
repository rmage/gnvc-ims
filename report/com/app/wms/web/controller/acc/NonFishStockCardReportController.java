/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.controller.acc;

import com.app.wms.engine.db.dao.NonFishStockCardSummaryDao;
import com.app.wms.engine.db.dao.ProductCategoryDao;
import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dto.NonFishStockCardSummary;
import com.app.wms.engine.db.dto.ProductCategory;
import com.app.wms.engine.db.exceptions.ProductCategoryDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
public class NonFishStockCardReportController extends MultiActionController {

    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    /* DAO | Define needed dao here */
    private final ProductDao productDao = DaoFactory.createProductDao();

    private final ProductCategoryDao productCategoryDao = DaoFactory.createProductCategoryDao();

    private final NonFishStockCardSummaryDao nonFishStockCardSummaryDao = DaoFactory.createNonFishStockCardSummaryDao();

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> modelMap = new HashMap<String, Object>();

        List<ProductCategory> productCategoryList = null;
        try {
            productCategoryList = productCategoryDao.findAll();
        } catch (ProductCategoryDaoException ex) {
            Logger.getLogger(NonFishStockCardReportController.class.getName()).log(Level.SEVERE, null, ex);
        }

        modelMap.put("prodCategory", productCategoryList);

        return new ModelAndView("accounting/NonFishStockCardReport", "model", modelMap);
    }

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> modelMap = new HashMap<String, Object>();

        /*INITIALIZE*/
        List<NonFishStockCardSummary> nonFishStockCardReportList = new ArrayList<NonFishStockCardSummary>();

        Calendar aCalendar = Calendar.getInstance();
        Date now = null;
        String lastDateOfThisMonthString = "";

        /*GET PARAMETER*/
        String productCat = request.getParameter("productcat");
        String dateAsOf = request.getParameter("asOf");

        try {
            now = df.parse(dateAsOf);
        } catch (ParseException ex) {
            Logger.getLogger(NonFishStockCardSummaryDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*GET LAST DATE*/
        /*SET DATE REPORT TO PREVIOUS MONTH LAST DAY*/
        aCalendar.setTime(now);

        /*SET LAST DAY OF MONTH*/
        aCalendar.set(Calendar.DATE, aCalendar.getActualMaximum(Calendar.DATE));

        Date lastDateOfThisMonth = aCalendar.getTime();
        lastDateOfThisMonthString = df.format(lastDateOfThisMonth);

        /*VAR FOR TOTALING*/
        Double totalQuantity = 0d;
        BigDecimal totalAmountToDate = BigDecimal.ZERO;
        BigDecimal totalTransactionAmount = BigDecimal.ZERO;

        nonFishStockCardReportList = nonFishStockCardSummaryDao.findByItemCategoryandDate(productCat, lastDateOfThisMonthString);

        for (NonFishStockCardSummary nfs : nonFishStockCardReportList) {
            totalQuantity += nfs.getQuantity();
            totalAmountToDate = totalAmountToDate.add(nfs.getAmountToDate());
            totalTransactionAmount = totalTransactionAmount.add(nfs.getTransactionAmount());
        }

        modelMap.put("nonFishStockCardReportList", nonFishStockCardReportList);
        modelMap.put("productCat", productCat);
        modelMap.put("dateAsOf", dateAsOf);
        /*PUT TOTALING TO MODEL*/
        modelMap.put("totalQuantity", totalQuantity);
        modelMap.put("totalAmountToDate", totalAmountToDate);
        modelMap.put("totalTransactionAmount", totalTransactionAmount);

        return new ModelAndView("accounting/NonFishStockCardReportGenerate", "model", modelMap);
    }

}