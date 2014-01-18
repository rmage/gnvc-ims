package net.sf.jett.jdbc;

import java.util.LinkedHashMap;

/**
 * A <code>ResultSetRow</code> contains data from one row of a
 * <code>ResultSet</code>.  Its {@link #get <code>get</code>} and
 * {@link #set <code>set</code>} methods allow easy propery manipulation.
 * Plus, the <code>get</code> method allows dynamic properties to be accessed
 * in JETT via JEXL Expressions, e.g <code>${employee.first_name}</code> is
 * accessed via a call to <code>employee.get("first_name")</code>, since the
 * <code>getFirstName()</code> method would not be found.
 *
 * @author Randy Gettman
 * @since 0.6.0
 */
public class ResultSetRow extends LinkedHashMap<String, Object>{   
//   private Map<String, Object> myValues;
   private static final long serialVersionUID = 7699390106122529953L;

/**
    * Constructs an empty <code>ResultSetRow</code>.
    */
   public ResultSetRow()
   {
//      myValues = new HashMap<String, Object>();
   }

   /**
    * Sets the given property string name to the given value.
    * @param property The property string name.
    * @param value The value.
    */
   public void set(String property, Object value)
   {
//	   myValues.put(property.toLowerCase(), value);
	   super.put(property.toLowerCase(), value);
   }

   /**
    * Returns the value for a given property string name.
    * @param property A property string name.
    * @return The value, or <code>null</code> if the property string name did
    *    not exist.
    */
   public Object get(String property)
   {
//      return myValues.get(property.toLowerCase());
	   return super.get(property.toLowerCase());
   }
}
