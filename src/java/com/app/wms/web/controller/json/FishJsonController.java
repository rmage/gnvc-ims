package com.app.wms.web.controller.json;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.richfaces.json.JSONArray;
import org.richfaces.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.app.wms.engine.db.dao.FishBalanceDao;
import com.app.wms.engine.db.dao.FishSupplierDao;
import com.app.wms.engine.db.dao.FishVesselDao;
import com.app.wms.engine.db.dao.FishWdsDao;
import com.app.wms.engine.db.dao.FishWdsDetailDao;
import com.app.wms.engine.db.dao.FishWsDao;
import com.app.wms.engine.db.dao.FishWsDetailDao;
import com.app.wms.engine.db.dto.FishBalance;
import com.app.wms.engine.db.dto.FishSupplier;
import com.app.wms.engine.db.dto.FishVessel;
import com.app.wms.engine.db.dto.FishWds;
import com.app.wms.engine.db.dto.FishWdsDetail;
import com.app.wms.engine.db.dto.FishWs;
import com.app.wms.engine.db.dto.FishWsDetail;
import com.app.wms.engine.db.factory.DaoFactory;

public class FishJsonController extends MultiActionController {
	
	private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		return new ModelAndView("fish/FishJsonView");
	}
	
	public ModelAndView findBatchNumber(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		String query = request.getParameter("query");
		FishVesselDao dao = DaoFactory.createFishVesselDao();
		List<FishVessel> fishVesselList = dao.findByVesselName(query);
		
		JSONArray jsonArray = new JSONArray();		
		for (FishVessel fishVessel : fishVesselList) {
			JSONObject dataObject = new JSONObject();
			dataObject.put("vesselId", fishVessel.getId());
			dataObject.put("batchNo", fishVessel.getBatchNo());
			dataObject.put("vesselName", fishVessel.getName());
			dataObject.put("supplierName", fishVessel.getSupplier().getName());
			jsonArray.put(dataObject);
		}

		int totalPage = countPage(fishVesselList);
		int totalRecords = fishVesselList.size();
		String data = compileJson(jsonArray, totalPage, totalRecords);
		
		HashMap<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("data", data);
		
		return new ModelAndView("fish/FishJsonView", "model", modelMap);
	}
	
	public ModelAndView findFishSupplier(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		String query = request.getParameter("query");
		FishSupplierDao dao = DaoFactory.createFishSupplierDao();
		List<FishSupplier> fishSupplierList = dao.findBySupplierName(query);
		
		JSONArray jsonArray = new JSONArray();
		for (FishSupplier fishSupplier : fishSupplierList) {
			JSONObject dataObject = new JSONObject();
			dataObject.put("id", fishSupplier.getId());
			dataObject.put("code", fishSupplier.getCode());
			dataObject.put("name", fishSupplier.getName());
			jsonArray.put(dataObject);
		}
		
		int totalPage = countPage(fishSupplierList);
		int totalRecords = fishSupplierList.size();
		
		String data = compileJson(jsonArray, totalPage, totalRecords);
		HashMap<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("data", data);
		
		return new ModelAndView("fish/FishJsonView", "model", modelMap);
	}
	
	public ModelAndView findWsData(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		Integer vesselId = Integer.valueOf(request.getParameter("vesselId"));
		Date dateShift = df.parse(request.getParameter("dateShift"));
		
		FishWsDao dao = DaoFactory.createFishWsDao();
		List<FishWs> wsDataList = dao.findByVesselIdAndDateShift(vesselId, dateShift);
		
		JSONArray jsonArray = new JSONArray();
		for (FishWs fishWs : wsDataList) {
			JSONObject dataObject = new JSONObject();
			dataObject.put("wsId", fishWs.getId());
			dataObject.put("wsNo", fishWs.getWsNo());
			dataObject.put("wsType", fishWs.getWsType().getCode());
			dataObject.put("supplierName", fishWs.getVessel().getSupplier().getName());
			jsonArray.put(dataObject);
		}
		
		int totalPage = countPage(wsDataList);
		int totalRecords = wsDataList.size();
		
		String data = compileJson(jsonArray, totalPage, totalRecords);
		HashMap<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("data", data);
		
		return new ModelAndView("fish/FishJsonView", "model", modelMap);
	}
	
	public ModelAndView findFishStockByVesselId(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		Integer vesselId = Integer.valueOf(request.getParameter("vesselId"));
		FishBalanceDao fishBalanceDao = DaoFactory.createFishBalanceDao();
		List<FishBalance> fishBalanceList = fishBalanceDao.findBalanceByVesselId(vesselId);
		
		JSONArray jsonArray = new JSONArray();
		for (FishBalance fishBalance : fishBalanceList) {
			JSONObject dataObject = new JSONObject();
			dataObject.put("fishId", fishBalance.getFishId());
			dataObject.put("storageId", fishBalance.getStorageId());
			dataObject.put("storageName", 
					fishBalance.getStorageId() == 0 ? "-" : fishBalance.getStorage().getCode());
			dataObject.put("fishCode", fishBalance.getFish().getCode());
			dataObject.put("balance", fishBalance.getBalance());
			dataObject.put("fishDesc", fishBalance.getFish().getFishType().getDescription() +
					" " + fishBalance.getFish().getFishWeightType().getDescription());
			
			jsonArray.put(dataObject);
		}
		
		int totalPage = countPage(fishBalanceList);
		int totalRecords = fishBalanceList.size();
		
		String data = compileJson(jsonArray, totalPage, totalRecords);
		HashMap<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("data", data);
		
		return new ModelAndView("fish/FishJsonView", "model", modelMap);
	}
	
	public ModelAndView findWsDetailData(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		String wsDetailIds = request.getParameter("wsDetailIds");
		FishWsDetailDao wsDetailDao = DaoFactory.createFishWsDetailDao();
		List<FishWsDetail> wsDetailList = wsDetailDao.findByWsDetailIds(wsDetailIds);
		
		JSONArray jsonArray = new JSONArray();
		for (FishWsDetail fishWsDetail : wsDetailList) {
			JSONObject dataObject = new JSONObject();
			dataObject.put("id", fishWsDetail.getId());
			dataObject.put("wsId", fishWsDetail.getWsId());
			dataObject.put("fishId", fishWsDetail.getFishId());
			dataObject.put("storageId", fishWsDetail.getWeightSlip().getStorageId());
			dataObject.put("wsNo", fishWsDetail.getWeightSlip().getWsNo());
			dataObject.put("fishType", fishWsDetail.getFish().getCode());
			dataObject.put("totalWeight", fishWsDetail.getTotalWeight());
			
			jsonArray.put(dataObject);
		}
		
		JSONObject jsonPage = new JSONObject();
		jsonPage.put("wsDetails", jsonArray);
		
		String data = jsonPage.toString();
		HashMap<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("data", data);
		
		return new ModelAndView("fish/FishJsonView", "model", modelMap);
	}
	
	public ModelAndView findWdsData(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		String query = request.getParameter("query");
		FishWdsDao dao = DaoFactory.createFishWdsDao();
		List<FishWds> wdsList = dao.findAllByWdsNo(query);
		
		JSONArray jsonArray = new JSONArray();
		for (FishWds fishWds : wdsList) {
			JSONObject dataObject = new JSONObject();
			dataObject.put("wdsId", fishWds.getId());
			dataObject.put("vesselId", fishWds.getVesselId());
			dataObject.put("wdsNo", fishWds.getWdsNo());
			dataObject.put("batchNo", fishWds.getVessel().getBatchNo());
			dataObject.put("requestedBy", fishWds.getRequestedBy());
			
			jsonArray.put(dataObject);
		}
		
		int totalPage = countPage(wdsList);
		int totalRecords = wdsList.size();
		
		String data = compileJson(jsonArray, totalPage, totalRecords);
		HashMap<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("data", data);
		
		return new ModelAndView("fish/FishJsonView", "model", modelMap);
	}
	
	public ModelAndView findWdsDetailData(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		Integer wdsId = Integer.valueOf(request.getParameter("wdsId"));
		FishWdsDetailDao wdsDetailDao = DaoFactory.createFishWdsDetailDao();
		List<FishWdsDetail> wdsDetailList = wdsDetailDao.findAllByWdsId(wdsId);
		
		JSONArray jsonArray = new JSONArray();
		for (FishWdsDetail fishWdsDetail : wdsDetailList) {
			JSONObject dataObject = new JSONObject();
			dataObject.put("fishId", fishWdsDetail.getFishId());
			dataObject.put("fishCode", fishWdsDetail.getFish().getCode());
			dataObject.put("description", fishWdsDetail.getDescription());
			dataObject.put("storageId", fishWdsDetail.getStorageId());
			dataObject.put("storageName", fishWdsDetail.getStorageId() == 0 ? 
					"-" : fishWdsDetail.getStorage().getCode());
			dataObject.put("qty", fishWdsDetail.getQuantity());
			dataObject.put("uomCode", fishWdsDetail.getUomCode());
			
			jsonArray.put(dataObject);
		}
		
		JSONObject jsonPage = new JSONObject();
		jsonPage.put("wdsDetails", jsonArray);
		
		String data = jsonPage.toString();
		HashMap<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("data", data);
		
		return new ModelAndView("fish/FishJsonView", "model", modelMap);
	}
	
	private int countPage(List<?> data) {
		return data.size() > 10 ? data.size() / 10 : 1;
	}
	
	private String compileJson(JSONArray data, int totalPage, int totalRecords) throws Exception {
		JSONObject jsonPage = new JSONObject();
		
		jsonPage.put("page", 1);
		jsonPage.put("total", totalPage);
		jsonPage.put("records", totalRecords);
		jsonPage.put("rows", data);
		
		return jsonPage.toString();
	}
}
