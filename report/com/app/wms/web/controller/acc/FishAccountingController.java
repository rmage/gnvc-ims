package com.app.wms.web.controller.acc;


import com.app.wms.engine.db.dao.CurrencyDao;
import com.app.wms.engine.db.dao.CurrencyRateDao;
import com.app.wms.engine.db.dao.FishDao;
import com.app.wms.engine.db.dao.FishWsDao;
import com.app.wms.engine.db.dto.Currency;
import com.app.wms.engine.db.dto.CurrencyRate;
import com.app.wms.engine.db.dto.Fish;
import com.app.wms.engine.db.dto.FishWs;
import com.app.wms.engine.db.exceptions.CurrencyDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


public class FishAccountingController extends MultiActionController {
    
    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    
    /* DAO | Define needed dao here */
    private final FishDao fishDao = DaoFactory.createFishDao();
    
    private final FishWsDao fishWsDao = DaoFactory.createFishWsDao();
    
    private final CurrencyDao currencyDao = DaoFactory.createCurrencyDao();
    
    private final CurrencyRateDao currencyRateDao = DaoFactory.createCurrencyRateDao();

    /* GNVS | New Fish Created Fish Inventory */
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> modelMap = new HashMap<String, Object>();
        
        List<Fish> fishes = new ArrayList<Fish>();
        
        /*QUERY ALL FISH REGISTERED*/
        fishes = fishDao.findAllActiveAndNotDelete();
        
        for (Fish f : fishes) {
            System.out.println("-------------------------PMK----------------------------");
            System.out.println(f.getId());
            System.out.println(f.getCode());
            System.out.println();
        }
        for (int i=0;i<fishes.size();i++) {
            String buffCode = fishes.get(i).getCode();
            buffCode = buffCode.replace(" ", "_");
            fishes.get(i).setCode(buffCode);
        }
        
        modelMap.put("fishes", fishes);
        return new ModelAndView("accounting/FishStockAccounting","model", modelMap);
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
        HashMap<String, Object> modelMap = new HashMap<String, Object>();
        Calendar cal = Calendar.getInstance();
        String[] parsedDate;
        
        List<List<CurrencyRate>> listOfCurrencyRateList = new ArrayList<List<CurrencyRate>>();
        List<CurrencyRate> currencyRateList;
        
        String fc = request.getParameter("fishcode");
        String dateAsOfString = request.getParameter("asOf");
        
        System.out.println("FC create " + fc);
        System.out.println("asOF create " + dateAsOfString);
        
        Date currDate = df.parse(dateAsOfString);
        
        /*GET LIST FISHWS*/
        List<FishWs> fishWses = new ArrayList<FishWs>();
        fishWses = fishWsDao.findWSWithinDateAndFishType(dateAsOfString, fc);
        
        /*GET CURRENCY LIST IN MASTER*/
        List<Currency> currs = new ArrayList<Currency>();
        try {
            currs = currencyDao.findAll();
        } catch (CurrencyDaoException currencyDaoException) {

        }
        
        for (int i=0;i<currs.size();i++) {
            currencyRateList = currencyRateDao.findByCurrencyCodeAndDate(currs.get(i).getCurrencyCode(), currDate);
            listOfCurrencyRateList.add(i, currencyRateList);
        }

        parsedDate = dateAsOfString.split("-");
        
        int date;
        if (Integer.valueOf(parsedDate[1]) != 2) {
            date = countDayOfMonth(Integer.valueOf(parsedDate[1]));
        } else {
            if ((Integer.valueOf(parsedDate[0]) % 4) == 0) {
                date = 29;
            } else {
                date = 28;
            }
        }
        
        System.out.println("Date month " + date);
        
        System.out.println("CURR CODE " + currs.get(0).getCurrencyCode());

        modelMap.put("currs", currs);
        modelMap.put("fishcode", fc);
        modelMap.put("asOf", dateAsOfString);
        modelMap.put("fishWses", fishWses);
        modelMap.put("listOfCurrencyRateList", listOfCurrencyRateList);

        return new ModelAndView("accounting/FishStockAccountingGenerate", "model", modelMap);
    }

    public boolean isLogin(HttpServletRequest request) {
        return request.getSession().getAttribute("user") != null;
    }
    
    public int countDayOfMonth(int month) {
        int result;
        
        result = 30 + ((month>7 ? 0 : 1) +month) % 2;
        
        return result;
    }

}
