import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

import org.junit.jupiter.api.Test;

class BSTTest {
	private static Integer[] firstListOfNumbers = {18,14,30,10,16,20,32,5,12,15,17,19,21,31,33};
	private static Integer[] secondListOfNumbers = {18,14,30,10,16,20,32,5,12,15,17,19,21,31};
	private static BST<Integer> firstBST = new BST<>(firstListOfNumbers);
	private static BST<Integer> secondBST = new BST<>(secondListOfNumbers);
	
	@Test
	public void heightReturnsCorrect() {
		int result = firstBST.height();
		assertEquals(result, 4);
	}
	
	@Test
	public void heightReturnsIncorrect() {
		int number = firstBST.height();
		boolean result = (number == 3);
		assertFalse(result);
	}
	
	@Test
	public void isPerfectReturnsTrue () {
		boolean perfect = firstBST.isPerfect();
		assertTrue(perfect);
	} 
	
	@Test
	public void isPerfectReturnsFalse () {
		boolean perfect = secondBST.isPerfect();
		assertFalse(perfect);
	} 
	
	@Test
	public void getNoOfLeavesReturnsCorrect () {
		int result = firstBST.getNoOfLeaves();
		assertEquals(result, 8);
	}
	
	@Test
	public void getNoOfLeavesReturnsIncorrect () {
		int number = firstBST.getNoOfLeaves();
		boolean result = (number == 7);
		assertFalse(result);
	}
	
	@Test
	public void getNoOfNonLeavesReturnsCorrect () {
		int result = firstBST.getNoOfNonLeaves();
		assertEquals(result, 7);
	}
	
	@Test
	public void getNoOfNonLeavesReturnsIncorrect () {
		int number = firstBST.getNoOfNonLeaves();
		boolean result = (number == 6);
		assertFalse(result);
	}
	
	@Test
	public void inOrderReturnsCorrect () {
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		firstBST.inorderStackTraversal();
		assertEquals("5 10 12 14 15 16 17 18 19 20 21 30 31 32 33 ", out.toString());
	}
	
	@Test
	public void inOrderReturnsIncorrect () {
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		firstBST.inorderStackTraversal();
		boolean result = (out.toString() == "5 10 12 14 15 16 17 18 19 20 21 30 31 32 33 ");
		assertFalse(result);
	}
}
