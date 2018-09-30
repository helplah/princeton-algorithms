/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            String[] sequence = StdIn.readString().split(" ");
            StdRandom.shuffle(sequence);

            // prints k of them, uniformly at random and each item from the sequence at most once
            for (int i = 0; i < k; i++) {
                System.out.println(sequence[i]);
            }
        }
    }
}
