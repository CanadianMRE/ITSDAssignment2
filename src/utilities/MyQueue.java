package utilities;

import java.util.NoSuchElementException;
import exceptions.EmptyQueueException;

public class MyQueue<E> implements QueueADT<E> {

	private static final long serialVersionUID = 1L;

	private MyDLL<E> queueList;

	public MyQueue() {
		queueList = new MyDLL<E>();
	}

	@Override
	public void enqueue(E toAdd) throws NullPointerException {
		if (toAdd == null) {
			throw new NullPointerException();
		}
		queueList.add(toAdd);
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
		MyQueue<E> otherQueue = (MyQueue<E>) that;
		return queueList.equals(otherQueue.queueList);
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
