package exceptions;

@SuppressWarnings("serial")
public class exceedQueueSizeException extends RuntimeException{
	public exceedQueueSizeException(){
		System.out.println("List has reached max size");
	}
}
