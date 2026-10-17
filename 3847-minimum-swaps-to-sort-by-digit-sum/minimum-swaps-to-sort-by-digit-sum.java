import java.util.*;

class Solution {
    private int digitSum(int x) {
        int s = 0;
        while (x > 0) {
            s += x % 10;
            x /= 10;
        }
        return s;
    }

    public int minSwaps(int[] nums) {
        int n = nums.length;
        Integer[] idx = new Integer[n];
        int[] ds = new int[n];
        for (int i = 0; i < n; i++) {
            idx[i] = i;
            ds[i] = digitSum(nums[i]);
        }

        Arrays.sort(idx, (a, b) -> {
            if (ds[a] != ds[b]) return Integer.compare(ds[a], ds[b]);
            return Integer.compare(nums[a], nums[b]);
        });

        // pos[i] = target position of the element currently at index i
        int[] pos = new int[n];
        for (int k = 0; k < n; k++) {
            pos[idx[k]] = k;
        }

        boolean[] visited = new boolean[n];
        int cycles = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                cycles++;
                int j = i;
                while (!visited[j]) {
                    visited[j] = true;
                    j = pos[j];
                }
            }
        }
        return n - cycles;
    }

}