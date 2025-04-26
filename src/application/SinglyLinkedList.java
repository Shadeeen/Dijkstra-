
package application;

/* Singly Linked List to save edges for each vertex */
public class SinglyLinkedList<T extends Comparable<T>> {

	private Node<T> head;
	private Node<T> tail;

	// Method to add a new node at the start of the list
	public void addAtStart(T data) {
		Node<T> newNode = new Node<T>(data);
		if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			newNode.setNext(head);
			head = newNode;
		}
	}

	// Method to add a new node at the end of the list
	public void addAtEnd(T data) {
		Node<T> newNode = new Node<T>(data);
		if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			tail.setNext(newNode);
			tail = newNode;
		}
	}

	// Method to check if the list is empty
	public boolean isEmpty() {
		return this.head == null;
	}

	// Method to get the head of the list (first node)
	public Node<T> getHead() {
		return this.head;
	}

	// Method to get the tail of the list (last node)
	public Node<T> getTail() {
		return this.tail;
	}

}

//package application;
///*this double list to save the edges to each vertex by adding in the end*/
//public class DoublyEndedLinkedList<T extends Comparable<T>> {
//
//	private Node<T> head;
//	private Node<T> tail;
//
//	public void addAtStart(T data) { 
//		Node<T> newNode = new Node<T>(data);
//		if (head == null) {
//			head = newNode;
//			tail = newNode;
//		} else {
//			newNode.setNext(head);
//			head = newNode;
//		}
//	}
//
//	public void addAtEnd(T data) { 
//		Node<T> newNode = new Node<T>(data);
//		if (head == null) {
//			head = newNode;
//			tail = newNode;
//		} else {
//			tail.setNext(newNode);
//			tail = newNode;
//		}
//	}
//
//	public boolean isEmpty() {
//		return this.head == null;
//	}
//
//	public Node<T> getHead() {
//		return this.head;
//	}
//
//	public Node<T> getTail() {
//		return this.tail;
//	}
//}
