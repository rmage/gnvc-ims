package com.app.wms.web.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
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
import com.app.wms.engine.db.dto.PutawayDetail;
import com.app.wms.engine.db.exceptions.PutawayDetailDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

@Controller
@RequestMapping( {"/locationproductpicking2.htm"})
public class LocationProductPicking2Controller {
	@RequestMapping(method = RequestMethod.GET, value =  "/locationproductpicking2.htm")
    public ModelAndView jsonrequest(HttpServletRequest request, HttpServletResponse response) throws PutawayDetailDaoException {

        String productCode = request.getParameter("productId");
        String lotId1 = request.getParameter("lotId");
        PutawayDetail pud = new PutawayDetail();
        pud.setProductCode(productCode);
        pud.setLotid(lotId1);
        
        PutawayDetailDao dao = DaoFactory.createPutawayDetailDao();
        List<PutawayDetail> pd = dao.findWhereProductCodeEquals2(pud);
        
        List result = new ArrayList();
        for(PutawayDetail valueSearch : pd){
        	Map returnMap = new HashMap();
        	String locationCode = valueSearch.getLocationCode();
        	String locationName = valueSearch.getWhLocation().getLocationName();
        	Timestamp receivedDate = valueSearch.getReceivedDate();
        	Timestamp expiredDate  = valueSearch.getExpiredDate();
        	String lotId		= valueSearch.getLotid();
        	Integer qty			= valueSearch.getQtyPut();
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
