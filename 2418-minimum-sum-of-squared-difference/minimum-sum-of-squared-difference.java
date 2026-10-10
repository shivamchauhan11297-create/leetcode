class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long[] diff = new long[n];
        long total = (long) k1 + k2;
        long maxDiff = 0;
        long sumDiff = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diff[i]);
            sumDiff += diff[i];
        }

        if (total >= sumDiff) {
            return 0;
        }

        long left = 0, right = maxDiff;

        while (left < right) {
            long mid = left + (right - left) / 2;
            long needed = 0;

            for (long d : diff) {
                if (d > mid) {
                    needed += d - mid;
                }
            }

            if (needed <= total) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        long answer = 0;
        long used = 0;

        for (long d : diff) {
            if (d > left) {
                used += d - left;
                d = left;
            }
            answer += d * d;
        }

        long remaining = total - used;
        answer -= remaining * (2 * left - 1);

        return answer;
    }
}