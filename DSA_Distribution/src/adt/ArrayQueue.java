/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import java.util.Iterator;

/**
 *
 * @author Asus
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    private T[] donationList;
    private final static int firstIndex = 0;
    private int lastIndex;
    private static final int DONATION_SIZE = 100;

    public ArrayQueue() {
        this(DONATION_SIZE);
    }

    public ArrayQueue(int initialCapacity) {
        donationList = (T[]) new Object[initialCapacity];
        lastIndex = -1;
    }

    public void enqueue(T newEntry) {
        if (!isArrayFull()) {
            lastIndex++;
            donationList[lastIndex] = newEntry;
        }
    }

    public T getFront() {
        T front = null;
        if (!isEmpty()) {
            front = donationList[firstIndex];
        }
        return front;
    }

    public T dequeue() {
        T front = null;
        if (!isEmpty()) {
            front = donationList[firstIndex];      // shift remaining array items forward one position      
            for (int i = firstIndex; i < lastIndex; ++i) {
                donationList[i] = donationList[i + 1];
            }
            lastIndex--;
        }
        return front;
    }

    public boolean isEmpty() {
        return firstIndex > lastIndex;
    }

    public void clear() {
        if (!isEmpty()) { // deallocates only the used portion      
            for (int index = firstIndex; index <= lastIndex; index++) {
                donationList[index] = null;
            }
            lastIndex = -1;
        }
    }

    private boolean isArrayFull() {
        return lastIndex == donationList.length - 1;
    }

    public Iterator<T> getIterator() {
        return new ArrayQueueIterator();
    }

    @Override
    public int size() {
        return lastIndex + 1;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return donationList[firstIndex + index];
    }

    private class ArrayQueueIterator implements Iterator<T> {

        private int nextIndex;

        private ArrayQueueIterator() {
            nextIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return nextIndex <= lastIndex;
        }

        @Override
        public T next() {
            if (hasNext()) {
                T nextEntry = donationList[nextIndex];
                nextIndex++;
                return nextEntry;
            } else {
                return null;
            }
        }
    }

}
