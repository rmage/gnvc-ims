/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.wms.engine.report;

import java.util.ArrayList;
import net.sf.jasperreports.engine.JRParameter;

/**
 *
 * @author zyrex
 */
public class ReportManager extends BaseReportManager {

    private ArrayList<String> parameterValues = new ArrayList<String>();
    private String parameterValue = "";

    public ReportManager() {
        super();
    }

    /**
     *
     * @return String
     */
    @Override
    public String createOnlineReport() {
        long start = System.currentTimeMillis();
        //setTemplateName();
        //setParameterKeys();

        //System.out.println(templateName + "====" + parametersKey);

        //System.out.println("Value of par1 is: " + getParametersValue());
        System.out.println("filling reports");
        //System.out.println("set report parameters for firm type");
        // Mistake!
        if (getParametersKey().length() != 0 && this.parameterValues.size() != 0) {
            String[] keys = getParametersKey().split(",");
            //String[] values = getParametersValue().split(",");
            if (keys.length != this.parameterValues.size()) {
                System.out.println("keys-values count not match!");
                System.out.println("parameter keys = " + getParametersKey());
                System.out.println("parameter values = ");
                for (String val : this.parameterValues) {
                    System.out.println("val = " + val);
                }
            } else {
                org = new OOReportGenerator();
                org.setTemplateName(templateName);
                System.out.println("Template name used: " + templateName);
                org.setOutputSuffix(outputFormat);
                System.out.println("Generate report in " + outputFormat + " format");

                /**
                 * Section to copy template embedded images
                 */
                org.setReportParameter(JRParameter.REPORT_CONNECTION, getCon());

                /**
                 * debug purpose logging
                 */
                for (int i = 0; i < keys.length; i++) {
                    System.out.println("key=" + keys[i] + ", value=" + this.parameterValues.get(i));
                    org.setReportParameter(keys[i], this.parameterValues.get(i));
                }

                org.configureOnline();
                org.generateReport();
                System.out.println(" running time (msec): " + (System.currentTimeMillis() - start));
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
        return outcome;
    }

    /**
     * @return the parameterValue
     */
    public String getParameterValue() {
        return parameterValue;
    }

    /**
     * @param parameterValue the parameterValue to set
     */
    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
        this.parameterValues.add(parameterValue);
    }
}
