import java.util.*;

class Solution {
    public int minimumChanges(String s, int k) {

        int n = s.length();
        int INF = 1000000000;

        // cost[i][j] = minimum changes needed
        // to make s[i...j] a semi-palindrome
        int[][] cost = new int[n][n];

        // Precompute cost for every substring
        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n; j++) {

                int len = j - i + 1;
                int best = INF;

                // d must divide len and d < len
                for (int d = 1; d < len; d++) {

                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;

                    // Check each of the d groups
                    for (int offset = 0; offset < d; offset++) {

                        int left = i + offset;
                        int right = j - d + 1 + offset;

                        // Compare mirrored characters
                        while (left < right) {

                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    best = Math.min(best, changes);
                }

                cost[i][j] = best;
            }
        }

        // dp[i][parts] =
        // minimum changes to divide s[0...i-1]
        // into 'parts' semi-palindromes
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }

        dp[0][0] = 0;

        // Build the partitions
        for (int i = 1; i <= n; i++) {

            for (int parts = 1; parts <= k; parts++) {

                // Previous partition ends at 'prev - 1'
                for (int prev = 0; prev < i; prev++) {

                    if (dp[prev][parts - 1] == INF) {
                        continue;
                    }

                    // Current substring = prev ... i-1
                    if (i - prev >= 2) {

                        dp[i][parts] = Math.min(
                            dp[i][parts],
                            dp[prev][parts - 1] + cost[prev][i - 1]
                        );
                    }
                }
            }
        }

        return dp[n][k];
    }
}