package com.app.wms.web.controller;



import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.wms.engine.db.dao.PutawayDetailDao;
import com.app.wms.engine.db.dto.PutawayDetail;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.PutawayDetailDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

@Controller
@RequestMapping( {"/locationproductpicking.htm"})
public class LocationProductPickingController {
	
	@RequestMapping(method = RequestMethod.GET, value =  "/locationproductpicking.htm")
    public ModelAndView jsonrequest(HttpServletRequest request, HttpServletResponse response) throws PutawayDetailDaoException {
		
		LoginUser lu 		= (LoginUser) request.getSession().getAttribute("user");
		
        String productCode = request.getParameter("productId");
        PutawayDetail pud = new PutawayDetail();
        pud.setProductCode(productCode);
        pud.setCorpId((String)lu.getCorpId());
        pud.setWhCode((String)lu.getWhCode());
        
        PutawayDetailDao dao = DaoFactory.createPutawayDetailDao();
        //List<PutawayDetail> putawayDetail = new ArrayList<PutawayDetail>();
        List<PutawayDetail> pd = dao.findWhereProductCodeEquals(pud);
        //putawayDetail.addAll(dao.findWhereProductCodeEquals(pud));
       System.out.println("pd>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+pd);
        List result = new ArrayList();
        for(PutawayDetail valueSearch : pd){
        	
        	Map returnMap = new HashMap();
        	String locationCode = valueSearch.getLocationCode();
        	String locationName = valueSearch.getWhLocation().getLocationName();
        	Timestamp receivedDate = valueSearch.getReceivedDate();
        	Timestamp expiredDate  = valueSearch.getExpiredDate();
        	String lotId		= valueSearch.getLotid();
        	Integer qty			= valueSearch.getBalance();
        	returnMap.put("locationCode", locationCode);
        	returnMap.put("locationName", locationName);
        	returnMap.put("receivedDate", receivedDate);
        	
        	if(expiredDate != null){
        		returnMap.put("expiredDate", expiredDate);
        	}
        	returnMap.put("lotId", lotId);
        	returnMap.put("qty", qty);
        	
        	result.add(returnMap);
        }
        
        Map model = new HashMap();                            
        model.put("putawayDetail", result);
        
        return new ModelAndView("jsonView", model);
    }
}
