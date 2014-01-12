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

import com.app.wms.engine.db.dao.ConsigneeDao;
import com.app.wms.engine.db.dto.Consignee;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.ConsigneeDaoException;
import com.app.wms.engine.db.factory.DaoFactory;


@Controller
@RequestMapping( "/soconsigneejson.htm")
public class SalesOrderConsigneeJsonController extends BaseJsonController {
	
	@RequestMapping(method = RequestMethod.GET)
    public ModelAndView soconsignee(HttpServletRequest request, HttpServletResponse response) throws ConsigneeDaoException {        

		LoginUser lu  = (LoginUser) request.getSession().getAttribute("user");
		String corpId = lu.getCorpId();
		String whCode = lu.getWhCode();
		Consignee cons = new Consignee();
		cons.setCorpId(corpId);
		cons.setWhCode(whCode);
		
		process(request);
		
		ConsigneeDao dao = DaoFactory.createConsigneeDao();
		List<Consignee> co = dao.findWhereConsignee(cons);

		List result = new ArrayList();
		for(Consignee valueSearch : co){
			Map returnMap = new HashMap();
			String consigneecode = valueSearch.getConsigneeCode();
			String consigneename = valueSearch.getConsigneeName();
			String consigneeaddress = valueSearch.getAddress1();
			String consigneephone = valueSearch.getConsigneePhone();
			returnMap.put("consigneecode", consigneecode);
			returnMap.put("consigneename", consigneename);
			returnMap.put("consigneeaddress", consigneeaddress);
			returnMap.put("consigneephone", consigneephone);
			result.add(returnMap);
		}
        
		if(sord.equalsIgnoreCase("asc"))
            Collections.reverse(co);
		
        Map model = new HashMap();
        model.put(PAGE, page);
        model.put(TOTAL, getTotalPages(co.size()));
        model.put(RECORDS, co.size());                       
        model.put(ROWS, pagination(result));
		
        return new ModelAndView("jsonView", model);
    }


}
