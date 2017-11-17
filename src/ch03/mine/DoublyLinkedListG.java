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
 * A basic doubly linked list implementation.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public class DoublyLinkedListG<E> {

	private static class Node2<E> {

		private E theElement;
		private Node2<E> thePrevious;
		private Node2<E> theNext;

		public Node2(E aElement, Node2<E> aPrevious, Node2<E> aNext) {
			theElement = aElement;
			thePrevious = aPrevious;
			theNext = aNext;
		}

		public E getElement() {
			return theElement;
		}

		public Node2<E> getPrev() {
			return thePrevious;
		}

		public Node2<E> getNext() {
			return theNext;
		}

		// Update methods
		public void setPrev(Node2<E> aNode) {
			thePrevious = aNode;
		}

		public void setNext(Node2<E> aNode) {
			theNext = aNode;
		}
	} // ----------- end of nested Node class -----------

	// instance variables of the DoublyLinkedList
	private Node2<E> theHeader;
	private Node2<E> theTrailer;
	private int theSize = 0;

	/** Constructs a new empty list. */
	public DoublyLinkedListG() {
		// create header
		theHeader = new Node2<>(null, null, null);

		// trailer precedes header
		theTrailer = new Node2<>(null, theHeader, null);

		// trailer follows header
		theHeader.setNext(theTrailer);
	}

	public int size() {
		return theSize;
	}

	public boolean isEmpty() {
		return theSize == 0;
	}

	public E first() {
		if (isEmpty()) {
			return null;
		} else {
			// first element is beyond header
			Node2<E> tmpNode = theHeader.getNext();
			E tmpElement = tmpNode.getElement();
			return tmpElement;
		}
	}

	public E last() {
		if (isEmpty()) {
			return null;
		} else {
			// last element is before trailer
			Node2<E> tmpNode = theTrailer.getPrev();
			E tmpElement = tmpNode.getElement();
			return tmpElement;
		}
	}

	public void addFirst(E aElement) {
		// place just after the header
		Node2<E> tmpNode = theHeader.getNext();
		addBetween(aElement, theHeader, tmpNode);
	}

	public void addLast(E e) {
		// place just before the trailer
		Node2<E> tmpNode = theTrailer.getPrev();
		addBetween(e, tmpNode, theTrailer);
	}

	public E removeFirst() {
		if (isEmpty()) {
			return null;
		} else {
			// first element is beyond header
			return remove(theHeader.getNext());
		}
	}

	public E removeLast() {
		if (isEmpty()) {
			return null;
		} else {
			// last element is before trailer
			return remove(theTrailer.getPrev());
		}
	}

	private void addBetween(E aElement, Node2<E> aPredecessor, Node2<E> aSuccessor) {
		Node2<E> newNode = new Node2<>(aElement, aPredecessor, aSuccessor);
		aPredecessor.setNext(newNode);
		aSuccessor.setPrev(newNode);
		theSize = theSize + 1;
	}

	private E remove(Node2<E> aNode) {
		Node2<E> predecessor = aNode.getPrev();
		Node2<E> successor = aNode.getNext();

		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		theSize = theSize - 1;

		return aNode.getElement();
	}

	public String toString() {
		StringBuilder tmpStringBuilder = new StringBuilder("(");
		Node2<E> walk = theHeader.getNext();
		while (walk != theTrailer) {
			tmpStringBuilder.append(walk.getElement());
			walk = walk.getNext();
			if (walk != theTrailer) {
				tmpStringBuilder.append(", ");
			} else {
			}
		}
		tmpStringBuilder.append(")");
		return tmpStringBuilder.toString();
	}
} 
