package exceptions;

/**
 * Exception which is thrown when we exceed the max queue size
 * 
 * @author Group 7
 *
 */
@SuppressWarnings("serial")
public class exceedQueueSizeException extends RuntimeException{
	public exceedQueueSizeException(){
		System.out.println("List has reached max size");
	}
}
