package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.DepartmentDao;
import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dao.StockBalanceDao;
import com.app.wms.engine.db.dao.StockInventoryDao;
import com.app.wms.engine.db.dao.SwsDtlDao;
import com.app.wms.engine.db.dao.TsDao;
import com.app.wms.engine.db.dao.TsDtlDao;
import com.app.wms.engine.db.dao.UserDao;
import com.app.wms.engine.db.dto.Department;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.StockInventory;
import com.app.wms.engine.db.dto.Sws;
import com.app.wms.engine.db.dto.SwsDtl;
import com.app.wms.engine.db.dto.Ts;
import com.app.wms.engine.db.dto.TsDtl;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.DepartmentDaoException;
import com.app.wms.engine.db.exceptions.ProductDaoException;
import com.app.wms.engine.db.exceptions.StockInventoryDaoException;
import com.app.wms.engine.db.exceptions.UserDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class TransferSlipController extends MultiActionController {

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("non_fish/TSList");
    }

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {

        /* DATA | get initial value */
        HashMap m = new HashMap();

        /* DAO | Define needed dao here */
        TsDao tsDao = DaoFactory.createTsDao();

        /* TRANSACTION | Something complex here */
        String module = request.getParameter("module");
        String type = request.getParameter("type");
        if (module.equals("NF")) {
            if (type.equals("NORMAL")) {
                List<Sws> ss = tsDao.findWhereNotInTs();
                m.put("s", ss);
                return new ModelAndView("non_fish/TSAdd", "model", m);
            } else if (type.equals("OTHERS")) {
                return new ModelAndView("non_fish/TSAddO", "model", m);
            } else {
                return null;
            }
        } else {
            if (type.equals("BAD_STOCKS")) {
                return new ModelAndView("finish_goods/TSAddBS", "model", m);
            } else {
                return null;
            }
        }
        
    }

    public ModelAndView save(HttpServletRequest request, HttpServletResponse response)
            throws ParseException, StockInventoryDaoException, UserDaoException {

        /* DATA | get initial value */
        Ts t = new Ts();
        String[] qtys = request.getParameterValues("qty");
        String[] items = request.getParameterValues("item");
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

        /* DAO | Define needed dao here */
        TsDao tsDao = DaoFactory.createTsDao();
        TsDtlDao tsDtlDao = DaoFactory.createTsDtlDao();
        StockBalanceDao stockBalanceDao = DaoFactory.createStockBalanceDao();
        StockInventoryDao stockInventoryDao = DaoFactory.createStockInventoryDao();
        UserDao uDao = DaoFactory.createUserDao();

        /* TRANSACTION | Something complex here */
        // insert transfer slip
        t.setTsCode(Integer.parseInt(request.getParameter("tsCode")));
        t.setTsDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(request.getParameter("tsDate") + " "
                + new SimpleDateFormat("HH:mm:ss").format(new Date())));
        t.setTsInfo(request.getParameter("tsInfo"));
        t.setTsTo(request.getParameter("tsTo"));
        t.setTsModule(request.getParameter("module"));
        t.setTsType(request.getParameter("type"));
        t.setSwsCode(t.getTsType().equals("NORMAL") ? Integer.parseInt(request.getParameter("swsCode")) : 0);
        t.setCreatedBy(uDao.findByPrimaryKey(lu.getUserId()).getName());
        t.setCreatedDate(new Date());
        tsDao.insert(t);

        int i = 0;
        TsDtl td = new TsDtl();
        td.setTsCode(t.getTsCode());
        td.setCreatedBy(t.getCreatedBy());
        td.setCreatedDate(t.getCreatedDate());
        for (String x : items) {
            td.setProductCode(x);
            td.setQty(Integer.parseInt(qtys[i]));
            tsDtlDao.insert(td);

            // stock balance history for stock card
            StockInventory si = stockInventoryDao.findWhereProductCodeEquals(td.getProductCode()).get(0);
            if (t.getTsType().equals("NORMAL")) {
                stockBalanceDao.insertOrUpdate(td.getProductCode(), new Date(), si.getBalance(), new BigDecimal(td.getQty()), 20);
            } else if (t.getTsType().equals("OTHERS")) {
                stockBalanceDao.insertOrUpdate(td.getProductCode(), new Date(), si.getBalance(), new BigDecimal(td.getQty()), 21);
            }

            // substract stock inventory balance
            tsDao.updateStockInventory(td.getProductCode(), td.getQty());
            i++;
        }

        return new ModelAndView("redirect:TransferSlip.htm?module=" + t.getTsModule());

    }

    public void getSwsDetail(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ProductDaoException {

        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        int swsCode = Integer.parseInt(request.getParameter("key"));

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
        String productName = request.getParameter("term");

        /* DAO | Define needed dao here */
        ProductDao productDao = DaoFactory.createProductDao();
        StockInventoryDao stockInventoryDao = DaoFactory.createStockInventoryDao();

        /* TRANSACTION | Something complex here */
        pw.print("[");
        List<Product> ps = productDao.findWhereProductNameEquals(productName, request.getParameter("module"), 5);
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

    public void getDepartment(HttpServletRequest request, HttpServletResponse response)
            throws IOException, DepartmentDaoException {

        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        String DepartmentName = request.getParameter("term");

        /* DAO | Define needed dao here */
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        /* TRANSACTION | Something complex here */
        pw.print("[");
        List<Department> ds = departmentDao.findWhereDepartmentNameEquals(DepartmentName);
        for (Department x : ds) {
            if (b) {
                pw.print(",");
            }

            pw.print("{\"department\": \"" + x.getDepartmentName() + "\"}");

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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        /* DAO | Define needed dao here */
        TsDao tsDao = DaoFactory.createTsDao();
        
        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(tsDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = tsDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("ts_code")).append("\", ");
            sb.append("\"2\": \"").append(x.get("ts_code")).append("\", ");
            sb.append("\"3\": \"").append(sdf.format(x.get("ts_date"))).append("\", ");
            sb.append("\"4\": \"").append(x.get("ts_type")).append("\", ");
            sb.append("\"5\": \"").append(x.get("sws_code")).append("\", ");
            sb.append("\"6\": \"").append(x.get("ts_info")).append("\", ");
            sb.append("\"7\": \"").append(x.get("created_by")).append("\"}");
            
            b = Boolean.TRUE;
        }
        sb.append("]}");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
    }

}
