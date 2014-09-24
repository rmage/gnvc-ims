package com.spfi.ims.controller;

import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.RenderingFishDao;
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
            sb.append("{\"1\": \"").append(x.get("rend_id")).append("\", ");
            sb.append("\"2\": \"").append(x.get("rend_id")).append("\", ");
            sb.append("\"3\": \"").append(x.get("rend_date")).append("\", ");
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
    
}
