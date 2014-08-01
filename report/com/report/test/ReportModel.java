package com.report.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.jett.jdbc.ResultSetRow;
import net.sf.jett.transform.ExcelTransformer;

import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.lowagie.text.DocumentException;
import com.report.test.ReportFactory.Report;

public class ReportModel {

    protected transient String name;
    protected transient Map<String, Object> extras;
    protected transient boolean isAutofit = true;
    protected transient short paperSize = HSSFPrintSetup.A4_PAPERSIZE;
    protected transient boolean isLandscape = true;
    protected transient double pdfScale = 1.0;

    protected transient double iScale = 0.4;
    protected transient int iY = 40;
    protected transient int iX = 40;
    protected transient double left_margin = -1;
    private List<String> fixed_list;

    public static class Pair<K, V> {

        public K first;
        public V second;

        public Pair(K k, V v) {
            first = k;
            second = v;
        }
    }

    public static <A, B> Map<A, B> asMap(Pair<A, B>... e) {
        HashMap<A, B> map = new HashMap<A, B>();
        for (Pair<A, B> i : e) {
            map.put(i.first, i.second);
        }
        return map;
    }

    public static <K, V> Pair<K, V> p(K i, V i2) {
        return new Pair<K, V>(i, i2);
    }

    public ReportModel(String template, String... fixed_list) {
        this.name = template;
        this.fixed_list = Arrays.asList(fixed_list);
    }

    public ReportModel(String template, Boolean isLandscape, String... fixed_list) {
        this.name = template;
        if (isLandscape != null) {
            this.isLandscape = isLandscape;
        }
        this.fixed_list = Arrays.asList(fixed_list);
    }

    public ReportModel(String template, Boolean isLandscape, Double pdfScale, Integer dX, Integer dY, Double left_margin, String... fixed_list) {
        this.name = template;
        if (pdfScale != null) {
            this.pdfScale = pdfScale;
        }
        if (dX != null) {
            this.iY = dY;
        }
        if (dY != null) {
            this.iX = dX;
        }
        if (left_margin != null) {
            this.left_margin = left_margin;
        }
        if (isLandscape != null) {
            this.isLandscape = isLandscape;
        }
        this.fixed_list = Arrays.asList(fixed_list);
    }

    public List<String> getFixedList() {
        return fixed_list;
    }

    public HSSFWorkbook getTemplate(Map<String, Object> beans) throws InvalidFormatException, IOException {
        ExcelTransformer transformer = new ExcelTransformer();
        if (getFixedList() != null) {
            for (String fixed : getFixedList()) {
                transformer.addFixedSizeCollectionName(fixed);
            }
        }
        HSSFWorkbook book = (HSSFWorkbook) transformer.transform(getClass().getResourceAsStream("/template/" + name + ".xls"), beans);
        return book;
    }

    public boolean isAutofit() {
        return isAutofit;
    }

    public short getPaperSize() {
        return paperSize;
    }

    public boolean isLandscape() {
        return isLandscape;
    }

    @SuppressWarnings("rawtypes")
    public byte[] getReportXLS(List data, Map<String, Object> map) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        HSSFWorkbook book = getTemplate(getBeans(data, map));
        book.write(out);
        out.flush();
        out.close();

