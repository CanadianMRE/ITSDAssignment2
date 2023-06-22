package tests;

import exceptions.EmptyQueueException;
import org.junit.Before;
import org.junit.Test;
import utilities.*;
import exceptions.exceedQueueSizeException;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

public class MyQueueTests {

	private MyQueue<Integer> queueTest;

	@Before
	public void setup() {
		queueTest = new MyQueue<>();
	}

	@Test
	public void testEnqueue() {
		assertTrue(queueTest.isEmpty());
		queueTest.enqueue(1);
		assertFalse(queueTest.isEmpty());
		assertEquals(1, queueTest.size());
	}
	
	@Test(expected = exceedQueueSizeException.class)
    public void addFull() {
        MyQueue<Integer> queue = new MyQueue<>(1);
        queue.enqueue(1);
        queue.enqueue(2);
    }


	@Test(expected = NullPointerException.class)
	public void testEnqueueWithNull() {
		queueTest.enqueue(null);
	}

	@Test
	public void testDequeue() {
		queueTest.enqueue(1);
		queueTest.enqueue(2);
		queueTest.enqueue(3);
		assertEquals(1, (int) queueTest.dequeue());
		assertEquals(2, (int) queueTest.dequeue());
		assertEquals(3, (int) queueTest.dequeue());
		assertTrue(queueTest.isEmpty());
	}

	@Test(expected = EmptyQueueException.class)
	public void testDequeueOnEmptyQueue() {
		queueTest.dequeue();
	}

	@Test
	public void testPeek() {
		queueTest.enqueue(1);
		assertEquals(1, (int) queueTest.peek());
		assertEquals(1, queueTest.size());
	}

	@Test(expected = EmptyQueueException.class)
	public void testPeekOnEmptyQueue() {
		queueTest.peek();
	}

	@Test
	public void testDequeueAll() {
		queueTest.enqueue(1);
		queueTest.enqueue(2);
		queueTest.enqueue(3);
		queueTest.dequeueAll();
		assertTrue(queueTest.isEmpty());
	}

	@Test
	public void testIsEmpty() {
		assertTrue(queueTest.isEmpty());
		queueTest.enqueue(1);
		assertFalse(queueTest.isEmpty());
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEquals() {
		MyQueue<Integer> otherQueue = new MyQueue<>();
		assertTrue(queueTest.equals(otherQueue));

		queueTest.enqueue(1);
		assertFalse(queueTest.equals(otherQueue));

		otherQueue.enqueue(1);
		assertTrue(queueTest.equals(otherQueue));

		otherQueue.enqueue(2);
		otherQueue.enqueue(3);
		assertFalse(queueTest.equals(otherQueue));

		otherQueue.dequeue();
		assertFalse(queueTest.equals(otherQueue));
		
		MyQueue<Integer> queue1 = new MyQueue<>();
        assertTrue(queue1.equals(queue1));
        
        MyQueue<Integer> queue2 = new MyQueue<>();
        String notQueue = "Not a queue";
        assertFalse(queue2.equals(notQueue));
        
        MyQueue<Integer> queue = new MyQueue<>();
        assertFalse(queue.equals(null));
        
      
	}
	
	@Test
	public void testNotEqual(){
		 MyQueue<Integer> queue3 = new MyQueue<>();
	        queue3.enqueue(1);
	        queue3.enqueue(2);
	        queue3.enqueue(3);

	        MyQueue<Integer> queue4 = new MyQueue<>();
	        queue4.enqueue(1);
	        queue4.enqueue(5);
	        queue4.enqueue(3);

	        assertFalse(queue3.equals(queue4));
	}

	@Test
	public void testToArray() {
		queueTest.enqueue(1);
		queueTest.enqueue(2);
		queueTest.enqueue(3);
		Object[] arr = queueTest.toArray();
		assertEquals(3, arr.length);
		assertArrayEquals(new Object[] { 1, 2, 3 }, arr);
	}

	@Test
	public void testToArrayWithParameter() {
		queueTest.enqueue(1);
		queueTest.enqueue(2);
		queueTest.enqueue(3);
		Integer[] arr = new Integer[3];
		Integer[] result = queueTest.toArray(arr);
		assertSame(arr, result);
		assertArrayEquals(new Integer[] { 1, 2, 3 }, arr);
	}

	@Test
    public void testIsFull_QueueNotFull() {
        MyQueue<Integer> queue = new MyQueue<>(5);
        queue.enqueue(1);
        queue.enqueue(2);
        assertFalse(queue.isFull());
    }

    @Test
    public void testIsFull_QueueFull() {
        MyQueue<Integer> queue = new MyQueue<>(3);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertTrue(queue.isFull());
    }
    
	@Test
	public void testSize() {
		assertEquals(0, queueTest.size());
		queueTest.enqueue(1);
		queueTest.enqueue(2);
		assertEquals(2, queueTest.size());
		queueTest.dequeue();
		assertEquals(1, queueTest.size());
	}

	@Test
	public void testIterator() {
		queueTest.enqueue(1);
		queueTest.enqueue(2);
		queueTest.enqueue(3);
		int count = 1;

		Iterator<Integer> iterator = queueTest.iterator();
		while (iterator.hasNext()) {
			Integer element = iterator.next();
			assertEquals(count++, element.intValue());
		}
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testNext() {
		queueTest.iterator().next();
	}
}