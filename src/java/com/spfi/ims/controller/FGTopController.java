package com.spfi.ims.controller;

import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.FGTopDao;
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

public class FGTopController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/finish_goods/TopList");
    }
    
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/finish_goods/TopAdd");
    }
    
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        HashMap<String, Object> model;
        int key = Integer.parseInt(request.getParameter("key"));
        
        /* DAO | Define needed dao here */
        FGTopDao fgtDao = DaoFactory.createFGTopDao();
        model = (HashMap<String, Object>) fgtDao.findById(key);
        
        /* TRANSACTION | Something complex here */
        
        return new ModelAndView("default/finish_goods/TopEdit", "ims", model);
    }
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            String mode = request.getParameter("mode");
            String data = request.getParameter("data");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            
            /* DAO | Define needed dao here */
            FGTopDao fgtDao = DaoFactory.createFGTopDao();
            
            /* TRANSACTION | Something complex here */
            if (mode.equals("insert")) {
                fgtDao.insert(data, lu.getUserId());
            } else if (mode.equals("edit")) {
                int key = Integer.parseInt(request.getParameter("key"));
                fgtDao.edit(key, data, lu.getUserId());
            }
            
            return new ModelAndView("redirect:FGTop.htm");
        } catch(Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FGTop.htm?action=create");
        }
    }
    
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            int key = Integer.parseInt(request.getParameter("key"));
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            DaoFactory.createFGTopDao().delete(key, lu.getUserId());
            return new ModelAndView("redirect:FGTop.htm");
        } catch(Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FGTop.htm");
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
        FGTopDao fgtDao = DaoFactory.createFGTopDao();
        
        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(fgtDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = fgtDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) sb.append(",");
            sb.append("{\"1\": \"").append(x.get("top_id")).append("\", ");
            sb.append("\"2\": \"").append(x.get("top_code")).append("\", ");
            sb.append("\"3\": \"").append(x.get("top_description")).append("\", ");
            sb.append("\"4\": \"").append(x.get("created_by")).append("\", ");
            sb.append("\"5\": \"").append(sdf.format(x.get("created_date"))).append("\"}");

            b = Boolean.TRUE;
        }
        sb.append("]}"); pw.print(sb.toString()); pw.flush(); pw.close();
    }
    
}
