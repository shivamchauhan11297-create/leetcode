class Solution {
    private int m, n;

    public int countIslands(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    long sum = dfs(grid, i, j);
                    if (sum % k == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private long dfs(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= m || c >= n || grid[r][c] <= 0) {
            return 0;
        }
        long total = grid[r][c];
        grid[r][c] = 0;  // mark visited

        total += dfs(grid, r + 1, c);
        total += dfs(grid, r - 1, c);
        total += dfs(grid, r, c + 1);
        total += dfs(grid, r, c - 1);
        return total;
    }
}