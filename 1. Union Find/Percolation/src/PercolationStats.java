import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import static java.lang.Math.sqrt;

public class PercolationStats {
    private int n;
    private int trials;
    private double[] a = null;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n and trials must be positive!");
        }
        this.n = n;
        this.trials = trials;
        a = new double[trials];
        for (int i = 0; i < this.trials; i++) {
            a[i] = takeExperiment();
        }
    }

    // Take an experiment
    private double takeExperiment() {
        Percolation exp = new Percolation(this.n);
        int row;
        int col;
        do {
            do {
                row = StdRandom.uniformInt(1, this.n + 1);
                col = StdRandom.uniformInt(1, this.n + 1);
            } while (exp.isOpen(row, col));
            exp.open(row, col);
        } while (!exp.percolates());
        return exp.numberOfOpenSites() * 1.0 / (this.n * this.n);
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(a);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(a);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - 1.96 * this.stddev() / sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + 1.96 * this.stddev() / sqrt(trials);
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(2, 1000000);

        System.out.printf("mean                    = %f\n", stats.mean());
        StdOut.printf("stddev                  = %f\n", stats.stddev());
        StdOut.printf("95%% confidence interval = [%f, %f]\n", stats.confidenceLo(), stats.confidenceHi());
    }

}