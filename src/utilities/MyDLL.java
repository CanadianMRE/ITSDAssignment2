package utilities;

import java.util.NoSuchElementException;

public class MyDLL<E> implements ListADT<E> {


	/**
	 * 
	 */
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
	}

	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		
		if (index > size) {
			throw new IndexOutOfBoundsException();
		} else if (index == size) {	
			return add(toAdd);
		} else if (index == 0) {	
			MyDLLNode<E> newNode = new MyDLLNode<E>(toAdd);
			
			newNode.setNext(head);
			head.setPrev(newNode);
			
			head = newNode;
		} else {	
			MyDLLNode<E> newNode = new MyDLLNode<E>(toAdd);
			
			MyDLLNode<E> next = this.getNodeAtIndex(index + 1);
			MyDLLNode<E> prev = this.getNodeAtIndex(index);
			
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
		
		if (node != null) {
			return node.getElement();
		}
		
		return null;
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E remove(E toRemove) throws NullPointerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
		MyDLLNode<E> nodeAtIndex = this.getNodeAtIndex(index);
		
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Iterator<E> iterator() {
		return new MyDLLIterator();
	}
	
	private class MyDLLIterator implements Iterator<E> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex <= size() - 1;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            
            currentIndex++;       
            
            return get(currentIndex - 1);
        }
    }
	
	private MyDLLNode<E> getNodeAtIndex(int index) throws IndexOutOfBoundsException {
		if (index == 0) {
			return head;
		} else if (index == this.size() - 1) {
			return tail;
		} else if (index >= this.size() - 1) {
			throw new IndexOutOfBoundsException();
		}
		
		if (head == null) {
			return null;
		}
		
		MyDLLNode<E> currentNode = head;
		MyDLLNode<E> goalNode = null;
		
		int i = 0;
		while(i < index) {
			i++;

			currentNode = currentNode.getNext();
		}
		
		if (currentNode != null) {
			goalNode = currentNode;
		}
		
		return goalNode;
	}
	
}
