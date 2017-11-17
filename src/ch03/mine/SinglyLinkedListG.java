/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ch03.mine;

/**
 * A basic singly linked list implementation.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public class SinglyLinkedListG<E> implements Cloneable {
	// ---------------- nested Node class ----------------
	/**
	 * Node of a singly linked list, which stores a reference to its element and to
	 * the subsequent node in the list (or null if this is the last node).
	 */
	private static class Node<E> {

		private E theElement;

		private Node<E> theNext;

		public Node(E aElement, Node<E> aNode) {
			theElement = aElement;
			theNext = aNode;
		}

		public E getElement() {
			return theElement;
		}

		public Node<E> getNext() {
			return theNext;
		}

		public void setNext(Node<E> aNode) {
			theNext = aNode;
		}
	} // ----------- end of nested Node class -----------

	// instance variables of the SinglyLinkedList
	private Node<E> theHead = null; // head node of the list (or null if empty)

	private Node<E> theTail = null; // last node of the list (or null if empty)

	private int theSize = 0;

	public SinglyLinkedListG() {
	}

	public int size() {
		return theSize;
	}

	public boolean isEmpty() {
		return theSize == 0;
	}

	public E first() { // returns (but does not remove) the first element
		if (isEmpty()) {
			return null;
		} else {
			return theHead.getElement();
		}
	}

	public E last() { // returns (but does not remove) the last element
		if (isEmpty()) {
			return null;
		} else {
			return theTail.getElement();
		}
	}

	public void addFirst(E aElement) { // adds element e to the front of the list
		theHead = new Node<>(aElement, theHead); // create and link a new node
		if (theSize == 0) {
			// special case: new node becomes tail also
			theTail = theHead;
		} else {
			theSize = theSize + 1;
		}
	}

	public void addLast(E e) {
		// newNode will eventually be the tai
		Node<E> newNode = new Node<>(e, null);
		if (isEmpty()) {
			// special case: previously empty list
			theHead = newNode;
		} else {
			// new node after existing tail
			theTail.setNext(newNode);
		}
		// new node becomes the tail
		theTail = newNode;
		theSize = theSize + 1;
	}

	public E removeFirst() { // removes and returns the first element
		if (isEmpty()) {
			return null;
		} else {
			E answer = theHead.getElement();
			theHead = theHead.getNext(); // will become null if list had only one node
			theSize = theSize - 1;
			if (theSize == 0) {
				// special case as list is now empty
				theTail = null;
			} else {
			}
			return answer;
		}
	}

	@SuppressWarnings({ "unchecked" })
	public boolean equals(Object aObject) {
		if (aObject == null)
			return false;

		if (getClass() != aObject.getClass())
			return false;

		SinglyLinkedListG other = (SinglyLinkedListG) aObject; // use nonparameterized type
		if (theSize != other.theSize)
			return false;

		Node walkA = theHead; // traverse the primary list
		Node walkB = other.theHead; // traverse the secondary list
		while (walkA != null) {
			if (!walkA.getElement().equals(walkB.getElement()))
				return false; // mismatch
			walkA = walkA.getNext();
			walkB = walkB.getNext();
		}
		return true; // if we reach this, everything matched successfully
	}

	@SuppressWarnings({ "unchecked" })
	public SinglyLinkedListG<E> clone() throws CloneNotSupportedException {

		// always use inherited Object.clone() to create the initial copy
		SinglyLinkedListG<E> other = (SinglyLinkedListG<E>) super.clone(); // safe cast

		if (theSize > 0) { 
			// we need independent chain of nodes
			other.theHead = new Node<>(theHead.getElement(), null);
			Node<E> walk = theHead.getNext(); // walk through remainder of original list
			Node<E> otherTail = other.theHead; // remember most recently created node

			while (walk != null) { // make a new node storing same element
				Node<E> newest = new Node<>(walk.getElement(), null);
				otherTail.setNext(newest); // link previous node to this one
				otherTail = newest;
				walk = walk.getNext();
			}
		}
		return other;
	}

	public int hashCode() {
		int h = 0;
		for (Node walk = theHead; walk != null; walk = walk.getNext()) {
			h ^= walk.getElement().hashCode(); // bitwise exclusive-or with element's code
			h = (h << 5) | (h >>> 27); // 5-bit cyclic shift of composite code
		}
		return h;
	}

	/**
	 * Produces a string representation of the contents of the list. This exists for
	 * debugging purposes only.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("(");
		Node<E> walk = theHead;
		while (walk != null) {
			sb.append(walk.getElement());
			if (walk != theTail)
				sb.append(", ");
			walk = walk.getNext();
		}
		sb.append(")");
		return sb.toString();
	}
}
