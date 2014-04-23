package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.FishMealDao;
import com.app.wms.engine.db.dao.FishMealDtlDao;
import com.app.wms.engine.db.dto.FishMeal;
import com.app.wms.engine.db.dto.FishMealDtl;
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

public class FishMealController extends MultiActionController {
    
    public static final int initYear = 2014;
    public static final int initMonth = 3;
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        
        /* DATA | get initial value */
        HashMap m = new HashMap();
        
        /* DAO | Define needed dao here */
        
        /* TRANSACTION | Something complex here */
        // set year
        int currYear = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
//        int[] years = new int[initYear - currYear + 1];
//        for(int i = initYear; i <= currYear; i++) {
//            years[i - currYear] = i;
//        } 
        int[] years = new int[currYear - initYear + 1];
        for (int i = 0; i < years.length; i++) {
            years[i] = currYear;
            currYear--;
        }
        m.put("year", years);
        
        // set month
        String[][] month = { {"01", "January", "0"}, {"02", "February", "0"}, {"03", "March", "0"}, 
            {"04", "April", "0"}, {"05", "May", "0"}, {"06", "June", "0"}, {"07", "July", "0"}, 
            {"08", "August", "0"}, {"09", "September", "0"}, {"10", "October", "0"}, {"11", "November", "0"}, 
            {"12", "December", "0"} };
        for(String[] x : month) {
            if(x[0].equals(new SimpleDateFormat("MM").format(new Date()))) {
                x[2] = "1"; break;
            }
        }
        m.put("month", month);
        m.put("date", new SimpleDateFormat("dd").format(new Date()));
        
