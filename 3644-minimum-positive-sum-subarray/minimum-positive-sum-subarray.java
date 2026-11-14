class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {

        int n = nums.size();

        // Prefix sum
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums.get(i);
        }

        int answer = Integer.MAX_VALUE;

        // Start index
        for (int i = 0; i < n; i++) {

            // Try every allowed subarray length
            for (int len = l; len <= r; len++) {

                int j = i + len;

                // Subarray would go outside the array
                if (j > n) {
                    break;
                }

                // Sum of nums[i ... j-1]
                int sum = prefix[j] - prefix[i];

                // We need the smallest POSITIVE sum
                if (sum > 0) {
                    answer = Math.min(answer, sum);
                }
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}