package utilities;

import exceptions.EmptyQueueException;
import java.io.*;

/**
 * This Queue incorporates all the standard Queue operations, including methods to help flexibility and usage
 * 
 * Created On: June 21,2023
 * 
 * Queue.java
 * 
 * @author Group 7
 * @version 1.0
 */
public interface MyQueueADT<E> extends Serializable {
	/**
	 * Enqueue adds the last item to the last position in the queue. code>null</code>
	 * values will be thrown with NullPointerExceptions and will not be permitted
	 * 
	 * 
	 * @param toAdd parameter contains the element to be added to the queue
	 * @throws NullPointerException will happen when a <code>null</code> object is
	 *                              attempted as a parameter
	 */
	public void enqueue(E toAdd) throws NullPointerException;

	/**
	 * Dequeue deletes the first item in the Queue.
	 * 
	 * @return returns the first element in the Queue.
	 * @throws EmptyQueueException occurs when the length of the queue is zero.
	 */
	public E dequeue() throws EmptyQueueException;

	/**
	 * Peek returns a reference to the first element within the queue and does not
	 * remove from the queue
	 * 
	 * @return the first item in the queue.
	 * @throws EmptyQueueException raised when the queue's length is zero (0).
	 */
	public E peek() throws EmptyQueueException;

	/**
	 * dequeueAll deletes every element within the queue.
	 */
	public void dequeueAll();

	/**
	 * Returns <code>true</code> when the queue is empty.
	 * 
	 * @return <code>true</code> when the length of the queue is equal to zero.
	 */
	public boolean isEmpty();

	/**
	 * This function provides an iterator that traverses the elements in this queue
	 * in the correct order.
	 * 
	 * @return An iterator that sequentially iterates over the elements in this
	 *         queue according to their proper sequence.
	 */
	public Iterator<E> iterator();

	/**
	 * 
	 * Compares two Queue ADT's. Returns true if the two queues contain the same
	 * items in the same sequential order
	 * 
	 * @param that the Queue ADT to be compared to this queue.
	 * @return <code>true</code> if the queues are equal.
	 */
	public boolean equals(MyQueueADT<E> that);

	/**
	 * This method returns an array that includes all the elements in this list,
	 * following their proper sequence. It adheres to the general contract of the
	 * Collection.toArray method.
	 * 
	 * @return an array containing all of the elements in this list in proper
	 *         sequence.
	 */
	public Object[] toArray();

	/**
	 * This method returns an array that includes all the elements in this list,
	 * following their proper sequence.;
	 * 
	 * The returned array's type is equal to the specified array type and follows
	 * the general contract of the Collection.toArray(Object[]) method.
	 * 
	 * @param toHold
	 * 
	 *               contains the array where the items of this queue are stored if
	 *               there is sufficient space; if not, a new array of the same
	 *               runtime type is created for the queue
	 * 
	 * @return an array that contains the items of the queue.
	 * @throws NullPointerException if the array is empty and null.
	 */
	public E[] toArray(E[] holder) throws NullPointerException;

	/**
	 * (Optional Method) Returns true if the number of items in the queue equals the
	 * length. This function is only called when a you require a queue of fixed
	 * length
	 * 
	 * @return <code>true</code> if queue is full.
	 */
	public boolean isFull();

	/**
	 * Returns the length of the current queue as an int value.
	 * 
	 * @return the current size to the queue as an int value.
	 */
	public int size();
}
