package com.spfi.ims.controller;

import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.FGPalletTransferDao;
import com.spfi.ims.helper.StringHelper;
import com.spfi.ims.helper.ValidatorHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.NumberFormat;
import java.util.ArrayList;
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

public class FGPalletTransferController extends MultiActionController {

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/finish_goods/PalletTransferList");
    }

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        HashMap<String, Object> model = new HashMap<String, Object>();

        /* DAO | Define needed dao here */
        FGPalletTransferDao fgptDao = DaoFactory.createFGPalletTransferDao();

        /* TRANSACTION | Something complex here */
        model.put("ls", fgptDao.getLocation());

        return new ModelAndView("default/finish_goods/PalletTransferAdd", "ims", model);
    }

    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            String data = request.getParameter("data");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            /* DAO | Define needed dao here */
            FGPalletTransferDao fgptDao = DaoFactory.createFGPalletTransferDao();

            /* TRANSACTION | Something complex here */
            fgptDao.insert(data, lu.getUserId());

            return new ModelAndView("redirect:FGPalletTransfer.htm");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FGPalletTransfer.htm?action=create");
        }
    }

    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response)
            throws IOException, JSONException {
        /* DATA | get initial value */
        JSONObject json = new JSONObject();
        JSONArray rows = new JSONArray();

        /* DAO | Define needed dao here */
        FGPalletTransferDao fgptDao = DaoFactory.createFGPalletTransferDao();

        /* TRANSACTION | Something complex here */
        json.put("maxpage", fgptDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where")));
        List<Map<String, Object>> ms = fgptDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            JSONObject row = new JSONObject();
            row.put("1", x.get("pts_id"));
            row.put("2", x.get("pts_code"));
            row.put("3", x.get("pts_date"));
            row.put("4", x.get("pts_for_brand"));
            row.put("5", x.get("pack_style") + " (" + x.get("can_size") + ")");
            row.put("6", x.get("pts_can_code"));
            row.put("7", x.get("bor_reference"));
            row.put("8", x.get("loca_code"));
            row.put("9", NumberFormat.getInstance().format(x.get("pts_total_qty")));
            row.put("10", (x.get("is_hold").equals("N") ? (x.get("is_reject").equals("N") ? "RELEASE" : "<b style=\"color: #cccccc;\">REJECT</b>") : "<b style=\"color: #ff0000;\">HOLD</b>"));
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
        FGPalletTransferDao fgptDao = DaoFactory.createFGPalletTransferDao();

        /* TRANSACTION | Something complex here */
        List<Map<String, Object>> ms = fgptDao.getBor(request.getParameter("term"));
        for (Map<String, Object> x : ms) {
            HashMap<String, Object> row = new HashMap<String, Object>();
            row.put("1", x.get("bor_reference"));
            row.put("2", x.get("bor_number"));
            row.put("3", x.get("bor_date"));
            row.put("4", x.get("bor_buyer"));

            json.put(row);
        }

        json.write(response.getWriter());
    }

    public void getItem(HttpServletRequest request, HttpServletResponse response)
            throws IOException, JSONException {

        /* DATA | get initial value */
        JSONArray json = new JSONArray();

        /* DAO | Define needed dao here */
        FGPalletTransferDao fgptDao = DaoFactory.createFGPalletTransferDao();

        /* TRANSACTION | Something complex here */
        List<Map<String, Object>> ms = fgptDao.getItem(request.getParameter("term"));
        for (Map<String, Object> x : ms) {
            HashMap<String, Object> row = new HashMap<String, Object>();
            row.put("1", x.get("ProductCode"));
            row.put("2", x.get("CanSize"));
            row.put("3", x.get("PackStyleCode"));
            json.put(row);
        }

        json.write(response.getWriter());

    }

    // 2015 Update | by FYA
    public ModelAndView ajaxNSave(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> json = new HashMap<String, Object>();

        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            String data = URLDecoder.decode(request.getParameter("data"), "utf-8");
            String[] separator = StringHelper.getDataSeparator(data, 2);

            data = data.replaceAll(":s:", separator[0]).replaceAll(":se:", separator[1]);
            DaoFactory.createFGPalletTransferDao().ajaxNSave(data, separator[0], separator[1], lu.getUserId(), ValidatorHelper.backDateHelper(lu, "FGPalletTransfer.htm"));

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
            DaoFactory.createFGPalletTransferDao().delete(Integer.parseInt(request.getParameter("key")), lu.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:FGPalletTransfer.htm");
    }

    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();

        try {
            String key = request.getParameter("key");

            FGPalletTransferDao fgptDao = DaoFactory.createFGPalletTransferDao();
            model.put("ptss", fgptDao.getPalletTransfer(Integer.parseInt(key)));
            model.put("ls", fgptDao.getLocation());

            return new ModelAndView("default/finish_goods/PalletTransferUpdate", "model", model);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FGPalletTransfer.htm");
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
            DaoFactory.createFGPalletTransferDao().ajaxNUpdate(data, separator[0], separator[1], lu.getUserId());

            json.put("message", "");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
        }

        return new ModelAndView("jsonView", json);
    }

}
