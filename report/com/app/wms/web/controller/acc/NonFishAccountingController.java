package com.app.wms.web.controller.acc;

import com.app.wms.engine.db.dao.CurrencyDao;
import com.app.wms.engine.db.dao.CurrencyRateDao;
import com.app.wms.engine.db.dao.DrDao;
import com.app.wms.engine.db.dao.NonFishStockCardDao;
import com.app.wms.engine.db.dao.NonFishStockCardSummaryDao;
import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dao.ReceiveReportDao;
import com.app.wms.engine.db.dao.StockInventoryDao;
import com.app.wms.engine.db.dao.TsDao;
import com.app.wms.engine.db.dto.Dr;
import com.app.wms.engine.db.dto.NonFishStockCardAccounting;
import com.app.wms.engine.db.dto.NonFishStockCardSummary;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.ReceiveReport;
import com.app.wms.engine.db.dto.StockInventory;
import com.app.wms.engine.db.dto.Ts;
import com.app.wms.engine.db.exceptions.ProductDaoException;
import com.app.wms.engine.db.exceptions.StockInventoryDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


/* 
 @Author Faridzi Febrian
 Controller to Generate Transaction Report
 */
public class NonFishAccountingController extends MultiActionController {

    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    /* DAO | Define needed dao here */
    private final CurrencyDao currencyDao = DaoFactory.createCurrencyDao();

    private final CurrencyRateDao currencyRateDao = DaoFactory.createCurrencyRateDao();

    private final ProductDao productDao = DaoFactory.createProductDao();

    private final StockInventoryDao stockInventoryDao = DaoFactory.createStockInventoryDao();

    private final ReceiveReportDao receiveReportDao = DaoFactory.createReceiveReportDao();

    private final TsDao tsDao = DaoFactory.createTsDao();

    private final DrDao drDao = DaoFactory.createDrDao();
    
    private final NonFishStockCardDao nonFishStockCardDao = DaoFactory.createNonFishStockCardDao();

    private final NonFishStockCardSummaryDao nonFishStockCardSummaryDao = DaoFactory.createNonFishStockCardSummaryDao();

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> modelMap = new HashMap<String, Object>();

