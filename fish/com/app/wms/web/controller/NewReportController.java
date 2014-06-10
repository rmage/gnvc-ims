package com.app.wms.web.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.app.wms.engine.db.dao.FishBadStockDetailDao;
import com.app.wms.engine.db.dao.FishRrDetailDao;
import com.app.wms.engine.db.dao.FishSpoilageDao;
import com.app.wms.engine.db.dao.FishTsDetailDao;
import com.app.wms.engine.db.dao.FishWdsDetailDao;
import com.app.wms.engine.db.dao.FishWsDetailDao;
import com.app.wms.engine.db.dao.FishWsTypeDao;
import com.app.wms.engine.db.dao.ProductCategoryDao;
import com.app.wms.engine.db.dao.UserDao;
import com.app.wms.engine.db.dto.FishBadStockDetail;
import com.app.wms.engine.db.dto.FishRrDetail;
import com.app.wms.engine.db.dto.FishSpoilage;
import com.app.wms.engine.db.dto.FishTsDetail;
import com.app.wms.engine.db.dto.FishWSType;
import com.app.wms.engine.db.dto.FishWdsDetail;
import com.app.wms.engine.db.dto.FishWsDetail;
import com.app.wms.engine.db.dto.ProductCategory;
import com.app.wms.engine.db.dto.User;
import com.app.wms.engine.db.exceptions.ProductCategoryDaoException;
import com.app.wms.engine.db.exceptions.UserDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import com.report.test.ReportModel;
import java.util.ArrayList;

public class NewReportController extends MultiActionController {

	private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	public ModelAndView getWsReportById(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		Integer wsId = Integer.valueOf(request.getParameter("wsId"));
		String format = request.getParameter("format");
		String typeCode = request.getParameter("wsType").toLowerCase();
        
        FishWsTypeDao wsTypeDao = DaoFactory.createFishWsTypeDao();
        FishWSType wsType = wsTypeDao.findByTypeCode(typeCode);
		
		FishWsDetailDao wsDetailDao = DaoFactory.createFishWsDetailDao();
		List<FishWsDetail> wsDetails = wsDetailDao.findByWsIdGroupByFish(wsId);
		FishWsDetail dto = wsDetails.get(0);
		Map<String, Object> info =  new HashMap<String, Object>();
        
        info.put("title", wsType.getDescription());
		info.put("supplier", dto.getWeightSlip().getVessel().getSupplier().getName());
		info.put("batch", dto.getWeightSlip().getVessel().getBatchNo());
		info.put("timeShift", dto.getWeightSlip().getTimeShift());
		info.put("dateShift", df.format(dto.getWeightSlip().getDateShift()));
		info.put("regu", dto.getWeightSlip().getRegu());
		info.put("no", dto.getWeightSlip().getWsNo());
		
		byte[] data = null;
		String filename = typeCode + dto.getWeightSlip().getWsNo();
		
		if(format.equalsIgnoreCase("pdf")) {
			data = new ReportModel("wsgeneral", false).getReportPDF(wsDetails, info);
			response.setHeader("Content-disposition", "attachment; filename="+filename+".pdf");
		}
		else if(format.equalsIgnoreCase("xls")) {
			data = new ReportModel("wsgeneral", false).getReportXLS(wsDetails, info);
			response.setHeader("Content-disposition", "attachment; filename="+filename+".xls");	
		}
		else if(format.equalsIgnoreCase("csv")) {
			data = new ReportModel("wsgeneral", false).getReportCSV(wsDetails, info);
			response.setHeader("Content-disposition", "attachment; filename="+filename+".csv");	
		}
		
		response.setContentLength(data.length);
		response.getOutputStream().write(data);
		
		return null;
	}
	
