
import java.util.*;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        final long MOD = 1_000_000_007L;
        int n = nums.size();
        int[] bits = new int[31];

        // Count how many numbers contain each set bit
        for (int num : nums) {
            for (int b = 0; b < 31; b++) {
                if ((num & (1 << b)) != 0) {
                    bits[b]++;
                }
            }
        }

        long answer = 0;

        // Construct the largest possible number k times
        for (int i = 0; i < k; i++) {
            long value = 0;

            for (int b = 30; b >= 0; b--) {
                if (bits[b] > 0) {
                    value |= (1L << b);
                    bits[b]--;
                }
            }

            answer = (answer + value * value) % MOD;
        }

        return (int) answer;
    }
}
