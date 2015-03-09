package com.spfi.ims.controller;

import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.RenderingFishDao;
import com.spfi.ims.helper.StringHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class RenderingFishController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/rendering/FishList");
    }
    
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/rendering/FishAdd");
    }
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            String data = request.getParameter("data");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            
            /* DAO | Define needed dao here */
            RenderingFishDao rfDao = DaoFactory.createRenderingFishDao();
            
            /* TRANSACTION | Something complex here */
            rfDao.insert(data, lu.getUserId());

            return new ModelAndView("redirect:RenderingFish.htm");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:RenderingFish.htm?action=create");
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
        RenderingFishDao rfDao = DaoFactory.createRenderingFishDao();

        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(rfDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = rfDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("fmfo_code")).append("\", ");
            sb.append("\"2\": \"").append(x.get("fmfo_code")).append("\", ");
            sb.append("\"3\": \"").append(x.get("fmfo_date")).append("\", ");
            sb.append("\"4\": \"").append(x.get("fmfo_spsack")).append("\", ");
            sb.append("\"5\": \"").append(x.get("fmfo_spkg")).append("\", ");
            sb.append("\"6\": \"").append(x.get("created_by")).append("\", ");
            sb.append("\"7\": \"").append(sdf.format(x.get("created_date"))).append("\"}");

            b = Boolean.TRUE;
        }
        sb.append("]}");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
    }
    
    public void ajaxPrepareData(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        
        /* DAO | Define needed dao here */
        RenderingFishDao rfDao = DaoFactory.createRenderingFishDao();
        
        /* TRANSACTION | Something complex here */
        Map<String, Object> data = rfDao.ajaxPrepare(request.getParameter("date"));
        if (data.isEmpty()) {
            sb.append("{\"status\": 0}");
        } else {
            if (data.get("status").equals(1)) {
                sb.append("{\"status\": ").append(data.get("status")).append(", ");
                sb.append("\"1\": ").append(data.get("col1")).append(", ");
                sb.append("\"2\": ").append(data.get("col2")).append(", ");
                sb.append("\"3\": ").append(data.get("col3")).append(", ");
                sb.append("\"4\": ").append(data.get("col4")).append(", ");
                sb.append("\"5\": ").append(data.get("col5")).append(", ");
                sb.append("\"6\": ").append(data.get("col6")).append("}");
            } else {
                sb.append("{\"status\": ").append(data.get("status")).append("}");
            }
        }
        
        pw.print(sb.toString());
        pw.flush();
        pw.close();
        
    }
    
    // 2015 Update | by FYA
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            DaoFactory.createRenderingFishDao().delete(Integer.parseInt(request.getParameter("key")), lu.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:RenderingFish.htm");
    }

    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();

        try {
            String key = request.getParameter("key");
            model.put("rH", DaoFactory.createRenderingFishDao().getRenderingFish(key, 0));
            model.put("rR", DaoFactory.createRenderingFishDao().getRenderingFish(key, 1));
            model.put("rD", DaoFactory.createRenderingFishDao().getRenderingFish(key, 2));
            model.put("rM", DaoFactory.createRenderingFishDao().getRenderingFish(key, 3));
            model.put("rB", DaoFactory.createRenderingFishDao().getRenderingFish(key, 4));
            model.put("rS", DaoFactory.createRenderingFishDao().getRenderingFish(key, 5));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("default/rendering/FishUpdate", "model", model);
    }

    public ModelAndView ajaxNUpdate(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map<String, Object> json = new HashMap<String, Object>();

        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            String data = URLDecoder.decode(request.getParameter("data"), "utf-8");
            String[] separator = StringHelper.getDataSeparator(data, 2);

            data = data.replaceAll(":s:", separator[0]).replaceAll(":se:", separator[1]);
            DaoFactory.createRenderingFishDao().ajaxNUpdate(data, separator[0], separator[1], lu.getUserId());

            json.put("message", "");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
        }

        return new ModelAndView("jsonView", json);
    }
    
}
