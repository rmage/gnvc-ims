package com.spfi.ims.controller;

import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.FGReturnCargoDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class FGReturnCargoController extends MultiActionController {

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/finish_goods/ReturnCargoList");
    }

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/finish_goods/ReturnCargoAdd");
    }

    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            String data = request.getParameter("data");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            /* DAO | Define needed dao here */
            FGReturnCargoDao fgrcDao = DaoFactory.createFGReturnCargoDao();

            /* TRANSACTION | Something complex here */
            fgrcDao.insert(data, lu.getUserId());

            return new ModelAndView("redirect:FGReturnCargo.htm");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FGReturnCargo.htm?action=create");
        }
    }

    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();

        /* DAO | Define needed dao here */
        FGReturnCargoDao fgrcDao = DaoFactory.createFGReturnCargoDao();

        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(fgrcDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = fgrcDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("rr_code")).append("\", ");
            sb.append("\"2\": \"").append(x.get("rr_code")).append("\", ");
            sb.append("\"3\": \"").append(x.get("rr_date")).append("\", ");
            sb.append("\"4\": \"").append(x.get("eds_code")).append("\", ");
            sb.append("\"5\": \"").append(x.get("rr_from")).append("\", ");
            sb.append("\"6\": \"").append(x.get("rr_remarks")).append("\", ");
            sb.append("\"7\": \"").append(x.get("created_by")).append("\"}");

            b = Boolean.TRUE;
        }
        sb.append("]}");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
    }

    public void getPalletTransfer(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();

        /* DAO | Define needed dao here */
        FGReturnCargoDao fgrcDao = DaoFactory.createFGReturnCargoDao();

        /* TRANSACTION | Something complex here */
        sb.append("[");
        List<Map<String, Object>> ms = fgrcDao.getPalletTransfer(request.getParameter("key"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("eds_code")).append("\", ");
            sb.append("\"2\": \"").append(x.get("bor_buyer")).append("\", ");
            sb.append("\"3\": \"").append(x.get("pack_style")).append("\", ");
            sb.append("\"4\": \"").append(x.get("pack_size")).append("\", ");
            sb.append("\"5\": \"").append(x.get("pts_code")).append("\", ");
            sb.append("\"6\": \"").append(x.get("item_name")).append("\", ");
            sb.append("\"7\": \"").append(x.get("lmr_labeled")).append("\", ");
            sb.append("\"101\": \"").append(x.get("item_id")).append("\", ");
            sb.append("\"102\": \"").append(x.get("pack_per_cs")).append("\"}");

            b = Boolean.TRUE;
        }
        sb.append("]");
        pw.print(sb.toString());
        pw.flush();
        pw.close();

    }

}