	public ModelAndView getRrReportById(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		Integer rrId = Integer.valueOf(request.getParameter("rrId"));
		String format = request.getParameter("format");
		
		FishRrDetailDao dao = DaoFactory.createFishRrDetailDao();
		List<FishRrDetail> rrDetails = dao.findByRrIdGroupByFish(rrId);
		FishRrDetail dto = rrDetails.get(0);
		
		Set<String> wsNumbers = new HashSet<String>();
        Double totalSpoilage = Double.valueOf(0);
		for (FishRrDetail rrDetail : rrDetails) {
            totalSpoilage += rrDetail.getSpoilageWeight();
			wsNumbers.add(rrDetail.getWeightSlip().getWsNo());
		}
		
		String wsNumberString = "";
		for (String wsNumber : wsNumbers) {
			wsNumberString += wsNumber + ",";
		}
		wsNumberString = wsNumberString.substring(0, wsNumberString.length() - 1);
		
		Map<String, Object> info =  new HashMap<String, Object>();
		String supplierName = dto.getReceivingReport().getVessel().getSupplier().getName();
		String batchNo = dto.getReceivingReport().getVessel().getBatchNo();
		String from = supplierName + "/" + batchNo;
		info.put("from", from);
		info.put("wsno", wsNumberString);
		info.put("batchno", dto.getWeightSlip().getVessel().getBatchNo());
		info.put("no", dto.getReceivingReport().getRrNo());
		info.put("date", df.format(dto.getReceivingReport().getRrDate()));
        info.put("totalspoilage", totalSpoilage);
		
		byte[] data = null;
		
		if(format.equalsIgnoreCase("pdf")) {
			data = new ReportModel("fish_receivingReport", false).getReportPDF(rrDetails, info);
			response.setHeader("Content-disposition", "attachment; filename=receiving"+dto.getReceivingReport().getRrNo()+".pdf");
		}
		else if(format.equalsIgnoreCase("xls")) {
			data = new ReportModel("fish_receivingReport", false).getReportXLS(rrDetails, info);
			response.setHeader("Content-disposition", "attachment; filename=receiving"+dto.getReceivingReport().getRrNo()+".xls");	
		}
		else if(format.equalsIgnoreCase("csv")) {
			data = new ReportModel("fish_receivingReport", false).getReportCSV(rrDetails, info);
			response.setHeader("Content-disposition", "attachment; filename=receiving"+dto.getReceivingReport().getRrNo()+".csv");	
		}
		
		response.setContentLength(data.length);
		response.getOutputStream().write(data);
		
		return null;
	}
	
	public ModelAndView getWdsReportById(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		int wdsId = Integer.valueOf(request.getParameter("wdsId"));
		String format = request.getParameter("format");
		
		FishWdsDetailDao dao = DaoFactory.createFishWdsDetailDao();
		List<FishWdsDetail> wdsDetails = dao.findAllByWdsId(wdsId);
		
		FishWdsDetail dto = wdsDetails.get(0);
		Map<String, Object> info =  new HashMap<String, Object>();
		info.put("supplier", dto.getWithdrawalSlip().getVessel().getSupplier().getName());
		info.put("date", df.format(dto.getWithdrawalSlip().getWdsDate()));
        info.put("batchno", dto.getWithdrawalSlip().getVessel().getBatchNo());
        info.put("wdsno", dto.getWithdrawalSlip().getWdsNo());
		
		byte[] data = null;
		
		if(format.equalsIgnoreCase("pdf")) {
			data = new ReportModel("fish_withdrawalSlip", false).getReportPDF(wdsDetails, info);
			response.setHeader("Content-disposition", "attachment; filename=withdrawal-slip-"+dto.getWithdrawalSlip().getWdsNo()+".pdf");
		}
		else if(format.equalsIgnoreCase("xls")) {
			data = new ReportModel("fish_withdrawalSlip", false).getReportXLS(wdsDetails, info);
			response.setHeader("Content-disposition", "attachment; filename=withdrawal-slip-"+dto.getWithdrawalSlip().getWdsNo()+".xls");	
		}
		else if(format.equalsIgnoreCase("csv")) {
			data = new ReportModel("fish_withdrawalSlip", false).getReportCSV(wdsDetails, info);
			response.setHeader("Content-disposition", "attachment; filename=withdrawal-slip-"+dto.getWithdrawalSlip().getWdsNo()+".csv");	
		}
		
		response.setContentLength(data.length);
		response.getOutputStream().write(data);
		
		return null;
	}
	
	public ModelAndView getTsReportById(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		int tsId = Integer.valueOf(request.getParameter("tsId"));
		String format = request.getParameter("format");
		
		FishTsDetailDao dao = DaoFactory.createFishTsDetailDao();
		List<FishTsDetail> tsDetails = dao.findAllByTsId(tsId);
		FishTsDetail dto = tsDetails.get(0);
		
		Map<String, Object> info =  new HashMap<String, Object>();
		info.put("no", dto.getTransferSlip().getTsNo());
		info.put("date", df.format(dto.getTransferSlip().getTsDate()));
		info.put("issuedBy", dto.getTransferSlip().getIssuedBy());
		info.put("notedBy", dto.getTransferSlip().getNotedBy());
		info.put("approvedBy", dto.getTransferSlip().getApprovedBy());
		info.put("receivedBy", dto.getTransferSlip().getReceivedBy());
		info.put("wdsno", dto.getTransferSlip().getWithdrawalSlip().getWdsNo());
		
		byte[] data = null;
		
		if(format.equalsIgnoreCase("pdf")) {
			data = new ReportModel("fish_transferSlip", false).getReportPDF(tsDetails, info);
			response.setHeader("Content-disposition", "attachment; filename=transfer-slip-"+dto.getTransferSlip().getTsNo()+".pdf");
		}
		else if(format.equalsIgnoreCase("xls")) {
			data = new ReportModel("fish_transferSlip", false).getReportXLS(tsDetails, info);
			response.setHeader("Content-disposition", "attachment; filename=transfer-slip-"+dto.getTransferSlip().getTsNo()+".xls");	
		}
		else if(format.equalsIgnoreCase("csv")) {
			data = new ReportModel("fish_transferSlip", false).getReportCSV(tsDetails, info);
			response.setHeader("Content-disposition", "attachment; filename=transfer-slip-"+dto.getTransferSlip().getTsNo()+".csv");	
		}
		
		response.setContentLength(data.length);
		response.getOutputStream().write(data);
		
		
		return null;
	}
	
