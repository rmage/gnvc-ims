package com.app.wms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.app.wms.engine.db.dao.FishTsDao;
import com.app.wms.engine.db.dao.FishTsDetailDao;
import com.app.wms.engine.db.dto.FishTs;
import com.app.wms.engine.db.dto.FishTsDetail;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.helper.StringHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;

public class FishTsController extends MultiActionController {

    private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("fish/TSDataList");
    }

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap<String, Object> modelMap = new HashMap<String, Object>();

        modelMap.put("mode", "create");
        return new ModelAndView("fish/TSDataAdd", "model", modelMap);
    }
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            String data = request.getParameter("data");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            
            /* DAO | Define needed dao here */
            FishTsDao ftDao = DaoFactory.createFishTsDao();
            
            /* TRANSACTION | Something complex here */
            ftDao.insert2(data, lu.getUserId());

            return new ModelAndView("redirect:FishTs.htm");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FishTs.htm?action=create");
        }
    }
    
    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();

        /* DAO | Define needed dao here */
        FishTsDao ftDao = DaoFactory.createFishTsDao();

        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(ftDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = ftDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("id")).append("\", ");
            sb.append("\"2\": \"").append(x.get("ts_no")).append("\", ");
            sb.append("\"3\": \"").append(x.get("ts_date")).append("\", ");
            sb.append("\"4\": \"").append(x.get("wds_no")).append("\", ");
            sb.append("\"5\": \"").append(x.get("issued_by")).append("\", ");
            sb.append("\"6\": \"").append(x.get("noted_by")).append("\", ");
            sb.append("\"7\": \"").append(x.get("approved_by")).append("\", ");
            sb.append("\"8\": \"").append(x.get("received_by")).append("\", ");
            sb.append("\"9\": \"").append(x.get("created_by")).append("\"}");

            b = Boolean.TRUE;
        }
        
        sb.append("]}");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
    }

    public ModelAndView ajaxDocument(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Map<String, Object> modelMap = new HashMap<String, Object>();
        int tsId = Integer.valueOf(request.getParameter("tsId"));
        FishTsDetailDao dao = DaoFactory.createFishTsDetailDao();
        List<FishTsDetail> tsDetailList = dao.findAllByTsId(tsId);

        Map tableMap = new HashMap();
        for (FishTsDetail fishTsDetail : tsDetailList) {
            Map<String, Object> returnMap = new HashMap<String, Object>();
            returnMap.put("tsNo", fishTsDetail.getTransferSlip().getTsNo());
            returnMap.put("fishType", fishTsDetail.getFish().getCode());
            returnMap.put("fishName", fishTsDetail.getFish().getFishType().getDescription());
            returnMap.put("storage", fishTsDetail.getStorageId() == 0
                    ? "FRESH" : fishTsDetail.getStorage().getCode());
            returnMap.put("qty", fishTsDetail.getQuantity());

            tableMap.put(returnMap, returnMap);
        }

        modelMap.put("tableMap", tableMap);
        return new ModelAndView("fish/TSDataDetailList", "model", modelMap);
    }
    
    // 2015 Update | by FYA

    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            DaoFactory.createFishTsDao().delete(Integer.parseInt(request.getParameter("key")), lu.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:FishTs.htm");
    }

    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();

        try {
            model.put("tss", DaoFactory.createFishTsDao().getTransfer(Integer.parseInt(request.getParameter("key"))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("default/fish/TSDataUpdate", "model", model);
    }

    public ModelAndView ajaxNUpdate(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map<String, Object> json = new HashMap<String, Object>();

        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            String data = URLDecoder.decode(request.getParameter("data"), "utf-8");
            String[] separator = StringHelper.getDataSeparator(data, 2);

            data = data.replaceAll(":s:", separator[0]).replaceAll(":se:", separator[1]);
            DaoFactory.createFishReclassificationDao().ajaxNUpdate(data, separator[0], separator[1], lu.getUserId());

            json.put("message", "");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
        }

        return new ModelAndView("jsonView", json);
    }
    
}
