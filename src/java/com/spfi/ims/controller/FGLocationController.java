package com.spfi.ims.controller;

import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.FGLocationDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class FGLocationController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/finish_goods/LocationList");
    }
    
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/finish_goods/LocationAdd");
    }
    
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        HashMap<String, Object> model;
        int key = Integer.parseInt(request.getParameter("key"));
        
        /* DAO | Define needed dao here */
        FGLocationDao fglDao = DaoFactory.createFGLocationDao();
        model = (HashMap<String, Object>) fglDao.findById(key);
        
        /* TRANSACTION | Something complex here */
        
        return new ModelAndView("default/finish_goods/LocationEdit", "ims", model);
    }
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            String mode = request.getParameter("mode");
            String data = request.getParameter("locationCode") + "," + request.getParameter("locationName") + ",";
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            
            /* DAO | Define needed dao here */
            FGLocationDao fglDao = DaoFactory.createFGLocationDao();
            
            /* TRANSACTION | Something complex here */
            if (mode.equals("insert")) {
                fglDao.insert(data, lu.getUserId());
            } else if (mode.equals("edit")) {
                int key = Integer.parseInt(request.getParameter("key"));
                fglDao.edit(key, data, lu.getUserId());
            }
            
            return new ModelAndView("redirect:FGLocation.htm");
        } catch(Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FGLocation.htm?action=create");
        }
    }
    
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            int key = Integer.parseInt(request.getParameter("key"));
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            DaoFactory.createFGLocationDao().delete(key, lu.getUserId());
            return new ModelAndView("redirect:FGLocation.htm");
        } catch(Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FGLocation.htm");
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
        FGLocationDao fglDao = DaoFactory.createFGLocationDao();
        
        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(fglDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = fglDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) sb.append(",");
            sb.append("{\"1\": \"").append(x.get("loca_id")).append("\", ");
            sb.append("\"2\": \"").append(x.get("loca_code")).append("\", ");
            sb.append("\"3\": \"").append(x.get("loca_name")).append("\", ");
            sb.append("\"4\": \"").append(x.get("created_by")).append("\", ");
            sb.append("\"5\": \"").append(sdf.format(x.get("created_date"))).append("\"}");

            b = Boolean.TRUE;
        }
        sb.append("]}"); pw.print(sb.toString()); pw.flush(); pw.close();
    }
    
}
