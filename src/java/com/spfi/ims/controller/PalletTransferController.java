package com.spfi.ims.controller;

import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.PalletTransferDao;
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

public class PalletTransferController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/finish_goods/PalletTranferList");
    }
    
    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response) 
        throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        /* DAO | Define needed dao here */
        PalletTransferDao ptDao = DaoFactory.createPalletTransferDao();
        
        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(ptDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = ptDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) sb.append(",");
            sb.append("{\"1\": \"").append(x.get("PTS_CODE")).append("\", ");
            sb.append("\"2\": \"").append(x.get("PTS_CODE")).append("\", ");
            sb.append("\"3\": \"").append(sdf.format(x.get("PTS_DATE"))).append("\", ");
            sb.append("\"4\": \"").append(x.get("PTS_FORBRAND")).append("\", ");
            sb.append("\"5\": \"").append(x.get("PTS_PACKSIZE")).append("\", ");
            sb.append("\"6\": \"").append(x.get("PTS_CANCODE")).append("\", ");
            sb.append("\"7\": \"").append(x.get("PTS_REFF")).append("\", ");
            sb.append("\"8\": \"").append(x.get("PTS_LOCATION")).append("\", ");
            sb.append("\"9\": \"").append(x.get("PTS_QTY")).append("\"}");

            b = Boolean.TRUE;
        }
        sb.append("]}"); pw.print(sb.toString()); pw.flush(); pw.close();
    }
    
}
