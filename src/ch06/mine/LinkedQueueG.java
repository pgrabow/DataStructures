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
 * Realization of a FIFO queue as an adaptation of a SinglyLinkedList. All
 * operations are performed in constant time.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 * @see SinglyLinkedListG
 */
public class LinkedQueueG<E> implements QueueIntfG<E> {

	private SinglyLinkedListG<E> list = new SinglyLinkedListG<>();

	public LinkedQueueG() {
	} // new queue relies on the initially empty list

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	// Inserts an element at the rear of queue.
	@Override
	public void enqueue(E element) {
		list.addLast(element);
	}

	// Returns, but does not remove, the first element of queue.
	@Override
	public E first() {
		return list.first();
	}

	// Removes and returns the first element of queue.
	@Override
	public E dequeue() {
		return list.removeFirst();
	}

	public String toString() {
		return list.toString();
	}
}
