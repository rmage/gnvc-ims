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
import com.app.wms.engine.db.dao.FishSpoilageDao;
import com.app.wms.engine.db.dto.Fish;
import com.app.wms.engine.db.dto.FishSpoilage;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import java.io.IOException;
import java.io.PrintWriter;

public class FishSpoilageDataController extends MultiActionController {

    private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap<String, Object> modelMap = this.searchAndPaging(request, response);
        return new ModelAndView("fish/FishSpoilageList", "model", modelMap);
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

            FishSpoilageDao dao = DaoFactory.createFishSpoilageDao();
            List<FishSpoilage> spoilageList = null;

            if (request.getParameter("search") != null) {
                String batchNo = request.getParameter("batchNo");
                if (request.getParameter("dateShift") != null) {
                    Date dateShift = df.parse(request.getParameter("dateShift"));
                    spoilageList = dao.searchDistinctAndPaging(batchNo, dateShift, paging, offset);
                    String querySearch = "&search=true&batchNo=" + batchNo + "&dateShift=" + df.format(dateShift);
                    modelMap.put("querySearch", querySearch);
                    modelMap.put("queryBatchNo", batchNo);
                    modelMap.put("queryDateShift", df.format(dateShift));
                } else {
                    spoilageList = dao.searchDistinctAndPagingWithoutDate(batchNo, paging, offset);
                    String querySearch = "&search=true&batchNo=" + batchNo;
                    modelMap.put("querySearch", querySearch);
                    modelMap.put("queryBatchNo", batchNo);
                }
            } else {
                spoilageList = dao.findAllDistinctAndPaging(paging, offset);
            }

