class Solution {
    public int uniquePaths(int[][] grid) {
        final long MOD = 1_000_000_007L;
        int m = grid.length, n = grid[0].length;

        long[][] inR = new long[m][n];
        long[][] inD = new long[m][n];
        long last = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long a = inR[i][j] % MOD;
                long b = inD[i][j] % MOD;

                boolean start = (i == 0 && j == 0);
                if (start || grid[i][j] == 0) {
                    long ways = start ? 1 : (a + b) % MOD;
                    if (i == m - 1 && j == n - 1) last = ways;
                    if (j + 1 < n) inR[i][j + 1] = (inR[i][j + 1] + ways) % MOD;
                    if (i + 1 < m) inD[i + 1][j] = (inD[i + 1][j] + ways) % MOD;
                } else {
                    // mirror: right-entry turns down, down-entry turns right
                    if (i + 1 < m) inD[i + 1][j] = (inD[i + 1][j] + a) % MOD;
                    if (j + 1 < n) inR[i][j + 1] = (inR[i][j + 1] + b) % MOD;
                }
            }
        }
        return (int) (last % MOD);
    }
}