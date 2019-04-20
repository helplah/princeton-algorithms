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
    private int size;
    private int index; // dequeue n sample index
    private boolean isShuffled = false;
    // [1, 2, 3, 4, null] - size = 4, array.length = 5

    public RandomizedQueue() {
        queue = (Item[]) new Object[2];
        size = 0;
        index = 0;
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
        // how to ensure dequeue returns a random item, and doesn't repeat the same index?
        // 1) shift null elements to the back, shuffle array then increment x
        // 2) shuffle array then increment index 0...

        // [9, 2, 1, 3, 5, 7, 8, 4, 6, 10] - shuffle 1...10 array to get this
        // [null, 2, 1, 3, 5, 7, 8, 4, 6, 10] - dequeue 9; size = 9;

        /*
        int randomIndex = StdRandom.uniform(size);
        Item item = queue[randomIndex];

        while (item == null) {
            item = queue[randomIndex];
        } */

        shuffle();
        Item item = queue[index];
        queue[index] = null;
        size--;
        index++;

        if (size > 0 && size == queue.length / 4) {
            resize(queue.length / 2);
        }
        return item;
    }

    public Item sample() {
        noSuchElementException();
        /*
        int randomIndex = StdRandom.uniform(size);
        Item item = queue[randomIndex];

        while (item == null) {
            item = queue[randomIndex];
        }*/

        return queue[index];
    }

    private void shuffle() {
        if (!isShuffled) {
            StdRandom.shuffle(queue);
        }
    }

    private void resize(int capacity) {
        // assert capacity >= size;

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
        private int index = 0;
        private boolean firstTime = false;

        public boolean hasNext() {
            return index < size - 1;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more items to return");
            }

            /*
            int randomIndex = StdRandom.uniform(size);
            Item item = queue[randomIndex];

            while (item == null) {
                item = queue[randomIndex];
            } */

            shuffle();
            if (!firstTime) {
                firstTime = true;
                return queue[index];
            }

            return queue[++index];
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        int size = queue.size();
        // queue.sample()
        System.out.println("Empty: " + queue.isEmpty() + ", size: " + size + ", sample: " + queue.sample());
        // StdOut.println("remove: " + queue.dequeue());

        for (Integer ele : queue) {
            StdOut.println(ele);
        }

        /*
        int a[] = new int[10];
        for (int x = 0; x < a.length; x++) {

        } */
    }
}
