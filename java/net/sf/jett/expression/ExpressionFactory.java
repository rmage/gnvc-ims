package net.sf.jett.expression;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.jexl2.JexlEngine;

/**
 * <p>An <code>ExpressionFactory</code> is a singleton factory class that
 * creates and uses a <code>JexlEngine</code> to create JEXL
 * <code>Expressions</code>.</p>
 *
 * <p>It passes through several items to the JEXL Engine, including "silent"
 * and "lenient" flags, the JEXL parse cache size, and JEXL namespace function
 * objects (including jAgg functionality).</p>
 *
 * @author Randy Gettman
 */
public class ExpressionFactory
{
   private static final ExpressionFactory theFactory = new ExpressionFactory();

   private JexlEngine myEngine;
   private Map<String, Object> myFuncs = new HashMap<String, Object>();

   /**
    * Singleton constructor.
    */
   private ExpressionFactory()
   {
      myEngine = new JexlEngine();
      myEngine.setLenient(true);
      myEngine.setSilent(false);
      myFuncs = new HashMap<String, Object>();
      myEngine.setFunctions(myFuncs);
      myFuncs.put("jagg", JaggFuncs.class);
      myFuncs.put("jett", JettFuncs.class);
   }

   /**
    * Passes the given "lenient" flag on to the internal
    * <code>JexlEngine</code>.
    * @param lenient Whether the internal <code>JexlEngine</code> should be
    *    "lenient".
    */
   public void setLenient(boolean lenient)
   {
      myEngine.setLenient(lenient);
   }

   /**
    * Returns the internal <code>JexlEngine's</code> "lenient" flag.
    * @return Whether the internal <code>JexlEngine</code> is currently
    *    "lenient".
    */
   public boolean isLenient()
   {
      return myEngine.isLenient();
   }

   /**
    * Passes the given "silent" flag on to the internal
    * <code>JexlEngine</code>.
    * @param silent Whether the internal <code>JexlEngine</code> should be
    *    "silent".
    */
   public void setSilent(boolean silent)
   {
      myEngine.setSilent(silent);
   }

   /**
    * Returns the internal <code>JexlEngine's</code> "silent" flag.
    * @return Whether the internal <code>JexlEngine</code> is currently
    *    "silent".
    */
   public boolean isSilent()
   {
      return myEngine.isSilent();
   }

   /**
    * Sets the size of the Expression cache to be used inside the JEXL Engine.
    * @param size The size of the cache.
    * @since 0.2.0
    */
   public void setCache(int size)
   {
      myEngine.setCache(size);
   }

   /**
    * Registers an object under the given namespace in the JEXL Engine.  Each
    * public method in the object's class is exposed as a "function" available
    * in the JEXL Engine.  To use instance methods, pass an instance of the
    * object.  To use class methods, pass a <code>Class</code> object.
    * @param namespace The namespace.
    * @param funcsObject An object (or a <code>Class</code>) containing the
    *    methods to expose as JEXL Engine functions.
    * @throws IllegalArgumentException If the namespace has already been
    *    registered.
    * @since 0.2.0
    */
   public void registerFuncs(String namespace, Object funcsObject)
   {
      if (myFuncs.get(namespace) != null)
      {
         throw new IllegalArgumentException("A functions object with namespace \"" +
            namespace + "\" has already been registered.");
      }
      myFuncs.put(namespace, funcsObject);
   }

   /**
    * Returns the singleton <code>ExpressionFactory</code>.
    * @return The singleton <code>ExpressionFactory</code>.
    */
   public static ExpressionFactory getExpressionFactory()
   {
      return theFactory;
   }

   /**
    * Create a JEXL <code>Expression</code> from a string.
    * @param expression The expression as a <code>String</code>.
    * @return A JEXL <code>Expression</code>.
    */
   public org.apache.commons.jexl2.Expression createExpression(String expression)
   {
      return myEngine.createExpression(expression);
   }
}

