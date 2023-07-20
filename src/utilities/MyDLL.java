package utilities;

import java.util.NoSuchElementException;

/**
 * Implementation for MyDLL. Supports all standard operations of a Dynamic Linked List
 * 
 * @author Group 7
 */
public class MyDLL<E> implements ListADT<E> {

	private static final long serialVersionUID = 1L;

	private MyDLLNode<E> head;
	private MyDLLNode<E> tail;

	private int size;

	public MyDLL() {
		size = 0;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public void clear() {
		tail = null;
		head = null;
		size = 0;
	}

	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		
		if (index > this.size()) {
			throw new IndexOutOfBoundsException();
		} else if (index == this.size()) {
			return add(toAdd);
		} else {
			MyDLLNode<E> newNode = new MyDLLNode<E>(toAdd);

			MyDLLNode<E> next = this.getNodeAtIndex(index);
			MyDLLNode<E> prev = this.getNodeAtIndex(index - 1);

			newNode.setNext(next);
			next.setPrev(newNode);
			newNode.setPrev(prev);
			prev.setNext(newNode);
		}

		size++;

		return true;
	}

	@Override
	public boolean add(E toAdd) throws NullPointerException {
		MyDLLNode<E> newNode = new MyDLLNode<E>(toAdd);
		
		if (head == null) {
			head = newNode;
		}

		if (tail != null) {
			tail.setNext(newNode);
			newNode.setPrev(tail);
		}
		tail = newNode;

		size++;

		return true;
	}

	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
		@SuppressWarnings("unchecked")
		Iterator<E> iterator = (Iterator<E>) toAdd.iterator();

		while (iterator.hasNext()) {
			E next = iterator.next();
			this.add(next);
		}

		return true;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		MyDLLNode<E> node = this.getNodeAtIndex(index);

		return node.getElement();
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		
		MyDLLNode<E> old = this.getNodeAtIndex(index);
			
		MyDLLNode<E> next = old.getNext();
		MyDLLNode<E> prev = old.getPrev();
		
		if (next == null && prev == null) {
			head = null;
			tail = null;
		} else if (next == null) {
			tail = prev;
			prev.setNext(null);
		} else if (prev == null) {
			head = next;
			next.setPrev(null);
		} else {
			next.setPrev(prev);
			prev.setNext(next);
		}

		size--;

		return old.getElement();
	}

	@Override
	public E remove(E toRemove) throws NullPointerException {
		E oldElement = null;
		
		Iterator<MyDLLNode<E>> iter = new MyDLLIteratorForNodes();
		
		while (iter.hasNext()) {
			MyDLLNode<E> nextIter = iter.next();
			
			if (nextIter.getElement().equals(toRemove)) {

				MyDLLNode<E> next = nextIter.getNext();
				MyDLLNode<E> prev = nextIter.getPrev();
				
				if (next == null && prev == null) {
					head = null;
					tail = null;
				} else if (next == null) {
					tail = prev;
					prev.setNext(null);
				} else if (prev == null) {
					head = next;
					next.setPrev(null);
				} else {
					next.setPrev(prev);
					prev.setNext(next);
				}

				size--;
				
				oldElement = nextIter.getElement();
				
				break;
			}
		}

		return oldElement;
	}

	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
		if (index > this.size() - 1) {
			throw new IndexOutOfBoundsException();
		}

		MyDLLNode<E> nodeAtIndex = this.getNodeAtIndex(index);
		MyDLLNode<E> newNode = new MyDLLNode<E>(toChange);

		MyDLLNode<E> next = nodeAtIndex.getNext();
		MyDLLNode<E> prev = nodeAtIndex.getPrev();

		if (next == null) {
			tail = newNode;
		} else {
			nodeAtIndex.setNext(null);
			newNode.setNext(next);
		}

		if (prev == null) {
			head = newNode;
		} else {
			nodeAtIndex.setPrev(null);
			newNode.setPrev(prev);
		}

		return nodeAtIndex.getElement();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException {
		Iterator<E> iter = this.iterator();

		while (iter.hasNext()) {
			E next = iter.next();

			if (next.equals(toFind)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
		if (toHold == null) {
			throw new NullPointerException();
		}

		if (toHold.length >= this.size()) {
			Iterator<E> iter = this.iterator();

			int i = 0;
			while (iter.hasNext()) {
				E next = iter.next();

				toHold[i] = next;

				i++;
			}

			return toHold;
		} else {
			return toArray();
		}
	}

	@Override
	public E[] toArray() {
		Iterator<E> iter = this.iterator();
		
		@SuppressWarnings("unchecked")
		E[] toHold = (E[]) new Object[size];

		int i = 0;
		while (iter.hasNext()) {
			E next = iter.next();

			toHold[i] = next;

			i++;
		}

		return toHold;
	}

	@Override
	public Iterator<E> iterator() {
		return new MyDLLIteratorForElements();
	}

	public Iterator<MyDLLNode<E>> nodeIterator() {
		return new MyDLLIteratorForNodes();
	}

	private class MyDLLIteratorForElements implements Iterator<E> {
		private MyDLLNode<E> currentNode;
		
		public MyDLLIteratorForElements() {
			currentNode = head;
		}

		@Override
		public boolean hasNext() {
			if (currentNode == null) {
				return false;
			}
			
			return true;
		}

		@Override
		public E next() throws NoSuchElementException {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			
			MyDLLNode<E> thisNode = currentNode;
			currentNode = thisNode.getNext();

			return thisNode.getElement();
		}
	}
	
	private class MyDLLIteratorForNodes implements Iterator<MyDLLNode<E>> {
		private MyDLLNode<E> currentNode;
		
		public MyDLLIteratorForNodes() {
			currentNode = head;
		}


		@Override
		public boolean hasNext() {
			if (currentNode == null) {
				return false;
			}
			
			return true;
		}

		@Override
		public MyDLLNode<E> next() throws NoSuchElementException {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			
			MyDLLNode<E> thisNode = currentNode;
			currentNode = thisNode.getNext();

			return thisNode;
		}
	}

	public MyDLLNode<E> getNodeAtIndex(int index) throws IndexOutOfBoundsException {
		if (index >= this.size()) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			return head;
		} else if (index == this.size() - 1) {
			return tail;
		}

		Iterator<MyDLLNode<E>> iter = new MyDLLIteratorForNodes();
		MyDLLNode<E> myNode = null;
		
		int i = 0;
		while (iter.hasNext()) {
			MyDLLNode<E> next = iter.next();
			
			if (i == index) {
				myNode = next;
				break;
			}
			i++;
		}

		return myNode;
	}

}
