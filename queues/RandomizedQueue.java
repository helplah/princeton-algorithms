/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item item;

    // construct an empty randomized queue
    public RandomizedQueue() {

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return false;
    }

    // return the number of items on the randomized queue
    public int size() {
        return 0;
    }

    // add the item
    public void enqueue(Item item) {

    }

    // remove and return a random item
    public Item dequeue() {
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {


        public boolean hasNext() {
            return false;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more items to return");
            }
            return item;
        }
    }

    // unit testing (optional)
    public static void main(String[] args) {

    }
}
