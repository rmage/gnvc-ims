package com.spfi.ims.controller;

import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.FGExportDeliveryDao;
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

public class FGExportDeliveryController extends MultiActionController {

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/finish_goods/ExportDeliveryList");
    }

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/finish_goods/ExportDeliveryAdd");
    }
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            String data = request.getParameter("data");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            
            /* DAO | Define needed dao here */
            FGExportDeliveryDao fgedDao = DaoFactory.createFGExportDeliveryDao();
            
            /* TRANSACTION | Something complex here */
            fgedDao.insert(data, lu.getUserId());

            return new ModelAndView("redirect:FGExportDelivery.htm");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FGExportDelivery.htm?action=create");
        }
    }

    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response)
            throws IOException, JSONException {

        /* DATA | get initial value */
        JSONObject json = new JSONObject();
        JSONArray rows = new JSONArray();

        /* DAO | Define needed dao here */
        FGExportDeliveryDao fgedDao = DaoFactory.createFGExportDeliveryDao();

        /* TRANSACTION | Something complex here */
        json.put("maxpage", fgedDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where")));
        List<Map<String, Object>> ms = fgedDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            JSONObject row = new JSONObject();
            row.put("1", x.get("eds_id"));
            row.put("2", x.get("eds_code"));
            row.put("3", x.get("eds_date"));
            row.put("4", x.get("lmr_code"));
            row.put("5", x.get("ofal_code"));
            row.put("6", x.get("buyer_name"));
            row.put("7", x.get("brand_name"));
            row.put("8", x.get("eds_van"));
            row.put("9", x.get("eds_pelayaranseal"));
            row.put("10", x.get("eds_vessel"));
            row.put("11", x.get("eds_platno"));
            row.put("12", x.get("created_by"));
            rows.put(row);
        }
        json.put("data", rows);
        json.write(response.getWriter());

    }
    
    public void getLabelingMonitoring(HttpServletRequest request, HttpServletResponse response)
            throws IOException, JSONException {
        
        /* DATA | get initial value */
        JSONArray json = new JSONArray();
        
        /* DAO | Define needed dao here */
        FGExportDeliveryDao fgedDao = DaoFactory.createFGExportDeliveryDao();
        
        /* TRANSACTION | Something complex here */
        List<Map<String, Object>> ms = fgedDao.getLabelingMonitoring(request.getParameter("key"));
        for (Map<String, Object> x : ms) {
            JSONObject row = new JSONObject();
            row.put("1", x.get("lmr_id"));
            row.put("2", x.get("lmr_code"));
            row.put("3", x.get("buyer_name"));
            row.put("4", x.get("brand_name"));
            row.put("5", x.get("bor_reference"));
            row.put("6", x.get("bor_destination"));
            row.put("7", x.get("number"));
            row.put("8", x.get("pack_style"));
            row.put("9", x.get("pack_size"));
            row.put("10", x.get("pts_cancode"));
            row.put("11", x.get("lmr_labeled"));
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
            DaoFactory.createFGExportDeliveryDao().ajaxNSave(data, separator[0], separator[1], lu.getUserId());

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
            DaoFactory.createFGExportDeliveryDao().delete(Integer.parseInt(request.getParameter("key")), lu.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:FGExportDelivery.htm");
    }

    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();

        try {
            String key = request.getParameter("key");

            FGExportDeliveryDao fglmDao = DaoFactory.createFGExportDeliveryDao();
            model.put("edss", fglmDao.getExportDelivery(Integer.parseInt(key)));

            return new ModelAndView("default/finish_goods/ExportDeliveryUpdate", "model", model);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FGExportDelivery.htm");
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
            DaoFactory.createFGExportDeliveryDao().ajaxNUpdate(data, separator[0], separator[1], lu.getUserId());

            json.put("message", "");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
        }

        return new ModelAndView("jsonView", json);
    }

}
