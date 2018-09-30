/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

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
        // assert check();
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

        if (isEmpty()) {
            last = first;
        } else if (size == 2) {
            // this equal last node
            last = oldFirst;
        }
        // assert check(); ????
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

        if (isEmpty()) {
            first = last;
        } else if (size == 2) {
            // this equal last node
            first = oldLast;
        }
        // assert check(); ????
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        Item item = first.item;
        first = first.next;
        size--;
        // assert check(); ????
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        Item item = last.item;
        last = last.prev;
        size--;
        // assert check(); ????
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
        Deque<String> deque = new Deque<String>();

        // test Deque methods
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (item.equals("q")) {
                break;
            } else if (item.equals("-")) {
                // problem
                StdOut.print(deque.removeLast());
            } else if (item.equals("+")) {
                StdOut.print(deque.removeFirst());
            } else if (item.contains("tail")) {
                // problem
                deque.addLast(item);
            } else {
                deque.addFirst(item);
            }
        }

        // test iterator with long code
        Iterator<String> iterator = deque.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            StdOut.println(s);
        }
        System.out.println();

        // test iterator with short code (forEach)
        for (String s : deque) {
            StdOut.println(s);
        }
        System.out.println();

        System.out.println("Size of deque = " + deque.size());
    }
}
