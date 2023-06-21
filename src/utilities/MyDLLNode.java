package utilities;

import java.io.Serializable;

public class MyDLLNode<E> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private E element;
	private MyDLLNode<E> next;
	private MyDLLNode<E> prev;
	
	public MyDLLNode(E element, MyDLLNode<E> prev, MyDLLNode<E> next)
	{
		this.element = element;
		this.prev = prev;
		this.next = next;
	}

	
	public Object getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	
	public MyDLLNode<E> getNext() {
		return next;
	}

	
	public void setNext(MyDLLNode<E> next) {
		this.next = next;
	}

	public MyDLLNode<E> getPrev() {
		return prev;
	}

	public void setPrev(MyDLLNode<E> prev) {
		this.prev = prev;
	}

}
