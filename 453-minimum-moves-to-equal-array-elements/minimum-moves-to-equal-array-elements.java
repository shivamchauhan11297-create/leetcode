class Solution {
    public int minMoves(int[] nums) {
        long sum = 0;
        int min = Integer.MAX_VALUE;
        for (int x : nums) {
            sum += x;
            if (x < min) min = x;
        }
        return (int) (sum - (long) nums.length * min);
    }
}