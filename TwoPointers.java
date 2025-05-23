// Remove Duplicates from Sorted Array

class Solution {
    public int removeDuplicates(int[] nums) {
        // If the array is empty, no elements to process, return 0
        if (nums.length == 0) return 0;

        // Pointer to track the position of the next unique element
        int idx = 1;

        // Loop through the array starting from the second element
        for (int j = 1; j < nums.length; j++) {
            // Check if the current element is different from the previous unique element
            if(nums[j] != nums[idx - 1]) {
                // Place the current unique element at the idx position
                nums[idx] = nums[j];
                // Move the index forward
                idx++;
            }
        }

        // Return the count of unique elements
        return idx;
    }
}



// Container With Most Water


class Solution {
      public int maxArea(int[] height) {
        // Variable to store the maximum area found
        int maxArea = 0;

        // Two pointers: one at the start and one at the end of the array
        int left = 0;
        int right = height.length - 1;

        // Iterate while the left pointer is less than the right pointer
        while (left < right) {
            // Calculate the area formed by the current pointers
            maxArea = Math.max(maxArea, (right - left) * Math.min(height[left], height[right]));

            // Move the pointer pointing to the shorter line inward
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        // Return the maximum area found
        return maxArea;        
    }
}
}


// 2. 3Sum


class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // Initialize a list to store the resulting triplets.
        List<List<Integer>> res = new ArrayList<>();
        
        // Sort the array to allow the use of the two-pointer technique.
        Arrays.sort(nums);

        // Iterate through the array with the first pointer, 'i'.
        for (int i = 0; i < nums.length; i++) {
            // Skip duplicate values for the first element to avoid duplicate triplets.
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            // Initialize the two pointers: 
            // 'j' starts right after 'i' (as the second element),
            // 'k' starts at the end of the array (as the third element).
            int j = i + 1;
            int k = nums.length - 1;

            // Use the two-pointer technique to find triplets that sum to zero.
            while (j < k) {
                // Calculate the sum of the current triplet.
                int total = nums[i] + nums[j] + nums[k];

                if (total > 0) {
                    // If the sum is greater than zero, move the right pointer 'k' leftward 
                    // to decrease the sum.
                    k--;
                } else if (total < 0) {
                    // If the sum is less than zero, move the left pointer 'j' rightward 
                    // to increase the sum.
                    j++;
                } else {
                    // If the sum is zero, add the triplet to the result list.
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++; // Move the left pointer to find other potential triplets.

                    // Skip duplicate values for the second element to avoid duplicate triplets.
                    while (nums[j] == nums[j - 1] && j < k) {
                        j++;
                    }
                }
            }
        }

        // Return the list of all unique triplets that sum to zero.
        return res;        
    }
}



// 3. Longest Palindromic Substring



class Solution {
    public String longestPalindrome(String s) {
        // Edge case: if the string is null or empty, return an empty string.
        if (s == null || s.length() == 0) return "";
        
        // Variables to store the start and end indices of the longest palindrome found.
        int start = 0;
        int end = 0;

        // Iterate through each character in the string as a potential center of a palindrome.
        for (int i = 0; i < s.length(); i++) {
            // Check for odd-length palindromes (single character center).
            int odd = expandAroundCenter(s, i, i);
            
            // Check for even-length palindromes (two-character center).
            int even = expandAroundCenter(s, i, i + 1);
            
            // Find the maximum length between odd and even palindromes.
            int maxLen = Math.max(odd, even);

            // Update the start and end indices if a longer palindrome is found.
            if (maxLen > end - start) {
                // Calculate the start index of the palindrome.
                start = i - (maxLen - 1) / 2;
                
                // Calculate the end index of the palindrome.
                end = i + maxLen / 2;
            }
        }

        // Extract and return the longest palindrome substring using the start and end indices.
        return s.substring(start, end + 1);
    }

    // Helper function to expand around the center and calculate palindrome length.
    private int expandAroundCenter(String s, int left, int right) {
        // Expand outward as long as the characters match and indices are within bounds.
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--; // Move the left pointer outward.
            right++; // Move the right pointer outward.
        }

