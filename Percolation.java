/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int top_index;
    private int bottom_index;
    private int open_counter = 0;
    private boolean[][] grid;
    private final WeightedQuickUnionUF quickUFind;
    private int size;
    public static void main(String[] args) {
        Percolation myPerco = new Percolation(Integer.parseInt(args[0]));
    }

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        size = n;
        if (n <= 0) throw new IllegalArgumentException("n is either negative or equal to 0.");
        grid = new boolean[n][n];
        top_index = 0;
        bottom_index = n * n + 1;
        quickUFind = new WeightedQuickUnionUF(n * n + 2);
        open(1,1);
        open(1,2);
        open( 2, 1);
        prt();
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        if (row < 1 || col < 1 || row > size || col > size){
            throw new IllegalArgumentException("row or col out of boundaries");
        }
        if (this.isOpen(row, col)){
            return;
        }else {
            grid[row - 1][col - 1] = true;
            open_counter++;
            int qf_index = (row - 1) * size + col;

            //if on first row ... is connected to top
            if (row == 1) {
                System.out.println("Unifying index " + qf_index + " and " + top_index);
                this.quickUFind.union(qf_index, top_index);
            }
            //if on last row ... is connected to bottom
            if (row == size) {
                System.out.println("Unifying index " + qf_index + " and " + bottom_index);
                this.quickUFind.union(qf_index, bottom_index);
            }
            //if up is open
            if (row > 1 && isOpen(row-1, col)){
                int qf_up_index = (row - 2) * size + col;
                System.out.println("Unifying index " + qf_index + " and " + qf_up_index);
                this.quickUFind.union(qf_index, qf_up_index);
            }
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if (row < 1 || col < 1 || row > size || col > size){
            throw new IllegalArgumentException("row or col out of boundaries");
        }
        return grid[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        if (row < 1 || col < 1 || row > size || col > size){
            throw new IllegalArgumentException("row or col out of boundaries");
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return open_counter;
    }

    // does the system percolate?
    public boolean percolates(){
        return false;
    }

    private void prt(){
        for (int i=0; i<=this.size-1; i++){
            for (int j=0; j<=this.size-1; j++){
                System.out.print(this.grid[i][j]+"\t");
            }
            System.out.println();
        }

    }
}

