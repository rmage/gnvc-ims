package net.sf.jagg.msd;

/**
 * An <code>AbstractExtractor</code> encapsulates functionality needed by all
 * <code>Extractors</code>, specifically, determining whether an Extractor is
 * complete, and a chained <code>Extractor</code>.
 *
 * @param <E> The type of element.
 * @param <T> The type of label generated by the chained extractor.
 *
 * @author Randy Gettman
 * @since 0.5.0
 */
public abstract class AbstractExtractor<E, T>
{
   /**
    * The index that influences label generation.  This could be an array
    * index, a List position, or any index that represents part of an element.
    */
   protected int myIndex;
   /**
    * The chained <code>Extractor</code>.
    */
   protected Extractor<E, T> myExtractor;
   /**
    * Controlled by a discriminator, this notes down whether an equivalence
    * class is "complete", such that all elements in an equivalence class
    * have been examined completely.
    */
   protected boolean amIAllComplete;

   /**
    * Creates an <code>AbstractExtractor</code> that uses the given
    * <code>Extractor</code> in a chain for its labels.
    *
    * @param extractor The chained <code>Extractor</code>.
    */
   public AbstractExtractor(Extractor<E, T> extractor)
   {
      myExtractor = extractor;
   }

   /**
    * Sets which portion is retrieved as the label.
    *
    * @param index The index.
    */
   public void setIndex(int index)
   {
      myIndex = index;
   }

   /**
    * Determines whether discrimination is complete for the given element, at
    * the given index.  All elements that would throw, for example, an
    * <code>IndexOutOfBoundsException</code>, would return <code>true</code>
    * here so the algorithm doesn't call <code>getLabel</code>.  All
    * <code>AbstractExtractors</code> should check their chained
    * <code>Extractor</code>, to see if it's complete first.
    * @param element The element.
    * @return <code>true</code> if discrimination is complete or cannot
    *    continue, usually because the discrimination has run off the end of
    *    the label, <code>false</code> otherwise.
    */
   public abstract boolean isComplete(E element);

   /**
    * The <code>Discriminator</code> calls this method to indicate whether all
    * elements in its current equivalence class were complete.
    * @param allComplete Whether all elements were complete.
    */
   public void setAllComplete(boolean allComplete)
   {
      amIAllComplete = allComplete;
   }

   /**
    * The specific <code>Discriminator</code> calls this method to determine
    * whether all elements in the current equivalence class were complete.
    * @return Whether all elements were complete.
    */
   public boolean isAllComplete()
   {
      return amIAllComplete;
   }
}
