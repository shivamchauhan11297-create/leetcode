class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        final int MAXV = 1024;

        boolean[][][] reach = new boolean[m][n][MAXV];
        reach[0][0][grid[0][0]] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                int v = grid[i][j];
                for (int x = 0; x < MAXV; x++) {
                    boolean ok = (i > 0 && reach[i - 1][j][x])
                              || (j > 0 && reach[i][j - 1][x]);
                    if (ok) reach[i][j][x ^ v] = true;
                }
            }
        }

        for (int x = 0; x < MAXV; x++) {
            if (reach[m - 1][n - 1][x]) return x;
        }
        return 0;
    }
}