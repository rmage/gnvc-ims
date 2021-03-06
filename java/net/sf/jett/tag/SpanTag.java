package net.sf.jett.tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;

import net.sf.jett.exception.TagParseException;
import net.sf.jett.model.Block;
import net.sf.jett.model.CellStyleCache;
import net.sf.jett.model.ExcelColor;
import net.sf.jett.transform.BlockTransformer;
import net.sf.jett.util.AttributeUtil;
import net.sf.jett.util.SheetUtil;

/**
 * <p>A <code>SpanTag</code> represents a cell or merged region that will span
 * extra rows and/or extra columns, depending on growth and/or adjustment
 * factors.  If this tag is applied to a cell that is not part of a merged
 * region, then it may result in the creation of a merged region.  If this tag
 * is applied to a cell that is part of a merged region, then it may result in
 * the removal of the merged region.</p>
 *
 * <br/>Attributes:
 * <ul>
 * <li><em>Inherits all attributes from {@link BaseTag}.</em></li>
 * <li>factor (optional): <code>int</code></li>
 * <li>adjust (optional): <code>int</code></li>
 * <li>value (required): <code>RichTextString</code></li>
 * <li>expandRight (optional): <code>boolean</code></li>
 * </ul>
 *
 * <p>Either one or both of the <code>factor</code> and the <code>adjust</code>
 * attributes must be specified.</p>
 *
 * @author Randy Gettman
 */
public class SpanTag extends BaseTag
{
   private static final boolean DEBUG = false;

   /**
    * Attribute for specifying the growth factor.
    */
   public static final String ATTR_FACTOR = "factor";
   /**
    * Attribute for specifying an adjustment to the size of the merged region.
    * @since 0.4.0
    */
   public static final String ATTR_ADJUST = "adjust";
   /**
    * Attribute for forcing "expand right" behavior.  (Default is expand down.)
    */
   public static final String ATTR_EXPAND_RIGHT = "expandRight";
   /**
    * Attribute that specifies the value of the cell/merged region.
    */
   public static final String ATTR_VALUE = "value";

   private static final List<String> REQ_ATTRS =
      new ArrayList<String>(Arrays.asList(ATTR_VALUE));
   private static final List<String> OPT_ATTRS =
      new ArrayList<String>(Arrays.asList(ATTR_EXPAND_RIGHT, ATTR_FACTOR, ATTR_ADJUST));

   private int myFactor = 1;
   private int myAdjust = 0;
   private RichTextString myValue;

   /**
    * Returns this <code>Tag's</code> name.
    * @return This <code>Tag's</code> name.
    */
   public String getName()
   {
      return "span";
   }

   /**
    * Returns a <code>List</code> of required attribute names.
    * @return A <code>List</code> of required attribute names.
    */
   @Override
   protected List<String> getRequiredAttributes()
   {
      List<String> reqAttrs = new ArrayList<String>(super.getRequiredAttributes());
      reqAttrs.addAll(REQ_ATTRS);
      return reqAttrs;
   }

   /**
    * Returns a <code>List</code> of optional attribute names.
    * @return A <code>List</code> of optional attribute names.
    */
   @Override
   protected List<String> getOptionalAttributes()
   {
      List<String> optAttrs = new ArrayList<String>(super.getOptionalAttributes());
      optAttrs.addAll(OPT_ATTRS);
      return optAttrs;
   }

