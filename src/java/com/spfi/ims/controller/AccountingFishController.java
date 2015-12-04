package com.spfi.ims.controller;

import com.app.wms.engine.db.dao.FishRrDao;
import com.app.wms.engine.db.dao.ReceiveReportDao;
import com.app.wms.engine.db.dto.ReceiveReport;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.mapper.MapperFishReceiveReportAccounting;
import com.spfi.ims.helper.StringHelper;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class AccountingFishController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("default/accounting/AccountingFishList");
    }
    
    public ModelAndView getRR(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        HashMap<String, Object> json = new HashMap<String, Object>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<HashMap<String, Object>> rows = new ArrayList<HashMap<String, Object>>();

        FishRrDao frrDao = DaoFactory.createFishRrDao();
        List<Map<String, Object>> rrList = frrDao.findByDatePeriod(sdf.parse(request.getParameter("dateFrom")), sdf.parse(request.getParameter("dateTo")));
        for (Map<String, Object> rr : rrList) {
            HashMap<String, Object> row = MapperFishReceiveReportAccounting.parseToHashMap(rr);
            rows.add(row);
        }
        json.put("rows", rows);

        return new ModelAndView("jsonView", json);
    }
    
    public ModelAndView getRRDetail(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        HashMap<String, Object> json = new HashMap<String, Object>();

        FishRrDao rrDao = DaoFactory.createFishRrDao();

        List<Map<String, Object>> rows;
        if (request.getParameter("isRevise") == null) {
            rows = rrDao.getReceivingDetailForAccounting(request.getParameter("rrCode"));
        } else {
            rows = rrDao.getReceivingDetailForAccounting(request.getParameter("rrCode"), 1);
        }
        json.put("rows", rows);

        return new ModelAndView("jsonView", json);
    }
    
    public ModelAndView doRRProcess(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> json = new HashMap<String, Object>();

        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            String data = URLDecoder.decode(request.getParameter("data"), "utf-8");
            String[] separator = StringHelper.getDataSeparator(data, 2);

            data = data.replaceAll(":s:", separator[0]).replaceAll(":se:", separator[1]);

            if (request.getParameter("isRevise").equals("0")) {
                DaoFactory.createFishRrDao().doAccounting(data, separator[0], separator[1], lu.getUserId());
            } else {
                DaoFactory.createFishRrDao().doAccountingRevise(data, separator[0], separator[1], lu.getUserId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
        }

        return new ModelAndView("jsonView", json);
    }
    
    public ModelAndView doRRRemove(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> json = new HashMap<String, Object>();

        try {
            DaoFactory.createFishRrDao().removeAccounting(request.getParameter("rrCode"));
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
        }

        return new ModelAndView("jsonView", json);
    }
    
}
