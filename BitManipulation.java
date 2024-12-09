// Bitwise AND of Numbers Range

class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        // Initialize a counter to keep track of the number of right shifts
        int count = 0;

        // Keep shifting both left and right until they become equal
        while (left != right) {
            // Right shift both left and right by 1 (this effectively removes the rightmost bit)
            left >>= 1;
            right >>= 1;

            // Increment the counter to track how many bits have been shifted
            count++;
        }

        // Once left equals right, all differing bits have been eliminated
        // Left shift the common value back to restore the original bit positions
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
