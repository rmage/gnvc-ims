package net.sf.jett.tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Sheet;

import net.sf.jett.event.TagEvent;
import net.sf.jett.event.TagListener;
import net.sf.jett.exception.TagParseException;
import net.sf.jett.model.Block;
import net.sf.jett.model.WorkbookContext;
import net.sf.jett.util.AttributeUtil;
import net.sf.jett.util.SheetUtil;

/**
 * <p>The abstract class <code>BaseTag</code> provides common functionality to
 * all <code>Tags</code>.</p>
 *
 * <br/>Attributes:
 * <ul>
 * <li>onProcessed (optional): <code>TagListener</code></li>
 * </ul>
 *
 * @author Randy Gettman
 */
public abstract class BaseTag implements Tag
{
   private static final boolean DEBUG = false;

   /**
    * Attribute for specifying a <code>TagListener</code> to listen for
    * <code>TagEvents</code>.
    */
   public static final String ATTR_ON_PROCESSED = "onProcessed";

   private static final List<String> OPT_ATTRS =
      new ArrayList<String>(Arrays.asList(ATTR_ON_PROCESSED));

   private Map<String, RichTextString> myAttributes;
   private TagContext myContext;
   private WorkbookContext myWorkbookContext;
   private boolean amIBodiless;
   private TagListener myTagListener;

   /**
    * Separates expressions in attributes that take multiple values.  This was
    * originally defined as the same value in multiple sub-classes, but was
    * moved to BaseTag for 0.3.0.
    * @since 0.3.0
    */
   public static final String SPEC_SEP = ";";

   /**
    * When a <code>Tag</code> is created, the attributes are passed in via a
    * (possibly empty) <code>Map</code> of attribute names and values.
    * @param attributes A <code>Map</code> of attribute names and values.
    */
   public void setAttributes(Map<String, RichTextString> attributes)
   {
      myAttributes = attributes;
   }

   /**
    * Returns the <code>Map</code> of attribute names and attribute values.
    * @return The <code>Map</code> of attribute names and attribute values.
    */
   public Map<String, RichTextString> getAttributes()
   {
      return myAttributes;
   }

   /**
    * Sets the <code>TagContext</code> to which the <code>Tag</code> is
    * associated.
    * @param context A <code>TagContext</code>.
    */
   public void setContext(TagContext context)
   {
      myContext = context;
   }

   /**
    * Returns the <code>WorkbookContext</code> to which the <code>Tag</code> is
    * associated.
    * @return The associated <code>WorkbookContext</code>.
    */
   public WorkbookContext getWorkbookContext()
   {
      return myWorkbookContext;
   }

   /**
    * Sets the <code>WorkbookContext</code> to which the <code>Tag</code> is
    * associated.
    * @param context A <code>WorkbookContext</code>.
    */
   public void setWorkbookContext(WorkbookContext context)
   {
      myWorkbookContext = context;
   }

   /**
    * Returns the <code>TagContext</code> to which the <code>Tag</code> is
    * associated.
    * @return The associated <code>TagContext</code>.
    */
   public TagContext getContext()
   {
      return myContext;
   }

   /**
    * Sets whether this <code>Tag</code> is bodiless.
    * @param bodiless <code>true</code> if this tag does not have a body,
    *    <code>false</code> if this tag does have a body.
    */
   public void setBodiless(boolean bodiless)
   {
      amIBodiless = bodiless;
   }

   /**
    * Returns whether this <code>Tag</code> is bodiless.
    * @return <code>true</code> if this tag does not have a body,
    *    <code>false</code> if this tag does have a body.
    */
   public boolean isBodiless()
   {
      return amIBodiless;
   }

   /**
    * <p>Checks the <code>Tag's</code> attributes to ensure that certain
    * requirements are met:</p>
    * <ol>
    * <li>All required attributes are present, and
    * <li>All attributes present are recognized.
    * <li>All attributes are validated through the method
    *    <code>validateAttributes</code> (i.e. that method doesn't throw an
    *    <code>Exception</code>).
    * </ol>
    * <p>A <code>TagParseException</code> is thrown if not all conditions above
    * are not met.</p>
    * <p>This calls <code>validateAttributes</code> if all conditions are met.</p>
    * @throws TagParseException If the above listed conditions are not met.
    * @see #validateAttributes
    */
   public void checkAttributes()
   {
      Map<String, RichTextString> attributes = getAttributes();
      List<String> required = getRequiredAttributes();
      List<String> optional = getOptionalAttributes();
      // Ensure all required attributes are found.
      if (required != null)
      {
         for (String reqName : required)
         {
            if (!attributes.containsKey(reqName))
               throw new TagParseException("Required attribute \"" + reqName +
                  "\" not found for tag \"" + getName() + "\".");
         }
      }
      // Ensure all attributes are in either the required list or in the
      // optional list.
      Set<String> keys = attributes.keySet();
      for (String key : keys)
      {
         if ((required == null || !required.contains(key)) &&
             (optional == null || !optional.contains(key)))
         {
            throw new TagParseException("Unrecognized attribute \"" + key +
               "\" for tag \"" + getName() + "\".");
         }
      }

      // Validate the attributes.
      validateAttributes();
   }