   /**
    * Validates the attributes for this <code>Tag</code>.  Some optional
    * attributes are only valid for bodiless tags, and others are only valid
    * for tags without bodies.
    */
   public void validateAttributes()
   {
      super.validateAttributes();
      TagContext context = getContext();
      Map<String, Object> beans = context.getBeans();
      Map<String, RichTextString> attributes = getAttributes();
      Block block = context.getBlock();

      if (!isBodiless())
         throw new TagParseException("SpanTag: Must be bodiless");

      myValue = attributes.get(ATTR_VALUE);

      List<RichTextString> atLeastOne = Arrays.asList(attributes.get(ATTR_FACTOR), attributes.get(ATTR_ADJUST));
      AttributeUtil.ensureAtLeastOneExists(atLeastOne, Arrays.asList(ATTR_FACTOR, ATTR_ADJUST));
      myFactor = AttributeUtil.evaluateNonNegativeInt(attributes.get(ATTR_FACTOR), beans, ATTR_FACTOR, 1);
      myAdjust = AttributeUtil.evaluateInt(attributes.get(ATTR_ADJUST), beans, ATTR_ADJUST, 0);

      boolean explicitlyExpandingRight = AttributeUtil.evaluateBoolean(attributes.get(ATTR_EXPAND_RIGHT), beans, false);
      if (explicitlyExpandingRight)
         block.setDirection(Block.Direction.HORIZONTAL);
      else
         block.setDirection(Block.Direction.VERTICAL);
   }

