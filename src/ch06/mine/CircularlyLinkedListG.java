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
package ch06.mine;

/**
 * An implementation of a circularly linked list.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public class CircularlyLinkedListG<E> {
	// ---------------- nested Node class ----------------
	/**
	 * Singly linked node, which stores a reference to its element and to the
	 * subsequent node in the list.
	 */
	private static class Node<E> {

		private E theElement; // an element stored at this node
		private Node<E> theNext; // a reference to the subsequent node in the list

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

	// instance variables of the CircularlyLinkedList
	private Node<E> theTail = null; // we store tail (but not head)
	private int theSize = 0;

	public CircularlyLinkedListG() {
	}

	public int size() {
		return theSize;
	}

	public boolean isEmpty() {
		return theSize == 0;
	}

	// Returns first element but does not remove it
	public E first() {
		if (isEmpty()) {
			return null;
		} else {
			Node<E> nextNode = theTail.getNext();
			E tmpElement = nextNode.getElement();
			return tmpElement;
		} // the head is *after* the tail within the circle
	}

	// Returns last element but does not remove it
	public E last() {
		if (isEmpty()) {
			return null;
		} else {
			return theTail.getElement();
		}
	}

	// update methods
	/**
	 * Rotate the first element to the back of the list.
	 */
	public void rotate() {
		if (theTail != null) {
			theTail = theTail.getNext();
		} else {
		}
	}

	public void addFirst(E aElement) { // adds element e to the front of the list
		if (theSize == 0) {
			theTail = new Node<>(aElement, null);
			theTail.setNext(theTail); // link to itself circularly
		} else {
			Node<E> newest = new Node<>(aElement, theTail.getNext());
			theTail.setNext(newest);
		}
		theSize = theSize + 1;
	}

	public void addLast(E aElement) {
		addFirst(aElement);
		theTail = theTail.getNext();
	}

	public E removeFirst() { // removes and returns the first element
		if (isEmpty()) {
			return null;
		} else {
			Node<E> head = theTail.getNext();
			if (head == theTail)
				theTail = null; // must be the only node left
			else
				theTail.setNext(head.getNext()); // removes "head" from the list
			theSize = theSize - 1;
			return head.getElement();
		}
	}

	public String toString() {
		if (theTail == null) {
			return "()";
		} else {
			StringBuilder tmpStringBuildeer = new StringBuilder("(");
			Node<E> walk = theTail;

			do {
				walk = walk.getNext();
				tmpStringBuildeer.append(walk.getElement());
				if (walk != theTail)
					tmpStringBuildeer.append(", ");
			} while (walk != theTail);

			tmpStringBuildeer.append(")");
			return tmpStringBuildeer.toString();
		}
	}
}
