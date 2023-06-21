package utilities;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyArrayList<E> implements ListADT<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private E[] elements;
	private int size;
	
	@SuppressWarnings("unchecked")
	public MyArrayList() {
		elements = (E[]) new Object[0];
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		for (int i = this.size() - 1; i > 0; i--) {
			this.elements[i] = null;
		}
		this.size = 0;
	}

	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		
		if (index > this.size()) {
			throw new IndexOutOfBoundsException();
		}
		
		@SuppressWarnings("unchecked")
		E[] newArray = (E[]) new Object[this.elements.length + 1];
		
		Boolean inserted = false;
		for (int i = 0; i < this.size() + 1; i++) {
			if (i == index) {
				newArray[i] = toAdd;
				inserted = true;
			} else if (inserted) {
				newArray[i] = this.elements[i - 1];
				this.elements[i - 1] = null;
			} else {
				newArray[i] = this.elements[i];
				this.elements[i] = null;
			}
		}
		
		size++;
		
		elements = newArray;
		
		
		return true;
	}

	@Override
	public boolean add(E toAdd) throws NullPointerException {
		return this.add(this.size(), toAdd);
	}

	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
		@SuppressWarnings("unchecked")
		Iterator<E> iterator = (Iterator<E>) toAdd.iterator();
		 
        // while loop
        while (iterator.hasNext()) {
        	this.add(iterator.next());
        }
		
		return true;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException { 
		return elements[index];
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		
		int newIndex = this.elements.length - 1;
		
		if (newIndex < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		@SuppressWarnings("unchecked")
		E[] newArray = (E[]) new Object[newIndex];
		E old = elements[index];
		
		Boolean removed = false;
		for (int i = 0; i < this.size(); i++) {
			if (i == index) {
				elements[i] = null;
				removed = true;
			} else if (removed) {
				newArray[i - 1] = this.elements[i];
				this.elements[i] = null;
			} else {
				newArray[i] = this.elements[i];
				this.elements[i] = null;
			}
		}
		
		elements = newArray;
		
		size--;
		
		return old;
	}

	@Override
	public E remove(E toRemove) throws NullPointerException {
		for ( int i=0 ; i< this.size() ; i++) {
			if (toRemove.equals(this.get(i))) {
				return this.remove(i);
			}
		}
		
		return null;
	}

	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
		if (index >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		
		E old = this.get(index);
		
		elements[index] = toChange;
		
		return old;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException {
		for ( int i=0 ; i< this.size() ; i++) {
			if (toFind.equals(this.get(i))) {
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
			for ( int i=0 ; i< this.size() ; i++) {
				toHold[i] = elements[i];
			}
			return toHold;
		} else {
			return elements;
		}
	}

	@Override
	public E[] toArray() {
		return elements;
	}

	@Override
	public Iterator<E> iterator() {
		return new MyArrayListIterator();
	}
	
	private class MyArrayListIterator implements Iterator<E> {
        private int currentIndex = -1;

        @Override
        public boolean hasNext() {
            return currentIndex < size();
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            
            return get(currentIndex++);
        }
    }


}