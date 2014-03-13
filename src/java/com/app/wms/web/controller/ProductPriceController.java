package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.ProductCategoryDao;
import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dao.ProductPriceDao;
import com.app.wms.engine.db.dao.StockInventoryDao;
import com.app.wms.engine.db.dao.UserDao;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.ProductCategory;
import com.app.wms.engine.db.dto.ProductPrice;
import com.app.wms.engine.db.dto.StockInventory;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.ProductCategoryDaoException;
import com.app.wms.engine.db.exceptions.ProductDaoException;
import com.app.wms.engine.db.exceptions.StockInventoryDaoException;
import com.app.wms.engine.db.exceptions.UserDaoException;
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

public class ProductPriceController extends MultiActionController {
    
//    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) 
        throws ProductCategoryDaoException {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();
        
        /* DAO | Define needed dao here */
        ProductCategoryDao productCategoryDao = DaoFactory.createProductCategoryDao();
        
        /* TRANSACTION | Something complex here */
        List<ProductCategory> pcs = productCategoryDao.findAll();
        m.put("pc", pcs);
        
        return new ModelAndView("accounting/ACPList", "model", m);
        
    }
    
    public void update (HttpServletRequest request, HttpServletResponse response)
        throws IOException, UserDaoException, StockInventoryDaoException {
        
        /* DATA | get initial value */
        PrintWriter pw = response.getWriter();
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

        /* DAO | Define needed dao here */
        ProductPriceDao productPriceDao = DaoFactory.createProductPriceDao();

        /* TRANSACTION | Something complex here */
        ProductPrice pp = new ProductPrice();
        pp.setProductCode(request.getParameter("itemCode"));
        pp.setCurrency(request.getParameter("currency"));
        pp.setUnitPrice(new BigDecimal(request.getParameter("price")));
        pp.setUpdatedBy(lu.getUserId());
        pp.setUpdatedDate(new Date());
        pp = productPriceDao.update(pp, 0, BigDecimal.ZERO);
        
        pw.print("{\"s\": true, \"rr\": " + pp.getLastRr() + ", \"currency\": \"" + pp.getCurrency() + 
            "\", \"old\": " + pp.getUnitPrice() + "}");
    }
    
    public void getProductList(HttpServletRequest request, HttpServletResponse response) 
        throws ProductDaoException, IOException {
        
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        String categoryCode = request.getParameter("key");

        /* DAO | Define needed dao here */
        ProductDao productDao = DaoFactory.createProductDao();
        ProductPriceDao productPriceDao = DaoFactory.createProductPriceDao();

        /* TRANSACTION | Something complex here */
        List<Product> ps = productDao.findWhereProductCategoryEquals(categoryCode);
        pw.print("[");
        for(Product x : ps) {
            if(b)
                pw.print(",");
            
            ProductPrice pp = productPriceDao.findByProduct(x.getProductCode());
            
            pw.print("{\"code\": \"" + x.getProductCode() + "\", ");
            pw.print("\"name\": \"" + x.getProductName() + "\",");
            pw.print("\"type\": \"" + x.getProductCategory() + "\",");
            pw.print("\"rr\": \"" + pp.getLastRr() + "\",");
            pw.print("\"currency\": \"" + pp.getCurrency() + "\",");
            pw.print("\"old\": \"" + pp.getUnitPrice() + "\"}");
            
            b = Boolean.TRUE;
        } pw.print("]");
        
    }
    
}
