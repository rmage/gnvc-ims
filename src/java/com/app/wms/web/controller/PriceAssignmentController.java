package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.AssignCanvassingDao;
import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dao.SupplierDao;
import com.app.wms.engine.db.dto.AssignCanvassing;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.Supplier;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.ProductDaoException;
import com.app.wms.engine.db.exceptions.SupplierDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import java.io.IOException;
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

public class PriceAssignmentController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {

        HashMap m = new HashMap();

        return new ModelAndView("non_fish/PAList", "model", m);

    }
     
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) 
        throws SupplierDaoException {
        
        HashMap m = new HashMap();
        
        /* get supplier list */
        SupplierDao supplierDao = DaoFactory.createSupplierDao();
        List<Supplier> ss = supplierDao.findWhereIsActiveEquals("Y");
        m.put("supplier", ss);
        
        return new ModelAndView("non_fish/PAAdd", "model", m);

    }
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) 
        throws ParseException {
        
        String[] prsNumbers = request.getParameterValues("prsNumber");
        String[] itemCodes = request.getParameterValues("itemCode");
        String[] supplierCodes = request.getParameterValues("supplierCode");
        String[] selecteds = request.getParameterValues("selected");
        String[] prices = request.getParameterValues("price");
        String[] tops = request.getParameterValues("top");
        String[] topDescs = request.getParameterValues("topDesc");
        String[] tods = request.getParameterValues("tod");
        String[] wps = request.getParameterValues("wp");
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        
        /* get prs assign to supplier */
        AssignCanvassingDao assignCanvassingDao = DaoFactory.createAssignCanvassingDao();
        for(int i = 0; i < prsNumbers.length; i++) {
            AssignCanvassing ac = assignCanvassingDao.findForPriceSaving(prsNumbers[i], itemCodes[i], supplierCodes[0]);
            ac.setIsSelected(selecteds[i].equals("on") ? "Y" : "N");
            ac.setUnitPrice(new BigDecimal(prices[i]));
            ac.setTop(tops[i]);
            ac.setTopDesc(topDescs[i]);
            ac.setTod(tods[i]);
            ac.setWp(wps[i].isEmpty() ? null : new SimpleDateFormat("dd/MM/yyyy").parse(wps[i]));
            ac.setUpdatedBy(lu.getUserId());
            ac.setUpdatedDate(new Date());
            assignCanvassingDao.update(ac);
        }
        
        return findByPrimaryKey(request, response);
        
    }
     
    public void getSupplier(HttpServletRequest request, HttpServletResponse response) 
        throws IOException, ProductDaoException, SupplierDaoException {
         
        String supplierCode = request.getParameter("key");

        /* get assigned supplier */
        ProductDao productDao = DaoFactory.createProductDao();
        SupplierDao supplierDao = DaoFactory.createSupplierDao();
        AssignCanvassingDao assignCanvassingDao = DaoFactory.createAssignCanvassingDao();
        List<AssignCanvassing> acs = assignCanvassingDao.findForPriceAssign(supplierCode);
        String out = "[";
        for(AssignCanvassing x : acs) {
            if(!out.equals("["))
                out += ",";
            
            Product p = productDao.findWhereProductCodeEquals(x.getProductCode()).get(0);
            Supplier s = supplierDao.findWhereSupplierCodeEquals(x.getSupplierCode()).get(0);
            
            out += "{\"prsNo\": \"" + x.getPrsNumber()+ "\", "
                + "\"assignDate\": \"" + new SimpleDateFormat("dd/MM/yyyy").format(x.getCreateDate()) + "\", "
                + "\"itemCode\": \"" + x.getProductCode()+ "\", "
                + "\"itemName\": \"" + p.getProductName()+ "\", "
                + "\"supplierCode\": \"" + x.getSupplierCode()+ "\", "
                + "\"supplierName\": \"" + s.getSupplierName()+ "\"}";
        }
        out += "]";
        response.getWriter().print(out);
        
     }
    
}
