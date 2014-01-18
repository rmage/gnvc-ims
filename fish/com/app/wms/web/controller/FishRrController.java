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
import com.app.wms.engine.db.dao.FishRrDao;
import com.app.wms.engine.db.dao.FishRrDetailDao;
import com.app.wms.engine.db.dto.FishBalance;
import com.app.wms.engine.db.dto.FishBalanceHistory;
import com.app.wms.engine.db.dto.FishRr;
import com.app.wms.engine.db.dto.FishRrDetail;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;

public class FishRrController extends MultiActionController {

	private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, Object> modelMap = this.searchAndPaging(request, response);
		return new ModelAndView("fish/RRDataList", "model", modelMap);
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
            
            FishRrDao dao = DaoFactory.createFishRrDao();
            List<FishRr> rrDataList = null;
            
            if(request.getParameter("search") != null) {
            	String rrNo = request.getParameter("rrNo");
                if(request.getParameter("rrDate") != null) {
                    Date rrDate = df.parse(request.getParameter("rrDate"));
                    rrDataList = dao.searchAndPaging(rrNo, rrDate, paging, offset);
                    String querySearch = "&search=true&rrNo="+rrNo+"&rrDate="+df.format(rrDate);
                    modelMap.put("querySearch", querySearch);
                    modelMap.put("queryRrNo", rrNo);
                    modelMap.put("queryRrDate", df.format(rrDate));
                }
                else {
                    rrDataList = dao.searchAndPagingWithoutDate(rrNo, paging, offset);
                    String querySearch = "&search=true&rrNo="+rrNo;
                    modelMap.put("querySearch", querySearch);
                    modelMap.put("queryRrNo", rrNo);
                }
            }
            else {
                rrDataList = dao.findAllAndPaging(paging, offset);
            }
            
            modelMap.put("rrDataList", rrDataList);
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
		return new ModelAndView("fish/RRDataAdd", "model", modelMap);
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
        	String rrNo = request.getParameter("rrNo");
        	String wsNo = request.getParameter("wsNo");
        	Date rrDate = df.parse(request.getParameter("rrDate"));
        	Integer vesselId = Integer.valueOf(request.getParameter("vesselId"));
        	String receivedBy = request.getParameter("receivedBy");
        	String approvedBy = request.getParameter("approvedBy");
        	String evaluatedBy = request.getParameter("evaluatedBy");
        	
        	FishRr dto = new FishRr();
        	dto.setRrNo(rrNo);
        	dto.setWsNo(wsNo);
        	dto.setRrDate(rrDate);
        	dto.setVesselId(vesselId);
        	dto.setReceivedBy(receivedBy);
        	dto.setApprovedBy(approvedBy);
        	dto.setEvaluatedBy(evaluatedBy);
        	dto.setCreatedDate(new Date());
        	dto.setCreatedBy(user.getUserId());
        	dto.setIsActive("Y");
        	dto.setIsDelete("N");
        	
        	FishRrDao dao = DaoFactory.createFishRrDao();
        	Integer rrId = dao.insert(dto);
        	
