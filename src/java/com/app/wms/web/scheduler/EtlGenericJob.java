/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.scheduler;

import com.app.wms.engine.model.ReportProperty;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.logging.LogWriter;
import org.pentaho.di.core.parameters.UnknownParamException;
import org.pentaho.di.job.JobEntryLoader;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.trans.StepLoader;

/**
 *
 * @author zyrex
 */
public class EtlGenericJob {

    protected static Log log = LogFactory.getLog(EtlGenericJob.class);
    protected String inputFilename;
    protected String jobFilename;
    protected JobMeta transMeta;
    protected LogWriter logwrt;
    protected HashMap pmap;

    public EtlGenericJob() {
        pmap = new HashMap();
    }

    public void execute() throws RuntimeException, KettleException {
        String today = EtlJob.dateFormat.format(new Date());
        log.info("========================================");
        log.info("Extract and Transformation " );
        log.info("----------------------------------------");
        log.info("Date     : " + today);
        log.info("========================================");
        long firstStart = System.currentTimeMillis();
        logwrt = LogWriter.getInstance(LogWriter.LOG_LEVEL_BASIC);

        if (jobFilename == null) {
            //fill with default job
            jobFilename = ReportProperty.ETL_PRODUCT_PRICE_JOB_FILENAME;
        }

        System.out.println("ETL_JOB_FILENAME: " + jobFilename);

        StepLoader.init();
        JobEntryLoader.init();
        transMeta = new JobMeta(logwrt, jobFilename, null);
        //String[] kargs = {"PRODUCT_PRICE.xls"};
        //transMeta.setArguments(kargs);
        transMeta.setParameterValue("ParExcelInput", getInputFilename());
        setPassedParameters();
        org.pentaho.di.job.Job job = new org.pentaho.di.job.Job(logwrt, StepLoader.getInstance(), null, transMeta);
        job.getJobMeta().setInternalKettleVariables(job);
        job.getJobMeta().activateParameters();
        job.start();
        //job.waitUntilFinished();        

        job.waitUntilFinished();

        log.info("errors number:" + job.getErrors());
        if (job.getErrors() > 0) {
            log.error("There were errors during transformation execution.");
            //ReportGeneratorJob.writeSummaryFileError(jobFullName, firstStart, System.currentTimeMillis());
            throw new RuntimeException(
                    "There were errors during transformation execution.");
        }        
        
        long finish = System.currentTimeMillis();
        log.info("Extract and Transform Running time: " + EtlJob.milisToSec(finish - firstStart));
        //System.out.println("Extract and Transform Running time: " + EtlJob.milisToSec(finish - firstStart));
        log.info("===================================");
    }

    /**
     *
     * @param param parameterKeys
     * @param value parameterValues
     * @throws UnknownParamException
     */
    public void setEtlParameter(String param, String value){
        pmap.put(param, value);
        //transMeta.setParameterValue(param, value);
        //log.info("param=" + param + ", value=" + value.toString());
    }

    public void addEtlParameters(HashMap inmap){
        pmap.putAll(inmap);
    }

    private void setPassedParameters() throws UnknownParamException {
        Iterator it = pmap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            /*
             * Disabled obviously for security reason, only enabled it if a need exist
             */
            log.info("param=" + (String)pairs.getKey() + ", value=" + (String)pairs.getValue());
            transMeta.setParameterValue((String)pairs.getKey(), (String)pairs.getValue());
        }
    }

    /**
     * @return the jobFilename
     */
    public String getJobFilename() {
        return jobFilename;
    }

    /**
     * @param jobFilename the jobFilename to set
     */
    public void setJobFilename(String jobFilename) {
        this.jobFilename = jobFilename;
    }

    /**
     * @return the inputFilename
     */
    public String getInputFilename() {
        return inputFilename;
    }

    /**
     * @param inputFilename the inputFilename to set
     */
    public void setInputFilename(String inputFilename) {
        this.inputFilename = inputFilename;
    }
}
