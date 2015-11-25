package com.spfi.ims.controller;

import com.app.wms.engine.db.dao.ReceiveReportDao;
import com.app.wms.engine.db.dto.ReceiveReport;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.mapper.MapperNonFishReceiveReportAccounting;
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

public class AccountingNonFishController extends MultiActionController {

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("default/accounting/AccountingNonFishList");
    }

    public ModelAndView getRR(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        HashMap<String, Object> json = new HashMap<String, Object>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<HashMap<String, Object>> rows = new ArrayList<HashMap<String, Object>>();

        ReceiveReportDao rrDao = DaoFactory.createReceiveReportDao();
        List<ReceiveReport> rrList = rrDao.findByDatePeriod(sdf.parse(request.getParameter("dateFrom")), sdf.parse(request.getParameter("dateTo")));
        for (ReceiveReport rr : rrList) {
            HashMap<String, Object> row = MapperNonFishReceiveReportAccounting.parseToHashMap(rr);
            rows.add(row);
        }
        json.put("rows", rows);

        return new ModelAndView("jsonView", json);
    }

    public ModelAndView getRRDetail(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        HashMap<String, Object> json = new HashMap<String, Object>();

        ReceiveReportDao rrDao = DaoFactory.createReceiveReportDao();

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
                DaoFactory.createReceiveReportDao().doAccounting(data, separator[0], separator[1], lu.getUserId());
            } else {
                DaoFactory.createReceiveReportDao().doAccountingRevise(data, separator[0], separator[1], lu.getUserId());
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
            DaoFactory.createReceiveReportDao().removeAccounting(request.getParameter("rrCode"));
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
        }

        return new ModelAndView("jsonView", json);
    }

//    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response)
//            throws IOException {
//        
//    }
//    
//    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
//        return new ModelAndView("default/accounting/AccountingNonFishAdd");
//    }
//    
//    // 2015 Update | by FYA
//    public ModelAndView ajaxNSave(HttpServletRequest request, HttpServletResponse response)
//            throws IOException {
//        return null;
//    }
//    
//    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return new ModelAndView("redirect:xxx.htm");
//    }
//    
//    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
//        Map<String, Object> model = new HashMap<String, Object>();
//        
//        try {
//            return new ModelAndView("default/accounting/AccountingNonFishUpdate", "model", model);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ModelAndView("redirect:xxx.htm");
//        }
//    }
//    
//    public ModelAndView ajaxNUpdate(HttpServletRequest request, HttpServletResponse response)
//            throws IOException {
//        Map<String, Object> json = new HashMap<String, Object>();
//
//        try {
//            json.put("message", "");
//        } catch (Exception e) {
//            e.printStackTrace();
//            json.put("message", e.getMessage());
//        }
//
//        return new ModelAndView("jsonView", json);
//    }
}
