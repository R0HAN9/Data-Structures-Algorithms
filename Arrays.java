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
        
        int[] tails = new int[nums.length];
        int size = 0;

        for (int x : nums) {
            int i = 0;
            int j = size;

            while (i != j) {
                int m = (i + j) / 2;

                if (tails[m] < x) i = m + 1;
                else j = m;
            }

            tails[i] = x;
            if (i == size) ++size;
        }

        return size;
    }
}




// Longest Consecutive Sequence

class Solution {
    public int longestConsecutive(int[] nums) {
        
        int maxSeq = 0;
        Set<Integer> sequences = new HashSet<>();

        for (int num : nums) {
            sequences.add(num);
        }

        for (int num : nums) {

            int nextInSeq = num + 1;
            int prevInSeq = num - 1;
            int currentSequence = 1;

            while (sequences.remove(prevInSeq--)) currentSequence++;
            while (sequences.remove(nextInSeq++)) currentSequence++;

            if (currentSequence > maxSeq) maxSeq = currentSequence;

        }
        return maxSeq;
    }



// Game of Life


class Solution {
    public void gameOfLife(int[][] board) {
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                setAns(i, j, board);
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                if (board[i][j] == 3) {
                    board[i][j] = 1;
                }
                else if (board[i][j] == 4) {
                    board[i][j] = 0;
                }
            }
        }
    }

    public void setAns(int row, int col, int[][] board) {
        int countOne = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                if (i == 0 && j == 0) continue;

                int nrow = row + i;
                int ncol = col + j;

                if (nrow >= 0 && nrow < board.length && ncol >= 0 && ncol < board[0].length) {
                    if (board[nrow][ncol] == 1 || board[nrow][ncol] == 4) {
                        countOne++;
                    }
                }
            }
        }

        if (board[row][col] == 0 && countOne == 3) {
            board[row][col] = 3;
        }
        else if (board[row][col] == 1 && countOne > 3) {
            board[row][col] = 4;
        }
        else if (board[row][col] == 1 && countOne < 2) {
            board[row][col] = 4;
        }
    }
}
