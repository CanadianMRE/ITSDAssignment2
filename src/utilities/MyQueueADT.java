package utilities;

import java.io.Serializable;

import exceptions.EmptyQueueException;

public interface MyQueueADT<E> extends Serializable
{
	
	public void enqueue( E toAdd ) throws NullPointerException;
	
	
	public E dequeue() throws EmptyQueueException;
	
	
	public E peek() throws EmptyQueueException;
	
	public void dequeueAll();
	
	
	public boolean isEmpty();
	
	
	public Iterator<E> iterator();
	
	
	public boolean equals( QueueADT<E> that );
	
	public Object[] toArray();

	
	public boolean isFull();
	
	public int size();
}