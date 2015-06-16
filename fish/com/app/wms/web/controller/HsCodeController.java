package com.app.wms.web.controller;


import com.app.wms.engine.db.dao.HsCodeDao;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.helper.StringHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class HsCodeController extends MultiActionController {
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("hs_code/HSCodeList");
    }

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response){

        /* DATA | get initial value */
        HashMap m = new HashMap();
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

        return new ModelAndView("hs_code/HSCodeAdd", "model", m);
    }

    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

        /* DAO | Define needed dao here */
        HsCodeDao hscodeDao = DaoFactory.createHsCodeDao();

        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(hscodeDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = hscodeDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("code")).append("\", ");
            sb.append("\"2\": \"").append(x.get("code")).append("\", ");
            sb.append("\"3\": \"").append(x.get("name")).append("\", ");
            sb.append("\"4\": \"").append(x.get("created_by")).append("\"}");

            System.out.println("append 1 = " + x.get("name"));
            System.out.println("append 2 = " + x.get("code"));
            System.out.println("append 3 = " + x.get("created_by"));
            b = Boolean.TRUE;
        }
        sb.append("]}");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
    }

    public ModelAndView ajaxNSave(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map<String, Object> json = new HashMap<String, Object>();

        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            String data = URLDecoder.decode(request.getParameter("data"), "utf-8");
            String[] separator = StringHelper.getDataSeparator(data, 2);

            data = data.replaceAll(":s:", separator[0]).replaceAll(":se:", separator[1]);
            DaoFactory.createHsCodeDao().ajaxNSave(data, separator[0], separator[1], lu.getUserId());
            
            System.out.println("data = " + data);
            System.out.println("separator 0 = " + separator[0]);
            System.out.println("separator 1 = " + separator[1]);            

            json.put("message", "");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
        }

        return new ModelAndView("jsonView", json);
    }

    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            DaoFactory.createHsCodeDao().delete(request.getParameter("key"), lu.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:HsCode.htm");
    }

    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
            try {
                String key = request.getParameter("key");
                model.put("hs_code", DaoFactory.createHsCodeDao().getHsCode(key));
            } catch (Exception e) {
                e.printStackTrace();
            }

        return new ModelAndView("hs_code/HSCodeUpdate", "model", model);
    }

    public ModelAndView ajaxNUpdate(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map<String, Object> json = new HashMap<String, Object>();

        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            String data = URLDecoder.decode(request.getParameter("data"), "utf-8");
            String[] separator = StringHelper.getDataSeparator(data, 2);

            data = data.replaceAll(":s:", separator[0]).replaceAll(":se:", separator[1]);
            DaoFactory.createHsCodeDao().ajaxNUpdate(data, separator[0], separator[1], lu.getUserId());

            json.put("message", "");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
        }

        return new ModelAndView("jsonView", json);
    }
}
