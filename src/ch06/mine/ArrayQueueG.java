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
 * Implementation of the queue ADT using a fixed-length array. All operations
 * are performed in constant time. An exception is thrown if an enqueue
 * operation is attempted when the size of the queue is equal to the length of
 * the array.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */

public class ArrayQueueG<E> implements QueueIntfG<E> {

	public static final int DEFAULT_CAPACITY = 1000;

	private E[] theData;

	private int theFrontIndex = 0;

	private int theSize = 0;

	// constructors
	public ArrayQueueG() {
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings({ "unchecked" })
	public ArrayQueueG(int aCapacity) {
		theData = (E[]) new Object[aCapacity]; // safe cast; compiler may give warning
	}

	@Override
	public int size() {
		return theSize;
	}

	@Override
	public boolean isEmpty() {
		return (theSize == 0);
	}

	@Override
	public void enqueue(E aElement) throws IllegalStateException {
		if (theSize == theData.length) {
			throw new IllegalStateException("Queue is full");
		} else {
			int available = (theFrontIndex + theSize) % theData.length; // use modular arithmetic
			theData[available] = aElement;
			theSize++;
		}
	}

	@Override
	public E first() {
		if (isEmpty()) {
			return null;
		} else {
			return theData[theFrontIndex];
		}
	}

	@Override
	public E dequeue() {
		if (isEmpty()) {
			return null;
		} else {
			E result = theData[theFrontIndex];
			theData[theFrontIndex] = null; // dereference to help garbage collection
			theFrontIndex = (theFrontIndex + 1) % theData.length;
			theSize--;
			return result;
		}
	}

	/**
	 * Returns a string representation of the queue as a list of elements. This
	 * method runs in O(n) time, where n is the size of the queue.
	 * 
	 * @return textual representation of the queue.
	 */
	public String toString() {
		StringBuilder tmpStringBuilder = new StringBuilder("(");
		int frontIndex = theFrontIndex;
		for (int j = 0; j < theSize; j++) {
			if (j > 0)
				tmpStringBuilder.append(", ");
			tmpStringBuilder.append(theData[frontIndex]);
			frontIndex = (frontIndex + 1) % theData.length;
		}
		tmpStringBuilder.append(")");
		return tmpStringBuilder.toString();
	}
}
