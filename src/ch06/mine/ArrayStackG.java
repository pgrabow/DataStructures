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
 * Implementation of the stack ADT using a fixed-length array. All operations
 * are performed in constant time. An exception is thrown if a push operation is
 * attempted when the size of the stack is equal to the length of the array.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public class ArrayStackG<E> implements StackIntfG<E> {

	public static final int DEFAULT_CAPACITY = 1000;
	private E[] theData;
	private int theTopIndex = -1;

	public ArrayStackG() {
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings({ "unchecked" })
	public ArrayStackG(int capacity) { // constructs stack with given capacity
		theData = (E[]) new Object[capacity]; // safe cast; compiler may give warning
	}

	@Override
	public int size() {
		return (theTopIndex + 1);
	}

	@Override
	public boolean isEmpty() {
		return (theTopIndex == -1);
	}

	@Override
	public void push(E e) throws IllegalStateException {
		if (size() == theData.length) {
			throw new IllegalStateException("Stack is full");
		} else {
			theTopIndex = theTopIndex + 1;
			theData[theTopIndex] = e;
		}
	}

	@Override
	public E top() {
		if (isEmpty()) {
			return null;
		} else {
			return theData[theTopIndex];
		}
	}

	@Override
	public E pop() {
		if (isEmpty()) {
			return null;
		} else {
			E result = theData[theTopIndex];
			theData[theTopIndex] = null; // dereference to help garbage collection
			theTopIndex = theTopIndex - 1;
			return result;
		}
	}

	public String toString() {
		StringBuilder tmpStringBuilder = new StringBuilder("(");
		for (int j = theTopIndex; j >= 0; j--) {
			tmpStringBuilder.append(theData[j]);
			if (j > 0)
				tmpStringBuilder.append(", ");
		}
		tmpStringBuilder.append(")");
		return tmpStringBuilder.toString();
	}

	/** Demonstrates sample usage of a stack. */
	public static void main(String[] args) {
		StackIntfG<Integer> S = new ArrayStackG<>(); // contents: ()
		S.push(5); // contents: (5)
		S.push(3); // contents: (5, 3)
		System.out.println(S.size()); // contents: (5, 3) outputs 2
		System.out.println(S.pop()); // contents: (5) outputs 3
		System.out.println(S.isEmpty()); // contents: (5) outputs false
		System.out.println(S.pop()); // contents: () outputs 5
		System.out.println(S.isEmpty()); // contents: () outputs true
		System.out.println(S.pop()); // contents: () outputs null
		S.push(7); // contents: (7)
		S.push(9); // contents: (7, 9)
		System.out.println(S.top()); // contents: (7, 9) outputs 9
		S.push(4); // contents: (7, 9, 4)
		System.out.println(S.size()); // contents: (7, 9, 4) outputs 3
		System.out.println(S.pop()); // contents: (7, 9) outputs 4
		S.push(6); // contents: (7, 9, 6)
		S.push(8); // contents: (7, 9, 6, 8)
		System.out.println(S.pop()); // contents: (7, 9, 6) outputs 8
	}
}
