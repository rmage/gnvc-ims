package com.app.wms.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.wms.engine.db.dao.PickingDao;
import com.app.wms.engine.db.dao.SalesOrderDao;
import com.app.wms.engine.db.dto.Picking;
import com.app.wms.engine.db.dto.SalesOrder;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.PickingDaoException;
import com.app.wms.engine.db.exceptions.SalesOrderDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

@Controller
@RequestMapping( "/sodetailjson.htm")
public class SalesOrderDetailJsonController extends BaseJsonController {
	
	 @RequestMapping(method = RequestMethod.GET)
	    public ModelAndView so(HttpServletRequest request, HttpServletResponse response) throws PickingDaoException {        

	        String salesOrder = request.getParameter("param");
	        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
	        
	        Picking pic = new Picking();
	        pic.setSoNumber(salesOrder);
	        pic.setCorpId(lu.getCorpId());
	        pic.setWhCode(lu.getWhCode());
	        
	        //process(request);
	        
	        PickingDao dao = DaoFactory.createPickingDao();
	        List<Picking> p = dao.findProductDelivery(pic);
	       
	        List result = new ArrayList();
	        for(Picking valueSearch : p){
	        	
	        	Map returnMap = new HashMap();
	        	String pickingId = valueSearch.getPickingDetail().getPickingId();
	        	String productId = valueSearch.getPickingDetail().getProducts().getProductId();
	        	String productCode = valueSearch.getPickingDetail().getProducts().getProductCode();
	        	String productName = valueSearch.getPickingDetail().getProducts().getProductName();
	        	Integer qty = valueSearch.getPickingDetail().getBalance();
	        	String unitCode = valueSearch.getPickingDetail().getUnitCode();
	        	returnMap.put("pickingId",pickingId);
	        	returnMap.put("productId",productId);
	        	returnMap.put("productCode",productCode);
	        	returnMap.put("productName",productName);
	        	returnMap.put("qty",qty);
	        	returnMap.put("unitCode", unitCode);
	        	
	        	result.add(returnMap);
	        }

	        if(sord.equalsIgnoreCase("asc"))
	            Collections.reverse(p);
	        
	        Map model = new HashMap();
	        model.put("page", page);
	        model.put("total ", getTotalPages(p.size()));
	        model.put("records ", p.size());                       
	        model.put("rows", pagination(result));
			
	        return new ModelAndView("jsonView", model);
	    }

}