        return out.toByteArray();
    }

    @SuppressWarnings("rawtypes")
    public byte[] getReportCSV(List data, Map<String, Object> map) throws Exception {
        HSSFWorkbook book = getTemplate(getBeans(data, map));
        return excelToCSV(book);
    }

    public static String getDate(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

    @SuppressWarnings("rawtypes")
    public byte[] getReportHTML(List data, Map<String, Object> map) {
        try {
            HSSFWorkbook book = getTemplate(getBeans(data, map));
            return getHTML(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    public byte[] getReportPDF(List data, Map<String, Object> map) throws Exception {
        return getReportPDF(getBeans(data, map));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    protected Map<String, Object> getBeans(List data, Map<String, Object> map) {
        if (map != null) {
            if (data != null) {
                map.put("data", data);
            }
            return map;
        } else {
            return asMap(p("data", (Object) data));
        }
    }

    public byte[] getReportPDF(Map<String, Object> beans) throws Exception {
        HSSFWorkbook book = getTemplate(beans);
        ByteArrayInputStream bais = new ByteArrayInputStream(getHTML(book));
        return convertHTMLtoPDF(bais, isAutofit());
    }

    private double getPdfScale() {
        return pdfScale;
    }

    public static byte[] convertHTMLtoPDF(InputStream bais, boolean isFitWidth) throws ParserConfigurationException, SAXException, IOException, DocumentException {
        ITextRenderer renderer = new ITextRenderer();
//		renderer.getFontResolver().addFont("calibri.ttf", true);
        renderer.setScaleToFit(isFitWidth);
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource(bais);
        Document doc = builder.parse(is);

        renderer.setDocument(doc, null);

        renderer.layout();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        renderer.createPDF(out);
        out.flush();
        out.close();

        return out.toByteArray();
    }

    public byte[] getHTML(HSSFWorkbook book) throws IOException {
        double width = 21.0;
        double height = 29.7;
        if (isLandscape()) {
            width += height;
            height = width - height;
            width = width - height;
        }
        byte[] html = convert(book, width, height).getBytes();
        ByteArrayInputStream in = new ByteArrayInputStream(html);

        // Clean up the HTML to be well formed
        HtmlCleaner cleaner = new HtmlCleaner();
        CleanerProperties props = cleaner.getProperties();
        TagNode node = cleaner.clean(in, "UTF-8");

//        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // Instead of writing to System.out we now write to the ByteArray buffer
//        return 	new PrettyXmlSerializer(props).getAsString(node, "UTF-8").getBytes();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // Instead of writing to System.out we now write to the ByteArray buffer
        new PrettyXmlSerializer(props).writeToStream(node, out);

        return out.toByteArray();
    }

    public void setImageProperties(double scale, int dx, int dy) {
        this.iScale = scale;
        this.iX = dx;
        this.iY = dy;
    }

    public String convert(HSSFWorkbook book, double width, double height) {
        try {
            double scale = 1 / getPdfScale();
            ExcelToHtmlConverter excelToHtmlConverter = new ExcelToHtmlConverter(
                    DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .newDocument());
//			excelToHtmlConverter.setUseDivsToSpan(true);
            excelToHtmlConverter.setScale(scale);
            excelToHtmlConverter.setImageProperties(iScale, iX, iY);
            excelToHtmlConverter.setOutputColumnHeaders(false);
            excelToHtmlConverter.setOutputRowNumbers(false);
            excelToHtmlConverter.processWorkbook(book);
            System.out.println("Size:" + width + ":" + height);
            return "<style type=\"text/css\">"
                    + "div.header {\n"
                    + "display: block; text-align: center;\n"
                    + "position: running(header);}\n"
                    + "div.footer {\n"
                    + "display: block; text-align: center;\n"
                    + "position: running(footer);}\n"
                    + "<!--@page { size:" + width * scale + "cm " + height * scale + "cm;" + (left_margin > 0 ? "margin-left:" + left_margin + "cm;" : "") + "}\n"
                    + "@page { @top-center { content: element(header) }}\n "
                    + "@page { @bottom-center { content: element(footer) }}-->\n"
                    + "</style>"
                    + excelToHtmlConverter.getHTML();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * *
     * Date format used to convert excel cell date value
     */
    private static final String OUTPUT_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * Comma separated characters
     */
    private static final String CVS_SEPERATOR_CHAR = ",";
    /**
     * New line character for CSV file
     */
    private static final String NEW_LINE_CHARACTER = "\r\n";

    /**
     * Convert the Excel file data into CSV file
     *
     * @param excelFileName
     * @param csvFileName
     * @throws Exception
     */
    public static byte[] excelToCSV(HSSFWorkbook myWorkBook) throws Exception {
        HSSFSheet mySheet = myWorkBook.getSheetAt(0);
        Iterator<Row> rowIter = mySheet.rowIterator();
        String csvData = "";
        while (rowIter.hasNext()) {
            HSSFRow myRow = (HSSFRow) rowIter.next();
            for (int i = 0; i < myRow.getLastCellNum(); i++) {
                csvData += getCellData(myRow.getCell(i));
            }
            csvData += NEW_LINE_CHARACTER;
        }

        return csvData.getBytes();
    }

    /**
     * Get cell value based on the excel column data type
     *
     * @param myCell
     * @return
     */
    private static String getCellData(HSSFCell myCell) throws Exception {
        String cellData = "";
        if (myCell == null) {
            cellData += CVS_SEPERATOR_CHAR;;
        } else {
            switch (myCell.getCellType()) {
                case HSSFCell.CELL_TYPE_STRING:
                case HSSFCell.CELL_TYPE_BOOLEAN:
                    cellData += myCell.getRichStringCellValue() + CVS_SEPERATOR_CHAR;
                    break;
                case HSSFCell.CELL_TYPE_NUMERIC:
                    cellData += getNumericValue(myCell);
                    break;
                case HSSFCell.CELL_TYPE_FORMULA:
                    cellData += getFormulaValue(myCell);
                default:
                    cellData += CVS_SEPERATOR_CHAR;
                    ;
            }
        }
        return cellData;
    }

    /**
     * Get the formula value from a cell
     *
     * @param myCell
     * @return
     * @throws Exception
     */
    private static String getFormulaValue(HSSFCell myCell) throws Exception {
        String cellData = "";
        if (myCell.getCachedFormulaResultType() == HSSFCell.CELL_TYPE_STRING || myCell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
            cellData += myCell.getRichStringCellValue() + CVS_SEPERATOR_CHAR;
        } else if (myCell.getCachedFormulaResultType() == HSSFCell.CELL_TYPE_NUMERIC) {
            cellData += getNumericValue(myCell) + CVS_SEPERATOR_CHAR;
        }
        return cellData;
    }

    /**
     * Get the date or number value from a cell
     *
     * @param myCell
     * @return
     * @throws Exception
     */
    private static String getNumericValue(HSSFCell myCell) throws Exception {
        String cellData = "";
        if (HSSFDateUtil.isCellDateFormatted(myCell)) {
            cellData += new SimpleDateFormat(OUTPUT_DATE_FORMAT).format(myCell.getDateCellValue()) + CVS_SEPERATOR_CHAR;
        } else {
            cellData += new BigDecimal(myCell.getNumericCellValue()).toString() + CVS_SEPERATOR_CHAR;
        }
        return cellData;
    }
}
