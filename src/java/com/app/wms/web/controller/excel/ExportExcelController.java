package com.app.wms.web.controller.excel;

import com.app.wms.engine.db.dao.UserDao;
import com.app.wms.web.helper.DateHelper;
import com.app.wms.engine.db.dto.GenericDtoReport;
import com.app.wms.engine.db.dto.User;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.web.excel.ExcelExport;
import com.app.wms.engine.io.PossFileUtils;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class ExportExcelController extends MultiActionController {

    protected String parametersKey = "";
    protected static Connection con;
    protected String templateName = "";
    protected ServletContext context;
    protected static final String sp = File.separator;
    protected BasicDataSource PossDs;
    protected GenericDtoReport gdr;
    protected String returnPath = "report/rptAgentList";
    protected ArrayList<String> parameterValues = new ArrayList<String>();
    protected ArrayList<String> parameterKeys = new ArrayList<String>();

    private static ExcelExport xlEx;
    private static final int DT_STRING = 0;
    private static final int DT_NUMERIC = 1;
    private static final int DT_DATE = 2;
    private static final int DT_BOOLEAN = 3;
    private static final int AL_LEFT = 3;
    private static final int AL_CENTER = 4;
    private static final int AL_RIGHT = 5;

    private ResultSet res = null;
    private PreparedStatement preparedStatement = null;

    protected static Log log = LogFactory.getLog(ExportExcelController.class);

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            if (lu == null) {
                log.warn("no login detected");
                return new ModelAndView("login");
            }
            
            String requestPath = request.getRequestURL().toString();
            String requestTail = PossFileUtils.displayFilenameOnly(requestPath);
            returnPath = "report_excel/" + requestTail;
            log.info(returnPath);
            PossDs = DaoFactory.getReportDataSource();
            con = PossDs.getConnection();
            context = getServletContext();
            if (con.isClosed()) {
                log.warn("failed to connect with oracle");
            }
            if (gdr == null) {
                log.warn("create new gdr object");
                gdr = new GenericDtoReport();
            }
            
            log.info(gdr);
            /*
            List<Bg> bgList = new ArrayList();
            BgDao bg = DaoFactory.createBgDao();
       
            if (lu.getIsDsa().equalsIgnoreCase("y") && lu.getIsWic().equalsIgnoreCase("y")) {
                gdr.setBgType("%%");
                log.info("superadmin detected");
                bgList = bg.findByAuthLogin(lu.getUserId());
            } else if (lu.getIsDsa().equalsIgnoreCase("y")) {
                gdr.setBgType("BG_DSA");
                log.info("dsa detected");
                bgList = bg.findByAuthLogin(lu.getUserId());
            } else if (lu.getIsWic().equalsIgnoreCase("y")) {
                gdr.setBgType("BG_WIC");
                log.info("wic detected");
                bgList = bg.findByAuthLogin(lu.getUserId());
            } else {
                log.info("current user doesn't have wic or dsa membership");
                gdr.setBgType("null");
                bgList = bg.findByAuthLogin(lu.getUserId());
            }
            */
            boolean isALL = false;

//            if (bgList.size() > 1) {
//                // multiple org
//                isALL = true;
//            }            
            HashMap model = new HashMap();
//            model.put("bgList", bgList);
            model.put("lu", lu);
            model.put("isALL", isALL);

            ModelAndView mav = new ModelAndView(returnPath);
            mav.addObject("dto", gdr);
            mav.addObject("model", model);

            return mav;

        } catch (SQLException ex) {
            log.error("Error ExcelExportController.findByPrimaryKey");
            log.error(ex);
            return new ModelAndView("Error");
        }
        //return new ModelAndView(returnPath, "dto", gdr);
    }    

    public void saveExcell(HttpServletRequest request, HttpServletResponse response, GenericDtoReport oCommand) throws Exception {
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        UserDao userDao = DaoFactory.createUserDao();
     //   User user = userDao.findByPrimaryKey(new BigDecimal(lu.getUserId()));
        
        FileInputStream fin = null;
        if (oCommand == null) {
            log.warn("zero command object");
        }

        if (gdr == null) {
            log.warn("bean model was resetted, hence empty");
        }

        if (con == null) {
            log.warn("connection object was closed attempting to re-connect with oracle");
            PossDs = DaoFactory.getReportDataSource();
            con = PossDs.getConnection();
        }
        if (con.isClosed()) {
            log.warn("failed to connect with oracle attempting to re-connect with oracle");
            con = PossDs.getConnection();
        }

        gdr = oCommand;
        templateName = request.getParameter("templateName");
        parametersKey = request.getParameter("parametersKey");

        java.util.Date now = new java.util.Date();
        String printDate = DateHelper.dateToString(DateHelper.DEFAULT_DATETIME_PATTERN, now);

        log.info("gdr parameter size: " + gdr.getParameterValues().size());
        setParameterValues(gdr.getParameterValues());
        
        String filePath = "";
        if ( templateName.equalsIgnoreCase("xlsPostPaidPaymentReport") ) {
//            filePath = postpaidPaymentReport(templateName, printDate, user.getName());
        } else if ( templateName.equalsIgnoreCase("xlsSalesEvoucherReport") ) {
 //           filePath = salesEvoucherReport(templateName, printDate, user.getName());
        } else if ( templateName.equalsIgnoreCase("xlsSalesServiceReport") ) {
  //          filePath = salesServiceReport(templateName, printDate, user.getName());
        } else if ( templateName.equalsIgnoreCase("xlsSalesConsignmentReport") ) {
            filePath = salesConsignmentReport(templateName, printDate, printDate);
        } else if ( templateName.equalsIgnoreCase("xlsSalesSerialReport") ) {
            filePath = salesSerialReport(templateName, printDate, printDate);
        } else if ( templateName.equalsIgnoreCase("xlsSalesBundleReport") ) {
            filePath = salesBundleReport(templateName, printDate, printDate);
        } else if ( templateName.equalsIgnoreCase("xlsSalesPaymentReport") ) {
            filePath = salesPaymentReport(templateName, printDate, printDate);
        }

        File f = new File(filePath);
        log.info("filePath below");
        log.info(f.getPath());
        byte[] reportFile = null;

        fin = new FileInputStream(f);
        reportFile = new byte[(int) f.length()];
        fin.read(reportFile);

        String mime = "application/vnd.ms-excel";
        response.setContentType(mime);
        response.setContentLength(reportFile.length);
        response.setHeader("Content-disposition", "attachment; filename=" + f.getName()); // save file
        ServletOutputStream out = response.getOutputStream();
        response.resetBuffer();
        out.write(reportFile);
        
    }

    public ArrayList<String> getParameterKeys() {
        return parameterKeys;
    }

    public void setParameterKeys(ArrayList<String> parameterKeys) {
        this.parameterKeys = parameterKeys;
    }

    public ArrayList<String> getParameterValues() {
        return parameterValues;
    }

    public void setParameterValues(ArrayList<String> parameterValues) {
        this.parameterValues = parameterValues;
    }

    public String getParametersKey() {
        return parametersKey;
    }

    public void setParametersKey(String parametersKey) {
        this.parametersKey = parametersKey;
    }    

    protected static String milisToSec(long millis) {
        int menit = (int) millis / 60000;
        int detik = (int) (millis % 60000) / 1000;
        int koma = (int) (millis % 1000) / 10;

        return new String(menit + " menit, " + detik + "." + koma + " detik");
    }    

    private void releaseConnection() {        
            try {
                    if (res != null)
                         res.close();

                } catch (SQLException ex) {
                    log.error("#error closing ResultSet");
                    log.error(ex);
                }

            try {
                    if (preparedStatement != null)
                         preparedStatement.close();

                } catch (SQLException ex) {
                    log.error("#error closing PreparedStatement");
                    log.error(ex);
                }

            try {
                    if (con != null)
                         con.close();

                } catch (SQLException ex) {
                    log.error("#error closing Connection");
                    log.error(ex);
                }
            log.info("ExportExcelController.releaseConnection done");
    }

    private boolean checkParameters(){
        if (getParametersKey().length() != 0 && getParameterValues().size() != 0) {
            String[] keys = getParametersKey().split(",");
            for (int i = 0; i < getParameterValues().size(); i++) {
                log.info("param[" + i + ", value=" + getParameterValues().get(i));
            }
            if (keys.length != getParameterValues().size()) {
                log.error("xls keys-values count not match!");
                return false;
            } else {
                for (int i = 0; i < keys.length; i++) {
                    log.info(i+"- key=" + keys[i] + ", value=" + getParameterValues().get(i));
                }
                return true;
            }
        } else {
            log.error("xls Either keys or lengths is problematic!");
            return false;
        }        
    }

    private String postpaidPaymentReport(String excelFileName, String printedDate, String printedBy) throws Exception {
        long start = System.currentTimeMillis();
        log.info("postpaidPaymentReport: excelFileName="+excelFileName);
        log.info("xls Template name used: " + templateName);

        StringBuilder sql = new StringBuilder("SELECT to_char(D.CREATED_DATE,'DD-MON-YY') AS SALES_DATE");
        sql.append(",P.BG_CODE AS BG_CODE,U.NAME AS AGENT ,D.DOCUMENT_NO AS RECEIPT_NO,P.MSISDN AS MSISDN");
        sql.append(",D.METHOD_CODE,D.AMOUNT AS PAYMENT_AMOUNT");
        sql.append(",CASE WHEN P.PAYMENT_TYPE IN ('FULL_PAID','LESS_PAID') THEN 'PAYMENT' ELSE P.PAYMENT_TYPE END AS TRANS_TYPE_CODE");
        sql.append(" FROM POSTPAID P ");
        sql.append(" INNER JOIN POSTPAID_DTL D ON P.DOCUMENT_NO=D.POSTPAID_DOCUMENT_NO");
        sql.append(" INNER JOIN \"USER\" U ON U.USER_ID = D.CREATED_BY ");
        sql.append(" WHERE P.PAYMENT_TYPE NOT LIKE '%CANCEL%' ");
        sql.append(" AND P.BG_CODE LIKE ? ");
        sql.append(" AND D.TRANS_DATE >= to_date( ?||',00:00:00','dd-mm-yyyy,HH24:MI:ss') ");
        sql.append(" AND D.TRANS_DATE <= to_date( ?||',23:59:59','dd-mm-yyyy,HH24:MI:ss') ");
        sql.append(" ORDER BY SALES_DATE, BG_CODE ");

        if (checkParameters()) {
            preparedStatement = con.prepareStatement(sql.toString());
            preparedStatement.setString(1, getParameterValues().get(0));
            preparedStatement.setString(2, getParameterValues().get(2));
            preparedStatement.setString(3, getParameterValues().get(1));
            res = preparedStatement.executeQuery();
        }                

        xlEx = new ExcelExport("", "", res);
        xlEx.setRowNumber(false);
        xlEx.setHeaderTitle("Postpaid Payment Report ");
        xlEx.addHeader("Printed Date ", printedDate);
        xlEx.addHeader("Printed By ", printedBy);
        xlEx.addHeader("Start Date ", getParameterValues().get(2));
        xlEx.addHeader("End Date ", getParameterValues().get(1));
        xlEx.addFieldMap("SALES DATE", "SALES_DATE", DT_STRING);
        xlEx.addFieldMap("DEALER CODE", "BG_CODE", DT_STRING);
        xlEx.addFieldMap("DOCUMENT NO", "RECEIPT_NO", DT_STRING);
        xlEx.addFieldMap("AGENT", "AGENT", DT_STRING);
        xlEx.addFieldMap("MSISDN", "MSISDN", DT_STRING);
        xlEx.addFieldMap("PAYMENT TYPE", "TRANS_TYPE_CODE", DT_STRING);
        xlEx.addFieldMap("PAYMENT METHOD", "METHOD_CODE", DT_STRING);
        xlEx.addFieldMap("PAYMENT AMOUNT", "PAYMENT_AMOUNT", DT_NUMERIC);        
        xlEx.setExcelFileName(excelFileName);
        xlEx.execute();
        releaseConnection();
        log.info("xlEx executed...generated:"+xlEx.getExcelFileGenerated());        
        log.info("xls running time (msec): " + (System.currentTimeMillis() - start));
        log.info("--------------------------------");
        long finish = System.currentTimeMillis();
        log.info("xls Running time: " + milisToSec(finish - start));
        log.info("===================================");

        return xlEx.getExcelFileGenerated();
    }

    private String salesEvoucherReport(String excelFileName, String printedDate, String printedBy) throws Exception {
        
        StringBuilder sql = new StringBuilder(" select to_char(o.trx_date,'DD-MON-YY') as sales_date, e.bg_code as dealer_code, o.sales_document_no as invoice_number,  ");
        sql.append(" u.name as agent_name, o.sales_item_code as item_code, o.nomor_hp, o.trx_no, o.eamount ");
        sql.append(" from evoucher e ");
        sql.append(" inner join evoucher_out o on e.evoucher_code=o.evoucher_code ");
        sql.append(" inner join sales s on o.sales_document_no=s.document_no ");
        sql.append(" inner join \"USER\" u on u.user_id=o.created_by ");
        sql.append(" where s.status_code='PAID' ");
        sql.append(" and e.bg_code LIKE ? ");
        sql.append(" and s.created_date >= to_date( ?||',00:00:00','dd-mm-yyyy,HH24:MI:ss') ");
        sql.append(" and s.created_date <= to_date( ?||',23:59:59','dd-mm-yyyy,HH24:MI:ss') ");
        sql.append(" order by sales_date , dealer_code ");

        if (checkParameters()) {
            preparedStatement = con.prepareStatement(sql.toString());
            preparedStatement.setString(1, getParameterValues().get(0));
            preparedStatement.setString(2, getParameterValues().get(2));
            preparedStatement.setString(3, getParameterValues().get(1));
            res = preparedStatement.executeQuery();
        }

        xlEx = new ExcelExport("", "", res);
        xlEx.setRowNumber(false);
        xlEx.setHeaderTitle("Sales EVoucher Report ");
        xlEx.addHeader("Printed Date ", printedDate);
        xlEx.addHeader("Printed By ", printedBy);
        xlEx.addHeader("Start Date ", getParameterValues().get(2));
        xlEx.addHeader("End Date ", getParameterValues().get(1));
        
        xlEx.addFieldMap("SALES DATE", "SALES_DATE", DT_STRING);
        xlEx.addFieldMap("DEALER CODE", "DEALER_CODE", DT_STRING);
        xlEx.addFieldMap("INVOICE NO", "INVOICE_NUMBER", DT_STRING);
        xlEx.addFieldMap("AGENT NAME", "AGENT_NAME", DT_STRING);
        xlEx.addFieldMap("ITEM CODE", "ITEM_CODE", DT_STRING);
        xlEx.addFieldMap("NOMOR HP", "NOMOR_HP", DT_STRING);
        xlEx.addFieldMap("TRX NO", "TRX_NO", DT_STRING);
        xlEx.addFieldMap("EAMOUNT", "EAMOUNT", DT_NUMERIC);
        xlEx.setExcelFileName(excelFileName);
        xlEx.execute();
        releaseConnection();

        return xlEx.getExcelFileGenerated();
    }

    private String salesServiceReport(String excelFileName, String printedDate, String printedBy) throws Exception {

        StringBuilder sql = new StringBuilder(" select to_char(ss.created_date,'DD-MON-YY') as sales_date,s.bg_code as dealer_code,ss.document_no as invoice_number, ");
        sql.append(" u.name as agent_name,  ss.service_Code as item_Code, ss.service_code as item_code2,ss.service_ident as imei, ss.customer_price as price ");
        sql.append(" from sales s ");
        sql.append(" inner join sales_dtl_service ss on s.document_no=ss.document_no ");
        sql.append(" inner join \"USER\" u on u.user_id=ss.created_by  ");
        sql.append(" where s.status_code='PAID'  ");
        sql.append(" and s.bg_code LIKE ? ");
        sql.append(" and s.created_date >= to_date( ?||',00:00:00','dd-mm-yyyy,HH24:MI:ss') ");
        sql.append(" and s.created_date <= to_date( ?||',23:59:59','dd-mm-yyyy,HH24:MI:ss') ");
        sql.append(" order by sales_date, dealer_code ");

        if (checkParameters()) {
            preparedStatement = con.prepareStatement(sql.toString());
            preparedStatement.setString(1, getParameterValues().get(0));
            preparedStatement.setString(2, getParameterValues().get(2));
            preparedStatement.setString(3, getParameterValues().get(1));
            res = preparedStatement.executeQuery();
        }

        xlEx = new ExcelExport("", "", res);
        xlEx.setRowNumber(false);
        xlEx.setHeaderTitle("Sales Service Report ");
        xlEx.addHeader("Printed Date ", printedDate);
        xlEx.addHeader("Printed By ", printedBy);
        xlEx.addHeader("Start Date ", getParameterValues().get(2));
        xlEx.addHeader("End Date ", getParameterValues().get(1));

        xlEx.addFieldMap("SALES DATE", "SALES_DATE", DT_STRING);
        xlEx.addFieldMap("DEALER CODE", "DEALER_CODE", DT_STRING);
        xlEx.addFieldMap("INVOICE NO", "INVOICE_NUMBER", DT_STRING);
        xlEx.addFieldMap("AGENT NAME", "AGENT_NAME", DT_STRING);
        xlEx.addFieldMap("ITEM CODE", "ITEM_CODE", DT_STRING);
        xlEx.addFieldMap("ITEM NAME", "ITEM_CODE2", DT_STRING);
        xlEx.addFieldMap("IMEI", "IMEI", DT_STRING);
        xlEx.addFieldMap("PRICE", "PRICE", DT_NUMERIC);
        xlEx.setExcelFileName(excelFileName);
        xlEx.execute();
        releaseConnection();

        return xlEx.getExcelFileGenerated();
    }

    private String salesConsignmentReport(String excelFileName, String printedDate, String printedBy) throws Exception {

        StringBuilder sql = new StringBuilder(" select to_char(sdi.created_date,'DD-MON-YY') as sales_date, s.bg_code as dealer_code, sdi.document_no as invoice_number, ");
        sql.append(" u.name as agent_name, sdi.item_code as item_code, sdi.item_code as item_code2, sdi.ident_no as imei, sdp.customer_price as price ");
        sql.append(" from sales s ");
        sql.append(" inner join sales_dtl_ident sdi on s.document_no=sdi.document_no ");
        sql.append(" inner join sales_Dtl_product sdp on sdp.catalog_code=sdi.catalog_code and sdp.document_no=sdi.document_no and sdp.item_code=sdi.item_code ");
        sql.append(" inner join \"USER\" u on u.user_id=ss.created_by  ");
        sql.append(" where s.status_code='PAID'  ");
        sql.append(" and s.bg_code LIKE ? ");
        sql.append(" and s.created_date >= to_date( ?||',00:00:00','dd-mm-yyyy,HH24:MI:ss') ");
        sql.append(" and s.created_date <= to_date( ?||',23:59:59','dd-mm-yyyy,HH24:MI:ss') ");
        sql.append(" order by sales_date, dealer_code ");

        if (checkParameters()) {
            preparedStatement = con.prepareStatement(sql.toString());
            preparedStatement.setString(1, getParameterValues().get(0));
            preparedStatement.setString(2, getParameterValues().get(2));
            preparedStatement.setString(3, getParameterValues().get(1));
            res = preparedStatement.executeQuery();
        }

        xlEx = new ExcelExport("", "", res);
        xlEx.setRowNumber(false);
        xlEx.setHeaderTitle("Sales Consignment Report ");
        xlEx.addHeader("Printed Date ", printedDate);
        xlEx.addHeader("Printed By ", printedBy);
        xlEx.addHeader("Start Date ", getParameterValues().get(2));
        xlEx.addHeader("End Date ", getParameterValues().get(1));

        xlEx.addFieldMap("SALES DATE", "SALES_DATE", DT_STRING);
        xlEx.addFieldMap("DEALER CODE", "DEALER_CODE", DT_STRING);
        xlEx.addFieldMap("INVOICE NO", "INVOICE_NUMBER", DT_STRING);
        xlEx.addFieldMap("AGENT NAME", "AGENT_NAME", DT_STRING);
        xlEx.addFieldMap("ITEM CODE", "ITEM_CODE", DT_STRING);
        xlEx.addFieldMap("ITEM NAME", "ITEM_CODE2", DT_STRING);
        xlEx.addFieldMap("IMEI", "IMEI", DT_STRING);
        xlEx.addFieldMap("PRICE", "PRICE", DT_NUMERIC);
        xlEx.setExcelFileName(excelFileName);
        xlEx.execute();
        releaseConnection();

        return xlEx.getExcelFileGenerated();
    }

    private String salesSerialReport(String excelFileName, String printedDate, String printedBy) throws Exception {

        StringBuilder sql = new StringBuilder(" select ");
        sql.append(" to_char(s.document_date,'DD-MON-YY') as sales_date, s.bg_code as dealer_code, ssn.document_no as invoice_number, u.name as agent_name, ");
        sql.append(" ssn.item_Code as item_Code, p.name as alias_name, ssn.sn as SN,sdp.customer_price as price ");
        sql.append(" from sales s ");
        sql.append(" inner join sales_dtl_product sdp on s.document_no=sdp.document_no ");
        sql.append(" inner join sales_dtl_sn ssn on sdp.document_no=ssn.document_no and sdp.item_code=ssn.item_code and sdp.catalog_code=ssn.catalog_code ");
        sql.append(" left join product p on ssn.item_code=p.item_code ");
        sql.append(" inner join \"USER\" u on u.user_id=s.created_by ");
        sql.append(" where s.status_code='PAID'  ");
        sql.append(" and s.bg_code LIKE ? ");
        sql.append(" and s.created_date >= to_date( ?||',00:00:00','dd-mm-yyyy,HH24:MI:ss') ");
        sql.append(" and s.created_date <= to_date( ?||',23:59:59','dd-mm-yyyy,HH24:MI:ss') ");
        sql.append(" order by sales_date, dealer_code ");

        if (checkParameters()) {
            preparedStatement = con.prepareStatement(sql.toString());
            preparedStatement.setString(1, getParameterValues().get(0));
            preparedStatement.setString(2, getParameterValues().get(2));
            preparedStatement.setString(3, getParameterValues().get(1));
            res = preparedStatement.executeQuery();
        }

        xlEx = new ExcelExport("", "", res);
        xlEx.setRowNumber(false);
        xlEx.setHeaderTitle("Sales Serial Report ");
        xlEx.addHeader("Printed Date ", printedDate);
        xlEx.addHeader("Printed By ", printedBy);
        xlEx.addHeader("Start Date ", getParameterValues().get(2));
        xlEx.addHeader("End Date ", getParameterValues().get(1));

        xlEx.addFieldMap("SALES DATE", "SALES_DATE", DT_STRING);
        xlEx.addFieldMap("DEALER CODE", "DEALER_CODE", DT_STRING);
        xlEx.addFieldMap("INVOICE NO", "INVOICE_NUMBER", DT_STRING);
        xlEx.addFieldMap("AGENT NAME", "AGENT_NAME", DT_STRING);
        xlEx.addFieldMap("ITEM CODE", "ITEM_CODE", DT_STRING);
        xlEx.addFieldMap("ALIAS", "ALIAS_NAME", DT_STRING);
        xlEx.addFieldMap("SN", "SN", DT_STRING);
        xlEx.addFieldMap("PRICE", "PRICE", DT_NUMERIC);
        xlEx.setExcelFileName(excelFileName);
        xlEx.execute();
        releaseConnection();

        return xlEx.getExcelFileGenerated();
    }

    private String salesBundleReport(String excelFileName, String printedDate, String printedBy) throws Exception {

        StringBuilder sql = new StringBuilder(" select ");
        sql.append(" to_char(sdb.created_date,'DD-MON-YY') as sales_date, s.bg_code as dealer_code,  sdb.document_no as invoice_number, u.name as agent_name, ");
        sql.append(" sdb.bundle_code as bundle_code, sdbi.item_Code as item_code, sdbi.ident_no as imei, sdb.customer_price as price ");
        sql.append(" from sales s ");
        sql.append(" inner join sales_dtl_bundle sdb on s.document_no=sdb.document_no ");
        sql.append(" inner join sales_Dtl_bundle_dtl_ident sdbi on sdb.catalog_code=sdbi.catalog_code ");
        sql.append(" inner join \"USER\" u on u.user_id=s.created_by  ");
        sql.append(" where s.status_code='PAID'  ");
        sql.append(" and s.bg_code LIKE ? ");
        sql.append(" and s.created_date >= to_date( ?||',00:00:00','dd-mm-yyyy,HH24:MI:ss') ");
        sql.append(" and s.created_date <= to_date( ?||',23:59:59','dd-mm-yyyy,HH24:MI:ss') ");
        sql.append(" order by sales_date, dealer_code ");

        if (checkParameters()) {
            preparedStatement = con.prepareStatement(sql.toString());
            preparedStatement.setString(1, getParameterValues().get(0));
            preparedStatement.setString(2, getParameterValues().get(2));
            preparedStatement.setString(3, getParameterValues().get(1));
            res = preparedStatement.executeQuery();
        }

        xlEx = new ExcelExport("", "", res);
        xlEx.setRowNumber(false);
        xlEx.setHeaderTitle("Sales Bundle Report ");
        xlEx.addHeader("Printed Date ", printedDate);
        xlEx.addHeader("Printed By ", printedBy);
        xlEx.addHeader("Start Date ", getParameterValues().get(2));
        xlEx.addHeader("End Date ", getParameterValues().get(1));

        xlEx.addFieldMap("SALES DATE", "SALES_DATE", DT_STRING);
        xlEx.addFieldMap("DEALER CODE", "DEALER_CODE", DT_STRING);
        xlEx.addFieldMap("INVOICE NO", "INVOICE_NUMBER", DT_STRING);
        xlEx.addFieldMap("AGENT NAME", "AGENT_NAME", DT_STRING);
        xlEx.addFieldMap("BUNDLE CODE", "BUNDLE", DT_STRING);
        xlEx.addFieldMap("ITEM", "ITEM_CODE", DT_STRING);
        xlEx.addFieldMap("IMEI", "IMEI", DT_STRING);
        xlEx.addFieldMap("PRICE", "PRICE", DT_NUMERIC);
        xlEx.setExcelFileName(excelFileName);
        xlEx.execute();
        releaseConnection();

        return xlEx.getExcelFileGenerated();
    }

    private String salesPaymentReport(String excelFileName, String printedDate, String printedBy) throws Exception {
        
        StringBuilder sql = new StringBuilder("SELECT ");
        sql.append(" to_char(s.document_date,'DD-MON-YY') as invoice_date, s.bg_code as dealer_code, ");
        sql.append(" s.document_no as invoice_no, s.status_code, spd.method_code, spd.amount ");
        sql.append(" from sales s ");
        sql.append(" inner join sales_payment sp on s.document_no=sp.sales_document_no");
        sql.append(" inner join sales_payment_dtl spd on sp.document_no=spd.document_no ");
        sql.append(" where s.status_code='PAID' ");
        sql.append(" and s.bg_code LIKE ? ");
        sql.append(" and s.created_date >= to_date( ?||',00:00:00','dd-mm-yyyy,HH24:MI:ss') ");
        sql.append(" and s.created_date <= to_date( ?||',23:59:59','dd-mm-yyyy,HH24:MI:ss') ");
        sql.append(" order by invoice_date, dealer_code ");

        if (checkParameters()) {
            preparedStatement = con.prepareStatement(sql.toString());
            preparedStatement.setString(1, getParameterValues().get(0));
            preparedStatement.setString(2, getParameterValues().get(2));
            preparedStatement.setString(3, getParameterValues().get(1));
            res = preparedStatement.executeQuery();
        }

        xlEx = new ExcelExport("", "", res);
        xlEx.setRowNumber(false);
        xlEx.setHeaderTitle("Sales Payment Report ");
        xlEx.addHeader("Printed Date ", printedDate);
        xlEx.addHeader("Printed By ", printedBy);
        xlEx.addHeader("Start Date ", getParameterValues().get(2));
        xlEx.addHeader("End Date ", getParameterValues().get(1));
        
        xlEx.addFieldMap("SALES DATE", "INVOICE_DATE", DT_STRING);
        xlEx.addFieldMap("DEALER CODE", "DEALER_CODE", DT_STRING);
        xlEx.addFieldMap("INVOICE NO", "INVOICE_NO", DT_STRING);
        xlEx.addFieldMap("STATUS", "STATUS_CODE", DT_STRING);
        xlEx.addFieldMap("PAYMENT METHOD", "METHOD_CODE", DT_STRING);
        xlEx.addFieldMap("AMOUNT", "AMOUNT", DT_NUMERIC);
        xlEx.setExcelFileName(excelFileName);
        xlEx.execute();
        releaseConnection();
        
        return xlEx.getExcelFileGenerated();
    }
    
}
