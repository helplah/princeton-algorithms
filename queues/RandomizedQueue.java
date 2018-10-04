/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int size;

    public RandomizedQueue() {
        queue = (Item[]) new Object[2];
        size = 0;
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
        queue[randomIndex] = null;
        size--;

        if (size > 0 && size == queue.length / 4) {
            resize(queue.length / 2);
        }
        return item;
    }

    public Item sample() {
        noSuchElementException();

        return queue[StdRandom.uniform(queue.length)];
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
        private int index;

        public RandomIterator() {
            index = size - 1;
            StdRandom.shuffle(queue);
        }

        public boolean hasNext() {
            return index >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more items to return");
            }

            return queue[index--];
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.enqueue("5");
        int size = queue.size();
        System.out.println("Empty: " + queue.isEmpty() + ", size: " + size);
        for (int i = 0; i < size; i++) {
            System.out.println(queue.dequeue());
            System.out.println("Empty: " + queue.isEmpty() + ", size: " + queue.size());
        }
    }
}
