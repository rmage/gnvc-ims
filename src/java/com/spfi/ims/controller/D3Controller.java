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
            } break;
        }

        pw.print(sb.toString());
        pw.flush();
        pw.close();

    }

    public String utilRemoveLastChar(String s, int q) {
        int l = s.length();
        return s.substring(0, l - q);
    }

}
