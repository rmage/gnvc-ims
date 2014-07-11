package com.spfi.ims.controller;

import com.app.wms.engine.db.dao.FishRrDao;
import com.app.wms.engine.db.dao.FishStorageDao;
import com.app.wms.engine.db.dto.FishStorage;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.DaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import com.spfi.ims.dao.AirBlastFreezingDao;
import com.spfi.ims.dto.AirBlastFreezing;
import com.spfi.ims.dto.AirBlastFreezingDetail;
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

public class FishAirBlastFreezingController extends MultiActionController {
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse
            response) {
        /* DATA | get initial value */
        /* DAO | Define needed dao here */
        /* TRANSACTION | Something complex here */
        return new ModelAndView("default/fish/AirBlastFreezingList");
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
        
        return new ModelAndView("default/fish/AirBlastFreezingAdd", "model", model);
    }
    
    public ModelAndView save(HttpServletRequest request, HttpServletResponse
            response) {
        try {
            /* DATA | get initial value */
            String[] header = request.getParameter("header").split(":", -1);
            String[] details = request.getParameterValues("detail");
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            /* DAO | Define needed dao here */
            AirBlastFreezingDao abfDao = DaoFactory.createAirBlastFreezingDao();
            
            /* TRANSACTION | Something complex here */
            AirBlastFreezing abf = new AirBlastFreezing();
            abf.setAbfNo(header[0]);
            abf.setAbfDate(sdf.parse(header[1]));
            abf.setWsId(Integer.parseInt(header[2]));
            abf.setStorageId(Integer.parseInt(header[3]));
            abf.setBatchNo(header[4]);
            abf.setRegu(header[5]);
            abf.setTimeShift(header[6]);
            abf.setTimeStart(header[7] + ":" + header[8]);
            abf.setTimeFinish(header[9] + ":" + header[10]);
            abf.setCreatedBy(lu.getUserId());
            abf.setId(abfDao.insertH(abf));
            
            AirBlastFreezingDetail abfd = new AirBlastFreezingDetail();
            abfd.setAbfId(abf.getId());
            abfd.setCreatedby(lu.getUserId());
            for(String x : details) {
                String[] detail = x.split(":", -1);
                abfd.setFishId(Integer.parseInt(detail[0]));
                abfd.setTotalWeight(new BigDecimal(detail[1]));
                if(abfd.getTotalWeight().compareTo(BigDecimal.ZERO) > 0) {
                    abfDao.insertD(abfd, abf.getAbfNo(), abf.getBatchNo(), abf.getStorageId());
                }
            }
            
            return new ModelAndView("redirect:AirBlastFreezing.htm");
        } catch(Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:AirBlastFreezing.htm?action=create");
        }
    }
    
    public void ajaxSearch(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        StringBuilder sb = new StringBuilder();

        /* DAO | Define needed dao here */
        AirBlastFreezingDao abfDao = DaoFactory.createAirBlastFreezingDao();

        /* TRANSACTION | Something complex here */
        sb.append("{\"maxpage\": ").append(abfDao.ajaxMaxPage(new BigDecimal(request.getParameter("show")), request.getParameter("where"))).append(",\"data\": [");
        List<Map<String, Object>> ms = abfDao.ajaxSearch(Integer.parseInt(request.getParameter("page"), 10), Integer.parseInt(request.getParameter("show"), 10), request.getParameter("where"), request.getParameter("order"));
        for (Map<String, Object> x : ms) {
            if (b) {
                sb.append(",");
            }
            sb.append("{\"1\": \"").append(x.get("id")).append("\", ");
            sb.append("\"2\": \"").append(x.get("abf_no")).append("\", ");
            sb.append("\"3\": \"").append(x.get("abf_date")).append("\", ");
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
        AirBlastFreezingDao abfDao = DaoFactory.createAirBlastFreezingDao();
        
        /* TRANSACTION | Something complex here */
        sb.append("[");
        List<Map<String, Object>> ms = abfDao.getFish(batchNo, wsNo);
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
