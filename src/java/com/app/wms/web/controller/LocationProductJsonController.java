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

import com.app.wms.engine.db.dao.PutawayDetailDao;
import com.app.wms.engine.db.dao.WarehouseLocationDao;
import com.app.wms.engine.db.dto.PutawayDetail;
import com.app.wms.engine.db.dto.WarehouseLocation;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.PutawayDetailDaoException;
import com.app.wms.engine.db.exceptions.SalesOrderDetailDaoException;
import com.app.wms.engine.db.exceptions.WarehouseLocationDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

@Controller
@RequestMapping( "/locationproductjson.htm")
public class LocationProductJsonController extends BaseJsonController {
	
	@RequestMapping(method = RequestMethod.GET)
    public ModelAndView locationproduct(HttpServletRequest request, HttpServletResponse response) throws WarehouseLocationDaoException {        

		LoginUser lu  = (LoginUser) request.getSession().getAttribute("user");
		String corpId = lu.getCorpId();
		String whCode = lu.getWhCode();
		WarehouseLocation wl = new WarehouseLocation();
		wl.setCorpId(corpId);
		wl.setWhCode(whCode);
		
		process(request);
		
		WarehouseLocationDao dao = DaoFactory.createWarehouseLocationDao();
		List<WarehouseLocation> wd = dao.findWhereProductLocation(wl);

		//Product Code','Product Name', 'Category','Brand Name', 'Description'
		List result = new ArrayList();
		for(WarehouseLocation valueSearch : wd){
			
			Map returnMap = new HashMap();
			String productcode = valueSearch.getWhLocationDetail().getProductCode();
			String productname = valueSearch.getWhLocationDetail().getProductName();
			String productcategory    = valueSearch.getWhLocationDetail().getProductCategory();
			String brandname   = valueSearch.getProduct().getBrandName();
			String productdescription = valueSearch.getProduct().getProductDescription();
			returnMap.put("productcode", productcode);
			returnMap.put("productname", productname);
			returnMap.put("productcategory", productcategory);
			returnMap.put("brandname", brandname);
			returnMap.put("productdescription", productdescription);
			
			result.add(returnMap);
		}
        
		if(sord.equalsIgnoreCase("asc"))
            Collections.reverse(wd);
		
        Map model = new HashMap();
        model.put(PAGE, page);
        model.put(TOTAL, getTotalPages(wd.size()));
        model.put(RECORDS, wd.size());                       
        model.put(ROWS, pagination(result));
		
        return new ModelAndView("jsonView", model);
    }


}
