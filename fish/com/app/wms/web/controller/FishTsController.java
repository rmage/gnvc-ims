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

import com.app.wms.engine.db.dao.FishTsDao;
import com.app.wms.engine.db.dao.FishTsDetailDao;
import com.app.wms.engine.db.dto.FishTs;
import com.app.wms.engine.db.dto.FishTsDetail;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class FishTsController extends MultiActionController {

    private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("fish/TSDataList");
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

            if (request.getParameter("search") != null) {
                String tsNo = request.getParameter("tsNo");
                if (request.getParameter("tsDate") != null) {
                    Date tsDate = df.parse(request.getParameter("tsDate"));
                    fishTsList = dao.searchAndPaging(tsNo, tsDate, paging, offset);
                    String querySearch = "&search=true&tsNo=" + tsNo + "&tsDate=" + df.format(tsDate);
                    modelMap.put("querySearch", querySearch);
                    modelMap.put("queryTsNo", tsNo);
                    modelMap.put("queryTsDate", df.format(tsDate));
                } else {
                    fishTsList = dao.searchAndPagingWithoutDate(tsNo, paging, offset);
                    String querySearch = "&search=true&tsNo=" + tsNo;
                    modelMap.put("querySearch", querySearch);
                    modelMap.put("queryTsNo", tsNo);
                }
            } else {
                fishTsList = dao.findAllAndPaging(paging, offset);
            }

            modelMap.put("tsDataList", fishTsList);
            modelMap.put("totalRows", 2000);
            modelMap.put("page", page);
            modelMap.put("paging", paging);
        } catch (Exception e) {
            throw e;
        }

        return modelMap;
    }

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap<String, Object> modelMap = new HashMap<String, Object>();

        modelMap.put("mode", "create");
        return new ModelAndView("fish/TSDataAdd", "model", modelMap);
    }
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            String data = request.getParameter("data");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            
            /* DAO | Define needed dao here */
            FishTsDao ftDao = DaoFactory.createFishTsDao();
            
            /* TRANSACTION | Something complex here */
            ftDao.insert2(data, lu.getUserId());

            return new ModelAndView("redirect:FishTs.htm");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FishTs.htm?action=create");
        }
    }

//    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        LoginUser user = (LoginUser) request.getSession().getAttribute("user");
//        String mode = request.getParameter("mode");
//        HashMap<String, Object> modelMap = new HashMap<String, Object>();
//
//        if (user == null) {
//            String msg = "You haven't login or your session has been expired! Please do login again";
//            modelMap.put("msg", msg);
//            return new ModelAndView("login", "model", modelMap);
//        } else {
//            Integer wdsId = Integer.valueOf(request.getParameter("wdsId"));
//            Integer vesselId = Integer.valueOf(request.getParameter("vesselId"));
//            String tsNo = request.getParameter("tsNo");
//            Date tsDate = df.parse(request.getParameter("tsDate"));
//            String issuedBy = request.getParameter("issuedBy");
//            String notedBy = request.getParameter("notedBy");
//            String approvedBy = request.getParameter("approvedBy");
//            String receivedBy = request.getParameter("receivedBy");
//
//            FishTs dto = new FishTs();
//            dto.setWdsId(wdsId);
//            dto.setVesselId(vesselId);
//            dto.setTsNo(tsNo);
//            dto.setTsDate(tsDate);
//            dto.setIssuedBy(issuedBy);
//            dto.setNotedBy(notedBy);
//            dto.setApprovedBy(approvedBy);
//            dto.setReceivedBy(receivedBy);
//            dto.setCreatedDate(new Date());
//            dto.setCreatedBy(user.getUserId());
//            dto.setIsActive("Y");
//            dto.setIsDelete("N");
//
//            FishTsDao dao = DaoFactory.createFishTsDao();
//            int tsId = dao.insert(dto);
//            int totalData = Integer.valueOf(request.getParameter("totalData"));
//
//            for (int i = 1; i <= totalData; i++) {
//                int fishId = Integer.valueOf(request.getParameter("fishId" + i));
//                int storageId = Integer.valueOf(request.getParameter("storageId" + i));
//                String description = request.getParameter("description" + i);
//                Double qty = Double.valueOf(request.getParameter("qty" + i));
//                String uomCode = request.getParameter("uomCode" + i);
//
//                FishTsDetail tsDetail = new FishTsDetail();
//                tsDetail.setTsId(tsId);
//                tsDetail.setFishId(fishId);
//                tsDetail.setStorageId(storageId);
//                tsDetail.setDescription(description);
//                tsDetail.setQuantity(qty);
//                tsDetail.setUomCode(uomCode);
//                tsDetail.setCreatedDate(new Date());
//                tsDetail.setCreatedBy(user.getUserId());
//                tsDetail.setIsActive("Y");
//                tsDetail.setIsDelete("N");
//
//                FishTsDetailDao tsDetailDao = DaoFactory.createFishTsDetailDao();
//                if (qty.compareTo(Double.valueOf("0.00")) >= 0) {
//                    tsDetailDao.insert(tsDetail);
//                }
//            }
//
//            modelMap = this.searchAndPaging(request, response);
//            return new ModelAndView("fish/TSDataList", "model", modelMap);
//        }
//    }

    public ModelAndView inactivate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.valueOf(request.getParameter("id"));
        FishTsDao dao = DaoFactory.createFishTsDao();
        dao.delete(id);

        FishTsDetailDao detailDao = DaoFactory.createFishTsDetailDao();
        detailDao.deleteAllByTsId(id);

        HashMap<String, Object> modelMap = this.searchAndPaging(request, response);
        return new ModelAndView("fish/TSDataList", "model", modelMap);
    }
    
    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();

        /* DAO | Define needed dao here */
        FishTsDao ftDao = DaoFactory.createFishTsDao();

        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(ftDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = ftDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("id")).append("\", ");
            sb.append("\"2\": \"").append(x.get("ts_no")).append("\", ");
            sb.append("\"3\": \"").append(x.get("ts_date")).append("\", ");
            sb.append("\"4\": \"").append(x.get("wds_no")).append("\", ");
            sb.append("\"5\": \"").append(x.get("issued_by")).append("\", ");
            sb.append("\"6\": \"").append(x.get("noted_by")).append("\", ");
            sb.append("\"7\": \"").append(x.get("approved_by")).append("\", ");
            sb.append("\"8\": \"").append(x.get("received_by")).append("\", ");
            sb.append("\"9\": \"").append(x.get("created_by")).append("\"}");

            b = Boolean.TRUE;
        }
        
        sb.append("]}");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
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
            returnMap.put("storage", fishTsDetail.getStorageId() == 0
                    ? "FRESH" : fishTsDetail.getStorage().getCode());
            returnMap.put("qty", fishTsDetail.getQuantity());

            tableMap.put(returnMap, returnMap);
        }

        modelMap.put("tableMap", tableMap);
        return new ModelAndView("fish/TSDataDetailList", "model", modelMap);
    }
}
