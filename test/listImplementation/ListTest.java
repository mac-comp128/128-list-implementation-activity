package listImplementation;

import listImplementation.arrayList.ArrayList;
import listImplementation.exceptions.ElementNotFoundException;
import listImplementation.linkedList.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This test class works for both LinkedLists and ArrayLists.
 *
 * Created by shoop on 4/3/17, Modified by Bret Jackson 2/19/20.
 */
public class ListTest {
    ArrayList<String> aList;
    ArrayList<String> aLittleList;
    LinkedList<String> lList;

    @BeforeEach
    public void makeLists() {
        aList = new ArrayList<String>();
        aLittleList = new ArrayList<String>(2);

        lList = new LinkedList<String>();
    }

    @Test
    public void allArrayTests() {
        testAddElement(aList);
        testAddAtIndex(aLittleList);
        aLittleList = new ArrayList<String>(2);
        testRemoveAtIndex(aLittleList);
    }

    @Test
    public void allLinkedTests() {
        testAddElement(lList);
        lList = new LinkedList<String>();
        testAddAtIndex(lList);
        lList = new LinkedList<String>();
        testRemoveAtIndex(lList);
    }

    /*
         This tests add(T elem), addAfter(), and remove(T elem), including throwing the exception when empty()
     */
    public void testAddElement(ListADT<String> theList) {
        System.out.println("Test add(T elem)  ========================");
        theList.add("alpha");
        theList.add("beta");
        theList.add("delta");
        theList.addAfter("chicago", "beta");

        System.out.println(theList);  // so you can see what List toString does
        // Note two ways we can check for proper representation of the string
        assertEquals("4 elements (front to back): alpha beta chicago delta",
                theList.toString());

        // you could try this below, but above gives you more information
        assertTrue(theList.toString().equals(
                "4 elements (front to back): alpha beta chicago delta"));

        assertEquals(theList.size(), 4);
        assertFalse(theList.isEmpty());
        assertTrue(theList.contains("chicago"));

        // Now see if remove(), which is provided, seems to work
        String last = theList.remove("delta");
        assertEquals("delta", last);

        last = theList.remove("chicago"); // remove "chicago"
        last = theList.remove("beta"); // remove "beta"
        last = theList.remove("alpha"); // remove "alpha"
        assertEquals("alpha", last) ;
        assertTrue(theList.isEmpty());  // make sure we think it is empty

        try {
            last = theList.remove("Gamma"); // exception
        } catch (ElementNotFoundException e) {
            if (theList instanceof ArrayList) {
                assertEquals(e.getMessage(), "The target element is not in this ArrayList");
            } else {
                assertEquals(e.getMessage(), "The target element is not in this LinkedList");
            }
        }

    }


    /**
     * Tests add method using an index.
     * @param theList
     */
    public void testAddAtIndex(ListADT<String> theList) {
        System.out.println("Test add(int index) ========================");
        theList.add("delta");
        System.out.println(theList);  // so you can see what List toString does
        theList.add("beta", 0);
        System.out.println(theList);  // so you can see what List toString does
        theList.add("alpha", 0);         // will expand capacity of ArrayList here
        System.out.println(theList);  // so you can see what List toString does

        theList.addAfter("chicago", "beta");   // this should work if shiftElementsRight() is correct

        System.out.println(theList);  // so you can see what List toString does
        // Note two ways we can check for proper representation of the string
        assertEquals( "4 elements (front to back): alpha beta chicago delta",
                theList.toString() );
        assertEquals(theList.size(), 4);
        assertFalse(theList.isEmpty());

    }


    public void testRemoveAtIndex(ListADT<String> theList) {

        System.out.println("Test remove(int index) ========================");
        theList.add("delta", 0);
        theList.add("beta", 0);
        theList.add("alpha", 0);         // will expand capacity of ArrayList here
        theList.addAfter("chicago", "beta");   // this should work if shiftElementsRight() is correct

        System.out.println(theList);
        String removedElement = theList.remove(0);
        System.out.println(theList);
        assertEquals(removedElement, "alpha");
        assertEquals("3 elements (front to back): beta chicago delta",
                theList.toString());
        assertEquals(theList.size(), 3);
        assertFalse(theList.isEmpty());

        removedElement = theList.remove(1);
        System.out.println(theList);
        assertEquals(removedElement, "chicago");
        assertEquals("2 elements (front to back): beta delta",
                theList.toString());
        assertEquals(theList.size(), 2);

        removedElement = theList.remove(1);
        System.out.println(theList);
        assertEquals(removedElement, "delta");
        assertEquals("1 elements (front to back): beta",
                theList.toString());
        assertEquals(theList.size(), 1);

        // throw the exceptions for these
        String listType;
        if (theList instanceof ArrayList) {
            listType = "ArrayList";
        } else {
            listType = "LinkedList";
        }
        try {
            theList.remove(3);
        } catch (IndexOutOfBoundsException e) {
            assertEquals(e.getMessage(), "The index 3 is out of bounds for this " + listType);
        }

        try {
            theList.remove(6);
        } catch (IndexOutOfBoundsException e) {
            assertEquals(e.getMessage(), "The index 6 is out of bounds for this " + listType);
        }
    }
}
