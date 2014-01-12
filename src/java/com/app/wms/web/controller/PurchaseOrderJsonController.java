package com.app.wms.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.wms.engine.db.dao.BundleDao;
import com.app.wms.engine.db.dao.PoDao;
import com.app.wms.engine.db.dto.Bundle;
import com.app.wms.engine.db.dto.Po;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.BundleDaoException;
import com.app.wms.engine.db.exceptions.PoDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

@Controller
@RequestMapping( "/purchaseorderjson.htm")
public class PurchaseOrderJsonController extends BaseJsonController {
	
	@RequestMapping(method = RequestMethod.GET)
    public ModelAndView po(HttpServletRequest request, HttpServletResponse response) throws BundleDaoException, PoDaoException {        

		LoginUser lu  = (LoginUser) request.getSession().getAttribute("user");
		
		Po p = new Po();
		process(request);
		
		PoDao dao = DaoFactory.createPoDao();
		List<Po> pos = dao.findWherePoNumberNotInGR();

		List result = new ArrayList();
		for(Po valueSearch : pos){
			Map returnMap = new HashMap();
			String ponumber = valueSearch.getPonumber();
			Date deliverydate = valueSearch.getDeliverydate();
			String supplier = valueSearch.getSupplierName();
			String createdby = valueSearch.getCreatedby();
			returnMap.put("ponumber", ponumber);
			returnMap.put("deliverydate", deliverydate);
			returnMap.put("supplier", supplier);
			returnMap.put("createdby", createdby);
			result.add(returnMap);
		}
		
		if(sord.equalsIgnoreCase("asc"))
            Collections.reverse(pos);
        
        Map model = new HashMap();
        model.put(PAGE, page);
        model.put(TOTAL, getTotalPages(pos.size()));
        model.put(RECORDS, pos.size());                       
        model.put(ROWS, pagination(result));
		
        return new ModelAndView("jsonView", model);
    }

}
