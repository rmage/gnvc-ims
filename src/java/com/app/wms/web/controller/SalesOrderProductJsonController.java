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

import com.app.wms.engine.db.dao.PickingDetailDao;
import com.app.wms.engine.db.dao.PutawayDetailDao;
import com.app.wms.engine.db.dao.SalesOrderDetailDao;
import com.app.wms.engine.db.dto.PickingDetail;
import com.app.wms.engine.db.dto.PutawayDetail;
import com.app.wms.engine.db.dto.SalesOrderDetail;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.PutawayDetailDaoException;
import com.app.wms.engine.db.exceptions.SalesOrderDetailDaoException;
import com.app.wms.engine.db.factory.DaoFactory;


@Controller
@RequestMapping( "/soproductjson.htm")
public class SalesOrderProductJsonController extends BaseJsonController {

	
	@RequestMapping(method = RequestMethod.GET)
    public ModelAndView soproduct(HttpServletRequest request, HttpServletResponse response) throws SalesOrderDetailDaoException, PutawayDetailDaoException {        

		LoginUser lu  = (LoginUser) request.getSession().getAttribute("user");
		String corpId = lu.getCorpId();
		String whCode = lu.getWhCode();
		PutawayDetail put = new PutawayDetail();
		put.setCorpId(corpId);
		put.setWhCode(whCode);
		
		process(request);
		
		PutawayDetailDao dao = DaoFactory.createPutawayDetailDao();
		List<PutawayDetail> pd = dao.findWhereProductLocation(put);

		List result = new ArrayList();
		for(PutawayDetail valueSearch : pd){
			
			Map returnMap = new HashMap();
			String productcode = valueSearch.getProductCode();
			String productname = valueSearch.getProduct().getProductName();
			Integer balance    = valueSearch.getBalance();
			String sku	   = valueSearch.getUnitCode();
			String locationCode= valueSearch.getLocationCode();
			returnMap.put("productcode", productcode);
			returnMap.put("productname", productname);
			returnMap.put("balance", balance);
			returnMap.put("sku", sku);
			returnMap.put("locationCode", locationCode);
			
			result.add(returnMap);
		}
        
        if(sord.equalsIgnoreCase("asc"))
            Collections.reverse(pd);
        
        Map model = new HashMap();
        model.put(PAGE, page);
        model.put(TOTAL, getTotalPages(pd.size()));
        model.put(RECORDS, pd.size());                       
        model.put(ROWS, pagination(result));
		
        return new ModelAndView("jsonView", model);
    }



}
