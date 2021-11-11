/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
    double[] results;
    int T;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        T = trials;
        if (n <= 0 || T <= 0){
            throw new IllegalArgumentException("n or trials is equal or inferior to negative.");
        }


        results = new double[T];
        for (int i = 0; i < T; i++){

            Percolation perco = new Percolation(n);
            int nb_cases = n*n;
            while (!perco.percolates()) {
                int r = StdRandom.uniform(1, n+1);
                int c = StdRandom.uniform(1, n+1);
                perco.open(r,c);

            }
            int nb_o_sites = perco.numberOfOpenSites();
            float ratio = (float)nb_o_sites / nb_cases;
            results[i] = ratio;



        }
        System.out.println("Mean : " + mean());
        System.out.println("Standard deviation : " + stddev());
        System.out.println("Confidence low : " + confidenceLo());
        System.out.println("Confidence high : " + confidenceHi());
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean() + 1.96 * stddev()/Math.sqrt(T);
    }

    // test client (see below)
    public static void main(String[] args){
        PercolationStats percostats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    }

}