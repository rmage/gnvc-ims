package com.app.wms.web.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.app.wms.engine.db.dao.PrsDao;
import com.app.wms.engine.db.dao.PrsDetailDao;
import com.app.wms.engine.db.dto.Prs;
import com.app.wms.engine.db.dto.PrsDetail;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.PrsDaoException;
import com.app.wms.engine.db.exceptions.PrsDetailDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

@Controller
@RequestMapping( {"/prsjson.htm","/prsdetailjson.htm"})
public class PRSJsonController extends BaseJsonController {
	
	@RequestMapping(value="/prsjson.htm", method = RequestMethod.GET)
    public ModelAndView prs(HttpServletRequest request, HttpServletResponse response) throws PrsDaoException {        
		
		LoginUser lu  = (LoginUser) request.getSession().getAttribute("user");
		String prsnumber = request.getParameter("prsnumber");
		String prsdt = request.getParameter("prsdate");
		String poreferensi = request.getParameter("poreferensi");
		String departmentname = request.getParameter("departmentname");
		String createdby = request.getParameter("createdby");
		String remarks = request.getParameter("remarks");
		Date prsdate = new Date();
		
		List<Prs> prs = new ArrayList<Prs>();
		Prs p = new Prs();
		process(request);
		PrsDao dao = DaoFactory.createPrsDao();
		
		if(prsnumber != null ){
			
			prs = dao.findWherePrsnumberEquals(prsnumber);
			
		}else if(prsdt != null && prsdt != ""){
			try {
				prsdate = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("prsdate") + "");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if(poreferensi != null){
			
		}else if(departmentname != null){
			
		}else if(createdby != null){
			
		}else if(remarks != null){
			
		}else if(prsnumber == null && prsdt == null && poreferensi == null && departmentname == null
				 && createdby == null && remarks == null){
			prs = dao.findWherePrsNumberNotInPO();
		}
		
		List result = new ArrayList();
		for(Prs valueSearch : prs){
			Map returnMap = new HashMap();
			prsnumber = valueSearch.getPrsnumber();
			prsdate = valueSearch.getPrsdate();
			Date requestdate = valueSearch.getRequestdate();
			poreferensi = valueSearch.getPoreferensi();
			departmentname = valueSearch.getDepartmentName();
			String suppliername = valueSearch.getCanvassingDetail().getSupplierCode();
			createdby = valueSearch.getCreatedby();
			remarks = valueSearch.getRemarks();
			returnMap.put("prsnumber", prsnumber);
			returnMap.put("prsdate", prsdate);
			returnMap.put("requestdate", requestdate);
			returnMap.put("poreferensi", poreferensi);
			returnMap.put("departmentname", departmentname);
			returnMap.put("suppliername", suppliername);
			returnMap.put("createdby", createdby);
			returnMap.put("remarks", remarks);
			result.add(returnMap);
		}
		
		if(sord.equalsIgnoreCase("asc"))
            Collections.reverse(prs);
        
        Map model = new HashMap();
        model.put(PAGE, page);
        model.put(TOTAL, getTotalPages(prs.size()));
        model.put(RECORDS, prs.size());                       
        model.put(ROWS, pagination(result));
		
        return new ModelAndView("jsonView", model);
    }
	
	@RequestMapping(value="/prsdetailjson.htm", method = RequestMethod.GET)
	public ModelAndView prsdetail(HttpServletRequest request, HttpServletResponse response) throws PrsDetailDaoException { 
		
		LoginUser lu  = (LoginUser) request.getSession().getAttribute("user");
		String param = request.getParameter("param");
		String supplierz = request.getParameter("supplier");
		
		PrsDetailDao dao = DaoFactory.createPrsDetailDao();
		List<PrsDetail> prsDetails = dao.findWherePrsnumberCanvassingSelected(param, supplierz);
		
		List result = new ArrayList();
		for(PrsDetail valueSearch : prsDetails){
			Map returnMap = new HashMap();
			Integer id = valueSearch.getId();
			String prsnumber = valueSearch.getPrsnumber();
			String productcode = valueSearch.getProductcode();
			String productname = valueSearch.getProductname();
			BigDecimal qty = valueSearch.getQty();
			String uom = valueSearch.getUomName();
			Date warranty = valueSearch.getCanvassingDetail().getWarranty();
			String termpayment = valueSearch.getCanvassingDetail().getTermpayment();
			String termdelivery = valueSearch.getCanvassingDetail().getTermdelivery();
			BigDecimal unitprice = valueSearch.getCanvassingDetail().getPriceunit();
			BigDecimal discount = valueSearch.getCanvassingDetail().getDiscount();
			BigDecimal pph = valueSearch.getCanvassingDetail().getPph();
			BigDecimal ppn = valueSearch.getCanvassingDetail().getPpn();
			BigDecimal amount = unitprice.multiply(qty);
			String supplier = valueSearch.getCanvassingDetail().getSupplierCode();
			returnMap.put("id", id);
			returnMap.put("productcode", productcode);
	    	returnMap.put("productname", productname);
	    	returnMap.put("qty", qty);
	    	returnMap.put("uom", uom);
	    	returnMap.put("warranty", warranty);
	    	returnMap.put("termpayment", termpayment);
	    	returnMap.put("termdelivery", termdelivery);
	    	returnMap.put("unitprice", unitprice);
	    	returnMap.put("discount", discount);
	    	returnMap.put("amount", amount.setScale(2, RoundingMode.HALF_UP));
	    	returnMap.put("pph", pph);
	    	returnMap.put("ppn", ppn);
	    	returnMap.put("supplier", supplier);
	    	result.add(returnMap);
		}
		
		Map model = new HashMap();
		model.put("prsDetails", result);
		
		return new ModelAndView("jsonView", model);
	}

}
