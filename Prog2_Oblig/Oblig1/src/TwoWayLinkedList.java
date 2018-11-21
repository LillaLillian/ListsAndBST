import java.util.Iterator;
import java.util.ListIterator;
 
public class TwoWayLinkedList<E> implements MyList<E> {
	private Node<E> head, tail;
	private int size = 0; // Number of elements in the list

	/** Create a default list */
	public TwoWayLinkedList() {
	}

	/** Create a list from an array of objects */
	public TwoWayLinkedList(E[] objects) {
		for (int i = 0; i < objects.length; i++)
			add(objects[i]);
	}

	/** Return the head element in the list */
	public E getFirst() {
		if (size == 0) {
			return null;
		} else {
			return head.element;
		}
	}

	/** Return the last element in the list */
	public E getLast() {
		if (size == 0) {
			return null;
		} else {
			return tail.element;
		}
	}

	/** Add an element to the beginning of the list */
	public void addFirst(E e) { 
		Node<E> newNode = new Node<>(e); // Create a new node
		newNode.next = head; // link the new node with the head
		head = newNode; // head points to the new node
		size++; // Increase list size

		if (tail == null) { // the new node is the only node in list
			tail = head;
		} else {
			(newNode.next).previous = head;
		}
	}

	/** Add an element to the end of the list */
	public void addLast(E e) { 
		Node<E> newNode = new Node<>(e); // Create a new for element e

		if (tail == null) {
			head = tail = newNode; // The new node is the only node in list
		} else {
			tail.next = newNode; // Link the new with the last node via next
			newNode.previous = tail; // Link new node to last node via previous
			tail = newNode; // tail now points to the last node
		}

		size++; // Increase size
	}

	/**
	 * Add a new element at the specified index in this list The index of the head
	 * element is 0
	 */
	@Override 
	public void add(int index, E e) {	//Lillian
		if (index < 0 || index > size) 
			throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
		
		if (index == 0) {
			addFirst(e);
		} else if (index >= size) {
			addLast(e);
		} else {
			Node<E> current = head;
			for (int i = 1; i < index; i++) {
				current = current.next;
			}
			Node<E> temp = current.next;
			Node<E> newNode = new Node<>(e);

			current.next = newNode;
			newNode.previous = current;

			(current.next).next = temp;
			temp.previous = newNode;

			size++;
		}
	}

	/**
	 * Remove the head node and return the object that is contained in the removed
	 * node.
	 */
	public E removeFirst() { 
		if (size == 0) {
			return null;
		}
		Node<E> current = head;
		if (head == tail) {
			tail = null;
		}
		
		head.next.previous = null;
		head = head.next;
		current.next = null;
		size--;
		return current.element;//returns removed element
	}

	/**
	 * Remove the last node and return the object that is contained in the removed
	 * node.
	 */

	public E removeLast() {
		if (size == 0) {
			return null;
		}
		Node<E> current = tail;
		if (head == tail) {
			head = null;
		}
		
		tail.previous.next = null; 
		tail = tail.previous; 
		current.previous = null; 

		size--;
		return current.element; // returns removed object
	}

	/**
	 * Remove the element at the specified position in this list. Return the element
	 * that was removed from the list.
	 */
	public E remove(int index) {	
		if (index < 0 || index >= size) 
			throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
		
		else if(index == 0)
			return removeFirst();
		else if (index == size - 1)
			return removeLast();
		else {
			Node<E> current = head;
			int count = 1;
			while (count < index) {
				current = current.next;
				count++;
			}

			Node<E> temp = current.next; // the one we want to remove
			current.next = temp.next;// connects left element to right element
			(temp.next).previous = current;// connects right element to left element
			size--;
			
			return temp.element;
		}
	}

	@Override
	public String toString() { 
		StringBuilder result = new StringBuilder("[");
		Node<E> current = head;
		for (int i = 0; i < size; i++) {
			result.append(current.element);
			current = current.next;
			if (current != null) {
				result.append(", ");
			} else {
				result.append("]");
			}
		}
		return result.toString();
	}

	/** Clear the list */
	public void clear() { 
		size = 0;
		head = tail = null;
	}

