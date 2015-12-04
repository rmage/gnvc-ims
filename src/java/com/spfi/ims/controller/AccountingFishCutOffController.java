package com.spfi.ims.controller;

import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class AccountingFishCutOffController extends MultiActionController {

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();

        model.put("header", DaoFactory.createAccountingCutOffDao().getHeaderData());

        return new ModelAndView("default/accounting/AccountingFishCutOffList", "model", model);
    }

    public ModelAndView getDetailData(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> json = new HashMap<String, Object>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date dateFrom = simpleDateFormat.parse(request.getParameter("dateFrom"));
            Date dateTo = simpleDateFormat.parse(request.getParameter("dateTo"));
            BigDecimal currencyRate = new BigDecimal(request.getParameter("currencyRate"));

            json.put("rows", DaoFactory.createAccountingCutOffDao().getDetailData(simpleDateFormat.format(dateFrom), simpleDateFormat.format(dateTo), request.getParameter("storage"), currencyRate));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("jsonView", json);
    }
    
    public ModelAndView saveDetailData(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> json = new HashMap<String, Object>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            
            Date dateFrom = simpleDateFormat.parse(request.getParameter("dateFrom"));
            Date dateTo = simpleDateFormat.parse(request.getParameter("dateTo"));
            BigDecimal currencyRate = new BigDecimal(request.getParameter("currencyRate"));
            
            DaoFactory.createAccountingCutOffDao().saveDetailData(simpleDateFormat.format(dateFrom), simpleDateFormat.format(dateTo), request.getParameter("storage"), currencyRate, lu.getUserId());
            json.put("message", "");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
        }

        return new ModelAndView("jsonView", json);
    }

}
