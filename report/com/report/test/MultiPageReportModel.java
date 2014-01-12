package com.report.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

@SuppressWarnings("deprecation")
public class MultiPageReportModel extends ReportModel{
	private transient int sizePerPage;

	public MultiPageReportModel(String template, int sizePerPage, String... fixed_list) {
		super(template, fixed_list);
		this.sizePerPage = sizePerPage; 
	}
	
	public MultiPageReportModel(String template, int sizePerPage, Boolean isLandscape, Double pdfScale, Integer dX, Integer dY, Double left_margin, String... fixed_list) {
		super(template, isLandscape, pdfScale, dX, dY, left_margin, fixed_list);
		this.sizePerPage = sizePerPage;
	}
	
	@SuppressWarnings("rawtypes")
	protected List<Map<String, Object>> split(List data, Map<String, Object> map) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		int page = 1;
		for(int x = 0; x < data.size();x += sizePerPage){
			Map<String, Object> b = map!=null?map:new HashMap<String, Object>();
			b.put("data", data.subList(x, Math.min(data.size(), x+sizePerPage)));
			b.put("page", page);
			page++;
			result.add(b);
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
    public byte[] getReportPDF(List data, Map<String, Object> map) throws Exception{
		List<ByteArrayInputStream> pdfs = new ArrayList<ByteArrayInputStream>();
		for(Map<String, Object> page:split(data, map)){
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			pdfs.add(new ByteArrayInputStream(getReportPDF(page)));
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		concatPDFs(pdfs, out, true);
		try {
			return out.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
    @SuppressWarnings("rawtypes")
	public byte[] getReportXLS(List data, Map<String, Object> map){
		try {
			List<HSSFSheet> xlss = new ArrayList<HSSFSheet>();
			for(Map<String, Object> page:split(data, map)){
				xlss.add(getTemplate(page).getSheetAt(0));
			}
			return concatXLSs(xlss);
		}catch(Exception e){}
		return null;
	}

	public static byte[] concatXLSs(List<HSSFSheet> sheets) {
		 try {
	       	ByteArrayOutputStream out = new ByteArrayOutputStream();
			HSSFWorkbook book = new HSSFWorkbook();
			for(HSSFSheet sheet:sheets){
				book.createSheet();
				copySheets(book.getSheetAt(book.getNumberOfSheets()-1), sheet);
			}
		    book.write(out);
		    out.flush();
			out.close();
		        
		    return out.toByteArray();
	    }catch(Exception e){e.printStackTrace();}
		return null;
	}
	
	 public static void copySheets(HSSFSheet newSheet, HSSFSheet sheet){  
        copySheets(newSheet, sheet, true);  
    }  
	 
    public static void copySheets(HSSFSheet newSheet, HSSFSheet sheet, boolean copyStyle){  
        int maxColumnNum = 0;  
        Map<Integer, HSSFCellStyle> styleMap = (copyStyle)  
                ? new HashMap<Integer, HSSFCellStyle>() : null;  
  
        for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {  
            HSSFRow srcRow = sheet.getRow(i);  
            HSSFRow destRow = newSheet.createRow(i);  
            if (srcRow != null) {  
                copyRow(sheet, newSheet, srcRow, destRow, styleMap);  
                if (srcRow.getLastCellNum() > maxColumnNum) {  
                    maxColumnNum = srcRow.getLastCellNum();  
                }  
            }  
        }  
        for (int i = 0; i <= maxColumnNum; i++) {  
            newSheet.setColumnWidth(i, sheet.getColumnWidth(i));  
        }  
    }  
    
    public static void concatPDFs(List<? extends InputStream> streamOfPDFFiles, OutputStream outputStream, boolean paginate) {
	    com.lowagie.text.Document document = new com.lowagie.text.Document();
	    try {
	      List<? extends InputStream> pdfs = streamOfPDFFiles;
	      List<PdfReader> readers = new ArrayList<PdfReader>();
	      int totalPages = 0;
	      Iterator<? extends InputStream> iteratorPDFs = pdfs.iterator();

	      // Create Readers for the pdfs.
	      while (iteratorPDFs.hasNext()) {
	        InputStream pdf = iteratorPDFs.next();
	        PdfReader pdfReader = new PdfReader(pdf);
	        readers.add(pdfReader);
//	        totalPages += pdfReader.getNumberOfPages();
	        ++totalPages;
	      }
	      // Create a writer for the outputstream
	      PdfWriter writer = PdfWriter.getInstance(document, outputStream);

	      document.open();
	      BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
	      PdfContentByte cb = writer.getDirectContent(); // Holds the PDF
	      // data

	      PdfImportedPage page;
	      int currentPageNumber = 0;
	      int pageOfCurrentReaderPDF = 0;
	      Iterator<PdfReader> iteratorPDFReader = readers.iterator();

	      // Loop through the PDF files and add to the output.
	      while (iteratorPDFReader.hasNext()) {
	        PdfReader pdfReader = iteratorPDFReader.next();

	        // Create a new page in the target for each source page.
//	        while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
	          document.newPage();
	          pageOfCurrentReaderPDF++;
	          currentPageNumber++;
	          page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);
	          cb.addTemplate(page, 0, 0);

	          // Code for pagination.
	          if (paginate) {
	            cb.beginText();
	            cb.setFontAndSize(bf, 9);
	            cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "" + currentPageNumber + " of " + totalPages, 520, 5, 0);
	            cb.endText();
	          }
//	        }
	        pageOfCurrentReaderPDF = 0;
	      }
	      outputStream.flush();
	      document.close();
	      outputStream.close();
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      if (document.isOpen())
	        document.close();
	      try {
	        if (outputStream != null)
	          outputStream.close();
	      } catch (IOException ioe) {
	        ioe.printStackTrace();
	      }
	    }
	}
  
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static void copyRow(HSSFSheet srcSheet, HSSFSheet destSheet, HSSFRow srcRow, HSSFRow destRow, Map<Integer, HSSFCellStyle> styleMap) {  
        Set mergedRegions = new TreeSet();  
        destRow.setHeight(srcRow.getHeight());  
        for (int j = srcRow.getFirstCellNum(); j <= srcRow.getLastCellNum(); j++) {  
            HSSFCell oldCell = srcRow.getCell(j);  
            HSSFCell newCell = destRow.getCell(j);  
            if (oldCell != null) {  
                if (newCell == null) {  
                    newCell = destRow.createCell(j);  
                }  
                copyCell(oldCell, newCell, styleMap);  
                Region mergedRegion = getMergedRegion(srcSheet, srcRow.getRowNum(), oldCell.getCellNum());  
                if (mergedRegion != null) {  
//	                    Region newMergedRegion = new Region( destRow.getRowNum(), mergedRegion.getColumnFrom(),  
//	                            destRow.getRowNum() + mergedRegion.getRowTo() - mergedRegion.getRowFrom(), mergedRegion.getColumnTo() );  
                    Region newMergedRegion = new Region(mergedRegion.getRowFrom(), mergedRegion.getColumnFrom(),  
                            mergedRegion.getRowTo(), mergedRegion.getColumnTo());  
                    if (isNewMergedRegion(newMergedRegion, mergedRegions)) {  
                        mergedRegions.add(newMergedRegion);  
                        destSheet.addMergedRegion(newMergedRegion);  
                    }  
                }  
            }  
        }  
          
    }  
    public static void copyCell(HSSFCell oldCell, HSSFCell newCell, Map<Integer, HSSFCellStyle> styleMap) {  
        if(styleMap != null) {  
            if(oldCell.getSheet().getWorkbook() == newCell.getSheet().getWorkbook()){  
                newCell.setCellStyle(oldCell.getCellStyle());  
            } else{  
                int stHashCode = oldCell.getCellStyle().hashCode();  
                HSSFCellStyle newCellStyle = styleMap.get(stHashCode);  
                if(newCellStyle == null){  
                    newCellStyle = newCell.getSheet().getWorkbook().createCellStyle();  
                    newCellStyle.cloneStyleFrom(oldCell.getCellStyle());  
                    styleMap.put(stHashCode, newCellStyle);  
                }  
                newCell.setCellStyle(newCellStyle);  
            }  
        }  
        switch(oldCell.getCellType()) {  
            case HSSFCell.CELL_TYPE_STRING:  
                newCell.setCellValue(oldCell.getStringCellValue());  
                break;  
            case HSSFCell.CELL_TYPE_NUMERIC:  
                newCell.setCellValue(oldCell.getNumericCellValue());  
                break;  
            case HSSFCell.CELL_TYPE_BLANK:  
                newCell.setCellType(HSSFCell.CELL_TYPE_BLANK);  
                break;  
            case HSSFCell.CELL_TYPE_BOOLEAN:  
                newCell.setCellValue(oldCell.getBooleanCellValue());  
                break;  
            case HSSFCell.CELL_TYPE_ERROR:  
                newCell.setCellErrorValue(oldCell.getErrorCellValue());  
                break;  
            case HSSFCell.CELL_TYPE_FORMULA:  
                newCell.setCellFormula(oldCell.getCellFormula());  
                break;  
            default:  
                break;  
        }  
          
    }  
    public static Region getMergedRegion(HSSFSheet sheet, int rowNum, short cellNum) {  
        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {  
            Region merged = sheet.getMergedRegionAt(i);  
            if (merged.contains(rowNum, cellNum)) {  
                return merged;  
            }  
        }  
        return null;  
    }  
  
    @SuppressWarnings("rawtypes")
	private static boolean isNewMergedRegion(Region region, Collection mergedRegions) {  
        return !mergedRegions.contains(region);  
    }
}
