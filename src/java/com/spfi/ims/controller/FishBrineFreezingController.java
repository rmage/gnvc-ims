package com.spfi.ims.controller;

import com.app.wms.engine.db.dao.FishRrDao;
import com.app.wms.engine.db.dao.FishStorageDao;
import com.app.wms.engine.db.dto.FishStorage;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.DaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.BrineFreezingDao;
import com.spfi.ims.dto.BrineFreezing;
import com.spfi.ims.dto.BrineFreezingDetail;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class FishBrineFreezingController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse
            response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/fish/BrineFreezingList");
    }
    
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) 
            throws DaoException {
        /* DATA | get initial value */
        HashMap<String, Object> model = new HashMap<String, Object>();
        
        /* DAO | Define needed dao here */
        FishStorageDao fishStorageDao = DaoFactory.createFishStorageDao();
        
        /* TRANSACTION | Something complex here */
        List<FishStorage> fishStorages = fishStorageDao.findAllActive();
        model.put("cs", fishStorages);
        
        return new ModelAndView("default/fish/BrineFreezingAdd", "model", model);
    }
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* DATA | get initial value */
            String[] header = request.getParameter("header").split(":", -1);
            String[] details = request.getParameterValues("detail");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            /* DAO | Define needed dao here */
            BrineFreezingDao bfDao = DaoFactory.createBrineFreezingDao();
            
            /* TRANSACTION | Something complex here */
            BrineFreezing bf = new BrineFreezing();
            bf.setBfNo(header[0]);
            bf.setBfDate(sdf.parse(header[1]));
            bf.setWsId(Integer.parseInt(header[2]));
            bf.setStorageId(Integer.parseInt(header[3]));
            bf.setBatchNo(header[4]);
            bf.setRegu(header[5]);
            bf.setTimeShift(header[6]);
            bf.setTimeStart(header[7] + ":" + header[8]);
            bf.setTimeFinish(header[9] + ":" + header[10]);
            bf.setCreatedBy(lu.getUserId());
            bf.setId(bfDao.insertH(bf));
            
            BrineFreezingDetail bfd = new BrineFreezingDetail();
            bfd.setBfId(bf.getId());
            bfd.setCreatedby(lu.getUserId());
            for(String x : details) {
                String[] detail = x.split(":", -1);
                bfd.setFishId(Integer.parseInt(detail[0]));
                bfd.setTotalWeight(new BigDecimal(detail[1]));
                if(bfd.getTotalWeight().compareTo(BigDecimal.ZERO) > 0) {
                    bfDao.insertD(bfd, bf.getBfNo(), bf.getBatchNo(), bf.getStorageId());
                }
            }
            
            return new ModelAndView("redirect:BrineFreezing.htm");
        } catch(Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:BrineFreezing.htm?action=create");
        }
    }
    
    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        StringBuilder sb = new StringBuilder();

        /* DAO | Define needed dao here */
        BrineFreezingDao bfDao = DaoFactory.createBrineFreezingDao();

        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(bfDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = bfDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("id")).append("\", ");
            sb.append("\"2\": \"").append(x.get("bf_no")).append("\", ");
            sb.append("\"3\": \"").append(x.get("bf_date")).append("\", ");
            sb.append("\"4\": \"").append(x.get("ws_no")).append("\", ");
            sb.append("\"5\": \"").append(x.get("supplier")).append("\", ");
            sb.append("\"6\": \"").append(x.get("batch_no")).append("\", ");
            sb.append("\"7\": \"").append(x.get("regu")).append("\", ");
            sb.append("\"8\": \"").append(x.get("time_shift")).append("\", ");
            sb.append("\"9\": \"").append(x.get("time_start")).append("\", ");
            sb.append("\"10\": \"").append(x.get("time_finish")).append("\"}");

            b = Boolean.TRUE;
        }
        sb.append("]}");
        response.getWriter().print(sb.toString());
    }
    
    public void getBatchInfo(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        StringBuilder sb = new StringBuilder();
        String batchNo = request.getParameter("term");

        /* DAO | Define needed dao here */
        FishRrDao frDao = DaoFactory.createFishRrDao();

        /* TRANSACTION | Something complex here */
        sb.append("[");
        List<Map<String, Object>> ms = frDao.getBatchInfo(batchNo);
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"batchNo\":\"").append(x.get("batch_no")).append("\",");
            sb.append("\"boat\":\"").append(x.get("boat")).append("\",");
            sb.append("\"supplier\":\"").append(x.get("supplier")).append("\"}");
            b = Boolean.TRUE;
        }
        sb.append("]");
        response.getWriter().print(sb.toString());
    }
    
    public void getFish(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        String batchNo = request.getParameter("batchNo");
        String wsNo = request.getParameter("wsNo");
        Boolean b = Boolean.FALSE;
        StringBuilder sb = new StringBuilder();
        
        /* DAO | Define needed dao here */
        BrineFreezingDao bfDao = DaoFactory.createBrineFreezingDao();
        
        /* TRANSACTION | Something complex here */
        sb.append("[");
        List<Map<String, Object>> ms = bfDao.getFish(batchNo, wsNo);
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"wsid\":\"").append(x.get("wsid")).append("\",");
            sb.append("\"fishid\":\"").append(x.get("fishid")).append("\",");
            sb.append("\"fish\":\"").append(x.get("fish")).append("\",");
            sb.append("\"weight\":\"").append(x.get("weight")).append("\"}");
            b = Boolean.TRUE;
        }
        sb.append("]");
        response.getWriter().print(sb.toString());
    }
    
}