        	Integer totalData = Integer.valueOf(request.getParameter("totalData"));
        	for(int i=1; i<=totalData; i++) {
        		Integer wsId = Integer.valueOf(request.getParameter("wsId"+i));
        		Integer fishId = Integer.valueOf(request.getParameter("fishId"+i));
        		Integer storageId = Integer.valueOf(request.getParameter("storageId"+i));
        		Double totalWeight = Double.valueOf(request.getParameter("totalWeight"+i));
        		Double goodWeight = Double.valueOf(request.getParameter("goodWeight"+i));
        		Double spoilageWeight = Double.valueOf(request.getParameter("spoilageWeight"+i));
        		
        		FishRrDetail rrDetail = new FishRrDetail();
        		rrDetail.setRrId(rrId);
        		rrDetail.setWsId(wsId);
        		rrDetail.setFishId(fishId);
        		rrDetail.setStorageId(storageId);
        		rrDetail.setTotalWeight(totalWeight);
        		rrDetail.setGoodWeight(goodWeight);
        		rrDetail.setSpoilageWeight(spoilageWeight);
        		rrDetail.setCreatedDate(new Date());
        		rrDetail.setCreatedBy(user.getUserId());
        		rrDetail.setIsActive("Y");
        		rrDetail.setIsDelete("N");
        		
        		FishRrDetailDao rrDetailDao = DaoFactory.createFishRrDetailDao();
        		int id = rrDetailDao.insert(rrDetail);
        		
        		if(id > 0) {
        			FishBalanceDao fishBalanceDao = DaoFactory.createFishBalanceDao();
        			FishBalance fishBalance = fishBalanceDao.findUniqueFishBalance(
        					vesselId, rrDetail.getStorageId(), rrDetail.getFishId());
        			
        			if(fishBalance != null) {
        				Double balance = fishBalance.getBalance();
        				fishBalance.setBalance(balance + rrDetail.getGoodWeight());
        				fishBalance.setUpdatedDate(new Date());
        				fishBalance.setUpdatedBy(user.getUserId());
        				fishBalanceDao.update(fishBalance.getId(), fishBalance);
        			}
        			else {
        				fishBalance = new FishBalance();
        				fishBalance.setVesselId(vesselId);
        				fishBalance.setStorageId(rrDetail.getStorageId());
        				fishBalance.setFishId(rrDetail.getFishId());
        				fishBalance.setBalance(rrDetail.getGoodWeight());
        				fishBalance.setCreatedDate(new Date());
        				fishBalance.setCreatedBy(user.getUserId());
        				fishBalance.setIsActive("Y");
        				fishBalance.setIsDelete("N");
        				int balanceId = fishBalanceDao.insert(fishBalance);
        				fishBalance = fishBalanceDao.findByPrimaryKey(balanceId);
        			}
        			
        			//insert balance history
        			Double currentBalance = fishBalance.getBalance();
        			FishBalanceHistory fishBalanceHistory = new FishBalanceHistory();
        			fishBalanceHistory.setDocNo(rrNo);
        			fishBalanceHistory.setBatchNo(fishBalance.getVessel().getBatchNo());
        			fishBalanceHistory.setFishType(fishBalance.getFish().getCode());
        			fishBalanceHistory.setStorage(fishBalance.getStorageId() == 0 ?
        					"FRESH" : fishBalance.getStorage().getCode());
        			fishBalanceHistory.setQtyIn(rrDetail.getGoodWeight());
        			fishBalanceHistory.setQtyOut(Double.valueOf("0"));
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
    		return new ModelAndView("fish/RRDataList", "model", modelMap);
        }
	}
	
	public ModelAndView ajaxDocument(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int rrId = Integer.valueOf(request.getParameter("rrId"));
		FishRrDetailDao dao = DaoFactory.createFishRrDetailDao();
		List<FishRrDetail> rrDetailList = dao.findByRrIdGroupByFishAndStorage(rrId);
		
		Map tableMap = new HashMap();
		for (FishRrDetail fishRrDetail : rrDetailList) {
			Map<String, Object> returnMap = new HashMap<String, Object>();
			returnMap.put("wsNo", fishRrDetail.getWeightSlip().getWsNo());
			returnMap.put("fishType", fishRrDetail.getFish().getCode());
			returnMap.put("fishName", fishRrDetail.getFish().getFishType().getDescription());
			returnMap.put("goodWeight", fishRrDetail.getTotalWeight());
			returnMap.put("spoilageWeight", fishRrDetail.getSpoilageWeight());
			returnMap.put("totalWeight", fishRrDetail.getTotalWeight());
			returnMap.put("storage", 
					fishRrDetail.getStorageId() == 0 ? 
							"FRESH" : fishRrDetail.getWeightSlip().getStorage().getCode());
			
			tableMap.put(returnMap, returnMap);
		}
		
		modelMap.put("tableMap", tableMap);
		return new ModelAndView("fish/RRDataDetailList", "model", modelMap);
	}
}
