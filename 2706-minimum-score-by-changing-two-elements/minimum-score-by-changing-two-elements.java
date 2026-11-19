import java.util.*;

class Solution {
    public int minimizeSum(int[] nums) {

        Arrays.sort(nums);

        int n = nums.length;

        // Case 1:
        // Change the two largest elements
        int case1 = nums[n - 1] - nums[2];

        // Case 2:
        // Change the largest and the smallest element
        int case2 = nums[n - 2] - nums[1];

        // Case 3:
        // Change the two smallest elements
        int case3 = nums[n - 3] - nums[0];

        return Math.min(case1, Math.min(case2, case3));
    }
}