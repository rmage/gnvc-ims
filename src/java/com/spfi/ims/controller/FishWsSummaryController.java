package com.spfi.ims.controller;

import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.FishWsSummaryDao;
import com.spfi.ims.dto.FishWsSummary;
import com.spfi.ims.helper.StringHelper;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class FishWsSummaryController extends MultiActionController {

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/fish/WsSummaryList");
    }

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/fish/WsSummaryAdd");
    }

    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            FishWsSummary fws = new FishWsSummary();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            
            /* DAO | Define needed dao here */
            FishWsSummaryDao fwsDao = DaoFactory.createFishWsSummaryDao();
            
            /* TRANSACTION | Something complex here */
            fws.setCode(request.getParameter("wssCode"));
            fws.setBatchNo(request.getParameter("batchNo"));
            fws.setDateFrom(sdf.parse(request.getParameter("dateFrom")));
            fws.setDateTo(sdf.parse(request.getParameter("dateTo")));
            fws.setCreatedBy(lu.getUserId());
            fwsDao.insert(fws);
            
            return new ModelAndView("redirect:FishWsSummary.htm");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FishWsSummary.htm?action=create");
        }

    }

    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        StringBuilder sb = new StringBuilder();

        /* DAO | Define needed dao here */
        FishWsSummaryDao fwsDao = DaoFactory.createFishWsSummaryDao();

        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(fwsDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = fwsDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("id")).append("\", ");
            sb.append("\"2\": \"").append(x.get("code")).append("\", ");
            sb.append("\"3\": \"").append(x.get("date_from")).append("\", ");
            sb.append("\"4\": \"").append(x.get("date_to")).append("\", ");
            sb.append("\"5\": \"").append(x.get("batch_no")).append("\", ");
            sb.append("\"6\": \"").append(x.get("supplier")).append("\", ");
            sb.append("\"7\": \"").append(x.get("boat")).append("\", ");
            sb.append("\"8\": \"").append(x.get("created_by")).append("\"}");

            b = Boolean.TRUE;
        }
        sb.append("]}");
        response.getWriter().print(sb.toString());
    }

    public void getBatchInfo(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        StringBuilder sb = new StringBuilder();
        String batchNo = request.getParameter("term");

        /* DAO | Define needed dao here */
        FishWsSummaryDao fwsDao = DaoFactory.createFishWsSummaryDao();

        /* TRANSACTION | Something complex here */
        sb.append("[");
        List<Map<String, Object>> ms = fwsDao.getBatchInfo(batchNo);
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"batchNo\":\"").append(x.get("batch_no")).append("\",");
            sb.append("\"boat\":\"").append(x.get("boat")).append("\",");
            sb.append("\"supplier\":\"").append(x.get("supplier")).append("\"}");
            b = Boolean.TRUE;
        }
        sb.append("]");
        response.getWriter().print(sb.toString());
    }
    
    // 2015 Update | by FYA
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            DaoFactory.createFishWsSummaryDao().delete(Integer.parseInt(request.getParameter("key")), lu.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:FishWsSummary.htm");
    }

    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();

        try {
            model.put("wss", DaoFactory.createFishWsSummaryDao().getWSSummary(Integer.parseInt(request.getParameter("key"))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("default/fish/WsSummaryUpdate", "model", model);
    }

    public ModelAndView ajaxNUpdate(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map<String, Object> json = new HashMap<String, Object>();

        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            String data = URLDecoder.decode(request.getParameter("data"), "utf-8");
            String[] separator = StringHelper.getDataSeparator(data, 2);

            data = data.replaceAll(":s:", separator[0]).replaceAll(":se:", separator[1]);
            DaoFactory.createFishWsSummaryDao().ajaxNUpdate(data, separator[0], separator[1], lu.getUserId());

            json.put("message", "");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
        }

        return new ModelAndView("jsonView", json);
    }

}
