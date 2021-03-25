package listImplementation.arrayList;

import listImplementation.ListADT;
import listImplementation.exceptions.*;
import listImplementation.exceptions.IndexOutOfBoundsException;

import java.util.Arrays;

/**
 * ArrayList represents an array implementation of a list. The front of
 * the list is kept at array index 0. This class will be extended
 * to create a specific kind of list.
 *
 * @author Java Foundations
 * @version 4.0
 */
public class ArrayList<T> implements ListADT<T>
{
	private final static int DEFAULT_CAPACITY = 100;
	private final static int NOT_FOUND = -1;

	protected int rear;
	protected T[] list;

	/**
	 * Creates an empty list using the default capacity.
	 */
	public ArrayList()
	{
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Creates an empty list using the specified capacity.
	 *
	 * @param initialCapacity the integer value of the size of the array list
	 */
	public ArrayList(int initialCapacity)
	{
		rear = 0;
		list = (T[])(new Object[initialCapacity]);
	}

	/**
	 * Creates a new array to store the contents of this list with
	 * twice the capacity of the old one. Called by descendant classes
	 * that add elements to the list.
	 */
	protected void expandCapacity()
	{
		// completed just as done in the stack version
		list = Arrays.copyOf(list, list.length * 2);
	}

	/**
	 * Adds the specified element to the rear of this list.
	 *
	 * @param element the element to be added to the list
	 */
	public void add(T element)
	{
		if (rear == list.length) {
			expandCapacity();
		}
		list[rear] = element;
		rear++;
	}

	/**
	 * Adds the specified element at the position given by index.
	 * The front of the list is position 0.
	 * Throws an ElementNotFoundException if the target is not found.
	 *
	 * @param element the element to be added at the index
	 * @param target  the target that the element is to be added after
	 */
	public void addAfter(T element, T target)
	{
		if (size() == list.length) {
			expandCapacity();
		}

		int scan = 0;

		// find the insertion point
		while (scan < rear && !target.equals(list[scan]))
			scan++;

		if (scan == rear) {
			throw new ElementNotFoundException("ArrayList");
		}

		scan++;  // add the element after the matched one

		// shift elements up one to the right
		shiftElementsRight(scan);

		// insert element
		list[scan] = element;
		rear++;
	}

	/**
	 * Adds the specified element at the position given by index.
	 * The from of the list is position 0.
	 *
	 * @param element the element to be added after the target
	 * @param index   the position in the list where the element will be added
	 *
	 * @throws EmptyCollectionException if the list is empty
	 * @throws IndexOutOfBoundsException if the index is less that 0 or beyond the size of the list
	 *
	 */
	public void add(T element, int index) {


		// if empty and index >0, throw EmptyCollectionException
		if (isEmpty() && index > 0) {
			throw new EmptyCollectionException("ArrayList");
		}

		// if index is off current end, then throw IndexOutOfBoundsException
		if (index > rear || index < 0) {
			throw new IndexOutOfBoundsException("ArrayList", index);
		}

		// TODO: complete this after finishing shiftElementsRight() below
		// remember to expand the capacity, shift elements to the right,
		// set the list at index to element,
		// and update rear


	}

	/**
	 * Removes and returns the specified element.
	 *
	 * @param  element the element to be removed and returned from the list
	 * @return the removed elememt
	 * @throws ElementNotFoundException if the element is not in the list
	 */
	public T remove(T element)
	{
		T result;
		int index = indexOf(element);

		if (index == NOT_FOUND) {
			throw new ElementNotFoundException("ArrayList");
		}

		result = remove(index);

		return result;
	}

	/**
	 * Removes and returns the element at the specified index in the list.
	 *
	 * @param index the element should be removed from this position in the list
	 * @return the element at that position
	 * @throws IndexOutOfBoundsException if no element at that position in the list
	 */
	public T remove(int index) {

		// throw exception if index is beyond the last element at the end
		if (index > rear || index < 0) {
			throw new IndexOutOfBoundsException("ArrayList", index);
		}

		T result = null;

		// TODO: complete this method
		// assign the value in the array at index to the result (declared above),
		// shift elements beyond index to the left,
		// decrement rear,
		// return the result




		return result;   //keep this!
	}

	/**
	 * When an element is at the given index is being removed,
	 * all elements further down in the array are shifted to the left
	 * by one.
	 *
	 * @param index of element that is being removed
	 */
	private void shiftElementsLeft(int index) {
		for (int scan = index; scan < rear-1; scan++) {
			list[scan] = list[scan + 1];
		}
	}

	/**
	 * When an element is being inserted into the array at a given index,
	 * all elements, including the one at the index must be shifted to the right
	 * by one.
	 *
	 * @param index of where new element will be inserted
	 */
	private void shiftElementsRight(int index) {
		if (index > rear || index < 0){
			throw new IndexOutOfBoundsException("ArrayList", index);
		}

		// TODO: finish this method.
		// Hint: consider where (which end) to begin moving elements down
		//       Use shiftElementsLeft just above as a guide


	}

	/**
	 * Returns true if this list contains the specified element.
	 *
	 * @param target the target element
	 * @return true if the target is in the list, false otherwise 
	 */
	public boolean contains(T target)
	{
		return (indexOf(target) != NOT_FOUND);
	}

	/**
	 * Returns the array index of the specified element, or the
	 * constant NOT_FOUND if it is not found.
	 *
	 * @param target the target element
	 * @return the index of the target element, or the 
	 *         NOT_FOUND constant
	 */
	public int indexOf(T target)
	{
		int scan = 0;           // next index in array
		int result = NOT_FOUND;

		if (!isEmpty()) {
			while (result == NOT_FOUND && scan < rear) {
				if (target.equals(list[scan])) {
					result = scan;
				} else {
					scan++;
				}
			}
		}

		return result;
	}

	/**
	 * Returns true if this list is empty and false otherwise. 
	 *
	 * @return true if the list is empty, false otherwise
	 */
	public boolean isEmpty()
	{
		// extremely similar to what you had to do for the ArrayStack
		return rear == 0;
	}

	/**
	 * Returns the number of elements currently in this list.
	 *
	 * @return the number of elements in the list
	 */
	public int size()
	{
		// extremely similar to what you had to do for the ArrayStack
		return rear;

	}

	/**
	 * Returns a string representation of this list. 
	 * 
	 * @return the string representation of the list
	 */
	@Override
	public String toString()
	{
		StringBuilder returnStr = new StringBuilder();
		returnStr.append(size());
		returnStr.append(" elements (front to back): ");
		for (int i = 0; i < rear; i++) {
			returnStr.append(list[i]);
			returnStr.append(" ");
		}

		return returnStr.toString().trim();  // easy way to get rid of the last space at the end
	}



}
