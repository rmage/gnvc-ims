    /*
     * To change this template, choose Tools | Templates
     * and open the template in the editor.
     */
    package com.app.wms.engine.report;
     
    import com.app.wms.engine.db.factory.DaoFactory;
    import com.app.wms.engine.io.PossFileUtils;
    import com.app.wms.engine.model.ReportProperty;
    import java.io.File;
    import java.io.FileInputStream;
    import java.io.FileNotFoundException;
    import java.io.IOException;
    import java.io.InputStream;
    import java.sql.Connection;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;
    import java.util.Set;
    import java.util.logging.Level;
    //import javax.faces.context.FacesContext;
    import javax.servlet.ServletContext;
    import net.sf.jasperreports.engine.JRBand;
    import net.sf.jasperreports.engine.JRElement;
    import net.sf.jasperreports.engine.JRException;
    import net.sf.jasperreports.engine.JRExporterParameter;
    import net.sf.jasperreports.engine.JRExpression;
    import net.sf.jasperreports.engine.JRParameter;
    import net.sf.jasperreports.engine.JasperCompileManager;
    import net.sf.jasperreports.engine.JasperFillManager;
    import net.sf.jasperreports.engine.JasperPrint;
    import net.sf.jasperreports.engine.JasperReport;
    import net.sf.jasperreports.engine.design.JRDesignBand;
    import net.sf.jasperreports.engine.design.JRDesignSubreport;
    import net.sf.jasperreports.engine.design.JasperDesign;
    import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
    import net.sf.jasperreports.engine.util.FileResolver;
    import net.sf.jasperreports.engine.util.JRSaver;
    import net.sf.jasperreports.engine.util.SimpleFileResolver;
    import net.sf.jasperreports.engine.xml.JRXmlLoader;
    import org.apache.log4j.Logger;
     
    /**
     *
     * @author zyrex
     */
    public abstract class ReportGenerator {
     
        private static Logger log = Logger.getLogger(ReportGenerator.class);
        private JasperDesign jasperDesign = null;
        private JasperReport jasperReport = null;
        private JasperPrint jasperPrint = null;
        private Map<String, Object> reportParameters = new HashMap<String, Object>();
        private Map<JRExporterParameter, Object> exportParameters = new HashMap<JRExporterParameter, Object>();
        private JRFileVirtualizer jrVirtualizer = null;
        //private Map<String, Object> customProperties;
        private Connection _conn;
        protected static final String sp = File.separator;
        private String templateName = ""; //default template name
        private String outputSuffix = "";
        private String templateContextPath;
        private FileResolver fr;
        public String JRXML_FILENAME;
        public String JASPER_FILENAME;
        public String JRPRINT_FILENAME;
        public String EXPORTED_FILENAME;
        public String JRXML_PATH = ReportProperty.JRXML_PATH;
        public String JASPER_PATH = ReportProperty.JASPER_PATH;
        public String JRPRINT_PATH = ReportProperty.JRPRINT_PATH;
        public String EXPORTED_PATH = ReportProperty.EXPORTED_PATH;
        public String LOGO_FILENAME;
        public File LOGO_FILE;
        public File EXPORTED_FILE = null;
        private ServletContext context;
     
        /**
         * initiating this class without parameters means context used taken from faces
         */
        public ReportGenerator() {
        }
     
        public ReportGenerator(ServletContext context) {
            this.context = context;
        }
     
        /**
         * Configuring ReportGenerator:<br/>
         * <ul>
         * <li>configureTemplate()</li>
         * <ul>
         * <li>configurePath() - Configure path for getting template and store
         * generated report (output) file.</li>
         * <li>compileToFile() - Compile Template Design and produce .jasper file.</li>
         * <li>configureExportPath() - Configure export path and file name.</li>
         * </ul>
         * <li>configureExport()</li>
         * <ul>
         * <li>fillReport() - Filling compiled report template.</li>
         * <li>setting general export parameters.</li>
         * </ul>
         * </ul>
         *
         */
        public abstract void generateReport();
     
        public void configureOffline() {
            // prepare template design
            configureTemplate();
     
            // fill report template & prepare the generator
            configureExport();
        }
     
        public List<String> getImagesFromContext() {
            //String curpath = context.getExternalContext().;
            //System.out.println("context path=" + curpath);
            //Set<String> imageResources = FacesContext.getCurrentInstance().getExternalContext().getResourcePaths("/WEB-INF/templates/jrxml");
            Set<String> imageResources = context.getResourcePaths("/WEB-INF/templates/jrxml");
            //String realContextPath = context.getRealPath(request.getContextPath());
     
            return PossFileUtils.listImageFiles(imageResources);
        }
     
        public String getImagesPath() {
            String imgPath = context.getRealPath("/WEB-INF/templates/jrxml");
            imgPath = imgPath.replace(System.getProperty("file.separator"), "/");
            log.info("images path: " + imgPath);
            return imgPath;
        }
     
        public void configureOnline() {
            try {
                //FacesContext context = FacesContext.getCurrentInstance();
                System.out.println(">>>>>>>>>>>>>>>>>>>> "+context);
                System.out.println(">>>>>>>>>>>>>>>>>>>> "+templateName);
                InputStream jrxmlStream = context.getResourceAsStream("/WEB-INF/templates/jrxml/" + templateName + ".jrxml");
     
                if (jrxmlStream == null) {
                    log.error("template not found!");
                } else {
                    EXPORTED_FILE = File.createTempFile(templateName, "");
                    configurePath(EXPORTED_FILE);
                    copyAllLogosToTmp();
                    compileToFile(jrxmlStream);
                    configureExportFile();
                    configureExport();
                }
     
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(ReportGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
     
        public void copyLogoToTmp(InputStream fis) {
            LOGO_FILE = PossFileUtils.copyStreamToFile(fis, LOGO_FILENAME);
        }
     
        /**
         * configureTemplate()
         * <ul>
         * <li>configurePath() - Configure path for getting template and store
         * generated report (output) file.</li>
         * <li>compileToFile() - Compile Template Design and produce .jasper file.</li>
         * <li>configureExportPath() - Configure export path and file name.</li>
         * </ul>
         *
         *
         */
        public void configureTemplate() {
            configurePath();
            compileToFile();
            configureExportPath();
        }
     
        /**
         * Configure path for getting template and store compiled report file. Must
         * be the first method to call.
         */
        private void configurePath() {
            File f;
     
            /*
            if (null == reportTemplate.getOutputSufix()
            || "".equals(reportTemplate.getOutputSufix().trim())) {
            df = new SimpleDateFormat("yyMMdd");
            } else {
            try {
            if (reportTemplate.getOutputSufix().startsWith("yy")
            || reportTemplate.getOutputSufix().startsWith("MM")
            || reportTemplate.getOutputSufix().startsWith("dd")) {
            df = new SimpleDateFormat(reportTemplate.getOutputSufix());
            } else {
            df = new SimpleDateFormat(reportTemplate.getOutputSufix().substring(2));
            }
            } catch (IllegalArgumentException iae) {
            log.error("Invalid output sufix: "
            + reportTemplate.getOutputSufix());
            }
            }*/
     
            StringBuffer jrxmlbuff = new StringBuffer();
            jrxmlbuff.append(JRXML_PATH).append("/").append(
                    getTemplateName()).append(".jrxml");
            JRXML_FILENAME = jrxmlbuff.toString();
     
            if (!PossFileUtils.isFileExists(JRXML_FILENAME)) {
                log.error("template ");
                log.error(JRPRINT_FILENAME);
                log.error(" is not found!");
            }
     
            StringBuffer jasperBuff = new StringBuffer();
            jasperBuff.append(JASPER_PATH).append("/").append(
                    getTemplateName()).append(".jasper");
            JASPER_FILENAME = jasperBuff.toString();
     
            f = new File(JASPER_FILENAME).getParentFile();
            if (!f.exists()) {
                f.mkdirs();
            }
     
            StringBuffer jrprintBuff = new StringBuffer();
            jrprintBuff.append(JRPRINT_PATH).append("/").append(
                    getTemplateName()).append(".jrprint");
            JRPRINT_FILENAME = jrprintBuff.toString();
     
            f = new File(JRPRINT_FILENAME).getParentFile();
            if (!f.exists()) {
                f.mkdirs();
            }
            f = null;
        }
     
        private void copyAllLogosToTmp() {
            InputStream logoStream;
            /**
             * To be replaced with more robust image copier
             */
            for (String image : getImagesFromContext()) {
                logoStream = context.getResourceAsStream("/WEB-INF/templates/jrxml/" + image);
                if (logoStream == null) {
                    log.error("logo ");
                    log.error(image);
                    log.error(" is not found!");
                } else {
                    StringBuffer logoBuff = new StringBuffer();
                    logoBuff.append(templateContextPath).append(sp).append(image);
                    LOGO_FILENAME = logoBuff.toString();
                    //System.out.println("image tmp path=" + LOGO_FILENAME);
                    copyLogoToTmp((InputStream) logoStream);
                }
            }
        }
     
        private void configurePath(File fjrxml) {
            File f;
            templateContextPath = fjrxml.getParent();        
            log.info("temp path=");
            log.info(templateContextPath);
            StringBuffer jasperBuff = new StringBuffer();
            jasperBuff.append(templateContextPath).append(sp).append(
                    getTemplateName()).append(".jasper");
            JASPER_FILENAME = jasperBuff.toString();
     
            StringBuffer jasperPathBuff = new StringBuffer();
            jasperPathBuff.append(templateContextPath);
            JASPER_PATH = jasperPathBuff.toString();
     
            f = new File(JASPER_FILENAME).getParentFile();
            if (!f.exists()) {
                f.mkdirs();
            }
     
            StringBuffer jrprintBuff = new StringBuffer();
            jrprintBuff.append(templateContextPath).append(sp).append("jrprint").append(sp).append(
                    getTemplateName()).append(".jrprint");
            JRPRINT_FILENAME = jrprintBuff.toString();
     
            f = new File(JRPRINT_FILENAME).getParentFile();
            if (!f.exists()) {
                f.mkdirs();
            }
     
            f = null;
        }
     
        /**
         * Compile Template Design and produce .jasper file
         *
         * @throws IDXReportException
         */
        private void compileToFile() {
            try {
                log.info("JRXML_FILE:" + JRXML_FILENAME);
                setJasperDesign(JRXmlLoader.load(new FileInputStream(JRXML_FILENAME)));
     
                /*
                 * JasperDesign design = JRXmlLoader.load(
                new LegacyJasperInputStream(new FileInputStream("MyXsdBasedDesign.jrxml"))
                );
     
                 */
                if (getJasperDesign() == null) {
                    log.error("jasper design is null");
                }
                setJasperReport(JasperCompileManager.compileReport(getJasperDesign()));
                JRSaver.saveObject(getJasperReport(), JASPER_FILENAME);
            } catch (FileNotFoundException ex) {
                log.error(ex);
            } catch (JRException jre) {
                jre.printStackTrace();
            }
        }
     
        private Boolean compileSubReport() {
            JRBand[] bands = jasperDesign.getDetailSection().getBands();
            //JRDesignBand jrBand;
            for (JRBand band : bands) {
                //jrBand = (JRDesignBand) band;
                JRDesignBand jrBand = (JRDesignBand) jasperDesign.getDetailSection().getBands()[0];
                JRElement[] jrElements = jrBand.getElements();
                for (JRElement jrElement : jrElements) {
                    log.info("jrElement:");
                    log.info(jrElement);
                    if (jrElement instanceof JRDesignSubreport) {
                        try {
                            log.info("Sub report found, compiling now.");
                            log.info("jrElement subreport");
                            log.info(jrElement);
                            JRDesignSubreport subReportDesign = (JRDesignSubreport) jrElement;
                            JRExpression jrExpression = subReportDesign.getExpression();
                            String file = jrExpression.getText();
                            file = PossFileUtils.displayFilenameOnly(file);
                            file = PossFileUtils.stripLeadingAndTrailingQuotes(file);
                            log.info("subreport filename:");
                            log.info(file);
                            //String subreportTemplateName = file + ".jrxml";
                            InputStream subreportJrxmlStream = context.getResourceAsStream("/WEB-INF/templates/jrxml/" + file + ".jrxml");
                            log.info("subreport length=");
                            log.info(subreportJrxmlStream.available());
                            JasperDesign subDesign = JRXmlLoader.load(subreportJrxmlStream);
                            //System.out.println("subreport path:" + subreportTemplateName);
                            JasperReport subReport = JasperCompileManager.compileReport(subDesign);
                            String jasperName = JASPER_PATH + sp + file + ".jasper";
                            JRSaver.saveObject(subReport, jasperName);
                            log.info("generated subreport jasper path=");
                            log.info(jasperName);
                        } catch (IOException ex) {
                            java.util.logging.Logger.getLogger(ReportGenerator.class.getName()).log(Level.SEVERE, null, ex);
                            log.error(ex);
                        } catch (JRException ex) {
                            java.util.logging.Logger.getLogger(ReportGenerator.class.getName()).log(Level.SEVERE, null, ex);
                            log.error(ex);
                            return false;
                        }
                    }
                }
            }
            return true;
        }
     
        private void compileToFile(InputStream is) {
            try {
                setJasperDesign(JRXmlLoader.load(is));
     
                /*
                 * JasperDesign design = JRXmlLoader.load(
                new LegacyJasperInputStream(new FileInputStream("MyXsdBasedDesign.jrxml"))
                );
     
                 */
                if (getJasperDesign() == null) {
                    log.error("jasper design is null");
                }
                Boolean compileStatus = compileSubReport();
                if(!compileStatus)
                    log.error("subreport compilation failed");
                setJasperReport(JasperCompileManager.compileReport(getJasperDesign()));
                JRSaver.saveObject(getJasperReport(), JASPER_FILENAME);
            } catch (JRException jre) {
                jre.printStackTrace();
            }
        }
     
        /**
         * Configure path for getting template and store exported report file.
         */
        private void configureExportPath() {
            File f;
            StringBuffer targetBuff = new StringBuffer();
            //collectCustomProperties();
     
            targetBuff.append(EXPORTED_PATH).append(sp);
     
            targetBuff.append(getTemplateName()).append(".").append(getOutputSuffix());// .toLowerCase());
            EXPORTED_FILENAME = targetBuff.toString();
     
            f = new File(EXPORTED_FILENAME).getParentFile();
            if (!f.exists()) {
                f.mkdirs();
            }
     
            setExportParameter(JRExporterParameter.OUTPUT_FILE_NAME, EXPORTED_FILENAME);
            ReportProperty.setExportedFileName(EXPORTED_FILENAME);
        }
     
        private void configureExportFile() {
            //fillReport();
            setExportParameter(JRExporterParameter.OUTPUT_FILE, EXPORTED_FILE);
            log.info("exported file= ");
            log.info(EXPORTED_FILE);
            ReportProperty.setExportedFile(EXPORTED_FILE);
        }
     
        /**
         * configureExport()
         * <ul>
         * <li>fillReport() - Filling compiled report template.</li>
         * <li>setting general export parameters.</li>
         * </ul>
         *
         */
        public void configureExport() {
            fillReport();
            setExportParameter(JRExporterParameter.JASPER_PRINT, getJasperPrint());
        }
     
        public void setReportParameter(String param, Object value) {
           
            if(param.equals("REPORT_CONNECTION") && value == null) {
                try {
                    setConnection(DaoFactory.getReportDataSource().getConnection());
                    value = DaoFactory.getReportDataSource().getConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }
               
            }
           
            this.reportParameters.put(param, value);
            log.info("param=" + param + ", value=" + value);
            if (value instanceof Connection) {
                setConnection((Connection) value);
            }
        }
     
        public void setConnection(Connection _conn) {
            this._conn = _conn;
        }
     
        /**
         * @return the exportParameters
         */
        public Map<JRExporterParameter, Object> getExportParameters() {
            return exportParameters;
        }
     
        /**
         * @param param
         */
        public void setExportParameters(Map<JRExporterParameter, Object> param) {
            this.exportParameters = param;
        }
     
        /**
         * @param param
         *            export parameter Map
         */
        public void addExportParameters(
                Map<? extends JRExporterParameter, Object> param) {
            exportParameters.putAll(param);
        }
     
        /**
         * Filling compiled report template and store JasperPrint Object to file.
         *
         * @param dest
         *            Destination path and file name.
         *
         */
        protected void fillReportToFile(String dest) {
            if (null == getJasperPrint()) {
                fillReport();
            }
     
            try {
                JRSaver.saveObject(getJasperPrint(), dest);
            } catch (JRException jre) {
                jre.printStackTrace();
            }
        }
     
        /**
         * @param param
         * @param value
         */
        public void setExportParameter(JRExporterParameter param, Object value) {
            this.exportParameters.put(param, value);
        }
     
        private void fillReport() {
            // jrVirtualizer = (JRFileVirtualizer) reportParameters
            // .get(JRParameter.REPORT_VIRTUALIZER);
            // if (null == jrVirtualizer) {
            // File tmp = new File(JASPER_PATH + sp + "cache");
            // if (!tmp.exists())
            // tmp.mkdirs();
            // jrVirtualizer = new JRFileVirtualizer(30, tmp.getAbsolutePath());
            // }
            // reportParameters.put(JRParameter.REPORT_VIRTUALIZER, jrVirtualizer);
     
            try {
                long start = System.currentTimeMillis();
                //set relative path during compilation process in jasper
                fr = new SimpleFileResolver(new File(templateContextPath));
                reportParameters.put(JRParameter.REPORT_FILE_RESOLVER, fr);
                setJasperPrint(JasperFillManager.fillReport(getJasperReport(), reportParameters));
                //System.out.println("isFirmTypeParamSet=" + reportParameters.containsKey("firmTypeParam"));
            } catch (JRException jre) {
                jre.printStackTrace();
     
            }
     
            // jrVirtualizer.setReadOnly(true);
        }
     
        /**
         * @return the jasperDesign
         */
        public JasperDesign getJasperDesign() {
            return jasperDesign;
        }
     
        /**
         * @param jasperDesign the jasperDesign to set
         */
        public void setJasperDesign(JasperDesign jasperDesign) {
            this.jasperDesign = jasperDesign;
        }
     
        /**
         * @return the jasperReport
         */
        public JasperReport getJasperReport() {
            return jasperReport;
        }
     
        /**
         * @param jasperReport the jasperReport to set
         */
        public void setJasperReport(JasperReport jasperReport) {
            this.jasperReport = jasperReport;
        }
     
        /**
         * @return the jasperPrint
         */
        public JasperPrint getJasperPrint() {
            return jasperPrint;
        }
     
        /**
         * @param jasperPrint the jasperPrint to set
         */
        public void setJasperPrint(JasperPrint jasperPrint) {
            this.jasperPrint = jasperPrint;
        }
     
        /**
         * @return the jrVirtualizer
         */
        public JRFileVirtualizer getJrVirtualizer() {
            return jrVirtualizer;
        }
     
        /**
         * @param jrVirtualizer the jrVirtualizer to set
         */
        public void setJrVirtualizer(JRFileVirtualizer jrVirtualizer) {
            this.jrVirtualizer = jrVirtualizer;
        }
     
        /**
         * @return the outputSuffix
         */
        public String getOutputSuffix() {
            return outputSuffix;
        }
     
        /**
         * @param outputSuffix the outputSuffix to set
         */
        public void setOutputSuffix(String outputSuffix) {
            this.outputSuffix = outputSuffix;
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
         * @return the context
         */
        public ServletContext getContext() {
            return context;
        }
     
        /**
         * @param context the context to set
         */
        public void setContext(ServletContext context) {
            this.context = context;
        }
    }
