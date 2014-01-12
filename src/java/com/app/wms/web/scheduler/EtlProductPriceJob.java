/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.scheduler;

import com.app.wms.engine.model.ReportProperty;
import java.util.Date;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.logging.LogWriter;
import org.pentaho.di.job.JobEntryLoader;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.trans.StepLoader;

/**
 *
 * @author zyrex
 */
public class EtlProductPriceJob extends EtlGenericJob {

    public void execute(String catalogCode) {
        String today = EtlJob.dateFormat.format(new Date());
        log.info("========================================");
        log.info("Extract and Transform Product Price");
        log.info("----------------------------------------");
        log.info("Date     : " + today);
        log.info("========================================");
        long firstStart = System.currentTimeMillis();
        LogWriter logwrt = LogWriter.getInstance(LogWriter.LOG_LEVEL_BASIC);

        if (jobFilename == null) {
            //fill with default job
            jobFilename = ReportProperty.ETL_PRODUCT_PRICE_JOB_FILENAME;
        }

        System.out.println("ETL_JOB_FILENAME: " + jobFilename);
        try {
            StepLoader.init();
            JobEntryLoader.init();
            JobMeta transMeta = new JobMeta(logwrt, jobFilename, null);
            //String[] kargs = {"PRODUCT_PRICE.xls"};
            //transMeta.setArguments(kargs);
            transMeta.setParameterValue("ParExcelInput", getInputFilename());
            transMeta.setParameterValue("ParCatalogCode", catalogCode);
            org.pentaho.di.job.Job job = new org.pentaho.di.job.Job(logwrt, StepLoader.getInstance(), null, transMeta);
            job.getJobMeta().setInternalKettleVariables(job);
            job.getJobMeta().activateParameters();
            job.start();
            //job.waitUntilFinished();
            if (job.getErrors() > 0) {
                log.error("There were errors during transformation execution.");
                //ReportGeneratorJob.writeSummaryFileError(jobFullName, firstStart, System.currentTimeMillis());
                throw new RuntimeException(
                        "There were errors during transformation execution.");
            }
        } catch (KettleException e) {
            log.error(e.getMessage(), e);
            //ReportGeneratorJob.writeSummaryFileError(jobFullName, firstStart, System.currentTimeMillis());
        }
        long finish = System.currentTimeMillis();
        log.info("Extract and Transform Running time: " + EtlJob.milisToSec(finish - firstStart));
        log.info("===================================");

    }
}
