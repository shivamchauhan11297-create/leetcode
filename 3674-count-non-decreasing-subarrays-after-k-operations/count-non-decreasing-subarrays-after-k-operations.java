import java.util.*;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {

        int n = nums.length;

        Deque<Integer> dq = new ArrayDeque<>();

        long cost = 0;
        long answer = 0;

        int right = n - 1;

        // Process from right to left
        for (int left = n - 1; left >= 0; left--) {

            // Merge smaller values into nums[left]
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[left]) {

                int index = dq.pollLast();

                int nextIndex = dq.isEmpty()
                        ? right + 1
                        : dq.peekLast();

                cost += (long) (nextIndex - index)
                        * (nums[left] - nums[index]);
            }

            dq.offerLast(left);

            // If cost is too large, shrink from the right
            while (cost > k) {

                cost -= (long) nums[dq.peekFirst()] - nums[right];

                if (dq.peekFirst() == right) {
                    dq.pollFirst();
                }

                right--;
            }

            // All subarrays [left...left], [left...left+1], ... [left...right]
            // are valid
            answer += right - left + 1L;
        }

        return answer;
    }
}