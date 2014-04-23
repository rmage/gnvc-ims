package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.FishOilDao;
import com.app.wms.engine.db.dao.FishOilDtlDao;
import com.app.wms.engine.db.dto.FishOil;
import com.app.wms.engine.db.dto.FishOilDtl;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class FishOilController extends MultiActionController {

    public static final int initYear = 2014;
    public static final int initMonth = 4;

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        HashMap m = new HashMap();
        int currYear = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
//        int[] years = new int[initYear - currYear + 1];
//        for (int i = initYear; i <= currYear; i++) {
//            years[i - currYear] = i;
//        }
        int[] years = new int[currYear - initYear + 1];
        for (int i = 0; i < years.length; i++) {
            years[i] = currYear;
            currYear--;
        }
        m.put("year", years);
        String[][] month = {{"01", "January", "0"}, {"02", "February", "0"}, {"03", "March", "0"},
        {"04", "April", "0"}, {"05", "May", "0"}, {"06", "June", "0"}, {"07", "July", "0"},
        {"08", "August", "0"}, {"09", "September", "0"}, {"10", "October", "0"}, {"11", "November", "0"},
        {"12", "December", "0"}};
        for (String[] x : month) {
            if (x[0].equals(new SimpleDateFormat("MM").format(new Date()))) {
                x[2] = "1";
                break;
            }
        }
        m.put("month", month);
        m.put("date", new SimpleDateFormat("dd").format(new Date()));

        return new ModelAndView("fish_oil/FOList", "model", m);
    }

    public void getContent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        int year = Integer.parseInt(request.getParameter("year"));
        int month = Integer.parseInt(request.getParameter("month"));
        int currMonth = Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));

        FishOilDao fishOilDao = DaoFactory.createFishOilDao();
        FishOilDtlDao fishOilDtlDao = DaoFactory.createFishOilDtlDao();

        if (initYear == year && initMonth > month) {
            pw.print("{\"message\": \"Date is to old from our system!\"}");
            return;
        } else if (initYear <= year && month > currMonth) {
            pw.print("{\"message\": \"Date is to early from our system!\"}");
            return;
        } else if (initYear == year && initMonth == month) {
            if (fishOilDao.testFirstUse()) {
                pw.print("{\"message\": false, \"request\": true}");
                return;
            }
        }

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, (month - 1));
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");

        List<FishOilDtl> fods = fishOilDtlDao.findCurrentMonth(year, month, Integer.parseInt(new SimpleDateFormat("dd").format(cal.getTime())));
        if (fods.isEmpty()) {
            if (month == 1) {
                year -= 1;
                month = 12;
            } else {
                month -= 1;
            }
            cal.add(Calendar.MONTH, -1);
            cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
            FishOilDtl fod = fishOilDtlDao.findLastDate(year, month);
            if (sdf.format(fod.getFoDate()).equals(sdf.format(cal.getTime()))) 
                pw.print("{\"message\": false, \"drums\": " + fod.getFoEiDrums() + ", \"kilos\": " + fod.getFoEiKilos() + "}");
            else 
                pw.print("{\"message\": \"Please complete all form previous month!\"}");
            
        } else {
            String val = fishOilDao.findAdjusted(year + "-" + month + "-01", 1);
            pw.print("{\"message\": false, \"foId\": " + fods.get(0).getFoId() + ", \"adjust\":\"" + (val == null ? "0:0" : val) + "\",\"data\": [");
            for (FishOilDtl x : fods) {
                if (b) 
                    pw.print(",");
                
                pw.print("{\"id\": " + x.getId() + ", ");
                pw.print("\"biDrums\": " + x.getFoBiDrums() + ", ");
                pw.print("\"biKilos\": " + x.getFoBiKilos() + ",");
                pw.print("\"rS1Rm\": " + x.getFoRS1Rm() + ",");
                pw.print("\"rS1Drums\": " + x.getFoRS1Drums() + ",");
                pw.print("\"rS1Kilos\": " + x.getFoRS1Kilos() + ",");
                pw.print("\"rS2Rm\": " + x.getFoRS2Rm() + ",");
                pw.print("\"rS2Drums\": " + x.getFoRS2Drums() + ",");
                pw.print("\"rS2Kilos\": " + x.getFoRS2Kilos() + ",");
                pw.print("\"rS3Rm\": " + x.getFoRS3Rm() + ",");
                pw.print("\"rS3Drums\": " + x.getFoRS3Drums() + ",");
                pw.print("\"rS3Kilos\": " + x.getFoRS3Kilos() + ",");
                pw.print("\"ttdDrums\": " + x.getFoTtdDrums() + ",");
                pw.print("\"ttdKilos\": " + x.getFoTtdKilos() + ",");
                pw.print("\"iDrums\": " + x.getFoIDrums() + ",");
                pw.print("\"iKilos\": " + x.getFoIKilos() + ",");
                pw.print("\"iPrice\": " + x.getFoIPrice() + ",");
                pw.print("\"eiDrums\": " + x.getFoEiDrums() + ",");
                pw.print("\"eiKilos\": " + x.getFoEiKilos() + ",");
                pw.print("\"rMhrs\": \"" + x.getFoRMhrs() + "\",");
                pw.print("\"rOthr\": \"" + x.getFoROthr() + "\"}");

                b = Boolean.TRUE;
            }
            pw.print("]}");
        }
    }

    public void confirmFirstUse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        int drums = Integer.parseInt(request.getParameter("drums"));
        int kilos = Integer.parseInt(request.getParameter("kilos"));
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        FishOilDao fishOilDao = DaoFactory.createFishOilDao();
        if (fishOilDao.insertFirstUse(drums, kilos, lu.getUserId())!=0) {
            pw.print("{\"message\": true}");
        }
    }

    public void saveContent(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        int id = 0;
        int foId = 0;
        PrintWriter pw = response.getWriter();
        String masters = request.getParameter("master");
        String details = request.getParameter("detail");
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        FishOilDao fishOilDao = DaoFactory.createFishOilDao();
        FishOilDtlDao fishOilDtlDao = DaoFactory.createFishOilDtlDao();
        if(masters != null) {
            String[] master = masters.split(":", -1);
            FishOil fo = new FishOil();
            fo.setFoMonth(master[0]);
            fo.setFoYear(master[1]);
            fo.setCreatedBy(lu.getUserId());
            fo.setCreatedDate(new Date());
            foId = fishOilDao.insert(fo);
        }
        String[] detail = details.split(":", -1);
        FishOilDtl fod = new FishOilDtl();
        fod.setFoDate(new SimpleDateFormat("dd/MM/yyyy").parse(detail[0]));
        fod.setFoBiDrums(Integer.parseInt(detail[1]));
        fod.setFoBiKilos(Integer.parseInt(detail[2]));
        fod.setFoRS1Rm(Integer.parseInt(detail[3]));
        fod.setFoRS1Drums(Integer.parseInt(detail[4]));
        fod.setFoRS1Kilos(Integer.parseInt(detail[5]));
        fod.setFoRS2Rm(Integer.parseInt(detail[6]));
        fod.setFoRS2Drums(Integer.parseInt(detail[7]));
        fod.setFoRS2Kilos(Integer.parseInt(detail[8]));
        fod.setFoRS3Rm(Integer.parseInt(detail[9]));
        fod.setFoRS3Drums(Integer.parseInt(detail[10]));
        fod.setFoRS3Kilos(Integer.parseInt(detail[11]));
        fod.setFoTtdDrums(Integer.parseInt(detail[12]));
        fod.setFoTtdKilos(Integer.parseInt(detail[13]));
        fod.setFoIDrums(Integer.parseInt(detail[14]));
        fod.setFoIKilos(Integer.parseInt(detail[15]));
        fod.setFoIPrice(new BigDecimal(detail[16]));
        fod.setFoEiDrums(Integer.parseInt(detail[17]));
        fod.setFoEiKilos(Integer.parseInt(detail[18]));
        
        if(request.getParameter("status").equals("0")) {
            if(masters != null)
                fod.setFoId(foId);
            else
                fod.setFoId(Integer.parseInt(request.getParameter("foId")));
            
            fod.setCreatedBy(lu.getUserId());
            fod.setCreatedDate(new Date());
            id = fishOilDtlDao.insert(fod);
            pw.print("{\"message\": true, \"foId\": " + fod.getFoId() + ", \"id\": " + id + "}");
        } else {
            fod.setId(Integer.parseInt(request.getParameter("id")));
            fod.setFoId(Integer.parseInt(request.getParameter("foId")));
            fod.setUpdatedBy(lu.getUserId());
            fod.setUpdatedDate(new Date());
            fishOilDtlDao.update(fod);
            pw.print("{\"message\": true, \"foId\": " + fod.getFoId() + ", \"id\": " + fod.getId() + "}");
        }
    }
}