        return new ModelAndView("fish_meal/FMList", "model", m);
        
    }
    
    public ModelAndView adjustStock(HttpServletRequest request, HttpServletResponse response) {
        
        /* DATA | get initial value */
        String date = request.getParameter("date");
        int bags = Integer.parseInt(request.getParameter("bags"));
        int kilos = Integer.parseInt(request.getParameter("kilos"));
        
        /* DAO | Define needed dao here */
        FishMealDao fishMealDao = DaoFactory.createFishMealDao();
        
        /* TRANSACTION | Something complex here */
        fishMealDao.insertOrUpdateAdjustFM(bags, kilos, date, 1);
        
        return new ModelAndView("redirect:FishMeal.htm");
    }
    
    public void saveContent(HttpServletRequest request, HttpServletResponse response) 
        throws IOException, ParseException {
        
        /* DATA | get initial value */
        int id = 0;
        int fmId = 0;
        PrintWriter pw = response.getWriter();
        String masters = request.getParameter("master");
        String details = request.getParameter("detail");
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

        /* DAO | Define needed dao here */
        FishMealDao fishMealDao = DaoFactory.createFishMealDao();
        FishMealDtlDao fishMealDtlDao = DaoFactory.createFishMealDtlDao();

        /* TRANSACTION | Something complex here */
        // insert fish meal master
        if(masters != null) {
            String[] master = masters.split(":", -1);
            FishMeal fm = new FishMeal();
            fm.setFmMonth(master[0]);
            fm.setFmYear(master[1]);
            fm.setCreatedBy(lu.getUserId());
            fm.setCreatedDate(new Date());
            fmId = fishMealDao.insert(fm);
        }
        
        
        String[] detail = details.split(":", -1);
        FishMealDtl fmd = new FishMealDtl();
        fmd.setFmDate(new SimpleDateFormat("dd/MM/yyyy").parse(detail[0]));
        fmd.setFmBiBags(Integer.parseInt(detail[1]));
        fmd.setFmBiKilos(Integer.parseInt(detail[2]));
        fmd.setFmRS1Rm(Integer.parseInt(detail[3]));
        fmd.setFmRS1Bags(Integer.parseInt(detail[4]));
        fmd.setFmRS1Kilos(Integer.parseInt(detail[5]));
        fmd.setFmRS2Rm(Integer.parseInt(detail[6]));
        fmd.setFmRS2Bags(Integer.parseInt(detail[7]));
        fmd.setFmRS2Kilos(Integer.parseInt(detail[8]));
        fmd.setFmRS3Rm(Integer.parseInt(detail[9]));
        fmd.setFmRS3Bags(Integer.parseInt(detail[10]));
        fmd.setFmRS3Kilos(Integer.parseInt(detail[11]));
        fmd.setFmTtdBags(Integer.parseInt(detail[12]));
        fmd.setFmTtdKilos(Integer.parseInt(detail[13]));
        fmd.setFmIBags(Integer.parseInt(detail[14]));
        fmd.setFmIKilos(Integer.parseInt(detail[15]));
        fmd.setFmIPrice(new BigDecimal(detail[16]));
        fmd.setFmEiBags(Integer.parseInt(detail[17]));
        fmd.setFmEiKilos(Integer.parseInt(detail[18]));
//        fmd.setFmRMhrs(detail[19]);
//        fmd.setFmROthr(detail[20]);
        
        if(request.getParameter("status").equals("0")) {
            if(masters != null)
                fmd.setFmId(fmId);
            else
                fmd.setFmId(Integer.parseInt(request.getParameter("fmId")));
            
            fmd.setCreatedBy(lu.getUserId());
            fmd.setCreatedDate(new Date());
            id = fishMealDtlDao.insert(fmd);
            pw.print("{\"message\": true, \"fmId\": " + fmd.getFmId() + ", \"id\": " + id + "}");
        } else {
            fmd.setId(Integer.parseInt(request.getParameter("id")));
            fmd.setFmId(Integer.parseInt(request.getParameter("fmId")));
            fmd.setUpdatedBy(lu.getUserId());
            fmd.setUpdatedDate(new Date());
            fishMealDtlDao.update(fmd);
            pw.print("{\"message\": true, \"fmId\": " + fmd.getFmId() + ", \"id\": " + fmd.getId() + "}");
        }
        
    }
    
    public void getContent(HttpServletRequest request, HttpServletResponse response) 
        throws IOException {
        
        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        int year = Integer.parseInt(request.getParameter("year"));
        int month = Integer.parseInt(request.getParameter("month"));
        int currMonth = Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));

        /* DAO | Define needed dao here */
        FishMealDao fishMealDao = DaoFactory.createFishMealDao();
        FishMealDtlDao fishMealDtlDao = DaoFactory.createFishMealDtlDao();

        /* TRANSACTION | Something complex here */
        if(initYear == year && initMonth > month) {
            // can't access earlier data
            pw.print("{\"message\": \"Date is to old from our system!\"}");
            return;
        } else if(initYear <= year && month > currMonth) {
            // mustn't access future data
            pw.print("{\"message\": \"Date is to early from our system!\"}");
            return;
        } else if(initYear == year && initMonth == month) {
            // first time use
            if(fishMealDao.testFirstUse()) {
                pw.print("{\"message\": false, \"request\": true}");
                return;
            }
        }
        
        // get current month report
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, (month - 1));
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        
        List<FishMealDtl> fmds = fishMealDtlDao.findCurrentMonth(year, month, Integer.parseInt(new SimpleDateFormat("dd").format(cal.getTime())));
        if(fmds.isEmpty()) {
            if(month == 1) {
                year -= 1;
                month = 12;
            } else {
                month -= 1;
            }
            cal.add(Calendar.MONTH, -1);
            cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
            FishMealDtl fmd = fishMealDtlDao.findLastDate(year, month);
            if(sdf.format(fmd.getFmDate()).equals(sdf.format(cal.getTime())))
                pw.print("{\"message\": false, \"bags\": " + fmd.getFmEiBags() + ", \"kilos\": " + fmd.getFmEiKilos() + "}");
            else
                pw.print("{\"message\": \"Please complete all form previous month!\"}");
        } else {
            String val = fishMealDao.findAdjusted(year + "-" + month + "-01", 1);
            pw.print("{\"message\": false, \"fmId\": " + fmds.get(0).getFmId() + ", \"adjust\":\"" + (val == null ? "0:0" : val) + "\",\"data\": [");
            for(FishMealDtl x : fmds) {
                if(b)
                    pw.print(",");
                
                pw.print("{\"id\": " + x.getId() + ", "); 
                pw.print("\"biBags\": " + x.getFmBiBags() + ", ");
                pw.print("\"biKilos\": " + x.getFmBiKilos() + ",");
                pw.print("\"rS1Rm\": " + x.getFmRS1Rm() + ",");
                pw.print("\"rS1Bags\": " + x.getFmRS1Bags()+ ",");
                pw.print("\"rS1Kilos\": " + x.getFmRS1Kilos() + ",");
                pw.print("\"rS2Rm\": " + x.getFmRS2Rm() + ",");
                pw.print("\"rS2Bags\": " + x.getFmRS2Bags() + ",");
                pw.print("\"rS2Kilos\": " + x.getFmRS2Kilos() + ",");
                pw.print("\"rS3Rm\": " + x.getFmRS3Rm() + ",");
                pw.print("\"rS3Bags\": " + x.getFmRS3Bags() + ",");
                pw.print("\"rS3Kilos\": " + x.getFmRS3Kilos() + ",");
                pw.print("\"ttdBags\": " + x.getFmTtdBags() + ",");
                pw.print("\"ttdKilos\": " + x.getFmTtdKilos() + ",");
                pw.print("\"iBags\": " + x.getFmIBags() + ",");
                pw.print("\"iKilos\": " + x.getFmIKilos() + ",");
                pw.print("\"iPrice\": " + x.getFmIPrice() + ",");
                pw.print("\"eiBags\": " + x.getFmEiBags() + ",");
                pw.print("\"eiKilos\": " + x.getFmEiKilos() + ",");
                pw.print("\"rMhrs\": \"" + x.getFmRMhrs() + "\",");
                pw.print("\"rOthr\": \"" + x.getFmROthr() + "\"}");
                
                b = Boolean.TRUE;
            } pw.print("]}");
        }
    }
    
    public void confirmFirstUse(HttpServletRequest request, HttpServletResponse response) 
        throws IOException {
        
        /* DATA | get initial value */
        PrintWriter pw = response.getWriter();
        int bags = Integer.parseInt(request.getParameter("bags"));
        int kilos = Integer.parseInt(request.getParameter("kilos"));
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

        /* DAO | Define needed dao here */
        FishMealDao fishMealDao = DaoFactory.createFishMealDao();

        /* TRANSACTION | Something complex here */
        if(fishMealDao.insertFirstUse(bags, kilos, lu.getUserId()) != 0) {
            pw.print("{\"message\": true}");
        }
        
    }
    
//    public void getReport(HttpServletRequest request, HttpServletResponse response) {
//        
//        /* DATA | get initial value */
//
//        /* DAO | Define needed dao here */
//
//        /* TRANSACTION | Something complex here */
//        
//    }
    
}
