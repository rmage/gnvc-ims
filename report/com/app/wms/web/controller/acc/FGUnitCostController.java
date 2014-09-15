/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.controller.acc;

import com.app.wms.engine.db.dao.CurrencyDao;
import com.app.wms.engine.db.dto.Currency;
import com.app.wms.engine.db.dto.FGItem;
import com.app.wms.engine.db.dto.FGUnitCost;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.CurrencyDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.FGItemDao;
import com.spfi.ims.dao.FgUnitCostDao;
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
public class FGUnitCostController extends MultiActionController {

    private final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    /* DAO | Define needed dao here */
    private final CurrencyDao currencyDao = DaoFactory.createCurrencyDao();

    private final FGItemDao fgItemDao = DaoFactory.createFGItemDao();

    private final FgUnitCostDao fgUnitCostDao = DaoFactory.createFgUnitCostDao();

    /*INITIALIZE VAR*/
    private List<FGItem> fgItems = new ArrayList<FGItem>();

    private List<Currency> currencies = new ArrayList<Currency>();

    private List<FGUnitCost> fgUnitCostList = new ArrayList<FGUnitCost>();

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> modelMap = new HashMap<String, Object>();

        /*GET ALL FG ITEM*/
        fgItems = fgItemDao.findAllActive();
        try {
            /*GET ALL CURRENCY*/
            currencies = currencyDao.findAll();
        } catch (CurrencyDaoException ex) {
            Logger.getLogger(FGUnitCostController.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*PUT THOSE TO MODEL*/
        modelMap.put("fgItems", fgItems);
        modelMap.put("currencies", currencies);
        return new ModelAndView("accounting/FGUnitCostList", "model", modelMap);
    }

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("mode", "create");

        /*PUT THOSE TO MODEL*/
        modelMap.put("fgItems", fgItems);
        modelMap.put("currencies", currencies);

        return new ModelAndView("accounting/FGUnitCostAdd", "model", modelMap);
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
        LoginUser user = (LoginUser) request.getSession().getAttribute("user");
        Date now = new Date();
        Date fgUcDate = new Date();

        FGUnitCost fgUc = new FGUnitCost();

        /*GET FORM SUBMITTED VALUE*/
        Integer fgItemId = Integer.parseInt(request.getParameter("fg_item_id"));
        String currencyCode = request.getParameter("currency_code");
        BigDecimal amount = new BigDecimal(request.getParameter("amount"));

        fgUcDate = df.parse(request.getParameter("queryDate"));

        /*BIND DATA TO OBJ*/
        fgUc.setFgItemId(fgItemId);
        fgUc.setCurrencyCode(currencyCode);
        fgUc.setAmount(amount);
        fgUc.setUnitCostDate(fgUcDate);

        /*SET AUDITABLE*/
        fgUc.setCreatedBy(user.getUserId());
        fgUc.setCreatedDate(now);
        
        /*SAVE OBJ*/
        fgUnitCostDao.save(fgUc);

        return findByPrimaryKey(request, response);
    }

    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();

        pw.print("{\"maxpage\": " + fgUnitCostDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where")) + ",\"data\": [");
        fgUnitCostList = fgUnitCostDao.ajaxSearch(request.getParameter("where"), request.getParameter("order"), Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10));
        for (FGUnitCost fgUc : fgUnitCostList) {
            if (b) {
                pw.print(",");
            }
            pw.print("{\"1\": \"" + fgUc.getId() + "\", ");
            pw.print("\"2\": \"" + fgUc.getFgItem().getItemCode() + "\", ");
            pw.print("\"3\": \"" + fgUc.getFgItem().getItemName() + "\", ");
            pw.print("\"4\": \"" + fgUc.getCurrencyCode() + "\", ");
            pw.print("\"5\": \"" + fgUc.getAmount() + "\", ");
            pw.print("\"6\": \"" + fgUc.getUnitCostDate() + "\"}");

            b = Boolean.TRUE;
        }
        pw.print("]}");
    }

}
