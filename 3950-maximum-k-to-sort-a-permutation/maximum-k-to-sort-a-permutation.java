class Solution {
    public int sortPermutation(int[] nums) {
        int ans = -1; // all bits set
        boolean any = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                ans &= nums[i];
                any = true;
            }
        }
        return any ? ans : 0;
    }
}