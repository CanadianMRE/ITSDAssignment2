package utilities;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class MyStack<E> implements StackADT<E> {
   
	private static final long serialVersionUID = 1L;
	private MyArrayList<E> stackList;

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

    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        return stackList.toArray(holder);
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
        return stackList.equals(otherStack.stackList);
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