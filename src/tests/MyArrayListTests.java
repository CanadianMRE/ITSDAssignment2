package tests;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilities.MyArrayList;

/**
 * A test file to handle tests of MyArrayList
 * 
 * @author Group 7
 *
 */
public class MyArrayListTests {
	private MyArrayList<String> arrayTest;

	@Before
	public void setUp() {
		// Create a new instance of MyArrayList before each test case
		arrayTest = new MyArrayList<>();
	}

	@After
	public void tearDown() {
		// Reset or clean up any resources after each test case
		arrayTest = null;
	}

	@Test
	public void testAdd() {
		arrayTest.add("A");
		assertEquals(1, arrayTest.size());
		assertEquals("A", arrayTest.get(0));
	}

	@Test
	public void testAddWithIndex() {
		arrayTest.add("A");
		arrayTest.add(0, "B");
		assertEquals(2, arrayTest.size());
		assertEquals("B", arrayTest.get(0));
		assertEquals("A", arrayTest.get(1));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddWithInvalidIndex() {
		arrayTest.add(1, "A");
	}

	@Test
	public void testRemove() {
		arrayTest.add("A");
		arrayTest.add("B");
		boolean removed = arrayTest.remove("A") != null;
		assertTrue(removed);
		assertEquals(1, arrayTest.size());
		assertEquals("B", arrayTest.get(0));
	}

	@Test
	public void testRemoveNonExistentElement() {
		arrayTest.add("A");
		boolean removed = arrayTest.remove("B") != null;
		assertFalse(removed);
		assertEquals(1, arrayTest.size());
		assertEquals("A", arrayTest.get(0));
	}

	@Test
	public void testRemoveWithIndex() {
		arrayTest.add("A");
		arrayTest.add("B");
		String removed = arrayTest.remove(1);
		assertEquals(1, arrayTest.size());
		assertEquals("B", removed);
		assertEquals("A", arrayTest.get(0));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveWithInvalidIndex() {
		arrayTest.remove(0);
	}

	@Test
	public void testContains() {
		arrayTest.add("A");
		assertTrue(arrayTest.contains("A"));
		assertFalse(arrayTest.contains("B"));
	}

	@Test
	public void testSize() {
		assertEquals(0, arrayTest.size());
		arrayTest.add("A");
		assertEquals(1, arrayTest.size());
		arrayTest.remove("A");
		assertEquals(0, arrayTest.size());
	}

	@Test
	public void testIsEmpty() {
		assertTrue(arrayTest.isEmpty());
		arrayTest.add("A");
		assertFalse(arrayTest.isEmpty());
		arrayTest.remove("A");
		assertTrue(arrayTest.isEmpty());
	}

	@Test
	public void testGet() {
		arrayTest.add("A");
		arrayTest.add("B");
		assertEquals("A", arrayTest.get(0));
		assertEquals("B", arrayTest.get(1));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetWithInvalidIndex() {
		arrayTest.get(0);
	}

	@Test
	public void testSet() {
		arrayTest.add("A");
		String previous = arrayTest.set(0, "B");
		assertEquals("A", previous);
		assertEquals("B", arrayTest.get(0));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetWithInvalidIndex() {
		arrayTest.set(0, "A");
	}

	@Test
	public void testClear() {
		arrayTest.add("A");
		arrayTest.add("B");
		arrayTest.clear();
		assertEquals(0, arrayTest.size());
		assertTrue(arrayTest.isEmpty());
	}

	@Test
	public void testToArray() {
		arrayTest.add("A");
		arrayTest.add("B");
		Object[] array = arrayTest.toArray();
		Object[] expected = { "A", "B" };
		assertArrayEquals(expected, array);
	}

	@Test
	public void testToArrayWithArrayParameter() {
		arrayTest.add("A");
		arrayTest.add("B");
		String[] array = new String[2];
		String[] result = arrayTest.toArray(array);
		assertArrayEquals(new String[] { "A", "B" }, array);
		assertSame(array, result);
	}

	@Test(expected = NullPointerException.class)
	public void testToArrayWithNullArray() {
		arrayTest.toArray(null);
	}

	@Test
	public void testToArrayWithSmallArray() {
		arrayTest.add("A");
		arrayTest.add("B");
		arrayTest.add("C");
		arrayTest.add("D");

		String[] array = new String[2];
		Object[] result = arrayTest.toArray(array);

		assertArrayEquals(new String[] { "A", "B", "C", "D" }, result);
	}

	@Test
	public void testAddAll() {
		MyArrayList<String> array2 = new MyArrayList<String>();

		array2.add("A");
		array2.add("B");
		array2.add("C");

		arrayTest.addAll(array2);

		assertEquals("A", arrayTest.get(0));
		assertEquals("B", arrayTest.get(1));
		assertEquals("C", arrayTest.get(2));
	}

	@Test(expected = NoSuchElementException.class)
	public void testBreakAddAll() {
		utilities.Iterator<String> iter = arrayTest.iterator();

		iter.next();

	}

}