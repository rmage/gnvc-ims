package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.CurrencyDao;
import com.app.wms.engine.db.dao.CurrencyRateDao;
import com.app.wms.engine.db.dto.Currency;
import com.app.wms.engine.db.dto.CurrencyRate;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.CurrencyDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.web.helper.StringHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
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

public class CurrencyRateController extends MultiActionController {

    private final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("-----------findByPrimaryKey--------------");
            HashMap m = new HashMap();

            String btnGroup = request.getParameter("btnGroup");           

            final String mode = request.getParameter("mode");
            if (StringHelper.emptyIfNull(btnGroup).equals("Add")) {
                System.out.println("ADD");
                return create(request, response);
            } else if (StringHelper.emptyIfNull(btnGroup).equals("Clear")) {
                System.out.println("CLEAR ");
                return new ModelAndView("accounting/CurrencyRate", "model", m);
            } else {
                HashMap model = this.searchAndPaging(request, response);
                return new ModelAndView("accounting/CurrencyRate", "model", model);
            }

        } catch (Throwable e) {
            e.printStackTrace();
            return new ModelAndView("Error", "th", e);
        }

    }

    private HashMap searchAndPaging(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            System.out.println("-----------searchAndPaging--------------");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            HashMap m = new HashMap();

            Date newerDate = null;
            String currCode = request.getParameter("currency_code_from");
            String newerDateString;
            newerDateString = request.getParameter("rate_date");

            if (currCode == null) {
                currCode = "ALL";
            }

            if (newerDateString != null) {
                if (newerDateString.equalsIgnoreCase("")) {
                    newerDate = null;
                } else {
                    newerDate = df.parse(newerDateString);
                }
            }

            /* DAO | Define needed dao here */
            CurrencyRateDao currencyRateDao = DaoFactory.createCurrencyRateDao();
            CurrencyDao currencyDao = DaoFactory.createCurrencyDao();

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

            System.out.println("WHERE " + request.getParameter("where"));
            System.out.println("PAGE " + page);
            System.out.println("PAGING " + paging);
            System.out.println("currCode " + currCode);
            System.out.println("newerDateString " + newerDateString);
            int start = (page - 1) * paging + 1;
            int end = start + paging - 1;

            List<CurrencyRate> crs = new ArrayList<CurrencyRate>();
            List<Currency> currs = new ArrayList<Currency>();

            try {
                currs = currencyDao.findAll();
            } catch (CurrencyDaoException currencyDaoException) {

            }

            m.put("currs", currs);

            crs = currencyRateDao.findByCurrencyCodeAndDate(currCode, newerDate, page, paging);

            System.out.println("SIZE " + crs.size());

            int total = 2000;
            m.put("totalRows", total);
            m.put("page", page);
            m.put("paging", paging);
            m.put("crs", crs);
            m.put("queryNewerThanDate", newerDateString);
            m.put("selectedCurrCode", currCode);

            return m;

        } catch (NumberFormatException e) {
            throw e;
        }
    }

    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("-----------ajaxSearch--------------");
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        CurrencyRateDao currencyRateDao = DaoFactory.createCurrencyRateDao();

        Date newerDate = null;
        String currCode = request.getParameter("currency_code_from");
        String newerDateString;
        newerDateString = request.getParameter("rate_date");
        
        System.out.println("WHERE " + request.getParameter("where"));
        System.out.println("currCode " + currCode);
        System.out.println("newerDateString " + newerDateString);

        if (currCode == null) {
            currCode = "ALL";
        }

        if (newerDateString != null) {
            if (newerDateString.equalsIgnoreCase("")) {
                newerDate = null;
            } else {
                try {
                    newerDate = df.parse(newerDateString);
                } catch (ParseException ex) {
                    Logger.getLogger(CurrencyRateController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        pw.print("{\"maxpage\": " + currencyRateDao.ajaxMaxPage(request.getParameter("where"), new BigDecimal(request.getParameter("show"))) + ",\"data\": [");
        List<CurrencyRate> crs = currencyRateDao.ajaxSearch(request.getParameter("where"), request.getParameter("where"), request.getParameter("order"), Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10));
        for (CurrencyRate x : crs) {
            if (b) {
                pw.print(",");
            }
            pw.print("{\"1\": \"" + x.getRateId() + "\", ");
            pw.print("\"2\": \"" + x.getCurrencyCodeFrom() + "\", ");
            pw.print("\"3\": \"" + x.getCurrencyCodeTo() + "\", ");
            pw.print("\"4\": \"" + x.getRateValue() + "\", ");
            pw.print("\"5\": \"" + x.getRateDate() + "\", ");
            pw.print("\"6\": \"" + x.getCreatedBy() + "\", ");
            pw.print("\"7\": \"" + x.getCreatedDate() + "\"}");
            b = Boolean.TRUE;
        }
        pw.print("]}");
    }

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap<String, Object> modelMap = new HashMap<String, Object>();

        CurrencyDao currencyDao = DaoFactory.createCurrencyDao();
        List<Currency> currs = new ArrayList<Currency>();

        try {
            currs = currencyDao.findAll();
        } catch (CurrencyDaoException currencyDaoException) {

        }

        modelMap.put("currs", currs);
        modelMap.put("mode", "create");
        return new ModelAndView("accounting/CurrencyRateAdd", "model", modelMap);
    }

    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LoginUser user = (LoginUser) request.getSession().getAttribute("user");
        String mode = request.getParameter("mode");
        HashMap<String, Object> modelMap = new HashMap<String, Object>();
        CurrencyRateDao currencyRateDao = DaoFactory.createCurrencyRateDao();

        if (user == null) {
            String msg = "You haven't login or your session has been expired! Please do login again";
            modelMap.put("msg", msg);
            return new ModelAndView("login", "model", modelMap);
        } else {
            System.out.println("SAVE");
            /*POPULATE USERID*/
            String userId = "";
            userId = (String) user.getUserId();
            /*GATHER FROM FORM DATA*/
            String currrencyCodeFrom = request.getParameter("groupCurrencyCodeFrom");
            String currrencyCodeTo = request.getParameter("groupCurrencyCodeTo");
            String rateValueString = request.getParameter("rateValue");
            String rateDateString = request.getParameter("rateDate");
            Date createdDate = new Date();
            String createdBy = userId;
            Date rateDate = df.parse(rateDateString);
            double value = Double.parseDouble(rateValueString);
            BigDecimal db = BigDecimal.valueOf(value);

            /*BIND DATA TO OBJECT*/
            CurrencyRate currencyRate = new CurrencyRate();
            currencyRate.setCreatedBy(userId);
            currencyRate.setCreatedDate(createdDate);
            currencyRate.setRateDate(rateDate);
            currencyRate.setRateValue(db);
            currencyRate.setcurrencyCodeFrom(currrencyCodeFrom);
            currencyRate.setCurrencyCodeTo(currrencyCodeTo);

            int id = currencyRateDao.insert(currencyRate);

            return findByPrimaryKey(request, response);
        }
    }

    /*WE'RE NOT REALLY USE THIS CODE BELOW RIGHT NOW*/
//    public void setRate(HttpServletRequest request, HttpServletResponse response)
//            throws IOException {
//
//        /* DATA | get initial value */
//        PrintWriter pw = response.getWriter();
//        String currencyCode = request.getParameter("cc");
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        BigDecimal rateValue = new BigDecimal(request.getParameter("v"));
//        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
//
//        /* DAO | Define needed dao here */
//        CurrencyRateDao currencyRateDao = DaoFactory.createCurrencyRateDao();
//
//        /* TRANSACTION | Something complex here */
//        CurrencyRate cr = new CurrencyRate();
//        cr.setCurrencyCode(currencyCode);
//        cr.setRateValue(rateValue);
//        cr.setRateDate(new Date());
//        cr.setCreatedBy(lu.getUsername());
//        cr.setCreatedDate(new Date());
//        if (currencyRateDao.insert(cr) > 0) {
//            pw.print("{\"status\": 1, \"date\": \"" + sdf.format(cr.getRateDate()) + "\", \"by\": \"" + cr.getCreatedBy() + "\"}");
//        } else {
//            pw.print("{\"status\": 0}");
//        }
//
//    }

}
