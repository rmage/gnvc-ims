package com.app.wms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.app.wms.engine.db.dao.FishDao;
import com.app.wms.engine.db.dao.FishSpoilageDao;
import com.app.wms.engine.db.dao.FishTypeDao;
import com.app.wms.engine.db.dto.Fish;
import com.app.wms.engine.db.dto.FishSpoilage;
import com.app.wms.engine.db.dto.FishType;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;

public class FishSpoilageDataController extends MultiActionController {

	private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, Object> modelMap = this.searchAndPaging(request, response);
		return new ModelAndView("fish/FishSpoilageList", "model", modelMap);
	}
	
	private HashMap<String, Object> searchAndPaging(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, Object> modelMap = new HashMap<String, Object>();
		
		try {
			Integer page = 1;
            Integer paging = 10;
            Integer offset = 1;
            
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
                offset = (page - 1) * paging + 1;
            }
            if (request.getParameter("paging") != null) {
                paging = Integer.parseInt(request.getParameter("paging"));
            }
            
            FishSpoilageDao dao = DaoFactory.createFishSpoilageDao();
    		List<FishSpoilage> spoilageDataList = dao.findAllDistinctAndPaging(paging, offset);

    		modelMap.put("spoilageDataList", spoilageDataList);
            modelMap.put("totalRows", 2000);
            modelMap.put("page", page);
            modelMap.put("paging", paging);
		}
		catch (Exception e) {
			throw e;
		}
		
		return modelMap;
	}
	
	public void ajaxSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginUser user = (LoginUser) request.getSession().getAttribute("user");
        if (user != null) {
            String catcherNo = request.getParameter("catcherNo");
            Date dateShift = df.parse(request.getParameter("dateShift"));
            String timeShift = request.getParameter("timeShift");
            Integer vesselId = Integer.valueOf(request.getParameter("vesselId"));
            Integer fishId = Integer.valueOf(request.getParameter("fishId"));
            Double rawWeight = Double.valueOf(request.getParameter("rawWeight"));
            Double cookedWeight = Double.valueOf(request.getParameter("cookedWeight"));
            Double totalProcessed = Double.valueOf(request.getParameter("totalProcessed"));
            String reason = request.getParameter("reason");
            
            FishSpoilageDao dao = DaoFactory.createFishSpoilageDao();
            FishSpoilage dto = new FishSpoilage();
            dto.setCatcherNo(catcherNo);
            dto.setDateShift(dateShift);
            dto.setTimeShift(timeShift);
            dto.setVesselId(vesselId);
            dto.setFishId(fishId);
            dto.setRawWeight(rawWeight);
            dto.setCookedWeight(cookedWeight);
            dto.setTotalProcessed(totalProcessed);
            dto.setReason(reason);
            dto.setCreatedDate(new Date());
            dto.setCreatedBy(user.getUserId());
            dto.setIsActive("Y");
            dto.setIsDelete("N");
            
            dao.insert(dto);
        }
	}
	
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		FishDao dao = DaoFactory.createFishDao();
		List<Fish> fishDataList = dao.findAllActive();
		
		HashMap<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("fishDataList", fishDataList);
		
		return new ModelAndView("fish/FishSpoilageAdd", "model", modelMap);
	}
	
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		LoginUser user = (LoginUser) request.getSession().getAttribute("user");
        HashMap<String, Object> modelMap = new HashMap<String, Object>();
        
        if (user == null) {
            String msg = "You haven't login or your session has been expired! Please do login again";
            modelMap.put("msg", msg);
            
            return new ModelAndView("login", "model", modelMap);
        }else{
        	int totalData = Integer.valueOf(request.getParameter("totalData"));
        	Date dateShift = df.parse(request.getParameter("dateShift"));
        	String timeShift = request.getParameter("timeShift");
        	int vesselId = Integer.valueOf(request.getParameter("vesselId"));
        	
        	for(int i=1; i<=totalData; i++) {
        		String catcherNo = request.getParameter("catcherNo"+i);
        		int fishId = Integer.valueOf(request.getParameter("fishId"+i));
        		Double cookedWeight = Double.valueOf(request.getParameter("cookedWeight"+i));
        		Double rawWeight = Double.valueOf(request.getParameter("rawWeight"+i));
        		Double totalProcessed = Double.valueOf(request.getParameter("totalProcessed"+i));
        		String reason = request.getParameter("reason"+i);
        		
        		FishSpoilage dto = new FishSpoilage();
        		dto.setCatcherNo(catcherNo);
        		dto.setDateShift(dateShift);
        		dto.setTimeShift(timeShift);
        		dto.setVesselId(vesselId);
        		dto.setFishId(fishId);
        		dto.setCookedWeight(cookedWeight);
        		dto.setRawWeight(rawWeight);
        		dto.setTotalProcessed(totalProcessed);
        		dto.setReason(reason);
        		dto.setCreatedDate(new Date());
        		dto.setCreatedBy(user.getUserId());
        		dto.setIsActive("Y");
        		dto.setIsDelete("N");
        		
        		FishSpoilageDao dao = DaoFactory.createFishSpoilageDao();
        		dao.insert(dto);
        	}
        	
        	FishDao fishDao = DaoFactory.createFishDao();
    		List<Fish> fishDataList = fishDao.findAllActive();
    		
    		modelMap = new HashMap<String, Object>();
    		modelMap.put("fishDataList", fishDataList);
    		
    		return new ModelAndView("fish/FishSpoilageAdd", "model", modelMap);
        }
	}
}
