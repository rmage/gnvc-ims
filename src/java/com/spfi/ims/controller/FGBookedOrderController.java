package com.spfi.ims.controller;

import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.FGBookedOrderDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class FGBookedOrderController extends MultiActionController {

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/finish_goods/BookedOrderList");
    }

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/finish_goods/BookedOrderAdd");
    }

    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            String data = request.getParameter("data").replaceAll(":numberSign:", "#");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            
            /* DAO | Define needed dao here */
            FGBookedOrderDao fgboDao = DaoFactory.createFGBookedOrderDao();
            
            /* TRANSACTION | Something complex here */
            fgboDao.insert(data, lu.getUserId());

            return new ModelAndView("redirect:FGBookedOrder.htm");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FGBookedOrder.htm?action=create");
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
        FGBookedOrderDao fgboDao = DaoFactory.createFGBookedOrderDao();
        
        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(fgboDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = fgboDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("bor_code")).append("\", ");
            sb.append("\"2\": \"").append(x.get("bor_code")).append("\", ");
            sb.append("\"3\": \"").append(sdf.format(x.get("bor_date"))).append("\", ");
            sb.append("\"4\": \"").append(x.get("created_by")).append("\"}");
            
            b = Boolean.TRUE;
        }
        sb.append("]}");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
    }
    
    public void getPackStyle(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        
        /* DAO | Define needed dao here */
        FGBookedOrderDao fgboDao = DaoFactory.createFGBookedOrderDao();
        
        /* TRANSACTION | Something complex here */
        sb.append("[");
        List<Map<String, Object>> ms = fgboDao.getPackStyle(request.getParameter("term"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("pack_id")).append("\", ");
            sb.append("\"2\": \"").append(x.get("pack_style")).append("\", ");
            sb.append("\"3\": \"").append(x.get("pack_size")).append("\", ");
            sb.append("\"4\": \"").append(x.get("pack_per_cs")).append("\"}");
            
            b = Boolean.TRUE;
        }
        sb.append("]");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
    }
    
    public void getItem(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        
        /* DAO | Define needed dao here */
        FGBookedOrderDao fgboDao = DaoFactory.createFGBookedOrderDao();
        
        /* TRANSACTION | Something complex here */
        sb.append("[");
        List<Map<String, Object>> ms = fgboDao.getItem(Integer.parseInt(request.getParameter("key")));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("item_id")).append("\", ");
            sb.append("\"2\": \"").append(x.get("item_code")).append("\", ");
            sb.append("\"3\": \"").append(x.get("item_name")).append("\"}");
            
            b = Boolean.TRUE;
        }
        sb.append("]");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
    }

}
