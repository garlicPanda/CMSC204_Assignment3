/**
 * 
 * @author vanessa
 *
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T>{
	
	//Node: head and tail
	Node<T> head;
	Node<T> tail;
	
	//size
	int size;
	
	/**
	 * Constructor both the head and tail are set to null
	 */
	public BasicDoubleLinkedList() {
		head = new Node<T>(null, null, null);
		tail = new Node<T>(head, null, null);
		head.setNext(tail);
		
		size = 0;
	}
	
	/**
	 * Inner node class for doubly linked list
	 * @param <AnyType>
	 */
	public class Node<AnyType>{
		public AnyType data;
		Node<AnyType> next;
		Node<AnyType> previous;
		
		/**
		 * Constructor for the first added element DoubleLinkedList
		 * @param data node data
		 */
		Node(AnyType data){
			
			this.next = null;
			this.data = data;
			this.previous = null;
		}
		
		/**
		 * Constructor for doubleLinkedList
		 * @param data node data
		 * @param next next node
		 * @param prev the previous node
		 */
		
		Node(Node<AnyType> prev, AnyType data, Node<AnyType> next){
			
			this.previous = prev;
			this.data = data;
			this.next = next;
		}
		
		public AnyType getData() {
			return data;
		}
		
		public void setData(AnyType data) {
			this.data = data;
		}
		
		public Node<AnyType> getNext(){
			return next;
		}
		
		public void setNext(Node<AnyType> next) {
			this.next = next;
		}
		
		public Node<AnyType> getPrevious(){
			return previous;
		}
		
		public void setPrevious(Node<AnyType> previous) {
			this.previous = previous;
		}
	}
	
	
	/**
	 * This method calls the ListIterator class to perform functions
	 * @return BasicDoubleLinkedListIterator class
	 * @throws NoSuchElementException
	 * @throws UnsupportedOperationException
	 */
	
	ListIterator<T> iterator() throws NoSuchElementException, UnsupportedOperationException{
		
		return new BasicDoubleLinkedlistIterator();
	}
	
	/**
	 * Inner class BasicDoubleLinkedListIterator the implement ListIterator<T>
	 */
	
	protected class BasicDoubleLinkedlistIterator implements ListIterator<T>{
		
		private Node<T> current = head.getNext();
		private int index = 0;
		
		/**
		 * Check if current node not null
		 * @return true or false
		 */
		@Override
		public boolean hasNext() {
			return index < getSize();
		}
		
		/**
		 * Go next node and get its data
		 * @return data of the node
		 */
		@Override
		public T next() {
			
			T data;
			
			//If this node is not null, return the data of this node and go next of this node
			if(hasNext()) {
				
				data = current.getData();
				current = current.getNext();
				index++;
				
				return data;
			}
			else {
				throw new NoSuchElementException();
			}
		}
		
		/**
		 * Check if current node not null
		 * @return true of false
		 */
		@Override
		public boolean hasPrevious() {
			
			return index > 0;
		}
		
		/**
		 * Go previous node and get its data
		 * @return data of the node
		 */
		@Override
		public T previous() {
			
			//If this node is not null, return the data of this node and go previous of this node
			if (hasPrevious()) {
				
				current = current.getPrevious();
				index--;
				T data = current.getData();
				
				return data;
			}
			else {
				throw new NoSuchElementException();
			}
		}
		
		
		//Unsupported methods
		@Override
		public int nextIndex() throws UnsupportedOperationException{
			
			throw new UnsupportedOperationException();
		}
		
		@Override
		public int previousIndex() throws UnsupportedOperationException{
			
			throw new UnsupportedOperationException();
		}
		
		@Override
		public void remove() throws UnsupportedOperationException{
			
			throw new UnsupportedOperationException();
		}
		
		@Override
		public void set(T e) throws UnsupportedOperationException{
			
			throw new UnsupportedOperationException();
		}
		
		@Override
		public void add(T e) throws UnsupportedOperationException{
			
			throw new UnsupportedOperationException();
		}
	}
	
	
	/**
	 * Adds elements to the front of the list. 
	 * @param data
	 * @return to the current object
	 */
	public BasicDoubleLinkedList<T> addToFront(T data){
		
		//Create a new node
		//The nowNode reference the first element(head) and the next of the first element(head.next)
		Node<T> newNode = new Node<T>(head, data, head.getNext());
		
		//Link newNode between head and head.next
		head.getNext().setPrevious(newNode);
		head.setNext(newNode);
		
		//Increment size
		size++;
		
		return this;
	}
	
	
	/**
	 * Adds an element to the end of the list.
	 * @param data
	 * @return reference to the current object
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data){
		
		//Create a new node
		//The nowNode reference the previous of the last element(tail.previous) and the last element(tail)
		Node<T> newNode = new Node<T>(tail.getPrevious(), data, tail);
				
		//Link newNode between tail and tail.previous
		tail.getPrevious().setNext(newNode);
		tail.setPrevious(newNode);
		newNode.setNext(tail);
				
		//Increment size
		size++;
				
		return this;
	}
	
	
	/**
	 * Removes the first instance of the targetData from the list.
	 * Use any of the other retrieval methods associated with the class in order to complete the removal process
	 * Use the provided comparator to find those elements that match the target.
	 * @param targetData
	 * @param comparator
	 * @return data element of null
	 */
	public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator){
		
		//return null if empty
		if(isEmpty()) {
			return null;
		}
		
		//copy the first element that contains data and start the while loop
		Node<T> current = head.getNext();
		
		//Traverse until the end of the list, until find the targetData
		while(current != null && comparator.compare(targetData, current.getData()) != 0) {
			current = current.getNext();
		}
		
		Node<T> nodeToEliminate = current;
		Node<T> predecessor = nodeToEliminate.getPrevious();
		Node<T> succesor = nodeToEliminate.getNext();
		
		//Unlink the nodeToEliminate and link between the predecessor and succesor
		predecessor.setNext(succesor);;
		succesor.setPrevious(predecessor);
		
		nodeToEliminate = null;
		
		//Decrement size
		size--;
		return this;
	}
	
	
	/**
	 * Removes and returns the first element from the list.If there are no elements the method returns null.
	 * @return data element or null
	 */
	public T retrieveFirstElement() {
		
		if(isEmpty()) {
			return null;
		}
		
		//head = null; head.next = first element with data
		Node<T> nodeToEliminate = head.getNext();
		Node<T> predecessor = nodeToEliminate.getPrevious();
		Node<T> succesor = nodeToEliminate.getNext();
		
		//Unlink the nodeToEliminate, and link between the predecessor and succesor
		predecessor.setNext(succesor);
		succesor.setPrevious(predecessor);
		
		return nodeToEliminate.getData();
	}
	
	
	/**
	 * Removes and returns the last element from the list. ifthere are no elements the method returns null.
	 * @return data element or null
	 */
	public T retrieveLastElement() {
		
		if(isEmpty()) {
			return null;
		}
		
		//tail = null; head.previous = last element with data
		Node<T> nodeToEliminate = tail.getPrevious();
		Node<T> predecessor = nodeToEliminate.getPrevious();
		Node<T> succesor = nodeToEliminate.getNext();
		
		//Unlink the nodeToEliminate and link between the predecessor and succesor
		predecessor.setNext(succesor);
		succesor.setPrevious(predecessor);
		
		return nodeToEliminate.getData();
	}
	
	/**
	 * Return the first element fo the list
	 * @return data element or null
	 */
	public T getFirst() {
		
		if(isEmpty()) {
			return null;
		}
		return head.getNext().data;
	}
	
	/**
	 * Return the last element of the lsit
	 * @return data element or null
	 */
	public T getLast() {
		
		if(isEmpty()) {
			return null;
		}
		return tail.getPrevious().data;
	}
	
	
	/**
	 * This method just returns the value of the instance variable use to keep track of size.
	 * @return the size of the linked list
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Return true if it is empty, otherwise false
	 * @return boolean true of false
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	
	/**
	 * Return and arraylist of the items in the list from head of list to tail of list
	 * @return an arraylist of the itesm in the lis
	 */
	public ArrayList<T> toArrayList(){
		
		ArrayList<T>listOfData = new ArrayList<T>();
		Node<T> current = head.getNext();
		
		int i = 0;
		while(current != null) {
			
			listOfData.add(current.getData());
			current = current.getNext();
			i++;
		}
		
		return listOfData;
	}
}