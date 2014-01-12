/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.wms.engine.report;
import org.apache.commons.dbcp.BasicDataSource;
import com.app.wms.engine.app.PossHcptCoreContextManager;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.engine.io.PossFileUtils;
import com.app.wms.engine.io.PossWebUtils;
import com.app.wms.engine.model.ReportProperty;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRParameter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author zyrex
 */
public class BaseReportManager {

    protected String parametersKey = "";
    protected String parametersValue = "";
    protected Connection con;
    protected String outcome = null;
    //private String path = ReportProperty.JRXML_PATH;
    protected String templateName = "";
    protected String outputFormat = "";
    protected OOReportGenerator org;
    protected FacesContext context=null;
    protected Map<String, Object> templates = null;
    protected static final String sp = File.separator;
    //protected DriverManagerDataSource PossDs;
     protected BasicDataSource PossDs;

    public BaseReportManager() {
        try {
            //con = new DBManager("oracle").getConnection();
            //High probability of being a mistake, might be need to make workaround for dao-beans.xml
            System.out.println("loading report data source");
            PossDs = DaoFactory.getReportDataSource();
            con = PossDs.getConnection();
            context = FacesContext.getCurrentInstance();
            if (con.isClosed()) {
                System.out.println("failed to connect with oracle");
            }
        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
            Logger.getLogger(BaseReportManager.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    /**
     * @return the parametersValue
     */
    public String getParametersValue() {
        return parametersValue;
    }

    /**
     * @param parametersValue the parametersValue to set
     */
    public void setParametersValue(String parametersValue) {
        this.parametersValue = parametersValue;
    }

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
        Set<String> Resources = context.getExternalContext().getResourcePaths("/WEB-INF/templates/jrxml");
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
        //System.out.println("Value of par1 is: " + getParametersValue());
        System.out.println("filling reports");
        //System.out.println("set report parameters for firm type");
        // Mistake!
        if (getParametersKey().length() != 0 && getParametersValue().length() != 0) {
            org = new OOReportGenerator();
            org.setTemplateName(templateName);
            System.out.println("Template name used: " + templateName);
            org.setOutputSuffix(outputFormat);
            System.out.println("Generate report in " + outputFormat + " format");
            org.setReportParameter(JRParameter.REPORT_CONNECTION, getCon());
            String[] keys = getParametersKey().split(",");
            String[] values = getParametersValue().split(",");
            if (keys.length != values.length) {
                System.out.println("keys-values count not match!");
            } else {
                //keys & values MUST be matched
                for (int i = 0; i < keys.length; i++) {
                    System.out.println("key=" + keys[i] + ", value=" + values[i]);
                    org.setReportParameter(keys[i], values[i]);
                }

                org.configureOnline();
                org.generateReport();
                System.out.println(" running time (msec): "
                        + (System.currentTimeMillis() - start));
                System.out.println("--------------------------------");
                long finish = System.currentTimeMillis();
                System.out.println("Running time: " + milisToSec(finish - start));
                System.out.println("===================================");
                //writeSummaryFile(jobFullName, firstStart, finish);
                closeConnection();
                outcome = "success";
            }
        } else {
            System.out.println("Either keys or lengths is problematic!");
            outcome = "failure";
            //System.exit(-1);
        }
        //return nowhere
        return "";
    }

    public void saveOnlineReport() {
        createOnlineReport();
        FileInputStream fin = null;
        File file = org.EXPORTED_FILE;

        byte[] reportFile = null;
        try {
            fin = new FileInputStream(file);
            reportFile = new byte[(int) file.length()];
            fin.read(reportFile);

            System.out.println("reportFile=" + reportFile.length);
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            //problematic around here
            response.setContentType(PossWebUtils.generateMimeType(outputFormat));
            response.setContentLength(reportFile.length);
            // response.setHeader("Content-disposition","inline; filename=" + fileName + ".pdf"); // show on browser
            response.setHeader("Content-disposition", "attachment; filename=" + file.getName()); // save file
            ServletOutputStream out = response.getOutputStream();
            out.write(reportFile);
            context.responseComplete();
            out.close();
            org.EXPORTED_FILE.deleteOnExit();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BaseReportManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BaseReportManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fin.close();
            } catch (IOException ex) {
                Logger.getLogger(BaseReportManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //return new ByteArrayInputStream(reportFile);
    }

    public void saveOfflineReport() {
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
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            response.setContentType(PossWebUtils.generateMimeType(org.EXPORTED_FILENAME));
            response.setContentLength(reportFile.length);
            // response.setHeader("Content-disposition","inline; filename=" + fileName + ".pdf"); // show on browser
            response.setHeader("Content-disposition", "attachment; filename=" + file.getName()); // save file
            ServletOutputStream out = response.getOutputStream();
            out.write(reportFile);
            context.responseComplete();
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BaseReportManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BaseReportManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fin.close();
            } catch (IOException ex) {
                Logger.getLogger(BaseReportManager.class.getName()).log(Level.SEVERE, null, ex);
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
        ServletContext sc = (ServletContext) context.getExternalContext().getContext();
        //could be quite problematic becaouse of file_separator differences
        String path = sc.getRealPath("/WEB-INF/templates/jrxml/exported");
        //String previewPath = path + sp + "preview_result.html";
        System.out.println("servlet context path=" + path);
        path=path.replace(System.getProperty("file.separator"),"/");
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
}
