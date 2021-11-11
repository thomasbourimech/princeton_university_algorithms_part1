/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int topIndex;
    private int bottomIndex;
    private int openCounter = 0;
    private boolean[][] grid;
    private final WeightedQuickUnionUF weightedQuickUnionFind;

    private int size;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        size = n;
        if (n <= 0) throw new IllegalArgumentException("n is either negative or "
                                                               + "equal to 0.");
        grid = new boolean[n][n];
        topIndex = 0;
        bottomIndex = n * n + 1;
        weightedQuickUnionFind = new WeightedQuickUnionUF(n * n + 2);

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || col < 1 || row > size || col > size) {
            throw new IllegalArgumentException("row or col is out of boundaries");
        }
        if (!this.isOpen(row, col)) {
            grid[row - 1][col - 1] = true;
            openCounter++;
            int qf_index = (row - 1) * size + col;

            // if on first row ... is connected to top
            if (row == 1) {
                // System.out.println("Unifying index " + qf_index + " and "
                //                           + top_index);
                this.weightedQuickUnionFind.union(qf_index, topIndex);
            }
            // if on last row ... is connected to bottom
            if (row == size) {
                //System.out.println("Unifying index " + qf_index + " and " + bottom_index);
                this.weightedQuickUnionFind.union(qf_index, bottomIndex);
            }
            // if up is open
            if (row > 1 && isOpen(row-1, col)) {
                int qf_up_index = qf_index - size;
                //System.out.println("Unifying index " + qf_index + " and " + qf_up_index);
                this.weightedQuickUnionFind.union(qf_index, qf_up_index);
            }
            // if left is open
            if (col > 1 && isOpen(row, col-1)) {
                //int qf_left_index = row * size - col - 1  ;
                int qf_left_index = qf_index - 1  ;
                //System.out.println("Unifying index " + qf_index + " and " + qf_left_index);
                this.weightedQuickUnionFind.union(qf_index, qf_left_index);
            }

            // if right is open
            if (col < size && isOpen(row , col+1)) {
                int qf_right_index = qf_index + 1;
                //System.out.println("Unifying index " + qf_index + " and " + qf_right_index);
                this.weightedQuickUnionFind.union(qf_index, qf_right_index);
            }

            // if down is open
            if (row < size && isOpen(row+1 , col)) {
                int qf_down_index = qf_index + size;
                //System.out.println("Unifying index " + qf_index + " and " + qf_down_index);
                this.weightedQuickUnionFind.union(qf_index, qf_down_index);
            }
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || col < 1 || row > size || col > size) {
            throw new IllegalArgumentException("row or col out of boundaries");
        }
        return grid[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || col < 1 || row > size || col > size) {
            throw new IllegalArgumentException("row or col out of boundaries");
        }
        int qf_index = (row - 1) * size + col;
        return this.weightedQuickUnionFind.find(qf_index) == this.weightedQuickUnionFind.find(0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCounter;
    }

    // does the system percolate?
    public boolean percolates() {
        return this.weightedQuickUnionFind.find(bottomIndex) ==
               this.weightedQuickUnionFind.find(topIndex);

    }


}


