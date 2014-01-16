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

import com.app.wms.engine.db.dao.FishBadStockDao;
import com.app.wms.engine.db.dao.FishBadStockDetailDao;
import com.app.wms.engine.db.dao.FishBalanceDao;
import com.app.wms.engine.db.dao.FishBalanceHistoryDao;
import com.app.wms.engine.db.dto.FishBadStock;
import com.app.wms.engine.db.dto.FishBadStockDetail;
import com.app.wms.engine.db.dto.FishBalance;
import com.app.wms.engine.db.dto.FishBalanceHistory;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;

public class FishBadStockController extends MultiActionController {

private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, Object> modelMap = this.searchAndPaging(request, response);
		return new ModelAndView("fish/BSDataList", "model", modelMap);
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
            
            FishBadStockDao dao = DaoFactory.createFishBadStockDao();
            List<FishBadStock> fishBsList = null;
            
            if(request.getParameter("search") != null) {
            	String bsNo = request.getParameter("bsNo");
            	Date bsDate = df.parse(request.getParameter("bsDate"));
            	fishBsList = dao.searchAndPaging(bsNo, bsDate, paging, offset);
            	String querySearch = "&search=true&bsNo="+bsNo+"&bsDate="+df.format(bsDate);
            	modelMap.put("querySearch", querySearch);
            	modelMap.put("queryBsNo", bsNo);
            	modelMap.put("queryBsDate", bsDate);
            }
            else {
                fishBsList = dao.findAllAndPaging(paging, offset);
            }
            
            modelMap.put("bsDataList", fishBsList);
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
		return new ModelAndView("fish/BSDataAdd", "model", modelMap);
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
        	Integer vesselId = Integer.valueOf(request.getParameter("vesselId"));
        	String bsNo = request.getParameter("bsNo");
        	Date bsDate = df.parse(request.getParameter("bsDate"));
        	String issuedBy = request.getParameter("issuedBy");
        	String notedBy = request.getParameter("notedBy");
        	String approvedBy = request.getParameter("approvedBy");
        	String receivedBy = request.getParameter("receivedBy");
        	
        	FishBadStock dto = new FishBadStock();
        	dto.setVesselId(vesselId);
        	dto.setBsNo(bsNo);
        	dto.setBsDate(bsDate);
        	dto.setIssuedBy(issuedBy);
        	dto.setNotedBy(notedBy);
        	dto.setApprovedBy(approvedBy);
        	dto.setReceivedBy(receivedBy);
        	dto.setCreatedDate(new Date());
        	dto.setCreatedBy(user.getUserId());
        	dto.setIsActive("Y");
        	dto.setIsDelete("N");
        	
        	FishBadStockDao dao = DaoFactory.createFishBadStockDao();
        	int bsId = dao.insert(dto);
        	int totalData = Integer.valueOf(request.getParameter("totalData"));
        	
        	for(int i=1; i<=totalData; i++) {
        		int fishId = Integer.valueOf(request.getParameter("fishId"+i));
        		int storageId = Integer.valueOf(request.getParameter("storageId"+i));
        		String description = request.getParameter("description"+i);
        		Double qty = Double.valueOf(request.getParameter("qty"+i));
        		String uomCode = request.getParameter("uomCode"+i);
        		
        		FishBadStockDetail bsDetail = new FishBadStockDetail();
        		bsDetail.setBsId(bsId);
        		bsDetail.setFishId(fishId);
        		bsDetail.setStorageId(storageId);
        		bsDetail.setDescription(description);
        		bsDetail.setQuantity(qty);
        		bsDetail.setUomCode(uomCode);
        		bsDetail.setCreatedDate(new Date());
        		bsDetail.setCreatedBy(user.getUserId());
        		bsDetail.setIsActive("Y");
        		bsDetail.setIsDelete("N");
        		
        		FishBadStockDetailDao bsDetailDao = DaoFactory.createFishBadStockDetailDao();
        		bsDetailDao.insert(bsDetail);
        		
        		//Cut fish balance in DB
        		FishBalanceDao fishBalanceDao = DaoFactory.createFishBalanceDao();
        		FishBalance fishBalance = fishBalanceDao.findUniqueFishBalance(
        				vesselId, bsDetail.getStorageId(), bsDetail.getFishId());
        		
        		Double newBalance = fishBalance.getBalance() - bsDetail.getQuantity();
        		fishBalance.setBalance(newBalance);
        		fishBalanceDao.update(fishBalance.getId(), fishBalance);
        		
        		//insert balance history
    			Double currentBalance = fishBalance.getBalance();
    			FishBalanceHistory fishBalanceHistory = new FishBalanceHistory();
    			fishBalanceHistory.setDocNo(bsNo + " (BAD)");
    			fishBalanceHistory.setBatchNo(fishBalance.getVessel().getBatchNo());
    			fishBalanceHistory.setFishType(fishBalance.getFish().getCode());
    			fishBalanceHistory.setStorage(fishBalance.getStorageId() == 0 ?
    					"FRESH" : fishBalance.getStorage().getCode());
    			fishBalanceHistory.setQtyIn(Double.valueOf("0"));
    			fishBalanceHistory.setQtyOut(bsDetail.getQuantity());
    			fishBalanceHistory.setBalance(currentBalance);
    			fishBalanceHistory.setCreatedDate(new Date());
    			fishBalanceHistory.setCreatedBy(user.getUserId());
    			fishBalanceHistory.setIsActive("Y");
    			fishBalanceHistory.setIsDelete("N");
    			
    			FishBalanceHistoryDao balanceHistoryDao = DaoFactory.createFishBalanceHistoryDao();
    			balanceHistoryDao.insert(fishBalanceHistory);
        	}
        	
        	modelMap = this.searchAndPaging(request, response);
    		return new ModelAndView("fish/BSDataList", "model", modelMap);
        }
	}
	
	public ModelAndView ajaxDocument(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		 
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int bsId = Integer.valueOf(request.getParameter("bsId"));
		FishBadStockDetailDao dao = DaoFactory.createFishBadStockDetailDao();
		List<FishBadStockDetail> bsDetailList = dao.findAllByBsId(bsId);
		
		Map tableMap = new HashMap();
		for (FishBadStockDetail fishBsDetail : bsDetailList) {
			Map<String, Object> returnMap = new HashMap<String, Object>();
			returnMap.put("bsNo", fishBsDetail.getBadstockSlip().getBsNo());
			returnMap.put("fishType", fishBsDetail.getFish().getCode());
			returnMap.put("fishName", fishBsDetail.getFish().getFishType().getDescription());
			returnMap.put("storage", fishBsDetail.getStorageId() == 0 ?
					"FRESH" : fishBsDetail.getStorage().getCode());
			returnMap.put("qty", fishBsDetail.getQuantity());
			
			tableMap.put(returnMap, returnMap);
		}
		
		modelMap.put("tableMap", tableMap);
		return new ModelAndView("fish/BSDataDetailList", "model", modelMap);
	}
}