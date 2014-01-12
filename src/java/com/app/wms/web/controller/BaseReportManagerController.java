/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.controller;

import com.app.wms.engine.db.dto.GenericDtoReport;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.engine.io.PossFileUtils;
import com.app.wms.engine.io.PossWebUtils;
import com.app.wms.engine.model.ReportProperty;
import com.app.wms.engine.report.OOReportGenerator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRParameter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.apache.commons.dbcp.BasicDataSource;

/**
 *
 * @author zyrex
 */
public abstract class BaseReportManagerController extends MultiActionController {

    protected String parametersKey = "";
    protected static Connection con;
    protected String outcome = null;
    //private String path = ReportProperty.JRXML_PATH;
    protected String templateName = "";
    protected String outputFormat = "";
    protected OOReportGenerator org;
    //protected FacesContext context=null;
    protected ServletContext context;
    protected Map<String, Object> templates = null;
    protected static final String sp = File.separator;
    //protected DriverManagerDataSource ImsDs;
    protected BasicDataSource ImsDs;
    protected GenericDtoReport gdr;
    protected String returnPath = "report/rptAgentList";
    protected ArrayList<String> parameterValues = new ArrayList<String>();
    protected ArrayList<String> parameterKeys = new ArrayList<String>();
    protected static Log log = LogFactory.getLog(BaseReportManagerController.class);

    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            //returnPath = request.getParameter("returnPath");
            String requestPath = request.getRequestURL().toString();
            log.warn("getting request context path: " + requestPath);
            String requestTail = PossFileUtils.displayFilenameOnly(requestPath);
            log.warn("request tail path: " + requestTail);
            log.info("loading report data source");
            returnPath = "report/" + requestTail;
            //ImsDs = DaoFactory.getDataSource();
            ImsDs = DaoFactory.getReportDataSource();
            con = ImsDs.getConnection();
            context = getServletContext();
            if (con.isClosed()) {
                log.warn("failed to connect with oracle");
            }
            if (gdr == null) {
                log.warn("create new gdr object");
                gdr = new GenericDtoReport();
            }
            //bind(request, gdr);

        } catch (SQLException ex) {
            Logger.getLogger(BaseReportManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ModelAndView(returnPath, "dto", gdr);
    }

    protected static String milisToSec(long millis) {
        int menit = (int) millis / 60000;
        int detik = (int) (millis % 60000) / 1000;
        int koma = (int) (millis % 1000) / 10;

        return new String(menit + " menit, " + detik + "." + koma + " detik");
    }

    protected void closeConnection() {
        if (null != getCon()) {
            try {
                getCon().close();
                setCon(null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @return the outcome
     */
    public String getOutcome() {
        return outcome;
    }

    /**
     * @param outcome the outcome to set
     */
    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    /**
     * @return the parametersKey
     */
    public String getParametersKey() {
        return parametersKey;
    }

    /**
     * @param parametersKey the parametersKey to set
     */
    public void setParametersKey(String parametersKey) {
        this.parametersKey = parametersKey;
    }

    /*
    protected static SelectItem[] outputFormatItems = {
    new SelectItem("txt", "TXT"),
    new SelectItem("xls", "XLS"),
    new SelectItem("csv", "CSV"),
    new SelectItem("pdf", "PDF"),
    new SelectItem("rtf", "RTF"),
    new SelectItem("html", "HTML")
    };

    public SelectItem[] getOutputFormatItems() {
    return outputFormatItems;
    }
     * 
     */
    public Map<String, Object> getTemplates() {
        if (templates == null) {
            templates = new LinkedHashMap<String, Object>();
            for (String t : PossFileUtils.listFiles(ReportProperty.JRXML_PATH)) {
                templates.put(t, t);
            }
        }
        return templates;
    }

    public Map<String, Object> getTemplatesFromContext() throws IOException {
        //String curpath = context.getExternalContext().;
        //System.out.println("context path=" + curpath);
        Set<String> Resources = context.getResourcePaths("/WEB-INF/templates/jrxml");
        //String realContextPath = context.getRealPath(request.getContextPath());
        if (templates == null) {
            templates = new LinkedHashMap<String, Object>();
            for (String t : PossFileUtils.listTemplateFiles(Resources)) {
                templates.put(t, t);
            }
        }
        return templates;
    }

    /**
     * @return the templateName
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * @param templateName the templateName to set
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    /**
     * @return the outputFormat
     */
    public String getOutputFormat() {
        return outputFormat;
    }

    /**
     * @param outputFormat the outputFormat to set
     */
    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public String createOnlineReport() {
        long start = System.currentTimeMillis();
        //setTemplateName();
        //setParameterKeys();

        //System.out.println(templateName + "====" + parametersKey);

        //System.out.println("Value of par1 is: " + getParametersValue());
        log.info("filling reports");
        //System.out.println("set report parameters for firm type");
        // Mistake!
        if (getParametersKey().length() != 0 && this.getParameterValues().size() != 0) {
            String[] keys = getParametersKey().split(",");
            //String[] values = getParametersValue().split(",");
            if (keys.length != this.getParameterValues().size()) {
                log.info("keys-values count not match!");
                log.debug("parameter keys = " + getParametersKey());
                log.debug("parameter values = ");
                for (String val : this.getParameterValues()) {
                    log.debug("val = " + val);
                }
            } else {
                org = new OOReportGenerator(context);
                org.setTemplateName(templateName);
                log.info("Template name used: " + templateName);
                org.setOutputSuffix(outputFormat);
                log.info("Generate report in " + outputFormat + " format");

                /**
                 * Section to copy template embedded images
                 */
                org.setReportParameter(JRParameter.REPORT_CONNECTION, getCon());

                /**
                 * debug purpose logging
                 */
                for (int i = 0; i < keys.length; i++) {
                    log.debug("key=" + keys[i] + ", value=" + this.getParameterValues().get(i));
                    org.setReportParameter(keys[i], this.getParameterValues().get(i));
                }

                org.configureOnline();
                org.generateReport();
                log.debug(" running time (msec): " + (System.currentTimeMillis() - start));
                log.debug("--------------------------------");
                long finish = System.currentTimeMillis();
                log.debug("Running time: " + milisToSec(finish - start));
                log.debug("===================================");
                //writeSummaryFile(jobFullName, firstStart, finish);
                closeConnection();
                outcome = "success";
            }

        } else {
            log.error("Either keys or lengths is problematic!");
            outcome = "failure";
            //System.exit(-1);
        }
        return outcome;
    }

    public void saveOnlineReport(HttpServletRequest request, HttpServletResponse response, GenericDtoReport oCommand) throws Exception {
        //GenericDtoReport gdr = (GenericDtoReport) request.getAttribute("reportDto");
        if (oCommand == null) {
            log.warn("zero command object");
        }
        gdr = oCommand;

        if (gdr == null) {
            log.error("failed to get GenericDtoReport object");
        }

        parametersKey = request.getParameter("parametersKey");
        log.info("parametersKey: " + parametersKey);
        templateName = request.getParameter("templateName");
        log.info("gdr parameter size: " + gdr.getParameterValues().size());
        setParameterValues(gdr.getParameterValues());
        outputFormat = gdr.getOutputFormat();
        createOnlineReport();

        try {
            printToStream(response);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BaseReportManagerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BaseReportManagerController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //gdr = null;
        }
        //return this.findByPrimaryKey(request, response);
    }

    protected String appendDocumentNo(String adjusted) {
        //assume in each receipt form parameter to be passed only one to the report generator
        String docNo;


        //detect receipt report type. The hint is for receipt type, there should be only 1 value for parameterValues
        if (parametersKey.contains("P_DOC_NO")) {
            docNo = this.parameterValues.get(0);
            //int prefixLength=templateName.length();
            String postAdjusted = templateName + docNo + "." + outputFormat;
            String parentPath = adjusted.substring(0, adjusted.lastIndexOf("/"));
            log.debug("appendDocumentNo parentPath:" + parentPath);

            return (parentPath + "/" + postAdjusted);
        }
        return adjusted;
    }

    private String fileNameWithoutExt(String fileName) {
        return fileName.replaceFirst("[.][^.]+$", "");
    }

    public void printToStream(HttpServletResponse response) throws FileNotFoundException, IOException {
    	FileInputStream fin = null;
        File file = org.EXPORTED_FILE;
    	try{

            String parentTmp = file.getParent();
            parentTmp = parentTmp.replace(System.getProperty("file.separator"), "/");
            String renamedName = file.getName();
            log.info("original file name:" + renamedName);
            renamedName = renamedName + "." + outputFormat;
            renamedName = parentTmp + "/" + renamedName;
            String renamed2 = appendDocumentNo(renamedName);


            log.info("adjusted name:" + renamed2);

            File cleanFile = new File(renamed2);

            int counter = 1;

// terus coba rename smp bisa, berbahasa bisa time out
            while ((cleanFile.exists() || !file.renameTo(cleanFile)) && counter < 10000) { // dibatasi 100 putaran
//
            	int maxLength = fileNameWithoutExt(renamed2).length();
                renamed2 = fileNameWithoutExt(renamed2)+ maxLength + "." + outputFormat;
                String str = renamed2.replace(renamed2, renamedName);
                System.out.println(">>>> ??? str:" + str);
                cleanFile = new File(str);
                System.out.println(">>>> ??? adjusted name:" + renamed2);
              //  counter += 1;
            }

 //           if (counter >= 100) {
//                log.error("failed to rename file");
//                log.error("supposed file name:" + cleanFile.getName());
 //           }
//            boolean check = file.renameTo(cleanFile);
////
//            if (check == false) {
//                System.out.println("failed to rename file");
//                System.out.println("supposed file name:" + cleanFile.getName());
//            }

            byte[] reportFile = null;

            fin = new FileInputStream(cleanFile);
            reportFile = new byte[(int) cleanFile.length()];



            fin.read(reportFile);

            log.info("reportFile=" + reportFile.length);
            //HttpServletResponse response = (HttpServletResponse) context.getResponse();
            //problematic around here
            String mime = PossWebUtils.generateMimeType(renamedName);
            response.setContentType(mime);
            log.info("mime string:" + mime);

            response.setContentLength(reportFile.length);
            // response.setHeader("Content-disposition","inline; filename=" + fileName + ".pdf"); // show on browser
            response.setHeader("Content-disposition", "attachment; filename=" + cleanFile.getName()); // save file
            //response.setHeader("Content-disposition", "attachment; filename=" + renamed2);
            ServletOutputStream out = response.getOutputStream();
            response.resetBuffer();
            out.write(reportFile);
        
    		
    	}catch(Exception e){
    		
    	}
    }
    
    public void printToStream1(HttpServletResponse response) throws FileNotFoundException, IOException {
        FileInputStream fin = null;
        File file = org.EXPORTED_FILE;
        try {
            String parentTmp = file.getParent();
            parentTmp = parentTmp.replace(System.getProperty("file.separator"), "/");
            String renamedName = file.getName();
            log.info("original file name:" + renamedName);
            renamedName = renamedName + "." + outputFormat;
            renamedName = parentTmp + "/" + renamedName;
            String renamed2 = appendDocumentNo(renamedName);


            log.info("adjusted name:" + renamed2);

            File cleanFile = new File(renamed2);

            int counter = 1;

// terus coba rename smp bisa, berbahasa bisa time out
            while ((cleanFile.exists() || !file.renameTo(cleanFile)) && counter < 100) { // dibatasi 100 putaran

                renamed2 = fileNameWithoutExt(renamed2) + "." + counter + "." + outputFormat;
                cleanFile = new File(renamed2);
                //System.out.println("adjusted name:" + renamed2);
                counter += 1;
            }

            if (counter >= 100) {
                log.error("failed to rename file");
                log.error("supposed file name:" + cleanFile.getName());
            }
//            boolean check = file.renameTo(cleanFile);
////
//            if (check == false) {
//                System.out.println("failed to rename file");
//                System.out.println("supposed file name:" + cleanFile.getName());
//            }

            byte[] reportFile = null;

            fin = new FileInputStream(cleanFile);
            reportFile = new byte[(int) cleanFile.length()];



            fin.read(reportFile);

            log.info("reportFile=" + reportFile.length);
            //HttpServletResponse response = (HttpServletResponse) context.getResponse();
            //problematic around here
            String mime = PossWebUtils.generateMimeType(renamedName);
            response.setContentType(mime);
            log.info("mime string:" + mime);

            response.setContentLength(reportFile.length);
            // response.setHeader("Content-disposition","inline; filename=" + fileName + ".pdf"); // show on browser
            response.setHeader("Content-disposition", "attachment; filename=" + cleanFile.getName()); // save file
            //response.setHeader("Content-disposition", "attachment; filename=" + renamed2);
            ServletOutputStream out = response.getOutputStream();
            response.resetBuffer();
            out.write(reportFile);
        } finally {
            //context.responseComplete();
            //out.close();
            if (org.EXPORTED_FILE != null) {
                org.EXPORTED_FILE.deleteOnExit();
            }
            if (fin != null) {
                fin.close();
            }
        }
    }

    public void saveOfflineReport(HttpServletRequest request, HttpServletResponse response) {
        createOnlineReport();
        FileInputStream fin = null;
        File file = null;

        byte[] reportFile = null;
        try {
            file = new File(org.EXPORTED_FILENAME);
            fin = new FileInputStream(file);
            reportFile = new byte[(int) file.length()];
            fin.read(reportFile);

            System.out.println("reportFile=" + reportFile.length);
            //HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            response.setContentType(PossWebUtils.generateMimeType(org.EXPORTED_FILENAME));
            response.setContentLength(reportFile.length);
            // response.setHeader("Content-disposition","inline; filename=" + fileName + ".pdf"); // show on browser
            response.setHeader("Content-disposition", "attachment; filename=" + file.getName()); // save file
            ServletOutputStream out = response.getOutputStream();
            out.write(reportFile);
            //context.responseComplete();
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BaseReportManagerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BaseReportManagerController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fin.close();
            } catch (IOException ex) {
                Logger.getLogger(BaseReportManagerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //return new ByteArrayInputStream(reportFile);
    }

    /**
     * pending item
     * @return String
     */
    public String previewReport() {
        //set output format to txt as preview
        outputFormat = "html";
        createOnlineReport();
        ServletContext sc = context;
        //could be quite problematic becaouse of file_separator differences
        String path = sc.getRealPath("/WEB-INF/templates/jrxml/exported");
        //String previewPath = path + sp + "preview_result.html";
        System.out.println("servlet context path=" + path);
        path = path.replace(System.getProperty("file.separator"), "/");
        File previewFile = PossFileUtils.copyFileToFile(org.EXPORTED_FILE, path, "preview_result.html");
        System.out.println("preview file size=" + previewFile.length());
        //File export = new File(path);
        //File f = new File();

        return "previewReport";
    }

    /**
     * @return the con
     */
    public Connection getCon() {
        return con;
    }

    /**
     * @param con the con to set
     */
    public void setCon(Connection con) {
        this.con = con;
    }

    /**
     * @return the parameterValues
     */
    public ArrayList<String> getParameterValues() {
        return parameterValues;
    }

    /**
     * @param parameterValues the parameterValues to set
     */
    public void setParameterValues(ArrayList<String> parameterValues) {
        this.parameterValues = parameterValues;
    }

    /**
     * @return the parameterKeys
     */
    public ArrayList<String> getParameterKeys() {
        return parameterKeys;
    }

    /**
     * @param parameterKeys the parameterKeys to set
     */
    public void setParameterKeys(ArrayList<String> parameterKeys) {
        this.parameterKeys = parameterKeys;
    }
}
