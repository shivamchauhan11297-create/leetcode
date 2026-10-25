import java.util.*;

class Solution {
    private void fill(int[][] g, int sr, int sc) {
        int m = g.length, n = g[0].length;
        Deque<int[]> st = new ArrayDeque<>();
        g[sr][sc] = 1;
        st.push(new int[]{sr, sc});
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        while (!st.isEmpty()) {
            int[] cur = st.pop();
            for (int d = 0; d < 4; d++) {
                int r = cur[0] + dr[d], c = cur[1] + dc[d];
                if (r >= 0 && r < m && c >= 0 && c < n && g[r][c] == 0) {
                    g[r][c] = 1;
                    st.push(new int[]{r, c});
                }
            }
        }
    }

    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        // remove land connected to the border
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 0) fill(grid, i, 0);
            if (grid[i][n - 1] == 0) fill(grid, i, n - 1);
        }
        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 0) fill(grid, 0, j);
            if (grid[m - 1][j] == 0) fill(grid, m - 1, j);
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    count++;
                    fill(grid, i, j);
                }
            }
        }
        return count;
    }
}