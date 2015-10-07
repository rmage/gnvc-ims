package com.spfi.ims.controller;

import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.FGOrderFillDao;
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

public class FGOrderFillController extends MultiActionController {

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/finish_goods/OrderFillList");
    }

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/finish_goods/OrderFillAdd");
    }

    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            String data = request.getParameter("data");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            
            /* DAO | Define needed dao here */
            FGOrderFillDao fgofDao = DaoFactory.createFGOrderFillDao();
            
            /* TRANSACTION | Something complex here */
            fgofDao.insert(data, lu.getUserId());

            return new ModelAndView("redirect:FGOrderFill.htm");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FGOrderFill.htm?action=create");
        }
    }

    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response)
            throws IOException, JSONException {
        
        /* DATA | get initial value */
        JSONArray rows = new JSONArray();
        JSONObject json = new JSONObject();

        /* DAO | Define needed dao here */
        FGOrderFillDao fgofDao = DaoFactory.createFGOrderFillDao();

        /* TRANSACTION | Something complex here */
        json.put("maxpage", fgofDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where")));
        List<Map<String, Object>> ms = fgofDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            JSONObject row = new JSONObject();
            row.put("1", x.get("ofal_id"));
            row.put("2", x.get("ofal_code"));
            row.put("3", x.get("ofal_date"));
            row.put("4", x.get("ofal_cancode"));
            row.put("5", x.get("ofal_shipment"));
            row.put("6", x.get("buyer_name"));
            row.put("7", x.get("brand_name"));
            row.put("8", x.get("bor_reference"));
            row.put("9", x.get("bor_destination"));
            row.put("10", x.get("ofal_remarks"));
            row.put("11", x.get("created_by"));
            rows.put(row);
        }
        json.put("data", rows);
        json.write(response.getWriter());
        
    }
    
    public void getBor(HttpServletRequest request, HttpServletResponse response)
            throws IOException, JSONException {
        
        /* DATA | get initial value */
        JSONArray json = new JSONArray();
        
        /* DAO | Define needed dao here */
        FGOrderFillDao fgofDao = DaoFactory.createFGOrderFillDao();
        
        /* TRANSACTION | Something complex here */
        List<Map<String, Object>> ms = fgofDao.getBor(request.getParameter("term"));
        for (Map<String, Object> x : ms) {
            String[] ndw = (x.get("pack_style").toString().split(" ; "))[5].split("/");
            
            JSONObject row = new JSONObject();
            row.put("1", x.get("bor_reference"));
            row.put("2", x.get("bor_date"));
            row.put("3", x.get("bor_reference"));
            row.put("4", x.get("bor_number"));
            row.put("5", x.get("bor_buyer"));
            row.put("6", x.get("brand_name"));
            row.put("7", x.get("bor_destination"));
            row.put("8", x.get("bor_case"));
            row.put("9", x.get("pack_style_code") + " / " + x.get("pack_size"));
            row.put("10", ndw[0].trim());
            row.put("11", x.get("bor_maxcancode"));
            row.put("12", ndw[1].trim());
            row.put("13", x.get("bor_flakes"));
            row.put("101", x.get("item_code"));
            json.put(row);
        }
        json.write(response.getWriter());
        
    }

    public void getPts(HttpServletRequest request, HttpServletResponse response)
            throws IOException, JSONException {

        /* DATA | get initial value */
        JSONArray json = new JSONArray();

        /* DAO | Define needed dao here */
        FGOrderFillDao fgofDao = DaoFactory.createFGOrderFillDao();

        /* TRANSACTION | Something complex here */
        List<Map<String, Object>> ms = fgofDao.getPalletTransfer(request.getParameter("pts"), request.getParameter("item"));
        for (Map<String, Object> x : ms) {
            JSONObject row = new JSONObject();
            row.put("0", x.get("pts_id"));
            row.put("1", x.get("pts_code"));
            row.put("2", x.get("pts_can_code"));
            row.put("3", x.get("pts_pdate"));
            row.put("4", x.get("production_batch"));
            row.put("5", x.get("pts_shift"));
            row.put("6", x.get("pts_quantity"));
            row.put("7", x.get("co_nw"));
            row.put("8", x.get("co_dw"));
            row.put("9", x.get("co_flk"));
            row.put("10", x.get("pts_location"));
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
            DaoFactory.createFGOrderFillDao().ajaxNSave(data, separator[0], separator[1], lu.getUserId());

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
            DaoFactory.createFGOrderFillDao().delete(Integer.parseInt(request.getParameter("key")), lu.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:FGOrderFill.htm");
    }

    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();

        try {
            String key = request.getParameter("key");

            FGOrderFillDao fgptDao = DaoFactory.createFGOrderFillDao();
            model.put("ofals", fgptDao.getOrderFill(Integer.parseInt(key)));

            return new ModelAndView("default/finish_goods/OrderFillUpdate", "model", model);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FGOrderFill.htm");
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
            DaoFactory.createFGOrderFillDao().ajaxNUpdate(data, separator[0], separator[1], lu.getUserId());

            json.put("message", "");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
        }

        return new ModelAndView("jsonView", json);
    }

}
