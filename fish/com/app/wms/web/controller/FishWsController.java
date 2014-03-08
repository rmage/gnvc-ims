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

import com.app.wms.engine.db.dao.FishDao;
import com.app.wms.engine.db.dao.FishStorageDao;
import com.app.wms.engine.db.dao.FishWsDao;
import com.app.wms.engine.db.dao.FishWsDetailDao;
import com.app.wms.engine.db.dao.FishWsTypeDao;
import com.app.wms.engine.db.dto.Fish;
import com.app.wms.engine.db.dto.FishBalance;
import com.app.wms.engine.db.dto.FishBalanceHistory;
import com.app.wms.engine.db.dto.FishStorage;
import com.app.wms.engine.db.dto.FishWSType;
import com.app.wms.engine.db.dto.FishWs;
import com.app.wms.engine.db.dto.FishWsDetail;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.DaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class FishWsController extends MultiActionController {
	
	private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
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
            List<FishWs> fishWsDataList = null;
            
            if(request.getParameter("search") != null) {
            	String wsNo = request.getParameter("wsNo");
                
                if(request.getParameter("wsDate") != null) {
                    Date wsDate = df.parse(request.getParameter("wsDate"));
                    fishWsDataList = dao.searchAndPaging(wsNo, wsDate, paging, offset);
                    String querySearch = "&search=true&wsNo="+wsNo+"&wsDate="+df.format(wsDate);
                    modelMap.put("queryWsNo", wsNo);
                    modelMap.put("queryWsDate", df.format(wsDate));
                    modelMap.put("querySearch", querySearch);   
                }
                else {
                    fishWsDataList = dao.searchAndPagingWithoutDate(wsNo, paging, offset);
                    String querySearch = "&search=true&wsNo="+wsNo;
                    modelMap.put("queryWsNo", wsNo);
                    modelMap.put("querySearch", querySearch);
                }
            }
            else {
                fishWsDataList = dao.findAllAndPaging(paging, offset);
            }

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
		int wsId = Integer.valueOf(request.getParameter("id"));
        FishWsDao dao = DaoFactory.createFishWsDao();
        dao.delete(wsId);
        
        FishWsDetailDao detailDao = DaoFactory.createFishWsDetailDao();
        detailDao.deleteAllByWsId(wsId);
        
        HashMap<String, Object> modelMap = this.searchAndPaging(request, response);
		return new ModelAndView("fish/WSDataList", "model", modelMap);
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
        	Date dateShift = df.parse(request.getParameter("dateShift"));
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
        	
        	int totalData = Integer.valueOf(request.getParameter("totalData"));
        	for(int i=1; i<=totalData; i++) {
        		Integer fishId = Integer.valueOf(request.getParameter("fishId"+i));
            	Double totalWeight = Double.valueOf(request.getParameter("totalWeight"+i));
            	
            	FishWsDetail wsDetail = new FishWsDetail();
            	wsDetail.setWsId(dto.getId());
            	wsDetail.setFishId(fishId);
            	wsDetail.setTotalWeight(totalWeight);
            	wsDetail.setCreatedDate(new Date());
            	wsDetail.setCreatedBy(userId);
            	wsDetail.setIsActive("Y");
            	wsDetail.setIsDelete("N");
            	
            	FishWsDetailDao wsDetailDao = DaoFactory.createFishWsDetailDao();
            	int wsid = wsDetailDao.insert(wsDetail);
                
                if(wsid > 0) {
                    FishWsTypeDao wsTypeDao = DaoFactory.createFishWsTypeDao();
                    FishWSType wsTypesFrz = wsTypeDao.findTypeCodeById(wsTypeId.intValue());
                    String wsTypeFrozen = wsTypesFrz.getCode();
                    FishBalanceDao fishBalanceDao = DaoFactory.createFishBalanceDao();
                    FishBalance fishBalance = fishBalanceDao.findUniqueFishBalance(vesselId, dto.getStorageId(), 
                        wsDetail.getFishId());
                    
                    if (wsTypeFrozen.equalsIgnoreCase("WSBF") || wsTypeFrozen.equalsIgnoreCase("WSABF") || 
                        wsTypeFrozen.equalsIgnoreCase("WSNC")) {   
                        if(fishBalance != null) {
                            Double balance = fishBalance.getBalance();
                            fishBalance.setBalance(balance + wsDetail.getTotalWeight());
                            fishBalance.setUpdatedDate(new Date());
                            fishBalance.setUpdatedBy(user.getUserId());
                            fishBalanceDao.update(fishBalance.getId(), fishBalance);
                        } else {
                            fishBalance = new FishBalance();
                            fishBalance.setVesselId(vesselId);
                            fishBalance.setStorageId(dto.getStorageId());
                            fishBalance.setFishId(wsDetail.getFishId());
                            fishBalance.setBalance(wsDetail.getTotalWeight());
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
                        fishBalanceHistory.setDocNo(wsNo);
                        fishBalanceHistory.setBatchNo(fishBalance.getVessel().getBatchNo());
                        fishBalanceHistory.setFishType(fishBalance.getFish().getCode());
                        fishBalanceHistory.setStorage(fishBalance.getStorageId() == 0 ?
                                        "FROZEN" : fishBalance.getStorage().getCode());
                        fishBalanceHistory.setQtyIn(wsDetail.getTotalWeight());
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
            }
        	
        	modelMap = this.searchAndPaging(request, response);
    		return new ModelAndView("fish/WSDataList", "model", modelMap);
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
