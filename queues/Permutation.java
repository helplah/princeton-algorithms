/* *****************************************************************************
 *  Name: Permutation.java
 *  Date:
 *  Description: Princeton Algorithms Stacks and Queues (Week 2)
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        // StdOut.println("prints k string: " + k);
        RandomizedQueue<String> queue = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            String input = StdIn.readString();
            queue.enqueue(input);
            // StdOut.println("input: " + input);
        }

        // prints k of them, uniformly at random and each item from the sequence at most once
        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }
    }
}
