package com.app.wms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.app.wms.engine.db.dao.FishDao;
import com.app.wms.engine.db.dao.FishStorageDao;
import com.app.wms.engine.db.dao.FishVesselDao;
import com.app.wms.engine.db.dao.FishWsDao;
import com.app.wms.engine.db.dao.FishWsDetailDao;
import com.app.wms.engine.db.dao.FishWsTypeDao;
import com.app.wms.engine.db.dto.Fish;
import com.app.wms.engine.db.dto.FishStorage;
import com.app.wms.engine.db.dto.FishVessel;
import com.app.wms.engine.db.dto.FishWSType;
import com.app.wms.engine.db.dto.FishWs;
import com.app.wms.engine.db.dto.FishWsDetail;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.DaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class FishWsController extends MultiActionController {
	
	private SimpleDateFormat dfApp = new SimpleDateFormat("dd/MM/yyyy");
	
	public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, Object> modelMap = this.searchAndPaging(request, response);
		return new ModelAndView("fish/WSDataList", "model", modelMap);
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
            
            FishWsDao dao = DaoFactory.createFishWsDao();
            List<FishWs> fishWsDataList = dao.findAllAndPaging(paging, offset);
            modelMap.put("fishWsData", fishWsDataList);
            modelMap.put("totalRows", 2000);
            modelMap.put("page", page);
            modelMap.put("paging", paging);
		}
		catch (Exception e) {
			throw e;
		}
		
		return modelMap;
	}
	
	public ModelAndView inactivate(HttpServletRequest request, HttpServletResponse response)throws Exception {
		return null;
	}
	
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, Object> modelMap = new HashMap<String, Object>();
		FishWs dto = new FishWs();
		
		FishDao fishDao = DaoFactory.createFishDao();
		List<Fish> fishes = fishDao.findAllActive();
		
		FishWsTypeDao wsTypeDao = DaoFactory.createFishWsTypeDao();
		List<FishWSType> wsTypes = wsTypeDao.findAllActive();
		
		FishStorageDao fishStorageDao = DaoFactory.createFishStorageDao();
		List<FishStorage> fishStorages = fishStorageDao.findAllActive();
		
		modelMap.put("mode", "create");
		modelMap.put("dto", dto);
		modelMap.put("fishes", fishes);
		modelMap.put("wsTypes", wsTypes);
		modelMap.put("fishStorages", fishStorages);
		
		return new ModelAndView("fish/WSDataAdd", "model", modelMap);
	}
	
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginUser user = (LoginUser) request.getSession().getAttribute("user");
		String mode = request.getParameter("mode");
        HashMap<String, Object> modelMap = new HashMap<String, Object>();
        
        if (user == null) {
            String msg = "You haven't login or your session has been expired! Please do login again";
            modelMap.put("msg", msg);
            return new ModelAndView("login", "model", modelMap);
        }else{
        	String userId = "";
        	userId = (String)user.getUserId();
        	
        	String wsNo = request.getParameter("wsNo");
        	Integer wsTypeId = Integer.valueOf(request.getParameter("wsTypeId"));
        	Integer vesselId = Integer.valueOf(request.getParameter("vesselId"));
        	Integer storageId = Integer.valueOf(request.getParameter("storageId"));
        	Date dateShift = dfApp.parse(request.getParameter("dateShift"));
        	String timeShift = request.getParameter("timeShift");
        	String regu = request.getParameter("regu");
        	
        	FishWs dto = new FishWs();
        	dto.setWsNo(wsNo);
        	dto.setWsTypeId(wsTypeId);
        	dto.setVesselId(vesselId);
        	dto.setStorageId(storageId);
        	dto.setDateShift(dateShift);
        	dto.setTimeShift(timeShift);
        	dto.setRegu(regu);
        	dto.setCreatedDate(new Date());
        	dto.setCreatedBy(userId);
        	dto.setIsActive("Y");
        	dto.setIsDelete("N");
        	
        	FishWsDao dao = DaoFactory.createFishWsDao();
        	int id = dao.insert(dto);
        	dto.setId(id);
        	
        	Integer fishId = Integer.valueOf(request.getParameter("fishId"));
        	Double totalWeight = Double.valueOf(request.getParameter("totalWeight"));
        	
        	FishWsDetail wsDetail = new FishWsDetail();
        	wsDetail.setWsId(dto.getId());
        	wsDetail.setFishId(fishId);
        	wsDetail.setTotalWeight(totalWeight);
        	wsDetail.setCreatedDate(new Date());
        	wsDetail.setCreatedBy(userId);
        	wsDetail.setIsActive("Y");
        	wsDetail.setIsDelete("N");
        	
        	FishWsDetailDao wsDetailDao = DaoFactory.createFishWsDetailDao();
        	wsDetailDao.insert(wsDetail);
        	List<FishWsDetail> wsDetails = wsDetailDao.findByWsId(dto.getId());
        	
        	FishVesselDao fishVesselDao = DaoFactory.createFishVesselDao();
        	FishVessel fishVessel = fishVesselDao.findByPrimaryKey(dto.getVesselId());
        	dto.setVessel(fishVessel);
        	
        	FishWsTypeDao wsTypeDao = DaoFactory.createFishWsTypeDao();
        	FishWSType wsType = wsTypeDao.findByPrimaryKey(dto.getWsTypeId());
        	dto.setWsType(wsType);
        	
        	FishDao fishDao = DaoFactory.createFishDao();
        	List<Fish> fishes = fishDao.findAllActive();
        	
        	modelMap.put("dto", dto);
        	modelMap.put("wsDetails", wsDetails);
        	modelMap.put("fishes", fishes);
    		return new ModelAndView("fish/WSDataView", "model", modelMap);
        }
	}
	
	public ModelAndView saveDetail(HttpServletRequest request, HttpServletResponse response) {
		LoginUser user = (LoginUser) request.getSession().getAttribute("user");
		String mode = request.getParameter("mode");
        HashMap<String, Object> modelMap = new HashMap<String, Object>();
        
        if (user == null) {
            String msg = "You haven't login or your session has been expired! Please do login again";
            modelMap.put("msg", msg);
            return new ModelAndView("login", "model", modelMap);
        }
        else {
        	Integer wsId = Integer.valueOf(request.getParameter("wsId"));
        	Integer fishId = Integer.valueOf(request.getParameter("fishId"));
        	Double totalWeight = Double.valueOf(request.getParameter("totalWeight"));
        	
        	FishWsDetail wsDetail = new FishWsDetail();
        	wsDetail.setWsId(wsId);
        	wsDetail.setFishId(fishId);
        	wsDetail.setTotalWeight(totalWeight);
        	wsDetail.setCreatedDate(new Date());
        	wsDetail.setCreatedBy(user.getUserId());
        	wsDetail.setIsActive("Y");
        	wsDetail.setIsDelete("N");
        	
        	FishWsDetailDao wsDetailDao = DaoFactory.createFishWsDetailDao();
        	wsDetailDao.insert(wsDetail);
        	
        	try {
        		FishWsDao dao = DaoFactory.createFishWsDao();
            	FishWs dto = dao.findByPrimaryKey(wsId);
            	
            	FishDao fishDao = DaoFactory.createFishDao();
            	List<Fish> fishes = fishDao.findAllActive();
            	
            	List<FishWsDetail> wsDetails = wsDetailDao.findByWsId(wsId);
            	
            	modelMap.put("dto", dto);
            	modelMap.put("wsDetails", wsDetails);
            	modelMap.put("fishes", fishes);
        	}
        	catch (DaoException e) {
				e.printStackTrace();
			}

    		return new ModelAndView("fish/WSDataView", "model", modelMap);
        }
	}
	
	public ModelAndView ajaxDocument(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int wsId = Integer.valueOf(request.getParameter("wsId"));
		FishWsDetailDao dao = DaoFactory.createFishWsDetailDao();
		List<FishWsDetail> wsDetailList = dao.findByWsIdGroupByFish(wsId);
		
		Map tableMap = new HashMap();
		for (FishWsDetail fishWsDetail : wsDetailList) {
			Map<String, Object> returnMap = new HashMap<String, Object>();
			returnMap.put("wsNo", fishWsDetail.getWeightSlip().getWsNo());
			returnMap.put("fishType", fishWsDetail.getFish().getCode());
			returnMap.put("fishName", fishWsDetail.getFish().getFishType().getDescription());
			returnMap.put("totalWeight", fishWsDetail.getTotalWeight());
			
			tableMap.put(returnMap, returnMap);
		}
		
		modelMap.put("tableMap", tableMap);
		return new ModelAndView("fish/WSDataDetailList", "model", modelMap);
	}
}
