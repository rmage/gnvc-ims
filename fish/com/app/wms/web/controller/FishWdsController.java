package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.FishBalanceDao;
import com.app.wms.engine.db.dao.FishBalanceHistoryDao;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.app.wms.engine.db.dao.FishWdsDao;
import com.app.wms.engine.db.dao.FishWdsDetailDao;
import com.app.wms.engine.db.dto.FishBalance;
import com.app.wms.engine.db.dto.FishBalanceHistory;
import com.app.wms.engine.db.dto.FishWds;
import com.app.wms.engine.db.dto.FishWdsDetail;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;

public class FishWdsController extends MultiActionController {
		
	private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, Object> modelMap = this.searchAndPaging(request, response);
		return new ModelAndView("fish/WDSDataList", "model", modelMap);
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
            
            FishWdsDao dao = DaoFactory.createFishWdsDao();
            List<FishWds> fishWdsList = null;
            
            if(request.getParameter("search") != null) {
            	String wdsNo = request.getParameter("wdsNo");
                if(request.getParameter("wdsDate") != null) {
                    Date wdsDate = df.parse(request.getParameter("wdsDate"));
                    fishWdsList = dao.searchAndPaging(wdsNo, wdsDate, paging, offset);
                    String querySearch = "&search=true&wdsNo="+wdsNo+"&wdsDate="+df.format(wdsDate);
                    modelMap.put("querySearch", querySearch);
                    modelMap.put("queryWdsNo", wdsNo);
                    modelMap.put("queryWdsDate", df.format(wdsDate));   
                }
                else {
                    fishWdsList = dao.searchAndPagingWithoutDate(wdsNo, paging, offset);
                    String querySearch = "&search=true&wdsNo="+wdsNo;
                    modelMap.put("querySearch", querySearch);
                    modelMap.put("queryWdsNo", wdsNo);
                }
            }
            else {
                fishWdsList = dao.findAllAndPaging(paging, offset);
            }
            
            modelMap.put("wdsDataList", fishWdsList);
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
		return new ModelAndView("fish/WDSDataAdd", "model", modelMap);
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
        	String wdsNo = request.getParameter("wdsNo");
        	Date wdsDate = df.parse(request.getParameter("wdsDate"));
        	Integer vesselId = Integer.parseInt(request.getParameter("vesselId"));
        	String requestedBy = request.getParameter("requestedBy");
        	String approvedBy = request.getParameter("approvedBy");
        	
        	FishWds dto = new FishWds();
        	dto.setWdsNo(wdsNo);
        	dto.setWdsDate(wdsDate);
        	dto.setVesselId(vesselId);
        	dto.setRequestedBy(requestedBy);
        	dto.setApprovedBy(approvedBy);
        	dto.setCreatedDate(new Date());
        	dto.setCreatedBy(user.getUserId());
        	dto.setIsActive("Y");
        	dto.setIsDelete("N");
        	
        	FishWdsDao dao = DaoFactory.createFishWdsDao();
        	int wdsId = dao.insert(dto);
        	int totalData = Integer.valueOf(request.getParameter("totalData"));
        	