        // Calculate the length of the palindrome.
        return right - left - 1; // Subtract 1 because left and right have moved past the valid range.
    }
}



// Two Sum II - Input Array Is Sorted


class Solution {
    public int[] twoSum(int[] numbers, int target) {
        // Create an array to store the result (1-based indices of the two numbers).
        int[] ans = new int[2];
        
        // Initialize two pointers:
        // 'left' starts at the beginning of the array.
        int left = 0;
        
        // 'right' starts at the end of the array.
        int right = numbers.length - 1;

        // Use the two-pointer technique to find the two numbers that sum up to the target.
        while (left < right) {
            // Calculate the sum of the two numbers at the current pointers.
            int sum = numbers[left] + numbers[right];

            if (sum < target) {
                // If the sum is less than the target, move the left pointer to the right 
                // to increase the sum.
                left++;
            } else if (sum > target) {
                // If the sum is greater than the target, move the right pointer to the left
                // to decrease the sum.
                right--;
            } else {
                // If the sum matches the target:
                // Store the 1-based indices in the result array.
                ans[0] = left + 1;
                ans[1] = right + 1;
                
                // Break out of the loop as we found the solution.
                break;
            }
        }

        // Return the result array containing the indices.
        return ans;
    }
}



 // Merge Sorted Array


class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Initialize pointers:
        // 'midx' points to the last element in the initial portion of nums1.
        int midx = m - 1;
        
        // 'nidx' points to the last element in nums2.
        int nidx = n - 1;
        
        // 'right' points to the last position in nums1 where the merged element will be placed.
        int right = m + n - 1;

        // Iterate while there are elements left in nums2 to merge.
        while (nidx >= 0) {
            // Compare elements from the end of nums1's initialized portion and nums2.
            if (midx >= 0 && nums1[midx] > nums2[nidx]) {
                // If the current element in nums1 is greater, place it at the 'right' position.
                nums1[right] = nums1[midx];
                
                // Move the pointer in nums1 backward.
                midx--;
            } else {
                // Otherwise, place the current element from nums2 at the 'right' position.
                nums1[right] = nums2[nidx];
                
                // Move the pointer in nums2 backward.
                nidx--;
            }
            
            // Move the 'right' pointer backward to fill the next position.
            right--;
        }
    }
}



// Sort List


class Solution {
    // Helper function to find the middle node of a linked list
    private ListNode mid(ListNode head) {
        // Base case: if the list is empty or has only one node, return the head
        if (head == null || head.next == null) return head;

        ListNode slow = head;  // Slow pointer to find the middle
        ListNode fast = head.next;  // Fast pointer to traverse the list twice as fast

        // Move the slow pointer one step and fast pointer two steps
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Slow pointer now points to the middle node
        return slow;
    }

    // Helper function to merge two sorted linked lists
    private ListNode mergeSort(ListNode l1, ListNode l2) {
        // Create a dummy node to simplify the merging process
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;  // Pointer to build the merged list

        // Compare nodes from both lists and attach the smaller one to the merged list
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;  // Attach l1's node
                l1 = l1.next;    // Move l1 to its next node
            } else {
                curr.next = l2;  // Attach l2's node
                l2 = l2.next;    // Move l2 to its next node
            }
            curr = curr.next;    // Move the pointer in the merged list
        }

        // Attach any remaining nodes from either list
        curr.next = (l1 != null) ? l1 : l2;

        // Return the merged list, skipping the dummy node
        return dummy.next;
    }

    // Main function to sort the linked list using merge sort
    public ListNode sortList(ListNode head) {
        // Base case: if the list is empty or has only one node, it's already sorted
        if (head == null || head.next == null) return head;

        // Find the middle node
        ListNode mid = mid(head);
        ListNode newNode = mid.next;  // The start of the right half
        mid.next = null;  // Split the list into two halves

        // Recursively sort both halves
        ListNode leftHalf = sortList(head);
        ListNode rightHalf = sortList(newNode);

        // Merge the two sorted halves and return the result
        return mergeSort(leftHalf, rightHalf);
    }
}

