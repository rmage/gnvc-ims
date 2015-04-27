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
import com.spfi.ims.helper.StringHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;

public class FishSpoilageDataController extends MultiActionController {

    private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("fish/FishSpoilageList");
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
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            String data = request.getParameter("data");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            
            /* DAO | Define needed dao here */
            FishSpoilageDao fsDao = DaoFactory.createFishSpoilageDao();
            
            /* TRANSACTION | Something complex here */
            fsDao.insert2(data, lu.getUserId());

            return new ModelAndView("redirect:FishSpoilageData.htm");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FishSpoilageData.htm?action=create");
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
    
    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        /* DAO | Define needed dao here */
        FishSpoilageDao fsDao = DaoFactory.createFishSpoilageDao();

        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(fsDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = fsDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("batch_no")).append(":").append(x.get("date_shift")).append(":").append(x.get("time_shift")).append(":@\", ");
            sb.append("\"2\": \"").append(x.get("batch_no")).append("\", ");
            sb.append("\"3\": \"").append(x.get("date_shift")).append("\", ");
            sb.append("\"4\": \"").append(x.get("time_shift")).append("\", ");
            sb.append("\"5\": \"").append(x.get("cooked_weight")).append("\", ");
            sb.append("\"6\": \"").append(x.get("raw_weight")).append("\", ");
            sb.append("\"7\": \"").append(x.get("processed_weight")).append("\", ");
            sb.append("\"8\": \"").append(x.get("created_by")).append("\"}");

            b = Boolean.TRUE;
        }
        sb.append("]}");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
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
    
    // 2015 Update | by FYA
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            DaoFactory.createFishSpoilageDao().delete(request.getParameter("key"), lu.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:FishSpoilageData.htm");
    }

    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();

        try {
            model.put("spoilage", DaoFactory.createFishSpoilageDao().getSpoilage(request.getParameter("key")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("default/fish/FishSpoilageUpdate", "model", model);
    }

    public ModelAndView ajaxNUpdate(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map<String, Object> json = new HashMap<String, Object>();

        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            String data = URLDecoder.decode(request.getParameter("data"), "utf-8");
            String[] separator = StringHelper.getDataSeparator(data, 2);

            data = data.replaceAll(":s:", separator[0]).replaceAll(":se:", separator[1]);
            DaoFactory.createFishSpoilageDao().ajaxNUpdate(data, separator[0], separator[1], lu.getUserId());

            json.put("message", "");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
        }

        return new ModelAndView("jsonView", json);
    }
}
