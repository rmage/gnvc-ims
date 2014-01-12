/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.wms.engine.model;


import java.io.File;

/**
 *
 * @author zyrex
 */
public class ReportProperty {

    public static String ETL_JOB_PATH = "c:/app/etl/";
    public static String ETL_PRODUCT_PRICE_JOB_FILENAME = "etlProductPriceJob.xml";
    public static String ETL_PRODUCT_ALIAS_PRICE_JOB_FILENAME = "etlProductAliasPriceJob.xml";
    public static String ETL_PRODUCT_PRICE_INPUT_FILENAME = "PRODUCT_PRICE2.xls";
    public static String ETL_SERVICE_PRICE_JOB_FILENAME = "etlServicePriceJob.xml";
    public static String ETL_PRODUCT_JOB_FILENAME = "etlProductJob.xml";
    public static String ETL_PRODUCT_ALIAS_JOB_FILENAME = "etlProductAliasJob.xml";
    public static String ETL_SERVICE_JOB_FILENAME = "etlServiceJob.xml";
    public static String ETL_USER_JOB_FILENAME = "etlUserJob.xml";
    public static String ETL_SN_HISTORY_JOB_FILENAME = "etlSnHistoryJob.xml";
    public static String ETL_BUNDLE_JOB_FILENAME = "etlBundleJob.xml";
    public static String ETL_BUNDLE_PRICE_JOB_FILENAME = "etlBundlePriceJob.xml";
    public static String ETL_BG_JOB_FILENAME = "etlBgJob.xml";
    public static String ETL_AGENT_JOB_FILENAME = "etlAgentJob.xml";
    public static String ETL_STOCK_SN_JOB_FILENAME = "etlStockSnJob.xml";
    public static String ETL_BUNDLE_DTL_ALIAS_FILENAME = "etlBundleDtlAliasJob.xml";
    public static String ETL_BUNDLE_DTL_ALIAS_PRICE_FILENAME = "etlBundleDtlAliasPriceJob.xml";
    public static String ETL_AGENT_SUPPLIER_JOB_FILENAME = "etlAgentSupplierJob.xml";
    public static String ETL_VENDOR_JOB_FILENAME = "etlVendorJob.xml";
    public static final String JRXML_PATH = "/WEB-INF/templates/jrxml";
    public static final String JASPER_PATH = "/WEB-INF/templates/jrxml";
    public static final String JRPRINT_PATH = "/WEB-INF/templates/jrxml";
    public static final String EXPORTED_PATH = "/WEB-INF/templates/jrxml";
    public static String JRXML_FILE;
    public static String JASPER_FILE;
    public static String JRPRINT_FILE;
    private static String exportedFileName;
    private static File exportedFile;

    /**
     * @return the exportedFileName
     */
    public static String getExportedFileName() {
        return exportedFileName;
    }

    /**
     * @param exportedFile2
     */
    public static void setExportedFileName(String exportedFile2) {
        exportedFileName = exportedFile2;
    }

    /**
     * @return the exportedFile
     */
    public static File getExportedFile() {
        return exportedFile;
    }

    /**
     * @param aExportedFile the exportedFile to set
     */
    public static void setExportedFile(File aExportedFile) {
        exportedFile = aExportedFile;
    }
}
