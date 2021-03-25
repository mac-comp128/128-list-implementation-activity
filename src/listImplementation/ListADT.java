package listImplementation;

/**
 * ListADT defines the interface to a general list collection. Specific
 * types of lists will extend this interface to complete the
 * set of necessary operations.
 *
 * @author Java Foundations
 * @version 4.0
 */
public interface ListADT<T>
{
	/**
	 * Adds the specified element to the rear of this list.
	 *
	 * @param element the element to be added to the rear of this list
	 */
	public void add(T element);

	/**
	 * Adds the specified element at the position given by index.
	 * The from of the list is position 0.
	 *
	 * @param element the element to be added at the index
	 * @param index   the position in the list where the element will be added
	 */
	public void add(T element, int index);

	/**
	 * Adds the specified element after the specified target.
	 *
	 * @param element the element to be added after the target
	 * @param target  the target is the item that the element will be added
	 *                after
	 */
	public void addAfter(T element, T target);

	/**  
	 * Removes and returns the specified element from this list. 
	 *
	 * @param element the element to be removed from the list
	 */
	public T remove(T element);

	/**
	 * Removes and returns the element at the specified index in the list.
	 *
	 * @param index the element should be removed from this position in the list
	 * @return the element at that position
	 */
	public T remove(int index);

	/**  
	 * Returns true if this list contains the specified target element. 
	 *
	 * @param target the target that is being sought in the list
	 * @return true if the list contains this element
	 */
	public boolean contains(T target);

	/**
	 * Returns the index of the target in the list or -1 if the target does not exist.
	 * If the list contains duplicates the index returned will be the first occurrence found.
	 * @param target the target that is being sought in the list
	 * @return index of the target or -1 if it does not exist
	 */
	public int indexOf(T target);

	/**  
	 * Returns true if this list contains no elements. 
	 *
	 * @return true if this list contains no elements
	 */
	public boolean isEmpty();

	/**  
	 * Returns the number of elements in this list. 
	 *
	 * @return the integer representation of number of elements in this list
	 */
	public int size();


	/**  
	 * Returns a string representation of this list. 
	 *
	 * @return a string representation of this list
	 */
	public String toString();
}