   /**
    * <p>If not already part of a merged region, and one of the factors is
    * greater than 1, then create a merged region.  Else, replace the current
    * merged region with a new merged region.</p>
    * @return Whether the first <code>Cell</code> in the <code>Block</code>
    *    associated with this <code>Tag</code> was processed.
    */
   public boolean process()
   {
      TagContext context = getContext();
      Sheet sheet = context.getSheet();
      Block block = context.getBlock();

      if (DEBUG)
         System.err.println("SpanTag.process: factor=" + myFactor + ", block direction is " + block.getDirection());

      int left = block.getLeftColNum();
      int right = left;
      int top = block.getTopRowNum();
      int bottom = top;
      // Assume a "merged region" of 1 X 1 for now.
      int height = 1;
      int width = 1;
      int index = findMergedRegionAtCell(sheet, left, top);
      if (index != -1)
      {
         // Get the height/width and remove the old merged region.
         CellRangeAddress remove = sheet.getMergedRegion(index);
         right = remove.getLastColumn();
         bottom = remove.getLastRow();
         height = remove.getLastRow() - remove.getFirstRow() + 1;
         width = remove.getLastColumn() - remove.getFirstColumn() + 1;
         if (DEBUG)
            System.err.println("  Removing region: " + remove + ", height=" + height + ", width=" + width);
         sheet.removeMergedRegion(index);
      }

      short borderBottomType = CellStyle.BORDER_NONE;
      short borderLeftType = CellStyle.BORDER_NONE;
      short borderRightType = CellStyle.BORDER_NONE;
      short borderTopType = CellStyle.BORDER_NONE;
      Color borderBottomColor = null;
      Color borderLeftColor = null;
      Color borderRightColor = null;
      Color borderTopColor = null;
      // Get current borders and border colors.
      Row rTop = sheet.getRow(top);
      if (rTop != null)
      {
         Cell cLeft = rTop.getCell(left);
         if (cLeft != null)
         {
            CellStyle cs = cLeft.getCellStyle();
            borderLeftType = cs.getBorderLeft();
            borderTopType = cs.getBorderTop();
            // Border colors need instanceof check.
            if (cs instanceof HSSFCellStyle)
            {
               borderLeftColor = ExcelColor.getHssfColorByIndex(cs.getLeftBorderColor());
               borderTopColor = ExcelColor.getHssfColorByIndex(cs.getTopBorderColor());
            }
            else
            {
               // XSSFCellStyle
               XSSFCellStyle xcs = (XSSFCellStyle) cs;
               borderLeftColor = xcs.getLeftBorderXSSFColor();
               borderTopColor = xcs.getTopBorderXSSFColor();
            }
         }
      }
      Row rBottom = sheet.getRow(bottom);
      if (rBottom != null)
      {
         Cell cRight = rBottom.getCell(right);
         if (cRight != null)
         {
            CellStyle cs = cRight.getCellStyle();
            borderRightType = cs.getBorderRight();
            borderBottomType = cs.getBorderBottom();
            // Border colors need instanceof check.
            if (cs instanceof HSSFCellStyle)
            {
               borderRightColor = ExcelColor.getHssfColorByIndex(cs.getRightBorderColor());
               borderBottomColor = ExcelColor.getHssfColorByIndex(cs.getBottomBorderColor());
            }
            else
            {
               // XSSFCellStyle
               XSSFCellStyle xcs = (XSSFCellStyle) cs;
               borderRightColor = xcs.getRightBorderXSSFColor();
               borderBottomColor = xcs.getBottomBorderXSSFColor();
            }
         }
      }
      if (borderTopType != CellStyle.BORDER_NONE || borderBottomType != CellStyle.BORDER_NONE ||
          borderRightType != CellStyle.BORDER_NONE || borderLeftType != CellStyle.BORDER_NONE)
      {
         removeBorders(sheet, left, right, top, bottom);
      }

      // The block for which to shift content out of the way or to remove is
      // actually the old merged region.
      Block mergedBlock = new Block(block.getParent(), left, right, top, bottom);
      mergedBlock.setDirection(block.getDirection());

      // Determine new height or width, plus new bottom or right.
      int change;
      if (block.getDirection() == Block.Direction.VERTICAL)
      {
         change = height * (myFactor - 1) + myAdjust;
         bottom += change;
         height = bottom - top + 1;
      }
      else
      {
         change = width * (myFactor - 1) + myAdjust;
         right += change;
         width = right - left + 1;
      }

      // Remove.
      if (height <= 0 || width <= 0)
      {
         if (DEBUG)
            System.err.println("  Calling removeBlock on block: " + mergedBlock);
         SheetUtil.removeBlock(sheet, mergedBlock, getWorkbookContext());
         return false;
      }
      // Shrink.
      if (change < 0)
      {
         Block remove;
         if (block.getDirection() == Block.Direction.VERTICAL)
            remove = new Block(block.getParent(), left, right, bottom + 1, bottom - change);
         else
            remove = new Block(block.getParent(), right + 1, right - change, top, bottom);
         remove.setDirection(block.getDirection());
         if (DEBUG)
            System.err.println("  Calling removeBlock on fabricated block: " + remove + " (change " + change + ")");
         SheetUtil.removeBlock(sheet, remove, getWorkbookContext());
      }
      // Expand.
      if (change > 0)
      {
         Block expand;
         if (block.getDirection() == Block.Direction.VERTICAL)
            expand = new Block(block.getParent(), left, right, bottom - change, bottom - change);
         else
            expand = new Block(block.getParent(), right - change, right - change, top, bottom);
         expand.setDirection(block.getDirection());
         if (DEBUG)
            System.err.println("  Calling shiftForBlock on fabricated block: " + expand + " with change " + (change + 1));
         SheetUtil.shiftForBlock(sheet, expand, getWorkbookContext(), change + 1);
      }
      if (borderTopType != CellStyle.BORDER_NONE || borderBottomType != CellStyle.BORDER_NONE ||
          borderRightType != CellStyle.BORDER_NONE || borderLeftType != CellStyle.BORDER_NONE)
      {
         putBackBorders(sheet, left, right, top, bottom,
            borderLeftType, borderRightType, borderTopType, borderBottomType,
            borderLeftColor, borderRightColor, borderTopColor, borderBottomColor);
      }


      // Set the value.
      Row row = sheet.getRow(top);
      Cell cell = row.getCell(left);
      SheetUtil.setCellValue(cell, myValue);

      // Create the replacement merged region, or the new merged region if it
      // didn't exist before.
      if (height > 1 || width > 1)
      {
         CellRangeAddress create = new CellRangeAddress(top, bottom, left, right);
         if (DEBUG)
            System.err.println("  Adding region: " + create);
         sheet.addMergedRegion(create);
      }

      BlockTransformer transformer = new BlockTransformer();
      transformer.transform(context, getWorkbookContext());

      return true;
   }

