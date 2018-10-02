/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue queue = new RandomizedQueue();

        while (!StdIn.isEmpty()) {
            String input = StdIn.readString();
            queue.enqueue(input);

            // prints k of them, uniformly at random and each item from the sequence at most once
            for (int i = 0; i < k; i++) {
                StdOut.println(queue.dequeue());
            }
        }
    }
}
