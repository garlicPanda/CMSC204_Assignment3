/**
 * 
 * @author vanessa
 *
 */
import java.util.Comparator;
import java.util.ListIterator;
import java.util.Collection;
import java.util.Collections;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T>{
	
	//Node<T> tail;
	//Node<T> head;
	Comparator<T> comparator2;
	
	/**
	 * Constructor that initialize a comparator
	 * @param comparator2
	 */
	SortedDoubleLinkedList(Comparator<T> comparator2){
		
		//this.tail = super.tail;
		//this.head = super.head;
		this.comparator2 = comparator2;
	}
	
	/**
	 * Inserts the specified element at the correct position in the sorted list
	 * @param data
	 * @return a reference to the current object
	 */
	public SortedDoubleLinkedList<T> add(T data){
		
		Node<T> newNode = new Node(null, data, null);
		Node<T> current = head.getNext();
		Node<T> previous = null;
		
		//traverse from the head.next to the tail
		while(current != null && current.getData() != null && comparator2.compare(data, current.getData()) >= 1) {
			
			previous = current;
			current = current.getNext();
		}
		
		//if previous element is null(head), then add between head and head.next
		if(previous == null) {
			
			newNode = new Node<T>(head, data, head.getNext());
			
			//link newNode between head and head.next
			head.getNext().setPrevious(newNode);
			head.setNext(newNode);;
			
			//increment size
			size++;
			
			return this;
		}
		//otherwise add between previous and current
		else {
			Node<T> predecessor = previous;
			Node<T> succesor = current;
			
			predecessor.setNext(newNode);
			succesor.setPrevious(newNode);
			
			newNode.setPrevious(predecessor);
			newNode.setNext(succesor);
			
			//increment size
			size++;
			
			return this;
		}
	}
	
	/**
	 * @throws UnsupportedOperationException if methods is called
	 */
	@Override
	public BasicDoubleLinkedList<T> addToEnd(T data) throws UnsupportedOperationException{
		
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}
	
	
	/**
	 * throws UnsupportedOperationException is methods is called
	 */
	@Override
	public BasicDoubleLinkedList<T> addToFront(T data) throws UnsupportedOperationException{
		
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}
	
	
	/**
	 * Implement the iterator by calling the super class iterator method
	 * @override iterator in class BasicDoubleLinkedList<T>
	 * @return an iterator positioned at the head of the list
	 */
	@Override
	public ListIterator<T> iterator(){
		
		return super.iterator();
	}
	
	/**
	 * Implements the remove operation by calling the super class remove method
	 * @Override remove in class BasicDoubleLinkedList<T>
	 * @param data the data element to be removed
	 * @param comparator the comparator to determinde equality of data elements
	 * @return this or null
	 */
	@Override
	public SortedDoubleLinkedList<T> remove (T data, Comparator<T> comparator){
		
		super.remove(data, comparator);
		return this;
	}

}
