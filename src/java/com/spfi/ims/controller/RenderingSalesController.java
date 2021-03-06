package com.spfi.ims.controller;

import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.RenderingSalesDao;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class RenderingSalesController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/rendering/SalesList");
    }
    
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/rendering/SalesAdd");
    }
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            String data = request.getParameter("data");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            
            /* DAO | Define needed dao here */
            RenderingSalesDao rsDao = DaoFactory.createRenderingSalesDao();
            
            /* TRANSACTION | Something complex here */
            rsDao.insert(data, lu.getUserId());

            return new ModelAndView("redirect:RenderingSales.htm");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:RenderingSales.htm?action=create");
        }
    }
    
    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();

        /* DAO | Define needed dao here */
        RenderingSalesDao rsDao = DaoFactory.createRenderingSalesDao();

        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(rsDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = rsDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("dr_code")).append("\", ");
            sb.append("\"2\": \"").append(x.get("dr_code")).append("\", ");
            sb.append("\"3\": \"").append(x.get("dr_date")).append("\", ");
            sb.append("\"4\": \"").append(x.get("dr_to")).append("\", ");
            sb.append("\"5\": \"").append(x.get("dr_location_to")).append("\", ");
            sb.append("\"6\": \"").append(x.get("dr_remarks")).append("\", ");
            sb.append("\"7\": \"").append(x.get("created_by")).append("\"}");

            b = Boolean.TRUE;
        }
        sb.append("]}");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
    }
    
    public void ajaxViewStock(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        
        /* DAO | Define needed dao here */
        RenderingSalesDao rsDao = DaoFactory.createRenderingSalesDao();
        
        /* TRANSACTION | Something complex here */
        Map<String, Object> data = rsDao.ajaxViewStock(request.getParameter("date"));
        if (data.isEmpty()) {
            sb.append("{\"col1\":0, \"col2\":0, \"col3\":0}");
        } else {
            sb.append("{\"col1\":").append(data.get("fmfo_fmst_kg"))
                    .append(", \"col2\":").append(data.get("fmfo_fmhp_kg"))
                    .append(", \"col3\":").append(data.get("fmfo_fo_kg")).append("}");
        }
        
        pw.print(sb.toString());
        pw.flush();
        pw.close();
    }
    
    // 2015 Update | by FYA
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            DaoFactory.createRenderingSalesDao().delete(request.getParameter("key"), lu.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:RenderingSales.htm");
    }

    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();

        try {
            String key = request.getParameter("key");
            model.put("rend", DaoFactory.createRenderingSalesDao().getSales(key));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("default/rendering/SalesUpdate", "model", model);
    }

    public ModelAndView ajaxNUpdate(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map<String, Object> json = new HashMap<String, Object>();

        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            String data = URLDecoder.decode(request.getParameter("data"), "utf-8");
            String[] separator = StringHelper.getDataSeparator(data, 2);

            data = data.replaceAll(":s:", separator[0]).replaceAll(":se:", separator[1]);
            DaoFactory.createRenderingSalesDao().ajaxNUpdate(data, separator[0], separator[1], lu.getUserId());

            json.put("message", "");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
        }

        return new ModelAndView("jsonView", json);
    }
    
}