            modelMap.put("spoilageDataList", spoilageList);
            modelMap.put("totalRows", 2000);
            modelMap.put("page", page);
            modelMap.put("paging", paging);
        } catch (Exception e) {
            throw e;
        }

        return modelMap;
    }

    public void ajaxSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LoginUser user = (LoginUser) request.getSession().getAttribute("user");
        if (user != null) {
            String catcherNo = request.getParameter("catcherNo");
            Date dateShift = df.parse(request.getParameter("dateShift"));
            String timeShift = request.getParameter("timeShift");
            Integer vesselId = Integer.valueOf(request.getParameter("vesselId"));
            Integer fishId = Integer.valueOf(request.getParameter("fishId"));
            Double rawWeight = Double.valueOf(request.getParameter("rawWeight"));
            Double cookedWeight = Double.valueOf(request.getParameter("cookedWeight"));
            Double totalProcessed = Double.valueOf(request.getParameter("totalProcessed"));
            String reason = request.getParameter("reason");

            FishSpoilageDao dao = DaoFactory.createFishSpoilageDao();
            FishSpoilage dto = new FishSpoilage();
            dto.setCatcherNo(catcherNo);
            dto.setDateShift(dateShift);
            dto.setTimeShift(timeShift);
            dto.setVesselId(vesselId);
            dto.setFishId(fishId);
            dto.setRawWeight(rawWeight);
            dto.setCookedWeight(cookedWeight);
            dto.setTotalProcessed(totalProcessed);
            dto.setReason(reason);
            dto.setCreatedDate(new Date());
            dto.setCreatedBy(user.getUserId());
            dto.setIsActive("Y");
            dto.setIsDelete("N");

            dao.insert(dto);
        }
    }

    public ModelAndView create(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        FishDao dao = DaoFactory.createFishDao();
        List<Fish> fishDataList = dao.findAllActive();

        HashMap<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("fishDataList", fishDataList);

        return new ModelAndView("fish/FishSpoilageAdd", "model", modelMap);
    }

    public ModelAndView save(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        LoginUser user = (LoginUser) request.getSession().getAttribute("user");
        HashMap<String, Object> modelMap = new HashMap<String, Object>();

        if (user == null) {
            String msg = "You haven't login or your session has been expired! Please do login again";
            modelMap.put("msg", msg);

            return new ModelAndView("login", "model", modelMap);
        } else {
            int totalData = Integer.valueOf(request.getParameter("totalData"));
            Date dateShift = df.parse(request.getParameter("dateShift"));
            String timeShift = request.getParameter("timeShift");
            int vesselId = Integer.valueOf(request.getParameter("vesselId"));

            for (int i = 1; i <= totalData; i++) {
                String catcherNo = request.getParameter("catcherNo" + i);
                int fishId = Integer.valueOf(request.getParameter("fishId" + i));
                Double cookedWeight = Double.valueOf(request.getParameter("cookedWeight" + i));
                Double rawWeight = Double.valueOf(request.getParameter("rawWeight" + i));
                Double totalProcessed = Double.valueOf(request.getParameter("totalProcessed" + i));
                String reason = request.getParameter("reason" + i);

                FishSpoilage dto = new FishSpoilage();
                dto.setCatcherNo(catcherNo);
                dto.setDateShift(dateShift);
                dto.setTimeShift(timeShift);
                dto.setVesselId(vesselId);
                dto.setFishId(fishId);
                dto.setCookedWeight(cookedWeight);
                dto.setRawWeight(rawWeight);
                dto.setTotalProcessed(totalProcessed);
                dto.setReason(reason);
                dto.setCreatedDate(new Date());
                dto.setCreatedBy(user.getUserId());
                dto.setIsActive("Y");
                dto.setIsDelete("N");

                FishSpoilageDao dao = DaoFactory.createFishSpoilageDao();
                dao.insert(dto);
            }

            modelMap = this.searchAndPaging(request, response);
            return new ModelAndView("fish/FishSpoilageList", "model", modelMap);
        }
    }

    public ModelAndView ajaxDocument(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Map<String, Object> modelMap = new HashMap<String, Object>();
        int vesselId = Integer.valueOf(request.getParameter("vesselId"));
        String timeShift = request.getParameter("timeShift");
        Date dateShift = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateShift"));

        FishSpoilageDao dao = DaoFactory.createFishSpoilageDao();
        List<FishSpoilage> spoilageDetailList = dao.findAllForReport(vesselId, dateShift, timeShift);

        Map tableMap = new HashMap();
        for (FishSpoilage fishSpoilageDetail : spoilageDetailList) {
            Map<String, Object> returnMap = new HashMap<String, Object>();
            returnMap.put("id", fishSpoilageDetail.getId());
            returnMap.put("batchNo", fishSpoilageDetail.getVessel().getBatchNo());
            returnMap.put("catcherNo", fishSpoilageDetail.getCatcherNo());
            returnMap.put("fishType", fishSpoilageDetail.getFish().getCode());
            returnMap.put("fishName", fishSpoilageDetail.getFish().getFishType().getDescription());
            returnMap.put("rawWeight", fishSpoilageDetail.getRawWeight());
            returnMap.put("cookedWeight", fishSpoilageDetail.getCookedWeight());
            returnMap.put("totalProcessed", fishSpoilageDetail.getTotalProcessed());
            returnMap.put("reason", fishSpoilageDetail.getReason());

            tableMap.put(returnMap, returnMap);
        }

        modelMap.put("tableMap", tableMap);
        return new ModelAndView("fish/FishSpoilageDetailList", "model", modelMap);
    }

    public ModelAndView inactivate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.valueOf(request.getParameter("id"));
        FishSpoilageDao dao = DaoFactory.createFishSpoilageDao();
        dao.delete(id);

        HashMap<String, Object> modelMap = this.searchAndPaging(request, response);
        return new ModelAndView("fish/FishSpoilageList", "model", modelMap);
    }

    public void getFishType(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        String batchNo = request.getParameter("term");
        System.out.println("batchNo: " + batchNo);

        FishDao dao = DaoFactory.createFishDao();

        pw.print("[");
        List<Fish> fishDataList = dao.findFishByBatchNo(batchNo);
        for (Fish x : fishDataList) {
            if (b) {
                pw.print(",");
            }
            pw.print("{\"id\": \"" + x.getId() + "\", ");
            pw.print("\"code\": \"" + x.getCode() + "\",");
            pw.print("\"description\": \"" + x.getCreatedBy() + "\"}");

            b = Boolean.TRUE;
        }
        pw.print("]");
    }
}