        	for(int i=1; i<=totalData; i++) {
        		int fishId = Integer.valueOf(request.getParameter("fishId"+i));
        		int storageId = Integer.valueOf(request.getParameter("storageId"+i));
        		Double qty = Double.valueOf(request.getParameter("qty"+i));
        		String uomCode = request.getParameter("uomCode"+i);
        		String description = request.getParameter("description"+i);
        		
        		FishWdsDetail wdsDetail = new FishWdsDetail();
        		wdsDetail.setWdsId(wdsId);
        		wdsDetail.setFishId(fishId);
        		wdsDetail.setDescription(description);
        		wdsDetail.setStorageId(storageId);
        		wdsDetail.setQuantity(qty);
        		wdsDetail.setUomCode(uomCode);
        		wdsDetail.setCreatedDate(new Date());
        		wdsDetail.setCreatedBy(user.getUserId());
        		wdsDetail.setIsActive("Y");
        		wdsDetail.setIsDelete("N");
        		
        		FishWdsDetailDao wdsDetailDao = DaoFactory.createFishWdsDetailDao();
        		wdsDetailDao.insert(wdsDetail);
                
                //Cut fish balance in DB
        		FishBalanceDao fishBalanceDao = DaoFactory.createFishBalanceDao();
        		FishBalance fishBalance = fishBalanceDao.findUniqueFishBalance(
        				vesselId, wdsDetail.getStorageId(), wdsDetail.getFishId());
        		
        		Double newBalance = fishBalance.getBalance() - wdsDetail.getQuantity();
        		fishBalance.setBalance(newBalance);
        		fishBalanceDao.update(fishBalance.getId(), fishBalance);
        		
        		//insert balance history
    			Double currentBalance = fishBalance.getBalance();
    			FishBalanceHistory fishBalanceHistory = new FishBalanceHistory();
    			fishBalanceHistory.setDocNo(wdsNo);
    			fishBalanceHistory.setBatchNo(fishBalance.getVessel().getBatchNo());
    			fishBalanceHistory.setFishType(fishBalance.getFish().getCode());
    			fishBalanceHistory.setStorage(fishBalance.getStorageId() == 0 ?
    					"FRESH" : fishBalance.getStorage().getCode());
    			fishBalanceHistory.setQtyIn(Double.valueOf("0"));
    			fishBalanceHistory.setQtyOut(wdsDetail.getQuantity());
    			fishBalanceHistory.setBalance(currentBalance);
    			fishBalanceHistory.setCreatedDate(new Date());
    			fishBalanceHistory.setCreatedBy(user.getUserId());
    			fishBalanceHistory.setIsActive("Y");
    			fishBalanceHistory.setIsDelete("N");
    			
    			FishBalanceHistoryDao balanceHistoryDao = DaoFactory.createFishBalanceHistoryDao();
    			balanceHistoryDao.insert(fishBalanceHistory);
        	}
        }
        
        modelMap = this.searchAndPaging(request, response);
		return new ModelAndView("fish/WDSDataList", "model", modelMap);
	}
    
    public ModelAndView inactivate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.valueOf(request.getParameter("id"));
        FishWdsDao dao = DaoFactory.createFishWdsDao();
        dao.delete(id);
        
        FishWdsDetailDao detailDao = DaoFactory.createFishWdsDetailDao();
        detailDao.deleteAllByWdsId(id);
        
        HashMap<String, Object> modelMap = this.searchAndPaging(request, response);
		return new ModelAndView("fish/WDSDataList", "model", modelMap);
    }
	
	public ModelAndView ajaxDocument(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int wdsId = Integer.valueOf(request.getParameter("wdsId"));
		FishWdsDetailDao dao = DaoFactory.createFishWdsDetailDao();
		List<FishWdsDetail> wdsDetailList = dao.findAllByWdsId(wdsId);
		
		Map tableMap = new HashMap();
		for (FishWdsDetail fishWdsDetail : wdsDetailList) {
			Map<String, Object> returnMap = new HashMap<String, Object>();
			returnMap.put("wdsNo", fishWdsDetail.getWithdrawalSlip().getWdsNo());
			returnMap.put("fishType", fishWdsDetail.getFish().getCode());
			returnMap.put("fishName", fishWdsDetail.getFish().getFishType().getDescription());
			returnMap.put("qty", fishWdsDetail.getQuantity());
			returnMap.put("storage", fishWdsDetail.getStorageId() == 0 ?
					"FRESH" : fishWdsDetail.getStorage().getCode());
			
			tableMap.put(returnMap, returnMap);
		}
				
		modelMap.put("tableMap", tableMap);
		return new ModelAndView("fish/WDSDataDetailList", "model", modelMap);
	}
}
