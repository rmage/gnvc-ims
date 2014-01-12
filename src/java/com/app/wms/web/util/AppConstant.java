/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.util;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 *
 * @author root
 */
public class AppConstant {

    public static Integer paging = 5;
    public static final String EOL = ";"; //System.getProperty("line.separator");
    public static final BigDecimal CASH_OUT_MIN_RETAIL = new BigDecimal("1000000");
    public static final boolean DEBUG = false;   //  set it to false when LIVE. JECO August 17, 2010 15:35 GMT +7
    //  PAYMENT METHOD
    public static final String PAYMENT_METHOD_CASH = "CASH";
    public static final String PAYMENT_METHOD_CREDIT = "CREDIT_CARD";
    public static final String PAYMENT_METHOD_DEBIT = "DEBIT_CARD";
    public static final String PAYMENT_METHOD_DEPOSIT = "DEPOSIT";
    //  POSTPAID PAYMENT TYPE
    // public static final String PAYMENT_TYPE_REGULAR_LESS = "LESS_PAID";   // <
    public static final String PAYMENT_TYPE_REGULAR_FULL = "FULL_PAID"; // >=
    public static final String PAYMENT_TYPE_DEPOSIT = "DEPOSIT";
    public static final String PAYMENT_TYPE_DEPARTMENT = "DEPARTMENT";
    public static final String PAYMENT_TYPE_CORPORATE = "CORPORATE";
    public static final String PAYMENT_LEVEL_REGULAR = "";
    public static final String PAYMENT_LEVEL_DEPARTMENT = "department";
    public static final String PAYMENT_LEVEL_CORPORATE = "root";
    //  POSTPAID
    public static final String TIBCO_RESPONSE_CODE_SUCCESS = "00000";
    public static final String TIBCO_RESPONSE_CODE_FAILED = "XXXXXX";//10.64.20.33 for testing

    //POSTPAID IP AND URL
    /*
    * LIVE IP :10.64.24.74
    *
    */

    //testing= 10.64.1.57:4848 //"//10.64.1.88:4848/";//  should not contain "http:"
    public static final String TIBCO_BILLING_SERVICE_URL = "//10.64.24.74:4848/";
    //public static final String TIBCO_BILLING_SERVICE_URL = "http://localhost:8080/possWeb/tibco-billing-service";

    //testing= 10.64.1.57:4646 //"//10.64.24.74:4848/";//"//10.64.1.88:4646/";//  should not contain "http:"
    public static final String TIBCO_GET_PAYMENT_SERVICE_URL = "//10.64.24.74:4646/";
    //public static final String TIBCO_GET_PAYMENT_SERVICE_URL = "http://localhost:8080/possWeb/tibco-get-payment-service";

    //testing= 10.64.1.57:4747 //"//10.64.24.74:4848/";//"//10.64.1.88:4747/";//  should not contain "http:"
    public static final String TIBCO_PAYMENT_SERVICE_URL = "//10.64.24.74:4747/";
    //public static final String TIBCO_PAYMENT_SERVICE_URL = "http://localhost:8080/possWeb/tibco-payment-service";

    //testing= 10.64.1.57:4545 //"//10.64.1.88:4545/";//  should not contain "http:"
    public static final String TIBCO_CANCEL_PAYMENT_SERVICE_URL = "//10.64.24.74:4545/";
    //public static final String TIBCO_CANCEL_PAYMENT_SERVICE_URL = "http://localhost:8080/possWeb/tibco-cancel-payment-service";

