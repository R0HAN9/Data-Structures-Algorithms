// Remove Element


class Solution {
    public int removeElement(int[] nums, int val) {
        int k = 0; // Counter to track the position of non-val elements

        // Iterate through the array
        for (int i = 0; i < nums.length; i++) {
            // If the current element is not equal to the given value
            if (nums[i] != val) {
                nums[k] = nums[i]; // Copy the element to the current position of k
                k++; // Increment the counter
            }
        }

        return k; // Return the new length of the array without the `val` elements
    }
}



// Rotate Array


class Solution {
    public void rotate(int[] nums, int k) {
        k %= nums.length; // Ensure that k is within the bounds of the array length

        // Reverse the entire array
        reverse(nums, 0, nums.length - 1);

        // Reverse the first k elements
        reverse(nums, 0, k - 1);

        // Reverse the remaining elements
        reverse(nums, k, nums.length - 1);
    }

    // Helper method to reverse a portion of the array from index `left` to index `right`
    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            // Swap the elements at `left` and `right`
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;

            left++; // Move the left pointer to the right
            right--; // Move the right pointer to the left
        }
    }
}



// Find Peak Element

class Solution {
    public int findPeakElement(int[] nums) {
        
        int left = 0;  // Start with the leftmost index
        int right = nums.length - 1;  // Start with the rightmost index

        // Perform a binary search to find the peak element
        while (left < right) {
            int mid = (left + right) / 2;  // Find the middle index
            
            // If the middle element is greater than the next element,
            // the peak must be on the left side (including mid itself)
            if (nums[mid] > nums[mid + 1]) {
                right = mid;  // Narrow the search to the left side
            }
            else {
                left = mid + 1;  // Narrow the search to the right side
            }
        }

        // At the end, `left` will be the index of the peak element
        return left;
    }
}



// Find Minimum in Rotated Sorted Array


class Solution {
    public int findMin(int[] nums) {
        
        int left = 0;  // Start with the leftmost index
        int right = nums.length - 1;  // Start with the rightmost index

        // Perform binary search to find the minimum element
        while (left < right) {
            int mid = left + (right - left) / 2;  // Calculate the middle index

            // If the middle element is less than the rightmost element,
            // it means the minimum element is on the left side (including mid)
            if (nums[mid] < nums[right]) {
                right = mid;  // Narrow the search to the left side
            }
            else {
                left = mid + 1;  // Narrow the search to the right side
            }
        }

        // At the end, `left` will point to the minimum element
        return nums[left];
    }
}




// Median of Two Sorted Arrays


class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        // Step 1: Merge the two arrays into one sorted array
        double[] mergedArray = new double[nums1.length + nums2.length]; // Create a new array to hold both arrays
        int index = 0;

        // Add elements from nums1 to the merged array
        for (int num : nums1) {
            mergedArray[index++] = num;
        }

        // Add elements from nums2 to the merged array
        for (int num : nums2) {
            mergedArray[index++] = num;
        }

        // Step 2: Sort the merged array
        Arrays.sort(mergedArray); // Sort the merged array in ascending order

        // Step 3: Calculate the median
        int totalLength = mergedArray.length; // Total length of the merged array

        // If the total length is even, return the average of the two middle elements
        if (totalLength % 2 == 0) {
            return (mergedArray[totalLength / 2] + mergedArray[totalLength / 2 - 1]) / 2.0;
        }
        // If the total length is odd, return the middle element
        else {
            return mergedArray[totalLength / 2];
        }
    }
}




// Longest Increasing Subsequence

class Solution {
    public int lengthOfLIS(int[] nums) {
        
        // Step 1: Create an array 'tails' to store the smallest tail of all increasing subsequences of different lengths
        int[] tails = new int[nums.length];
        int size = 0;  // This will keep track of the length of the longest increasing subsequence

        // Step 2: Iterate over each element in the input array 'nums'
        for (int x : nums) {
            int i = 0;
            int j = size;

            // Step 3: Perform binary search to find the correct position of 'x' in the 'tails' array
            while (i != j) {
                int m = (i + j) / 2;

                // Step 4: If 'tails[m]' is smaller than 'x', move the left pointer 'i' to m+1
                // Otherwise, move the right pointer 'j' to 'm'
                if (tails[m] < x) i = m + 1;
                else j = m;
            }

            // Step 5: Assign 'x' to 'tails[i]'
            tails[i] = x;

            // Step 6: If 'i' is equal to 'size', increase the 'size' as we found a new longer subsequence
            if (i == size) ++size;
        }

        // Step 7: Return the length of the longest increasing subsequence found
        return size;
    }
}





// Longest Consecutive Sequence

class Solution {
    public int longestConsecutive(int[] nums) {
        
        int maxSequenceLength = 0;  // Store the length of the longest consecutive sequence
        Set<Integer> uniqueNums = new HashSet<>();  // A set to hold all unique numbers for O(1) lookups

        // Step 1: Add all numbers from 'nums' to the 'uniqueNums' set to eliminate duplicates
        for (int num : nums) {
            uniqueNums.add(num);
        }

        // Step 2: Iterate over each number in the array 'nums'
        for (int num : nums) {

            // Step 3: Skip the number if it is not the start of a sequence
            if (!uniqueNums.contains(num)) continue;

            int currentNum = num;
            int currentSequenceLength = 1;  // At least the number itself forms a sequence of length 1

            // Step 4: Check for consecutive numbers before the current number (num - 1, num - 2, ...)
            while (uniqueNums.remove(currentNum - 1)) {
                currentSequenceLength++;
                currentNum--;
            }

            // Step 5: Check for consecutive numbers after the current number (num + 1, num + 2, ...)
            currentNum = num;  // Reset currentNum to the original number
            while (uniqueNums.remove(currentNum + 1)) {
                currentSequenceLength++;
                currentNum++;
            }

            // Step 6: Update the maximum sequence length found
            maxSequenceLength = Math.max(maxSequenceLength, currentSequenceLength);
        }

        // Step 7: Return the length of the longest consecutive sequence
        return maxSequenceLength;
    }
}




// Game of Life


class Solution {
    public void gameOfLife(int[][] board) {
        
        // Step 1: Traverse the entire board to determine the next state for each cell
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                updateCellState(row, col, board);
            }
        }

        // Step 2: Update the board to reflect the next state
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == 3) {
                    board[row][col] = 1;  // Cell becomes alive
                } else if (board[row][col] == 4) {
                    board[row][col] = 0;  // Cell becomes dead
                }
            }
        }
    }

    // Helper method to determine the next state of a cell
    public void updateCellState(int row, int col, int[][] board) {
        int liveNeighbors = 0;

        // Count the live neighbors around the current cell
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;  // Skip the current cell

                int newRow = row + i;
                int newCol = col + j;

                // Check if the new row and column are within bounds
                if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length) {
                    // If the neighbor is alive (or previously alive), increment the count
                    if (board[newRow][newCol] == 1 || board[newRow][newCol] == 4) {
                        liveNeighbors++;
                    }
                }
            }
        }

        // Determine the next state based on the live neighbors
        if (board[row][col] == 0 && liveNeighbors == 3) {
            board[row][col] = 3;  // Cell becomes alive
        } else if (board[row][col] == 1 && (liveNeighbors < 2 || liveNeighbors > 3)) {
            board[row][col] = 4;  // Cell becomes dead
        }
    }
}

