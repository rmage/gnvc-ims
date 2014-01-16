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

import com.app.wms.engine.db.dao.FishBalanceDao;
import com.app.wms.engine.db.dao.FishBalanceHistoryDao;
import com.app.wms.engine.db.dao.FishTsDao;
import com.app.wms.engine.db.dao.FishTsDetailDao;
import com.app.wms.engine.db.dto.FishBalance;
import com.app.wms.engine.db.dto.FishBalanceHistory;
import com.app.wms.engine.db.dto.FishTs;
import com.app.wms.engine.db.dto.FishTsDetail;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;

public class FishTsController extends MultiActionController {

private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, Object> modelMap = this.searchAndPaging(request, response);
		return new ModelAndView("fish/TSDataList", "model", modelMap);
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
            
            FishTsDao dao = DaoFactory.createFishTsDao();
            List<FishTs> fishTsList = null;
            
            if(request.getParameter("search") != null) {
            	String tsNo = request.getParameter("tsNo");
            	Date tsDate = df.parse(request.getParameter("tsDate"));
            	fishTsList = dao.searchAndPaging(tsNo, tsDate, paging, offset);
            	String querySearch = "&search=true&tsNo="+tsNo+"&tsDate="+df.format(tsDate);
            	modelMap.put("querySearch", querySearch);
            	modelMap.put("queryTsNo", tsNo);
            	modelMap.put("queryTsDate", tsDate);
            }
            else {
                fishTsList = dao.findAllAndPaging(paging, offset);
            }
            
            modelMap.put("tsDataList", fishTsList);
            modelMap.put("totalRows", 2000);
            modelMap.put("page", page);
            modelMap.put("paging", paging);
		}
		catch (Exception e) {
			throw e;
		}
		
		return modelMap;
	}
	
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, Object> modelMap = new HashMap<String, Object>();
		
		modelMap.put("mode", "create");
		return new ModelAndView("fish/TSDataAdd", "model", modelMap);
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
        	Integer wdsId = Integer.valueOf(request.getParameter("wdsId"));
        	Integer vesselId = Integer.valueOf(request.getParameter("vesselId"));
        	String tsNo = request.getParameter("tsNo");
        	Date tsDate = df.parse(request.getParameter("tsDate"));
        	String issuedBy = request.getParameter("issuedBy");
        	String notedBy = request.getParameter("notedBy");
        	String approvedBy = request.getParameter("approvedBy");
        	String receivedBy = request.getParameter("receivedBy");
        	
        	FishTs dto = new FishTs();
        	dto.setWdsId(wdsId);
        	dto.setVesselId(vesselId);
        	dto.setTsNo(tsNo);
        	dto.setTsDate(tsDate);
        	dto.setIssuedBy(issuedBy);
        	dto.setNotedBy(notedBy);
        	dto.setApprovedBy(approvedBy);
        	dto.setReceivedBy(receivedBy);
        	dto.setCreatedDate(new Date());
        	dto.setCreatedBy(user.getUserId());
        	dto.setIsActive("Y");
        	dto.setIsDelete("N");
        	
        	FishTsDao dao = DaoFactory.createFishTsDao();
        	int tsId = dao.insert(dto);
        	int totalData = Integer.valueOf(request.getParameter("totalData"));
        	
        	for(int i=1; i<=totalData; i++) {
        		int fishId = Integer.valueOf(request.getParameter("fishId"+i));
        		int storageId = Integer.valueOf(request.getParameter("storageId"+i));
        		String description = request.getParameter("description"+i);
        		Double qty = Double.valueOf(request.getParameter("qty"+i));
        		String uomCode = request.getParameter("uomCode"+i);
        		
        		FishTsDetail tsDetail = new FishTsDetail();
        		tsDetail.setTsId(tsId);
        		tsDetail.setFishId(fishId);
        		tsDetail.setStorageId(storageId);
        		tsDetail.setDescription(description);
        		tsDetail.setQuantity(qty);
        		tsDetail.setUomCode(uomCode);
        		tsDetail.setCreatedDate(new Date());
        		tsDetail.setCreatedBy(user.getUserId());
        		tsDetail.setIsActive("Y");
        		tsDetail.setIsDelete("N");
        		
        		FishTsDetailDao tsDetailDao = DaoFactory.createFishTsDetailDao();
        		tsDetailDao.insert(tsDetail);
        		
        		//Cut fish balance in DB
        		FishBalanceDao fishBalanceDao = DaoFactory.createFishBalanceDao();
        		FishBalance fishBalance = fishBalanceDao.findUniqueFishBalance(
        				vesselId, tsDetail.getStorageId(), tsDetail.getFishId());
        		
        		Double newBalance = fishBalance.getBalance() - tsDetail.getQuantity();
        		fishBalance.setBalance(newBalance);
        		fishBalanceDao.update(fishBalance.getId(), fishBalance);
        		
        		//insert balance history
    			Double currentBalance = fishBalance.getBalance();
    			FishBalanceHistory fishBalanceHistory = new FishBalanceHistory();
    			fishBalanceHistory.setDocNo(tsNo);
    			fishBalanceHistory.setBatchNo(fishBalance.getVessel().getBatchNo());
    			fishBalanceHistory.setFishType(fishBalance.getFish().getCode());
    			fishBalanceHistory.setStorage(fishBalance.getStorageId() == 0 ?
    					"FRESH" : fishBalance.getStorage().getCode());
    			fishBalanceHistory.setQtyIn(Double.valueOf("0"));
    			fishBalanceHistory.setQtyOut(tsDetail.getQuantity());
    			fishBalanceHistory.setBalance(currentBalance);
    			fishBalanceHistory.setCreatedDate(new Date());
    			fishBalanceHistory.setCreatedBy(user.getUserId());
    			fishBalanceHistory.setIsActive("Y");
    			fishBalanceHistory.setIsDelete("N");
    			
    			FishBalanceHistoryDao balanceHistoryDao = DaoFactory.createFishBalanceHistoryDao();
    			balanceHistoryDao.insert(fishBalanceHistory);
        	}
        	
        	modelMap = this.searchAndPaging(request, response);
    		return new ModelAndView("fish/TSDataList", "model", modelMap);
        }
	}
	
	public ModelAndView ajaxDocument(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		 
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int tsId = Integer.valueOf(request.getParameter("tsId"));
		FishTsDetailDao dao = DaoFactory.createFishTsDetailDao();
		List<FishTsDetail> tsDetailList = dao.findAllByTsId(tsId);
		
		Map tableMap = new HashMap();
		for (FishTsDetail fishTsDetail : tsDetailList) {
			Map<String, Object> returnMap = new HashMap<String, Object>();
			returnMap.put("tsNo", fishTsDetail.getTransferSlip().getTsNo());
			returnMap.put("fishType", fishTsDetail.getFish().getCode());
			returnMap.put("fishName", fishTsDetail.getFish().getFishType().getDescription());
			returnMap.put("storage", fishTsDetail.getStorageId() == 0 ?
					"FRESH" : fishTsDetail.getStorage().getCode());
			returnMap.put("qty", fishTsDetail.getQuantity());
			
			tableMap.put(returnMap, returnMap);
		}
		
		modelMap.put("tableMap", tableMap);
		return new ModelAndView("fish/TSDataDetailList", "model", modelMap);
	}
}