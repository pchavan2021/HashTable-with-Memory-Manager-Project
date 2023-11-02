import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;
/**
 * 
 *
 * @author Evan Howe (evanhowe03)
 * @author Pallavi Chavan (pallucha21)
 * @version 9/14/2023
 */
public class CommandProcessorTest extends student.TestCase {
	/**
	 * setUp method
	 */
	public void setUp() {
		
	}

	
	/**
	 * tests Scanner 
	 * @Test
	 */
	 public void testScanner() {
    	String testInput = "word1 word2 word3";
    	Scanner scanner = new Scanner(testInput);
    	
    	assertTrue(scanner.hasNext());
    	assertEquals("word1", scanner.next());
    	assertTrue(scanner.hasNext());
    	assertEquals("word2", scanner.next());
    	assertTrue(scanner.hasNext());
    	assertEquals("word3", scanner.next());
    	
    	assertFalse(scanner.hasNext());
    	
    }
	/**
	 * tests that its parsing
	 * the command words correctly
	 * @Test
	 */
	public void testWords() {
		String test = "insert search delete print";
		
		Scanner scanner = new Scanner(test);
		
		assertTrue(scanner.hasNext());
		assertEquals("insert", scanner.next());
		assertEquals("search", scanner.next());
		assertEquals("delete", scanner.next());
		assertEquals("print", scanner.next());
		
	}
}