package com.app.wms.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.wms.engine.db.dao.BundleDetailDao;
import com.app.wms.engine.db.dao.PickingDao;
import com.app.wms.engine.db.dao.SalesOrderDetailDao;
import com.app.wms.engine.db.dto.BundleDetail;
import com.app.wms.engine.db.dto.Picking;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.PutawayDetail;
import com.app.wms.engine.db.dto.SalesOrderDetail;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.BundleDetailDaoException;
import com.app.wms.engine.db.exceptions.PickingDaoException;
import com.app.wms.engine.db.exceptions.PutawayDetailDaoException;
import com.app.wms.engine.db.exceptions.SalesOrderDetailDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

@Controller
@RequestMapping( "/sopickingjson.htm")
public class SalesOrderPickingJsonController extends BaseJsonController {
	
	@RequestMapping(method = RequestMethod.GET)
    public ModelAndView so(HttpServletRequest request, HttpServletResponse response) throws SalesOrderDetailDaoException, PutawayDetailDaoException, BundleDetailDaoException {        

        String salesOrderNo = request.getParameter("param");
       
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        SalesOrderDetail sod = new SalesOrderDetail();
        sod.setSo_number(salesOrderNo);
        sod.setCorpId(lu.getCorpId());
        sod.setWhCode(lu.getWhCode());
        
        SalesOrderDetailDao dao = DaoFactory.createSalesOrderDetailDao();
        List<SalesOrderDetail> salesod = dao.findDetail(sod);

        List result = new ArrayList();
        Map returnMap = new HashMap();
        String productCode = "";
        Integer balance = 0;
        
        String bundleCode = "";
        Integer qtySO = 0;
        
        for(SalesOrderDetail valueSearch : salesod){
        	returnMap = new HashMap();
        	productCode = valueSearch.getProduct().getProductCode();
        	String productId = valueSearch.getProduct().getProductId();
        	String productName = valueSearch.getProduct().getProductName();
        	Integer qty = valueSearch.getQuantitySO();
        	String unitCode = valueSearch.getSku();
        	bundleCode = valueSearch.getBundle().getBundleCode();
        	
        	if(bundleCode!=null){
        		qtySO = valueSearch.getQuantitySO();
        	}
        	
        	if(productId != null && !productId.equalsIgnoreCase("") && productName != null && !productName.equalsIgnoreCase("")
        	   && productCode != null && !productCode.equalsIgnoreCase("")){
        		
	        	returnMap.put("productId",productId);
	        	returnMap.put("productCode",productCode);
	        	returnMap.put("productName",productName);
	        	returnMap.put("qty",qty);
	        	returnMap.put("unitCode", unitCode);
	        	
        	}
        	
        	
        	PutawayDetail pud = new PutawayDetail();
            pud.setProductCode(productCode);
           
            List<PutawayDetail> pd = dao.findWhereProductCodeBalanceEquals(pud);
            for(PutawayDetail valueSearchPD : pd){
            	String locationCode = valueSearchPD.getLocationCode();
            	String productCode1 = valueSearchPD.getProductCode();
            	String productName1 = valueSearchPD.getProduct().getProductName();
            	balance 			= valueSearchPD.getBalance();
            	Integer id 			= valueSearchPD.getId();
            }
            returnMap.put("balance", balance);
            result.add(returnMap);
        }
        
       //bundling product
        System.out.println("bundleCode ="+bundleCode);
        
        BundleDetailDao bdDao = DaoFactory.createBundleDetailDao();
        BundleDetail bud = new BundleDetail();
        bud.setBundleCode(bundleCode);
        List<BundleDetail> bd = bdDao.findWhereBundleCode(bud);
        
        String productIdBundle = "";
        String productCodeBundle = "";
        String productNameBundle = "";
        String unitCodeBundle = "";
        Integer qtyBundle = 0;
                
        for(BundleDetail valueSearch : bd){
        	Map bundleMap = new HashMap();
        	productCodeBundle = valueSearch.getProductCode();
        	productIdBundle = valueSearch.getProductId();
        	productNameBundle = valueSearch.getProductName();
        	qtyBundle = valueSearch.getQtyBundle();
        	unitCodeBundle = valueSearch.getUnitCode();
        	
        	bundleMap.put("productId",productIdBundle);
        	bundleMap.put("productCode",productCodeBundle);
        	bundleMap.put("productName",productNameBundle.concat(" (").concat(bundleCode).concat(") "));
        	bundleMap.put("qty",qtyBundle*qtySO);
        	bundleMap.put("unitCode", unitCodeBundle);
        	result.add(bundleMap);
        	
        }
     
           
        Map model = new HashMap();
        model.put("page", page);
        model.put("total ", getTotalPages(salesod.size()));
        model.put("records ", salesod.size());                       
        model.put("rows", result);
		
        return new ModelAndView("jsonView", model);
    }

}