   /**
    * Identify the merged region on the given <code>Sheet</code> whose top-left
    * corner is at the specified column and row indexes.
    * @param sheet A <code>Sheet</code>.
    * @param col The 0-based column index of the top-left corner.
    * @param row The 0-based row index of the top-left corner.
    * @return A 0-based index into the <code>Sheet's</code> list of merged
    *    regions, or -1 if not found.
    */
   private int findMergedRegionAtCell(Sheet sheet, int col, int row)
   {
      for (int i = 0; i < sheet.getNumMergedRegions(); i++)
      {
         CellRangeAddress candidate = sheet.getMergedRegion(i);
         if (candidate.getFirstRow() == row && candidate.getFirstColumn() == col)
            return i;
      }
      return -1;
   }

   /**
    * Remove all borders from all cells in the region described by the left,
    * right, top, and bottom bounds.
    * @param sheet The <code>Sheet</code>.
    * @param left The 0-based index indicating the left-most part of the region.
    * @param right The 0-based index indicating the right-most part of the region.
    * @param top The 0-based index indicating the top-most part of the region.
    * @param bottom The 0-based index indicating the bottom-most part of the region.
    */
   private void removeBorders(Sheet sheet, int left, int right, int top, int bottom)
   {
      if (DEBUG)
         System.err.println("removeBorders: " + left + ", " + right + ", " + top + ", " + bottom);
      CellStyleCache csCache = getWorkbookContext().getCellStyleCache();
      for (int r = top; r <= bottom; r++)
      {
         Row row = sheet.getRow(r);
         for (int c = left; c <= right; c++)
         {
            Cell cell = row.getCell(c);
            if (cell != null)
            {
               CellStyle cs = cell.getCellStyle();
               Font f = sheet.getWorkbook().getFontAt(cs.getFontIndex());
               Color fontColor;
               if (cs instanceof HSSFCellStyle)
               {
                  fontColor = ExcelColor.getHssfColorByIndex(f.getColor());
               }
               else
               {
                  fontColor = ((XSSFFont) f).getXSSFColor();
               }
               // At this point, we have all of the desired CellStyle and Font
               // characteristics.  Find a CellStyle if it exists.
               CellStyle foundStyle = csCache.retrieveCellStyle(f.getBoldweight(), f.getItalic(), fontColor,
                  f.getFontName(), f.getFontHeightInPoints(), cs.getAlignment(), CellStyle.BORDER_NONE,
                  CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, cs.getDataFormatString(),
                  f.getUnderline(), f.getStrikeout(), cs.getWrapText(), cs.getFillBackgroundColorColor(),
                  cs.getFillForegroundColorColor(), cs.getFillPattern(), cs.getVerticalAlignment(), cs.getIndention(),
                  cs.getRotation(), null, null, null, null,
                  f.getCharSet(), f.getTypeOffset(), cs.getLocked(), cs.getHidden());

               if (foundStyle == null)
               {
                  foundStyle = SheetUtil.createCellStyle(sheet.getWorkbook(), cs.getAlignment(), CellStyle.BORDER_NONE,
                  CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, cs.getDataFormatString(),
                     cs.getWrapText(), cs.getFillBackgroundColorColor(), cs.getFillForegroundColorColor(),
                     cs.getFillPattern(), cs.getVerticalAlignment(), cs.getIndention(), cs.getRotation(),
                     null, null, null, null, cs.getLocked(), cs.getHidden());
                  foundStyle.setFont(f);
               }
               cell.setCellStyle(foundStyle);
            }
         }
      }
   }

