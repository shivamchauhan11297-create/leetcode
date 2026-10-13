import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] sq = new long[n];
        for (int i = 0; i < n; i++) {
            sq[i] = (long) nums[i] * nums[i];
        }
        Arrays.sort(sq); // ascending

        int addCount = (n + 1) / 2;
        long score = 0;
        // largest addCount values are added
        for (int i = n - 1; i >= n - addCount; i--) {
            score += sq[i];
        }
        // remaining smaller values are subtracted
        for (int i = n - addCount - 1; i >= 0; i--) {
            score -= sq[i];
        }
        return score;
    }
}