package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.CurrencyDao;
import com.app.wms.engine.db.dao.DepartmentDao;
import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dao.StockInventoryDao;
import com.app.wms.engine.db.dao.SupplierDao;
import com.app.wms.engine.db.dao.SwsDao;
import com.app.wms.engine.db.dao.SwsDtlDao;
import com.app.wms.engine.db.dto.Currency;
import com.app.wms.engine.db.dto.Department;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.StockInventory;
import com.app.wms.engine.db.dto.Supplier;
import com.app.wms.engine.db.dto.SwsDtl;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.DepartmentDaoException;
import com.app.wms.engine.db.exceptions.ProductDaoException;
import com.app.wms.engine.db.exceptions.StockInventoryDaoException;
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

public class StoreWithdrawalSlipController extends MultiActionController {

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("non_fish/SWSList");
    }

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response)
            throws DepartmentDaoException {

        /* DATA | get initial value */
        HashMap m = new HashMap();
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

        /* DAO | Define needed dao here */
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        /* TRANSACTION | Something complex here */
        Department d = departmentDao.findWhereDepartmentCodeEquals(lu.getDepartmentCode()).get(0);
        m.put("department", d.getDepartmentCode() + ":" + d.getDepartmentName());

        return new ModelAndView("non_fish/SWSAdd", "model", m);

    }

    public void ajaxDocument(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ProductDaoException {

        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        String swsCode = request.getParameter("key");

        /* DAO | Define needed dao here */
        SwsDtlDao swsDtlDao = DaoFactory.createSwsDtlDao();
        ProductDao productDao = DaoFactory.createProductDao();

        /* TRANSACTION | Something complex here */
        pw.print("[");
        List<SwsDtl> sds = swsDtlDao.findBySws(swsCode);
        for (SwsDtl x : sds) {
            if (b) {
                pw.print(",");
            }

            Product p = productDao.findWhereProductCodeEquals(x.getProductCode()).get(0);
            pw.print("{\"itemCode\": \"" + p.getProductCode() + "\", ");
            pw.print("\"itemName\": \"" + p.getProductName() + "\",");
            pw.print("\"type\": \"" + p.getProductCategory() + "\",");
            pw.print("\"soh\": \"" + x.getSoh() + "\",");
            pw.print("\"qty\": \"" + x.getQty() + "\",");
            pw.print("\"uom\": \"" + x.getUom() + "\"}");

            b = Boolean.TRUE;
        }
        pw.print("]");

    }

    public void getProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException, StockInventoryDaoException {

        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        String term = request.getParameter("term");

        /* DAO | Define needed dao here */
        ProductDao productDao = DaoFactory.createProductDao();
        StockInventoryDao stockInventoryDao = DaoFactory.createStockInventoryDao();

        /* TRANSACTION | Something complex here */
        pw.print("[");
        List<Product> ps;
        if (request.getParameter("mode").equals("name")) {
            ps = productDao.findWhereProductNameEquals(term, 20);
        } else {
            ps = productDao.findWhereProductCodeEquals(term, 20);
        }
        for (Product x : ps) {
            if (b) {
                pw.print(",");
            }

            StockInventory si = stockInventoryDao.findWhereProductCodeEquals(x.getProductCode()).get(0);

            pw.print("{\"itemCode\": \"" + x.getProductCode() + "\", ");
            pw.print("\"itemName\": \"" + x.getProductName() + "\",");
            pw.print("\"type\": \"" + x.getProductCategory() + "\",");
            pw.print("\"soh\": \"" + si.getBalance() + "\",");
            pw.print("\"uom\": \"" + x.getUom() + "\"}");

            b = Boolean.TRUE;

        }
        pw.print("]");

    }

    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

        /* DAO | Define needed dao here */
        SwsDao swsDao = DaoFactory.createSwsDao();

        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(swsDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = swsDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"), lu.getDepartmentCode());
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("sws_code")).append("\", ");
            sb.append("\"2\": \"").append(x.get("sws_code")).append("\", ");
            sb.append("\"3\": \"").append(x.get("sws_date")).append("\", ");
            sb.append("\"4\": \"").append(x.get("department_code")).append("\", ");
            sb.append("\"5\": \"").append(x.get("department_name")).append("\", ");
            sb.append("\"6\": \"").append(x.get("sws_info")).append("\", ");
            sb.append("\"7\": \"").append(x.get("created_by")).append("\"}");

            b = Boolean.TRUE;
        }
        sb.append("]}");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
    }

    public void generateNumber(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            SwsDao swsDao = DaoFactory.createSwsDao();
            String swsCode = swsDao.generateNumber(lu.getUserId(), request.getParameter("department"));

            response.getWriter().print("{\"number\": \"" + swsCode + "\"}");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // 2015 Update | by FYA
    public ModelAndView ajaxNSave(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map<String, Object> json = new HashMap<String, Object>();

        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            String data = URLDecoder.decode(request.getParameter("data"), "utf-8");
            String[] separator = StringHelper.getDataSeparator(data, 2);

            data = data.replaceAll(":s:", separator[0]).replaceAll(":se:", separator[1]);
            DaoFactory.createSwsDao().ajaxNSave(data, separator[0], separator[1], lu.getUserId());

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
            DaoFactory.createSwsDao().delete(request.getParameter("key"), lu.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:Sws.htm");
    }

    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();

        try {
            String key = request.getParameter("key");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            
            model.put("sws", DaoFactory.createSwsDao().getStoresWithdrawal(key));
            model.put("department", DaoFactory.createDepartmentDao().findWhereDepartmentCodeEquals(lu.getDepartmentCode()).get(0));
            
            return new ModelAndView("default/non_fish/StoresWithdrawalUpdate", "model", model);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:Sws.htm");
        }
    }

    public ModelAndView ajaxNUpdate(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map<String, Object> json = new HashMap<String, Object>();

        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            String data = URLDecoder.decode(request.getParameter("data"), "utf-8");
            String[] separator = StringHelper.getDataSeparator(data, 2);

            data = data.replaceAll(":s:", separator[0]).replaceAll(":se:", separator[1]);
            DaoFactory.createSwsDao().ajaxNUpdate(data, separator[0], separator[1], lu.getUserId());

            json.put("message", "");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
        }

        return new ModelAndView("jsonView", json);
    }

}
