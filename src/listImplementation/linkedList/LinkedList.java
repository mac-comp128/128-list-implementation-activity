package listImplementation.linkedList;

import listImplementation.ListADT;
import listImplementation.exceptions.*;
import listImplementation.exceptions.IndexOutOfBoundsException;

/**
 * LinkedList represents a linked implementation of a list.
 * 
 * @author Java Foundations
 * @version 4.0
 */
public class LinkedList<T> implements ListADT<T>
{
	protected int count;
	protected LinearNode<T> head, tail;


	/**
	 * Creates an empty list.
	 */
	public LinkedList()
	{
		count = 0;
		head = tail = null;
	}
	

	/**
	 * Adds the specified element to the rear of this list.
	 *
	 * @param element the element to be added to the list
	 */
	public void add(T element)
	{
		LinearNode<T> newNode = new LinearNode<T>(element);
		if (isEmpty()) {
			head = newNode;
		} else {
			tail.setNext(newNode);
		}

		tail = newNode;
		count++;
	}


	/**
	 * Adds the specified element to this list after the given target.
	 *
	 * @param  element the element to be added to this list
	 * @param  target the target element to be added after
	 * @throws ElementNotFoundException if the target is not found
	 */
	public void addAfter(T element, T target)
	{
		LinearNode<T> current = head; // Current keeps track of the current node as we iterate
		boolean found = false; // flag to specify if we have found target yet as we iterate

		// TODO: complete this method
		// while current is not null,
		//   if current's element equals target:
		//       make a new node, set new node's next to current's next, and
		//       set current's next to the new node
		//		 if current is the tail, reset the tail to be the new node.
		//       set found to true
		//       increment count
		//       break out of while loop
		//   set current = current's next



		if (!found) {
			throw new ElementNotFoundException("LinkedList");
		}
	}


	/**
	 * Adds the specified element at the position given by index.
	 * The front of the list is position 0.
	 *
	 * @param element the element to be added at the index
	 * @param index   the position in the list where the element will be added
	 *  @throws IndexOutOfBoundsException if the element is off the end of the list
	 *  @throws EmptyCollectionException if the list is empty
	 */
	public void add(T element, int index) {
		// if empty and index >0, throw EmptyCollectionException
		if (isEmpty() && index > 0) {
			throw new EmptyCollectionException("LinkedList");
		}

		// if index is off current end, then throw ElementNotFoundException
		if (index > count || index < 0) {
			throw new IndexOutOfBoundsException("LinkedList", index);
		}

		// if index equals count, then use add(elem)
		if (index == count) {
			add(element); // more efficient to directly access the tail than iterate to it.
		} else {
			// add somewhere in the middle
			// iterate through each node until you reach the spot to insert
			LinearNode<T> previous = null;
			LinearNode<T> current = head;  // current node as we iterate
			int nodeNum = 0;               // current node's number

			// go down list until nodeNum equals index
			while (current != null) {
				// TODO: finish this part
				// If nodeNum equals index:
				// 		then make a new LinearNode,
				// 		If current is the head, reset head to be the new node
				// 		Otherwise, set the link for previous' next,

				//		Set the next link for the new LinearNode
				// 		update count and break out of while loop




				previous = current;
				current = current.getNext();
				nodeNum++;
			}
		}
	}

	/**
	 * Removes the first instance of the specified element from this
	 * list and returns a reference to it. Throws an EmptyCollectionException 
	 * if the list is empty. Throws a ElementNotFoundException if the 
	 * specified element is not found in the list.
	 *
	 * @param  targetElement the element to be removed from the list
	 * @return a reference to the removed element
	 * @throws ElementNotFoundException if the target element is not found
	 */
	public T remove(T targetElement) throws ElementNotFoundException
	{
		boolean found = false;
		LinearNode<T> previous = null;
		LinearNode<T> current = head;

		while (current != null && !found) {
			if (targetElement.equals(current.getElement()))
				found = true;
			else {
				previous = current;
				current = current.getNext();
			}
		}

		if (!found) {
			throw new ElementNotFoundException("LinkedList");
		}

		if (size() == 1) { // only one element in the list
			head = tail = null;
		} else if (current.equals(head)) {  // target is at the head
			head = current.getNext();
		} else if (current.equals(tail))  // target is at the tail
		{
			tail = previous;
			tail.setNext(null);
		}
		else {  // target is in the middle
			previous.setNext(current.getNext());
		}

		count--;

		return current.getElement();
	}

	/**
	 * Removes and returns the element at the specified index in the list.
	 *
	 * @param index the element should be removed from this position in the list
	 * @return the element at that position
	 * @throws EmptyCollectionException if the list is empty
	 * @throws IndexOutOfBoundsException if the index is beyond the size of the list
	 *
	 */
	public T remove(int index) {
		if (isEmpty()) {
			throw new EmptyCollectionException("ArrayList");
		}

		// if index is off current end, then throw IndexOutOfBoundsException
		if (index > count || index < 0) {
			throw new IndexOutOfBoundsException("LinkedList", index);
		}

		LinearNode<T> previous = null;
		LinearNode<T> current = head;
		int nodeNum = 0;

		// go down list until nodeNum equals index
		while (current != null) {
			if (nodeNum == index) {
				// We have reached the index for the current node we are trying to remove

				// TODO: finish this part
				// If we are trying to remove the head, reset head to be the next node in the list
				// If we are trying to remove the tail:
				//		reset tail to be the previous node
				//		reset the previous node's next link to be null
				// Otherwise, set previous' next link to be current's next node

				// Decrement the count
				// return current's element




			}
			previous = current;
			current = current.getNext();
			nodeNum++;
		}


		return null;   // temp
	}

	/**
	 * Returns true if the specified element is found in this list and 
	 * false otherwise. Throws an EmptyCollectionException if the list 
	 * is empty.
	 *
	 * @param  targetElement the element that is sought in the list
	 * @return true if the element is found in this list
	 * @throws EmptyCollectionException if the list is empty
	 */
	public boolean contains(T targetElement) throws EmptyCollectionException 
	{
		return (indexOf(targetElement) != -1);
	}

	/**
	 * Returns the index of the target in the list or -1 if the target does not exist.
	 * If the list contains duplicates the index returned will be the first occurrence found.
	 * @param target the target that is being sought in the list
	 * @return index of the target or -1 if it does not exist
	 */
	public int indexOf(T target) throws EmptyCollectionException
	{
		LinearNode<T> current = head;
		int index = 0;
		while (current != null) {
			if (target == current.getElement()){
				return index;
			}
			index++;
			current = current.getNext();
		}
		return -1;
	}

	/**
	 * Returns true if this list is empty and false otherwise.
	 *
	 * @return true if the list is empty, false otherwise
	 */
	public boolean isEmpty()
	{
		return count == 0;
	}

	/**
	 * Returns the number of elements in this list.
	 *
	 * @return the number of elements in the list
	 */
	public int size()
	{
		return count;
	}

	/**
	 * Returns a string representation of this list.
	 *
	 * @return a string representation of the list    
	 */
	public String toString()
	{
		StringBuilder returnStr = new StringBuilder();
		returnStr.append(size());
		returnStr.append(" elements (front to back): ");

		LinearNode<T> current = head;

		while (current != null) {
			returnStr.append(current.getElement().toString());
			returnStr.append(" ");
			current = current.getNext();
		}
		return returnStr.toString().trim();  // easy way to get rid of the last space at the end
	}


}


