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
import com.app.wms.engine.db.dao.BundleDao;
import com.app.wms.engine.db.dto.Bundle;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.BundleDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

@Controller
@RequestMapping( "/sobundlejson.htm")
public class SalesOrderBundleJsonController extends BaseJsonController {
	
	@RequestMapping(method = RequestMethod.GET)
    public ModelAndView sobundle(HttpServletRequest request, HttpServletResponse response) throws BundleDaoException {        

		LoginUser lu  = (LoginUser) request.getSession().getAttribute("user");
		String corpId = lu.getCorpId();
		String whCode = lu.getWhCode();
		Bundle b = new Bundle();
		b.setCorpId(corpId);
		b.setWhCode(whCode);
		
		process(request);
		
		BundleDao dao = DaoFactory.createBundleDao();
		List<Bundle> lb = dao.findBundling(b);

		List result = new ArrayList();
		for(Bundle valueSearch : lb){
			Map returnMap = new HashMap();
			String bundlecode = valueSearch.getBundleCode();
			String bundlename = valueSearch.getBundleName();
			returnMap.put("bundlecode", bundlecode);
			returnMap.put("bundlename", bundlename);
			result.add(returnMap);
		}
		
		if(sord.equalsIgnoreCase("asc"))
            Collections.reverse(lb);
        
        Map model = new HashMap();
        model.put(PAGE, page);
        model.put(TOTAL, getTotalPages(lb.size()));
        model.put(RECORDS, lb.size());                       
        model.put(ROWS, pagination(result));
		
        return new ModelAndView("jsonView", model);
    }

}
