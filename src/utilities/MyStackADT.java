package utilities;

import java.io.Serializable;
import java.util.EmptyStackException;

public interface MyStackADT<E> extends Serializable
{

	public void push( E toAdd ) throws NullPointerException;

	public E pop() throws EmptyStackException;

	public E peek() throws EmptyStackException;

	public void clear();

	public boolean isEmpty();

	public Object[] toArray();

	public E[] toArray( E[] holder ) throws NullPointerException;

	public boolean contains( E toFind ) throws NullPointerException;

	public int search( E toFind );

	public Iterator<E> iterator();

	public boolean equals( StackADT<E> that );
	
	public int size();
}