    public static final String CASH_TYPE_POSTPAID = "IN_POSTPAID_PAYMENT";
    public static final String AMOUNT_FORMAT = "#";
    public static final String AMOUNT_FORMAT_TWO_DIGIT = "#,###.##";
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_VIEW = "yyyy-MM-dd HH:mm:ss.S";
    public static final String DATE_FORMAT_OBJECT = "EEE MMM dd HH:mm:ss zzz yyyy";
    public static final String WH_CODE_STOCK_DEFAULT = "WH0";
    public static final String WH_CODE_STOCK_CASHIER = "WH1";
    public static final String WH_CODE_STOCK_RETURN = "WHR";
    public static final String WH_CODE_STOCK_MOVING = "WHT";
    public static final String WH_CODE_AGENT = "WHA";
    public static final String WH_CODE_SALES = "WHS";
    public static final String WH_CODE_STOCK_ADJUSTMENT = "WHJ";
    public static final String WH_CODE_STOCK_READY_FOR_SALES = "WH1";
    public static final String AGENT_TEAM_LEADER = "TL";
    public static final String AGENT_TEAM_MEMBER = "TM";
    public static final String STATUS_FALSE = "N";
    public static final String STATUS_CONFIRMED = "Y";
    public static final String SALES_STATUS_OPEN = "OPEN";
    public static final String SALES_STATUS_CONFIRM = "CONFIRM";
    public static final String SALES_STATUS_PAIDPART = "PAID_PART";
    public static final String SALES_STATUS_PAID = "PAID";
    public static final String SALES_STATUS_JOURNALPART = "JOURNAL_PART";
    public static final String SALES_STATUS_JOURNAL = "JOURNAL";
    //++++++++++++
    public static final String RESERVATION = "RESERVATION";
    public static final String RESERVATION_STATUS_NEW = "NEW";
    //++++++++++++
    public static final String TRANS_TYPE_SALES = "WIC_INVOICE";
    public static final String TRANS_TYPE_PAYMENT = "WIC_PAYMENT_RECEIPT";
    //+++++++++++
    public static final String TRANS_TYPE_PETTY_CYCLE = "PETTY_CYCLE";
    public static final String TRANS_TYPE_PETTY_OUT = "PETTY_OUT";
    public static final String TRANS_TYPE_PETTY_IN = "PETTY_IN";
    // ------ stock repair
    public static final String REP_STOCK_CYCLE = "REP_STOCK_CYCLE";
    // ------ ir document
    public static final String TRANS_TYPE_IR_CYCLE = "IR_REQUEST";//WIC_IR_CYCLE";
    public static final String TRANS_TYPE_SR_CYCLE = "IR_STOCK_RETURN";//"WIC_SR_CYCLE";
    public static final String TRANS_TYPE_SR_NS_CYCLE = "IR_STOCK_RETURN_NS";//"WIC_SR_CYCLE";
    //    public static final String TRANS_TYPE_SM_CYCLE = "INT_MOVING_SERIALIZED";//;"WIC_SM_CYCLE";
//    public static final String TRANS_TYPE_SM_NS_CYCLE = "INT_MOVING_NON_SERIALIZED";//;"WIC_SM_CYCLE";
    public static final String TRANS_TYPE_IRM_CYCLE = "IR_MOVING_EXTERNAL";
    public static final String TRANS_TYPE_IRM_NS_CYCLE = "IR_MOVING_EXTERNAL_NS";
    public static final String TRANS_TYPE_SA_DEBET = "IR_ADJ_DEBET";//;"WIC_SM_CYCLE";
    public static final String TRANS_TYPE_SA_CREDIT = "IR_ADJ_CREDIT";//IR_ADJ_CREDIT
    public static final String TRANS_TYPE_SA_DEBET_NS = "IR_ADJ_DEBET_NS";//;"WIC_SM_CYCLE";
    public static final String TRANS_TYPE_SA_CREDIT_NS = "IR_ADJ_CREDIT_NS";//IR_ADJ_CREDIT
    public static final String TRANS_TYPE_IR_GOOD_RECEIVE = "IR_GOOD_RECEIVE";
    public static final String TRANS_TYPE_IR_GOOD_RECEIVE_NS = "IR_GOOD_RECEIVE_NS";
    public static final String TRANS_TYPE_IR_GOOD_RECEIVE_MOVING = "IR_GOOD_RECEIVE_MOVING";
    public static final String TRANS_TYPE_IR_GOOD_RECEIVE_MOVING_NS = "IR_GOOD_RECEIVE_MOVING_NS";
    //
    public static final String TRANS_TYPE_IR_SR_UNDELIVERED = "IR_STOCK_RETURN_ND";
    public static final String TRANS_TYPE_IR_SR_UNDOCUMENTED = "IR_STOCK_RETURN_UN";
    // --------
    public static final String TRANS_TYPE_CASH_OPEN = "CASH_OPEN";
    public static final String TRANS_TYPE_CASH_CLOSE = "CASH_CLOSE";
    public static final String TRANS_TYPE_CASH_HANDOVER = "CASH_HANDOVER";
    public static final String TRANS_TYPE_CASH_CYCLE = "CASH_CYCLE";
    //---------
    public static final String TRANS_TYPE_STOCK_OPEN = "STOCK_OPEN";
    public static final String TRANS_TYPE_STOCK_CLOSE = "STOCK_CLOSE";
    public static final String TRANS_TYPE_STOCK_CYCLE = "STOCK_CYCLE";
//    // ---------
//    public static final String TRANS_TYPE_UPLOAD_PRODUCT="PRODUCT_UPLOAD";
//    public static final String TRANS_TYPE_UPLOAD_SERVICE="PRODUCT_UPLOAD";
//    public static final String TRANS_TYPE_UPLOAD_BUNDLE="PRODUCT_UPLOAD";
    // -----
    public static final String TRANS_TYPE_AGENT_IN = "DSA_AGENT_IN"; // DSA SAJA LAH YG DIPAKE
    public static final String TRANS_TYPE_AGENT_OUT = "DSA_AGENT_OUT";
    public static final String TRANS_TYPE_AGENT_SALES = "DSA_SALES";
    public static final String TRANS_TYPE_AGENT_STOCK_CYCLE = "AGENT_STOCK_CYCLE";
    //----------------
    public static final String TRANS_TYPE_REPAIR_SERVICE = "WIC_SERVICE"; // JECO. July 10, 2010 - TO BE ADVISE BY Triyono
    public static final String TRANS_TYPE_POSTPAID_PAYMENT = "WIC_POSTPAID_PAYMENT"; // JECO. July 14, 2010 - TO BE ADVISE BY Triyono
    public static final String TRANS_TYPE_REPAIR_MOVE_TO_SP = "REP_MOVE_INTERNAL_SP"; // harap diganti
    public static final String TRANS_TYPE_REPAIR_MOVE_TO_BG = "REP_MOVE_INTERNAL_BG";
    public static final String TRANS_TYPE_REPAIR_MOVE_TO_VENDOR = "REP_MOVE_EXTERNAL_OUT";
    public static final String TRANS_TYPE_REPAIR_MOVE_FROM_VENDOR = "REP_MOVE_EXTERNAL_IN";
    public static final String NORMAL_WIC = "NORMAL_WIC";
    public static final String NORMAL_DSA = "NORMAL_DSA";
    public static final String BG_TYPE_WIC = "BG_WIC";
    public static final String BG_TYPE_DSA = "BG_DSA";
    //----------------
    public static final String MAIL_HOST = "smtp.gmail.com";
    public static final String MAIL_USERNAME = "username";
    public static final String MAIL_PASSWORD = "password";
    //-----------------------------------------------------
    public static final String EVENT_CODE_RECEIVE = "RECEIVED";
    public static final String EVENT_CODE_SOLD = "SOLD";
    public static final String EVENT_CODE_RETURN = "RETURN";
    public static final String ICCID_DEFAULT = "62899";
    public static final String STATUS_CODE_GR_NCNF_SN_UNDELIVERED = "SN_UNDELIVERED";
    public static final String STATUS_CODE_GR_NCNF_SN_UNDOCUMENTED = "SN_UNDOCUMENTED";
    public static final String TRANS_TYPE_IR_STOCK = "IR_STOCK";
    public static final String BG_IR_STOCK = "JKT-WIC-00";
    public static final String EMAIL_FROM = "poss.support@three.co.id";
    // untuk tingkatan tingkatan IR approval, di pindahkan dari IRApprovalController
    public static final int APP_CONFIRM_BY_SCM = 40;
    public static final int APP_CONFIRM_BY_DSA_GM = 35;
    public static final int APP_CONFIRM_BY_WICBM_OR_DSABM = 30;
    public static final int APP_CONFIRM_BY_WICSM_OR_DSASPV = 20;
    public static final int APP_CONFIRM_BY_WICSPV_OR_PUPADMIN = 10;
    public static final int APP_NOTCONFIRM = 0;
    public static final int APP_SENT_TO_ERP = 1000;
    public static final int APP_GRN_CONFIRMED = 120;

