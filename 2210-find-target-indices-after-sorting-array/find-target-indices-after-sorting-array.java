import java.util.*;

class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int less = 0, equal = 0;
        for (int x : nums) {
            if (x < target) less++;
            else if (x == target) equal++;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < equal; i++) {
            res.add(less + i);
        }
        return res;
    }
}