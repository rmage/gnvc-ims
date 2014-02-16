package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dao.PtsDao;
import com.app.wms.engine.db.dao.PtsDtlDao;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.Pts;
import com.app.wms.engine.db.dto.PtsDtl;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.ProductDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class PTSController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) 
        throws ProductDaoException {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        
        /* DAO | Define needed dao here */
        PtsDao ptsDao = DaoFactory.createPtsDao();
        ProductDao productDao = DaoFactory.createProductDao();
        
        /* TRANSACTION | Something complex here */
        List<Pts> ps = ptsDao.findByUser(lu.getUserId());
        for(Pts x : ps) {
            Product p = productDao.findWhereProductCodeEquals(x.getProductCode()).get(0);
            x.setProductCode(p.getBrandName() + " (" + p.getProductCode() + " - " + p.getProductName() + "):"
                + p.getPackstyle() + " / " + p.getPacksize());
        } m.put("p", ps);
        
        return new ModelAndView("finish_goods/PTSList", "model", m);
        
    }
    
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();
        
        /* DAO | Define needed dao here */
        
        /* TRANSACTION | Something complex here */
        
        return new ModelAndView("finish_goods/PTSAdd", "model", m);
        
    }
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) 
        throws ParseException {
        
        /* DATA | get initial value */
        String[] master = request.getParameter("master").split(":", -1);
        String[] details = request.getParameterValues("detail");
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

        /* DAO | Define needed dao here */
        PtsDao ptsDao = DaoFactory.createPtsDao();
        PtsDtlDao ptsDtlDao = DaoFactory.createPtsDtlDao();

        /* TRANSACTION | Something complex here */
        // insert master pts
        Pts p = new Pts();
        p.setPtsCode(Integer.parseInt(master[0]));
        p.setPtsDate(new SimpleDateFormat("dd/MM/yyyy").parse(master[1]));
        p.setPtsCanCode(master[2]);
        p.setPtsCs(new BigDecimal(master[3]));
        p.setPtsLocation(master[4]);
        p.setProductCode(master[5]);
        p.setBorCode(master[6]);
        p.setCoeFlk(new BigDecimal(master[7]));
        p.setCoeNw(new BigDecimal(master[8]));
        p.setCoeDw(new BigDecimal(master[9]));
        p.setCoePw(new BigDecimal(master[10]));
        p.setQadReleaseTo(master[11]);
        p.setQadRemarks(master[12]);
        p.setCreatedBy(lu.getUserId());
        p.setCreatedDate(new Date());
        ptsDao.insert(p);
        
        for(String x : details) {
            // insert detail pts
            String[] detail = x.split(":");
            PtsDtl pd = new PtsDtl();
            pd.setPtsCode(p.getPtsCode());
            pd.setPtsShift(detail[0]);
            pd.setPtsDate(new SimpleDateFormat("dd/MM/yyyy").parse(detail[1]));
            pd.setPtsProdBatch(detail[2]);
            pd.setPtsBasket(detail[3]);
            pd.setPtsQty(new BigDecimal(detail[4]));
            pd.setCreatedBy(lu.getUserId());
            pd.setCreatedDate(new Date());
            ptsDtlDao.insert(pd);
            
            // update stock inventiry
            ptsDao.updateStockInventory(p.getProductCode(), pd.getPtsQty());
        }
        
        return new ModelAndView("redirect:Pts.htm");
        
    }
    
    public void ajaxDocument(HttpServletRequest request, HttpServletResponse response) 
        throws IOException, NumberFormatException{
        
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        int ptsCode = Integer.parseInt(request.getParameter("key"));

        /* DAO | Define needed dao here */
        PtsDtlDao ptsDtlDao = DaoFactory.createPtsDtlDao();

        /* TRANSACTION | Something complex here */
        pw.print("[");
        List<PtsDtl> pds = ptsDtlDao.findByPts(ptsCode);
        for(PtsDtl x : pds) {
            if(b)
                pw.print(",");
            
            pw.print("{\"shift\": \"" + x.getPtsShift() + "\", ");
            pw.print("\"date\": \"" + new SimpleDateFormat("dd/MM/yyyy").format(x.getPtsDate()) + "\",");
            pw.print("\"prodbatch\": \"" + x.getPtsProdBatch() + "\",");
            pw.print("\"basket\": \"" + x.getPtsBasket() + "\",");
            pw.print("\"qty\": \"" + x.getPtsQty() + "\"}");
            
            b = Boolean.TRUE;
        } pw.print("]");
        
    }
    
    public void getBrandName(HttpServletRequest request, HttpServletResponse response) 
        throws IOException {
        
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        String brandName = request.getParameter("term");

        /* DAO | Define needed dao here */
        ProductDao productDao = DaoFactory.createProductDao();

        /* TRANSACTION | Something complex here */
        pw.print("[");
        List<Product> ps = productDao.findWhereBrandNameEquals(brandName, 5);
        for(Product x : ps) {
            if(b)
                pw.print(",");
            
            pw.print("{\"itemCode\": \"" + x.getProductCode() + "\", ");
            pw.print("\"itemName\": \"" + x.getProductName() + "\",");
            pw.print("\"style\": \"" + x.getPackstyle() + "\",");
            pw.print("\"size\": \"" + x.getPacksize() + "\",");
            pw.print("\"brand\": \"" + x.getBrandName() + "\"}");
            
            b = Boolean.TRUE;
        } pw.print("]");
        
    }

}
