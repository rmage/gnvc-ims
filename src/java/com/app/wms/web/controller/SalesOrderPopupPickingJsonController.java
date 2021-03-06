package com.app.wms.web.controller;

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

import com.app.wms.engine.db.dao.SalesOrderDao;
import com.app.wms.engine.db.dto.SalesOrder;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.SalesOrderDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

@Controller
@RequestMapping( "/sojson1.htm")
public class SalesOrderPopupPickingJsonController extends BaseJsonController {
	
	@RequestMapping(method = RequestMethod.GET)
    public ModelAndView json(HttpServletRequest request, HttpServletResponse response) throws SalesOrderDaoException {      

    	LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
    	SalesOrder salesOrder = new SalesOrder();
    	salesOrder.setCorpId(lu.getCorpId());
    	salesOrder.setWhCode(lu.getWhCode());
    	
    	process(request);
    	
    	SalesOrderDao dao = DaoFactory.createSalesOrderDao();
        List<SalesOrder> sod = dao.findSONumberOnDeliveryOrder(salesOrder);
     
        if(sord.equalsIgnoreCase("asc"))
            Collections.reverse(sod);
        
        Map model = new HashMap();
        model.put(PAGE, page);
        model.put(TOTAL, getTotalPages(sod.size()));
        model.put(RECORDS, sod.size());                       
        model.put(ROWS, pagination(sod));

       return new ModelAndView("jsonView", model);
   }

}
