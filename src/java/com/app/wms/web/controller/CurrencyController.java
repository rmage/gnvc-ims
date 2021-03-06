package com.app.wms.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.app.wms.engine.db.dao.CurrencyDao;
import com.app.wms.engine.db.dao.CurrencyRateDao;
import com.app.wms.engine.db.dto.Currency;
import com.app.wms.engine.db.dto.CurrencyPk;
import com.app.wms.engine.db.dto.CurrencyRate;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.CurrencyDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.web.util.AppConstant;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

public class CurrencyController extends MultiActionController {

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
                return new ModelAndView("1_setup/CurrencyEdit", "model", m);
            } else {
                CurrencyDao dao = DaoFactory.createCurrencyDao();
                List<Currency> list = dao.findAll();
                m = this.getModelByPrimaryKey(request);

                Currency dto = new Currency();
                for (Currency valueSearch : list) {

                    String currencyCode = valueSearch.getCurrencyCode();
                    String currencyName = valueSearch.getCurrencyName();
                    String currencySymbol = valueSearch.getCurrencySymbol();

                    dto.setCurrencyCode(currencyCode);
                    dto.setCurrencyName(currencyName);
                    dto.setCurrencySymbol(currencySymbol);
                    m.put("dto", dto);
                }
                m.put("currency", list);
                // m = this.searchAndPaging(request, response);
                return new ModelAndView("1_setup/CurrencyList", "model", m);
            }

        } catch (Throwable e) {
            e.printStackTrace();
            return new ModelAndView("Error", "th", e);
        }

    }

    private HashMap getModelByPrimaryKey(HttpServletRequest request) throws Exception {
        try {

            CurrencyDao dao = DaoFactory.createCurrencyDao();
            Currency dto = null;

            String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                Integer id = Integer.parseInt(request.getParameter("id"));
                dto = dao.findByPrimaryKey(id);
            }

            if (dto == null) {
                dto = new Currency();
                dto.setCurrencyCode("");
                dto.setCurrencyName("");
                dto.setCurrencyCode("");
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

        return new ModelAndView("1_setup/CurrencyAdd", "model", m);
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
        boolean isCreate = true;
        String strError = "";
        Date now = new Date();
        String mode = request.getParameter("mode");
        Currency dto = null;
        HashMap modelMap = new HashMap();
        
        if (user == null) {
            String msg = "You haven't login or your session has been expired! Please do login again";
            modelMap.put("msg", msg);
            return new ModelAndView("login", "model", modelMap);
         } else{
            try {
                if (mode.equalsIgnoreCase("create")) {
                    isCreate = true;
                } else {
                    isCreate = false;
                }

                CurrencyDao dao = DaoFactory.createCurrencyDao();
                if (isCreate) {
                    dto = new Currency();
                } else {
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    dto = dao.findByPrimaryKey(id);
                }

                String code = request.getParameter("code");
                String name = request.getParameter("name");
                String symbol = request.getParameter("symbol");
                /*POPULATE USERID*/
                String userId = "";
                userId = (String)user.getUserId();
                List<Currency> tmp = dao.findWhereCurrencyCodeEquals(code);

                if ((isCreate && tmp != null && tmp.size() > 0) || (!isCreate && tmp != null && tmp.size() > 0 && !tmp.get(0).getCurrencyCode().equals(code))) {
                    strError += "Currency code already exists. Please try a different values" + AppConstant.EOL;
                }

                if (isCreate) {
                    dto.setCurrencyCode(code);
                    dto.setCurrencyName(name);
                    dto.setCurrencySymbol(symbol);
                    dto.setIsDelete("N");
                    dto.setIsActive(request.getParameter("isActive"));
                }

                dto.setCurrencyCode(code);
                dto.setCurrencyName(name);
                dto.setCurrencySymbol(symbol);
                dto.setIsDelete("N");
                dto.setIsActive("Y");
                dto.setCreatedBy(userId);

                if (strError.length() > 0) {
                    throw new Exception(strError);
                }

                if (isCreate) {
                    CurrencyPk cp = dao.insert(dto);
                    dto.setId(cp.getId());
                    
                    // 2015 | set CURRENCY_RATE to 1:1 for all currency and type
                    CurrencyDao currencyDao = DaoFactory.createCurrencyDao();
                    CurrencyRateDao currencyRateDao = DaoFactory.createCurrencyRateDao();
                    String[] rateType = {"DAILY"/*, "WEEKLY", "MONTHLY"*/};
                    for (String s : rateType) {
                        List<Currency> currencyList = currencyDao.findWhereIsActiveEquals("Y");
                        
                        for (Currency currency : currencyList) {
                            CurrencyRate currencyRate = new CurrencyRate();
                            currencyRate.setcurrencyCodeFrom(dto.getCurrencyCode());
                            currencyRate.setCurrencyCodeTo(currency.getCurrencyCode());
                            currencyRate.setRateValue(BigDecimal.ONE);
                            currencyRate.setCurrencyType(s);

                            Calendar calendar = Calendar.getInstance();
                            calendar.set(Calendar.YEAR, 2000);
                            calendar.set(Calendar.MONTH, 0);
                            calendar.set(Calendar.DAY_OF_MONTH, 1);
                            calendar.set(Calendar.HOUR_OF_DAY, 0);
                            calendar.set(Calendar.MINUTE, 0);
                            calendar.set(Calendar.SECOND, 0);
                            calendar.set(Calendar.MILLISECOND, 0);
                            currencyRate.setRateDate(calendar.getTime());
                            
                            currencyRate.setCreatedDate(new Date());
                            currencyRate.setCreatedBy(dto.getCreatedBy());
                            currencyRate.setCreatedDate(new Date());
                            currencyRateDao.insert(currencyRate);
                        }
                    }
                } else {
                    dao.update(dto.createPk(), dto);
                }

                return new ModelAndView("1_setup/CurrencyView", "dto", dto);
            } catch (Exception e) {
                e.printStackTrace();
                String errorMsg = e.getMessage();
                HashMap m = new HashMap();
                m.put("mode", mode);
                m.put("msg", errorMsg);

                if (isCreate) {
                    return new ModelAndView("1_setup/CurrencyAdd", "model", m);
                } else {
                    return new ModelAndView("1_setup/CurrencyEdit", "model", m);
                }
            }
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
        CurrencyDao dao = DaoFactory.createCurrencyDao();
        Currency dto = dao.findByPrimaryKey(id);

        if (dto != null) {
            dto.setIsActive(AppConstant.STATUS_FALSE);
            dto.setUpdatedBy(pcreatedBy);
            dto.setUpdatedDate(new java.util.Date());
            dao.update(dto.createPk(), dto);
        }

        List<Currency> list = dao.findAll();

        HashMap m = new HashMap();

        m.put("currency", list);
        m.put("totalRows", 0);

        return new ModelAndView("1_setup/CurrencyList", "model", m);
    }

    public void getUnique(HttpServletRequest request, HttpServletResponse response)
            throws IOException, CurrencyDaoException {

        PrintWriter pw = response.getWriter();
        String uniCode = request.getParameter("term");

        CurrencyDao currencyDao = DaoFactory.createCurrencyDao();

        List<Currency> dp = currencyDao.findWhereCurrencyCodeEquals(uniCode);
        if (dp.isEmpty()) {
            pw.print("{\"status\": false}");
        } else {
            pw.print("{\"status\": true}");
        }
    }
    
    //Modified 11 April
    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();

        CurrencyDao distDao = DaoFactory.createCurrencyDao();

        pw.print("{\"maxpage\": " + distDao.ajaxMaxPage(request.getParameter("where"), new BigDecimal(request.getParameter("show"))) + ",\"data\": [");
        List<Currency> ps = distDao.ajaxSearch(request.getParameter("where"), request.getParameter("order"), Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10));
        for (Currency x : ps) {
            if (b) {
                pw.print(",");
            }
            pw.print("{\"1\": \"" + x.getId() + "\", ");
            pw.print("\"2\": \"" + x.getCurrencyCode()+ "\", ");
            pw.print("\"3\": \"" + x.getCurrencyName()+ "\", ");
            pw.print("\"4\": \"" + x.getCurrencySymbol()+ "\", ");
            pw.print("\"5\": \"" + x.getIsActive()+ "\"}");

            b = Boolean.TRUE;
        }
        pw.print("]}");
    }
    
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.parseInt(request.getParameter("key"));
        CurrencyDao currencyDao = DaoFactory.createCurrencyDao();
        Currency dto = currencyDao.findId(id);
        
        Map map = new HashMap();
        map.put("mode", dto);
        return new ModelAndView("1_setup/CurrencyEdit","model",map);
    }
}