	public ModelAndView getBsReportById(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		int bsId = Integer.valueOf(request.getParameter("bsId"));
		String format = request.getParameter("format");
		
		FishBadStockDetailDao dao = DaoFactory.createFishBadStockDetailDao();
		List<FishBadStockDetail> bsDetails = dao.findAllByBsId(bsId);
		FishBadStockDetail dto = bsDetails.get(0);
		
		Map<String, Object> info =  new HashMap<String, Object>();
		info.put("no", dto.getBadstockSlip().getBsNo());
		info.put("date", df.format(dto.getBadstockSlip().getBsDate()));
		info.put("issuedBy", dto.getBadstockSlip().getIssuedBy());
		info.put("notedBy", dto.getBadstockSlip().getNotedBy());
		info.put("approvedBy", dto.getBadstockSlip().getApprovedBy());
		info.put("receivedBy", dto.getBadstockSlip().getReceivedBy());
		
		byte[] data = null;
		
		if(format.equalsIgnoreCase("pdf")) {
			data = new ReportModel("fish_badStockSlip", false).getReportPDF(bsDetails, info);
			response.setHeader("Content-disposition", "attachment; filename=badstock-slip-"+dto.getBadstockSlip().getBsNo()+".pdf");
		}
		else if(format.equalsIgnoreCase("xls")) {
			data = new ReportModel("fish_badStockSlip", false).getReportXLS(bsDetails, info);
			response.setHeader("Content-disposition", "attachment; filename=badstock-slip-"+dto.getBadstockSlip().getBsNo()+".xls");	
		}
		else if(format.equalsIgnoreCase("csv")) {
			data = new ReportModel("fish_badStockSlip", false).getReportCSV(bsDetails, info);
			response.setHeader("Content-disposition", "attachment; filename=badstock-slip-"+dto.getBadstockSlip().getBsNo()+".csv");	
		}
		
		response.setContentLength(data.length);
		response.getOutputStream().write(data);
		
		
		return null;
	}
	
	public ModelAndView getFishSpoilageReport(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		int vesselId = Integer.valueOf(request.getParameter("vesselId"));
		Date dateShift = Date.valueOf(request.getParameter("dateShift"));
		String timeShift = request.getParameter("timeShift");
		String format = request.getParameter("format");
		
		FishSpoilageDao dao = DaoFactory.createFishSpoilageDao();
		List<FishSpoilage> fishSpoilages = dao.findAllForReport(vesselId, dateShift, timeShift);
		FishSpoilage dto = fishSpoilages.get(0);
		
		Map<String, Object> info =  new HashMap<String, Object>();
		info.put("batchno", dto.getVessel().getBatchNo());
		info.put("dateshift", df.format(dto.getDateShift()));
		info.put("timeshift", dto.getTimeShift());
		
		byte[] data = null;
		
		if(format.equalsIgnoreCase("pdf")) {
			data = new ReportModel("spoilageReport", false).getReportPDF(fishSpoilages, info);
			response.setHeader("Content-disposition", "attachment; filename=fishSpoilage.pdf");
		}
		else if(format.equalsIgnoreCase("xls")) {
			data = new ReportModel("spoilageReport", false).getReportXLS(fishSpoilages, info);
			response.setHeader("Content-disposition", "attachment; filename=fishSpoilage.xls");	
		}
		else if(format.equalsIgnoreCase("csv")) {
			data = new ReportModel("spoilageReport", false).getReportCSV(fishSpoilages, info);
			response.setHeader("Content-disposition", "attachment; filename=fishSpoilage.csv");	
		}
		
		response.setContentLength(data.length);
		response.getOutputStream().write(data);
		
		return null;
	}
    
