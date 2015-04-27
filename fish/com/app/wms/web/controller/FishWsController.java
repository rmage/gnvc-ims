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
import com.spfi.ims.helper.StringHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;

public class FishWsController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("fish/WSDataList");
    }

    private final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

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

            if (request.getParameter("search") != null) {
                String wsNo = request.getParameter("wsNo");

                if (request.getParameter("wsDate") != null) {
                    Date wsDate = df.parse(request.getParameter("wsDate"));
                    fishWsDataList = dao.searchAndPaging(wsNo, wsDate, paging, offset);
                    String querySearch = "&search=true&wsNo=" + wsNo + "&wsDate=" + df.format(wsDate);
                    modelMap.put("queryWsNo", wsNo);
                    modelMap.put("queryWsDate", df.format(wsDate));
                    modelMap.put("querySearch", querySearch);
                } else {
                    fishWsDataList = dao.searchAndPagingWithoutDate(wsNo, paging, offset);
                    String querySearch = "&search=true&wsNo=" + wsNo;
                    modelMap.put("queryWsNo", wsNo);
                    modelMap.put("querySearch", querySearch);
                }
            } else {
                fishWsDataList = dao.findAllAndPaging(paging, offset);
            }

            modelMap.put("fishWsData", fishWsDataList);
            modelMap.put("totalRows", 2000);
            modelMap.put("page", page);
            modelMap.put("paging", paging);
        } catch (Exception e) {
            throw e;
        }

        return modelMap;
    }

    public ModelAndView inactivate(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            String data = request.getParameter("data");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            
            /* DAO | Define needed dao here */
            FishWsDao fwDao = DaoFactory.createFishWsDao();
            
            /* TRANSACTION | Something complex here */
            fwDao.insert2(data, lu.getUserId());

            return new ModelAndView("redirect:FishWs.htm");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:FishWs.htm?action=create");
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
        } else {
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
            } catch (DaoException e) {
                e.printStackTrace();
            }

            return new ModelAndView("fish/WSDataView", "model", modelMap);
        }
    }
    
    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        /* DAO | Define needed dao here */
        FishWsDao fwDao = DaoFactory.createFishWsDao();

        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(fwDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = fwDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("id")).append("\", ");
            sb.append("\"2\": \"").append(x.get("ws_no")).append("\", ");
            sb.append("\"3\": \"").append(x.get("date_shift")).append("\", ");
            sb.append("\"4\": \"").append(x.get("ws_type")).append("\", ");
            sb.append("\"5\": \"").append(x.get("batch_no")).append("\", ");
            sb.append("\"6\": \"").append(x.get("supplier")).append("\", ");
            sb.append("\"7\": \"").append(x.get("created_by")).append("\"}");

            b = Boolean.TRUE;
        }
        sb.append("]}");
        pw.print(sb.toString());
        pw.flush();
        pw.close();
    }
    
    // 2015 Update | by FYA
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            DaoFactory.createFishWsDao().delete(Integer.parseInt(request.getParameter("key")), lu.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:FishWs.htm");
    }

    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();

        try {
            String key = request.getParameter("key");
            model.put("ws", DaoFactory.createFishWsDao().getWeightSlip(Integer.parseInt(key)));
            model.put("fishes", DaoFactory.createFishDao().findAllActive());
            model.put("wsTypes", DaoFactory.createFishWsTypeDao().findAllActive());
            model.put("fishStorages", DaoFactory.createFishStorageDao().findAllActive());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("default/fish/WeightSlipUpdate", "model", model);
    }

    public ModelAndView ajaxNUpdate(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map<String, Object> json = new HashMap<String, Object>();

        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

            String data = URLDecoder.decode(request.getParameter("data"), "utf-8");
            String[] separator = StringHelper.getDataSeparator(data, 2);

            data = data.replaceAll(":s:", separator[0]).replaceAll(":se:", separator[1]);
            DaoFactory.createFishWsDao().ajaxNUpdate(data, separator[0], separator[1], lu.getUserId());

            json.put("message", "");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
        }

        return new ModelAndView("jsonView", json);
    }
    
}
