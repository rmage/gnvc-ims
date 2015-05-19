package com.spfi.ims.controller;

import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.FGLabelingMonitoringDao;
import com.spfi.ims.helper.StringHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.richfaces.json.JSONArray;
import org.richfaces.json.JSONException;
import org.richfaces.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class FGLabelingMonitoringController extends MultiActionController {

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/finish_goods/LabelingMonitoringList");
    }
    
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/finish_goods/LabelingMonitoringAdd");
    }
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            String data = request.getParameter("data");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            
            /* DAO | Define needed dao here */
            FGLabelingMonitoringDao fglmDao = DaoFactory.createFGLabelingMonitoringDao();
            
            /* TRANSACTION | Something complex here */
            fglmDao.insert(data, lu.getUserId());

            return new ModelAndView("redirect:FGLabelingMonitoring.htm");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FGLabelingMonitoring.htm?action=create");
        }
    }
    
    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();

        /* DAO | Define needed dao here */
        FGLabelingMonitoringDao fglmDao = DaoFactory.createFGLabelingMonitoringDao();

        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(fglmDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = fglmDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("lmr_id")).append("\", ");
            sb.append("\"2\": \"").append(x.get("lmr_code")).append("\", ");
            sb.append("\"3\": \"").append(x.get("lmr_date")).append("\", ");
            sb.append("\"4\": \"").append(x.get("lmr_qty")).append("\", ");
            sb.append("\"5\": \"").append(x.get("ofal_code")).append("\", ");
            sb.append("\"6\": \"").append(x.get("ofal_date")).append("\", ");
            sb.append("\"7\": \"").append(x.get("buyer_name")).append("\", ");
            sb.append("\"8\": \"").append(x.get("brand_name")).append("\", ");
            sb.append("\"9\": \"").append(x.get("bor_reference")).append("\", ");
            sb.append("\"10\": \"").append(x.get("created_by")).append("\"}");

            b = Boolean.TRUE;
        }
        sb.append("]}");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
        
    }
    
    public void getOfal(HttpServletRequest request, HttpServletResponse response)
            throws IOException, JSONException {
        
        /* DATA | get initial value */
        JSONArray json = new JSONArray();
        
        /* DAO | Define needed dao here */
        FGLabelingMonitoringDao fglmDao = DaoFactory.createFGLabelingMonitoringDao();
        
        /* TRANSACTION | Something complex here */
        List<Map<String, Object>> ms = fglmDao.getOfal(request.getParameter("key"));
        for (Map<String, Object> x : ms) {
            JSONObject row = new JSONObject();
            row.put("1", x.get("pack_style") + " / " + x.get("pack_size"));
            row.put("2", x.get("buyer_name"));
            row.put("3", x.get("brand_name"));
            row.put("4", x.get("bor_reference"));
            row.put("5", x.get("bor_destination"));
            row.put("6", x.get("bor_case"));
            row.put("7", x.get("pts_code"));
            row.put("8", x.get("pts_pprodbatch"));
            row.put("9", x.get("pts_pdate"));
            row.put("10", x.get("ofal_qty"));
            row.put("11", x.get("ofal_id"));
            row.put("12", x.get("ofald_id"));
            json.put(row);
        }
        json.write(response.getWriter());
        
    }
    
    // 2015 Update | by FYA
    public ModelAndView ajaxNSave(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map<String, Object> json = new HashMap<String, Object>();

        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            String data = URLDecoder.decode(request.getParameter("data"), "utf-8");
            String[] separator = StringHelper.getDataSeparator(data, 2);

            data = data.replaceAll(":s:", separator[0]).replaceAll(":se:", separator[1]);
            DaoFactory.createFGLabelingMonitoringDao().ajaxNSave(data, separator[0], separator[1], lu.getUserId());

            json.put("message", "");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
        }

        return new ModelAndView("jsonView", json);
    }

    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            DaoFactory.createFGLabelingMonitoringDao().delete(Integer.parseInt(request.getParameter("key")), lu.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:FGLabelingMonitoring.htm");
    }

    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();

        try {
            String key = request.getParameter("key");

            FGLabelingMonitoringDao fglmDao = DaoFactory.createFGLabelingMonitoringDao();
            model.put("lms", fglmDao.getLabelingMonitoring(Integer.parseInt(key)));

            return new ModelAndView("default/finish_goods/LabelingMonitoringUpdate", "model", model);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FGLabelingMonitoring.htm");
        }
    }

    public ModelAndView ajaxNUpdate(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map<String, Object> json = new HashMap<String, Object>();

        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            String data = URLDecoder.decode(request.getParameter("data"), "utf-8");
            String[] separator = StringHelper.getDataSeparator(data, 2);

            data = data.replaceAll(":s:", separator[0]).replaceAll(":se:", separator[1]);
            DaoFactory.createFGLabelingMonitoringDao().ajaxNUpdate(data, separator[0], separator[1], lu.getUserId());

            json.put("message", "");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
        }

        return new ModelAndView("jsonView", json);
    }

}
