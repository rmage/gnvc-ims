package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.CurrencyDao;
import com.app.wms.engine.db.dao.CurrencyRateDao;
import com.app.wms.engine.db.dto.Currency;
import com.app.wms.engine.db.dto.CurrencyRate;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.CurrencyDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
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

    private static final String DAILY_TYPE = "DAILY";
    private static final String WEEKLY_TYPE = "WEEKLY";
    private static final String MONTHLY_TYPE = "MONTHLY";

    private final SimpleDateFormat sdfIn = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");

    private final SimpleDateFormat sdfOut = new SimpleDateFormat("dd/MM/yyyy");

    private final SimpleDateFormat sdfOutMonth = new SimpleDateFormat("MMM yyyy");


    /* DAO | Define needed dao here */
    private final CurrencyRateDao currencyRateDao = DaoFactory.createCurrencyRateDao();
    private final CurrencyDao currencyDao = DaoFactory.createCurrencyDao();

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("-----------findByPrimaryKey--------------");
            HashMap m = new HashMap();

            String btnGroup = request.getParameter("btnGroup");

            final String mode = request.getParameter("mode");

            List<Currency> currs = new ArrayList<Currency>();

            try {
                currs = currencyDao.findAll();
            } catch (CurrencyDaoException currencyDaoException) {

            }

            m.put("currs", currs);
            return new ModelAndView("accounting/CurrencyRate", "model", m);

        } catch (Throwable e) {
            e.printStackTrace();
            return new ModelAndView("Error", "th", e);
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
            Date dateTemp = null;
            Date dateWeekStartTemp = null;
            Date dateWeekEndTemp = null;
            Date dateMontTemp = null;
            Date dateCreate = null;
            try {
                if (x.getRateDate() != null) {
                    dateTemp = sdfIn.parse(x.getRateDate().toString());
                }
                if (x.getWeekStartDate() != null) {
                    dateWeekStartTemp = sdfIn.parse(x.getWeekStartDate().toString());
                }
                if (x.getWeekEndDate() != null) {
                    dateWeekEndTemp = sdfIn.parse(x.getWeekEndDate().toString());
                }
                if (x.getMonthDate() != null) {
                    dateMontTemp = sdfIn.parse(x.getMonthDate().toString());
                }
                if (x.getCreatedDate() != null) {
                    dateCreate = sdfIn.parse(x.getCreatedDate().toString());
                }

            } catch (ParseException ex) {
                Logger.getLogger(CurrencyRateController.class.getName()).log(Level.SEVERE, null, ex);
            }

            pw.print("{\"1\": \"" + x.getRateId() + "\", ");
            pw.print("\"2\": \"" + x.getCurrencyType() + "\", ");
            pw.print("\"3\": \"" + x.getCurrencyCodeFrom() + "\", ");
            pw.print("\"4\": \"" + x.getCurrencyCodeTo() + "\", ");
            pw.print("\"5\": \"" + x.getRateValue() + "\", ");
            if (dateTemp != null) {
                pw.print("\"6\": \"" + sdfOut.format(dateTemp) + "\", ");
            } else {
                pw.print("\"6\": \"" + " " + "\", ");
            }
            if (dateWeekStartTemp != null) {
                pw.print("\"7\": \"" + sdfOut.format(dateWeekStartTemp) + "\", ");
            } else {
                pw.print("\"7\": \"" + " " + "\", ");
            }
            if (dateWeekEndTemp != null) {
                pw.print("\"8\": \"" + sdfOut.format(dateWeekEndTemp) + "\", ");
            } else {
                pw.print("\"8\": \"" + " " + "\", ");
            }
            if (dateMontTemp != null) {
                pw.print("\"9\": \"" + sdfOutMonth.format(dateMontTemp) + "\", ");
            } else {
                pw.print("\"9\": \"" + " " + "\", ");
            }

            pw.print("\"10\": \"" + sdfOut.format(dateCreate) + "\"}");
            
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
            String currrencyType = request.getParameter("groupCurrencyType");
            String dateWeekStartString = request.getParameter("rateWeekStart");
            String dateWeekEndString = request.getParameter("rateWeekEnd");
            String dateMonthString = "01/";
            Date rateDateMonth = null;

            if (request.getParameter("monthPicker").length() > 0) {
                dateMonthString += request.getParameter("monthPicker");
                rateDateMonth = df.parse(dateMonthString);
            }

            Date createdDate = new Date();
            String createdBy = userId;
            Date rateDate = df.parse(rateDateString);
            Date rateDateWeekStart = df.parse(dateWeekStartString);
            Date rateDateWeekEnd = df.parse(dateWeekEndString);

            double value = Double.parseDouble(rateValueString);
            BigDecimal db = BigDecimal.valueOf(value);

            /*BIND DATA TO OBJECT*/
            CurrencyRate currencyRate = new CurrencyRate();
            currencyRate.setCreatedBy(userId);
            currencyRate.setCreatedDate(createdDate);
            currencyRate.setRateDate(rateDate);
            currencyRate.setWeekStartDate(rateDateWeekStart);
            currencyRate.setWeekEndDate(rateDateWeekEnd);
            currencyRate.setMonthDate(rateDateMonth);
            currencyRate.setRateValue(db);
            currencyRate.setcurrencyCodeFrom(currrencyCodeFrom);
            currencyRate.setCurrencyCodeTo(currrencyCodeTo);

            if (currrencyType.equalsIgnoreCase(CurrencyRateController.DAILY_TYPE)) {
                currencyRate.setWeekStartDate(null);
                currencyRate.setWeekEndDate(null);
                currencyRate.setMonthDate(null);
                currencyRate.setCurrencyType(CurrencyRateController.DAILY_TYPE);
            } else if (currrencyType.equalsIgnoreCase(CurrencyRateController.WEEKLY_TYPE)) {
                currencyRate.setMonthDate(null);
                currencyRate.setRateDate(null);
                currencyRate.setCurrencyType(CurrencyRateController.WEEKLY_TYPE);
            } else {
                currencyRate.setWeekStartDate(null);
                currencyRate.setWeekEndDate(null);
                currencyRate.setRateDate(null);
                currencyRate.setCurrencyType(CurrencyRateController.MONTHLY_TYPE);
            }

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
