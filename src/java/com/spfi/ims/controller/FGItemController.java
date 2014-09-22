package com.spfi.ims.controller;

import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.FGItemDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class FGItemController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/finish_goods/ItemList");
    }
    
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        FGItemDao fglDao = DaoFactory.createFGItemDao();
        
        /* TRANSACTION | Something complex here */
        List<Map<String, Object>> pss = fglDao.getPackStyle();
        
        return new ModelAndView("default/finish_goods/ItemAdd", "ims", pss);
    }
    
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        Map<String, Object> model = new HashMap<String, Object>();
        int key = Integer.parseInt(request.getParameter("key"));
        
        /* DAO | Define needed dao here */
        FGItemDao fglDao = DaoFactory.createFGItemDao();
        
        /* TRANSACTION | Something complex here */
        model.put("i", fglDao.findById(key));
        model.put("ps", fglDao.getPackStyle());
        
        return new ModelAndView("default/finish_goods/ItemEdit", "ims", model);
    }
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            String mode = request.getParameter("mode");
            String data = request.getParameter("itemPackStyle") + "," + request.getParameter("itemCode") + ","
                    + request.getParameter("itemName") + "," + request.getParameter("itemOil") + "," + request.getParameter("itemLid") + ",";
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
//            fyaPrepareData(request, "item");
            /* DAO | Define needed dao here */
            FGItemDao fglDao = DaoFactory.createFGItemDao();
            
            /* TRANSACTION | Something complex here */
            if (mode.equals("insert")) {
                fglDao.insert(data, lu.getUserId());
            } else if (mode.equals("edit")) {
                int key = Integer.parseInt(request.getParameter("key"));
                fglDao.edit(key, data, lu.getUserId());
            }
            
            return new ModelAndView("redirect:FGItem.htm");
        } catch(Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FGItem.htm?action=create");
        }
    }
    
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            int key = Integer.parseInt(request.getParameter("key"));
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            DaoFactory.createFGItemDao().delete(key, lu.getUserId());
            return new ModelAndView("redirect:FGItem.htm");
        } catch(Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FGItem.htm");
        }
    }
    
    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response) 
        throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        /* DAO | Define needed dao here */
        FGItemDao fglDao = DaoFactory.createFGItemDao();
        
        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(fglDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = fglDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) sb.append(",");
            sb.append("{\"1\": \"").append(x.get("item_id")).append("\", ");
            sb.append("\"2\": \"").append(x.get("item_code")).append("\", ");
            sb.append("\"3\": \"").append(x.get("item_name")).append("\", ");
            sb.append("\"4\": \"").append(x.get("pack_style")).append("\", ");
            sb.append("\"5\": \"").append(x.get("pack_size")).append("\", ");
            sb.append("\"6\": \"").append(x.get("item_oil")).append("\", ");
            sb.append("\"7\": \"").append(x.get("item_lid")).append("\", ");
            sb.append("\"8\": \"").append(x.get("created_by")).append("\", ");
            sb.append("\"9\": \"").append(sdf.format(x.get("created_date"))).append("\"}");

            b = Boolean.TRUE;
        }
        sb.append("]}"); pw.print(sb.toString()); pw.flush(); pw.close();
    }
    
    public String fyaPrepareData (HttpServletRequest request, String prefix) {
        StringBuilder sb = new StringBuilder();
        
        Enumeration<String> paramNames = request.getParameterNames();
        HashMap<String, String[]> paramMap = (HashMap<String, String[]>) request.getParameterMap();
        while (paramNames.hasMoreElements()) {
            String s = "";
            String pn = paramNames.nextElement();
            if (pn.contains(prefix)) {
                String[] ss = paramMap.get(pn);
                for (String x : ss) {
                    s = s + x + (ss.length > 1 ? "!" : "");
                }
                sb.append(s).append(",");
            }
        }
//        System.err.println("IMS:DEBUG::" + sb.toString());
        return sb.toString();
    }
    
}
