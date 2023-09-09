import edu.princeton.cs.algs4.*;

import java.util.Arrays;

public class ThreeSum {
    public static int TwoSum(int[] a, int u, int sum) {
        int j = a.length - 1;
        int ans = 0;
        int pre = 0;
        for (int i = u; i < a.length; i++) {
            if (i > u && a[i] == a[i - 1]) {
                ans += pre;
                continue;
            }
            pre = 0;
            while (j >= u && a[i] + a[j] == sum) {
                pre++;
                j--;
            }
            ans += pre;
        }
        return ans / 2;
    }
    public static void main(String[] args) {
        In in = new In("..\\..\\algs4-data\\1Kints.txt");
        int[] a = in.readAllInts();
        Arrays.sort(a);
        long ans = 0;
        int pre = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > 0) {
                break;
            }
            if (i > 0 && a[i] == a[i - 1]) {
                ans += pre;
                continue;
            }
            pre = TwoSum(a, i, -a[i]);
            ans += pre;
        }
        StdOut.print(ans);
    }
}