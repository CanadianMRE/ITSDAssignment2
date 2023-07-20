package tests;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import utilities.MyDLL;

import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

/**
 * Handles testing for MyDLL
 * 
 * @author Group 7
 *
 */
public class MyDLLTests {

	private MyDLL<Integer> dll;

	@Before
	public void setup() {
		dll = new MyDLL<>();
	}

	@Test
	public void testSize() {
		assertEquals(0, dll.size());

		dll.add(10);
		assertEquals(1, dll.size());

		dll.add(5);
		assertEquals(2, dll.size());
	}

	@Test
	public void testClear() {
		dll.add(10);
		dll.add(5);

		dll.clear();
		assertEquals(0, dll.size());
	}

	@Test
	public void testAddAtIndex() {
		dll.add(0, 10);
		dll.add(1, 20);

		assertEquals(2, dll.size());
		assertEquals(Integer.valueOf(10), dll.get(0));
		assertEquals(Integer.valueOf(20), dll.get(1));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddAtIndexOutOfBounds() {
		dll.add(1, 10);
	}

	@Test
	public void testAddAll() {
		MyDLL<Integer> other = new MyDLL<>();
		other.add(10);
		other.add(20);
		other.add(30);

		dll.addAll(other);

		assertEquals(3, dll.size());
		assertEquals(Integer.valueOf(10), dll.get(0));
		assertEquals(Integer.valueOf(20), dll.get(1));
		assertEquals(Integer.valueOf(30), dll.get(2));
	}

	@Test
	public void testGet() {
		dll.add(10);
		dll.add(20);

		assertEquals(Integer.valueOf(10), dll.get(0));
		assertEquals(Integer.valueOf(20), dll.get(1));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetOutOfBounds() {
		dll.get(0);
	}

	@Test
	public void testRemoveAtIndex() {
		dll.add(10);
		dll.add(20);

		Integer removed = dll.remove(0);

		assertEquals(1, dll.size());
		assertEquals(Integer.valueOf(10), removed);
		assertEquals(Integer.valueOf(20), dll.get(0));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveAtIndexEmpty() {
		dll.remove(0);
	}

	@Test
	public void testRemoveAtMiddleIndex() {
		dll.add(10);
		dll.add(20);
		dll.add(30);

		Integer removed = dll.remove(1);

		assertEquals(2, dll.size());
		assertEquals(Integer.valueOf(20), removed);
		assertEquals(Integer.valueOf(10), dll.get(0));
	}

	@Test
	public void testRemoveAtIndex2() {
		dll.add(10);
		dll.add(20);

		Integer removed = dll.remove(1);

		assertEquals(1, dll.size());
		assertEquals(Integer.valueOf(20), removed);
		assertEquals(Integer.valueOf(10), dll.get(0));
	}

	@Test
	public void testRemoveAtIndexOneVal() {
		dll.add(10);

		Integer removed = dll.remove(0);

		assertEquals(0, dll.size());
		assertEquals(Integer.valueOf(10), removed);
	}

	@Test
	public void testRemoveByValue() {
		dll.add(10);
		dll.add(20);
		
		Integer removed = dll.remove(Integer.valueOf(10));

		assertEquals(1, dll.size());
		assertEquals(Integer.valueOf(10), removed);
		assertEquals(Integer.valueOf(20), dll.get(0));
		
		Integer removed2 = dll.remove(Integer.valueOf(20));
		assertEquals(0, dll.size());
		assertEquals(Integer.valueOf(20), removed2);
	}

	@Test
	public void testRemoveByValueEnd() {
		dll.add(10);
		dll.add(20);
		
		Integer removed = dll.remove(Integer.valueOf(20));

		assertEquals(1, dll.size());
		assertEquals(Integer.valueOf(20), removed);
		assertEquals(Integer.valueOf(10), dll.get(0));
	}

	@Test
	public void testRemoveBigByValue() {
		dll.add(10);
		dll.add(20);
		dll.add(30);
		
		Integer removed = dll.remove(Integer.valueOf(20));

		assertEquals(2, dll.size());
		assertEquals(Integer.valueOf(20), removed);
		assertEquals(Integer.valueOf(10), dll.get(0));
	}

	@Test
	public void testRemoveOneByValue() {
		dll.add(10);
		
		Integer removed = dll.remove(Integer.valueOf(10));

		assertEquals(0, dll.size());
		assertEquals(Integer.valueOf(10), removed);
	}
	
	@Test
	public void testRemoveNull() {
		Integer removed = dll.remove(Integer.valueOf(10));
		
		assertEquals(null, removed);
	}

	@Test
	public void testSet() {
		dll.add(10);
		dll.add(20);

		Integer replaced = dll.set(0, 30);

		assertEquals(2, dll.size());
		assertEquals(Integer.valueOf(10), replaced);
		assertEquals(Integer.valueOf(30), dll.get(0));
	}

	@Test
	public void testSetEnd() {
		dll.add(10);
		dll.add(20);

		Integer replaced = dll.set(1, 30);

		assertEquals(2, dll.size());
		assertEquals(Integer.valueOf(20), replaced);
		assertEquals(Integer.valueOf(30), dll.get(1));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetOutOfBounds() {
		dll.set(0, 10);
	}

	@Test
	public void testIsEmpty() {
		assertTrue(dll.isEmpty());

		dll.add(10);

		assertFalse(dll.isEmpty());
	}

	@Test
	public void testContains() {
		dll.add(10);
		dll.add(20);

		assertTrue(dll.contains(10));
		assertFalse(dll.contains(30));
	}

	@Test
	public void testToArray() {
		dll.add(10);
		dll.add(20);

		Integer[] arr = new Integer[2];
		Integer[] result = dll.toArray(arr);

		assertSame(arr, result);
		assertArrayEquals(new Integer[] { 10, 20 }, result);
	}

	@Test
	public void testToArrayTooSmall() {
		dll.add(10);
		dll.add(20);

		Integer[] arr = new Integer[1];
		Object[] result = dll.toArray(arr);
		
		assertArrayEquals(new Object[] { 10, 20 }, result);
	}

	@Test(expected = NullPointerException.class)
	public void testToArrayWithNullParameter() {
		dll.toArray(null);
	}

	@Test
	public void testToArrayNoParameter() {
		dll.add(10);
		dll.add(20);

		Object[] result = dll.toArray();

		assertArrayEquals(new Object[] { 10, 20 }, result);
	}
	
	@Test
	public void testAddEmptyIndex() {
		dll.add(10);
		dll.add(20);
		dll.add(30);
		
		dll.add(1, 40);

		assertEquals(Integer.valueOf(40), dll.get(1));
	}

	@Test(expected = NoSuchElementException.class)
	public void testIteratorNoSuchElementException() {
		dll.iterator().next();
	}

	@Test(expected = NoSuchElementException.class)
	public void testNodeIteratorNoSuchElementException() {
		dll.nodeIterator().next();
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetNodeAtIndexException() {
		dll.getNodeAtIndex(0);
	}
}