package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.CurrencyRateDao;
import com.app.wms.engine.db.dto.CurrencyRate;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class CurrencyRateController  extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();
        
        /* DAO | Define needed dao here */
        CurrencyRateDao currencyRateDao = DaoFactory.createCurrencyRateDao();
        
        /* TRANSACTION | Something complex here */
        List<CurrencyRate> crs = currencyRateDao.findAll();
        m.put("crs", crs);
        
        return new ModelAndView("accounting/CurrencyRate", "model", m);
        
    }
    
    public void setRate(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        
        /* DATA | get initial value */
        PrintWriter pw = response.getWriter();
        String currencyCode = request.getParameter("cc");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        BigDecimal rateValue = new BigDecimal(request.getParameter("v"));
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        
        /* DAO | Define needed dao here */
        CurrencyRateDao currencyRateDao = DaoFactory.createCurrencyRateDao();
        
        /* TRANSACTION | Something complex here */
        CurrencyRate cr = new CurrencyRate();
        cr.setCurrencyCode(currencyCode);
        cr.setRateValue(rateValue);
        cr.setRateDate(new Date());
        cr.setCreatedBy(lu.getUsername());
        cr.setCreatedDate(new Date());
        if (currencyRateDao.insert(cr) > 0) {
            pw.print("{\"status\": 1, \"date\": \"" + sdf.format(cr.getRateDate()) + "\", \"by\": \"" + cr.getCreatedBy() + "\"}");
        } else {
            pw.print("{\"status\": 0}");
        }
        
    }
    
}
