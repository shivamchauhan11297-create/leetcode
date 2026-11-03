class Solution {
    public int minMoves(int[] nums) {
        long sum = 0;
        int max = Integer.MIN_VALUE;
        for (int x : nums) {
            sum += x;
            if (x > max) max = x;
        }
        return (int) ((long) nums.length * max - sum);
    }
}