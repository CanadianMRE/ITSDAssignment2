package exceptions;

/**
 * Exception which is thrown on an empty queue.
 * 
 * @author Group 7
 *
 */
@SuppressWarnings("serial")
public class EmptyQueueException extends RuntimeException {
	public EmptyQueueException() {
//		System.out.println("The Queue is empty");
	}
}