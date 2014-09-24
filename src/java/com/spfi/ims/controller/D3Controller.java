package com.spfi.ims.controller;

import com.app.wms.engine.db.dao.CurrencyDao;
import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dto.Currency;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.exceptions.CurrencyDaoException;
import com.app.wms.engine.db.exceptions.ProductDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.D3Dao;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class D3Controller extends MultiActionController {

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/d3/Index");
    }

    public void NFUnitPriceTrend(HttpServletRequest request, HttpServletResponse response)
            throws IOException, CurrencyDaoException, ProductDaoException {

        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();

        int mode = Integer.parseInt(request.getParameter("mode"));

        /* DAO | Define needed dao here */
        D3Dao d3Dao = DaoFactory.createD3Dao();
        CurrencyDao cDao = DaoFactory.createCurrencyDao();
        ProductDao pDao = DaoFactory.createProductDao();

        /* TRANSACTION | Something complex here */
        switch (mode) {
            case 0: {
                String sCs = "";
                //List<Currency> cs = cDao.findAll();
                for (Currency x : cDao.findAll()) {
                    sCs = sCs + "{\"value\": \"" + x.getCurrencyCode() + "\", \"html\": \"" + x.getCurrencyName() + "\"},";
                }

                sb.append("{\"params\": ["
                        + "{\"title\": \"Item Code\", \"name\": \"itemCode\", \"type\": \"text\", \"size\": 6, \"is_autocomplete\": 1, \"autocomplete\": \"D3.htm?action=NFUnitPriceTrend&mode=10\"},"
                        + "{\"title\": \"Currency\", \"name\": \"currency\", \"type\": \"select\", \"options\": [").append(utilRemoveLastChar(sCs, 1)).append("]},"
                                + "{\"title\": \"Date From\", \"name\": \"dateFrom\", \"type\": \"text\", \"size\": 10, \"is_datepicker\": 1},"
                                + "{\"title\": \"Date To\", \"name\": \"dateTo\", \"type\": \"text\", \"size\": 10, \"is_datepicker\": 1}"
                                + "],"
                                + "\"data\": [{\"key\": \"itemCode\"}, {\"key\": \"currency\"}, {\"key\": \"dateFrom\"}, {\"key\": \"dateTo\"}],"
                                + "\"title\": \"itemCode\"}");
            }
            break;
            case 10: {
                sb.append("[");
                for (Product x : pDao.findWhereProductCodeEquals(request.getParameter("term"))) {
                    if (b) {
                        sb.append(",");
                    }
                    b = Boolean.TRUE;
                    sb.append("{\"itemCode\": \"").append(x.getProductCode()).append("\", ");
                    sb.append("\"itemName\": \"").append(x.getProductName()).append("\"}");
                }
                sb.append("]");
            }
            break;
            case 99: {
                String currencyCode = request.getParameter("currency");
                String[] dateFrom = request.getParameter("dateFrom").split("/", -1);
                String[] dateTo = request.getParameter("dateTo").split("/", -1);
                sb.append("{\"titley\": \"Unit Price (").append(currencyCode).append(")\", \"titlex\": \"Date\", \"data\": [");
                for (Map<String, Object> x : d3Dao.NFUnitPriceTrend(request.getParameter("itemCode"), currencyCode, (dateFrom[2] + "-" + dateFrom[1] + "-" + dateFrom[0]), (dateTo[2] + "-" + dateTo[1] + "-" + dateTo[0]))) {
                    if (b) {
                        sb.append(",");
                    }
                    b = Boolean.TRUE;
                    sb.append("{\"y\": \"").append(x.get("y")).append("\", ");
                    sb.append("\"x\": \"").append(x.get("x")).append("\"}");
                }
                sb.append("]}");
            }
            break;
        }

        pw.print(sb.toString());
        pw.flush();
        pw.close();

    }

    /* PRICE HISTORY
     * September 05, 2014
     */
    public ModelAndView priceHistory(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/d3/PriceHistory");
    }

    /*
     * AUTOCOMPLETE / AJAX FUNCTION
     */
    public void getProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        String term = request.getParameter("term");

        /* DAO | Define needed dao here */
        ProductDao productDao = DaoFactory.createProductDao();

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

            pw.print("{\"1\": \"" + x.getProductCode() + "\", ");
            pw.print("\"2\": \"" + x.getProductName() + "\"}");

            b = Boolean.TRUE;

        }
        pw.print("]");

    }

    public void getPriceList(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        
        /* DAO | Define needed dao here */
        D3Dao d3Dao = DaoFactory.createD3Dao();
        
        /* TRANSACTION | Something complex here */
        sb.append("[");
        List<Map<String, Object>> ms = d3Dao.NFPriceList(request.getParameter("itemCode"), request.getParameter("dateFrom"),
                request.getParameter("dateTo"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("d3_date")).append("\", ");
            sb.append("\"2\": \"").append(x.get("d3_currency")).append("\", ");
            sb.append("\"3\": \"").append(x.get("d3_price")).append("\"}");
            
            b = Boolean.TRUE;
        }
        sb.append("]");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
        
    }
    
    /*
     * REPORT PREVIEW
     */
    public void getFGStockInventoryPreview(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        
        /* DAO | Define needed dao here */
        D3Dao d3Dao = DaoFactory.createD3Dao();
        
        /* TRANSACTION | Something complex here */
        sb.append("[");
        List<Map<String, Object>> ms = d3Dao.getFGStockInventoryPreview(request.getParameter("param1"), request.getParameter("param2"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("col1")).append("\", ");
            sb.append("\"2\": \"").append(NumberFormat.getNumberInstance().format(new BigDecimal(x.get("col2").toString()))).append("\", ");
            sb.append("\"3\": \"").append(NumberFormat.getNumberInstance().format(new BigDecimal(x.get("col3").toString()))).append("\", ");
            sb.append("\"4\": \"").append(NumberFormat.getNumberInstance().format(new BigDecimal(x.get("col4").toString()))).append("\", ");
            sb.append("\"5\": \"").append(NumberFormat.getNumberInstance().format(new BigDecimal(x.get("col5").toString()))).append("\", ");
            sb.append("\"6\": \"").append(NumberFormat.getNumberInstance().format(new BigDecimal(x.get("col11").toString()))).append("\", ");
            sb.append("\"7\": \"").append(NumberFormat.getNumberInstance().format(new BigDecimal(x.get("col6").toString()))).append("\", ");
            sb.append("\"8\": \"").append(NumberFormat.getNumberInstance().format(new BigDecimal(x.get("col7").toString()))).append("\", ");
            sb.append("\"9\": \"").append(NumberFormat.getNumberInstance().format(new BigDecimal(x.get("col8").toString()))).append("\", ");
            sb.append("\"10\": \"").append(NumberFormat.getNumberInstance().format(new BigDecimal(x.get("col9").toString()))).append("\", ");
            sb.append("\"11\": \"").append(NumberFormat.getNumberInstance().format(new BigDecimal(x.get("col10").toString()))).append("\", ");
            sb.append("\"12\": \"").append(NumberFormat.getNumberInstance().format(new BigDecimal(x.get("col12").toString()))).append("\"}");
            
            b = Boolean.TRUE;
        }
        sb.append("]");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
        
    }
    
    public void getFGActualInventoryPreview(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        
        /* DAO | Define needed dao here */
        D3Dao d3Dao = DaoFactory.createD3Dao();
        
        /* TRANSACTION | Something complex here */
        sb.append("[");
        List<Map<String, Object>> ms = d3Dao.getFGActualInventoryPreview(request.getParameter("param1"), request.getParameter("param2"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("pts_code")).append("\", ");
            sb.append("\"2\": \"").append(x.get("item_name")).append("\", ");
            sb.append("\"3\": \"").append(x.get("item_oil")).append("\", ");
            sb.append("\"4\": \"").append(x.get("pack_style")).append("\", ");
            sb.append("\"5\": \"").append(x.get("pack_size")).append("\", ");
            sb.append("\"6\": \"").append(x.get("pts_cancode")).append("\", ");
            sb.append("\"7\": \"").append(NumberFormat.getNumberInstance().format(new BigDecimal(x.get("pts_quantity").toString()))).append("\", ");
            sb.append("\"8\": \"").append(NumberFormat.getNumberInstance().format(new BigDecimal(x.get("x_prodyear").toString()))).append("\", ");
            sb.append("\"9\": \"").append(NumberFormat.getNumberInstance().format(new BigDecimal(x.get("x_prodyear1").toString()))).append("\", ");
            sb.append("\"10\": \"").append(NumberFormat.getNumberInstance().format(new BigDecimal(x.get("x_prodyear2").toString()))).append("\", ");
            sb.append("\"11\": \"").append(NumberFormat.getNumberInstance().format(new BigDecimal(x.get("x_prodyear3").toString()))).append("\", ");
            sb.append("\"12\": \"").append(NumberFormat.getNumberInstance().format(new BigDecimal(x.get("x_prodyear4").toString()))).append("\", ");
            sb.append("\"13\": \"").append(NumberFormat.getNumberInstance().format(new BigDecimal(x.get("x_nocode").toString()))).append("\", ");
            sb.append("\"14\": \"").append(NumberFormat.getNumberInstance().format(new BigDecimal(x.get("x_mcyear").toString()))).append("\", ");
            sb.append("\"15\": \"").append(NumberFormat.getNumberInstance().format(new BigDecimal(x.get("x_mcyear1").toString()))).append("\", ");
            sb.append("\"16\": \"").append(NumberFormat.getNumberInstance().format(new BigDecimal(x.get("x_mcyear2").toString()))).append("\", ");
            sb.append("\"17\": \"").append(NumberFormat.getNumberInstance().format(new BigDecimal(x.get("x_mcyear3").toString()))).append("\", ");
            sb.append("\"18\": \"").append(NumberFormat.getNumberInstance().format(new BigDecimal(x.get("x_mcyear4").toString()))).append("\", ");
            sb.append("\"19\": \"").append(NumberFormat.getNumberInstance().format(new BigDecimal(x.get("x_labeled").toString()))).append("\", ");
            sb.append("\"20\": \"").append(NumberFormat.getNumberInstance().format(new BigDecimal(x.get("pts_coenw").toString()))).append("\", ");
            sb.append("\"21\": \"").append(NumberFormat.getNumberInstance().format(new BigDecimal(x.get("pts_coedw").toString()))).append("\", ");
            sb.append("\"22\": \"").append(NumberFormat.getNumberInstance().format(new BigDecimal(x.get("pts_coeflk").toString()))).append("\", ");
            sb.append("\"23\": \"").append(x.get("pts_brand")).append("\", ");
            sb.append("\"24\": \"").append(x.get("pts_lid")).append("\", ");
            sb.append("\"25\": \"").append(x.get("location_name")).append("\", ");
            sb.append("\"26\": \"").append(x.get("ctag_code")).append("\"}");
            
            b = Boolean.TRUE;
        }
        sb.append("]");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
        
    }

    /*
     * UTILITY FUNCTION
     */
    public String utilRemoveLastChar(String s, int q) {
        int l = s.length();
        return s.substring(0, l - q);
    }

}
