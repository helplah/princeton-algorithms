/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node pointer;

    private class Node {
        Item item;
        Node next;
    }

    public Deque() {
        pointer = null;
        size = 0;
        // assert check();
    }

    public boolean isEmpty() {
        return pointer == null;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item added to front is null");
        }

        Node oldFirst = pointer;
        pointer = new Node();
        pointer.item = item;
        pointer.next = oldFirst;
        size++;
        // assert check(); ????
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item added to end is null");
        }

        pointer = new Node();
        
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        Item item = pointer.item;
        pointer = pointer.next;
        size--;
        // assert check(); ????
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

    }

    public Iterator<Item> iterator() {

    }

    public static void main(String[] args) {
        System.out.println("Works");
    }
}
