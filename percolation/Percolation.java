/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF ufArray;
    private boolean[][] grid;
    private int size;
    private int openedIndex;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n is zero or below.");
        }
        size = n;

        ufArray = new WeightedQuickUnionUF(size * size + 2);
        grid = new boolean[size][size];
    }

    private void xyTo1D(int row, int col) {

    }

    private void validateIndices(int row, int col) {
        /*
        if (row <= 0 || row > size || col <= 0 || col > size) {
            throw new IllegalArgumentException(
                    "Row or column indices is outside the prescribed range.");
        }*/
    }

    private void linkLeft(int row, int col) {
        if (isOpen(row, col - 1)) {
            ufArray.union(openedIndex, openedIndex - 1);
        }
    }

    private void linkRight(int row, int col) {
        if (isOpen(row, col + 1)) {
            ufArray.union(openedIndex, openedIndex + 1);
        }
    }

    private void linkTop(int row, int col) {
        if (row == 1) {
            ufArray.union(openedIndex, 0);
        }
        else if (isOpen(row - 1, col)) {
            ufArray.union(openedIndex, openedIndex - size);
        }
    }

    private void linkBottom(int row, int col) {
        if (row == size) {
            ufArray.union(openedIndex, size * size + 1);
        }
        else if (isOpen(row + 1, col)) {
            ufArray.union(openedIndex, openedIndex + size);
        }
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        openedIndex = size * (row - 1) + col;

        // validateIndices(rowIndex, colIndex);
        grid[row - 1][col - 1] = true;

        /* links openedIndex to its open neighbours
            except for the four corners */
        if (col != 1 && col != size && row != 1 && row != size) {
            linkRight(row, col);
            linkLeft(row, col);
            linkTop(row, col);
            linkBottom(row, col);
        } // four corners
        else if (col == 1 || col == size) {
            linkTop(row, col);
            linkBottom(row, col);

            if (col == 1 && size > 1) {
                linkRight(row, col);
            }
            else if (col == size && size > 1) {
                linkLeft(row, col);
            }
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        int rowIndex = row - 1;
        int colIndex = col - 1;
        validateIndices(rowIndex, colIndex);

        if (grid[rowIndex][colIndex]) {
            return true;
        }
        return false;
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        int rowIndex = row - 1;
        int colIndex = col - 1;
        validateIndices(rowIndex, colIndex);

        // should I use isOpen(row, col)?
        // top??
        if (grid[rowIndex][colIndex] && ufArray.connected(openedIndex, ufArray.find(0))) {
            return true;
        }
        return false;
    }

    // number of open sites
    public int numberOfOpenSites() {
        // wrong there should not be for loop
        int counter = 0;
        for (int firstIndex = 0; firstIndex < grid.length; firstIndex++) {
            for (int secondIndex = 0; secondIndex < grid[secondIndex].length; secondIndex++) {
                counter++;
            }
        }

        return counter;
    }

    // does the system percolate?
    public boolean percolates() {
        if (ufArray.connected(0, size * size + 1)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Percolation testing = new Percolation(5);
        testing.open(2, 2);
        testing.open(3, 3);
    }
}