    public ModelAndView getWsSummaryReport(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        return new ModelAndView("fish/WSSummaryReport");
    }
	
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		String item = request.getParameter("item");
		String params = request.getParameter("params");
		HashMap<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("item", item);
		modelMap.put("params", params);
                if(item.equalsIgnoreCase("custom"))
                    return new ModelAndView("fish/GeneralReportCustom", "model", modelMap);
                return new ModelAndView("fish/GeneralReport", "model", modelMap);
	}
        
    public ModelAndView getPoNotYetDeliveredCash(HttpServletRequest request, HttpServletResponse response) 
        throws UserDaoException {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();

        /* DAO | Define needed dao here */
        UserDao userDao = DaoFactory.createUserDao();

        /* TRANSACTION | Something complex here */
        List<User> us = userDao.findRoleCanvasser();
        m.put("u", us);
        
        return new ModelAndView("fish/PoNotYetDeliveredCash", "model", m);
    }
    
    public ModelAndView getPoNotYetDeliveredCredit(HttpServletRequest request, HttpServletResponse response) 
        throws UserDaoException {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();

        /* DAO | Define needed dao here */
        UserDao userDao = DaoFactory.createUserDao();

        /* TRANSACTION | Something complex here */
        List<User> us = userDao.findRoleCanvasser();
        m.put("u", us);
        
        return new ModelAndView("fish/PoNotYetDeliveredCredit", "model", m);
    }
    
    public ModelAndView getPrsNotyetPO(HttpServletRequest request, HttpServletResponse response) 
        throws UserDaoException {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();

        /* DAO | Define needed dao here */
        UserDao userDao = DaoFactory.createUserDao();

        /* TRANSACTION | Something complex here */
        List<User> us = userDao.findRoleCanvasser();
        m.put("u", us);
        
        return new ModelAndView("fish/PrsNotyetPO", "model", m);
    }
    
    public ModelAndView getPoRegisterPerPeriode(HttpServletRequest request, HttpServletResponse response) {
        
        /* DATA | get initial value */

        /* DAO | Define needed dao here */

        /* TRANSACTION | Something complex here */
        
        return new ModelAndView("fish/PoRegisterPerPeriode");
        
    }
    
    public ModelAndView getPoConfirmatory(HttpServletRequest request, HttpServletResponse response) {
        
        /* DATA | get initial value */

        /* DAO | Define needed dao here */

        /* TRANSACTION | Something complex here */
        
        return new ModelAndView("fish/PoConfirmatory");
        
    }
    
    public ModelAndView getPoPerDepartment(HttpServletRequest request, HttpServletResponse response) {
        
        /* DATA | get initial value */

        /* DAO | Define needed dao here */

        /* TRANSACTION | Something complex here */
        
        return new ModelAndView("fish/PoPerDepartment");
        
    }
            
    public ModelAndView getPoIssuedPerSupplier(HttpServletRequest request, HttpServletResponse response) {
        
        /* DATA | get initial value */

        /* DAO | Define needed dao here */

        /* TRANSACTION | Something complex here */
        
        return new ModelAndView("fish/PoIssuedPerSupplier");
        
    }
    
    public ModelAndView getIMStockCardperCategory(HttpServletRequest request, HttpServletResponse response) 
        throws ProductCategoryDaoException {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();
        List<ProductCategory> pcs = new ArrayList<ProductCategory>();

        /* DAO | Define needed dao here */
        ProductCategoryDao productCategoryDao = DaoFactory.createProductCategoryDao();

        /* TRANSACTION | Something complex here */
        pcs = productCategoryDao.findAll();
        m.put("pc", pcs);
        
        return new ModelAndView("fish/IMStockCardperCategory", "model", m);
        
    }
    
    public ModelAndView getIMStockCardTransactionReport(HttpServletRequest request, HttpServletResponse response) 
        throws ProductCategoryDaoException {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();
        List<ProductCategory> pcs = new ArrayList<ProductCategory>();

        /* DAO | Define needed dao here */
        ProductCategoryDao productCategoryDao = DaoFactory.createProductCategoryDao();

        /* TRANSACTION | Something complex here */
        pcs = productCategoryDao.findAll();
        m.put("pc", pcs);
        
        return new ModelAndView("fish/IMStockCardTransactionReport", "model", m);
        
    }
    
    public ModelAndView getRRPeriode(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("non_fish/ReportRRPeriode");
    }
    
    public ModelAndView getPurchasedItems(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("non_fish/ReportPurchasedItems");
    }
    
    public ModelAndView getPurchasedPerSupplier(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("non_fish/ReportPurchasedPerSupplier");
    }
    
    public ModelAndView getTSPeriode(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("non_fish/ReportTSPeriode");
    }
    
}
