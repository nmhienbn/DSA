import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private boolean[][] opened = null;
    private int countOpened;
    private int src;
    private int dst;
    private WeightedQuickUnionUF dsu = null;

    // convert 2D-coordinate to 1D-coordinate
    private int to1D(int row, int col) {
        if (row < 1 || col < 1 || row > n || col > n) {
            throw new IllegalArgumentException("Non-positive row or column!");
        }
        return (row - 1) * n + (col - 1);
    }

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Non-positive percolation system size!");
        }
        this.n = n;
        opened = new boolean[n][n];
        dsu = new WeightedQuickUnionUF(n * n + 5);
        src = n * n;
        dst = src + 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || col < 1 || row > n || col > n) {
            throw new IllegalArgumentException("Non-positive row or column!");
        }
        if (isOpen(row, col)) {
            return;
        }
        countOpened++;
        opened[row - 1][col - 1] = true;
        int vtc = to1D(row, col);
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = col - 1; c <= col + 1; c++) {
                if (r != row && c != col && 1 <= r && r <= n && 1 <= c && c <= n) {
                    if (isOpen(r, c)) {
                        dsu.union(vtc, to1D(r, c));
                    }
                }
            }
        }
        if (row == 1) {
            dsu.union(src, vtc);
        }
        if (row == n) {
            dsu.union(dst, vtc);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || col < 1 || row > n || col > n) {
            throw new IllegalArgumentException("Non-positive row or column!");
        }
        return opened[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || col < 1 || row > n || col > n) {
            throw new IllegalArgumentException("Non-positive row or column!");
        }
        if (!isOpen(row, col)) {
            return false;
        }
        return dsu.connected(src, to1D(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return countOpened;
    }

    // does the system percolate?
    public boolean percolates() {
        return dsu.connected(src, dst);
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}