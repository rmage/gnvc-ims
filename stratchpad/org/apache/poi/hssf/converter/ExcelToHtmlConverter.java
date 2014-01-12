/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */
package org.apache.poi.hssf.converter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hwpf.converter.HtmlDocumentFacade;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.Beta;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * Converts xls files (97-2007) to HTML file.
 * 
 * @author Sergey Vladimirov (vlsergey {at} gmail {dot} com)
 */
@Beta
public class ExcelToHtmlConverter extends AbstractExcelConverter
{

    private static final POILogger logger = POILogFactory
            .getLogger( ExcelToHtmlConverter.class );

    /**
     * Java main() interface to interact with {@link ExcelToHtmlConverter}
     * 
     * <p>
     * Usage: ExcelToHtmlConverter infile outfile
     * </p>
     * Where infile is an input .xls file ( Word 97-2007) which will be rendered
     * as HTML into outfile
     */
    public static void main( String[] args )
    {
        if ( args.length < 2 )
        {
            System.err
                    .println( "Usage: ExcelToHtmlConverter <inputFile.xls> <saveTo.html>" );
            return;
        }

        System.out.println( "Converting " + args[0] );
        System.out.println( "Saving output to " + args[1] );
        try
        {
            Document doc = ExcelToHtmlConverter.process( new File( args[0] ) );

            FileWriter out = new FileWriter( args[1] );
            DOMSource domSource = new DOMSource( doc );
            StreamResult streamResult = new StreamResult( out );

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer serializer = tf.newTransformer();
            // TODO set encoding from a command argument
            serializer.setOutputProperty( OutputKeys.ENCODING, "UTF-8" );
            serializer.setOutputProperty( OutputKeys.INDENT, "no" );
            serializer.setOutputProperty( OutputKeys.METHOD, "html" );
            serializer.transform( domSource, streamResult );
            out.close();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    /**
     * Converts Excel file (97-2007) into HTML file.
     * 
     * @param xlsFile
     *            file to process
     * @return DOM representation of result HTML
     */
    public static Document process( File xlsFile ) throws Exception
    {
        final HSSFWorkbook workbook = ExcelToHtmlUtils.loadXls( xlsFile );
        ExcelToHtmlConverter excelToHtmlConverter = new ExcelToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder()
                        .newDocument() );
        excelToHtmlConverter.processWorkbook( workbook );
        return excelToHtmlConverter.getDocument();
    }

    private String cssClassContainerCell = null;

    private String cssClassContainerDiv = null;

    private String cssClassPrefixCell = "c";

    private String cssClassPrefixDiv = "d";

    private String cssClassPrefixRow = "r";

    private String cssClassPrefixTable = "t";

    private Map<Short, String> excelStyleToClass = new LinkedHashMap<Short, String>();

    private final HtmlDocumentFacade htmlDocumentFacade;

    private boolean useDivsToSpan = false;

	private FormulaEvaluator evaluator;

	private Map<String, HSSFPicture> picMap = new HashMap<String, HSSFPicture>();
	private int picIndex = 0;

	private double scale;

	private double iScale = 0.4;
	private int iY = 40;
	private int iX= 10;
	
	public void setScale(double scale) {
		this.scale = scale;
	}

    public ExcelToHtmlConverter( Document doc )
    {
        htmlDocumentFacade = new HtmlDocumentFacade( doc );
    }

    public ExcelToHtmlConverter( HtmlDocumentFacade htmlDocumentFacade )
    {
        this.htmlDocumentFacade = htmlDocumentFacade;
    }

    protected String buildStyle( HSSFWorkbook workbook, HSSFCellStyle cellStyle )
    {
        StringBuilder style = new StringBuilder();

        style.append( "white-space:pre-wrap;" );
        ExcelToHtmlUtils.appendAlign( style, cellStyle.getAlignment() );
        ExcelToHtmlUtils.appendVAlign( style, cellStyle.getVerticalAlignment());

        if ( cellStyle.getFillPattern() == 0 )
        {
            // no fill
        }
        else if ( cellStyle.getFillPattern() == 1 )
        {
            final HSSFColor foregroundColor = cellStyle
                    .getFillForegroundColorColor();
            if ( foregroundColor != null )
                style.append( "background-color:"
                        + ExcelToHtmlUtils.getColor( foregroundColor ) + ";" );
        }
        else
        {
            final HSSFColor backgroundColor = cellStyle
                    .getFillBackgroundColorColor();
            if ( backgroundColor != null )
                style.append( "background-color:"
                        + ExcelToHtmlUtils.getColor( backgroundColor ) + ";" );
        }
        style.append( "text-indent:"+ cellStyle.getIndention()*10+"px;" );

        buildStyle_border( workbook, style, "top", cellStyle.getBorderTop(),
                cellStyle.getTopBorderColor() );
        buildStyle_border( workbook, style, "right",
                cellStyle.getBorderRight(), cellStyle.getRightBorderColor() );
        buildStyle_border( workbook, style, "bottom",
                cellStyle.getBorderBottom(), cellStyle.getBottomBorderColor() );
        buildStyle_border( workbook, style, "left", cellStyle.getBorderLeft(),
                cellStyle.getLeftBorderColor() );

        HSSFFont font = cellStyle.getFont( workbook );
        buildStyle_font( workbook, style, font );

        return style.toString();
    }

    private void buildStyle_border( HSSFWorkbook workbook, StringBuilder style,
            String type, short xlsBorder, short borderColor )
    {
        if ( xlsBorder == HSSFCellStyle.BORDER_NONE )
            return;

        StringBuilder borderStyle = new StringBuilder();
        borderStyle.append( ExcelToHtmlUtils.getBorderWidth( xlsBorder ) );
        borderStyle.append( ' ' );
        borderStyle.append( ExcelToHtmlUtils.getBorderStyle( xlsBorder ) );

        final HSSFColor color = workbook.getCustomPalette().getColor(
                borderColor );
        if ( color != null )
        {
            borderStyle.append( ' ' );
            borderStyle.append( ExcelToHtmlUtils.getColor( color ) );
        }

        style.append( "border-" + type + ":" + borderStyle + ";" );
    }

    void buildStyle_font( HSSFWorkbook workbook, StringBuilder style,
            HSSFFont font )
    {
        switch ( font.getBoldweight() )
        {
        case HSSFFont.BOLDWEIGHT_BOLD:
            style.append( "font-weight:bold;" );
            break;
        case HSSFFont.BOLDWEIGHT_NORMAL:
            // by default, not not increase HTML size
            // style.append( "font-weight: normal; " );
            break;
        }

        final HSSFColor fontColor = workbook.getCustomPalette().getColor(
                font.getColor() );
        if ( fontColor != null )
            style.append( "color: " + ExcelToHtmlUtils.getColor( fontColor )
                    + "; " );

        if ( font.getFontHeightInPoints() != 0 )
            style.append( "font-size:" + font.getFontHeightInPoints()*0.65*scale + "pt;" );

        if ( font.getItalic() )
        {
            style.append( "font-style:italic;" );
        }
    }

    public String getCssClassPrefixCell()
    {
        return cssClassPrefixCell;
    }

    public String getCssClassPrefixDiv()
    {
        return cssClassPrefixDiv;
    }

    public String getCssClassPrefixRow()
    {
        return cssClassPrefixRow;
    }

    public String getCssClassPrefixTable()
    {
        return cssClassPrefixTable;
    }

    public Document getDocument()
    {
        return htmlDocumentFacade.getDocument();
    }

    protected String getStyleClassName( HSSFWorkbook workbook,
            HSSFCellStyle cellStyle )
    {
        final Short cellStyleKey = Short.valueOf( cellStyle.getIndex() );

        String knownClass = excelStyleToClass.get( cellStyleKey );
        if ( knownClass != null )
            return knownClass;

        String cssStyle = buildStyle( workbook, cellStyle );
        String cssClass = htmlDocumentFacade.getOrCreateCssClass(
                cssClassPrefixCell, cssStyle );
        excelStyleToClass.put( cellStyleKey, cssClass );
        return cssClass;
    }

    public boolean isUseDivsToSpan()
    {
        return useDivsToSpan;
    }

    protected boolean processCell( HSSFCell cell, Element tableCellElement,
            int normalWidthPx, int maxSpannedWidthPx, float normalHeightPt )
    {
        final HSSFCellStyle cellStyle = cell.getCellStyle();

        String value;
        switch ( cell.getCellType() )
        {
        case HSSFCell.CELL_TYPE_STRING:
            // XXX: enrich
            value = cell.getRichStringCellValue().getString();
            break;
        case HSSFCell.CELL_TYPE_FORMULA:
            final CellValue cv = evaluator.evaluate(cell);
            switch ( cv.getCellType() )
            {
            case HSSFCell.CELL_TYPE_STRING:
                String str = cv.getStringValue();
                if ( str != null && str.length() > 0 )
                {
                    value = ( str.toString() );
                }
                else
                {
                    value = ExcelToHtmlUtils.EMPTY;
                }
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                HSSFCellStyle style = cellStyle;
                if ( style == null )
                {
                    value = String.valueOf( cv.getNumberValue() );
                }
                else
                {
                    value = ( _formatter.formatRawCellContents(
                            cv.getNumberValue(), style.getDataFormat(),
                            style.getDataFormatString() ) );
                }
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                value = String.valueOf( cv.getBooleanValue() );
                break;
            case HSSFCell.CELL_TYPE_ERROR:
                value = ErrorEval.getText( cv.getErrorValue() );
                break;
            default:
                logger.log(
                        POILogger.WARN,
                        "Unexpected cell cachedFormulaResultType ("
                                + cell.getCachedFormulaResultType() + ")" );
                value = ExcelToHtmlUtils.EMPTY;
                break;
            }
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            value = ExcelToHtmlUtils.EMPTY;
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
            value = _formatter.formatCellValue( cell );
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            value = String.valueOf( cell.getBooleanCellValue() );
            break;
        case HSSFCell.CELL_TYPE_ERROR:
            value = ErrorEval.getText( cell.getErrorCellValue() );
            break;
        default:
            logger.log( POILogger.WARN,
                    "Unexpected cell type (" + cell.getCellType() + ")" );
            return true;
        }

        final boolean noText = ExcelToHtmlUtils.isEmpty( value );
        final boolean wrapInDivs = !noText && isUseDivsToSpan()
                && !cellStyle.getWrapText();

        final short cellStyleIndex = cellStyle.getIndex();
        if ( cellStyleIndex != 0 )
        {
            HSSFWorkbook workbook = cell.getRow().getSheet().getWorkbook();
            String mainCssClass = getStyleClassName( workbook, cellStyle );
            if ( wrapInDivs )
            {
                tableCellElement.setAttribute( "class", mainCssClass + " "
                        + cssClassContainerCell );
            }
            else
            {
                tableCellElement.setAttribute( "class", mainCssClass );
            }

            if ( noText )
            {
                /*
                 * if cell style is defined (like borders, etc.) but cell text
                 * is empty, add "&nbsp;" to output, so browser won't collapse
                 * and ignore cell
                 */
//                value = "\u00A0";
                value = " ";
            }
        } 

        if ( isOutputLeadingSpacesAsNonBreaking() && value.startsWith( " " ) )
        {
            StringBuilder builder = new StringBuilder();
            for ( int c = 0; c < value.length(); c++ )
            {
                if ( value.charAt( c ) != ' ' )
                    break;
                builder.append( '\u00a0' );
            }

            if ( value.length() != builder.length() )
                builder.append( value.substring( builder.length() ) );

            value = builder.toString();
        }

        Text text = htmlDocumentFacade.createText( value );

        if ( wrapInDivs )
        {
            Element outerDiv = htmlDocumentFacade.createBlock();
            outerDiv.setAttribute( "class", this.cssClassContainerDiv );

            Element innerDiv = htmlDocumentFacade.createBlock();
            StringBuilder innerDivStyle = new StringBuilder();
            innerDivStyle.append( "position:absolute;min-width:" );
            innerDivStyle.append( normalWidthPx );
            innerDivStyle.append( "px;" );
            if ( maxSpannedWidthPx != Integer.MAX_VALUE )
            {
                innerDivStyle.append( "max-width:" );
                innerDivStyle.append( maxSpannedWidthPx );
                innerDivStyle.append( "px;" );
            }
            innerDivStyle.append( "overflow:hidden;max-height:" );
            innerDivStyle.append( normalHeightPt );
            innerDivStyle.append( "pt;white-space:nowrap;" );
            ExcelToHtmlUtils.appendAlign( innerDivStyle,
                    cellStyle.getAlignment() );
            htmlDocumentFacade.addStyleClass( outerDiv, cssClassPrefixDiv,
                    innerDivStyle.toString() );

            innerDiv.appendChild( text );
            outerDiv.appendChild( innerDiv );
            tableCellElement.appendChild( outerDiv );
        }
        else
        {
            tableCellElement.appendChild( text );
        }

        return ExcelToHtmlUtils.isEmpty( value ) && cellStyleIndex == 0;
    }

    protected void processColumnHeaders( HSSFSheet sheet, int maxSheetColumns,
            Element table )
    {
        Element tableHeader = htmlDocumentFacade.createTableHeader();
        table.appendChild( tableHeader );

        Element tr = htmlDocumentFacade.createTableRow();

        if ( isOutputRowNumbers() )
        {
            // empty row at left-top corner
            tr.appendChild( htmlDocumentFacade.createTableHeaderCell() );
        }

        for ( int c = 0; c < maxSheetColumns; c++ )
        {
            if ( !isOutputHiddenColumns() && sheet.isColumnHidden( c ) )
                continue;

            Element th = htmlDocumentFacade.createTableHeaderCell();
            String text = getColumnName( c );
            th.appendChild( htmlDocumentFacade.createText( text ) );
            tr.appendChild( th );
        }
        tableHeader.appendChild( tr );
    }

    /**
     * Creates COLGROUP element with width specified for all columns. (Except
     * first if <tt>{@link #isOutputRowNumbers()}==true</tt>)
     */
    protected void processColumnWidths( HSSFSheet sheet, int maxSheetColumns,
            Element table )
    {
        // draw COLS after we know max column number
        Element columnGroup = htmlDocumentFacade.createTableColumnGroup();
        if ( isOutputRowNumbers() )
        {
            columnGroup.appendChild( htmlDocumentFacade.createTableColumn() );
        }
        for ( int c = 0; c < maxSheetColumns; c++ )
        {
            if ( !isOutputHiddenColumns() && sheet.isColumnHidden( c ) )
                continue;

            Element col = htmlDocumentFacade.createTableColumn();
            col.setAttribute( "width",
                    String.valueOf( getColumnWidth( sheet, c ) ) );
            columnGroup.appendChild( col );
        }
        table.appendChild( columnGroup );
    }

    protected void processDocumentInformation(
            SummaryInformation summaryInformation )
    {
        if ( ExcelToHtmlUtils.isNotEmpty( summaryInformation.getTitle() ) )
            htmlDocumentFacade.setTitle( summaryInformation.getTitle() );

        if ( ExcelToHtmlUtils.isNotEmpty( summaryInformation.getAuthor() ) )
            htmlDocumentFacade.addAuthor( summaryInformation.getAuthor() );

        if ( ExcelToHtmlUtils.isNotEmpty( summaryInformation.getKeywords() ) )
            htmlDocumentFacade.addKeywords( summaryInformation.getKeywords() );

        if ( ExcelToHtmlUtils.isNotEmpty( summaryInformation.getComments() ) )
            htmlDocumentFacade
                    .addDescription( summaryInformation.getComments() );
    }

    /**
     * @return maximum 1-base index of column that were rendered, zero if none
     */
    protected int processRow( CellRangeAddress[][] mergedRanges, HSSFRow row,
            Element tableRowElement )
    {
        final HSSFSheet sheet = row.getSheet();
        final short maxColIx = row.getLastCellNum();
        if ( maxColIx <= 0 )
            return 0;

        final List<Element> emptyCells = new ArrayList<Element>( maxColIx );

        if ( isOutputRowNumbers() )
        {
            Element tableRowNumberCellElement = htmlDocumentFacade
                    .createTableHeaderCell();
            processRowNumber( row, tableRowNumberCellElement );
            emptyCells.add( tableRowNumberCellElement );
        }

        int maxRenderedColumn = 0;
        for ( int colIx = 0; colIx < maxColIx; colIx++ )
        {
            if ( !isOutputHiddenColumns() && sheet.isColumnHidden( colIx ) )
                continue;

            CellRangeAddress range = ExcelToHtmlUtils.getMergedRange(
                    mergedRanges, row.getRowNum(), colIx );

            if ( range != null
                    && ( range.getFirstColumn() != colIx || range.getFirstRow() != row.getRowNum())){
                continue;
            }

            HSSFCell cell = row.getCell( colIx );

            int divWidthPx = 0;
            if ( isUseDivsToSpan() )
            {
                divWidthPx = getColumnWidth( sheet, colIx );

                boolean hasBreaks = false;
                for ( int nextColumnIndex = colIx + 1; nextColumnIndex < maxColIx; nextColumnIndex++ )
                {
                    if ( !isOutputHiddenColumns()
                            && sheet.isColumnHidden( nextColumnIndex ) )
                        continue;

                    if ( row.getCell( nextColumnIndex ) != null
                            && !isTextEmpty( row.getCell( nextColumnIndex ) ) )
                    {
                        hasBreaks = true;
                        break;
                    }

                    divWidthPx += getColumnWidth( sheet, nextColumnIndex );
                }

                if ( !hasBreaks )
                    divWidthPx = Integer.MAX_VALUE;
            }

            Element tableCellElement = htmlDocumentFacade.createTableCell();
            
            HSSFPicture pic = picMap.get(colIx+":"+row.getRowNum());
            if(pic != null && tableCellElement != null){
                try {
//                	StringBuilder out = new StringBuilder();
//                  out.append("data:");
//                  out.append(pic.getPictureData().getMimeType());
//                  out.append(";base64,");
//                  out.append(Base64.encodeBytes(pic.getPictureData().getData()));
//                  
//    	            Element h2 = htmlDocumentFacade.createImage(out.toString());
                	String name="temp"+picIndex ++;
    				FileOutputStream stream = new FileOutputStream(name);
    	            stream.write(pic.getPictureData().getData());
    	            stream.flush();
    	            stream.close();
    	            
    	            Element h2 = htmlDocumentFacade.createImage(name);
//    	            int x = pic.getPreferredSize().getDx1();
//    	            int y = pic.getPreferredSize().getDy1();        
//    	            h2.setAttribute("style", "left:"+x+"px;top:"+y+"px;position:absolute");
    	            h2.setAttribute("style", "width:"+pic.getImageDimension().width*iScale+
    	            		"px;height:"+pic.getImageDimension().height*iScale+"px;" +
    	            		"left:"+iX+"px;top:"+iY+"px;position:absolute");
//    	            tableCellElement.setAttribute("style", "position:relative");
    	            tableCellElement.appendChild( h2 );
                    maxRenderedColumn = colIx;
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
            }

            if ( range != null )
            {
                if ( range.getFirstColumn() != range.getLastColumn() )
                    tableCellElement.setAttribute(
                            "colspan",
                            String.valueOf( range.getLastColumn()
                                    - range.getFirstColumn() + 1 ) );
                if ( range.getFirstRow() != range.getLastRow() )
                    tableCellElement.setAttribute(
                            "rowspan",
                            String.valueOf( range.getLastRow()
                                    - range.getFirstRow() + 1 ) );
            }

            
            boolean emptyCell;
            if ( cell != null )
            {
                emptyCell = processCell( cell, tableCellElement,
                        getColumnWidth( sheet, colIx ), divWidthPx,
                        row.getHeight() / 20f );
            }
            else
            {
                emptyCell = true;
            }

            if ( emptyCell )
            {
                emptyCells.add( tableCellElement );
            }
            else
            {
                for ( Element emptyCellElement : emptyCells )
                {
                    tableRowElement.appendChild( emptyCellElement );
                }
                emptyCells.clear();

                tableRowElement.appendChild( tableCellElement );
                maxRenderedColumn = colIx;
            }
        }

        return maxRenderedColumn + 1;
    }

    protected void processRowNumber( HSSFRow row,
            Element tableRowNumberCellElement )
    {
        tableRowNumberCellElement.setAttribute( "class", "rownumber" );
        Text text = htmlDocumentFacade.createText( getRowName( row ) );
        tableRowNumberCellElement.appendChild( text );
    }

    protected void processSheet( HSSFSheet sheet )
    {
//        processSheetHeader( htmlDocumentFacade.getBody(), sheet );
//        if(logo != null)	
        processSheetImage( htmlDocumentFacade.getBody(), sheet );

        final int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
        if ( physicalNumberOfRows <= 0 )
            return;

        Element table = htmlDocumentFacade.createTable();
        table.setAttribute("cellpadding", "5");
        htmlDocumentFacade.addStyleClass( table, cssClassPrefixTable,
                "border-collapse:collapse;border-spacing:0;" );

        Element tableBody = htmlDocumentFacade.createTableBody();

        final CellRangeAddress[][] mergedRanges = ExcelToHtmlUtils
                .buildMergedRangesMap( sheet );

        final List<Element> emptyRowElements = new ArrayList<Element>(
                physicalNumberOfRows );
        int maxSheetColumns = 1;
        for ( int r = sheet.getFirstRowNum(); r <= sheet.getLastRowNum(); r++ )
        {
            HSSFRow row = sheet.getRow( r );
            System.out.println("MaxColumn:"+r+":"+row);
            
            if ( row == null ){
                Element tableRowElement = htmlDocumentFacade.createTableRow();
                htmlDocumentFacade.addStyleClass( tableRowElement,
                        cssClassPrefixRow, "height:8pt;" );
                emptyRowElements.add( tableRowElement );
                continue;
            }

            if ( !isOutputHiddenRows() && row.getZeroHeight() )
                continue;

            Element tableRowElement = htmlDocumentFacade.createTableRow();
            htmlDocumentFacade.addStyleClass( tableRowElement,
                    cssClassPrefixRow, "height:" + ( row.getHeight() / 20f )
                            + "pt;" );

            int maxRowColumnNumber = processRow( mergedRanges, row,
                    tableRowElement );
//            System.out.println("MaxColumn:"+r+":"+maxRowColumnNumber);

            if ( maxRowColumnNumber == 0 )
            {
                emptyRowElements.add( tableRowElement );
            }
            else
            {
                if ( !emptyRowElements.isEmpty() )
                {
                    for ( Element emptyRowElement : emptyRowElements )
                    {
                        tableBody.appendChild( emptyRowElement );
                    }
                    emptyRowElements.clear();
                }

                tableBody.appendChild( tableRowElement );
            }
            maxSheetColumns = Math.max( maxSheetColumns, maxRowColumnNumber );
        }

        processColumnWidths( sheet, maxSheetColumns, table );

        if ( isOutputColumnHeaders() )
        {
            processColumnHeaders( sheet, maxSheetColumns, table );
        }

        table.appendChild( tableBody );

        htmlDocumentFacade.getBody().appendChild( table );
    }

    private void processSheetImage(Element htmlBody, HSSFSheet sheet) {
//        Element h2 = htmlDocumentFacade.createImage(logo);
//        h2.setAttribute("style", "top:112px;left:300px;position:absolute");
//        htmlBody.appendChild( h2 );

        if (sheet.getDrawingPatriarch() != null) {
            final List<HSSFShape> shapes = sheet.getDrawingPatriarch()
                    .getChildren();
            for (int i = 0; i < shapes.size(); ++i) {
                if (shapes.get(i) instanceof HSSFPicture) {
                    try {
                        // Gain access to private field anchor.
                        final HSSFPicture pic = (HSSFPicture) shapes.get(i);
                        picMap.put(pic.getPreferredSize().getCol1()+":"+pic.getPreferredSize().getRow1(), pic);
                    } catch (final Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
	}

	@SuppressWarnings("unused")
	private String toString(HSSFClientAnchor anchor) {
		return "("+anchor.getDx1()+","+anchor.getDx2()+","+anchor.getDy1()+","+anchor.getDy2()+")"+
			   "("+anchor.getRow1()+","+anchor.getRow2()+","+anchor.getCol1()+","+anchor.getCol2()+")";
	}

	protected void processSheetHeader( Element htmlBody, HSSFSheet sheet )
    {
        Element h2 = htmlDocumentFacade.createHeader2();
        h2.appendChild( htmlDocumentFacade.createText( sheet.getSheetName() ) );
        htmlBody.appendChild( h2 );
    }

    public void processWorkbook( HSSFWorkbook workbook )
    {
        evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        final SummaryInformation summaryInformation = workbook
                .getSummaryInformation();
        if ( summaryInformation != null )
        {
            processDocumentInformation( summaryInformation );
        }

        if ( isUseDivsToSpan() )
        {
            // prepare CSS classes for later usage
            this.cssClassContainerCell = htmlDocumentFacade
                    .getOrCreateCssClass( cssClassPrefixCell,
                            "padding:0;margin:0;align:left;vertical-align:top;" );
            this.cssClassContainerDiv = htmlDocumentFacade.getOrCreateCssClass(
                    cssClassPrefixDiv, "position:relative;" );
        }

        for ( int s = 0; s < workbook.getNumberOfSheets(); s++ )
        {
            HSSFSheet sheet = workbook.getSheetAt( s );
            processSheet( sheet );
        }

        htmlDocumentFacade.updateStylesheet();
    }

    public void setCssClassPrefixCell( String cssClassPrefixCell )
    {
        this.cssClassPrefixCell = cssClassPrefixCell;
    }

    public void setCssClassPrefixDiv( String cssClassPrefixDiv )
    {
        this.cssClassPrefixDiv = cssClassPrefixDiv;
    }

    public void setCssClassPrefixRow( String cssClassPrefixRow )
    {
        this.cssClassPrefixRow = cssClassPrefixRow;
    }

    public void setCssClassPrefixTable( String cssClassPrefixTable )
    {
        this.cssClassPrefixTable = cssClassPrefixTable;
    }

    /**
     * Allows converter to wrap content into two additional DIVs with tricky
     * styles, so it will wrap across empty cells (like in Excel).
     * <p>
     * <b>Warning:</b> after enabling this mode do not serialize result HTML
     * with INDENT=YES option, because line breaks will make additional
     * (unwanted) changes
     */
    public void setUseDivsToSpan( boolean useDivsToSpan )
    {
        this.useDivsToSpan = useDivsToSpan;
    }
    
    public void setImageProperties(double scale, int dx, int dy){
    	this.iScale = scale;
    	this.iX = dx;
    	this.iY = dy;
    }

	public String getHTML() {
		try {
			StringWriter out = new StringWriter();
	        DOMSource domSource = new DOMSource( getDocument() );
	        StreamResult streamResult = new StreamResult( out );
	
	        TransformerFactory tf = TransformerFactory.newInstance();
	        Transformer serializer = tf.newTransformer();
	        // TODO set encoding from a command argument
	        serializer.setOutputProperty( OutputKeys.ENCODING, "UTF-8" );
	        serializer.setOutputProperty( OutputKeys.INDENT, "no" );
	        serializer.setOutputProperty( OutputKeys.METHOD, "html" );
	        serializer.transform( domSource, streamResult );
	        out.close();

	        return out.toString();
		}catch(Exception e){}
		return "";
	}
	
	public static String convert(HSSFWorkbook book) {
        HSSFPrintSetup page = book.getSheetAt(0).getPrintSetup();
        String size = "A4";
        if(page.getPaperSize() == HSSFPrintSetup.LETTER_PAPERSIZE)	size = "letter";
//        size += page.getLandscape()?"-landscape":"-portrait";
        System.out.println("Size:"+size+":"+page.getLandscape());
		try {
			ExcelToHtmlConverter excelToHtmlConverter = new ExcelToHtmlConverter(
					DocumentBuilderFactory.newInstance().newDocumentBuilder()
			                .newDocument() );
	        excelToHtmlConverter.setOutputColumnHeaders(false);
	        excelToHtmlConverter.setOutputRowNumbers(false);
	        excelToHtmlConverter.processWorkbook( book );
	        return 
	        	"<style type=\"text/css\">" +
		        	"div.header {\n" +
			            "display: block; text-align: center;\n" + 
			            "position: running(header);}\n" +
		            "div.footer {\n" +
			            "display: block; text-align: center;\n" + 
			            "position: running(footer);}\n" +
		        	"<!--@page { size:"+size+";}\n" +
		            "@page { @top-center { content: element(header) }}\n " +
		            "@page { @bottom-center { content: element(footer) }}-->\n" +
	        	"</style>"+
	        	excelToHtmlConverter.getHTML();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String convert(HSSFWorkbook book, double width, double height, double scale) {
       try {
			ExcelToHtmlConverter excelToHtmlConverter = new ExcelToHtmlConverter(
					DocumentBuilderFactory.newInstance().newDocumentBuilder()
			                .newDocument() );
			excelToHtmlConverter.setScale(scale);
	        excelToHtmlConverter.setOutputColumnHeaders(false);
	        excelToHtmlConverter.setOutputRowNumbers(false);
	        excelToHtmlConverter.processWorkbook( book);
	        System.out.println("Size:"+width+":"+height);
	        return 
	        	"<style type=\"text/css\">" +
		        	"div.header {\n" +
			            "display: block; text-align: center;\n" + 
			            "position: running(header);}\n" +
		            "div.footer {\n" +
			            "display: block; text-align: center;\n" + 
			            "position: running(footer);}\n" +
		        	"<!--@page { size:"+width*scale+"cm "+height*scale+"cm;margin:1cm;}\n" +
		            "@page { @top-center { content: element(header) }}\n " +
		            "@page { @bottom-center { content: element(footer) }}-->\n" +
	        	"</style>"+
	        	excelToHtmlConverter.getHTML();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
