package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utilities.MyStack;



public class MyStackTests {

    private static MyStack<String> stackTest;

    @BeforeClass
    public static void setUpBeforeClass() {
    	stackTest = new MyStack<>();
    }

    @AfterClass
    public static void tearDownAfterClass() {
    	stackTest = null;
    }

    @Before
    public void setUp() {
    	stackTest.clear();
    }

    @After
    public void tearDown() {
        
    }

    @Test
    public void testPushAndPop() {
    	stackTest.push("A");
    	stackTest.push("B");
    	stackTest.push("C");

        assertEquals("C", stackTest.pop());
        assertEquals("B", stackTest.pop());
        assertEquals("A", stackTest.pop());
    }
    
    @Test
    public void testPushAndPeek() {
    	stackTest.push("A");
    	stackTest.push("B");
    	stackTest.push("C");

        assertEquals("C", stackTest.peek());
        assertEquals(3, stackTest.size());
    }
    
    @Test
    public void testPeek() {
    	stackTest.push("A");
    	stackTest.push("B");
    	stackTest.push("C");

        assertEquals("C", stackTest.peek());
        assertEquals(3, stackTest.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(stackTest.isEmpty());

        stackTest.push("A");

        assertFalse(stackTest.isEmpty());

        stackTest.pop();

        assertTrue(stackTest.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(0, stackTest.size());

        stackTest.push("A");
        stackTest.push("B");
        stackTest.push("C");

        assertEquals(3, stackTest.size());

        stackTest.pop();

        assertEquals(2, stackTest.size());

        stackTest.clear();

        assertEquals(0, stackTest.size());
    }
    @Test
    public void testClear() {
    	stackTest.push("A");
    	stackTest.push("B");
    	stackTest.push("C");

    	stackTest.clear();

        assertTrue(stackTest.isEmpty());
        assertEquals(0, stackTest.size());
    }


}

