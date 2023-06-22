package utilities;

import java.io.Serializable;
import java.util.EmptyStackException;

<<<<<<< HEAD
/**
 * This Stacks incorporates all the standard Stack operations, including methods to help flexibility and usage
 * 
 * Created On: June 21, 2023
 * 
 * @author Group7
 * @version 1.0
 */

public interface MyStackADT<E> extends Serializable
{
=======
public interface MyStackADT<E> extends Serializable {
>>>>>>> branch 'master' of https://github.com/CanadianMRE/ITSDAssignment2.git

<<<<<<< HEAD
	/**
	 * Appends an item on the stack
	 * @param toAdd
	 * -this is the item being appended
	 * @throws NullPointerException
	 * -thrown when attempting to add a null element
	 */
	
	public void push( E toAdd ) throws NullPointerException;
	
	
	/**
	 * removes the top most object in the stack
	 * @return
	 * -the object removed
	 * @throws EmptyStackException
	 * -thrown when the is nothing to remove
	 */
=======
	public void push(E toAdd) throws NullPointerException;
>>>>>>> branch 'master' of https://github.com/CanadianMRE/ITSDAssignment2.git

	public E pop() throws EmptyStackException;
	
	
	/**
	 * Look at the top most object. Does not remove said object
	 * @return 
	 * -the object we are looking at
	 * @throws EmptyStackException
	 * -thrown when there is nothing in the stack to look at
	 */

	public E peek() throws EmptyStackException;
	
	
	/**
	 * clears any and all objects in the stack
	 */

	public void clear();
<<<<<<< HEAD
	
	
	/**
	 * Verify if stack is empty or not
	 * @return
	 * -returns true if stack is empty
	 */
	
=======

>>>>>>> branch 'master' of https://github.com/CanadianMRE/ITSDAssignment2.git
	public boolean isEmpty();
	
	
	/**
	 * Takes the list elements and places them into an array
	 * @return
	 * -an array with the list elements in sequence
	 */

	public Object[] toArray();
	
	
	/**
	 * Designates an array for the stack elements to be stored
	 * @param holder
	 * -asseses if array is large enough, if it isn't it creates a new array of same runtime type
	 * @return
	 * -an array with the stack elements
	 * @throws NullPointerException
	 * -thrown when array is null
	 */

<<<<<<< HEAD
	
	public E[] toArray( E[] holder ) throws NullPointerException;
	
	
	
	/**
	 * checks if the element is in the list. 
	 * @param toFind
	 * -element to be searched
	 * @return
	 * -true if element is in the list
	 * @throws NullPointerException
	 * -thrown if the element searched for is null
	 */
=======
	public E[] toArray(E[] holder) throws NullPointerException;
>>>>>>> branch 'master' of https://github.com/CanadianMRE/ITSDAssignment2.git

<<<<<<< HEAD
	public boolean contains( E toFind ) throws NullPointerException;
	
	
	
	/**
	 * Finds the element in the stack and returns its location in the stack. Top of stack starts at 1. 
	 * @param toFind
	 * -the object searched for
	 * @return
	 * -the location/index of the element in the stack. Starts at 1 from the top of the stack down. returns -1
	 * if object isn't in the stack
	 */
	
	public int search( E toFind );
	
	
	
	/**
	 * Iterates through the elements of the stack in sequence
	 * @return
	 * -an iterator through the elements of the stack in sequence.
	 */
=======
	public boolean contains(E toFind) throws NullPointerException;

	public int search(E toFind);
>>>>>>> branch 'master' of https://github.com/CanadianMRE/ITSDAssignment2.git

	public Iterator<E> iterator();
	
	
	
	/**
	 * Evaluates if 2 stack ADT's are equal (same objects, same order)
	 * @param that
	 * -the stack ADT we are comparing to this stack 
	 * @return
	 * -true if the stacks are equal.
	 */

<<<<<<< HEAD
	public boolean equals( StackADT<E> that );
	
	
	
	/**
	 * used to verify size/depth of the stack bucket
	 * @return
	 * -size of the stack as an integer
	 */
	
=======
	public boolean equals(StackADT<E> that);

>>>>>>> branch 'master' of https://github.com/CanadianMRE/ITSDAssignment2.git
	public int size();
}