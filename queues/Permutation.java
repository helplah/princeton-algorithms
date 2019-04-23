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
        // 1, 2, 4, 8, 16, 32...
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        int counter = 0;

        while (!StdIn.isEmpty() && counter < k) {
            String input = StdIn.readString();
            queue.enqueue(input);
            counter++;
        }

        // prints k of them, uniformly at random and each item from the sequence at most once
        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }
    }
}
