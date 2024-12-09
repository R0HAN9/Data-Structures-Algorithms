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
    // Backtracking function to explore all subsets of the array
    public void backtrack(int[] nums, int index, int currentOR, int maxOR, int[] count) {
        // If the current OR matches the maximum OR, we found a valid subset
        if (currentOR == maxOR) {
            count[0]++; // Increment the count of subsets achieving the max OR
        }

        // Iterate through the array, starting from the current index
        for (int i = index; i < nums.length; i++) {
            // Include the current number in the subset and calculate the new OR
            backtrack(nums, i + 1, currentOR | nums[i], maxOR, count);
        }
    }

    public int countMaxOrSubsets(int[] nums) {
        // Step 1: Calculate the maximum possible OR value of the entire array
        int maxOR = 0;
        for (int num : nums) {
            maxOR |= num; // Perform bitwise OR with each element
        }

        // Step 2: Initialize a counter to keep track of subsets achieving the max OR
        int[] count = new int[1]; // Using an array to pass by reference

        // Step 3: Use backtracking to explore all subsets
        backtrack(nums, 0, 0, maxOR, count);

        // Step 4: Return the count of subsets that achieve the max OR
        return count[0];
    }
}
