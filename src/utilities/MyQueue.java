package utilities;

import exceptions.EmptyQueueException;
import java.io.*;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyQueue<E> implements QueueADT<E> {
  
	private static final long serialVersionUID = 1L;
	private ArrayList<E> elements;

    public MyQueue() {
        elements = new ArrayList<>();
    }
    
    @Override
    public void enqueue(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException();
        }
        elements.add(toAdd);
    }
	
	
    @Override
    public E dequeue() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return elements.remove(0);
    }

	
    @Override
    public E peek() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return elements.get(0);
    }
	
	
    @Override
    public void dequeueAll() {
        elements.clear();
    }
	
	
    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

	
	
    @Override
    public utilities.Iterator<E> iterator() {
        return new MyQueueIterator();
    }
	
    @Override
    public boolean equals(QueueADT<E> that) {
        if (this == that) {
            return true;
        }
        if (that == null || !(that instanceof MyQueue)) {
            return false;
        }
        MyQueue<E> otherQueue = (MyQueue<E>) that;
        return elements.equals(otherQueue.elements);
    }
	
	
    @Override
    public Object[] toArray() {
        return elements.toArray();
    }

	
    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        return elements.toArray(holder);
    }
	
    @Override
    public boolean isFull() {
        return false; // Assuming the queue is never full
    }
	
	
    @Override
    public int size() {
        return elements.size();
    }
    
    private class MyQueueIterator implements utilities.Iterator<E> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < elements.size();
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return elements.get(currentIndex++);
        }
    }
    
}
