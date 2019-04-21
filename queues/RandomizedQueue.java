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
    // [1, 2, 3, 4, null] - size = 4, array.length = 5

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
        Item item = queue[randomIndex];

        return item;
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
        private int counter = 0;

        public boolean hasNext() {
            return counter < size;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more items to return");
            }

            int randomIndex = StdRandom.uniform(size);
            Item item = queue[randomIndex];
            counter++;

            return item;
        }
    }

    public static void main(String[] args) {
        /* Array.length grows from 1, 2, 4, 8, 16
         *
         * */

        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        int numOfItems = 1000;
        for (int x = 0; x < numOfItems; x++) {
            queue.enqueue(x);
        }

        int size = queue.size();
        // queue.sample()
        StdOut.println("Empty: " + queue.isEmpty() + ", size: " + size + ", sample: " + queue.sample());
        // StdOut.println("remove: " + queue.dequeue());
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
