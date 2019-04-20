/* *****************************************************************************
 *  Name: Deque.java
 *  Date:
 *  Description: Princeton Algorithms Stacks and Queues (Week 2)
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node first;
    private Node last;

    private class Node {
        Item item;
        Node prev;
        Node next;
    }

    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item added to front is null");
        }

        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.prev = null;
        first.next = oldFirst;
        size++;

        // link next node to first node, if next node has data
        if (first.next != null) {
            oldFirst.prev = first;
        }

        if (size == 1) {
            // if deque has 1 node, this is the first and last node
            last = first;
        } else if (size == 2) {
            // if deque has 2 nodes, next node is last node
            last = oldFirst;
        }
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item added to end is null");
        }

        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.prev = oldLast;
        last.next = null;
        size++;

        // link prev node to last node, if prev node has data
        if (last.prev != null) {
            oldLast.next = last;
        }

        if (size == 1) {
            // if deque has 1 node, this is the first and last node
            first = last;
        } else if (size == 2) {
            // if deque has 2 nodes, prev node is first node
            first = oldLast;
        }
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        Item item = first.item;
        // if deque has 1 node, set both prev and next node to null
        if (size == 1) {
            first.next = null;
        } else {
            first = first.next;
        }

        first.prev = null;
        size--;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        Item item = last.item;
        // if deque has 1 node, set both prev and next node to null
        if (size == 1) {
            last.prev = null;
        } else {
            last = last.prev;
        }

        last.next = null;
        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more items to return");
            }

            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        System.out.println("isEmpty: " + deque.isEmpty());
        System.out.println("removed element: " + deque.removeFirst());
        System.out.println("size: " + deque.size());

        /*
        for (int ele : deque) {
            StdOut.println(ele);
        } */
    }
}
