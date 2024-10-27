// Bitwise AND of Numbers Range

class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        
        int count = 0;
        while (left != right) {

            left >>= 1;
            right >>= 1;
            count++;
        }

        return (left << count);
    }
}



// Count Number of Maximum Bitwise-OR Subsets

class Solution {
    public void backtrack(int[] nums, int index, int currentOR, int maxOR, int[] count) {
        if (currentOR == maxOR) {
            count[0]++;
        }

        for (int i = index; i < nums.length; i++) {
            backtrack(nums, i + 1, currentOR | nums[i], maxOR, count);
        }
    }

    public int countMaxOrSubsets(int[] nums) {

        int maxOR = 0;
        for (int num : nums) {
            maxOR |= num;
        }

        int[] count = new int[1];
        backtrack(nums, 0, 0, maxOR, count);

        return count[0];
    }
}
