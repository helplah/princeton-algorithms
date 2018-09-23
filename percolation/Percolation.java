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
        openedIndex = size * (row - 1) + col;
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
        // if first row link top virtual site
        if (row == 1) {
            ufArray.union(openedIndex, 0);
        } else if (isOpen(row - 1, col)) {
            ufArray.union(openedIndex, openedIndex - size);
        }
    }

    private void linkBottom(int row, int col) {
        // if last row link bottom virtual site
        if (row == size) {
            ufArray.union(openedIndex, size * size + 1);
        } else if (isOpen(row + 1, col)) {
            ufArray.union(openedIndex, openedIndex + size);
        }
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        xyTo1D(row, col);

        // validateIndices(rowIndex, colIndex);
        grid[row - 1][col - 1] = true;

        /* links openedIndex to its open neighbours
            except for first and last row, first and last column */
        if (col != 1 && col != size && row != 1 && row != size) {
            linkRight(row, col);
            linkLeft(row, col);
            linkTop(row, col);
            linkBottom(row, col);
        } // first and last column
        else if (col == 1 || col == size) {
            linkTop(row, col);
            linkBottom(row, col);

            if (col == 1 && size > 1) {
                linkRight(row, col);
            } else if (col == size && size > 1) {
                linkLeft(row, col);
            }
        } // first and last row
        else if (row == 1 || row == size) {
            linkRight(row, col);
            linkLeft(row, col);
            linkTop(row, col);
            linkBottom(row, col);
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        int rowIndex = row - 1;
        int colIndex = col - 1;
        validateIndices(rowIndex, colIndex);

        return grid[rowIndex][colIndex];
    }

    // is site (row, col) open and connected to the virtual top site?
    public boolean isFull(int row, int col) {
        xyTo1D(row, col);

        int rowIndex = row - 1;
        int colIndex = col - 1;
        validateIndices(rowIndex, colIndex);

        // input2-no: 4 is in no way connected to 0, I can't use the last openedIndex
        // I need to use an array of first row sites and check if any of them is connected to 0
        /* During drawing, when isFull method is called, the result of all the tiles will be affected
            by openedIndex. openedIndex is overwritten during open method. If the last tile/input is not full,
            all the other tiles will become white even if they're full. */
        // I need to make sure (1,1) stays connected to 0
        System.out.println(row + " " + col + " - isOpen: " + isOpen(row, col) +
                ", connected: " + ufArray.connected(openedIndex, ufArray.find(0)));
        return isOpen(row, col) && ufArray.connected(openedIndex, ufArray.find(0));
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

    // is the top virtual site connected to the bottom virtual site?
    public boolean percolates() {
        return ufArray.connected(0, size * size + 1);
    }

    public static void main(String[] args) {
        Percolation testing = new Percolation(5);
        testing.open(2, 2);
        testing.open(3, 3);
    }
}
