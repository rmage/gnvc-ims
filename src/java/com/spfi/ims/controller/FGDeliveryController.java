package com.spfi.ims.controller;

import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.FGDeliveryDao;
import com.spfi.ims.helper.StringHelper;
import com.spfi.ims.helper.ValidatorHelper;
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

public class FGDeliveryController extends MultiActionController {
    
     public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/finish_goods/DeliveryList");
    }
    
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/finish_goods/DeliveryAdd");
    }
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            String data = request.getParameter("data");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            
            /* DAO | Define needed dao here */
            FGDeliveryDao fgdDao = DaoFactory.createFGDeliveryDao();
            
            /* TRANSACTION | Something complex here */
            fgdDao.insert(data, lu.getUserId());

            return new ModelAndView("redirect:FGDelivery.htm");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FGDelivery.htm?action=create");
        }
    }
    
    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response) 
        throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        
        /* DAO | Define needed dao here */
        FGDeliveryDao fgdDao = DaoFactory.createFGDeliveryDao();
        
        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(fgdDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = fgdDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) sb.append(",");
            sb.append("{\"1\": \"").append(x.get("dr_code")).append("\", ");
            sb.append("\"2\": \"").append(x.get("dr_code")).append("\", ");
            sb.append("\"3\": \"").append(x.get("dr_date")).append("\", ");
            sb.append("\"4\": \"").append(x.get("dr_from")).append("\", ");
            sb.append("\"5\": \"").append(x.get("dr_to")).append("\", ");
            sb.append("\"6\": \"").append(x.get("dr_remarks")).append("\", ");
            sb.append("\"7\": \"").append(x.get("created_by")).append("\"}");

            b = Boolean.TRUE;
        }
        sb.append("]}"); pw.print(sb.toString()); pw.flush(); pw.close();
    }
    
    public void getPalletTransfer(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();

        /* DAO | Define needed dao here */
        FGDeliveryDao fgdDao = DaoFactory.createFGDeliveryDao();

        /* TRANSACTION | Something complex here */
        sb.append("[");
        List<Map<String, Object>> ms = fgdDao.getPalletTransfer(request.getParameter("key"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("pack_style")).append("\", ");
            sb.append("\"2\": \"").append(x.get("pack_size")).append("\", ");
            sb.append("\"3\": \"").append(x.get("item_code")).append("\", ");
            sb.append("\"4\": \"").append(x.get("item_name")).append("\", ");
            sb.append("\"5\": \"").append(x.get("sc_cqty")).append("\", ");
            sb.append("\"6\": \"").append(x.get("pts_id")).append("\",");
            sb.append("\"7\": \"").append(x.get("pts_code")).append("\"}");
            

            b = Boolean.TRUE;
        }
        sb.append("]");
        pw.print(sb.toString());
        pw.flush();
        pw.close();

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
            DaoFactory.createFGDeliveryDao().ajaxNSave(data, separator[0], separator[1], lu.getUserId(), ValidatorHelper.backDateHelper(lu, "FGDelivery.htm"));

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
            DaoFactory.createFGDeliveryDao().delete(request.getParameter("key"), lu.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:FGDelivery.htm");
    }

    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();

        try {
            String key = request.getParameter("key");

            FGDeliveryDao dao = DaoFactory.createFGDeliveryDao();
            model.put("ds", dao.getDelivery(key));

            return new ModelAndView("default/finish_goods/DeliveryUpdate", "model", model);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FGDelivery.htm");
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
            DaoFactory.createFGDeliveryDao().ajaxNUpdate(data, separator[0], separator[1], lu.getUserId());

            json.put("message", "");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
        }

        return new ModelAndView("jsonView", json);
    }
    
}
