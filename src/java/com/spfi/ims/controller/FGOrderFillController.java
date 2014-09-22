package com.spfi.ims.controller;

import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.FGOrderFillDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class FGOrderFillController extends MultiActionController {

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/finish_goods/OrderFillList");
    }

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/finish_goods/OrderFillAdd");
    }

    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            String data = request.getParameter("data");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            
            /* DAO | Define needed dao here */
            FGOrderFillDao fgofDao = DaoFactory.createFGOrderFillDao();
            
            /* TRANSACTION | Something complex here */
            fgofDao.insert(data, lu.getUserId());

            return new ModelAndView("redirect:FGOrderFill.htm");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FGOrderFill.htm?action=create");
        }
    }

    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();

        /* DAO | Define needed dao here */
        FGOrderFillDao fgofDao = DaoFactory.createFGOrderFillDao();

        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(fgofDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = fgofDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("ofal_code")).append("\", ");
            sb.append("\"2\": \"").append(x.get("ofal_code")).append("\", ");
            sb.append("\"3\": \"").append(x.get("ofal_date")).append("\", ");
            sb.append("\"4\": \"").append(x.get("ofal_cancode")).append("\", ");
            sb.append("\"5\": \"").append(x.get("ofal_shipment")).append("\", ");
            sb.append("\"6\": \"").append(x.get("bor_buyer")).append("\", ");
            sb.append("\"7\": \"").append(x.get("bor_brand")).append("\", ");
            sb.append("\"8\": \"").append(x.get("bor_reff")).append("\", ");
            sb.append("\"9\": \"").append(x.get("bor_destport")).append("\", ");
            sb.append("\"10\": \"").append(x.get("ofal_remarks")).append("\", ");
            sb.append("\"11\": \"").append(x.get("created_by")).append("\"}");

            b = Boolean.TRUE;
        }
        sb.append("]}");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
        
    }
    
    public void getBor(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        
        /* DAO | Define needed dao here */
        FGOrderFillDao fgofDao = DaoFactory.createFGOrderFillDao();
        
        /* TRANSACTION | Something complex here */
        sb.append("[");
        List<Map<String, Object>> ms = fgofDao.getBor(request.getParameter("term"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("bor_id")).append("\", ");
            sb.append("\"2\": \"").append(x.get("bor_date")).append("\", ");
            sb.append("\"3\": \"").append(x.get("bor_code")).append("\", ");
            sb.append("\"4\": \"").append(x.get("bor_buyer")).append("\", ");
            sb.append("\"5\": \"").append(x.get("bor_brand")).append("\", ");
            sb.append("\"6\": \"").append(x.get("bor_reff")).append("\", ");
            sb.append("\"7\": \"").append(x.get("bor_destport")).append("\", ");
            sb.append("\"8\": \"").append(x.get("bor_qty")).append("\", ");
            sb.append("\"9\": \"").append(x.get("pack_style")).append(" / ").append(x.get("pack_size")).append("\", ");
            sb.append("\"10\": \"").append(x.get("bor_nw")).append("\", ");
            sb.append("\"11\": \"").append(x.get("bor_ocancodemax")).append("\", ");
            sb.append("\"12\": \"").append(x.get("bor_odw")).append("\", ");
            sb.append("\"13\": \"").append(x.get("bor_opercentflakes")).append("\", ");
            sb.append("\"101\": \"").append(x.get("item_id")).append("\", ");
            sb.append("\"102\": \"").append(x.get("pack_per_cs")).append("\"}");
            
            b = Boolean.TRUE;
        }
        sb.append("]");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
        
    }

    public void getPts(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();

        /* DAO | Define needed dao here */
        FGOrderFillDao fgofDao = DaoFactory.createFGOrderFillDao();

        /* TRANSACTION | Something complex here */
        sb.append("[");
        List<Map<String, Object>> ms = fgofDao.getPalletTransfer(request.getParameter("pts"), Integer.parseInt(request.getParameter("item")));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("pts_code")).append("\", ");
            sb.append("\"2\": \"").append(x.get("pts_cancode")).append("\", ");
            sb.append("\"3\": \"").append(x.get("production_date")).append("\", ");
            sb.append("\"4\": \"").append(x.get("production_batch")).append("\", ");
            sb.append("\"5\": \"").append(x.get("pts_shift")).append("\", ");
            sb.append("\"6\": \"").append(x.get("pts_quantity")).append("\", ");
            sb.append("\"7\": \"").append(x.get("co_nw")).append("\", ");
            sb.append("\"8\": \"").append(x.get("co_dw")).append("\", ");
            sb.append("\"9\": \"").append(x.get("co_flk")).append("\", ");
            sb.append("\"10\": \"").append(x.get("pts_location")).append("\"}");

            b = Boolean.TRUE;
        }
        sb.append("]");
        pw.print(sb.toString());
        pw.flush();
        pw.close();

    }

}