        return new ModelAndView("accounting/NonFishStockAccounting", "model", modelMap);
    }

    /**
     * Method 'create'
     *
     * @param request
     * @param response
     * @return ModelAndView
     */
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {

        HashMap<String, Object> modelMap = new HashMap<String, Object>();
        Product product = new Product();
        String productCode = request.getParameter("productcode");
        String dateAsOfString = request.getParameter("asOf");
        NonFishStockCardAccounting nfTemp;
        Date now = null;
        Date lastDateofThisMonth = null;

        /*SUMMARY STOCK CARD*/
        NonFishStockCardSummary nonFishStockCardSummary = new NonFishStockCardSummary();

        BeginningBalance begBalance = new BeginningBalance();

        List<ReceiveReport> receiveReportList;
        List<Ts> tsList;
        List<Dr> drList;

        Double lastMonthTotalQTY = 0d;
        Calendar aCalendar = Calendar.getInstance();
        String lastDateOfPreviousMonthString = "";
        String lastDateOfThisMonthString = "";

        try {
            now = df.parse(dateAsOfString);
        } catch (ParseException ex) {
            Logger.getLogger(NonFishAccountingController.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*GET PRODUCT BY PRODUCTCODE*/
        try {
            product = productDao.findByProductCode(productCode);
        } catch (ProductDaoException ex) {
            Logger.getLogger(NonFishAccountingController.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*CALCULATE LAST MONTH QTY FOR BEGINNING BALANCE*/
        /*BEGIN*/
        long start = System.currentTimeMillis();

        begBalance = calculateLastMonthQtyAndBalance(productCode, dateAsOfString);

        /*END*/
        long end = System.currentTimeMillis();
        long diff = end - start;
        System.out.println("Difference is : " + diff);

        /*GET LAST DATE*/
        /*SET DATE REPORT TO PREVIOUS MONTH LAST DAY*/
        aCalendar.setTime(now);

        /*SET LAST DAY OF MONTH*/
        aCalendar.set(Calendar.DATE, aCalendar.getActualMaximum(Calendar.DATE));

        Date lastDateOfThisMonth = aCalendar.getTime();
        lastDateOfThisMonthString = df.format(lastDateOfThisMonth);

        /*SET DATE REPORT TO PREVIOUS MONTH LAST DAY*/
        aCalendar.setTime(now);
        /*GET PREVIOUS MONTH*/
        aCalendar.add(Calendar.MONTH, -1);
        /*SET LAST DAY OF MONTH*/
        aCalendar.set(Calendar.DATE, aCalendar.getActualMaximum(Calendar.DATE));

        Date lastDateOfPreviousMonth = aCalendar.getTime();
        lastDateOfPreviousMonthString = df.format(lastDateOfPreviousMonth);

        /*INITIALIZE NON-FISH STOCK CARD LIST*/
        List<NonFishStockCardAccounting> nFStockCardList = new ArrayList<NonFishStockCardAccounting>();

        /*GET RR LIST*/
        receiveReportList = receiveReportDao.findByProductCode(productCode, dateAsOfString);
        for (ReceiveReport receiveReport : receiveReportList) {
            BigDecimal amountIDR;
            BigDecimal amountIN;
            /*DOCUMENT*/
            nfTemp = new NonFishStockCardAccounting();
            nfTemp.setStockCardDate(receiveReport.getRrDate());
            nfTemp.setCode("RR");
            nfTemp.setNumber(receiveReport.getRrCode());
            /*UNIT COST*/
            nfTemp.setCurrencyCode(receiveReport.getPo().getCurrency());
            nfTemp.setAmount(BigDecimal.valueOf(receiveReport.getUnitCost()));
            nfTemp.setRateValue(receiveReport.getPo().getCurrencyRate().getRateValue());
            System.out.println("-----RR------" + nfTemp.getRateValue() + "");
            System.out.println("-----RR------" + nfTemp.getAmount() + "");
            amountIDR = nfTemp.getRateValue().multiply(nfTemp.getAmount());
            nfTemp.setAmountIDR(amountIDR);
            /*IN*/
            nfTemp.setQuantityIN(receiveReport.getQty());
            nfTemp.setUnitCostIN(amountIDR);
            amountIN = amountIDR.multiply(BigDecimal.valueOf(nfTemp.getQuantityIN()));
            nfTemp.setAmountIN(amountIN);
            /*OUT*/
            /*THIS IS RR , SO OUT VALUE IS EMPTY*/

            /*ENDING*/
            nfTemp.setUnitCostEND(amountIDR);

            /*ADD NF TO LIST*/
            nFStockCardList.add(nfTemp);
        }

        /*GET TS LIST*/
        tsList = tsDao.findByProductCode(productCode, dateAsOfString);

        for (Ts ts : tsList) {
            BigDecimal amountIDR;
            BigDecimal amountOUT;
            /*DOCUMENT*/
            nfTemp = new NonFishStockCardAccounting();
            nfTemp.setStockCardDate(ts.getTsDate());
            nfTemp.setCode("TS");
            nfTemp.setNumber(ts.getTsCode() + "");
            /*UNIT COST*/
            /*UNIT COST HERE WILL BE CALCULATE WITH AVERAGE COST*/

            /*IN*/
            /*OUT*/
            nfTemp.setQuantityOUT(ts.getQty());

            /*ADD NF TO LIST*/
            nFStockCardList.add(nfTemp);
        }

        /*GET DR LIST*/
        drList = drDao.findByProductCode(productCode, dateAsOfString);

        for (Dr dr : drList) {
            BigDecimal amountIDR;
            BigDecimal amountOUT;
            /*DOCUMENT*/
            nfTemp = new NonFishStockCardAccounting();
            nfTemp.setStockCardDate(dr.getDrDate());
            nfTemp.setCode("DR");
            nfTemp.setNumber(dr.getDrCode() + "");
            /*UNIT COST*/
            /*UNIT COST HERE WILL BE CALCULATE WITH AVERAGE COST*/

            /*IN*/
            /*OUT*/
            nfTemp.setQuantityOUT(dr.getQty());

            /*ADD NF TO LIST*/
            nFStockCardList.add(nfTemp);
        }

        /*SORT NFSTOCK CARD LIST BY DATE*/
        Collections.sort(nFStockCardList, new Comparator<NonFishStockCardAccounting>() {
            public int compare(NonFishStockCardAccounting o1, NonFishStockCardAccounting o2) {
                if (o1.getStockCardDate() == null || o2.getStockCardDate() == null) {
                    return 0;
                }
                return o1.getStockCardDate().compareTo(o2.getStockCardDate());
            }
        });

        /*COUNT AVERAGE COST HERE*/
        Double tempTotalQty = 0d;
        Double tempAmount = 0d;
        BigDecimal tempTotalINIDR = BigDecimal.ZERO;
        int size = nFStockCardList.size();
        boolean isOut = false;

        /*INITIALIZE VAR FOR TOTALING*/
        Double totalQTY = begBalance.qty;
        Double totalQTYEnd = begBalance.qty;
        BigDecimal totalAmountEND = begBalance.balance;
        BigDecimal totalAmountIDR = BigDecimal.ZERO;
        BigDecimal totalINIDR = BigDecimal.ZERO;
        BigDecimal totalOUTIDR = BigDecimal.ZERO;
        BigDecimal balanceIDR = BigDecimal.ZERO;
        BigDecimal totalAmountIN = BigDecimal.ZERO;
        BigDecimal totalAmountOUT = BigDecimal.ZERO;
        Double totalQTYIN = 0d;
        Double totalQTYOUT = 0d;
        Double amountOut = begBalance.unitCost;

        /*COUNT AVERAGE COST AND TOTALING */
        for (int i = 0; i < size; i++) {
            NonFishStockCardAccounting nf = nFStockCardList.get(i);
            if (nf.getCode().equalsIgnoreCase("RR")) {
                if (isOut) {
                    tempTotalQty = 0d;
                    tempTotalINIDR = BigDecimal.ZERO;
                    isOut = false;
                    amountOut = nf.getAmountIN().doubleValue();
                }
                tempTotalQty += nf.getQuantityIN();
                tempTotalINIDR = tempTotalINIDR.add(nf.getAmountIN());
                amountOut = tempTotalINIDR.doubleValue() / tempTotalQty;

                totalQTYIN += nf.getQuantityIN();
                totalAmountIN = totalAmountIN.add(nf.getAmountIN());

                totalQTY += nf.getQuantityIN();
            }

            if (nf.getCode().equalsIgnoreCase("TS") || nf.getCode().equalsIgnoreCase("DR")) {
                Double qtyOut = nf.getQuantityOUT();
                if (tempTotalQty > 0) {
                    amountOut = tempTotalINIDR.doubleValue() / tempTotalQty;
                }
                nFStockCardList.get(i).setUnitCostOUT(BigDecimal.valueOf(amountOut));
                nFStockCardList.get(i).setAmountOUT(BigDecimal.valueOf(amountOut * qtyOut));

                totalQTYOUT += nf.getQuantityOUT();
                totalAmountOUT = totalAmountOUT.add(nFStockCardList.get(i).getAmountOUT());

                /*SET ENDING BALANCE UNIT COST*/
                nFStockCardList.get(i).setUnitCostEND(BigDecimal.valueOf(amountOut));
                isOut = true;

                totalQTY -= qtyOut;
            }

            /*SET ENDING BALANCE*/
            totalQTYEnd += nf.getQuantityIN();
            totalQTYEnd -= nf.getQuantityOUT();

            totalAmountEND = totalAmountEND.add(nf.getAmountIN());
            totalAmountEND = totalAmountEND.subtract(nf.getAmountOUT());

            nFStockCardList.get(i).setQuantityEND(totalQTYEnd);
            nFStockCardList.get(i).setAmountEND(totalAmountEND);

            nf = nFStockCardList.get(i);
            
            nFStockCardList.get(i).setProductId(product.getProductId());
            nFStockCardList.get(i).setProductCategory(product.getProductCategory());

            /*FOR TOTALING*/
            totalAmountIDR = totalAmountIDR.add(nf.getAmountIDR());
        }

        balanceIDR = totalAmountEND;
        
        /*INSERT OR UPDATE INTO TABLE nf_stock_card*/
        for (NonFishStockCardAccounting nf : nFStockCardList) {
            boolean isExist = false;
            isExist = nonFishStockCardDao.isExist(nf);
            if (isExist) {
                /*UPDATE*/
                nonFishStockCardDao.update(nf);
            } else {
                /*INSERT*/
                nonFishStockCardDao.insert(nf);
            }
        }

        try {
            lastDateofThisMonth = df.parse(lastDateOfThisMonthString);
        } catch (ParseException ex) {
            Logger.getLogger(NonFishAccountingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*SET STOCK CARD SUMMARY*/
        nonFishStockCardSummary.setProductCode(product.getProductCode());
        nonFishStockCardSummary.setProduct(product);
        nonFishStockCardSummary.setAsOFDate(now);
        nonFishStockCardSummary.setQuantity(totalQTY);
        nonFishStockCardSummary.setUnitCost(BigDecimal.valueOf(amountOut));
        nonFishStockCardSummary.setAmountToDate(balanceIDR);
        nonFishStockCardSummary.setBeginningAmount(begBalance.balance);
        BigDecimal trxAmount = balanceIDR.subtract(begBalance.balance);
        nonFishStockCardSummary.setTransactionAmount(trxAmount);
        nonFishStockCardSummary.setProductCategory(product.getProductCategory());

        /*INSERT INTO STOCK CARD SUMMARY*/
        /*CHECK IF EXIST*/
        boolean exist = nonFishStockCardSummaryDao.isExist(product.getProductCode(), lastDateOfThisMonthString);
        if (exist) {
            /*UPDATE*/
            NonFishStockCardSummary nfsTemp = new NonFishStockCardSummary();
            nfsTemp = nonFishStockCardSummaryDao.findByProductCodeAndDate(product.getProductCode(), lastDateOfThisMonthString);
            if (now.compareTo(nfsTemp.getAsOFDate()) > 0) {
                System.out.println("UPDATE");
                nonFishStockCardSummary.setId(nfsTemp.getId());
                nonFishStockCardSummaryDao.update(nonFishStockCardSummary);
            }
        } else {
            /*INSERT*/
            nonFishStockCardSummaryDao.insert(nonFishStockCardSummary);
        }

        modelMap.put("product", product);
        modelMap.put("productCat", product.getProductCategory());
        modelMap.put("asOf", dateAsOfString);
        modelMap.put("nFStockCardList", nFStockCardList);
        modelMap.put("lastDateOfPreviousMonth", lastDateOfPreviousMonthString);

        /*ADD TOTALING TO MAP*/
        modelMap.put("totalQTY", totalQTY);
        modelMap.put("totalAmountIDR", totalAmountIDR);
        modelMap.put("totalINIDR", totalINIDR);
        modelMap.put("totalOUTIDR", totalOUTIDR);
        modelMap.put("balanceIDR", balanceIDR);
        /*IN*/
        modelMap.put("totalQTYIN", totalQTYIN);
        modelMap.put("totalAmountIN", totalAmountIN);

        /*OUT*/
        modelMap.put("totalQTYOUT", totalQTYOUT);
        modelMap.put("totalAmountOUT", totalAmountOUT);

        /*END BALANCE*/
        modelMap.put("totalQTYEND", totalQTYEnd);
        modelMap.put("totalAmountEND", totalAmountEND);

        modelMap.put("lastMonthBalance", begBalance.balance);
        modelMap.put("lastMonthQTY", begBalance.qty);

        return new ModelAndView("accounting/NonFishStockAccountingGenerate", "model", modelMap);
    }

    /*METHOD 'getProdut' */
    public void getProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, StockInventoryDaoException {

        /* DATA | get initial value */
        Boolean b = Boolean.FALSE;
        PrintWriter pw = response.getWriter();
        String term = request.getParameter("term");

        /* DAO | Define needed dao here */
        ProductDao productDao = DaoFactory.createProductDao();
        StockInventoryDao stockInventoryDao = DaoFactory.createStockInventoryDao();

        /* TRANSACTION | Something complex here */
        pw.print("[");
        List<Product> ps;
        if (request.getParameter("mode").equals("name")) {
            ps = productDao.findWhereProductNameEquals(term, 20);
        } else {
            ps = productDao.findWhereProductCodeEquals(term, 20);
        }
        for (Product x : ps) {
            if (b) {
                pw.print(",");
            }

            StockInventory si = stockInventoryDao.findWhereProductCodeEquals(x.getProductCode()).get(0);

            pw.print("{\"itemCode\": \"" + x.getProductCode() + "\", ");
            pw.print("\"itemName\": \"" + x.getProductName() + "\",");
            pw.print("\"soh\": \"" + si.getBalance() + "\",");
            pw.print("\"uom\": \"" + x.getUom() + "\"}");

            b = Boolean.TRUE;

        }
        pw.print("]");

    }

    public boolean isLogin(HttpServletRequest request) {
        return request.getSession().getAttribute("user") != null;
    }

    public int countDayOfMonth(int month) {
        int result;

        result = 30 + ((month > 7 ? 0 : 1) + month) % 2;

        return result;
    }

    private BeginningBalance calculateLastMonthQtyAndBalance(String productCode, String lastMonth) {
        List<ReceiveReport> receiveReportList;
        List<Ts> tsList;
        List<Dr> drList;

        BeginningBalance result = new BeginningBalance();

        List<NonFishStockCardAccounting> nFStockCardList = new ArrayList<NonFishStockCardAccounting>();
        NonFishStockCardAccounting nfTemp;

        /*GET RR LIST*/
        receiveReportList = receiveReportDao.findByProductCodeAndBeforeThan(productCode, lastMonth);
        for (ReceiveReport receiveReport : receiveReportList) {
            BigDecimal amountIDR;
            BigDecimal amountIN;
            /*DOCUMENT*/
            nfTemp = new NonFishStockCardAccounting();
            nfTemp.setStockCardDate(receiveReport.getRrDate());
            nfTemp.setCode("RR");
            nfTemp.setNumber(receiveReport.getRrCode());
            /*UNIT COST*/
            nfTemp.setCurrencyCode(receiveReport.getPo().getCurrency());
            nfTemp.setAmount(BigDecimal.valueOf(receiveReport.getUnitCost()));
            nfTemp.setRateValue(receiveReport.getPo().getCurrencyRate().getRateValue());
            amountIDR = nfTemp.getRateValue().multiply(nfTemp.getAmount());
            nfTemp.setAmountIDR(amountIDR);
            /*IN*/
            nfTemp.setQuantityIN(receiveReport.getQty());
            nfTemp.setUnitCostIN(amountIDR);
            amountIN = amountIDR.multiply(BigDecimal.valueOf(nfTemp.getQuantityIN()));
            nfTemp.setAmountIN(amountIN);
            /*OUT*/
            /*THIS IS RR , SO OUT VALUE IS EMPTY*/

            /*ADD NF TO LIST*/
            nFStockCardList.add(nfTemp);
        }

        /*GET TS LIST*/
        tsList = tsDao.findByProductCodeAndBeforeThan(productCode, lastMonth);

        for (Ts ts : tsList) {
            BigDecimal amountIDR;
            BigDecimal amountOUT;
            /*DOCUMENT*/
            nfTemp = new NonFishStockCardAccounting();
            nfTemp.setStockCardDate(ts.getTsDate());
            nfTemp.setCode("TS");
            nfTemp.setNumber(ts.getTsCode() + "");
            /*UNIT COST*/
            /*UNIT COST HERE WILL BE CALCULATE WITH AVERAGE COST*/

            /*IN*/
            /*OUT*/
            nfTemp.setQuantityOUT(ts.getQty());

            /*ADD NF TO LIST*/
            nFStockCardList.add(nfTemp);
        }

        /*GET DR LIST*/
        drList = drDao.findByProductCodeAndBeforeThan(productCode, lastMonth);

        for (Dr dr : drList) {
            BigDecimal amountIDR;
            BigDecimal amountOUT;
            /*DOCUMENT*/
            nfTemp = new NonFishStockCardAccounting();
            nfTemp.setStockCardDate(dr.getDrDate());
            nfTemp.setCode("DR");
            nfTemp.setNumber(dr.getDrCode() + "");
            /*UNIT COST*/
            /*UNIT COST HERE WILL BE CALCULATE WITH AVERAGE COST*/

            /*IN*/
            /*OUT*/
            nfTemp.setQuantityOUT(dr.getQty());

            /*ADD NF TO LIST*/
            nFStockCardList.add(nfTemp);
        }

        /*SORT NFSTOCK CARD LIST BY DATE*/
        Collections.sort(nFStockCardList, new Comparator<NonFishStockCardAccounting>() {
            public int compare(NonFishStockCardAccounting o1, NonFishStockCardAccounting o2) {
                if (o1.getStockCardDate() == null || o2.getStockCardDate() == null) {
                    return 0;
                }
                return o1.getStockCardDate().compareTo(o2.getStockCardDate());
            }
        });

        /*COUNT AVERAGE COST HERE*/
        Double tempTotalQty = 0d;
        Double tempAmount = 0d;
        BigDecimal tempTotalINIDR = BigDecimal.ZERO;
        int size = nFStockCardList.size();
        boolean isOut = false;

        /*INITIALIZE VAR FOR TOTALING*/
        Double totalQTY = 0d;
        Double totalQTYEnd = 0d;
        BigDecimal totalAmountEND = BigDecimal.ZERO;
        BigDecimal totalAmountIDR = BigDecimal.ZERO;
        BigDecimal totalINIDR = BigDecimal.ZERO;
        BigDecimal totalOUTIDR = BigDecimal.ZERO;
        BigDecimal balanceIDR = BigDecimal.ZERO;
        Double amountOut = 0d;

        /*COUNT AVERAGE COST*/
        for (int i = 0; i < size; i++) {
            NonFishStockCardAccounting nf = nFStockCardList.get(i);
            if (nf.getCode().equalsIgnoreCase("RR")) {
                if (isOut) {
                    tempTotalQty = 0d;
                    tempTotalINIDR = BigDecimal.ZERO;
                    isOut = false;
                    amountOut = nf.getAmountIN().doubleValue();
                }
                tempTotalQty += nf.getQuantityIN();
                tempTotalINIDR = tempTotalINIDR.add(nf.getAmountIN());
                amountOut = tempTotalINIDR.doubleValue() / tempTotalQty;

                totalQTY += nf.getQuantityIN();
            }

            if (nf.getCode().equalsIgnoreCase("TS") || nf.getCode().equalsIgnoreCase("DR")) {
                Double qtyOut = nf.getQuantityOUT();
                if (tempTotalQty > 0) {
                    amountOut = tempTotalINIDR.doubleValue() / tempTotalQty;
                }
                nFStockCardList.get(i).setUnitCostOUT(BigDecimal.valueOf(amountOut));
                nFStockCardList.get(i).setAmountOUT(BigDecimal.valueOf(amountOut * qtyOut));

                /*SET ENDING BALANCE UNIT COST*/
                nFStockCardList.get(i).setUnitCostEND(BigDecimal.valueOf(amountOut));
                isOut = true;

                totalQTY -= qtyOut;
            }

            /*SET ENDING BALANCE*/
            totalQTYEnd += nf.getQuantityIN();
            totalQTYEnd += nf.getQuantityOUT();

            totalAmountEND = totalAmountEND.add(nf.getAmountIN());
            totalAmountEND = totalAmountEND.subtract(nf.getAmountOUT());

            nFStockCardList.get(i).setQuantityEND(totalQTYEnd);
            nFStockCardList.get(i).setAmountEND(totalAmountEND);

            nf = nFStockCardList.get(i);

            /*FOR TOTALING*/
            totalAmountIDR = totalAmountIDR.add(nf.getAmountIDR());

        }

//        balanceIDR = totalINIDR.subtract(totalOUTIDR);
        balanceIDR = totalAmountEND;

        result.balance = balanceIDR;
        result.qty = totalQTY;
        /*THIS IS FOR NEXT AVERAGE COST*/
        result.unitCost = amountOut;

        return result;
    }

    private class BeginningBalance {

        Double qty;
        BigDecimal balance;
        Double unitCost;

        public BeginningBalance() {
            this.qty = 0d;
            this.balance = BigDecimal.ZERO;
            this.unitCost = 0d;
        }
    }

}
