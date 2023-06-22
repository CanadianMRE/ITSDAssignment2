package exceptions;

@SuppressWarnings("serial")
public class EmptyQueueException extends RuntimeException {
	public EmptyQueueException() {
//		System.out.println("The Queue is empty");
	}
}