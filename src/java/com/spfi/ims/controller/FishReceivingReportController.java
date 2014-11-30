package com.spfi.ims.controller;

import com.app.wms.engine.db.dao.FishRrDao;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class FishReceivingReportController extends MultiActionController {

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/fish/ReceivingReportList");
    }

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/fish/ReceivingReportAdd");
    }

    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String[] data = request.getParameter("data").split(":", -1);
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            /* DAO | Define needed dao here */
            FishRrDao frDao = DaoFactory.createFishRrDao();

            /* TRANSACTION | Something complex here */
            frDao.insert(data[0], sdf.parse(data[1]), data[2], data[3], data[4], data[5], data[6], lu.getUserId());

            return new ModelAndView("redirect:FishReceivingReport.htm");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FishReceivingReport.htm?action=create");
        }
    }

    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ParseException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfOut = new SimpleDateFormat("dd/MM/yyyy");

        /* DAO | Define needed dao here */
        FishRrDao frDao = DaoFactory.createFishRrDao();

        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(frDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = frDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("id")).append("\", ");
            sb.append("\"2\": \"").append(x.get("rr_no")).append("\", ");
            sb.append("\"3\": \"").append(sdfOut.format(sdfIn.parse(x.get("rr_date").toString()))).append("\", ");
            sb.append("\"4\": \"").append(x.get("ws_no")).append("\", ");
            sb.append("\"5\": \"").append(x.get("supplier")).append("\", ");
            sb.append("\"6\": \"").append(x.get("batch_no")).append("\"}");

            b = Boolean.TRUE;
        }
        sb.append("]}");
        response.getWriter().print(sb.toString());
    }

    public void getBatchInfo(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        StringBuilder sb = new StringBuilder();
        String batchNo = request.getParameter("term");

        /* DAO | Define needed dao here */
        FishRrDao frDao = DaoFactory.createFishRrDao();

        /* TRANSACTION | Something complex here */
        sb.append("[");
        List<Map<String, Object>> ms = frDao.getBatchInfo(batchNo);
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"batchNo\":\"").append(x.get("batch_no")).append("\",");
            sb.append("\"boat\":\"").append(x.get("boat")).append("\",");
            sb.append("\"supplier\":\"").append(x.get("supplier")).append("\"}");
            b = Boolean.TRUE;
        }
        sb.append("]");
        response.getWriter().print(sb.toString());
    }

    public void getWeightSlip(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ParseException {
        /* DATA | get initial value */
        StringBuilder sb = new StringBuilder();
        String[][] row = new String[1][2];
        String batchNo = request.getParameter("batchNo");
        String dateFrom = request.getParameter("dateFrom");
        String dateTo = request.getParameter("dateTo");
        String type = request.getParameter("type");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfDB = new SimpleDateFormat("yyyy-MM-dd");

        /* DAO | Define needed dao here */
        FishRrDao frDao = DaoFactory.createFishRrDao();

        /* TRANSACTION | Something complex here */
        sb.append("[");
        Map<String, Object> x;
        List<Map<String, Object>> ms = frDao.getWeightSlip(batchNo, dateFrom, dateTo, type);
        for (int i = 0; i < ms.size(); i++) {
            x = ms.get(i);
            if (row[0][0] == null) {
                if (i > 0) {
                    sb.append(",");
                }
                row[0][0] = x.get("ws_no").toString();
                row[0][1] = x.get("ws_type").toString();
                sb.append("{\"wsNo\":\"").append(x.get("ws_no").toString()).append("\",");
                sb.append("\"dateShift\":\"").append(sdf.format(sdfDB.parse(x.get("date_shift").toString()))).append("\",");
                sb.append("\"regu\":\"").append(x.get("regu").toString()).append("\",");
                sb.append("\"timeShift\":\"").append(x.get("time_shift").toString()).append("\",");
                sb.append("\"description\":\"<ul><li>").append(x.get("code").toString()).append("(").append(x.get("total_weight").toString()).append(")</li>");
                // check if end of list (only 1 loop)
                if (ms.size() == (i + 1)) {
                    sb.append("</ul>\"}");
                } else if (!ms.get(i + 1).get("ws_no").equals(row[0][0]) && !ms.get(i + 1).get("ws_no").equals(row[0][1])) {
                    row[0][0] = null;
                    sb.append("</ul>\"}");
                }
            } else {
                sb.append("<li>").append(x.get("code").toString()).append("(").append(x.get("total_weight").toString()).append(")</li>");
                // check if end of list
                if (ms.size() == (i + 1)) {
                    sb.append("</ul>\"}");
                } else if (!ms.get(i + 1).get("ws_no").equals(row[0][0]) && !ms.get(i + 1).get("ws_no").equals(row[0][1])) {
                    row[0][0] = null;
                    sb.append("</ul>\"}");
                }
            }
        }
        sb.append("]");
        response.getWriter().print(sb.toString());
    }

}
