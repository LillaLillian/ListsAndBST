
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TwoWayLinkedListTest {
	
	private TwoWayLinkedList<String> list;
	
    @Before
    public void setUp() {
       list = new TwoWayLinkedList<>();
    }
    
    @After
    public void tearDown() {
    	list.clear();
    }
    
	@Test
	public void testEmptyListReturns_True() {
		assertEquals("Empty list should have 0 elements", true, list.isEmpty());
	}
	
	@Test
	public void testToStringMethodReturns_ExpectedString() {
		for(int i = 0; i < 10; i++) {
			list.add("" + i);
		}		
		
		assertEquals("toString method doesn't match", "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", list.toString());
	}
	
	@Test
	public void testSizeMethodReturns_10() {
		for(int i = 0; i < 10; i++) {
			list.add("" + i);
		}		
		
		assertThat(list.size(), is(10));
	}
	
	@Test
	public void testGetFirstReturns_String0() {
		for(int i = 0; i < 10; i++) {
			list.add("" + i);
		}		
		
		assertThat(list.getFirst(), is("0"));
	}
	
	@Test
	public void testGetIndexReturn_String4() {
		for(int i = 0; i < 10; i++) {
			list.add("" + i);
		}		
		
		assertThat(list.get(4), is("4"));
	}
	
	@Test
	public void testGetLastReturns_String10() {
		for(int i = 0; i < 10; i++) {
			list.add("" + i);
		}		
		
		assertThat(list.getLast(), is("9"));
	}
	
	@Test
	public void testAddFirstThenToStringReturns_ExpectedString() {
		for(int i = 0; i < 10; i++) {
			list.add("" + i);
		}		
		
		list.addFirst("" + 11);
		assertEquals("toString method doesn't match, method not working as intended", "[11, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", list.toString());
	}
	
	@Test
	public void testAddIndexThenToStringReturns_ExpectedString() {
		for(int i = 0; i < 10; i++) {
			list.add("" + i);
		}		
		
		list.add(4, "" + 11);
		assertEquals("toString method doesn't match, method not working as intended", "[0, 1, 2, 3, 11, 4, 5, 6, 7, 8, 9]", list.toString());
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void testAddIndexReturns_IndexOutOfBounds() {
		list.add("Cecilie");
		list.add("Lilian");
		list.add("Dominique");
		list.add("Hamid");
		
		list.add(231, "Haaha");
	}
	
	@Test
	public void testRemoveFirstReturns_ExpectedGeneric() {
		for(int i = 0; i < 10; i++) {
			list.add("" + i);
		}		
		
		//Sjekker return
		assertEquals("The first element did not get returned","0", list.removeFirst());
		//Stemmer toString?
		assertEquals("toString method doesn't match, method not working as intended", "[1, 2, 3, 4, 5, 6, 7, 8, 9]", list.toString());
		//Hva med size?
		assertThat(list.size(), is (9));		
	}
	
	@Test
	public void testRemoveLastReturns_ExpectedGeneric() {
		for(int i = 0; i < 10; i++) {
			list.add("" + i);
		}	
		
		//Sjekker return
		assertEquals("The last element did not get returned", "9", list.removeLast());
		//Stemmer toString?
		assertEquals("toString method doesn't match, method not working as intended", "[0, 1, 2, 3, 4, 5, 6, 7, 8]", list.toString());
		//Hva med size?
		assertThat(list.size(), is (9));		
	}
	
	@Test
	public void testRemoveIndexReturns_ExpectedGeneric() {
		for(int i = 0; i < 10; i++) {
			list.add("" + i);
		}	
		
		//Sjekker return
		assertEquals("The last element did not get returned", "1", list.remove(1));
		//Stemmer toString?
		assertEquals("toString method doesn't match, method not working as intended", "[0, 2, 3, 4, 5, 6, 7, 8, 9]", list.toString());
		//Hva med size?
		assertThat(list.size(), is (9));		
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void testRemoveIndexReturns_IndexOutOfBounds() {
		list.add("Cecilie");
		list.add("Lilian");
		list.add("Dominique");
		list.add("Hamid");
		
		list.remove(12);
	}
	
	@Test
	public void testClearListReturns_Size0() {
		for(int i = 0; i < 10; i++) {
			list.add("" + i);
		}	
		
		list.clear();
		assertEquals("List did not get cleared", 0, list.size());
	}
	
	@Test
	public void testContainsReturns_True() {
		list.add("Cecilie");
		list.add("Lilian");
		list.add("Dominique");
		list.add("Hamid");
		
		assertThat(list.contains("Cecilie"), is(true));
	}
	
	@Test
	public void testContainReturns_False() {
		list.add("Cecilie");
		list.add("Lilian");
		list.add("Dominique");
		list.add("Hamid");
		
		assertThat(list.contains("Haaaha"), is(false));
	}
	
	@Test
	public void testIndexOfReturns_1() {
		list.add("Cecilie");
		list.add("Lilian");
		list.add("Dominique");
		list.add("Hamid");
		
		assertThat(list.indexOf("Lilian"), is(1));
	}
	
	@Test
	public void testIndexOfReturns_Negative1() {
		list.add("Cecilie");
		list.add("Lilian");
		list.add("Dominique");
		list.add("Hamid");
		
		assertThat(list.indexOf("Haaha"), is(-1));
	}
	
	@Test
	public void testLastIndexOfReturns_4() {
		list.add("Cecilie");
		list.add("Lilian");
		list.add("Dominique");
		list.add("Hamid");
		list.add("Dominique");
		
		assertThat(list.lastIndexOf("Dominique"), is(4));
	}
	
	@Test
	public void testLastIndexOfWithOneListingReturns_3() {
		list.add("Cecilie");
		list.add("Lilian");
		list.add("Dominique");
		list.add("Hamid");
		
		assertThat(list.lastIndexOf("Hamid"), is(3));
	}
	
	@Test
	public void testLastIndexOfNoListingReturns_Negative1() {
		list.add("Cecilie");
		list.add("Lilian");
		list.add("Dominique");
		list.add("Hamid");
		
		assertThat(list.lastIndexOf("Haha"), is(-1));
	}
	
	
	@Test
	public void testGetIndexReturns_Cecilie() {
		list.add("Cecilie");
		list.add("Lilian");
		list.add("Dominique");
		list.add("Hamid");
		
		assertThat(list.get(0), is("Cecilie"));
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void testGetIndexReturns_IndexOutOfBounds() {
		list.add("Cecilie");
		list.add("Lilian");
		list.add("Dominique");
		list.add("Hamid");
		
		list.get(22);
	}
	
	@Test
	public void testSetValidIndexReturns_Hamid() {
		list.add("Cecilie");
		list.add("Lilian");
		list.add("Dominique");
		list.add("Hamid");
		
		assertThat(list.set(2, "Hamid"), is("Hamid"));
		//Stemmer toString?
		assertEquals("toString method doesn't match, method not working as intended", "[Cecilie, Lilian, Hamid, Hamid]", list.toString());
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void testSetInvalidIndexReturns_IndexOutOfBounds() {
		list.add("Cecilie");
		list.add("Lilian");
		list.add("Dominique");
		list.add("Hamid");
		
		list.set(6, "Hamid");
	}
	
}