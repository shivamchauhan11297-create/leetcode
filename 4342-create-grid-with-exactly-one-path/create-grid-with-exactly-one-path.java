import java.util.*;

class Solution {
    public String[] createGrid(int m, int n) {
        String[] res = new String[m];

        char[] top = new char[n];
        Arrays.fill(top, '.');
        res[0] = new String(top);

        for (int i = 1; i < m; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '#');
            row[n - 1] = '.';
            res[i] = new String(row);
        }
        return res;
    }
}