   /**
    * Puts back borders for the newly sized merged region.
    * @param sheet The <code>Sheet</code>.
    * @param left The 0-based index indicating the left-most part of the region.
    * @param right The 0-based index indicating the right-most part of the region.
    * @param top The 0-based index indicating the top-most part of the region.
    * @param bottom The 0-based index indicating the bottom-most part of the region.
    * @param borderLeft The left border type.
    * @param borderRight The right border type.
    * @param borderTop The top border type.
    * @param borderBottom The bottom border type.
    * @param borderLeftColor The left border color.
    * @param borderRightColor The right border color.
    * @param borderTopColor The top border color.
    * @param borderBottomColor The bottom border color.
    */
   private void putBackBorders(Sheet sheet, int left, int right, int top, int bottom,
      short borderLeft, short borderRight, short borderTop, short borderBottom,
      Color borderLeftColor, Color borderRightColor, Color borderTopColor, Color borderBottomColor)
   {
      if (DEBUG)
         System.err.println("putBackBorders: " + left + ", " + right + ", " + top + ", " + bottom);
      CellStyleCache csCache = getWorkbookContext().getCellStyleCache();
      for (int r = top; r <= bottom; r++)
      {
         Row row = sheet.getRow(r);
         if (row == null)
            row = sheet.createRow(r);
         for (int c = left; c <= right; c++)
         {
            Cell cell = row.getCell(c);
            if (cell == null)
               cell = row.createCell(c);

            CellStyle cs = cell.getCellStyle();
            Font f = sheet.getWorkbook().getFontAt(cs.getFontIndex());
            Color fontColor;
            if (cs instanceof HSSFCellStyle)
            {
               fontColor = ExcelColor.getHssfColorByIndex(f.getColor());
            }
            else
            {
               fontColor = ((XSSFFont) f).getXSSFColor();
            }
            short newBorderBottom = (r == bottom) ? borderBottom : CellStyle.BORDER_NONE;
            short newBorderLeft = (c == left) ? borderLeft : CellStyle.BORDER_NONE;
            short newBorderRight = (c == right) ? borderRight : CellStyle.BORDER_NONE;
            short newBorderTop = (r == top) ? borderTop : CellStyle.BORDER_NONE;
            Color newBorderBottomColor = (r == bottom) ? borderBottomColor : null;
            Color newBorderLeftColor = (c == left) ? borderLeftColor : null;
            Color newBorderRightColor = (c == right) ? borderRightColor : null;
            Color newBorderTopColor = (r == top) ? borderTopColor : null;
            // At this point, we have all of the desired CellStyle and Font
            // characteristics.  Find a CellStyle if it exists.
            CellStyle foundStyle = csCache.retrieveCellStyle(f.getBoldweight(), f.getItalic(), fontColor,
               f.getFontName(), f.getFontHeightInPoints(), cs.getAlignment(),
               newBorderBottom, newBorderLeft, newBorderRight, newBorderTop, cs.getDataFormatString(),
               f.getUnderline(), f.getStrikeout(), cs.getWrapText(), cs.getFillBackgroundColorColor(),
               cs.getFillForegroundColorColor(), cs.getFillPattern(), cs.getVerticalAlignment(), cs.getIndention(),
               cs.getRotation(), newBorderBottomColor, newBorderLeftColor, newBorderRightColor, newBorderTopColor,
               f.getCharSet(), f.getTypeOffset(), cs.getLocked(), cs.getHidden());

            if (foundStyle == null)
            {
               foundStyle = SheetUtil.createCellStyle(sheet.getWorkbook(), cs.getAlignment(), newBorderBottom,
                  newBorderLeft, newBorderRight, newBorderTop, cs.getDataFormatString(),
                  cs.getWrapText(), cs.getFillBackgroundColorColor(), cs.getFillForegroundColorColor(),
                  cs.getFillPattern(), cs.getVerticalAlignment(), cs.getIndention(), cs.getRotation(),
                  newBorderBottomColor, newBorderLeftColor, newBorderRightColor, newBorderTopColor,
                  cs.getLocked(), cs.getHidden());
               foundStyle.setFont(f);
            }
            cell.setCellStyle(foundStyle);
         }
      }
   }
}
