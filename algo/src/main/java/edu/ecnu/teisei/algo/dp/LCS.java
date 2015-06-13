package edu.ecnu.teisei.algo.dp;


/**
 * Longest Common subsequence
 * OPT(n,m) = max{ 1 + OPT(n-1, m-1) , OPT(n-1, m) , OPT(n, m -1) }
 * Created by dingcheng on 2015/3/18.
 */
public class LCS<T extends Comparable<? super T>> {
    /* space: O(n^2) */
    public int getLCS(T[] a, T[] b) {
        int[][] dp;
        int n = a.length;
        int m = b.length;
        dp = new int[a.length+1][b.length+1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = -1;
            }
        }
        dp[1][1] = 0;
        dp[1][2] = 0;
        dp[2][1] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (dp[i][j] == -1) {
                    int x = 0;
                    if (a[i].compareTo(b[j])==0) {
                        x += 1 + dp[i - 1][j - 1];
                    }
                    dp[i][j] = Math.max(x, Math.max(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                System.out.print("" + dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(dp[n-1][m-1]);
        return dp[n-1][m-1];
    }

    /* space: O(n) */
    public int getLCS2(T[] a, T[] b) {
        int[][] dp = new int[2][b.length+1];
        int n = a.length;
        int m = b.length;
        //initial values
        for (int i = 0; i < b.length; i++) {
            dp[0][i] = 0;
        }
        dp[1][0] = 0;
        for (int i = 1; i < b.length; i++) {
            dp[1][i] = -1;
        }
        int pre_row;
        int now_row = 1;
        for (int i = 1; i <= n; i++) {
            now_row = i % 2;//当前行号:1 or 0
            pre_row = now_row == 1 ? 0 : 1;
            for (int j = 1; j <= m; j++) {
                int now_i = i - 1, now_j = j - 1;
                int x = 0;
                if (a[now_i].compareTo(b[now_j]) == 0) {
                    x += 1 + dp[pre_row][j-1];
                }
                dp[now_row][j] = Math.max(x, Math.max(dp[pre_row][j], dp[now_row][j - 1]));
            }
        }
        System.out.println(dp[now_row][m]);
        return dp[now_row][m];
    }
}
