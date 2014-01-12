package com.app.wms.web.controller;

import java.math.BigDecimal;
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

import com.app.wms.engine.db.dao.PoDao;
import com.app.wms.engine.db.dao.SwsDao;
import com.app.wms.engine.db.dao.SwsDetailDao;
import com.app.wms.engine.db.dto.Po;
import com.app.wms.engine.db.dto.Sws;
import com.app.wms.engine.db.dto.SwsDetail;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.BundleDaoException;
import com.app.wms.engine.db.exceptions.PoDaoException;
import com.app.wms.engine.db.exceptions.SwsDaoException;
import com.app.wms.engine.db.exceptions.SwsDetailDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.hbm.bean.Vgrdetailproduct;

@Controller
@RequestMapping( {"/withdrawalslipjson.htm","/withdrawalslipdetailjson.htm"})
public class WithdrawalSlipJsonController extends BaseJsonController {

	
	@RequestMapping(value = "/withdrawalslipjson.htm", method = RequestMethod.GET)
    public ModelAndView sws(HttpServletRequest request, HttpServletResponse response) throws SwsDaoException {        

		LoginUser lu  = (LoginUser) request.getSession().getAttribute("user");
		
		Sws s = new Sws();
		process(request);
		
		SwsDao dao = DaoFactory.createSwsDao();
		List<Sws> sws = dao.findWhereSwsNumberNotInTS();
		System.out.println("sws ="+sws.size());
		System.out.println("sws ="+sws);
		List result = new ArrayList();
		for(Sws valueSearch : sws){
			Map returnMap = new HashMap();
			String swsnumber = valueSearch.getSwsnumber();
			Date swsdate = valueSearch.getSwsdate();
			String createdby = valueSearch.getCreatedby();
			String department = valueSearch.getDepartmentName();
			returnMap.put("swsnumber", swsnumber);
			returnMap.put("swsdate", swsdate);
			returnMap.put("createdby", createdby);
			returnMap.put("department", department);
			result.add(returnMap);
		}
		
		if(sord.equalsIgnoreCase("asc"))
            Collections.reverse(sws);
        
        Map model = new HashMap();
        model.put(PAGE, page);
        model.put(TOTAL, getTotalPages(sws.size()));
        model.put(RECORDS, sws.size());                       
        model.put(ROWS, pagination(result));
		
        return new ModelAndView("jsonView", model);
    }
	
	@RequestMapping(value = "/withdrawalslipdetailjson.htm", method = RequestMethod.GET)
    public ModelAndView swsDetail(HttpServletRequest request, HttpServletResponse response) throws SwsDetailDaoException {      
       String param = request.getParameter("param");
       
       List<SwsDetail> wsDetails = new ArrayList<SwsDetail>();
       
       LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
       
       SwsDetailDao dao = DaoFactory.createSwsDetailDao();
      
       wsDetails = dao.findWhereSwsnumberDetail(param);
      
       List result = new ArrayList();
       for(SwsDetail valueSearch : wsDetails){
    	   Map returnMap = new HashMap();
    	   Integer id = valueSearch.getId();
    	   String swsnumber = valueSearch.getSwsnumber();
    	   String productcode = valueSearch.getProductcode();
    	   String productName = valueSearch.getProduct().getProductName();
    	   String productcategory = valueSearch.getProduct().getProductCategory();
    	   String uom = valueSearch.getProduct().getUom();
    	   BigDecimal qty = valueSearch.getQty();
    	   String producttype = valueSearch.getProducttype();
    	   String warehouse = valueSearch.getStockInventory().getWhCode();
    	   BigDecimal balance = valueSearch.getStockInventory().getBalance();
    	   returnMap.put("id", id);
    	   returnMap.put("swsnumber", swsnumber);
    	   returnMap.put("productcode", productcode);
    	   returnMap.put("productName", productName);
    	   returnMap.put("productcategory", productcategory);
    	   returnMap.put("uom", uom);
    	   returnMap.put("qty", qty);
    	   returnMap.put("producttype", producttype);
    	   returnMap.put("warehouse", warehouse);
    	   returnMap.put("balance", balance);
    	   result.add(returnMap);
       }

       Map model = new HashMap();                      
       model.put("swsDetails", result);

       return new ModelAndView("jsonView", model);
   }

}
