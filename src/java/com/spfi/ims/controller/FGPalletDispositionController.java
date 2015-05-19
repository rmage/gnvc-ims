package com.spfi.ims.controller;

import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.FGPalletDispositionDao;
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

public class FGPalletDispositionController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/finish_goods/PalletDispositionList");
    }

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/finish_goods/PalletDispositionAdd");
    }

    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            String data = request.getParameter("data");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            /* DAO | Define needed dao here */
            FGPalletDispositionDao fgpdDao = DaoFactory.createFGPalletDispositionDao();

            /* TRANSACTION | Something complex here */
            fgpdDao.insert(data, lu.getUserId());

            return new ModelAndView("redirect:FGPalletDisposition.htm");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FGPalletDisposition.htm?action=create");
        }
    }

    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();

        /* DAO | Define needed dao here */
        FGPalletDispositionDao fgpdDao = DaoFactory.createFGPalletDispositionDao();

        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(fgpdDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = fgpdDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("qa_id")).append("\", ");
            sb.append("\"2\": \"").append(x.get("qa_code")).append("\", ");
            sb.append("\"3\": \"").append(x.get("qa_date")).append("\", ");
            sb.append("\"4\": \"").append(x.get("pts_code")).append("\", ");
            sb.append("\"5\": \"").append(x.get("evaluated_date")).append("\", ");
            sb.append("\"6\": \"").append(x.get("disposition_date")).append("\", ");
            sb.append("\"7\": \"").append(x.get("qa_reason")).append("\", ");
            sb.append("\"8\": \"").append(x.get("qa_remarks")).append("\", ");
            sb.append("\"9\": \"").append(x.get("created_by")).append("\"}");

            b = Boolean.TRUE;
        }
        sb.append("]}");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
    }

    public void getPalletTransfer(HttpServletRequest request, HttpServletResponse response)
            throws IOException, JSONException {

        /* DATA | get initial value */
        JSONArray json = new JSONArray();

        /* DAO | Define needed dao here */
        FGPalletDispositionDao fgpdDao = DaoFactory.createFGPalletDispositionDao();

        /* TRANSACTION | Something complex here */
        List<Map<String, Object>> ms = fgpdDao.getPalletTransfer(request.getParameter("key"));
        for (Map<String, Object> x : ms) {
            JSONObject row = new JSONObject();
            row.put("1", x.get("pts_id"));
            row.put("2", x.get("pts_can_code"));
            row.put("3", x.get("pts_pdate"));
            row.put("4", x.get("pack_style"));
            row.put("5", x.get("pack_size"));
            row.put("6", x.get("item_name"));
            row.put("7", x.get("pts_pqty"));
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
            DaoFactory.createFGPalletDispositionDao().ajaxNSave(data, separator[0], separator[1], lu.getUserId());

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
            DaoFactory.createFGPalletDispositionDao().delete(Integer.parseInt(request.getParameter("key")), lu.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:FGPalletDisposition.htm");
    }

    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();

        try {
            String key = request.getParameter("key");

            FGPalletDispositionDao dao = DaoFactory.createFGPalletDispositionDao();
            model.put("pds", dao.getPalletDisposition(Integer.parseInt(key)));

            return new ModelAndView("default/finish_goods/PalletDispositionUpdate", "model", model);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FGPalletDisposition.htm");
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
            DaoFactory.createFGPalletDispositionDao().ajaxNUpdate(data, separator[0], separator[1], lu.getUserId());

            json.put("message", "");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
        }

        return new ModelAndView("jsonView", json);
    }
    
}
