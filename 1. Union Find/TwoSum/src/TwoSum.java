import edu.princeton.cs.algs4.*;

import java.util.Arrays;

public class TwoSum {
    public static void main(String[] args) {
        In in = new In("..\\..\\algs4-data\\1Kints.txt");
        int[] a = in.readAllInts();
        Arrays.sort(a);
        int j = a.length - 1;
        long ans = 0;
        int pre = 0;
        for (int i = 0; i < a.length; i++) {
            if (i > 0 && a[i] == a[i - 1]) {
                ans += pre;
                continue;
            }
            pre = 0;
            while (j >= 0 && a[i] + a[j] == 0) {
                pre++;
                j--;
            }
            ans += pre;
        }
        ans /= 2;
        StdOut.print(ans);
    }
}