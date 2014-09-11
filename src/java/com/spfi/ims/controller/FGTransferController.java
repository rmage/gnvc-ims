package com.spfi.ims.controller;

import com.app.wms.engine.db.dao.DepartmentDao;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.DepartmentDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.FGTransferDao;
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

public class FGTransferController extends MultiActionController {

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/finish_goods/TransferList");
    }

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response)
            throws DepartmentDaoException {
        /* DATA | get initial value */
        HashMap<String, Object> model = new HashMap<String, Object>();

        /* DAO | Define needed dao here */
        DepartmentDao dDao = DaoFactory.createDepartmentDao();

        /* TRANSACTION | Something complex here */
        model.put("ds", dDao.findAll());

        return new ModelAndView("default/finish_goods/TransferAdd", "ims", model);
    }

    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            String data = request.getParameter("data");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            /* DAO | Define needed dao here */
            FGTransferDao fgtDao = DaoFactory.createFGTransferDao();

            /* TRANSACTION | Something complex here */
            fgtDao.insert(data, lu.getUserId());

            return new ModelAndView("redirect:FGTransfer.htm");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FGTransfer.htm?action=create");
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
        FGTransferDao fgtDao = DaoFactory.createFGTransferDao();

        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(fgtDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = fgtDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("ts_code")).append("\", ");
            sb.append("\"2\": \"").append(x.get("ts_code")).append("\", ");
            sb.append("\"3\": \"").append(x.get("ts_type")).append("\", ");
            sb.append("\"4\": \"").append(sdf.format(x.get("ts_date"))).append("\", ");
            sb.append("\"5\": \"").append(x.get("ts_from")).append("\", ");
            sb.append("\"6\": \"").append(x.get("ts_to")).append("\", ");
            sb.append("\"7\": \"").append(x.get("ts_remarks")).append("\", ");
            sb.append("\"8\": \"").append(x.get("created_by")).append("\"}");

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
        FGTransferDao fgtDao = DaoFactory.createFGTransferDao();

        /* TRANSACTION | Something complex here */
        sb.append("[");
        List<Map<String, Object>> ms = fgtDao.getPalletTransfer(request.getParameter("key"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("pack_style")).append("\", ");
            sb.append("\"2\": \"").append(x.get("pack_size")).append("\", ");
            sb.append("\"3\": \"").append(x.get("item_id")).append("\", ");
            sb.append("\"4\": \"").append(x.get("item_name")).append("\", ");
            sb.append("\"5\": \"").append(x.get("pts_code")).append("\", ");
            sb.append("\"6\": \"").append(x.get("sc_cqty")).append("\"}");

            b = Boolean.TRUE;
        }
        sb.append("]");
        pw.print(sb.toString());
        pw.flush();
        pw.close();

    }
    
    public void getBadPalletTransfer(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();

        /* DAO | Define needed dao here */
        FGTransferDao fgtDao = DaoFactory.createFGTransferDao();

        /* TRANSACTION | Something complex here */
        sb.append("[");
        List<Map<String, Object>> ms = fgtDao.getBadPalletTransfer(request.getParameter("key"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("pack_style")).append("\", ");
            sb.append("\"2\": \"").append(x.get("pack_size")).append("\", ");
            sb.append("\"3\": \"").append(x.get("item_id")).append("\", ");
            sb.append("\"4\": \"").append(x.get("item_name")).append("\", ");
            sb.append("\"5\": \"").append(x.get("pts_code")).append("\", ");
            sb.append("\"6\": \"").append(x.get("bs_cqty")).append("\"}");

            b = Boolean.TRUE;
        }
        sb.append("]");
        pw.print(sb.toString());
        pw.flush();
        pw.close();

    }

}
