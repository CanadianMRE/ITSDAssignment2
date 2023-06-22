package tests;

import static org.junit.Assert.*;

import java.util.EmptyStackException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilities.*;

public class MyStackTests {

	private MyStack<Integer> stackTest;

	@Before
	public void setUp() {
		stackTest = new MyStack<>();
	}

	@Test
	public void testPush() {
		stackTest.push(1);
		stackTest.push(2);
		stackTest.push(3);
		assertEquals(3, stackTest.size());
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
	public void testToArray() {
		stackTest.push(1);
		stackTest.push(2);
		stackTest.push(3);
		Object[] arr = stackTest.toArray();
		assertEquals(3, arr.length);
		assertArrayEquals(new Object[] { 1, 2, 3 }, arr);
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
		stackTest.push(1);
		stackTest.push(2);
		stackTest.push(3);
		assertTrue(stackTest.contains(2));
		assertFalse(stackTest.contains(4));
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
		stackTest.push(1);
		stackTest.push(2);
		stackTest.push(3);

		Iterator<Integer> iterator = stackTest.iterator();
		int count = 3;

		while (iterator.hasNext()) {
			assertEquals(count--, iterator.next().intValue());
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