	/**
	 * Oppretter en instans av LinkedListIterator for å iterere gjennom listen fra
	 * start. Sjekker første element og itererer deretter. 
	 * Dersom en objekt i listen matcher object e skal vi returnere true
	 * ellers false.
	 */
	public boolean contains(Object e) { 
		LinkedListIterator iterator = (LinkedListIterator) this.listIterator();
		
		if (((E) e).equals(iterator.current())) {
			return true;
		}
		while (iterator.hasNext()) {
			if (((E) e).equals(iterator.next())) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Oppretter en instans av LinkedListIterator med konstruktøren (int index), som
	 * iterer blar frem til index oppgitt og deretter gir mulighet for å iterere.
	 * Returnerer dermed current element.
	 */
	/** Return the element from this list at the specified index */
	public E get(int index) {	
		if (index < 0 || index >= size) 
			throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);

		LinkedListIterator iterator = (LinkedListIterator) this.listIterator(index);
		return iterator.current();
	}

	/**
	 * Oppretter en instans LinkedListIterator for å iterere gjennom listen fra
	 * start. Sjekker første element og itererer deretter. 
	 * Dersom en objekt i listen matcher e skal vi returnere indeksen, hvis
	 * ikke itererer vi over til neste node.
	 * 
	 * Returnerer -1 dersom det ikke er noe match.
	 */
	public int indexOf(Object e) { 
		LinkedListIterator iterator = (LinkedListIterator) this.listIterator();
		
		if (((E) e).equals(iterator.current())) {
			return iterator.nextIndex();
		}
		while (iterator.hasNext()) {
			if (((E) e).equals(iterator.next())) {
				return iterator.previousIndex();
			}
		}
		return -1;
	}

	/**
	 * Oppretter en ny instans LinkedListIterator som starter på slutten (tail).
	 * Sjekker nåværende element og itererer deretter.
	 * Dersom en object i listen matcher e skal vi returnere indeksen, hvis ikke
	 * itererer vi til forrige node.
	 * 
	 * Returnerer -1 dersom det ikke finnes noen match.
	 */
	public int lastIndexOf(Object e) { 
		LinkedListIterator iterator = (LinkedListIterator) this.listIterator(size - 1);
		
		if (((E) e).equals(iterator.current()))
			return iterator.nextIndex();
		while (iterator.hasPrevious()) {
			if (((E) e).equals(iterator.previous())) {
				return iterator.nextIndex();
			}
		}
		return -1;
	}

	/**
	 * Oppretter en ny instans LinkedListIterator konstruktøren (int index). Bruker
	 * set funksjonen i iteratoren til å bytte ut objektet, men beholde lenkene.
	 * 
	 * Returnerer dermed current node i iteratoren.
	 */
	public E set(int index, E e) {	
		if (index < 0 || index >= size) 
			throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
		
		LinkedListIterator iterator = (LinkedListIterator) this.listIterator(index);
		iterator.set(e);
		return iterator.current();
	}

	private class LinkedListIterator implements java.util.ListIterator<E> { 
		private Node<E> current = head;
		private int nextIndex = 0;

		public LinkedListIterator() {
		}

		public LinkedListIterator(int index) {
			if (index < 0 || index > size - 1) {
				throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);

			} else if (index < (size / 2)) { // itererer fra start hvis index er i første halvdel
				for (int i = 0; i < index; i++) {
					current = current.next;
					nextIndex++;
				}
			} else {// itererer fra slutten hvis ikke
				current = tail;
				nextIndex = size - 1;

				for (int i = size - 1; i > index; i--) {
					current = current.previous;
					nextIndex--;
				}
			}
		}

		public void setLast(E e) {
			current = tail;
			current.element = e;
		}

		@Override
		public boolean hasNext() {
			return (current != null);
		}

		@Override
		public E next() {
			E e = current.element;
			current = current.next;
			nextIndex++;
			return e;
		}

		@Override
		public boolean hasPrevious() {
			return (current != null && current.previous != null);
		}

		@Override
		public E previous() {
			E e = (current.previous).element;
			current = current.previous;
			nextIndex--;
			return e;
		}

		@Override
		public void set(E e) {
			current.element = e;
		}

		public E current() {
			return current.element;
		}

		@Override
		public void add(E args) {
			// TODO Auto-generated method stub

		}

		@Override
		public int nextIndex() {
			if (hasNext()) {
				return nextIndex;
			} else {
				return -1;
			}
		}

		@Override
		public int previousIndex() {
			if (hasPrevious()) {
				return nextIndex - 1;
			} else {
				return -1;
			}
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

		}
	}

	private class Node<E> {
		E element;
		Node<E> next;
		Node<E> previous;

		public Node(E o) {
			element = o;
		}
	}

	@Override
	public int size() { 
		return size;
	}

	public ListIterator<E> listIterator() { 
		return new LinkedListIterator();
	}

	public ListIterator<E> listIterator(int index) { 
		return new LinkedListIterator(index);
	}

	@Override
	public ListIterator<E> iterator() { // Cecilie
		return null;
	}

}
