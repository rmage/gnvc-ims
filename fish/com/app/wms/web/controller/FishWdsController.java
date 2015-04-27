package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.FishStorageDao;
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
import com.app.wms.engine.db.dto.FishStorage;
import com.app.wms.engine.db.dto.FishWds;
import com.app.wms.engine.db.dto.FishWdsDetail;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.helper.StringHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;

public class FishWdsController extends MultiActionController {

    private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("fish/WDSDataList");
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

            if (request.getParameter("search") != null) {
                String wdsNo = request.getParameter("wdsNo");
                if (request.getParameter("wdsDate") != null) {
                    Date wdsDate = df.parse(request.getParameter("wdsDate"));
                    fishWdsList = dao.searchAndPaging(wdsNo, wdsDate, paging, offset);
                    String querySearch = "&search=true&wdsNo=" + wdsNo + "&wdsDate=" + df.format(wdsDate);
                    modelMap.put("querySearch", querySearch);
                    modelMap.put("queryWdsNo", wdsNo);
                    modelMap.put("queryWdsDate", df.format(wdsDate));
                } else {
                    fishWdsList = dao.searchAndPagingWithoutDate(wdsNo, paging, offset);
                    String querySearch = "&search=true&wdsNo=" + wdsNo;
                    modelMap.put("querySearch", querySearch);
                    modelMap.put("queryWdsNo", wdsNo);
                }
            } else {
                fishWdsList = dao.findAllAndPaging(paging, offset);
            }

            modelMap.put("wdsDataList", fishWdsList);
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

        FishStorageDao fishStorageDao = DaoFactory.createFishStorageDao();
        List<FishStorage> fishStorages = fishStorageDao.findAllActive();
        modelMap.put("fses", fishStorages);

        modelMap.put("mode", "create");
        return new ModelAndView("fish/WDSDataAdd", "model", modelMap);
    }

    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            String data = request.getParameter("data");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            /* DAO | Define needed dao here */
            FishWdsDao fwDao = DaoFactory.createFishWdsDao();

            /* TRANSACTION | Something complex here */
            fwDao.insert2(data, lu.getUserId());

            return new ModelAndView("redirect:FishWds.htm");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FishWds.htm?action=create");
        }
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

    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        /* DAO | Define needed dao here */
        FishWdsDao fwDao = DaoFactory.createFishWdsDao();

        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(fwDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = fwDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("id")).append("\", ");
            sb.append("\"2\": \"").append(x.get("wds_no")).append("\", ");
            sb.append("\"3\": \"").append(x.get("batch_no")).append("\", ");
            sb.append("\"4\": \"").append(x.get("supplier")).append("\", ");
            sb.append("\"5\": \"").append(x.get("requested_by")).append("\", ");
            sb.append("\"6\": \"").append(x.get("approved_by")).append("\", ");
            sb.append("\"7\": \"").append(x.get("created_by")).append("\"}");

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
            returnMap.put("storage", fishWdsDetail.getStorageId() == 0
                    ? "FROZEN" : fishWdsDetail.getStorage().getCode());

            tableMap.put(returnMap, returnMap);
        }

        modelMap.put("tableMap", tableMap);
        return new ModelAndView("fish/WDSDataDetailList", "model", modelMap);
    }

    // 2015 Update | by FYA
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            DaoFactory.createFishWdsDao().delete(Integer.parseInt(request.getParameter("key")), lu.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:FishWds.htm");
    }

    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();

        try {
            model.put("wds", DaoFactory.createFishWdsDao().getWithdrawal(Integer.parseInt(request.getParameter("key"))));
            model.put("fses", DaoFactory.createFishStorageDao().findAllActive());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("default/fish/WithdrawalSlipUpdate", "model", model);
    }

    public ModelAndView ajaxNUpdate(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map<String, Object> json = new HashMap<String, Object>();

        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            String data = URLDecoder.decode(request.getParameter("data"), "utf-8");
            String[] separator = StringHelper.getDataSeparator(data, 2);

            data = data.replaceAll(":s:", separator[0]).replaceAll(":se:", separator[1]);
            DaoFactory.createFishWdsDao().ajaxNUpdate(data, separator[0], separator[1], lu.getUserId());

            json.put("message", "");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
        }

        return new ModelAndView("jsonView", json);
    }
}