    public static HashMap createMapLevel() {
        HashMap mapWIC = new HashMap();
        // di appriveLevel, tipe nya BigDecimal (?) so ini harus ngikut!
        // tambahkan map
        mapWIC.put(new BigDecimal(AppConstant.APP_NOTCONFIRM), "Not Confirm");
        mapWIC.put(new BigDecimal(AppConstant.APP_CONFIRM_BY_WICSPV_OR_PUPADMIN), "Supervisor");
        mapWIC.put(new BigDecimal(AppConstant.APP_CONFIRM_BY_WICSM_OR_DSASPV), "Manager");
        mapWIC.put(new BigDecimal(AppConstant.APP_CONFIRM_BY_WICBM_OR_DSABM), "BM");
        mapWIC.put(new BigDecimal(AppConstant.APP_CONFIRM_BY_SCM), "SCM");
        mapWIC.put(new BigDecimal(AppConstant.APP_SENT_TO_ERP), "Sent to ERP");
        mapWIC.put(new BigDecimal(AppConstant.APP_GRN_CONFIRMED), "GRN");
        HashMap mapDSA = new HashMap();
        mapDSA.put(new BigDecimal(AppConstant.APP_NOTCONFIRM), "Not Confirm");
        mapDSA.put(new BigDecimal(AppConstant.APP_CONFIRM_BY_WICSPV_OR_PUPADMIN), "Admin PUP");
        mapDSA.put(new BigDecimal(AppConstant.APP_CONFIRM_BY_WICSM_OR_DSASPV), "Supervisor");
        mapDSA.put(new BigDecimal(AppConstant.APP_CONFIRM_BY_WICBM_OR_DSABM), "BM");
        mapDSA.put(new BigDecimal(AppConstant.APP_CONFIRM_BY_DSA_GM), "GM");
        mapDSA.put(new BigDecimal(AppConstant.APP_CONFIRM_BY_SCM), "SCM");
        mapDSA.put(new BigDecimal(AppConstant.APP_SENT_TO_ERP), "Sent to ERP");
        mapWIC.put(new BigDecimal(AppConstant.APP_GRN_CONFIRMED), "GRN");
        HashMap mapLevel = new HashMap();
        mapLevel.put("BG_WIC", mapWIC);
        mapLevel.put("BG_DSA", mapDSA);
        return mapLevel;
    }
}
