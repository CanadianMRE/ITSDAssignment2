package tests;

import static org.junit.Assert.*;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import utilities.*;

/**
 * Tests for MyStack
 * 
 * @author Jaymen
 *
 */
public class MyStackTests {

	private MyStack<Integer> stackTest;

	@Before
	public void setUp() {
		stackTest = new MyStack<>();
	}

	@Test
	public void testPush() {
		MyStack<Integer> stack = new MyStack<>();

		// Test with non-null element
		stack.push(10);
		assertEquals(1, stack.size());
		assertEquals(Integer.valueOf(10), stack.peek());

		// Test with another non-null element
		stack.push(20);
		assertEquals(2, stack.size());
		assertEquals(Integer.valueOf(20), stack.peek());

		// Test with multiple non-null elements
		stack.push(30);
		stack.push(40);
		stack.push(50);
		assertEquals(5, stack.size());
		assertEquals(Integer.valueOf(50), stack.peek());

		// Test with null element
		try {
			stack.push(null);
			fail("NullPointerException should have been thrown.");
		} catch (NullPointerException e) {
			// Exception should be thrown
			assertEquals(5, stack.size());
			assertEquals(Integer.valueOf(50), stack.peek());
		}
	}

	@Test
	public void testPop() {
		stackTest.push(1);
		stackTest.push(2);
		stackTest.push(3);
		assertEquals(3, stackTest.pop().intValue());
		assertEquals(2, stackTest.size());
	}

	@Test(expected = EmptyStackException.class)
	public void testPopOnEmptyStack() {
		stackTest.pop();
	}

	@Test
	public void testPeek() {
		stackTest.push(1);
		stackTest.push(2);
		stackTest.push(3);
		assertEquals(3, stackTest.peek().intValue());
		assertEquals(3, stackTest.size());
	}

	@Test(expected = EmptyStackException.class)
	public void testPeekOnEmptyStack() {
		stackTest.peek();
	}

	@Test
	public void testClear() {
		stackTest.push(1);
		stackTest.push(2);
		stackTest.push(3);
		stackTest.clear();
		assertEquals(0, stackTest.size());
		assertTrue(stackTest.isEmpty());
	}

	@Test
	public void testIsEmpty() {
		assertTrue(stackTest.isEmpty());
		stackTest.push(1);
		assertFalse(stackTest.isEmpty());
		stackTest.pop();
		assertTrue(stackTest.isEmpty());
	}

	@Test
	public void trueEqualsTest() {
		stackTest.equals(stackTest);
	}

	@Test
	public void trueEqualsNumber() {
		stackTest.equals("");
	}

	@Test
	public void trueEqualsNumberNull() {
		stackTest.equals(null);
	}

	@Test
	public void testToArray() {
		stackTest.push(1);
		stackTest.push(2);
		stackTest.push(3);
		Object[] arr = stackTest.toArray();
		assertEquals(3, arr.length);
		assertArrayEquals(new Object[] { 1, 2, 3 }, arr);
	}

	@Test(expected = NullPointerException.class)
	public void testNullArray() {
		stackTest.toArray(null);

	}

	@Test
	public void testSmallArray() {
		stackTest.push(10);
		stackTest.push(20);

		Integer[] arr = new Integer[1];
		Object[] result = stackTest.toArray(arr);

		assertArrayEquals(new Object[] { 20, 10 }, result);
	}

	@Test
	public void testToArrayWithParameter() {
		stackTest.push(1);
		stackTest.push(2);
		stackTest.push(3);
		Integer[] arr = new Integer[3];
		Integer[] result = stackTest.toArray(arr);
		assertSame(arr, result);
		assertArrayEquals(new Integer[] { 3, 2, 1 }, result);
	}

	@Test
	public void testContains() {
		MyStack<Integer> stack = new MyStack<>();
		stack.push(10);
		stack.push(20);
		stack.push(30);

		// Test when element is present in the stack
		boolean result1 = stack.contains(20);
		assertTrue(result1);

		// Test when element is not present in the stack
		boolean result2 = stack.contains(40);
		assertFalse(result2);

		// Test with null element
		try {
			stack.contains(null);
			fail("NullPointerException should have been thrown.");
		} catch (NullPointerException e) {
			// Exception should be thrown
		}
	}

	@Test
	public void testSearch() {
		stackTest.push(1);
		stackTest.push(2);
		stackTest.push(3);
		assertEquals(1, stackTest.search(3));
		assertEquals(2, stackTest.search(2));
		assertEquals(-1, stackTest.search(4));
	}

	@Test
	public void testIterator() {
		// Create a stack and add elements
		MyStack<Integer> stack = new MyStack<>();
		stack.push(10);
		stack.push(20);
		stack.push(30);

		// Create an iterator and test its functionality
		Iterator<Integer> iterator = stack.iterator();
		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(30, (int) iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(20, (int) iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(10, (int) iterator.next());
		Assert.assertFalse(iterator.hasNext());

		// Try calling next when there are no more elements
		try {
			iterator.next();
			Assert.fail("NoSuchElementException should have been thrown.");
		} catch (NoSuchElementException e) {
			// Exception should be thrown
		}
	}

	@Test
	public void testEquals() {
		MyStack<Integer> otherStack = new MyStack<>();
		assertTrue(stackTest.equals(otherStack));

		stackTest.push(1);
		assertFalse(stackTest.equals(otherStack));

		otherStack.push(1);
		assertTrue(stackTest.equals(otherStack));
		stackTest.push(5);
		stackTest.push(6);
		otherStack.push(2);
		otherStack.push(3);
		assertFalse(stackTest.equals(otherStack));

		otherStack.pop();
		assertFalse(stackTest.equals(otherStack));
	}

	@Test
	public void testSize() {
		assertEquals(0, stackTest.size());
		stackTest.push(1);
		stackTest.push(2);
		assertEquals(2, stackTest.size());
		stackTest.pop();
		assertEquals(1, stackTest.size());
	}

	@After
	public void tearDown() {
		stackTest = null;
	}
}