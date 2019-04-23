/* *****************************************************************************
 *  Name: RandomizedQueue.java
 *  Date:
 *  Description:  Princeton Algorithms Stacks and Queues (Week 2)
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int size = 0;
    // [1, 2, null, null] - size = 2, array.length = 4

    public RandomizedQueue() {
        queue = (Item[]) new Object[2];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Enqueue item cannot be null");
        } else if (size == queue.length) {
            resize(2 * queue.length);
        }

        queue[size++] = item;
    }

    public Item dequeue() {
        noSuchElementException();
        int randomIndex = StdRandom.uniform(size);
        Item item = queue[randomIndex];

        int lastIndex = size - 1;
        queue[randomIndex] = queue[lastIndex];
        queue[lastIndex] = null;
        size--;

        if (size > 0 && size == queue.length / 4) {
            resize(queue.length / 2);
        }
        return item;
    }

    public Item sample() {
        noSuchElementException();
        int randomIndex = StdRandom.uniform(size);

        return queue[randomIndex];
    }

    private void resize(int capacity) {
        assert capacity >= size;

        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
    }

    private void noSuchElementException() {
        if (isEmpty()) {
            throw new NoSuchElementException("Randomized queue is empty");
        }
    }

    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private int numOfItems = size;
        private int[] nonNull;

        // All elements must be returned only once, except for null values
        private RandomIterator() {
            // nonNull array is required to store and shuffle the index 0 to size of nonNull values in queue
            nonNull = new int[numOfItems];
            // doesn't matter whether pre or post increment is used in the third section of for loop, since update is last
            for (int x = 0; x < numOfItems; x++) {
                nonNull[x] = x; // if numOfItems = 10, 0...9
            }
            StdRandom.shuffle(nonNull);
        }

        public boolean hasNext() {
            return numOfItems > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more items to return");
            }

            return queue[nonNull[--numOfItems]]; // if numOfItems = 10, 0...9
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        int numOfItems = 10;
        for (int x = 0; x < numOfItems; x++) {
            queue.enqueue(x);
        }

        /*
        queue.enqueue(10);
        StdOut.println("remove: " + queue.dequeue());
        StdOut.println("remove: " + queue.dequeue());
        StdOut.println("remove: " + queue.dequeue());
        StdOut.println("remove: " + queue.dequeue()); */

        int size = queue.size();
        // + queue.sample()
        StdOut.println("Empty: " + queue.isEmpty() + ", size: " + size + ", sample: ");

        StdOut.println("Testing iterator below: ");
        int numOfPrintedItems = 0;
        for (Integer ele : queue) {
            StdOut.println(ele);
            numOfPrintedItems++;
        }
        StdOut.println("numOfPrintedItems: " + numOfPrintedItems);

        /*
        for (int x = 0; x < numOfItems; x++) {
            StdOut.println("sample: " + queue.sample());
        } */

        /*
        int numOfRemovedItems = 0;
        for (int x = 0; x < numOfItems; x++) {
            StdOut.println("remove: " + queue.dequeue());
            numOfRemovedItems++;
        }
        StdOut.println("numOfRemovedItems: " + numOfRemovedItems); */
    }
}
