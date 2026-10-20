class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;
            if (check(nums, n, k)) ans += k;
        }
        return ans;
    }

    private boolean check(int[] nums, int n, int k) {
        int prevMax = Integer.MIN_VALUE;
        for (int s = 0; s < n; s += k) {
            int mn = Integer.MAX_VALUE, mx = Integer.MIN_VALUE;
            int descents = 0;
            for (int i = s; i < s + k; i++) {
                int nxt = (i + 1 < s + k) ? nums[i + 1] : nums[s];
                if (nums[i] > nxt) descents++;
                mn = Math.min(mn, nums[i]);
                mx = Math.max(mx, nums[i]);
            }
            if (descents > 1) return false;
            if (prevMax > mn) return false;
            prevMax = mx;
        }
        return true;
    }
}