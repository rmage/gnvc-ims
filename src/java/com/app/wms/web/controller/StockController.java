package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.*;
import com.app.wms.web.helper.StringHelper;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.*;
import java.math.*;
import java.util.Date;
import java.util.List;

import com.app.wms.engine.db.dao.WhDao;
import com.app.wms.engine.db.dto.*;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.dvo.StockWhView;
import com.app.wms.engine.db.factory.*;
import com.app.wms.web.util.AppConstant;
import java.util.ArrayList;
import java.util.HashMap;

public class StockController extends MultiActionController {

    public ModelAndView detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
	try {
	    LoginUser loginUser = (LoginUser) request.getSession().getAttribute("user");
	    java.lang.String pstockCode = request.getParameter("stockCode");

	    // create the DAO class
	    StockDao dao = DaoFactory.createStockDao();


	    HashMap maps = new HashMap();
	    return new ModelAndView("13_stock/StockDtlList", "model", maps);
	} catch (Throwable e) {
	    e.printStackTrace();
	    return new ModelAndView("Error", "th", e);
	}
    }

    /**
     * Method 'findByPrimaryKey'
     *
     * @param request
     * @param response
     * @throws Exception
     * @return ModelAndView
     */
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
	try {
	    LoginUser loginUser = (LoginUser) request.getSession().getAttribute("user");
	    // parse parameters
	    java.lang.String pbgCode = request.getParameter("bgCode");
	    java.lang.String pitemCode = request.getParameter("itemCode");
	    java.lang.String pwhCode = request.getParameter("whCode");
	    java.lang.String pstockCode = request.getParameter("stockCode");

	    // create the DAO class
	    StockDao dao = DaoFactory.createStockDao();

	    // execute the finder
	    Stock dto = dao.findByPrimaryKey(pstockCode);

	    final String mode = request.getParameter("mode");
	    if (mode != null && mode.equals("edit")) {
		return new ModelAndView("StockEdit", "dto", dto);
	    } else {
		java.util.List<Stock> stockList = new java.util.ArrayList<Stock>();
		stockList = dao.findByUserIsCurrent(new BigDecimal(loginUser.getUserId()));
		HashMap maps = new HashMap();
		maps.put("stocks", stockList);
		return new ModelAndView("13_stock/StockList", "model", maps);
	    }

	} catch (Throwable e) {
	    e.printStackTrace();
	    return new ModelAndView("Error", "th", e);
	}

    }

    /**
     * Method 'create'
     *
     * @param request
     * @param response
     * @throws Exception
     * @return ModelAndView
     */
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws Exception {
	Stock dto = new Stock();
	return new ModelAndView("StockEdit", "dto", dto);
    }

    /**
     * Method 'save'
     *
     * @param request
     * @param response
     * @throws Exception
     * @return ModelAndView
     */
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws Exception {
	java.lang.String pstockCode = request.getParameter("stockCode");
	java.lang.String pbgCode = request.getParameter("bgCode");
//		java.lang.String pitemCode = request.getParameter("itemCode");
//		java.lang.String pwhCode = request.getParameter("whCode");
//		java.math.BigDecimal pbalance = new BigDecimal( request.getParameter("balance") );
//		java.math.BigDecimal pbalanceMinim = new BigDecimal( request.getParameter("balanceMinim") );
	java.util.Date pstartDate = (request.getParameter("startDate") == null || request.getParameter("startDate").trim().length() == 0) ? null : DateFormat.getDateTimeInstance().parse(request.getParameter("startDate"));
	java.util.Date pendDate = (request.getParameter("endDate") == null || request.getParameter("endDate").trim().length() == 0) ? null : DateFormat.getDateTimeInstance().parse(request.getParameter("endDate"));
	java.lang.String pisCurrent = request.getParameter("isCurrent");
	java.math.BigDecimal pcreatedBy = new BigDecimal(request.getParameter("createdBy"));
	java.util.Date pcreatedDate = (request.getParameter("createdDate") == null || request.getParameter("createdDate").trim().length() == 0) ? null : DateFormat.getDateTimeInstance().parse(request.getParameter("createdDate"));
	java.math.BigDecimal pupdatedBy = new BigDecimal(request.getParameter("updatedBy"));
	java.util.Date pupdatedDate = (request.getParameter("updatedDate") == null || request.getParameter("updatedDate").trim().length() == 0) ? null : DateFormat.getDateTimeInstance().parse(request.getParameter("updatedDate"));

	// create the DAO class
	StockDao dao = DaoFactory.createStockDao();

	Stock dto = dao.findByPrimaryKey(pstockCode);
	boolean isCreate = dto == null;
	if (isCreate) {
	    dto = new Stock();
	}

	dto.setStockCode(pstockCode);
	dto.setBgCode(pbgCode);
	dto.setStartDate(pstartDate);
	dto.setEndDate(pendDate);
	dto.setIsCurrent(pisCurrent);
	dto.setCreatedBy(pcreatedBy);
	dto.setCreatedDate(pcreatedDate);
	dto.setUpdatedBy(pupdatedBy);
	dto.setUpdatedDate(pupdatedDate);
	if (isCreate) {
	    dao.insert(dto);
	} else {
	    dao.update(dto.createPk(), dto);
	}

	return new ModelAndView("StockView", "dto", dto);
    }

    public ModelAndView listStockWh(HttpServletRequest request, HttpServletResponse response) throws Exception {
	LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
	String bg = "";
	String wh = "";
	String itemCode = "";
	String itemName = "";

	if (lu != null) {
	    if (request.getParameter("btnList") != null) {
		// search
		wh = request.getParameter("wh");
		bg = request.getParameter("bg");
		itemCode = request.getParameter("itemCode");
		itemName = request.getParameter("itemName");
		if (itemCode == null) {
		    itemCode = "";
		}
		if (itemName == null) {
		    itemName = "";
		}

//            stockWhListByBg = swDao.findByBgWhProduct(bg, eventCode, itemCode, sn); //swDao.findByBgWh(bg, eventCode);
//            request.getSession().setAttribute("searchlistStockWh_wh", eventCode);
//            request.getSession().setAttribute("searchlistStockWh_bg", bg);
//            request.getSession().setAttribute("searchlistStockWh_itemCode", itemCode);
//            request.getSession().setAttribute("searchlistStockWh_itemName", sn);

	    } else {
		// start
		wh = (String) request.getSession().getAttribute("searchlistStockWh_wh");
		bg = (String) request.getSession().getAttribute("searchlistStockWh_bg");
		itemCode = (String) request.getSession().getAttribute("searchlistStockWh_itemCode");
		itemName = (String) request.getSession().getAttribute("searchlistStockWh_itemName");

		if (wh == null) {
		    wh = "";
		    bg = "";
		    itemCode = "";
		    itemName = "";
		}
	    }
	}

	request.getSession().setAttribute("searchlistStockWh_wh", wh);
	request.getSession().setAttribute("searchlistStockWh_bg", bg);
	request.getSession().setAttribute("searchlistStockWh_itemCode", itemCode);
	request.getSession().setAttribute("searchlistStockWh_itemName", itemName);

//        if (request.getParameter("bg") != null) {
//        }

	HashMap m = new HashMap();
	m.put("bg", bg);
	m.put("wh", wh);
	m.put("itemCode", itemCode);
	m.put("itemName", itemName);

	return new ModelAndView("13_stock_wh/StockWhList", "model", m);
    }

    public ModelAndView detailStockWh(HttpServletRequest request, HttpServletResponse response) throws Exception {
	String whCode = request.getParameter("whCode");
	String stockCode = request.getParameter("stockCode");
	String itemCode = request.getParameter("itemCode");
	String catalogCode = request.getParameter("catalogCode");        

	HashMap m = new HashMap();
	m.put("whCode", whCode);
	m.put("stockCode", stockCode);
	m.put("itemCode", itemCode);
	m.put("catalogCode", catalogCode);

	if (catalogCode.equalsIgnoreCase("S")) {


	    return new ModelAndView("13_stock_wh/StockRangeList", "model", m);
	} else { // NS
	    Integer page = null;
	    Integer paging = null;
	    if (request.getParameter("page") != null) {
		page = Integer.parseInt(request.getParameter("page"));
	    }
	    if (request.getParameter("paging") != null) {
		paging = Integer.parseInt(request.getParameter("paging"));
	    }
	    if (page == null) {
		page = 1;
	    }
	    if (paging == null) {
		paging = 10;
	    }
	    int start = (page - 1) * paging + 1;
	    int end = start + paging - 1;


	    return new ModelAndView("13_stock_wh/StockIdentList", "model", m);
	}

    }

    public ModelAndView listStockSn(HttpServletRequest request, HttpServletResponse response) throws Exception {
	LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
	String bg = "";
	String wh = "";
	String itemCode = "";
	String itemName = "";
	String msisdn = "";

	Integer page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
	Integer paging = request.getParameter("paging") != null ? Integer.parseInt(request.getParameter("paging")) : AppConstant.paging;

	WhDao whDao = DaoFactory.createWhDao();
	List<Wh> listWh = whDao.findAll();

	if (lu != null) {
	    if (request.getParameter("btnList") != null) {
		// search
		wh = StringHelper.emptyIfNull(request.getParameter("wh"));
		bg = StringHelper.emptyIfNull(request.getParameter("bg"));
		itemCode = StringHelper.emptyIfNull(request.getParameter("itemCode"));
		itemName = StringHelper.emptyIfNull(request.getParameter("itemName"));
		msisdn = StringHelper.emptyIfNull(request.getParameter("msisdn"));
	    } else {
		// start
		wh = (String) request.getSession().getAttribute("searchlistStockSn_wh");
		bg = (String) request.getSession().getAttribute("searchlistStockSn_bg");
		itemCode = (String) request.getSession().getAttribute("searchlistStockSn_itemCode");
		itemName = (String) request.getSession().getAttribute("searchlistStockSn_itemName");
		msisdn = (String) request.getSession().getAttribute("searchlistStockSn_msisdn");

	    }
	}


	HashMap m = new HashMap();
	m.put("page", page);
	m.put("paging", paging);

	m.put("bg", bg);
	m.put("wh", wh);
	m.put("itemCode", itemCode);
	m.put("itemName", itemName);
	m.put("msisdn", msisdn);
	m.put("whs", listWh);

	return new ModelAndView("13_stock_wh/StockSnList", "model", m);
    }

    public ModelAndView listSnHistory(HttpServletRequest request, HttpServletResponse response) throws Exception {
	LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
//	String bg = "";
//	String eventCode = "";
//	String itemCode = "";
	String sn = "";
	String msisdn = "";

	Integer page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
	Integer paging = request.getParameter("paging") != null ? Integer.parseInt(request.getParameter("paging")) : AppConstant.paging;

//	List<Bg> listBg = new ArrayList<Bg>();
//	BgDao daoBg = DaoFactory.createBgDao();
//	if (lu != null) {
//	    listBg = daoBg.findByAuthLogin(lu.getUserId());
//	}
//
//	HistoryEventDao daoEvent = DaoFactory.createHistoryEventDao();
//	List<HistoryEvent> listEvent = daoEvent.findAll();

	if (lu != null) {
	    if (request.getParameter("btnList") != null) {
		// search
//		eventCode = StringHelper.emptyIfNull(request.getParameter("eventCode"));
//		bg = StringHelper.emptyIfNull(request.getParameter("bg"));
//		itemCode = StringHelper.emptyIfNull(request.getParameter("itemCode"));
		sn = StringHelper.emptyIfNull(request.getParameter("sn"));
		msisdn = StringHelper.emptyIfNull(request.getParameter("msisdn"));
	    } else {
		// start
//		eventCode = (String) request.getSession().getAttribute("searchlistStockSn_eventCode");
//		bg = (String) request.getSession().getAttribute("searchlistStockSn_bg");
//		itemCode = (String) request.getSession().getAttribute("searchlistStockSn_itemCode");
		sn = StringHelper.emptyIfNull((String) request.getSession().getAttribute("searchlistStockSn_sn"));
		msisdn = StringHelper.emptyIfNull((String) request.getSession().getAttribute("searchlistStockSn_msisdn"));

//		if (eventCode == null) {
//		    eventCode = listEvent != null && listEvent.size() > 0 ? listEvent.get(0).getEventCode() : "";
//		    bg = listBg != null && listBg.size() > 0 ? listBg.get(0).getBgCode() : "";
//		    itemCode = "";
//		    sn = "";
//		    msisdn = "";
//		}
	    }
	}

	request.getSession().setAttribute("searchlistStockSn_sn", sn);
	request.getSession().setAttribute("searchlistStockSn_msisdn", msisdn);

	HashMap m = new HashMap();
	m.put("page", page);
	m.put("paging", paging);

//	m.put("bg", bg);
//	m.put("eventCode", eventCode);
//	m.put("itemCode", itemCode);
	m.put("sn", sn);
	m.put("msisdn", msisdn);
//	m.put("bgs", listBg);
//	m.put("eventCodes", listEvent);

	return new ModelAndView("13_stock_wh/StockSnHistoryList", "model", m);
    }
}
