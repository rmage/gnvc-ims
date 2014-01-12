    /*
     * To change this template, choose Tools | Templates
     * and open the template in the editor.
     */
    package com.app.wms.web.controller;
     
    import com.app.wms.engine.db.dto.Branch;
    import com.app.wms.engine.db.dto.GenericDtoReport;
    import com.app.wms.engine.db.dto.PriceCatalog;
    import com.app.wms.engine.db.dto.map.LoginUser;
    import com.app.wms.engine.db.factory.DaoFactory;
    import com.app.wms.engine.io.PossFileUtils;
    import com.app.wms.engine.report.OOReportGenerator;
    import java.io.FileNotFoundException;
    import java.io.IOException;
    import java.sql.Connection;
    import java.sql.SQLException;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.logging.Level;
    import java.util.logging.Logger;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import net.sf.jasperreports.engine.JRParameter;
    import org.apache.commons.logging.Log;
    import org.apache.commons.logging.LogFactory;
    import org.springframework.web.servlet.ModelAndView;
     
    /**
     * @author zyrex
     * Assuming this class can inherit grand parent class MultiActionController
     */
    public class ReportManagerController extends BaseReportManagerController {
     
        protected static Log log = LogFactory.getLog(ReportManagerController.class);
     
        @Override
        public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
            try {
                //returnPath = request.getParameter("returnPath");
                String requestPath = request.getRequestURL().toString();
                //log.debug("getting request context path: " + requestPath);
                String requestTail = PossFileUtils.displayFilenameOnly(requestPath);
                log.info("request tail path: " + requestTail);
                //log.debug("loading report data source");
                returnPath = "report/" + requestTail;
                if (con == null) {
                    // PossDs = DaoFactory.getDataSource();
                	ImsDs = DaoFactory.getReportDataSource();
                    con = ImsDs.getConnection();
     
                }
     
                context = getServletContext();
               
                System.out.println("context >>>>>>>>>> "+context);
                if (con.isClosed()) {
                    log.warn("failed to connect with oracle");
                }
                LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
                if (lu == null) {
                    log.warn("no login detected");
                    return new ModelAndView("login");
                }
                String userId = lu.getUsername();
                //log.info(userId + ": " + lu.getIsDsa() + ":::" + lu.getIsWic());
                //log.info("create new gdr object for userId:" + userId);
                gdr = new GenericDtoReport();
                gdr.setUsername(userId);
                /*
                if (lu.getIsDsa().equalsIgnoreCase("y") && lu.getIsWic().equalsIgnoreCase("y")) {
                    gdr.setBgType("%%");
                    log.info("superadmin detected");
                    //log.info("parameter size:" + gdr.getParameterValues());
                    //bgList = bg.findAll(null, null);
                    bgList = bg.findByAuthLogin(lu.getUserId());
                } else if (lu.getIsDsa().equalsIgnoreCase("y")) {
                    gdr.setBgType("BG_DSA");
                    log.info("dsa detected");
                    //bgList = bg.findAll("N", "Y");
                    // perubahan by Tri, 2010-11-03
                    bgList = bg.findByAuthLogin(lu.getUserId());
                } else if (lu.getIsWic().equalsIgnoreCase("y")) {
                    gdr.setBgType("BG_WIC");
                    log.info("wic detected");
                    //bgList = bg.findAll("Y", "N");
                    // perubahan by Tri, 2010-11-03
                    bgList = bg.findByAuthLogin(lu.getUserId());
                } else {
                    log.info("current user doesn't have wic or dsa membership");
                    gdr.setBgType("null");
                    //bgList = bg.findAll("N", "N");
                    // perubahan by Tri, 2010-11-03
                    bgList = bg.findByAuthLogin(lu.getUserId());
                }
                */
                boolean isALL = false;
     
                HashMap model = new HashMap();
                model.put("lu", lu);
                model.put("isALL", isALL);
     
                ModelAndView mav = new ModelAndView(returnPath);
                mav.addObject("dto", gdr);
                mav.addObject("model", model);
                //bind(request, gdr);
                return mav;
            } catch (SQLException ex) {
                Logger.getLogger(BaseReportManagerController.class.getName()).log(Level.SEVERE, null, ex);
                return new ModelAndView("Error");
            }
        }
     
        public String createOnlineReportLegacy() {
            long start = System.currentTimeMillis();
            //setTemplateName();
            //setParameterKeys();
     
            //System.out.println(templateName + "====" + parametersKey);
     
            //System.out.println("Value of par1 is: " + getParametersValue());
            //log.info("filling reports");
            //System.out.println("set report parameters for firm type");
            // Mistake!
            if (getParametersKey().length() != 0 && this.getParameterValues().size() != 0) {
                String[] keys = getParametersKey().split(",");
                //String[] values = getParametersValue().split(",");
                if (keys.length != this.getParameterValues().size()) {
                    //log.warn("keys-values count not match!");
                    //System.out.println("parameter keys = " + getParametersKey());
                    //System.out.println("parameter values = ");
    //                for (String val : this.getParameterValues()) {
    //                    System.out.println("val = " + val);
    //                }
                } else {
                    org = new OOReportGenerator(context);
                    org.setTemplateName(templateName);
                    log.info("Template name used: " + templateName);
                    org.setOutputSuffix(outputFormat);
                    //log.info("Generate report in " + outputFormat + " format");
     
                    /**
                     * Section to copy template embedded images
                     */
                    org.setReportParameter(JRParameter.REPORT_CONNECTION, getCon());
     
                    /**
                     * debug purpose logging
                     */
                    for (int i = 0; i < keys.length; i++) {
                        //System.out.println("key=" + keys[i] + ", value=" + this.getParameterValues().get(i));
                        org.setReportParameter(keys[i], this.getParameterValues().get(i));
                    }
     
                    org.configureOnline();
                    org.generateReport();
    //                System.out.println(" running time (msec): " + (System.currentTimeMillis() - start));
    //                System.out.println("--------------------------------");
                    long finish = System.currentTimeMillis();
    //                System.out.println("Running time: " + milisToSec(finish - start));
    //                System.out.println("===================================");
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
     
        @Override
        public String createOnlineReport() {
            long start = System.currentTimeMillis();
            //setTemplateName();
            //setParameterKeys();
     
            //System.out.println(templateName + "====" + parametersKey);
     
            //System.out.println("Value of par1 is: " + getParametersValue());
            //log.info("filling reports");
            //System.out.println("set report parameters for firm type");
            // Mistake!
            if (getParameterKeys().size() != 0 && this.getParameterValues().size() != 0) {
                //String[] keys = getParametersKey().split(",");
                //String[] values = getParametersValue().split(",");
                if (this.getParameterKeys().size() != this.getParameterValues().size()) {
                    log.warn("keys-values count not match!");
                    //System.out.println("parameter keys = " + getParametersKey());
                    //System.out.println("parameter values = ");
                    for (int i = 0; i < this.getParameterKeys().size(); i++) {
                        //log.debug(parameterKeys.get(i) + " = " + parameterValues.get(i));
                    }
                } else {
                    if(context == null)
                        context = getServletContext();
                   
                    if(getCon() == null){
                        try {
                            Connection connection = DaoFactory.getReportDataSource().getConnection();
                            System.out.println("connection ="+connection);
                            org.setConnection(connection);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }                                        
                    }
                   
                    System.out.println("context >>>>>>>> "+context);
                   
                    org = new OOReportGenerator(context);
                    org.setTemplateName(templateName);
                    log.info("Template name used: " + templateName);
                    org.setOutputSuffix(outputFormat);
                    //log.info("Generate report in " + outputFormat + " format");
     
                    /**
                     * Section to copy template embedded images
                     */
                    org.setReportParameter(JRParameter.REPORT_CONNECTION, getCon());
     
                    /**
                     * debug purpose logging
                     */
                    for (int i = 0; i < this.getParameterKeys().size(); i++) {
                        //log.debug("key=" + parameterKeys.get(i) + ", value=" + getParameterValues().get(i));
                        org.setReportParameter(parameterKeys.get(i), getParameterValues().get(i));
                    }
     
                    org.configureOnline();
                    org.generateReport();
                    //log.debug(" running time (msec): " + (System.currentTimeMillis() - start));
                    //log.debug("--------------------------------");
                    long finish = System.currentTimeMillis();
                    //log.debug("Running time: " + milisToSec(finish - start));
                    //log.debug("===================================");
                    //writeSummaryFile(jobFullName, firstStart, finish);
                    //might be better commented to improve speed;
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
     
        @Override
        public void saveOnlineReport(HttpServletRequest request, HttpServletResponse response, GenericDtoReport oCommand) throws Exception {
            //GenericDtoReport gdr = (GenericDtoReport) request.getAttribute("reportDto");
            if (oCommand == null) {
                log.warn("zero command object");
                //return super.findByPrimaryKey(request, response);
                //System.out.println("failed to get GenericDtoReport object");
            }
     
            if (gdr == null) {
                log.warn("bean model was resetted, hence empty");
            }
     
            if (con == null) {
                log.warn("connection object was closed attempting to re-connect with oracle");
                ImsDs = DaoFactory.getReportDataSource();
                con = ImsDs.getConnection();
            }
            //gdr = new GenericDtoReport();
            //gdr.setUsername(userId);      
     
            gdr = oCommand;
            //parametersKey = request.getParameter("parametersKey");
            //System.out.println("parametersKey: " + parametersKey);
            templateName = request.getParameter("templateName");
            //log.debug("gdr parameter key size: " + gdr.getParameterKeys().size());
            //log.debug("gdr parameter value size: " + gdr.getParameterValues().size());
    //        for (int i = 0; i < gdr.getParameterKeys().size(); i++) {
    //            System.out.print("gdr---");
    //            System.out.println(parameterKeys.get(i) + " = " + parameterValues.get(i));
    //        }
            setParameterKeys(gdr.getParameterKeys());
            setParameterValues(gdr.getParameterValues());
            outputFormat = gdr.getOutputFormat();
            createOnlineReport();
     
            try {
                printToStream(response);
    //            return super.findByPrimaryKey(request, response);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BaseReportManagerController.class.getName()).log(Level.SEVERE, null, ex);
    //            return super.findByPrimaryKey(request, response);
            } catch (IOException ex) {
                Logger.getLogger(BaseReportManagerController.class.getName()).log(Level.SEVERE, null, ex);
    //            return super.findByPrimaryKey(request, response);
            }
        }
    }
