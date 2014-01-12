
package com.app.wms.web.excel;

import java.sql.Statement;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelExport {
    private Vector errorLog = new Vector ();
    private HashMap<Integer, String> headerTitleNames = new HashMap();
    private HashMap<Integer, String> headerTitleValues = new HashMap();
    private HashMap<Integer, String> headerParamNames = new HashMap();
    private HashMap<Integer, String> headerParamValues = new HashMap();
    private HashMap<Integer, String> headerParamFldNames = new HashMap();
    private HashMap<Integer, Integer> headerParamDataTypes = new HashMap();
    private HashMap<Integer, String> headerParamDataFormat = new HashMap();
    private HashMap<Integer, Integer> headerParamAlignment = new HashMap();
    private HashMap<Integer, String> fieldMapColNoToColHeader = new HashMap();
    private HashMap<Integer, String> fieldMapColNoToFldName = new HashMap();
    private HashMap<Integer, Integer> fieldMapColNoToDataType = new HashMap();
    private HashMap<Integer, String> fieldMapColNoToDataFormat = new HashMap();
    private HashMap<Integer, Integer> fieldMapColNoToAlignment = new HashMap();

    private HashMap<Integer, String> headerNames = new HashMap();
    private HashMap<Integer, String> headerValues = new HashMap();

    private String userName = "";
    private String password = "";
    private String excelFileName = "";
    private String excelFileGenerated = "";
    private String headerTitle = "";
    private boolean hasNoCnt = false;

    protected ServletContext context;

    private ResultSet res = null;
    private Connection conn = null;
    private Statement stmt = null;
    private PreparedStatement preparedStatement = null;
    private String sqlQuery = "";
    private boolean hasError = false;

    private String paramSubSystem = "";
    private String paramSqlStatement = "";
    private String paramTableName = "";
    private long paramUserId = 0;

    private static final int DT_STRING = 0;
    private static final int DT_NUMERIC = 1;
    private static final int DT_DATE = 2;

    private static final int AL_LEFT = 3;
    private static final int AL_CENTER = 4;
    private static final int AL_RIGHT = 5;

    private int noOfRecords = 0;
    private int RETURN_VALUE = 0;
    private int processNo = -1;
    private int headerTitleRowStart = 0;
    private int headerParamRowStart = 1;
    private int fieldMapColStart = 0;
    private int ColStart = 0;

    HSSFSheet sheet = null;
    HSSFFont boldFont = null;
    HSSFCellStyle boldStyle = null;
    HSSFCellStyle centerBoldStyle = null;
    HSSFCellStyle centerStyle = null;
    HSSFCellStyle dateStyle = null;
    HSSFCellStyle numStyle = null;
    HSSFCellStyle leftStyle = null;
    HSSFCellStyle rightStyle = null;
    HSSFDataFormat df = null;
    HSSFWorkbook workBook = new HSSFWorkbook();

    public ExcelExport(String subSystem, String sqlStatement, long userId, BasicDataSource possDs, ServletContext context)  throws Exception{
        try {         
            System.out.println("construct new ExcelExport");
            conn = possDs.getConnection();
            this.context = context;

        } catch (SQLException ex) {
            Logger.getLogger(ExcelExport.class.getName()).log(Level.SEVERE, null, ex);
        }

        paramSubSystem = subSystem;
        paramUserId = userId;
        paramSqlStatement = sqlStatement;

    }

    public ExcelExport(String subSystem, String userName, ResultSet resultSet)  throws Exception{

        res = resultSet;
        paramSubSystem = subSystem;
        //paramUserId = userId;

    }

    public void setExcelFileName(String fileName){
        excelFileName = fileName;
    }

    public String getExcelFileGenerated() {
        return excelFileGenerated;
    }

    public void setExcelFileGenerated(String excelFileGenerated) {
        this.excelFileGenerated = excelFileGenerated;
    }

    public void setHeaderTitle(String hdrTitle, String hdrValue){
        headerTitle = hdrTitle;

        ColStart = fieldMapColStart;

        headerTitleNames.put(headerTitleRowStart, hdrTitle);
        headerTitleValues.put(headerTitleRowStart, hdrValue);

        headerTitleRowStart++;
        headerParamRowStart++;
    }

    public void setHeaderTitle(String hdrTitle){
        setHeaderTitle(hdrTitle, "");
    }

    public void setRowNumber(boolean rowNumber){
        hasNoCnt = rowNumber;

        if(hasNoCnt){
            ++fieldMapColStart;
        }
    }

    public void setRowNumber(){
        setRowNumber(true);
    }

    //Valid dataFormat
    //For date yyyy-mm-dd, dd-mm-yyyy, dd-mm-yyyy hh:mm
    //For numeric #,##0, #,##0.00, 0.00
    public void addHeaderParam(String parameterName, String fieldName, int dataType, String dataFormat, int alignment){
        headerParamNames.put(headerParamRowStart, parameterName);
        headerParamFldNames.put(headerParamRowStart, fieldName);
        headerParamDataTypes.put(headerParamRowStart, dataType);
        headerParamDataFormat.put(headerParamRowStart, dataFormat);
        headerParamAlignment.put(headerParamRowStart, alignment);

        headerParamRowStart++;
    }

    public void addHeaderParam(String parameterName, String fieldName, int dataType){
        addHeaderParam(parameterName, fieldName, dataType, "", AL_LEFT);
    }

    public void addFieldMap(String columnHeader, String fieldName, int dataType, String dataFormat, int alignment){
        fieldMapColNoToColHeader.put(ColStart, columnHeader);
        fieldMapColNoToFldName.put(ColStart, fieldName);
        fieldMapColNoToDataType.put(ColStart, dataType);
        fieldMapColNoToDataFormat.put(ColStart, dataFormat);
        fieldMapColNoToAlignment.put(ColStart, alignment);

        ColStart++;
    }

    public void addFieldMap(String columnHeader, String fieldName, int dataType){
        addFieldMap(columnHeader, fieldName, dataType, "", AL_LEFT);
    }

    public void execute() throws SQLException,IOException{
        
        //stmt = conn.createStatement();

        getHeaderParamValues();

        writeToExcel();

        //stmt.close();
        //conn.close();
        
    }

    private void getHeaderParamValues() throws SQLException{
        if(headerParamFldNames.size() == 0) return;

        String fldNames = "";

        short r = (short) headerTitleRowStart;
        int x = 1 + r;

        for(int i=0;i<headerParamFldNames.size();++i){
            int a = i+x;
//            fldNames += headerParamFldNames.get(i+2)+",";
            fldNames += headerParamFldNames.get(i+x)+",";
        }

        fldNames = fldNames.substring(0, fldNames.length()-1);

        res = stmt.executeQuery("SELECT "+fldNames+" from ("+paramSqlStatement+") where rownum <=1");

        try{
            if(!res.next()){
                hasError = true;
                errorLog.addElement("Query result is empty");

                return;
            }
        }
        catch(SQLException e){

        }

        for(int i=0;i<headerParamFldNames.size();++i){
//            headerParamValues.put(i+2, res.getString(headerParamFldNames.get(i+2)));
            headerParamValues.put(i+x, res.getString(headerParamFldNames.get(i+x)));
        }
    }

    private void writeExcel(HttpServletResponse response) throws FileNotFoundException, IOException, SQLException{
        
        init();

        writeTitle();
        writeHeaderParam();
        writeColHeaders();
        writeData();
        resizeColumns();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] excelData = byteArrayOutputStream.toByteArray();
        //FacesContext faces = FacesContext.getCurrentInstance();
        response.setContentType("application/vnd.ms-excel");
        response.setContentLength(excelData.length);
        response.setHeader("Content-disposition",  "attachment; filename=" + excelFileName);
        ServletOutputStream out;
        out = response.getOutputStream();
        out.write(excelData);
        //faces.responseComplete();

        workBook.write(out);
        
    }

    @SuppressWarnings("static-access")
    private void init(){
        workBook = new HSSFWorkbook();
        sheet = workBook.createSheet();

        boldFont = workBook.createFont();
        boldFont.setBoldweight(boldFont.BOLDWEIGHT_BOLD);

        centerBoldStyle = workBook.createCellStyle();
        centerBoldStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        centerBoldStyle.setFont(boldFont);

        centerStyle = workBook.createCellStyle();
        centerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        rightStyle = workBook.createCellStyle();
        rightStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);

        leftStyle = workBook.createCellStyle();
        leftStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);

        boldStyle = workBook.createCellStyle();
        boldStyle.setFont(boldFont);

        df = workBook.createDataFormat();

    }

    private void writeTitle(){
//        HSSFRow headerRow = sheet.createRow(0);
//        HSSFCell headerCell = headerRow.createCell(0);
//
//        headerCell.setCellValue(headerTitle);
//        headerCell.setCellStyle(boldStyle);

        for(int i=0;i<headerTitleNames.size();++i){
            int c = 0;
            HSSFRow paramRow = sheet.createRow(i);
            HSSFCell paramCell = paramRow.createCell((short) c);
            paramCell.setCellValue(headerTitleNames.get(i));
            paramCell.setCellStyle(boldStyle);

            HSSFCell paramValueCell = paramRow.createCell((short) (c+1));
            paramValueCell.setCellValue(headerTitleValues.get(i));
        }
    }

    private void writeHeaderParam(){
        for(int i=0;i<headerParamNames.size();++i){
            int c = 0;
            short r = (short) headerTitleRowStart;
            int x = 1 + r;

            HSSFRow paramRow = sheet.createRow(i+r);
            HSSFCell paramCell = paramRow.createCell((short) c);
//            paramCell.setCellValue(headerParamNames.get(i+2));
            paramCell.setCellValue(headerParamNames.get(i+x));
            paramCell.setCellStyle(boldStyle);

            HSSFCell paramValueCell = paramRow.createCell((short) (c+1));
//            paramValueCell.setCellValue(headerParamValues.get(i+2));
            paramValueCell.setCellValue(headerParamValues.get(i+x));
        }
    }

    private void writeColHeaders(){
        HSSFRow colHeaderRow = sheet.createRow((short) headerParamRowStart);

        if(hasNoCnt){
            HSSFCell colHeaderCell = colHeaderRow.createCell((short) 0);
            colHeaderCell.setCellValue("NO");
            colHeaderCell.setCellStyle(centerBoldStyle);
        }

        for(int i=0;i<fieldMapColNoToColHeader.size();++i){
            HSSFCell colHeaderCell = colHeaderRow.createCell((short) (i+fieldMapColStart));
            colHeaderCell.setCellValue(fieldMapColNoToColHeader.get(i+fieldMapColStart));
            colHeaderCell.setCellStyle(centerBoldStyle);
        }
    }

    private void writeData() throws SQLException{
        HSSFRow colDataRow = null;
        res = stmt.executeQuery(paramSqlStatement);
        stmt.close();
        conn.close();
        
        noOfRecords = res.getFetchSize();

        int rowCnt = 1;

        while(res.next()){
            colDataRow = sheet.createRow((short) headerParamRowStart+1);

            if(hasNoCnt){
                HSSFCell dataCell = colDataRow.createCell((short) 0);
                dataCell.setCellValue(rowCnt);
                rowCnt++;
            }

            for(int i=0;i<fieldMapColNoToColHeader.size();++i){
                HSSFCell dataCell = colDataRow.createCell((short) (i+fieldMapColStart));

                if(fieldMapColNoToAlignment.get(i+fieldMapColStart) == AL_LEFT){
                    dataCell.setCellStyle(leftStyle);
                }
                else if(fieldMapColNoToAlignment.get(i+fieldMapColStart) == AL_CENTER){
                    dataCell.setCellStyle(centerStyle);
                }
                else if(fieldMapColNoToAlignment.get(i+fieldMapColStart) == AL_RIGHT){
                    dataCell.setCellStyle(rightStyle);
                }

                if(fieldMapColNoToDataType.get(i+fieldMapColStart) == DT_DATE){
                    dateStyle = workBook.createCellStyle();
                    dateStyle.setDataFormat(df.getFormat(fieldMapColNoToDataFormat.get(i+fieldMapColStart)));

                    dataCell.setCellStyle(dateStyle);
                    if(res.getTimestamp(fieldMapColNoToFldName.get(i+fieldMapColStart)) == null){
                        dataCell.setCellValue("");
                    }
                    else{
                        dataCell.setCellValue(res.getTimestamp(fieldMapColNoToFldName.get(i+fieldMapColStart)));
                    }
                }
                else if(fieldMapColNoToDataType.get(i+fieldMapColStart) == DT_NUMERIC){
                    numStyle = workBook.createCellStyle();
                    numStyle.setDataFormat(df.getFormat(fieldMapColNoToDataFormat.get(i+fieldMapColStart)));

                    dataCell.setCellStyle(numStyle);
                    if(res.getInt(fieldMapColNoToFldName.get(i+fieldMapColStart)) == 0){
                        dataCell.setCellValue("");
                    }
                    else{
                        dataCell.setCellValue(res.getInt(fieldMapColNoToFldName.get(i+fieldMapColStart)));
                }
                }
                else if(fieldMapColNoToDataType.get(i+fieldMapColStart) == DT_STRING){
                    dataCell.setCellValue(res.getString(fieldMapColNoToFldName.get(i+fieldMapColStart)));
                }
            }

            headerParamRowStart++;
        }


        if ( null!=res ) {
            res.close();
            res = null;
        }

    }

    private void writeDataExcel() throws SQLException{
        HSSFRow colDataRow = null;
                
        noOfRecords = res.getFetchSize();

        int rowCnt = 1;

        while(res.next()){
            colDataRow = sheet.createRow((short) headerParamRowStart+1);

            if(hasNoCnt){
                HSSFCell dataCell = colDataRow.createCell((short) 0);
                dataCell.setCellValue(rowCnt);
                rowCnt++;
            }

            for(int i=0;i<fieldMapColNoToColHeader.size();++i){
                HSSFCell dataCell = colDataRow.createCell((short) (i+fieldMapColStart));

                if(fieldMapColNoToAlignment.get(i+fieldMapColStart) == AL_LEFT){
                    dataCell.setCellStyle(leftStyle);
                }
                else if(fieldMapColNoToAlignment.get(i+fieldMapColStart) == AL_CENTER){
                    dataCell.setCellStyle(centerStyle);
                }
                else if(fieldMapColNoToAlignment.get(i+fieldMapColStart) == AL_RIGHT){
                    dataCell.setCellStyle(rightStyle);
                }

                if(fieldMapColNoToDataType.get(i+fieldMapColStart) == DT_DATE){
                    dateStyle = workBook.createCellStyle();
                    dateStyle.setDataFormat(df.getFormat(fieldMapColNoToDataFormat.get(i+fieldMapColStart)));

                    dataCell.setCellStyle(dateStyle);
                    if(res.getTimestamp(fieldMapColNoToFldName.get(i+fieldMapColStart)) == null){
                        dataCell.setCellValue("");
                    }
                    else{
                        dataCell.setCellValue(res.getTimestamp(fieldMapColNoToFldName.get(i+fieldMapColStart)));
                    }
                }
                else if(fieldMapColNoToDataType.get(i+fieldMapColStart) == DT_NUMERIC){
                    numStyle = workBook.createCellStyle();
                    numStyle.setDataFormat(df.getFormat(fieldMapColNoToDataFormat.get(i+fieldMapColStart)));

                    dataCell.setCellStyle(numStyle);
                    dataCell.setCellValue(res.getInt(fieldMapColNoToFldName.get(i+fieldMapColStart)));
                
                }
                else if(fieldMapColNoToDataType.get(i+fieldMapColStart) == DT_STRING){
                    dataCell.setCellValue(res.getString(fieldMapColNoToFldName.get(i+fieldMapColStart)));
                }
            }

            headerParamRowStart++;
        }       

    }

    private void resizeColumns(){
        int i = 0;

        for(i=0;i<fieldMapColNoToColHeader.size();++i){
            sheet.autoSizeColumn((short)(i));
        }

        if(hasNoCnt){
            sheet.autoSizeColumn((short)(i));
        }
    }

    /** tulis header **/
    private void writeHeader(){
        for(int i=0;i<headerNames.size();++i){
            int c = 0;
            short r = (short) headerTitleRowStart;
            int x = 1 + r;

            HSSFRow paramRow = sheet.createRow(i+r);
            HSSFCell paramCell = paramRow.createCell((short) c);
//            paramCell.setCellValue(headerParamNames.get(i+2));
            paramCell.setCellValue(headerNames.get(i+x));
            paramCell.setCellStyle(boldStyle);

            HSSFCell paramValueCell = paramRow.createCell((short) (c+1));
//            paramValueCell.setCellValue(headerParamValues.get(i+2));
            paramValueCell.setCellValue(headerValues.get(i+x));
        }
    }

    public void addHeader(String parameterName, String value){
        headerNames.put(headerParamRowStart, parameterName);
        headerValues.put(headerParamRowStart, value);

        headerParamRowStart++;
    }

    private String encloseString(String str){
        return "'"+str+"'";
    }

    private void writeToExcel() throws FileNotFoundException, IOException, SQLException{
        System.out.println("start writeExcel:"+excelFileName);
        init();

        writeTitle();
        writeHeader();
        writeHeaderParam();
        writeColHeaders();
        //writeData();
        if (res != null) {
            writeDataExcel();
        }
        resizeColumns();

        String dir = System.getProperty("java.io.tmpdir");
        System.out.println("tmpdir:"+dir);
        File source = new File(dir+"/");
        File file = File.createTempFile(excelFileName, ".xls", source );
        System.out.println("#writeToExcel file="+file.getPath());
        FileOutputStream fop=new FileOutputStream(file);
        workBook.write(fop);
        fop.flush();
        fop.close();
        setExcelFileGenerated(file.getPath());


    }
}
