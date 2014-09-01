package com.spfi.ims.controller;

import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.FGLabelingMonitoringDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class FGLabelingMonitoringController extends MultiActionController {

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/finish_goods/LabelingMonitoringList");
    }
    
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/finish_goods/LabelingMonitoringAdd");
    }
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            String data = request.getParameter("data");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            
            /* DAO | Define needed dao here */
            FGLabelingMonitoringDao fglmDao = DaoFactory.createFGLabelingMonitoringDao();
            
            /* TRANSACTION | Something complex here */
            fglmDao.insert(data, lu.getUserId());

            return new ModelAndView("redirect:FGLabelingMonitoring.htm");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FGLabelingMonitoring.htm?action=create");
        }
    }
    
    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();

        /* DAO | Define needed dao here */
        FGLabelingMonitoringDao fglmDao = DaoFactory.createFGLabelingMonitoringDao();

        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(fglmDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = fglmDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("lmr_code")).append("\", ");
            sb.append("\"2\": \"").append(x.get("lmr_code")).append("\", ");
            sb.append("\"3\": \"").append(x.get("lmr_date")).append("\", ");
            sb.append("\"4\": \"").append(x.get("lmr_qty")).append("\", ");
            sb.append("\"5\": \"").append(x.get("ofal_code")).append("\", ");
            sb.append("\"6\": \"").append(x.get("ofal_date")).append("\", ");
            sb.append("\"7\": \"").append(x.get("bor_buyer")).append("\", ");
            sb.append("\"8\": \"").append(x.get("bor_brand")).append("\", ");
            sb.append("\"9\": \"").append(x.get("bor_reff")).append("\", ");
            sb.append("\"10\": \"").append(x.get("created_by")).append("\"}");

            b = Boolean.TRUE;
        }
        sb.append("]}");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
        
    }
    
    public void getOfal(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        
        /* DAO | Define needed dao here */
        FGLabelingMonitoringDao fglmDao = DaoFactory.createFGLabelingMonitoringDao();
        
        /* TRANSACTION | Something complex here */
        sb.append("[");
        List<Map<String, Object>> ms = fglmDao.getOfal(request.getParameter("key"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("pack_style")).append(" / ").append(x.get("pack_size")).append("\", ");
            sb.append("\"2\": \"").append(x.get("bor_buyer")).append("\", ");
            sb.append("\"3\": \"").append(x.get("bor_brand")).append("\", ");
            sb.append("\"4\": \"").append(x.get("bor_reff")).append("\", ");
            sb.append("\"5\": \"").append(x.get("bor_destport")).append("\", ");
            sb.append("\"6\": \"").append(x.get("bor_qty")).append("\", ");
            sb.append("\"7\": \"").append(x.get("pack_per_cs")).append("\", ");
            sb.append("\"8\": \"").append(x.get("pts_code")).append("\", ");
            sb.append("\"9\": \"").append(x.get("pts_pprodbatch")).append("\", ");
            sb.append("\"10\": \"").append(x.get("pts_pdate")).append("\", ");
            sb.append("\"11\": \"").append(x.get("ofal_qty")).append("\", ");
            sb.append("\"12\": \"").append(x.get("ofal_id")).append("\"}");
            
            b = Boolean.TRUE;
        }
        sb.append("]");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
        
    }

}
