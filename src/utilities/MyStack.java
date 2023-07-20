package utilities;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

/**
 * Implementation of a stack. Supports all standard operations of a stack.
 * 
 * @author Jaymen
 */
public class MyStack<E> implements StackADT<E> {

	/**
	 * Serial version of the UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * A Arraylist which uses <E> type
	 */
	private MyArrayList<E> stackList;

	/**
	 * Creates a new stack
	 */
	public MyStack() {
		stackList = new MyArrayList<>();
	}
	
	@Override
	public void push(E toAdd) throws NullPointerException {
		if (toAdd == null) {
			throw new NullPointerException();
		}
		stackList.add(toAdd);
	}

	@Override
	public E pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return stackList.remove(stackList.size() - 1);
	}

	@Override
	public E peek() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return stackList.get(stackList.size() - 1);
	}

	@Override
	public void clear() {
		stackList.clear();
	}

	@Override
	public boolean isEmpty() {
		return stackList.isEmpty();
	}
	
	@Override
	public Object[] toArray() {
		return stackList.toArray();
	}

	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray(E[] holder) throws NullPointerException {
		if (holder == null) {
			throw new NullPointerException();
		}
		if (holder.length < stackList.size()) {
			holder = (E[]) new Object[stackList.size()];
		}
		for (int i = 0; i < stackList.size(); i++) {
			holder[i] = stackList.get(stackList.size() - 1 - i);
		}
		return holder;
	}
	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if (toFind == null) {
			throw new NullPointerException();
		}
		return stackList.contains(toFind);
	}
	
	@Override
	public int search(E toFind) {
		for (int i = stackList.size() - 1; i >= 0; i--) {
			if (stackList.get(i).equals(toFind)) {
				return stackList.size() - i;
			}
		}
		return -1;
	}

	@Override
	public Iterator<E> iterator() {
		return new MyStackIterator();
	}

	@Override
	public boolean equals(StackADT<E> that) {
		if (this == that) {
			return true;
		}
		if (that == null || !(that instanceof MyStack)) {
			return false;
		}
		MyStack<E> otherStack = (MyStack<E>) that;
		if (stackList.size() != otherStack.stackList.size()) {
			return false;
		}
		Iterator<E> thisIterator = iterator();
		Iterator<E> otherIterator = otherStack.iterator();
		while (thisIterator.hasNext() && otherIterator.hasNext()) {
			E thisElement = thisIterator.next();
			E otherElement = otherIterator.next();
			if (!thisElement.equals(otherElement)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int size() {
		return stackList.size();
	}

	private class MyStackIterator implements Iterator<E> {
		private int currentIndex = stackList.size() - 1;

		@Override
		public boolean hasNext() {
			return currentIndex >= 0;
		}

		@Override
		public E next() throws NoSuchElementException {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return stackList.get(currentIndex--);
		}
	}
}