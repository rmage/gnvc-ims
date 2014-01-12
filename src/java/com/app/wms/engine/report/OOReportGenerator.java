/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.wms.engine.report;


import it.businesslogic.ireport.export.JRTxtExporterParameter;
import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import org.apache.log4j.Logger;

/**
 *
 * @author zyrex
 */
public class OOReportGenerator extends ReportGenerator {
    // private JRTextExporter textExporter = null;

    private static Logger log = Logger.getLogger(OOReportGenerator.class);
    private JRTextExporter textExporter = null;
    private long start = 0;
    private JRXlsExporter xlsExporter = null;
    private JRCsvExporter csvExporter = null;
    private JRRtfExporter rtfExporter = null;
    private JRPdfExporter pdfExporter = null;
    private JRHtmlExporter htmlExporter = null;

    public OOReportGenerator(){

    }

    /**
     *
     * @param context Spring context
     */
    public OOReportGenerator(ServletContext context){
        super(context);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.akar.idx.report.ReportGenerator#generateReport()
     */

    @Override
    public void generateReport() {
        //log.info("Jasper Template");

        String outPutType = super.getOutputSuffix();

        if (outPutType.equalsIgnoreCase("TXT")) {
            System.out.println("exporting to txt format");
            exportToTxt();
        } else if (outPutType.equalsIgnoreCase("XLS")) {
            System.out.println("exporting to xls format");
            exportToXLS();
        } else if (outPutType.equalsIgnoreCase("CSV")) {
            System.out.println("exporting to csv format");
            exportToCSV(",");
        } else if (outPutType.equalsIgnoreCase("RTF")
                || outPutType.equalsIgnoreCase("DOC")) {
            System.out.println("exporting to rtf format");
            exportToRTF();
        } else if (outPutType.equalsIgnoreCase("PDF")) {
            System.out.println("exporting to pdf format");
            exportToPDF();
        } else if (outPutType.equalsIgnoreCase("HTML")) {
            System.out.println("exporting to html format");
            exportToHTML();
        }

        if (null != getJrVirtualizer()) {
            getJrVirtualizer().cleanup();
        }
    }

    private void exportToTxt() {
        //log.debug("exporting to TXT");
        // textExporter = new JRTextExporter();
        // setExportParameter(JRTextExporterParameter.CHARACTER_WIDTH, new
        // Integer(13));
        // setExportParameter(JRTextExporterParameter.CHARACTER_HEIGHT, new
        // Integer(20));

        textExporter = new JRTextExporter();
        if (getJasperReport().hasProperties()) {
            if (null != getJasperReport().getProperty("export.txt.page_rows")) {
                setExportParameter(JRTextExporterParameter.CHARACTER_WIDTH,
                        getJasperReport().getProperty("export.txt.character_width"));
            }
            if (null != getJasperReport().getProperty("export.txt.page_columns")) {
                setExportParameter(JRTxtExporterParameter.PAGE_COLUMNS,
                        getJasperReport().getProperty("export.txt.page_columns"));
            }
            if (null != getJasperReport().getProperty(
                    "export.txt.add_form_feed")) {
                setExportParameter(JRTxtExporterParameter.ADD_FORM_FEED,
                        new Boolean(getJasperReport().getProperty(
                        "export.txt.add_form_feed")));
            }
        }
        textExporter.setParameters(getExportParameters());
        try {
            start = System.currentTimeMillis();
            textExporter.exportReport();
            //log.info("export running time (msec): "
            //        + (System.currentTimeMillis() - start));
        } catch (JRException jre) {

            jre.printStackTrace();
        }
    }

    private void exportToXLS() {
        log.debug("exporting to XLS");
        xlsExporter = new JRXlsExporter();
        setExportParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
                Boolean.TRUE);
        setExportParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
                Boolean.FALSE);
        xlsExporter.setParameters(getExportParameters());
        try {
            start = System.currentTimeMillis();
            xlsExporter.exportReport();
            log.info("export running time (msec): "
                    + (System.currentTimeMillis() - start));
        } catch (JRException jre) {
            jre.printStackTrace();
            log.error(jre.getMessage());
        }
    }

    private void exportToCSV(String delim) {
        log.debug("exporting to CSV. Delimiter char '" + delim + "'");
        csvExporter = new JRCsvExporter();
        setExportParameter(JRCsvExporterParameter.FIELD_DELIMITER, delim);
        setExportParameter(JRCsvExporterParameter.RECORD_DELIMITER, "\r\n");
        csvExporter.setParameters(getExportParameters());
        try {
            start = System.currentTimeMillis();
            csvExporter.exportReport();
            log.info("export running time (msec): "
                    + (System.currentTimeMillis() - start));
        } catch (JRException jre) {
            jre.printStackTrace();
            log.error(jre.getCause());
        }
    }

    private void exportToRTF() {
        log.debug("exporting to RTF");
        rtfExporter = new JRRtfExporter();
        rtfExporter.setParameters(getExportParameters());
        try {
            start = System.currentTimeMillis();
            rtfExporter.exportReport();
            log.info("export running time (msec): "
                    + (System.currentTimeMillis() - start));
        } catch (JRException jre) {
            jre.printStackTrace();
            log.error(jre.getCause());
        }
    }

    private void exportToPDF() {
        log.debug("exporting to PDF");
        pdfExporter = new JRPdfExporter();
        pdfExporter.setParameters(getExportParameters());

        try {
            start = System.currentTimeMillis();
            pdfExporter.exportReport();
            log.info("export running time (msec): "
                    + (System.currentTimeMillis() - start));
        } catch (JRException jre) {
            jre.printStackTrace();
            log.error(jre.getCause());
        }
    }

    private void exportToHTML() {
        log.debug("exporting to HTML");
        htmlExporter = new JRHtmlExporter();
        setExportParameter(JRHtmlExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
        setExportParameter(JRHtmlExporterParameter.IMAGES_URI,"servlets/image?image=");
        setExportParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.TRUE);
        setExportParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR, Boolean.TRUE);
        setExportParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        htmlExporter.setParameters(getExportParameters());
        try {
            start = System.currentTimeMillis();
            htmlExporter.exportReport();
            log.info("export running time (msec): "
                    + (System.currentTimeMillis() - start));
        } catch (JRException jre) {
            jre.printStackTrace();
        }
    }
}
