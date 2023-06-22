package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import utilities.Iterator;
import utilities.MyDLL;

import java.util.NoSuchElementException;

public class MyDLLTests {

    private MyDLL<Integer> dll;

    @Before
    public void setup() {
        dll = new MyDLL<>();
    }

    @Test
    public void testSize() {
        Assert.assertEquals(0, dll.size());

        dll.add(10);
        Assert.assertEquals(1, dll.size());

        dll.add(5);
        Assert.assertEquals(2, dll.size());
    }

    @Test
    public void testClear() {
        dll.add(10);
        dll.add(5);

        dll.clear();
        Assert.assertEquals(0, dll.size());
    }

    @Test
    public void testAddAtIndex() {
        dll.add(0, 10);
        dll.add(1, 20);

        Assert.assertEquals(2, dll.size());
        Assert.assertEquals(Integer.valueOf(10), dll.get(0));
        Assert.assertEquals(Integer.valueOf(20), dll.get(1));
    }

    @Test
    public void testAddAtIndexOutOfBounds() {
        try {
            dll.add(1, 10);
            Assert.fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // Expected exception
        }
    }

    @Test
    public void testAddAll() {
        MyDLL<Integer> other = new MyDLL<>();
        other.add(10);
        other.add(20);
        other.add(30);

        dll.addAll(other);

        Assert.assertEquals(3, dll.size());
        Assert.assertEquals(Integer.valueOf(10), dll.get(0));
        Assert.assertEquals(Integer.valueOf(20), dll.get(1));
        Assert.assertEquals(Integer.valueOf(30), dll.get(2));
    }

    @Test
    public void testGet() {
        dll.add(10);
        dll.add(20);

        Assert.assertEquals(Integer.valueOf(10), dll.get(0));
        Assert.assertEquals(Integer.valueOf(20), dll.get(1));
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

        Assert.assertEquals(1, dll.size());
        Assert.assertEquals(Integer.valueOf(10), removed);
        Assert.assertEquals(Integer.valueOf(20), dll.get(0));
    }

    @Test
    public void testRemoveByValue() {
        dll.add(10);
        dll.add(20);

        Integer removed = dll.remove(Integer.valueOf(10));

        Assert.assertEquals(1, dll.size());
        Assert.assertEquals(Integer.valueOf(10), removed);
        Assert.assertEquals(Integer.valueOf(20), dll.get(0));
    }

    @Test
    public void testSet() {
        dll.add(10);
        dll.add(20);

        Integer replaced = dll.set(0, 30);

        Assert.assertEquals(2, dll.size());
        Assert.assertEquals(Integer.valueOf(10), replaced);
        Assert.assertEquals(Integer.valueOf(30), dll.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetOutOfBounds() {
        dll.set(0, 10);
    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(dll.isEmpty());

        dll.add(10);

        Assert.assertFalse(dll.isEmpty());
    }

    @Test
    public void testContains() {
        dll.add(10);
        dll.add(20);

        Assert.assertTrue(dll.contains(10));
        Assert.assertFalse(dll.contains(30));
    }

    @Test
    public void testToArray() {
        dll.add(10);
        dll.add(20);

        Integer[] arr = new Integer[2];
        Integer[] result = dll.toArray(arr);

        Assert.assertSame(arr, result);
        Assert.assertArrayEquals(new Integer[]{10, 20}, result);
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

        Assert.assertArrayEquals(new Object[]{10, 20}, result);
    }

    @Test
    public void testIterator() {
    	dll.add(10);
    	dll.add(20);
    	dll.add(30);

    	StringBuilder sb = new StringBuilder();
    	Iterator<Integer> iterator = dll.iterator();
    	while (iterator.hasNext()) {
    	    Integer element = iterator.next();
    	    sb.append(element).append(" ");
    	}

    	Assert.assertEquals("10 20 30 ", sb.toString());
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorNoSuchElementException() {
        dll.iterator().next();
    }
}