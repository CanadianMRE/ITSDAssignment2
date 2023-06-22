package utilities;

import java.util.NoSuchElementException;
import exceptions.EmptyQueueException;
import exceptions.exceedQueueSizeException;

public class MyQueue<E> implements QueueADT<E> {

	private static final long serialVersionUID = 1L;

	private MyDLL<E> queueList;
	private boolean adjustable;
	private int maxSize;

	public MyQueue() {
		queueList = new MyDLL<E>();
		adjustable = true;
	}
	
	public MyQueue (int maxSize) {
		queueList = new MyDLL<E>();
		adjustable = false;
		this.maxSize = maxSize;
	}

	@Override
	public void enqueue(E toAdd) throws NullPointerException,exceedQueueSizeException {
		if (toAdd == null) {
			throw new NullPointerException();
		}
		System.out.println(maxSize);
		if(adjustable) {
			queueList.add(toAdd);
		}else if(maxSize>this.size()) {
			queueList.add(toAdd);
		}else{
			throw new exceedQueueSizeException();
		}
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException();
		}
		return queueList.remove(0);
	}

	@Override
	public E peek() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException();
		}
		return queueList.get(0);
	}

	@Override
	public void dequeueAll() {
		queueList.clear();
	}

	@Override
	public boolean isEmpty() {
		return queueList.isEmpty();
	}

	@Override
	public boolean equals(QueueADT<E> that) {
		
		if (this == that) {
			return true;
		}
		if (that == null || !(that instanceof MyQueue)) {
			return false;
		}
		if(this.size()!=that.size()) {
			return false;
		}
		
		Iterator<E> it = this.iterator();
		Iterator<E> that1 = that.iterator();
		
		while(it.hasNext()&&that1.hasNext()) {
			E test = it.next();
			E test1 = that1.next();
			if(!test.equals(test1)) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public Object[] toArray() {
		return queueList.toArray();
	}

	@Override
	public E[] toArray(E[] holder) throws NullPointerException {
		return queueList.toArray(holder);
	}

	@Override
	public boolean isFull() {
	
		
		
		if(maxSize<=this.size()) {
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		return queueList.size();
	}

	@Override
	public Iterator<E> iterator() {
		return new MyQueueIterator();
	}

	private class MyQueueIterator implements Iterator<E> {
		private int currentIndex = 0;

		@Override
		public boolean hasNext() {
			return currentIndex < queueList.size();
		}

		@Override
		public E next() throws NoSuchElementException {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return queueList.get(currentIndex++);
		}
	}

}