   /**
    * <p>Validates all attributes and attribute values and processes this
    * <code>Tag</code>.</p>
    * <p>For 0.3.0, the methods "checkAttributes" and "process" were removed
    * and replaced by this method, to allow for additional logic.</p>
    * @return <code>true</code> if the <code>Cell</code> containing this
    *    <code>Tag</code> was transformed, <code>false</code> if it needs to be
    *    transformed again.  This may happen if the <code>Block</code>
    *    associated with the <code>Tag</code> was removed.
    * @throws net.sf.jett.exception.TagParseException If all required
    *    attributes are not present, if there is an unrecognized attribute or
    *    attribute value, or if any tag data is unacceptable in any other way.
    * @since 0.3.0
    */
   public boolean processTag()
   {
      checkAttributes();
      boolean processed = process();
      fireTagEvent();
      return processed;
   }

   /**
    * If there is a <code>TagListener</code>, then create and fire a
    * <code>TagEvent</code>, with beans, block, and sheet taken from the
    * decorated <code>BaseTag</code>.
    */
   private void fireTagEvent()
   {
      if (myTagListener != null)
      {
         TagEvent tagEvent = new TagEvent();
         TagContext context = getContext();
         tagEvent.setBeans(context.getBeans());
         tagEvent.setBlock(context.getBlock());
         if (DEBUG)
            System.err.println("BT.fireTagEvent: context's Block is " + context.getBlock());
         tagEvent.setSheet(context.getSheet());
         myTagListener.onTagProcessed(tagEvent);
      }
   }

   /**
    * Removes the <code>Block</code> of <code>Cells</code> associated with this
    * <code>Tag</code>.  This can be called by subclasses if it determines that
    * its <code>Block</code> needs to be removed and not processed.
    */
   protected void removeBlock()
   {
      TagContext context = getContext();
      Block block = context.getBlock();
      Sheet sheet = context.getSheet();
      SheetUtil.removeBlock(sheet, block, getWorkbookContext());
      block.collapse();
   }

   /**
    * Removes the content from the <code>Block</code> of <code>Cells</code>
    * associated with this <code>Tag</code>.  This can be called by subclasses
    * if it determines that its <code>Block</code> needs to have its content
    * removed.
    */
   protected void deleteBlock()
   {
      TagContext context = getContext();
      Block block = context.getBlock();
      Sheet sheet = context.getSheet();
      SheetUtil.deleteBlock(sheet, block, getWorkbookContext());
      block.collapse();
   }

   /**
    * Clears the content from the <code>Block</code> of <code>Cells</code>
    * associated with this <code>Tag</code>.  This can be called by subclasses
    * if it determines that its <code>Block</code> needs its contents cleared.
    */
   protected void clearBlock()
   {
      TagContext context = getContext();
      Block block = context.getBlock();
      Sheet sheet = context.getSheet();
      SheetUtil.clearBlock(sheet, block, getWorkbookContext());
      block.collapse();
   }

   /**
    * Returns a <code>List</code> of required attribute names.  Subclasses that
    * want to add to this list must override this method, call
    * <code>super.getRequiredAttributes</code>, and add their own required
    * attributes.
    * @return A <code>List</code> of required attribute names.
    */
   protected List<String> getRequiredAttributes()
   {
      return new ArrayList<String>();
   }

   /**
    * Returns a <code>List</code> of optional attribute names.  Subclasses that
    * want to add to this list must override this method, call
    * <code>super.getOptionalAttributes</code>, and add their own optional
    * attributes.
    * @return A <code>List</code> of optional attribute names.
    */
   protected List<String> getOptionalAttributes()
   {
      return new ArrayList<String>(OPT_ATTRS);
   }

   /**
    * Validates the attributes according to <code>Tag</code>-specific rules.
    * Subclasses that want to add to validate their own attributes, as well as
    * these attributes, must override this method, call
    * <code>super.validateAttributes</code>, and then validate their own
    * attributes.
    * @throws TagParseException If the attribute values are illegal or
    *    unacceptable.
    */
   protected void validateAttributes() throws TagParseException
   {
      TagContext context = getContext();
      Map<String, Object> beans = context.getBeans();
      Map<String, RichTextString> attributes = getAttributes();

      myTagListener = AttributeUtil.evaluateObject(attributes.get(ATTR_ON_PROCESSED), beans, ATTR_ON_PROCESSED,
         TagListener.class, null);

      if (DEBUG)
         System.err.println("BT.vA: myTagListener is " + ((myTagListener != null) ? myTagListener.toString() : " null"));
   }

   /**
    * Process this <code>Tag</code>.  The logic of the <code>Tag</code> is
    * performed in this method.
    * @return <code>true</code> if the <code>Cell</code> containing this
    *    <code>Tag</code> was transformed, <code>false</code> if it needs to be
    *    transformed again.  This may happen if the <code>Block</code>
    *    associated with the <code>Tag</code> was removed.
    */
   public abstract boolean process();
}

